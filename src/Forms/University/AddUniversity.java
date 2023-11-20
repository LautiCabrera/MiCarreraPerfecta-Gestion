package Forms.University;

import Models.University;
import Utils.DDBBConnection;
import Utils.JsonDataFetcher;
import Utils.ResultSetIES9021;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class AddUniversity extends javax.swing.JFrame {

    private boolean cancelFlag = false;

  //  DefaultTableModel modelo;

    public AddUniversity() {
        initComponents();
        setLocationRelativeTo(null);
        DefaultTableModel model=(DefaultTableModel) Tabla.getModel();
        Tabla.setModel(model);
        
        listar();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jSlider1 = new javax.swing.JSlider();
        jLabel5 = new javax.swing.JLabel();
        jSpinner1 = new javax.swing.JSpinner();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        TXTuniversity = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        BoxMagnamet = new javax.swing.JComboBox<>();
        BoxUsers = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tabla = new javax.swing.JTable();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        Actualizar = new javax.swing.JButton();

        jButton1.setText("jButton1");

        jButton2.setText("jButton2");

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(jList1);

        jScrollPane3.setViewportView(jTree1);

        jLabel5.setText("jLabel5");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("ADD University");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos"));
        jPanel1.setToolTipText("datos");
        jPanel1.setName("datos"); // NOI18N

        TXTuniversity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXTuniversityActionPerformed(evt);
            }
        });

        jLabel2.setText("University Name:");

        jLabel3.setText("ID Magnament:");

        jLabel4.setText("User Create:");

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        BoxMagnamet.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Publica", "Privada" }));
        BoxMagnamet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BoxMagnametActionPerformed(evt);
            }
        });

        BoxUsers.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Leandro Moyano", "Nicolas Basconcello", "Juan Salinas", "Amaya Dylan", "Amaya Rodrigo", "Arboit Ilko", "Arce Lucas", "Arenas Veronica", "Bustamante Enzo", "Bustos Pedro", "Cabrera Lautaro", "Castillo Joaquin", "Coria Adrian", "Fornasari Pablo", "Coria Agustin", "Garcia Dario", "Martorana Bruno", "Godoy Julian", "Olguin Bruno", "Ramirez Yoel", "Rico Enzo", "Tello Sebastian", "Urizar Arnaldo", "Sarmiento Marisa", "Saldeña Juan", "Rodriguez Federico", "Gogol Javier", "Nicolás Suarez" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel2)
                        .addComponent(jLabel3)
                        .addComponent(jLabel4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnGuardar)
                        .addGap(23, 23, 23)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCancelar)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(TXTuniversity)
                            .addComponent(BoxMagnamet, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(BoxUsers, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(195, 195, 195)))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(TXTuniversity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(BoxMagnamet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(BoxUsers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnCancelar))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("University"));

        Tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "University Name", "ID Magnement", "ID User Create", "ID User Update", "F Create", "F Update"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Tabla.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(Tabla);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        Actualizar.setText("Actualizar");
        Actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ActualizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(90, 90, 90)
                .addComponent(btnModificar)
                .addGap(18, 18, 18)
                .addComponent(btnEliminar)
                .addGap(35, 35, 35))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(8, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Actualizar)
                .addGap(45, 45, 45))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnModificar)
                    .addComponent(btnEliminar)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Actualizar)
                .addContainerGap(86, Short.MAX_VALUE))
        );

        jPanel1.getAccessibleContext().setAccessibleName("datos");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TXTuniversityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXTuniversityActionPerformed
        String name;
    }//GEN-LAST:event_TXTuniversityActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        cancelFlag = true;   
         dispose();// Presionar el botón de cancelar
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        guardar();
        listar();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        dispose();

        // Crear una instancia del formulario de modificación
        ModifyUniversity modifyForm = new ModifyUniversity();

        // Hacer visible el formulario de modificación
        modifyForm.setVisible(true);
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // Cerrar la ventana actual sin guardar cambios
        dispose();

        // Crear una instancia del formulario de eliminación
        DeleteUniversity deleteForm = new DeleteUniversity();

        // Hacer visible el formulario de eliminación
        deleteForm.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void ActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ActualizarActionPerformed
        listar();        // TODO add your handling code here:
    }//GEN-LAST:event_ActualizarActionPerformed

    private void BoxMagnametActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BoxMagnametActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BoxMagnametActionPerformed

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
            java.util.logging.Logger.getLogger(AddUniversity.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddUniversity.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddUniversity.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddUniversity.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddUniversity().setVisible(true);
            }
        });
    }

private void guardar() {
    String name = TXTuniversity.getText();

    if (name.isEmpty()) {
        JOptionPane.showMessageDialog(null, "El campo 'University Name' está vacío");
    } else {
        try {
            // Crear una instancia de University y establecer sus propiedades
            University university = new University();
            university.setName(name);

            // Obtener el valor seleccionado del JComboBox y asignar un valor numérico
            String selectedMagnament = (String) BoxMagnamet.getSelectedItem();
            int idManagement = 0;

            if (selectedMagnament.equals("Publica")) {
                idManagement = 2;
            } else if (selectedMagnament.equals("Privada")) {
                idManagement = 1;
            }

            university.setId_management(idManagement);

            // Obtener el ID del usuario seleccionado
            int idUser = ObtenerUsuario();
            university.setId_user_create(idUser);

            // Obtener la fecha actual en el formato necesario
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date currentDate = new Date(System.currentTimeMillis());
            String f_create = dateFormat.format(currentDate);
            university.setF_create(currentDate);
            university.setF_update(currentDate);

            // Insertar la universidad en la base de datos
            ResultSetIES9021 result = DDBBConnection.SendQuery("INSERT INTO university (name, id_management, id_user_create, f_create, f_update) VALUES ('" + university.getName() + "','" + university.getId_management() + "','" + university.getId_user_create() + "', '" + f_create + "','" + f_create + "');");

            if (result.getState()) {
                JOptionPane.showMessageDialog(this, "Universidad creada con éxito.", "Actualización Exitosa", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo crear la universidad.", "Error de Creación", JOptionPane.ERROR_MESSAGE);
            }
            listar();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al crear la universidad.", "Error de Creación", JOptionPane.ERROR_MESSAGE);
        }
    }
}


// Esta funcion sirve para rellenar la lista de Universidades
private void listar() {
    try {
        DefaultTableModel modelo = (DefaultTableModel) Tabla.getModel();
        modelo.setRowCount(0);
     
        List<String[]> result = JsonDataFetcher.selectQuery("*", "university", "");


        for (String[] datos : result) {
            System.out.println(Arrays.toString(datos));
            modelo.addRow(datos);
        }
    } catch (Exception e) {
        e.printStackTrace(); // Database error handling
        JOptionPane.showMessageDialog(this, "Error listing universities.", "Listing Error", JOptionPane.ERROR_MESSAGE);
    }
}

public int ObtenerUsuario() {
    String selectedUser = (String) BoxUsers.getSelectedItem();

    // Asignar el número de ID correspondiente al usuario seleccionado
    switch (selectedUser) {
        case "Leandro Moyano":
            return 1;
        case "Nicolas Basconcello":
            return 2;
        case "Juan Salinas":
            return 3;
        case "Amaya Dylan":
            return 4;
        case "Amaya Rodrigo":
            return 5;
        case "Arboit Ilko":
            return 6;
        case "Arce Lucas":
            return 7;
        case "Arenas Veronica":
            return 8;
        case "Bustamante Enzo":
            return 9;
        case "Bustos Pedro":
            return 10;
        case "Cabrera Lautaro":
            return 11;
        case "Castillo Joaquin":
            return 12;
        case "Coria Adrian":
            return 13;
        case "Fornasari Pablo":
            return 14;
        case "Coria Agustin":
            return 15;
        case "Garcia Dario":
            return 16;
        case "Martorana Bruno":
            return 20;
        case "Godoy Julian":
            return 19;
        case "Olguin Bruno":
            return 21;
        case "Ramirez Yoel":
            return 22;
        case "Rico Enzo":
            return 23;
        case "Tello Sebastian":
            return 24;
        case "Urizar Arnaldo":
            return 25;
        case "Sarmiento Marisa":
            return 26;
        case "Saldeña Juan":
            return 27;
        case "Rodriguez Federico":
            return 28;
        case "Gogol Javier":
            return 29;
        case "Nicolás Suarez":
            return 30;
        default:
            return 0; // Valor predeterminado o manejo de error
    }
}







    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Actualizar;
    private javax.swing.JComboBox<String> BoxMagnamet;
    private javax.swing.JComboBox<String> BoxUsers;
    private javax.swing.JTextField TXTuniversity;
    private javax.swing.JTable Tabla;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTree jTree1;
    // End of variables declaration//GEN-END:variables
}
