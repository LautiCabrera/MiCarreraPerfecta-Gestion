package Forms.Range;

import Utils.DDBBConnection;
import Utils.JsonDataFetcher;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.Arrays;

public class Range extends javax.swing.JFrame {

    public Range() {
        initComponents();
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("RANGE");
        JTable1.setModel(modelo); // Asigna el modelo al JTable
        cargarRangos();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        JTable1 = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldIdCarrera = new javax.swing.JTextField();
        buscarPorIdButton = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        modificarButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 255));
        jLabel1.setText("RANGE");
        jLabel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        JTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(JTable1);

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel4.setText("Búsqueda de carrera por ID");

        jTextFieldIdCarrera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldIdCarreraActionPerformed(evt);
            }
        });

        buscarPorIdButton.setText("Buscar");
        buscarPorIdButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarPorIdButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextFieldIdCarrera, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buscarPorIdButton)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextFieldIdCarrera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buscarPorIdButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton3.setBackground(new java.awt.Color(255, 102, 102));
        jButton3.setText("Eliminar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(204, 204, 255));
        jButton2.setText("Crear");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setText("Filtro");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        modificarButton.setBackground(new java.awt.Color(255, 255, 153));
        modificarButton.setText("Modificar");
        modificarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificarButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(modificarButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3)
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3)
                    .addComponent(jButton2)
                    .addComponent(jButton1)
                    .addComponent(modificarButton))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 616, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addGap(616, 616, 616))
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 124, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void modificarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificarButtonActionPerformed
        // TODO add your handling code here:
        System.out.println("Botón de Modificar presionado"); // Para verificar si el botón se presiona correctamente
        int selectedRow = JTable1.getSelectedRow();
        System.out.println("Fila seleccionada: " + selectedRow); // Para verificar si se selecciona una fila

        if (selectedRow == -1) {
            System.out.println("Ninguna fila seleccionada"); // Para verificar si se selecciona una fila
            JOptionPane.showMessageDialog(this, "Selecciona una carrera para modificar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        } else {

            String selectedRange = (String) jComboBox2.getSelectedItem();
            int idcarrera = Integer.parseInt(JTable1.getValueAt(selectedRow, 0).toString());
            int confirmacion = JOptionPane.showConfirmDialog(this, "¿Estás seguro? Se modificará el rango de la carrera seleccionada.", "Confirmar Acción", JOptionPane.YES_NO_OPTION);
            int newIdRange = 0;

            if (selectedRange.equals("REGIONAL")) {
                newIdRange = 1;
            } else if (selectedRange.equals("NACIONAL")) {
                newIdRange = 2;
            } else if (selectedRange.equals("INTERNACIONAL")) {
                newIdRange = 3;
            }
            if (confirmacion == JOptionPane.YES_OPTION) {
                if (newIdRange > 0) {
                    try {
                        String updateQuery = "UPDATE `ies9021_database`.`career` SET `id_range` = " + newIdRange + " WHERE `id_career` = " + idcarrera;
                        DDBBConnection.SendQuery(updateQuery);
                        JOptionPane.showMessageDialog(this, "Range modificado con éxito", "Operación exitosa", JOptionPane.OK_OPTION);
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(this, "El ID de carrera debe ser un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
    }//GEN-LAST:event_modificarButtonActionPerformed
        }
    }

    private void buscarPorIdButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarPorIdButtonActionPerformed
        // TODO add your handling code here:
        try {
            String jTextFieldId = jTextFieldIdCarrera.getText();

            if (jTextFieldId.matches("\\d{1,3}")) {
                int id = Integer.parseInt(jTextFieldId);

                // Realizar la consulta para obtener la carrera y el rango con JOIN
                String selectQuery = "career.id_career, career.name, range.name";
                String fromTables = "career INNER JOIN `range` ON career.id_range = `range`.id_range";
                String condition = "id_career = " + id;

                List<String[]> result = JsonDataFetcher.selectQuery(selectQuery, fromTables, condition);

                DefaultTableModel modelo = new DefaultTableModel();
                modelo.addColumn("ID");
                modelo.addColumn("Nombre Carrera");
                modelo.addColumn("RANGE");

                if (result != null && !result.isEmpty()) {
                    String[] data = result.get(0);
                    modelo.addRow(new Object[]{data[0], data[1], data[2]});
                } else {
                    JOptionPane.showMessageDialog(this, "No se encontró una carrera con el ID proporcionado.", "Carrera no encontrada", JOptionPane.INFORMATION_MESSAGE);
                }

                JTable1.setModel(modelo);
            } else {
                JOptionPane.showMessageDialog(this, "El ID de carrera debe ser un número entero de hasta 3 dígitos.", "Error", JOptionPane.ERROR_MESSAGE);
            initComponents();
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El ID de carrera debe ser un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_buscarPorIdButtonActionPerformed

    private void jTextFieldIdCarreraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldIdCarreraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldIdCarreraActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        String nombreNuevoRango = JOptionPane.showInputDialog(this, "Ingrese el nombre del nuevo rango:", "Crear Nuevo Rango", JOptionPane.PLAIN_MESSAGE);

        if (nombreNuevoRango == null || nombreNuevoRango.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El nombre del rango es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            String insertQuery = "INSERT INTO `ies9021_database`.`range` (name) VALUES ('" + nombreNuevoRango + "')";
            DDBBConnection.SendQuery(insertQuery);
            cargarRangos();
            JOptionPane.showMessageDialog(this, "Nuevo rango creado con éxito.", "Operación exitosa", JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException e) {
            e.printStackTrace();
            // Maneja cualquier excepción que pueda ocurrir durante la consulta.
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        // Obtén el rango seleccionado del JComboBox2
        String rangoAEliminar = (String) jComboBox2.getSelectedItem();
        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Estás seguro? Se eliminara el rango de la carrera seleccionada.", "Confirmar Acción", JOptionPane.YES_NO_OPTION);
        if (confirmacion == JOptionPane.YES_OPTION) {
            try {
                // Construye la consulta SQL para eliminar el rango
                String deleteQuery = "DELETE FROM `ies9021_database`.`range` WHERE name = '" + rangoAEliminar + "'";
                DDBBConnection.SendQuery(deleteQuery);
                JOptionPane.showMessageDialog(this, "Rango eliminado con éxito", "Atención", JOptionPane.INFORMATION_MESSAGE);
                // Vuelve a cargar los rangos en el JComboBox2
                cargarRangos();
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error al eliminar el rango.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
         String selectedRange = (String) jComboBox2.getSelectedItem();
        // Verifica si se seleccionó un rango
        if (selectedRange != null && !selectedRange.isEmpty()) {
            try {
                // Construye la consulta SQL para obtener las carreras del rango seleccionado
                String selectQuery = "SELECT career.id_career, career.name AS nombre_carrera, `range`.name " +
                        "FROM `ies9021_database`.career " +
                        "INNER JOIN `ies9021_database`.`range` ON career.id_range = `range`.id_range " +
                        "WHERE `range`.name = '" + selectedRange + "'";

                // Realiza la consulta en la base de datos
                List<String[]> queryResult = JsonDataFetcher.selectQuery("career.id_career, career.name, range.name",
                        "career INNER JOIN `range` ON career.id_range = `range`.id_range", "`range`.name = '" + selectedRange + "'");
                // Verifica si se obtuvieron resultados
                if (queryResult != null) {
                    System.out.println("Consulta exitosa");

                    // Crea y configura el modelo de la tabla
                    DefaultTableModel modelo = new DefaultTableModel();
                    modelo.addColumn("ID");
                    modelo.addColumn("Nombre Carrera");
                    modelo.addColumn("Range");

                    // Configura la tabla con el nuevo modelo
                    JTable1.setModel(modelo);

                    // Agrega las filas a la tabla con los resultados de la consulta
                    for (String[] strings : queryResult) {
                        modelo.addRow(strings);
                        System.out.println(Arrays.toString(strings));  // Muestra los datos de la consulta
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Error al recuperar datos.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error al filtrar las carreras por rango.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, selecciona un rango.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
      
    }//GEN-LAST:event_jButton4ActionPerformed
    
    private void cargarRangos() {
        try {
            String selectQuery = "SELECT name FROM `ies9021_database`.`range`";
            List<String[]> result = JsonDataFetcher.selectQuery("name", "`ies9021_database`.`range`", null);

            if (result != null) {
                jComboBox2.removeAllItems(); // Limpia los elementos anteriores del JComboBox

                for (String[] row : result) {
                    String nombreRango = row[0];
                    jComboBox2.addItem(nombreRango);
                }
            } else {
                // Maneja el caso en que la consulta no devuelva resultados
                System.out.println("No se encontraron resultados en la consulta.");
            }
        } catch (Exception e) {
            // Maneja cualquier excepción que pueda ocurrir durante la consulta.
            JOptionPane.showMessageDialog(this, "Error al cargar los rangos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

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
            java.util.logging.Logger.getLogger(Range.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Range.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Range.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Range.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Range().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable JTable1;
    private javax.swing.JButton buscarPorIdButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextFieldIdCarrera;
    private javax.swing.JButton modificarButton;
    // End of variables declaration//GEN-END:variables
}