package Forms.CampusForms;

import Models.University;
import Models.Users;
import Utils.DDBBConnection;
import Utils.JsonDataFetcher;
import Utils.ResultSetIES9021;
import javax.swing.JOptionPane;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class CampusAddForm extends javax.swing.JFrame {

    JsonDataFetcher dataFetcher = new JsonDataFetcher();
    private Campus_Interface_JF parentForm;
    private int selectedUniversityId;
    private int selectedUserId;
    private int selectedMainValue = 1;

    public CampusAddForm() {
        initComponents();
        loadUniversities();
        loadUsers();
        setLocationRelativeTo(null);
        handleUniversitySelection();
        handleUserSelection();
        handleMainSelection();
    }

    public void setParent(Campus_Interface_JF parent) {
        this.parentForm = parent;
    }
    
    private void loadUniversities() {
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
        } else {
            System.out.println("error");
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
    
    private void loadUsers() {
        String tableName = "users";
        String whereClause = null;
        Class<Users> returnType = Users.class;
        ResultSetIES9021<Users> result = dataFetcher.fetchTableData(tableName, whereClause, returnType);

        if (result.getState()) {

            // Crear un mapa para asociar nombres de universidades con objetos University
            Map<String, Users> userMap = new HashMap<>();

            // Agregar cada universidad al comboBox y al mapa
            for (Users user : result.getDatos()) {
                comboBoxUsers.addItem(user.getName()); // Agregar el nombre al comboBox
                userMap.put(user.getName(), user); // Asociar el nombre con el objeto University
            }

            // Asignar el mapa al comboBox para acceder a los objetos University más adelante
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
                selectedUserId = selectedUser.getId_user();
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
        jLabel10 = new javax.swing.JLabel();
        comboBoxUniversity = new javax.swing.JComboBox<>();
        comboBoxMain = new javax.swing.JComboBox<>();
        comboBoxUsers = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNameActionPerformed(evt);
            }
        });

        txtLatitude.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLatitudeActionPerformed(evt);
            }
        });

        txtLongitude.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLongitudeActionPerformed(evt);
            }
        });

        txtWww.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtWwwActionPerformed(evt);
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
        label1.setText("Creación Campus");

        jLabel3.setText("Nombre:");

        jLabel4.setText("Localidad:");

        jLabel5.setText("Longitud:");

        jLabel6.setText("Latitud:");

        jLabel7.setText("¿Es sede principal?");

        Cancel.setText("Cancelar");
        Cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelActionPerformed(evt);
            }
        });

        jLabel8.setText("Página Web:");

        jLabel9.setText("Email:");

        jLabel10.setText("Creador:");

        comboBoxUniversity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxUniversityActionPerformed(evt);
            }
        });

        comboBoxMain.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Si", "No" }));
        comboBoxMain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxMainActionPerformed(evt);
            }
        });

        comboBoxUsers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxUsersActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel2))
                        .addGap(42, 42, 42)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtName, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                            .addComponent(txtLocation)
                            .addComponent(txtLatitude)
                            .addComponent(txtLongitude)
                            .addComponent(comboBoxUniversity, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel10))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(Cancel)
                                .addGap(18, 18, 18)
                                .addComponent(Save))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(comboBoxMain, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtWww, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(comboBoxUsers, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(350, 350, 350)
                        .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(59, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboBoxMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(39, 39, 39)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel2)
                                .addComponent(comboBoxUniversity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel9)
                                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel8)
                                .addComponent(txtWww, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtLatitude, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(comboBoxUsers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtLongitude, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Save)
                        .addComponent(Cancel)))
                .addContainerGap(65, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveActionPerformed
        // Obtener los nuevos valores de los campos de creación
        String name = txtName.getText();
        String location = txtLocation.getText();
        String latitude = txtLatitude.getText();
        String longitude = txtLongitude.getText();
        int mainValue = selectedMainValue;
        String www = txtWww.getText();
        String email = txtEmail.getText();
        int idUserCreate = selectedUserId;
        // Obtener la fecha actual
        java.util.Date currentDate = new java.util.Date();
        // Convertir la fecha actual a un formato de fecha adecuado
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String f_create = dateFormat.format(currentDate);
        
        // Crear la consulta SQL para la inserción en la tabla "campus"
        String insertQuery = "INSERT INTO campus (id_university, name, location, latitude, longitude, main, www, email, id_user_create, id_user_update, f_create, f_update) VALUES " +
                            "(" + selectedUniversityId + ", '" + name + "', '" + location + "', " + Float.parseFloat(latitude) + ", " + Float.parseFloat(longitude) + ", " + 
                            mainValue + ", '" + www + "', '" + email + "', " + idUserCreate + ", " + idUserCreate + ", '" + f_create + "', '" + f_create + "');";

        // Ejecutar la consulta utilizando el método SendQuery
        ResultSetIES9021 result = DDBBConnection.SendQuery(insertQuery);

        // Verificar el estado del resultado
        if (result.getState()) {
            JOptionPane.showMessageDialog(this, "Campus creado con éxito.", "Actualización Exitosa", JOptionPane.INFORMATION_MESSAGE);
            // Cierra la ventana de CampusAddForm después de la inserción
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo crear el campus.", "Error de Creación", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_SaveActionPerformed

    private void CancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelActionPerformed
        this.dispose(); 
        if (parentForm != null) {
            parentForm.setVisible(true);
        }
    }//GEN-LAST:event_CancelActionPerformed

    private void comboBoxUniversityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxUniversityActionPerformed
        handleUniversitySelection();
    }//GEN-LAST:event_comboBoxUniversityActionPerformed

    private void comboBoxMainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxMainActionPerformed
        handleMainSelection();
    }//GEN-LAST:event_comboBoxMainActionPerformed

    private void comboBoxUsersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxUsersActionPerformed
        handleUserSelection();
    }//GEN-LAST:event_comboBoxUsersActionPerformed

    private void txtNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNameActionPerformed

    private void txtLatitudeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLatitudeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLatitudeActionPerformed

    private void txtLongitudeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLongitudeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLongitudeActionPerformed

    private void txtWwwActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtWwwActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtWwwActionPerformed

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
            java.util.logging.Logger.getLogger(CampusAddForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CampusAddForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CampusAddForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CampusAddForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CampusAddForm().setVisible(true);
            }
        });
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Cancel;
    private javax.swing.JButton Save;
    private javax.swing.JComboBox<String> comboBoxMain;
    private javax.swing.JComboBox<String> comboBoxUniversity;
    private javax.swing.JComboBox<String> comboBoxUsers;
    private javax.swing.JLabel jLabel10;
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
