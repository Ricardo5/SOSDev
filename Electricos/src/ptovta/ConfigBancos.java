/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ptovta;
import java.io.*;
import java.util.*;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
/**
 *
 * @author Vanessa
 */
public class ConfigBancos extends javax.swing.JDialog {

    /**
     * Creates new form ConfigBancos
     */
    public ConfigBancos(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        CargarArchivo();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jBGuardar = new javax.swing.JButton();
        jTInstancia = new javax.swing.JTextField();
        jTUsuario = new javax.swing.JTextField();
        jTPassword = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jBSalir = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jBGuardar.setText("Guardar");
        jBGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBGuardarActionPerformed(evt);
            }
        });

        jTInstancia.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTInstanciaFocusLost(evt);
            }
        });
        jTInstancia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTInstanciaMouseClicked(evt);
            }
        });
        jTInstancia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTInstanciaActionPerformed(evt);
            }
        });

        jTUsuario.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTUsuarioFocusLost(evt);
            }
        });
        jTUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTUsuarioActionPerformed(evt);
            }
        });

        jTPassword.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTPasswordFocusLost(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("*Instancia de CONTPAQ I BANCOS:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Usuario:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Contraseña:");

        jBSalir.setText("Salir");
        jBSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBSalirActionPerformed(evt);
            }
        });

        jLabel4.setText(" NomServidor\\InstanciaSQL");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jBGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jBSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jTInstancia, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                    .addComponent(jTUsuario)
                    .addComponent(jTPassword))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTInstancia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jBGuardar))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jBSalir)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBGuardarActionPerformed
  

        if(jTInstancia.getText().replace(" ", "").trim().compareTo("")==0)
        {
            /*Coloca el borde rojo*/                               
            jTInstancia.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.RED));
            
            /*Mensajea*/
            JOptionPane.showMessageDialog(null, "La instancia esta vacia.", "Campo vacio", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(getClass().getResource(Star.sRutIconAd)));
            
            /*Pon el foco del teclado en el campo de edición y regresa*/
            jTInstancia.grabFocus();            
            return;            
        }
       
         Properties prop = new Properties();
        OutputStream output = null;
        try {
            output = new FileOutputStream("configB.properties");
             prop.setProperty("rutaServidor",jTInstancia.getText());
            prop.setProperty("Usuario", jTUsuario.getText());
            prop.setProperty("Contraseña", jTPassword.getText());
        
            prop.store(output, null);
            
        } catch(IOException e) {
            e.printStackTrace();
        }
        finally{
            try{
                if(output!=null)
                    output.close();
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
                return;
            }
        }
       
        this.dispose();
    }//GEN-LAST:event_jBGuardarActionPerformed
        
        
        
        
    private void jTInstanciaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTInstanciaFocusLost
          /*Coloca el borde negro si tiene datos, caso contrario de rojo*/                               
        if(jTInstancia.getText().compareTo("")!=0)
            jTInstancia.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204,204,255)));        // TODO add your handling code here:
    }//GEN-LAST:event_jTInstanciaFocusLost

    private void jTInstanciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTInstanciaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTInstanciaActionPerformed

    private void jBSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBSalirActionPerformed
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jBSalirActionPerformed

    private void jTUsuarioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTUsuarioFocusLost
         if(jTUsuario.getText().compareTo("")!=0)
            jTUsuario.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204,204,255)));           // TODO add your handling code here:
    }//GEN-LAST:event_jTUsuarioFocusLost

    private void jTPasswordFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTPasswordFocusLost
         if(jTPassword.getText().compareTo("")!=0)
            jTPassword.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204,204,255)));               // TODO add your handling code here:
    }//GEN-LAST:event_jTPasswordFocusLost

    private void jTInstanciaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTInstanciaMouseClicked
                // TODO add your handling code here:
    }//GEN-LAST:event_jTInstanciaMouseClicked

    private void jTUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTUsuarioActionPerformed
    private void CargarArchivo(){
         String sFichero = "configB.properties";
        File fichero = new File(sFichero);
        if(fichero.exists()){
            Properties prop = new Properties();
            InputStream is = null;
            try {
                is = new FileInputStream("configB.properties");
                prop.load(is);
            } catch(IOException e) {
                System.out.println(e.toString());
            }
            jTInstancia.setText(prop.getProperty("rutaServidor"));
            jTUsuario.setText(prop.getProperty("Usuario"));
            jTPassword.setText(prop.getProperty("Contraseña"));
        }
    }
  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBGuardar;
    private javax.swing.JButton jBSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField jTInstancia;
    private javax.swing.JTextField jTPassword;
    private javax.swing.JTextField jTUsuario;
    // End of variables declaration//GEN-END:variables
}
