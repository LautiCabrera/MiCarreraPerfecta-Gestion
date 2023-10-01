package Forms.wordskey;

// @author Enzo Rico

import Models.WordsKey;
import Utils.DDBBConnection;
import Utils.JsonDataFetcher;
import Utils.ResultSetIES9021;
import Models.Users;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;

public class words_key_update extends javax.swing.JFrame {

    JsonDataFetcher dataFetcher = new JsonDataFetcher(); 
    private words_key_interface parentForm;
    private String idWord;
    private String idUser;
    private int selectedUserID;
    
    public words_key_update() {
        initComponents();
        loadUsers();
        handleUserSelection();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtfield_modify = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        btn_cancel = new javax.swing.JButton();
        btn_modify = new javax.swing.JButton();
        comboBoxUsers = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Modificar palabra clave");

        txtfield_modify.setForeground(new java.awt.Color(153, 153, 153));

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        btn_cancel.setBackground(new java.awt.Color(255, 0, 0));
        btn_cancel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_cancel.setForeground(new java.awt.Color(255, 255, 255));
        btn_cancel.setText("Cancelar");
        btn_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelActionPerformed(evt);
            }
        });

        btn_modify.setBackground(new java.awt.Color(51, 204, 0));
        btn_modify.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_modify.setForeground(new java.awt.Color(255, 255, 255));
        btn_modify.setText("Modificar");
        btn_modify.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_modifyActionPerformed(evt);
            }
        });

        comboBoxUsers.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        comboBoxUsers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxUsersActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_cancel)
                .addGap(18, 18, 18)
                .addComponent(btn_modify, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(comboBoxUsers, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_cancel)
                    .addComponent(btn_modify)
                    .addComponent(comboBoxUsers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(137, 137, 137)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtfield_modify, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(138, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtfield_modify, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void setParent(words_key_interface parent){
        this.parentForm = parent;
    }
    
    public void setWordData(String idWord, String word, String idUser){
        this.idWord = idWord;
        txtfield_modify.setText(word);
        this.idUser = idUser;
    }
    
    private void loadUsers() {
        String tableName = "users";
        String whereClause = null;
        Class<Users> returnType = Users.class;
        ResultSetIES9021<Users> result = dataFetcher.fetchTableData(tableName, whereClause, returnType);

        if (result.getState()) {

            // Crear un mapa para asociar nombres con objetos WordsKey
            Map<String, Users> userMap = new HashMap<>();

            // Agregar cada usuario al comboBox y al mapa
            for (Users user : result.getDatos()) {
                comboBoxUsers.addItem(user.getName()); // Agregar el nombre al comboBox
                userMap.put(user.getName(), user); // Asociar el nombre con el objeto WordsKey
            }

            // Asignar el mapa al comboBox para acceder a los objetos WordsKey más adelante
            comboBoxUsers.putClientProperty("userMap", userMap);
        } else {
            System.out.println("error");
        }
    }
    
        private void handleUserSelection() {
        int selectedIndex = comboBoxUsers.getSelectedIndex();
        if (selectedIndex != -1) {
            String selectedUserName = comboBoxUsers.getItemAt(selectedIndex);
            Map<String, Users> userMap = (Map<String, Users>) comboBoxUsers.getClientProperty("userMap");

            if (userMap != null && userMap.containsKey(selectedUserName)) {
                Users selectedUser = userMap.get(selectedUserName);
                selectedUserID = selectedUser.getId_user();
            }
        }
    }
    
    private void btn_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelActionPerformed
        this.dispose();
        if (parentForm != null) {
            parentForm.setVisible(true);
        }
    }//GEN-LAST:event_btn_cancelActionPerformed

    private void btn_modifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_modifyActionPerformed
        // Obtener los nuevos valores de los campos de edición
        String word = txtfield_modify.getText();
        int idUserUpdate = selectedUserID;
        
        System.out.println("El ID que actualiza es; "+idUserUpdate);

        // Obtener la fecha actual
        java.util.Date currentDate = new java.util.Date();

        // Convertir la fecha actual a un formato de fecha adecuado
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String f_update = dateFormat.format(currentDate);

        // Crear la consulta SQL para la actualización en la tabla "words_key"
        String updateQuery = "UPDATE words_key SET word = '" + word + "', id_user_update = " + idUserUpdate + ", f_update = '" + f_update + "' WHERE id_word_key = " + idWord + ";";

        // Ejecutar la consulta utilizando el método SendQuery
        ResultSetIES9021 result = DDBBConnection.SendQuery(updateQuery);

        // Verificar el estado del resultado
        if (result.getState()) {
            JOptionPane.showMessageDialog(this, "La palabra clave se actualizó con éxito.", "Actualización Exitosa", JOptionPane.INFORMATION_MESSAGE);
            // Cierra la ventana de "words_key_update" después de la actualización
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo actualizar la palabra clave.", "Error de Actualización", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_modifyActionPerformed

    private void comboBoxUsersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxUsersActionPerformed
        handleUserSelection();
    }//GEN-LAST:event_comboBoxUsersActionPerformed

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
            java.util.logging.Logger.getLogger(words_key_update.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(words_key_update.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(words_key_update.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(words_key_update.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new words_key_update().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cancel;
    private javax.swing.JButton btn_modify;
    private javax.swing.JComboBox<String> comboBoxUsers;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField txtfield_modify;
    // End of variables declaration//GEN-END:variables
}
