package Forms.Modality;

import Models.Modality;
import Utils.DDBBConnection;
import Utils.JsonDataFetcher;
import Utils.ResultSetIES9021;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;

public class ModalityInterface extends javax.swing.JFrame {

    JsonDataFetcher dataFetcher = new JsonDataFetcher(); // Crea una instancia

    //Metodo para mostrar la tabla modality cuando se inicie la vista
    private void loadTableData() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        model.setColumnIdentifiers(new String[]{"id_modality", "modality", "virtual", "id_user_create", "id_user_update", "fcreate", "fupdate"});
        String tableName = "modality";//Nombre de la tabla
        String whereClause = null;// Clausula where 
        Class<Modality> returnType = Modality.class;  //Clase que se utiliza para mapear los resultados 
        try {
            ResultSetIES9021<Modality> result = dataFetcher.fetchTableData(tableName, whereClause, returnType); // Llama al método para obtener los datos

            if (result.getState()) {
                //carga los nuevos datos 
                List<Modality> modalityList = result.getDatos();
                //Recorre la lista de objetos de modality y los agrega a la tabla 
                for (Modality modality : modalityList) {
                    Object[] rowData = {modality.getId_modality(), modality.getmodality(), modality.getvirtual(), modality.getId_user_create(), modality.getId_user_update(), modality.getfcreate(), modality.getfupdate()};
                    model.addRow(rowData);
                }
            } else {
                //Si no se puede muestra el mensaje de error 
                String clarification = result.getClarification();
                JOptionPane.showMessageDialog(this, "Error al cargar los datos: " + clarification, "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception error) {
            JOptionPane.showMessageDialog(this, "Error  al cargar los datos" + error.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    //Fin del metodo

    //Metodo para activar el boton de Modificar y borrar 
    private void configSelectionListener() {
        jTable1.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
            try {
                // Verifica si hay una fila seleccionada
                if (!e.getValueIsAdjusting() && jTable1.getSelectedRow() != -1) {
                    // Hay una fila seleccionada, habilita los botones "Modificar" y "Borrar"
                    BTNModify.setEnabled(true);
                    BTNDelete.setEnabled(true);

                    //Obtengo los datos de la fila seleccionada 
                    int selectedRowIndex = jTable1.getSelectedRow();
                    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                    selectedRowData = new Object[model.getColumnCount()];
                    for (int i = 0; i < model.getColumnCount(); i++) {
                        selectedRowData[i] = model.getValueAt(selectedRowIndex, i);
                    }
                }
            } catch (Exception error) {
                JOptionPane.showMessageDialog(this, "Error al seleccionar la fila" + error.getMessage() + "Error" + JOptionPane.ERROR_MESSAGE);
            }
        });
    }
    //Fin del metodo 

    //Instancia para almacenar los datos de una fila
    private Object[] selectedRowData;
    //Fin instancia

    // Método para generar un texto aleatorio
    private String generateRandomText() {
        int length = 6; // Longitud del texto aleatorio
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder randomText = new StringBuilder();
        try {
            for (int i = 0; i < length; i++) {
                int index = (int) (Math.random() * characters.length());
                randomText.append(characters.charAt(index));
            }
        } catch (Exception error) {
            JOptionPane.showMessageDialog(this, "Error al generar texto aleatorio" + error.getMessage() + JOptionPane.ERROR_MESSAGE);
        }
        return randomText.toString();
    }
    //Fin del metodo 

    //Metodo para daber si el id se esta usando en otra tabla 
    private boolean idUsed(int selectedID) {
       // Define una lista de nombres de tablas en las que deseas verificar la existencia del ID
    List<String> tablesToCheck = Arrays.asList("career"); // Reemplaza con los nombres de tus tablas

    // Define el nombre del campo en esas tablas donde deseas buscar el ID
    String idFieldName = "id_modality"; // Reemplaza con el nombre del campo correspondiente

    for (String tableName : tablesToCheck) {
        // Construye la consulta SQL para buscar el ID en la tabla actual
        String query = "SELECT COUNT(*) FROM " + tableName + " WHERE " + idFieldName + " = " + selectedID;

        // Ejecuta la consulta utilizando ResultSetIES9021
        ResultSetIES9021 result = DDBBConnection.SendQuery(query); 

        if (result.getState()) {
            List<Integer> counts = result.getDatos();
            if (!counts.isEmpty()) {
                int count = counts.get(0); // Obtén el resultado del recuento de filas
                if (count > 0) {
                    // Si count es mayor que 0, significa que el ID existe en esta tabla
                    return true;
                }
            }
        } else {
            // Manejo de errores si la consulta no fue exitosa
            String clarification = result.getClarification();
            System.out.println("Error al ejecutar la consulta en " + tableName + ": " + clarification);
            // Puedes elegir cómo manejar este error, como lanzar una excepción
        }
    }

    // Si llegas hasta aquí, significa que el ID no se encontró en ninguna de las tablas
    return false;
    }
    //Fin del metodo 

    //Metodo para eliminar una fila 
    private void deleteSelectedRow() {
        if (selectedRowData != null) {
            // Obtén el ID de la fila seleccionada 
            int selectedID = (int) selectedRowData[0];
            //Verifico si el id esta siendo usado en otra tabla 
            boolean idUsed = idUsed(selectedID);

            if (idUsed == true) {
                // El ID está asociado a otras tablas, muestra un mensaje de error
                JOptionPane.showMessageDialog(this, "No se puede eliminar esta fila porque el ID está asociado a otras tablas.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                // El ID no está asociado a otras tablas, procede con la eliminación
                // Construye y envía la consulta SQL para eliminar la fila
                String query = "DELETE FROM modality WHERE id_modality = " + selectedID;
                try {
                    ResultSetIES9021 result = DDBBConnection.SendQuery(query);

                    if (result.getState()) {
                        // Eliminación exitosa, ahora elimina la fila de la tabla
                        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

                        // Encuentra el índice de la fila seleccionada en el modelo de la tabla
                        for (int i = 0; i < model.getRowCount(); i++) {
                            int rowID = (int) model.getValueAt(i, 0); // 
                            if (rowID == selectedID) {
                                // Elimina la fila de la tabla
                                model.removeRow(i);
                                break;
                            }
                        }
                    } else {
                        // Manejo de excepción en caso de que la eliminación no sea exitosa
                        JOptionPane.showMessageDialog(this, "Error al eliminar la fila: " + result.getClarification(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception error) {
                    // Manejo de excepción en caso de error en la consulta SQL o manipulación de la tabla
                    JOptionPane.showMessageDialog(this, "Error inesperado al eliminar la fila: " + error.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    //Fin del metodo

    public ModalityInterface() {
        initComponents();
        this.setLocationRelativeTo(null);
        loadTableData();
        configSelectionListener();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LABTittle = new javax.swing.JLabel();
        BTNRefresh = new javax.swing.JButton();
        BTNDelete = new javax.swing.JButton();
        BTNModify = new javax.swing.JButton();
        BTNAdd = new javax.swing.JButton();
        BTNSearch = new javax.swing.JButton();
        TXTSearch = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Mi Carrera Perfecta");

        LABTittle.setText("Modality");

        BTNRefresh.setText("Refrescar");
        BTNRefresh.setMaximumSize(new java.awt.Dimension(82, 24));
        BTNRefresh.setPreferredSize(new java.awt.Dimension(82, 24));
        BTNRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNRefreshActionPerformed(evt);
            }
        });

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

        TXTSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXTSearchActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.setShowVerticalLines(false);
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BTNRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addComponent(LABTittle)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 366, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(TXTSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(BTNSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BTNRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNRefreshActionPerformed
        loadTableData();
    }//GEN-LAST:event_BTNRefreshActionPerformed

    private void BTNDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNDeleteActionPerformed
        if (selectedRowData != null) {
            int confirmInitial = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea borrar la fila seleccionada?", "Confirmar Borrado", JOptionPane.YES_NO_OPTION);
            if (confirmInitial == JOptionPane.YES_OPTION) {
                // El usuario ha confirmado la eliminación, genera un texto aleatorio
                String randomText = generateRandomText();
                String userInput = JOptionPane.showInputDialog(this, "Para confirmar, ingrese el siguiente texto:\n" + randomText);

                // Verifica si el usuario ingresó el texto aleatorio correctamente
                if (userInput != null && userInput.equals(randomText)) {
                    // El usuario ingresó correctamente el texto, procede a borrar la fila
                    deleteSelectedRow();
                    JOptionPane.showMessageDialog(this, "Fila borrada con exito.", "Exito", JOptionPane.INFORMATION_MESSAGE);
                } else if (userInput != null && !userInput.equals(randomText)) {
                    // El usuario no ingresó correctamente el texto, muestra un mensaje de error
                    JOptionPane.showMessageDialog(this, "Texto incorrecto. La fila no se ha borrado.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Ejecución terminada.", "Terminado", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } else {
            // Mostrar un mensaje de error si no se ha seleccionado ninguna fila
            JOptionPane.showMessageDialog(this, "Seleccione una fila para borrar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_BTNDeleteActionPerformed

    private void BTNModifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNModifyActionPerformed
        if (selectedRowData != null) {
            ModalityUpdateInterface updateInterface = new ModalityUpdateInterface(); //Instancia de la vista de modificar
            updateInterface.setModalityData(selectedRowData);//Envio los datos de la fila seleccionada a la vista Update 
            updateInterface.setVisible(true); //Cuando se aprete el boton de modificar se abre la vista ModalityUpdateInterface
        } else {
            // Mostrar un mensaje de error si no se ha seleccionado ninguna fila
            JOptionPane.showMessageDialog(this, "Seleccione una fila para modificar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_BTNModifyActionPerformed

    private void BTNAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNAddActionPerformed
        ModalityAddInterface addInterface = new ModalityAddInterface();
        addInterface.setVisible(true);
    }//GEN-LAST:event_BTNAddActionPerformed

    private void TXTSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXTSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXTSearchActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
public void run() {
                new ModalityInterface().setVisible(true);
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
