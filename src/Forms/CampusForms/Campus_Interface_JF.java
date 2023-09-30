package Forms.CampusForms;

import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import Models.Campus;
import Utils.DDBBConnection;
import Utils.JsonDataFetcher;
import Utils.ResultSetIES9021;
import java.util.List;
import javax.swing.event.ListSelectionEvent;

public class Campus_Interface_JF extends javax.swing.JFrame {

    JsonDataFetcher dataFetcher = new JsonDataFetcher();
    private int currentPage = 1; // Página actual, comienza en 1
    private final int pageSize = 10; // Tamaño de la página
    private List<Campus> campusList;

    public Campus_Interface_JF() {
        initComponents();
        ConfigurationStart();
        setLocationRelativeTo(null);
    }

    private void ConfigurationStart() {
        loadTableData();
        currentPage = 1;
        configSelectionListener();
        ResetButtons();
    }

    private void loadTableData() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        model.setColumnIdentifiers(new String[]{"id_campus", "id_university", "name", "location", "latitude", "longitude", "main", "www", "email", "id_user_create", "id_user_update", "f_create", "f_update"});
        String tableName = "campus"; // Nombre de la tabla
        Class<Campus> returnType = Campus.class; // Clase que se utiliza para mapear los resultados

        // Calcula el límite inferior y superior de la página actual
        int lowerLimit = (currentPage - 1) * pageSize + 1;
        int upperLimit = currentPage * pageSize;

        // Construye una consulta SQL con la cláusula WHERE que filtra por rango
        String whereClause = "id_campus BETWEEN " + lowerLimit + " AND " + upperLimit;

        ResultSetIES9021<Campus> result = dataFetcher.fetchTableData(tableName, whereClause, returnType);

        if (result.getState()) {
            // Carga los nuevos datos
            campusList = result.getDatos();
            // Recorre la lista de objetos de campus y los agrega a la tabla
            for (Campus campus : campusList) {
                Object[] rowData = {campus.getId_campus(), campus.getId_university(), campus.getName(), campus.getLocation(), campus.getLatitude(), campus.getLongitude(), campus.getMain(), campus.getWww(), campus.getEmail(), campus.getId_user_create(), campus.getId_user_update(), campus.getFcreate(), campus.getFupdate()};
                model.addRow(rowData);
            }
        } else {
            // Si no se puede muestra el mensaje de error
            String clarification = result.getClarification();
            JOptionPane.showMessageDialog(this, "Error al cargar los datos: " + clarification, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void configSelectionListener() {
        jTable1.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
            // Verifica si hay una fila seleccionada
            if (!e.getValueIsAdjusting() && jTable1.getSelectedRow() != -1) {
                // Hay una fila seleccionada, habilita los botones "Modificar" y "Borrar"
                BTNModify.setEnabled(true);
                BTNDelete.setEnabled(true);
            }
        });
    }

    private void ResetButtons() {
        BTNAdd.setText("Añadir");
        BTNAdd.setEnabled(true);
        btnPreviousPage.setEnabled(true);
        btnNextPage.setEnabled(true);
        BTNModify.setText("Modificar");
        BTNModify.setEnabled(false);
        BTNDelete.setText("Borrar");
        BTNDelete.setEnabled(false);
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
        btnNextPage = new javax.swing.JButton();
        btnPreviousPage = new javax.swing.JButton();

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

        btnNextPage.setText("Siguiente");
        btnNextPage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextPageActionPerformed(evt);
            }
        });

        btnPreviousPage.setText("Anterior");
        btnPreviousPage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreviousPageActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 955, Short.MAX_VALUE)
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
                                .addComponent(BTNDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnPreviousPage)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNextPage)))
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnNextPage)
                    .addComponent(btnPreviousPage))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BTNRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNRefreshActionPerformed
        jTable1.clearSelection();
        ConfigurationStart();
    }//GEN-LAST:event_BTNRefreshActionPerformed
    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        
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
                String idToDelete = jTable1.getValueAt(selectedRow, 0).toString(); 
                String deleteQuery = "DELETE FROM campus WHERE id_campus = " + idToDelete;

                // Ejecuta la eliminación en la base de datos y obtiene el resultado
                ResultSetIES9021 queryResult = DDBBConnection.SendQuery(deleteQuery);

                // Verifica el resultado de la eliminación
                if (queryResult.getState()) {
                    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                    model.removeRow(selectedRow); // Elimina la fila de la tabla si la eliminación en la BD fue exitosa
                    JOptionPane.showMessageDialog(null, "Registro eliminado con éxito.");
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo eliminar el registro: " + queryResult.getClarification());
                }
            }
        }
        ResetButtons();
        ConfigurationStart();
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
            String idCampus = jTable1.getValueAt(selectedRow, 0).toString();
            String idUniversity = jTable1.getValueAt(selectedRow, 1).toString();
            String name = jTable1.getValueAt(selectedRow, 2).toString();
            String location = jTable1.getValueAt(selectedRow, 3).toString();
            String latitude = jTable1.getValueAt(selectedRow, 4).toString();
            String longitude = jTable1.getValueAt(selectedRow, 5).toString();
            String main = jTable1.getValueAt(selectedRow, 6).toString();
            String www = jTable1.getValueAt(selectedRow, 7).toString();
            String email = jTable1.getValueAt(selectedRow, 8).toString();
            String id_user_create = jTable1.getValueAt(selectedRow, 9).toString();

            // Crea una instancia de CampusUpdateForm y pasa los datos
            CampusUpdateForm updateForm = new CampusUpdateForm();
            updateForm.setParent(this);
            updateForm.setCampusData(idCampus, idUniversity, name, location, latitude, longitude, main, www, email, id_user_create); 
            updateForm.setVisible(true);
        }
    }//GEN-LAST:event_BTNModifyActionPerformed

    private void BTNAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNAddActionPerformed
        CampusAddForm addForm = new CampusAddForm();
        addForm.setParent(this);
        addForm.setVisible(true);
    }//GEN-LAST:event_BTNAddActionPerformed
    
    private void BTNSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNSearchActionPerformed
        btnPreviousPage.setEnabled(false);
        btnNextPage.setEnabled(false);
        String searchText = TXTSearch.getText();
    
        // Utiliza JsonDataFetcher para buscar campus por nombre
        String whereClause = "name LIKE '%" + searchText + "%'";
        Class<Campus> returnType = Campus.class;
        ResultSetIES9021<Campus> result = dataFetcher.fetchTableData("campus", whereClause, returnType);
        
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        model.setColumnIdentifiers(new String[]{"id_campus", "id_university", "name", "location", "latitude", "longitude", "main", "www", "email", "id_user_create", "id_user_update", "f_create", "f_update"});

        if (result.getState()) {
            //carga los nuevos datos
            List<Campus> campusList = result.getDatos();
            //Recorre la lista de objetos de campus y los agrega a la tabla
            for (Campus campus : campusList) {
                Object[] rowData = {campus.getId_campus(), campus.getId_university(), campus.getName(), campus.getLocation(), campus.getLatitude(), campus.getLongitude(), campus.getMain(), campus.getWww(), campus.getEmail(), campus.getId_user_create(), campus.getId_user_update(), campus.getFcreate(), campus.getFupdate()};
                model.addRow(rowData);
            }
            if (campusList.isEmpty()) {
            // No se encontraron resultados, muestra un mensaje
            JOptionPane.showMessageDialog(this, "No se encontraron resultados para la búsqueda: " + searchText,
                "Búsqueda sin resultados", JOptionPane.INFORMATION_MESSAGE);
        }
        } else {
            // Si no se puede muestra el mensaje de error
            String clarification = result.getClarification();
            JOptionPane.showMessageDialog(this, "Error al cargar los datos: " + clarification, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_BTNSearchActionPerformed

    private void btnPreviousPageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreviousPageActionPerformed
        if (currentPage > 1) {
            currentPage--;
            loadTableData();
        } else {
            JOptionPane.showMessageDialog(this, "No hay más páginas hacia atrás.", "Información", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnPreviousPageActionPerformed

    private void btnNextPageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextPageActionPerformed
        currentPage++;
        loadTableData();
    }//GEN-LAST:event_btnNextPageActionPerformed
    
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
    private javax.swing.JButton btnNextPage;
    private javax.swing.JButton btnPreviousPage;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
