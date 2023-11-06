package Forms.CampusForms;

import Models.University;
import Models.Users;
import Utils.DDBBConnection;
import Utils.JsonDataFetcher;
import Utils.ResultSetIES9021;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import java.util.HashMap;
import java.util.Map;

public class CampusUpdateForm extends javax.swing.JFrame {

    JsonDataFetcher dataFetcher = new JsonDataFetcher();
    private Campus_Interface_JF parentForm;
    private String idCampus;
    private String idUniversity;
    private String idUser;
    private int selectedUniversityId;
    private int selectedUserId;
    private int selectedMainValue = 1;

    public CampusUpdateForm() {
        initComponents();
        comboBoxMain.addItem("Si");
        comboBoxMain.addItem("No");
        setLocationRelativeTo(null);
    }

    public void setParent(Campus_Interface_JF parent) {
        this.parentForm = parent;
    }

    public void setCampusData(String idCampus, String idUniversity, String name, String location, String latitude, String longitude, String main, String www, String email, String idUser) {
        // Asigna los datos a los elementos correspondientes de tu formulario
        this.idCampus = idCampus;
        this.idUniversity = idUniversity;
        txtName.setText(name);
        txtLocation.setText(location);
        txtLatitude.setText(latitude);
        txtLongitude.setText(longitude);
        if ("1".equals(main)) {
            comboBoxMain.setSelectedItem("Si");
        } else if ("0".equals(main)) {
            comboBoxMain.setSelectedItem("No");
        }
        txtWww.setText(www);
        txtEmail.setText(email);
        this.idUser = idUser;
        initializeUniversitiesComboBox();
        initializeUsersComboBox();
        handleUniversitySelection();
        handleUserSelection();
        handleMainSelection();
    }

    private void initializeUniversitiesComboBox() {
        // Cargar todas las universidades
        String tableName = "university";
        String whereClause = null;
        Class<University> returnType = University.class;
        ResultSetIES9021<University> result = dataFetcher.fetchTableData(tableName, whereClause, returnType);

        if (result.getState()) {
            // Limpiar el comboBox
            comboBoxUniversity.removeAllItems();

            // Crear un mapa para asociar nombres de universidades con objetos University
            Map<String, University> universityMap = new HashMap<>();

            // Agregar cada universidad al comboBox y al mapa
            for (University university : result.getDatos()) {
                comboBoxUniversity.addItem(university.getName()); // Agregar el nombre al comboBox
                universityMap.put(university.getName(), university); // Asociar el nombre con el objeto University
            }

            // Asignar el mapa al comboBox para acceder a los objetos University más adelante
            comboBoxUniversity.putClientProperty("universityMap", universityMap);
            
            // Seleccionar la universidad por defecto
            if (idUniversity != null) {
                for (int i = 0; i < comboBoxUniversity.getItemCount(); i++) {
                    String universityName = comboBoxUniversity.getItemAt(i);
                    University university = universityMap.get(universityName); // Obtén el objeto University desde el mapa
                    if (idUniversity.equals(String.valueOf(university.getId()))) {
                        comboBoxUniversity.setSelectedIndex(i);
                        break;
                    }
                }
            }
        } 
    }

    private void initializeUsersComboBox() {
        // Cargar todas las universidades
        String tableName = "users";
        String whereClause = null;
        Class<Users> returnType = Users.class;
        ResultSetIES9021<Users> result = dataFetcher.fetchTableData(tableName, whereClause, returnType);

        if (result.getState()) {
            // Limpiar el comboBox
            comboBoxUsers.removeAllItems();

            // Crear un mapa para asociar nombres de universidades con objetos User
            Map<String, Users> userMap = new HashMap<>();

            // Agregar cada universidad al comboBox y al mapa
            for (Users user : result.getDatos()) {
                comboBoxUsers.addItem(user.getName()); // Agregar el nombre al comboBox
                userMap.put(user.getName(), user); // Asociar el nombre con el objeto User
            }

            // Asignar el mapa al comboBox para acceder a los objetos User más adelante
            comboBoxUsers.putClientProperty("userMap", userMap);
            
            // Seleccionar la universidad por defecto
            if (idUser != null) {
                for (int i = 0; i < comboBoxUsers.getItemCount(); i++) {
                    String userName = comboBoxUsers.getItemAt(i);
                    Users user = userMap.get(userName); // Obtén el objeto Users desde el mapa
                    if (idUser.equals(String.valueOf(user.getId_user()))) {
                        comboBoxUsers.setSelectedIndex(i);
                        break;
                    }
                }
            }
        } 
    }
    
    private void handleMainSelection() {
        String selectedMainOption = comboBoxMain.getSelectedItem().toString();
        if ("Si".equals(selectedMainOption)) {
            selectedMainValue = 1; // SI
        } else if ("No".equals(selectedMainOption)) {
            selectedMainValue = 0; // NO
        }
    }
    
    private void handleUniversitySelection() {
        int selectedIndex = comboBoxUniversity.getSelectedIndex();
        if (selectedIndex != -1) {
            String selectedUniversityName = comboBoxUniversity.getItemAt(selectedIndex);
            Map<String, University> universityMap = (Map<String, University>) comboBoxUniversity.getClientProperty("universityMap");

            if (universityMap != null && universityMap.containsKey(selectedUniversityName)) {
                University selectedUniversity = universityMap.get(selectedUniversityName);
                selectedUniversityId = selectedUniversity.getId();
            }
        }
    }
    
    private void handleUserSelection() {
        int selectedIndex = comboBoxUsers.getSelectedIndex();
        if (selectedIndex != -1) {
            String selectedUserName = comboBoxUsers.getItemAt(selectedIndex);
            Map<String, Users> userMap = (Map<String, Users>) comboBoxUsers.getClientProperty("userMap");

            if (userMap != null && userMap.containsKey(selectedUserName)) {
                Users selectedUser = userMap.get(selectedUserName);
                selectedUserId = selectedUser.getId_user();
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtName = new javax.swing.JTextField();
        txtLocation = new javax.swing.JTextField();
        txtLatitude = new javax.swing.JTextField();
        txtLongitude = new javax.swing.JTextField();
        txtWww = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        Save = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        label1 = new java.awt.Label();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        Cancel = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        comboBoxUniversity = new javax.swing.JComboBox<>();
        comboBoxMain = new javax.swing.JComboBox<>();
        comboBoxUsers = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });

        Save.setText("Guardar");
        Save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveActionPerformed(evt);
            }
        });

        jLabel2.setText("Universidad:");

        label1.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        label1.setMaximumSize(new java.awt.Dimension(50000, 50000));
        label1.setText("Actualización Campus");

        jLabel3.setText("Nombre:");

        jLabel4.setText("Localidad:");

        jLabel5.setText("Longitude:");

        jLabel6.setText("Latitude:");

        jLabel7.setText("Sede Principal:");

        Cancel.setText("Cancelar");
        Cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelActionPerformed(evt);
            }
        });

        jLabel8.setText("Página Web:");

        jLabel9.setText("Email:");

        jLabel11.setText("Actualizó:");

        comboBoxUniversity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxUniversityActionPerformed(evt);
            }
        });

        comboBoxMain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxMainActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3))
                .addGap(42, 42, 42)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtLatitude, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtLocation, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtName, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboBoxUniversity, javax.swing.GroupLayout.Alignment.LEADING, 0, 280, Short.MAX_VALUE))
                        .addGap(18, 38, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel11)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtLongitude, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtWww)
                    .addComponent(txtEmail)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(Cancel)
                        .addGap(18, 18, 18)
                        .addComponent(Save))
                    .addComponent(comboBoxMain, 0, 280, Short.MAX_VALUE)
                    .addComponent(comboBoxUsers, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(96, 96, 96))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(278, 278, 278)
                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(comboBoxUniversity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboBoxMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jLabel4)
                        .addGap(26, 26, 26)
                        .addComponent(jLabel5)
                        .addGap(26, 26, 26)
                        .addComponent(jLabel6))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3)
                                .addComponent(txtWww, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtLatitude, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)
                            .addComponent(comboBoxUsers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtLongitude, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(Save)
                                .addComponent(Cancel)))))
                .addContainerGap(50, Short.MAX_VALUE))
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

    private void SaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveActionPerformed
        // Obtener los nuevos valores de los campos de edición
        String name = txtName.getText();
        String location = txtLocation.getText();
        String latitude = txtLatitude.getText();
        String longitude = txtLongitude.getText();
        int mainValue = selectedMainValue;
        String www = txtWww.getText();
        String email = txtEmail.getText();
        int idUserUpdate = selectedUserId;
        
        System.out.println("el id que actualiza es; "+idUserUpdate);

        // Obtener la fecha actual
        java.util.Date currentDate = new java.util.Date();

        // Convertir la fecha actual a un formato de fecha adecuado
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String f_update = dateFormat.format(currentDate);

        // Crear la consulta SQL para la actualización en la tabla "campus"
        String updateQuery = "UPDATE campus SET name = '" + name + "', location = '" + location + "', latitude = " + Float.parseFloat(latitude) +
                             ", longitude = " + Float.parseFloat(longitude) + ", main = " + mainValue + ", www = '" + www + "', email = '" + email +
                             "', id_user_update = " + idUserUpdate + ", f_update = '" + f_update + "' WHERE id_campus = " + idCampus + ";";

        // Ejecutar la consulta utilizando el método SendQuery
        ResultSetIES9021 result = DDBBConnection.SendQuery(updateQuery);

        // Verificar el estado del resultado
        if (result.getState()) {
            JOptionPane.showMessageDialog(this, "Los datos del campus se actualizaron con éxito.", "Actualización Exitosa", JOptionPane.INFORMATION_MESSAGE);
            // Cierra la ventana de CampusUpdateForm después de la actualización
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo actualizar el campus.", "Error de Actualización", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_SaveActionPerformed

    private void CancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelActionPerformed
        this.dispose();
        if (parentForm != null) {
            parentForm.setVisible(true);
        }
    }//GEN-LAST:event_CancelActionPerformed

    private void comboBoxUniversityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxUniversityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBoxUniversityActionPerformed
        
    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed

    private void comboBoxMainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxMainActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBoxMainActionPerformed

    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(CampusUpdateForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CampusUpdateForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CampusUpdateForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CampusUpdateForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CampusUpdateForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Cancel;
    private javax.swing.JButton Save;
    private javax.swing.JComboBox<String> comboBoxMain;
    private javax.swing.JComboBox<String> comboBoxUniversity;
    private javax.swing.JComboBox<String> comboBoxUsers;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private java.awt.Label label1;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtLatitude;
    private javax.swing.JTextField txtLocation;
    private javax.swing.JTextField txtLongitude;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtWww;
    // End of variables declaration//GEN-END:variables
}
