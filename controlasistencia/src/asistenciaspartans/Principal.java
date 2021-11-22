/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asistenciaspartans;

import com.digitalpersona.onetouch.DPFPGlobal;
import com.digitalpersona.onetouch.DPFPSample;
import com.digitalpersona.onetouch.DPFPTemplate;
import com.jtattoo.plaf.acryl.AcrylLookAndFeel;
import ds.desktop.notify.DesktopNotify;
import java.awt.Color;
import java.awt.Frame;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

/**
 *
 * @author PC-Programador4
 */
public class Principal extends javax.swing.JFrame {

    Enrrolar enrrolar = null;
    FondoPanel fondo = new FondoPanel();

    public static String TEMPLATE_PROPERTY = "template";
    public DPFPTemplate template;
    private DPFPSample sampleAux;

    public class TemplateFileFilter extends javax.swing.filechooser.FileFilter {

        @Override
        public boolean accept(File f) {
            return f.getName().endsWith(".fpt");
        }

        @Override
        public String getDescription() {
            return "Fingerprint Template File (*.fpt)";
        }
    }

    public Principal() {
        this.setContentPane(fondo);
//        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setState(Frame.NORMAL);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setIconImage(new ImageIcon(getClass().getResource("/imagenes/logoDC_64.png")).getImage());
        this.setTitle("SPARTANS GYM");
        setResizable(false);
        enrrolar = new Enrrolar(this);
        initComponents();
        jPIndex.add(enrrolar);
        this.addPropertyChangeListener(TEMPLATE_PROPERTY, new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getNewValue() == evt.getOldValue()) {
                    return;
                }
                if (template != null) {
                    JOptionPane.showMessageDialog(Principal.this, "La plantilla de huellas dactilares está lista para la verificación de huellas dactilares.", "Inscripción de huellas dactilares", JOptionPane.INFORMATION_MESSAGE);
                    DesktopNotify.showDesktopMessage("Inscripción de huellas dactilares", "La plantilla de huellas dactilares está lista para la verificación de huellas dactilares.", DesktopNotify.SUCCESS, 10000L);
                }
            }
        });
    }

    private void onLoad() {
        JFileChooser chooser = new JFileChooser();
        chooser.addChoosableFileFilter(new TemplateFileFilter());
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                FileInputStream stream = new FileInputStream(chooser.getSelectedFile());
                byte[] data = new byte[stream.available()];
                stream.read(data);
                stream.close();
                DPFPTemplate t = DPFPGlobal.getTemplateFactory().createTemplate();
                t.deserialize(data);
                setTemplate(t);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getLocalizedMessage(), "Fingerprint loading", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void setTemplate(DPFPTemplate template) {
        DPFPTemplate old = this.template;
        this.template = template;
        firePropertyChange(TEMPLATE_PROPERTY, old, template);
    }

    public DPFPTemplate getTemplate() {
        return template;
    }

    public void setSampleAux(DPFPSample sample) {
        this.sampleAux = sample;
    }

    public DPFPSample getSampleAux() {
        return sampleAux;
    }

    public void onEnroll() {
        EnrollmentForm form = new EnrollmentForm(this);
        form.setVisible(true);
    }

    public void verificarUsuario(String huellaVerificion) throws MalformedURLException, IOException {
        System.out.println("huellaVerificion: " + huellaVerificion);
    }

    private void onVerify(String rs) {
        VerificationForm form = new VerificationForm(this, rs);
        form.setVisible(true);
    }

    private void onSalida(String rs) {
        SalidaForm salidaFormOBJ = new SalidaForm(this, rs);
        salidaFormOBJ.setVisible(true);
    }

    public void nuevoPanel(JPanel panelActual) {
        jPIndex.removeAll();
        jPIndex.add(panelActual);
        jPIndex.repaint();
        jPIndex.revalidate();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPIndex = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMVerificar = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMAcercaDe = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPIndex.setLayout(new java.awt.CardLayout());

        jMenu1.setText("Opciones");

        jMVerificar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        jMVerificar.setText("Entrada");
        jMVerificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMVerificarActionPerformed(evt);
            }
        });
        jMenu1.add(jMVerificar);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Ayuda");
        jMenu2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu2ActionPerformed(evt);
            }
        });

        jMAcercaDe.setText("Acerca de");
        jMAcercaDe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMAcercaDeActionPerformed(evt);
            }
        });
        jMenu2.add(jMAcercaDe);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPIndex, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPIndex, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMVerificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMVerificarActionPerformed
        try {
            Biometrico biometricoOBJ = new Biometrico();
            String rs = biometricoOBJ.listaHuellas();
            onVerify(rs);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMVerificarActionPerformed

    private void jMenu2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu2ActionPerformed

    }//GEN-LAST:event_jMenu2ActionPerformed

    private void jMAcercaDeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMAcercaDeActionPerformed
        AcercaDe abrirAcercaDe = new AcercaDe();
        abrirAcercaDe.setVisible(true);
    }//GEN-LAST:event_jMAcercaDeActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            Properties props = new Properties();
            props.put("logoString", "Opciones");
            AcrylLookAndFeel.setCurrentTheme(props);
            UIManager.setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel");
//            UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
//            UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");
        } catch (Exception e) {
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem jMAcercaDe;
    private javax.swing.JMenuItem jMVerificar;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPIndex;
    // End of variables declaration//GEN-END:variables
}
