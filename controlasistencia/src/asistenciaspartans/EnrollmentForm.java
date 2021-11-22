package asistenciaspartans;

import com.digitalpersona.onetouch.*;
import com.digitalpersona.onetouch.processing.*;
import ds.desktop.notify.DesktopNotify;
import java.awt.*;
import javax.swing.JOptionPane;

public class EnrollmentForm extends CaptureForm {

    private DPFPEnrollment enroller = DPFPGlobal.getEnrollmentFactory().createEnrollment();

    EnrollmentForm(Frame owner) {
        super(owner);
    }

    @Override
    protected void init() {
        super.init();
        this.setTitle("Fingerprint Enrollment");
        updateStatus();
    }

    @Override
    protected void process(DPFPSample sample) {
        super.process(sample);
        // Process the sample and create a feature set for the enrollment purpose.
        DPFPFeatureSet features = extractFeatures(sample, DPFPDataPurpose.DATA_PURPOSE_ENROLLMENT);

        // Check quality of the sample and add to enroller if it's good
        if (features != null) {
            try {
                makeReport("Se creó el conjunto de funciones de huellas digitales.");
                enroller.addFeatures(features);		// Add feature set to template.
            } catch (DPFPImageQualityException ex) {
            } finally {
                updateStatus();

                // Check if template has been created.
                switch (enroller.getTemplateStatus()) {
                    case TEMPLATE_STATUS_READY:	// report success and stop capturing
                        stop();
                        ((Principal) getOwner()).setTemplate(enroller.getTemplate());
                        ((Principal) getOwner()).setSampleAux(sample);
                        setPrompt("Haga clic en Cerrar y luego en Verificación de huellas digitales.");
                        break;

                    case TEMPLATE_STATUS_FAILED:	// report failure and restart capturing
                        enroller.clear();
                        stop();
                        updateStatus();
                        ((Principal) getOwner()).setTemplate(null);
                        JOptionPane.showMessageDialog(EnrollmentForm.this, "La plantilla de huellas dactilares no es válida. Repita el registro de huellas dactilares.", "Inscripción de huellas dactilares", JOptionPane.ERROR_MESSAGE);
                        DesktopNotify.showDesktopMessage("Inscripción de huellas dactilares", "La plantilla de huellas dactilares no es válida. Repita el registro de huellas dactilares.", DesktopNotify.FAIL, 10000L);
                        start();
                        break;
                }
            }
        }
    }

    private void updateStatus() {
        // Show number of samples needed.
        setStatus(String.format("Se necesitan muestras de huellas dactilares: %1$s", enroller.getFeaturesNeeded()));
    }

}
