package asistenciaspartans;

import com.digitalpersona.onetouch.*;
import com.digitalpersona.onetouch.processing.DPFPFeatureExtraction;
import com.digitalpersona.onetouch.processing.DPFPImageQualityException;
import com.digitalpersona.onetouch.verification.*;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import ds.desktop.notify.DesktopNotify;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SalidaForm extends CaptureForm {

    Biometrico biometricoOBJ = new Biometrico();

    private final DPFPVerification verificador = DPFPGlobal.getVerificationFactory().createVerification();
    String respuestaServidor;

    SalidaForm(Frame owner, String rs) {
        super(owner);
        respuestaServidor = rs;
    }

    @Override
    protected void init() {
        super.init();
        this.setTitle(".:SALIDA:.");
        updateStatus(0);
    }

    @Override
    protected void process(DPFPSample sample) {
        super.process(sample);

        JsonParser parser = new JsonParser();
        JsonArray list = parser.parse(respuestaServidor).getAsJsonArray();

        DPFPFeatureExtraction extractor = DPFPGlobal.getFeatureExtractionFactory().createFeatureExtraction();
        int encontrada = 0;
        for (JsonElement jsonElement : list) {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            DPFPTemplate referenceTemplate = DPFPGlobal.getTemplateFactory().createTemplate();
            byte[] templateBuffer = Base64.getDecoder().decode(jsonObject.get("huella").getAsString());

            referenceTemplate.deserialize(templateBuffer);
            DPFPFeatureSet featuresVerification = null;
            try {
                featuresVerification = extractor.createFeatureSet(sample, DPFPDataPurpose.DATA_PURPOSE_VERIFICATION);
            } catch (DPFPImageQualityException ex) {
                Logger.getLogger(SalidaForm.class.getName()).log(Level.SEVERE, null, ex);
            }
            DPFPVerificationResult resultado = verificador.verify(featuresVerification, referenceTemplate);

            if (resultado.isVerified()) {
                String documento = jsonObject.get("documentoUsuario").getAsString();
                String nombre = jsonObject.get("nombreUsuario").getAsString();
                String cargo = jsonObject.get("cargoUsuario").getAsString();
                DesktopNotify.showDesktopMessage("SALIDA", "Usuario: " + nombre, DesktopNotify.SUCCESS, 10000L);
                ReproducirSonido("src/audios/salida.wav");
                try {
                    biometricoOBJ.registroSalida(documento, nombre, cargo);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(SalidaForm.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(SalidaForm.class.getName()).log(Level.SEVERE, null, ex);
                }
                encontrada = 1;
                break;
            }
        }
        if (encontrada == 1) {
            System.out.println("OK");

        } else {
            System.out.println("ERROR");
            DesktopNotify.showDesktopMessage("Asistencias", "Usuario no existe", DesktopNotify.ERROR, 10000L);
            ReproducirSonido("src/audios/usuarioNoRegistrado.wav");
        }
    }

    private void updateStatus(int FAR) {
        // Show "False accept rate" value
        setStatus(String.format("Verdadero, Falso (FAR) = %1$s", FAR));
    }

    public void ReproducirSonido(String nombreSonido) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(nombreSonido).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            System.out.println("Error al reproducir el sonido.");
        }
    }

}
