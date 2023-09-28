package Forms.CampusForms;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import Utils.DDBBConnection;
import java.sql.PreparedStatement;
import Forms.CampusForms.CampusAddForm;
import Forms.CampusForms.CampusUpdateForm;
import java.sql.Connection;

public class Campus_Interface_JF extends javax.swing.JFrame {

    String[] Columns;
    DefaultTableModel Tabla;
    ResultSet RS;
    DDBBConnection con = new DDBBConnection();
    int SelectedRow = -1;

    public Campus_Interface_JF() {
        initComponents();
        ConfigurationStart();
        setLocationRelativeTo(null);
    }

    private void ConfigurationStart() {
        PintarTablaColumns();
        jTable1.setSelectionMode(0);
        // Consulta SQL para obtener todos los datos
        RS = con.SendAndRecibe("SELECT * FROM campus");
        PintarTablaRows(RS);
        con.Disconect();
}

    private void ResetButtons() {
        BTNAdd.setText("Añadir");
        BTNAdd.setEnabled(true);
        BTNModify.setText("Modificar");
        BTNModify.setEnabled(false);
        BTNDelete.setText("Borrar");
        BTNDelete.setEnabled(false);
        SelectedRow = -1;
    }

    private void PintarTablaColumns() {
        Tabla = (DefaultTableModel) jTable1.getModel();
        Tabla.setColumnCount(Columns.length);
        int count = 0;
        for (String CN : Columns) {
            JTableHeader tableHeader = jTable1.getTableHeader();
            TableColumnModel tableColumnModel = tableHeader.getColumnModel();
            TableColumn tableColumn = tableColumnModel.getColumn(count);
            tableColumn.setHeaderValue(CN);
            tableHeader.repaint();
            count++;
        }
    }

    private void PintarTablaRows(ResultSet RSult) {
        try {
            Tabla.setRowCount(0);
            Object[] O = new Object[Columns.length];
            if (RSult != null) {
                while (RSult.next()) {
                    for (int i = 0; i < Columns.length; i++) {
                        O[i] = RSult.getObject(Columns[i]);
                    }
                    Tabla.addRow(O);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LABTittle = new javax.swing.JLabel();
        BTNRefresh = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        BTNDelete = new javax.swing.JButton();
        BTNModify = new javax.swing.JButton();
        BTNAdd = new javax.swing.JButton();
        BTNSearch = new javax.swing.JButton();
        TXTSearch = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        LABTittle.setText("Tabla Campus");

        BTNRefresh.setText("Refrescar");
        BTNRefresh.setMaximumSize(new java.awt.Dimension(82, 24));
        BTNRefresh.setPreferredSize(new java.awt.Dimension(82, 24));
        BTNRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNRefreshActionPerformed(evt);
            }
        });

        jTable1 = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.setFocusable(false);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        BTNDelete.setText("Borrar");
        BTNDelete.setEnabled(false);
        BTNDelete.setMaximumSize(new java.awt.Dimension(82, 24));
        BTNDelete.setPreferredSize(new java.awt.Dimension(82, 24));
        BTNDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNDeleteActionPerformed(evt);
            }
        });

        BTNModify.setText("Modificar");
        BTNModify.setEnabled(false);
        BTNModify.setMaximumSize(new java.awt.Dimension(82, 24));
        BTNModify.setPreferredSize(new java.awt.Dimension(82, 24));
        BTNModify.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNModifyActionPerformed(evt);
            }
        });

        BTNAdd.setText("Añadir");
        BTNAdd.setMaximumSize(new java.awt.Dimension(82, 24));
        BTNAdd.setPreferredSize(new java.awt.Dimension(82, 24));
        BTNAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNAddActionPerformed(evt);
            }
        });

        BTNSearch.setText("Buscar");
        BTNSearch.setMaximumSize(new java.awt.Dimension(82, 24));
        BTNSearch.setPreferredSize(new java.awt.Dimension(82, 24));
        BTNSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNSearchActionPerformed(evt);
            }
        });

        TXTSearch.setText("Buscar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 636, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(BTNRefresh, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
                            .addComponent(LABTittle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(TXTSearch)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(BTNSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(BTNAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(BTNModify, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(BTNDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BTNSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TXTSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LABTittle))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BTNRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BTNDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BTNModify, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BTNAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BTNRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNRefreshActionPerformed
        jTable1.clearSelection();
        ConfigurationStart();
    }//GEN-LAST:event_BTNRefreshActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        if (jTable1.getSelectedRow() != SelectedRow) {
            ResetButtons();
            BTNModify.setEnabled(true);
            BTNDelete.setEnabled(true);
            SelectedRow = jTable1.getSelectedRow();
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void BTNDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNDeleteActionPerformed
        if (BTNDelete.getText().equals("Borrar")) {
            int selectedRow = jTable1.getSelectedRow();
            if (selectedRow >= 0) {
                int response = JOptionPane.showConfirmDialog(null,
                    "¿Estás seguro de que deseas eliminar esta fila?",
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION);
                if (response == JOptionPane.YES_OPTION) {
                    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                    String idToDelete = jTable1.getValueAt(selectedRow, 0).toString(); // Supongo que el ID está en la primera columna
                    // Realiza aquí la eliminación en la base de datos
                    String query = "DELETE FROM campus WHERE id_campus = ?";
                    try (Connection connection = con.Conectar();
                         PreparedStatement statement = connection.prepareStatement(query)) {
                        statement.setString(1, idToDelete);
                        int rowsAffected = statement.executeUpdate();
                        if (rowsAffected > 0) {
                            model.removeRow(selectedRow); // Elimina la fila de la tabla si la eliminación en la BD fue exitosa
                            JOptionPane.showMessageDialog(null, "Registro eliminado con éxito.");
                        } else {
                            JOptionPane.showMessageDialog(null, "No se pudo eliminar el registro.");
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Error al eliminar el registro: " + ex.getMessage());
                    } finally {
                        con.Disconect();
                    }
                }
            }
            ResetButtons();
        } else {
            jTable1.clearSelection();
            ResetButtons();
        }
    }//GEN-LAST:event_BTNDeleteActionPerformed

    private void BTNModifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNModifyActionPerformed
        // Verifica si se ha seleccionado una fila
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow >= 0) {
            // Obtiene los datos de la fila seleccionada
            String campusID = jTable1.getValueAt(selectedRow, 0).toString();
            String idUniversity = jTable1.getValueAt(selectedRow, 1).toString();
            String name = jTable1.getValueAt(selectedRow, 2).toString();
            String location = jTable1.getValueAt(selectedRow, 3).toString();
            String latitude = jTable1.getValueAt(selectedRow, 4).toString();
            String longitude = jTable1.getValueAt(selectedRow, 5).toString();
            String main = jTable1.getValueAt(selectedRow, 6).toString();
            String www = jTable1.getValueAt(selectedRow, 7).toString();
            String email = jTable1.getValueAt(selectedRow, 8).toString();
            String idUserUpdate = jTable1.getValueAt(selectedRow, 10).toString();
            String fUpdate = jTable1.getValueAt(selectedRow, 12).toString();

            // Crea una instancia de CampusUpdateForm y pasa los datos
            CampusUpdateForm updateForm = new CampusUpdateForm();
            updateForm.setParent(this);
            updateForm.setCampusData(campusID, idUniversity, name, location, latitude, longitude, main, www, email, idUserUpdate, fUpdate); 
            updateForm.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione una fila para modificar.");
        }
    }//GEN-LAST:event_BTNModifyActionPerformed

    private void BTNAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNAddActionPerformed
        CampusAddForm addForm = new CampusAddForm();
        addForm.setParent(this);
        addForm.setVisible(true);
    }//GEN-LAST:event_BTNAddActionPerformed
    
    private void BTNSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNSearchActionPerformed
        String searchText = TXTSearch.getText();
        String query = "SELECT * FROM campus WHERE name LIKE '%" + searchText + "%'";
        ResultSet resultSet = con.SendAndRecibe(query);

        // Verifica si el conjunto de resultados está vacío
        try {
            if (!resultSet.isBeforeFirst()) {
                // No se encontraron resultados, muestra un mensaje
                JOptionPane.showMessageDialog(this, "No se encontraron resultados para la búsqueda: " + searchText,
                    "Búsqueda sin resultados", JOptionPane.INFORMATION_MESSAGE);
            } else {
                // Se encontraron resultados, muestra los resultados en la tabla
                PintarTablaRows(resultSet);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            con.Disconect();
        }
    }//GEN-LAST:event_BTNSearchActionPerformed
    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Campus_Interface_JF().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTNAdd;
    private javax.swing.JButton BTNDelete;
    private javax.swing.JButton BTNModify;
    private javax.swing.JButton BTNRefresh;
    private javax.swing.JButton BTNSearch;
    private javax.swing.JLabel LABTittle;
    private javax.swing.JTextField TXTSearch;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
