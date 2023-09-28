package Forms.CampusForms;

import Utils.DDBBConnection;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CampusUpdateForm extends javax.swing.JFrame {

    private Campus_Interface_JF parentForm;
    DDBBConnection con = new DDBBConnection();
    Connection connection = con.Conectar();
    ResultSet RS;
    private String idCampus;
    private String idUniversity;
    private Map<Integer, String> universityMap = new HashMap<>();

    public CampusUpdateForm() {
        initComponents();
        setLocationRelativeTo(null);
        initializeUniversityComboBox();
    }

    public void setParent(Campus_Interface_JF parent) {
        this.parentForm = parent;
    }
    
    public void setCampusData(String idCampus, String idUniversity, String name, String location, String latitude, String longitude, String main, String www, String email, String idUserUpdate, String fUpdate) {
        this.idCampus = idCampus;
        txtIdUniversity.setSelectedItem(universityMap.get(idUniversity));
        txtName.setText(name);
        txtLocation.setText(location);
        txtLatitude.setText(latitude);
        txtLongitude.setText(longitude);
        txtMain.setText(main);
        txtWww.setText(www);
        txtEmail.setText(email);
        txtIdUserUpdate.setText(idUserUpdate);
        txtFUpdate.setText(fUpdate);
    }
    
    private void initializeUniversityComboBox() {
    try {
        String sql = "SELECT id_university, name FROM university"; // Selecciona tanto el ID como el nombre
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        txtIdUniversity.removeAllItems(); // Elimina los elementos existentes en el JComboBox

        // Llena el JComboBox y el mapa
        while (resultSet.next()) {
            int idUniversity = resultSet.getInt("id_university"); // Obtén el ID como entero
            String nombreUniversidad = resultSet.getString("name");
            txtIdUniversity.addItem(nombreUniversidad);
            universityMap.put(idUniversity, nombreUniversidad); // Almacena el ID como entero en el mapa
        }
        resultSet.close();
        statement.close();
    } catch (SQLException ex) {
        ex.printStackTrace();
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
        txtMain = new javax.swing.JTextField();
        txtWww = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtIdUserUpdate = new javax.swing.JTextField();
        txtFUpdate = new javax.swing.JTextField();
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
        jLabel13 = new javax.swing.JLabel();
        txtIdUniversity = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtName.setText("jTextField3");

        txtLocation.setText("jTextField4");

        txtLatitude.setText("jTextField5");

        txtLongitude.setText("jTextField6");

        txtMain.setText("jTextField7");
        txtMain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMainActionPerformed(evt);
            }
        });

        txtWww.setText("jTextField8");

        txtEmail.setText("jTextField9");

        txtIdUserUpdate.setText("jTextField11");

        txtFUpdate.setText("jTextField13");

        Save.setText("Guardar");
        Save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveActionPerformed(evt);
            }
        });

        jLabel2.setText("ID Universidad:");

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

        jLabel11.setText("ID Usuario Actualizó:");

        jLabel13.setText("Fecha Actualización:");

        txtIdUniversity.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        txtIdUniversity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdUniversityActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3))
                .addGap(42, 42, 42)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtLatitude)
                    .addComponent(txtLocation)
                    .addComponent(txtLongitude)
                    .addComponent(txtName)
                    .addComponent(txtIdUniversity, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 320, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel11)
                            .addComponent(jLabel13))
                        .addGap(42, 42, 42)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtWww, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIdUserUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(Cancel)
                        .addGap(18, 18, 18)
                        .addComponent(Save)))
                .addGap(117, 117, 117))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(298, 298, 298)
                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(97, 97, 97)
                        .addComponent(jLabel2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtIdUniversity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel8)
                            .addComponent(txtWww, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtIdUserUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtFUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addGap(30, 30, 30)
                            .addComponent(jLabel4)
                            .addGap(26, 26, 26)
                            .addComponent(jLabel5)
                            .addGap(26, 26, 26)
                            .addComponent(jLabel6))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(txtLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(txtLatitude, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(txtLongitude, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Save)
                    .addComponent(Cancel))
                .addContainerGap(27, Short.MAX_VALUE))
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
        // Obtener los nuevos valores de los campos de edición
        String idCampusToUpdate = idCampus;
        String idUniversity = null;
        Object selectedUniversity = txtIdUniversity.getSelectedItem();
        if (selectedUniversity != null) {
            idUniversity = selectedUniversity.toString();
        }
        String name = txtName.getText();
        String location = txtLocation.getText();
        String latitude = txtLatitude.getText();
        String longitude = txtLongitude.getText();
        String main = txtMain.getText();
        String www = txtWww.getText();
        String email = txtEmail.getText();
        String idUserUpdate = txtIdUserUpdate.getText();
        String fUpdate = txtFUpdate.getText();
        

        // Realizar la actualización en la base de datos utilizando SQL
        String updateQuery = "UPDATE campus SET id_university = ?, name = ?, location = ?, latitude = ?, " +
        "longitude = ?, main = ?, www = ?, email = ?, id_user_update = ?, f_update = ? WHERE id_campus = ?";

        try {
            // Prepara la consulta SQL
            PreparedStatement statement = connection.prepareStatement(updateQuery);
            statement.setString(1, idUniversity);
            statement.setString(2, name);
            statement.setString(3, location);
            statement.setString(4, latitude);
            statement.setString(5, longitude);
            statement.setString(6, main);
            statement.setString(7, www);
            statement.setString(8, email);
            statement.setString(9, idUserUpdate);
            statement.setString(10, fUpdate);
            statement.setString(11, idCampusToUpdate);

            // Ejecuta la consulta SQL
            int rowsAffected = statement.executeUpdate();

            // Verifica si la actualización fue exitosa
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Los datos del campus se actualizaron con éxito.", "Actualización Exitosa", JOptionPane.INFORMATION_MESSAGE);
                // Cierra la ventana de CampusUpdateForm después de la actualización
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo actualizar el campus.", "Error de Actualización", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            // Manejo de errores de SQL
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al actualizar el campus: " + ex.getMessage(), "Error de SQL", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_SaveActionPerformed

    private void txtMainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMainActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMainActionPerformed

    private void CancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelActionPerformed
        this.dispose(); 
        if (parentForm != null) {
            parentForm.setVisible(true);
        }
    }//GEN-LAST:event_CancelActionPerformed

    private void txtIdUniversityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdUniversityActionPerformed
         idUniversity = universityMap.get(txtIdUniversity.getSelectedItem().toString());
    }//GEN-LAST:event_txtIdUniversityActionPerformed

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
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
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
    private javax.swing.JTextField txtFUpdate;
    private javax.swing.JComboBox<String> txtIdUniversity;
    private javax.swing.JTextField txtIdUserUpdate;
    private javax.swing.JTextField txtLatitude;
    private javax.swing.JTextField txtLocation;
    private javax.swing.JTextField txtLongitude;
    private javax.swing.JTextField txtMain;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtWww;
    // End of variables declaration//GEN-END:variables
}
