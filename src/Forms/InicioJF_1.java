package Forms;

import Models.Inicio;
import java.awt.Color;
import java.util.Arrays;
import javax.swing.JOptionPane;

public class InicioJF extends javax.swing.JFrame {

    InicioJFMethods IJFM;
   
    public InicioJF() {
        initComponents();
        IJFM = new InicioJFMethods();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        BTNIngresar = new javax.swing.JButton();
        TXTEmail = new javax.swing.JTextField();
        PSWInicio = new javax.swing.JPasswordField();
        CHBPassword = new javax.swing.JCheckBox();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Lucida Console", 0, 24)); // NOI18N
        jLabel1.setText("Mi Carrera Perfecta");

        BTNIngresar.setText("Ingresar");
        BTNIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNIngresarActionPerformed(evt);
            }
        });

        TXTEmail.setForeground(Color.lightGray);
        TXTEmail.setText("E-mail");
        TXTEmail.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                TXTEmailFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                TXTEmailFocusLost(evt);
            }
        });

        PSWInicio.setForeground(Color.lightGray);
        PSWInicio.setText("Password");
        PSWInicio.setToolTipText("");
        PSWInicio.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                PSWInicioFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                PSWInicioFocusLost(evt);
            }
        });

        CHBPassword.setText("Show Password");
        CHBPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CHBPasswordActionPerformed(evt);
            }
        });

        jButton1.setText("Recuperar Contraseña");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(112, 112, 112)
                        .addComponent(CHBPassword)))
                .addContainerGap(60, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(BTNIngresar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PSWInicio)
                    .addComponent(TXTEmail)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(97, 97, 97))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(54, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(52, 52, 52)
                .addComponent(TXTEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(PSWInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(CHBPassword)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BTNIngresar)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(95, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CHBPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CHBPasswordActionPerformed
        // TODO add your handling code here:
        if(CHBPassword.isSelected()){
            PSWInicio.setEchoChar((char)0);
        } else {
            PSWInicio.setEchoChar('*');
        }
    }//GEN-LAST:event_CHBPasswordActionPerformed

    private void TXTEmailFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TXTEmailFocusGained
        // TODO add your handling code here:
        if(TXTEmail.getForeground().equals(Color.lightGray)){
            TXTEmail.setForeground(Color.BLACK);
            TXTEmail.setText("");
        }
    }//GEN-LAST:event_TXTEmailFocusGained

    private void TXTEmailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TXTEmailFocusLost
        // TODO add your handling code here:
        if(TXTEmail.getText().equals("")){
            TXTEmail.setForeground(Color.lightGray);
            TXTEmail.setText("E-mail");
        }
    }//GEN-LAST:event_TXTEmailFocusLost

    private void PSWInicioFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_PSWInicioFocusGained
        // TODO add your handling code here:
        if(PSWInicio.getForeground().equals(Color.lightGray)){
            PSWInicio.setForeground(Color.BLACK);
            PSWInicio.setText("");
        }
    }//GEN-LAST:event_PSWInicioFocusGained

    private void PSWInicioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_PSWInicioFocusLost
        // TODO add your handling code here:
        if(Arrays.toString(PSWInicio.getPassword()).equals("[]")){
            PSWInicio.setForeground(Color.lightGray);
            PSWInicio.setText("Password");
        }
    }//GEN-LAST:event_PSWInicioFocusLost

    private void BTNIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNIngresarActionPerformed
        // TODO add your handling code here:
        if(TXTEmail.getForeground().equals(Color.BLACK)){
            Inicio MCP = new Inicio();
            String Email=TXTEmail.getText();
            MCP.Inicio(Email);
        }else{JOptionPane.showMessageDialog(null,"Faltan datos");}
    }//GEN-LAST:event_BTNIngresarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if(TXTEmail.getForeground().equals(Color.BLACK)){
        //mandar String a XFuncion y que compare si existe dicho correo, si existe que le envie la nueva contraseña al correo
            if(IJFM.Existe(TXTEmail.getText())){JOptionPane.showMessageDialog(this,"Enviado");}
            else{JOptionPane.showMessageDialog(this, "Error");}
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InicioJF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InicioJF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InicioJF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InicioJF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InicioJF().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTNIngresar;
    private javax.swing.JCheckBox CHBPassword;
    private javax.swing.JPasswordField PSWInicio;
    private javax.swing.JTextField TXTEmail;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
