/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Forms;

import java.awt.Color;
import Models.Career_Word_Key;

/**
 *
 * @author BTF
 */
public class Career_Word_Key_JF extends javax.swing.JFrame {

    /**
     * Creates new form C_W_K_JF
     */
    Career_Word_Key CWK;
    int User;
    
    public Career_Word_Key_JF() {
        initComponents();
    }
    
    
    public Career_Word_Key_JF(boolean OP,Object[] Datos,int User){
        initComponents();
        //OP=true == ADD;; OP=false == Modify
        Configuration(OP,Datos);
        this.User=User;
        this.setVisible(true);
    }
    
    private void Configuration(boolean OP,Object[] Datos){
        if(OP){
            this.setTitle("Añadir Career Word Key");
            CWK=new Career_Word_Key();
            CHKContinue.setEnabled(OP);
            CWK.setId_user_create(User);
            CWK.setF_create("current_time()");
        }else{
            CWK=new Career_Word_Key(Datos);
            this.setTitle("Modificar Career Word Key");
            TXTCWK.setText(Datos[0].toString());
            TXTCareer.setText(Datos[1].toString());
            TXTWord_Key.setText(Datos[2].toString());
        }
        CWK.setId_user_update(User);
        CWK.setF_update("current_time()");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        TXTWord_Key = new javax.swing.JTextField();
        TXTCareer = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        TXTCWK = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        BTNCancel = new javax.swing.JButton();
        BTNAccept = new javax.swing.JButton();
        CHKContinue = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Editable con BTN");

        jLabel3.setText("id_word_key");
        jLabel3.setMaximumSize(new java.awt.Dimension(105, 16));
        jLabel3.setMinimumSize(new java.awt.Dimension(105, 16));
        jLabel3.setPreferredSize(new java.awt.Dimension(105, 16));

        TXTWord_Key.setForeground(java.awt.Color.lightGray);
        TXTWord_Key.setText("id word key");
        TXTWord_Key.setMaximumSize(new java.awt.Dimension(120, 24));
        TXTWord_Key.setMinimumSize(new java.awt.Dimension(120, 24));
        TXTWord_Key.setPreferredSize(new java.awt.Dimension(120, 24));
        TXTWord_Key.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                TXTWord_KeyFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                TXTWord_KeyFocusLost(evt);
            }
        });

        TXTCareer.setForeground(java.awt.Color.lightGray);
        TXTCareer.setText("id career");
        TXTCareer.setMaximumSize(new java.awt.Dimension(120, 24));
        TXTCareer.setMinimumSize(new java.awt.Dimension(120, 24));
        TXTCareer.setPreferredSize(new java.awt.Dimension(120, 24));
        TXTCareer.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                TXTCareerFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                TXTCareerFocusLost(evt);
            }
        });

        jLabel2.setText("id_career");
        jLabel2.setMaximumSize(new java.awt.Dimension(105, 16));
        jLabel2.setMinimumSize(new java.awt.Dimension(105, 16));
        jLabel2.setPreferredSize(new java.awt.Dimension(105, 16));

        TXTCWK.setEditable(false);
        TXTCWK.setForeground(java.awt.Color.lightGray);
        TXTCWK.setText("Auto_Asignado");
        TXTCWK.setMaximumSize(new java.awt.Dimension(120, 24));
        TXTCWK.setMinimumSize(new java.awt.Dimension(120, 24));
        TXTCWK.setPreferredSize(new java.awt.Dimension(120, 24));

        jLabel1.setText("id_career_word_key");
        jLabel1.setMaximumSize(new java.awt.Dimension(105, 16));
        jLabel1.setMinimumSize(new java.awt.Dimension(105, 16));
        jLabel1.setPreferredSize(new java.awt.Dimension(105, 16));

        BTNCancel.setText("Cancelar");
        BTNCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNCancelActionPerformed(evt);
            }
        });

        BTNAccept.setText("Aceptar");
        BTNAccept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNAcceptActionPerformed(evt);
            }
        });

        CHKContinue.setText("Continuar");
        CHKContinue.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TXTCareer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TXTWord_Key, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TXTCWK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(CHKContinue)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                        .addComponent(BTNAccept)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BTNCancel)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TXTCWK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TXTCareer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TXTWord_Key, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BTNCancel)
                    .addComponent(BTNAccept)
                    .addComponent(CHKContinue))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TXTCareerFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TXTCareerFocusGained
        // TODO add your handling code here:
        if(TXTCareer.getForeground().equals(Color.lightGray)){
            TXTCareer.setForeground(Color.BLACK);
            TXTCareer.setText("");
        }
    }//GEN-LAST:event_TXTCareerFocusGained

    private void TXTCareerFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TXTCareerFocusLost
        // TODO add your handling code here:
        if(TXTCareer.getText().isEmpty()||TXTCareer.getText().isBlank()){
            TXTCareer.setForeground(Color.lightGray);
            TXTCareer.setText("id career");
        }
    }//GEN-LAST:event_TXTCareerFocusLost

    private void TXTWord_KeyFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TXTWord_KeyFocusGained
        // TODO add your handling code here:
        if(TXTWord_Key.getForeground().equals(Color.lightGray)){
            TXTWord_Key.setForeground(Color.BLACK);
            TXTWord_Key.setText("");
        }
    }//GEN-LAST:event_TXTWord_KeyFocusGained

    private void TXTWord_KeyFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TXTWord_KeyFocusLost
        // TODO add your handling code here:
        if(TXTWord_Key.getText().isEmpty()||TXTWord_Key.getText().isBlank()){
            TXTWord_Key.setForeground(Color.lightGray);
            TXTWord_Key.setText("id word key");
        }
    }//GEN-LAST:event_TXTWord_KeyFocusLost

    private void BTNAcceptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNAcceptActionPerformed
        // TODO add your handling code here:
        //Crea un CWK file y lo manda desde metodos internos, no usar CWKMethods
        if(this.getTitle().equals("Añadir Career Word Key")){
            CWK.create();
        }else{
            //CWK.update();
        }
        
        if(CHKContinue.isEnabled()&&CHKContinue.isSelected()){}
    }//GEN-LAST:event_BTNAcceptActionPerformed

    private void BTNCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNCancelActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_BTNCancelActionPerformed

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
            java.util.logging.Logger.getLogger(Career_Word_Key_JF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Career_Word_Key_JF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Career_Word_Key_JF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Career_Word_Key_JF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Career_Word_Key_JF().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTNAccept;
    private javax.swing.JButton BTNCancel;
    private javax.swing.JCheckBox CHKContinue;
    private javax.swing.JTextField TXTCWK;
    private javax.swing.JTextField TXTCareer;
    private javax.swing.JTextField TXTWord_Key;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
