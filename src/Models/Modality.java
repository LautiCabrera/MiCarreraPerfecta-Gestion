package Models;

import Utils.DDBBConnection;
import Utils.JsonDataFetcher;
import Utils.ResultSetIES9021;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Modality {

    private int id_modality;
    private String modality;
    private int virtual;
    private int id_user_create;
    private int id_user_update;
    private Date fcreate;
    private Date fupdate;

    public Modality() {
        // Constructor sin argumentos
    }

    public Modality(int id_modality, String modality, int virtual, int id_user_create, int id_user_update, Date fcreate, Date fupdate) {
        this.id_modality = id_modality;
        this.modality = modality;
        this.virtual = virtual;
        this.id_user_create = id_user_create;
        this.id_user_update = id_user_update;
        this.fcreate = fcreate;
        this.fupdate = fupdate;
    }

    public int getId_modality() {
        return id_modality;
    }

    public void setId_modality(int id_modality) {
        this.id_modality = id_modality;
    }

    public String getModality() {
        return modality;
    }

    public void setModality(String modality) {
        this.modality = modality;
    }

    public int getVirtual() {
        return virtual;
    }

    public void setVirtual(int virtual) {
        this.virtual = virtual;
    }

    public int getId_user_create() {
        return id_user_create;
    }

    public void setId_user_create(int id_user_create) {
        this.id_user_create = id_user_create;
    }

    public int getId_user_update() {
        return id_user_update;
    }

    public void setId_user_update(int id_user_update) {
        this.id_user_update = id_user_update;
    }

    public Date getfcreate() {
        return fcreate;
    }

    public void setfcreate(Date fcreate) {
        this.fcreate = fcreate;
    }

    public Date getfupdate() {
        return fupdate;
    }

    public void setfupdate(Date fupdate) {
        this.fupdate = fupdate;
    }

    JsonDataFetcher dataFetcher = new JsonDataFetcher();

    //+++++++++++++++Metodos para el funcionamiento de la interfas principal+++++++++++++++
    //METODO PARA LA CARGA DE DATOS
    public void loadTableData(JTable table, JFrame window) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        model.setColumnIdentifiers(new String[]{"id_modality", "modality", "virtual", "id_user_create", "id_user_update", "fcreate", "fupdate"});
        String tableName = "modality"; // Nombre de la tabla
        String whereClause = null;
        Class<Modality> returnType = Modality.class; // Clase que se utiliza para mapear los resultados
        ResultSetIES9021<Modality> result = dataFetcher.fetchTableData(tableName, whereClause, returnType); // Llama al método para obtener los datos

        if (result.getState()) {
            //carga los nuevos datos 
            List<Modality> modalityList = result.getDatos();
            //Recorre la lista de objetos de modality y los agrega a la tabla 
            for (Modality modalityTable : modalityList) {
                Object[] rowData = {modalityTable.getId_modality(), modalityTable.getModality(), modalityTable.getVirtual(), modalityTable.getId_user_create(), modalityTable.getId_user_update(), modalityTable.getfcreate(), modalityTable.getfupdate()};
                model.addRow(rowData);
            }
        } else {
            //Si no se puede muestra el mensaje de error 
            String clarification = result.getClarification();
            JOptionPane.showMessageDialog(window, "Error al cargar los datos: " + clarification, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    //FIN DEL METODO

    //METODO PARA LA BUSQUEDA 
    public void searchModality(String searchText, JTable table, JFrame window) {
        // Utiliza JsonDataFetcher para buscar campus por nombre
        String whereClause = "name LIKE '%" + searchText + "%'";
        Class<Modality> returnType = Modality.class;
        ResultSetIES9021<Modality> result = dataFetcher.fetchTableData("modality", whereClause, returnType);

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        model.setColumnIdentifiers(new String[]{"id_modality", "modality", "virtual", "id_user_create", "id_user_update", "fcreate", "fupdate"});

        if (result.getState()) {
            // Carga los nuevos datos
            List<Modality> modalityList = result.getDatos();
            // Recorre la lista de objetos de campus y los agrega a la tabla
            for (Modality modalitySearch : modalityList) {
                Object[] rowData = {modalitySearch.getId_modality(), modalitySearch.getModality(), modalitySearch.getVirtual(), modalitySearch.getId_user_create(), modalitySearch.getId_user_update(), modalitySearch.getfcreate(), modalitySearch.getfupdate()};
                model.addRow(rowData);
            }
            if (modalityList.isEmpty()) {
                JOptionPane.showMessageDialog(window, "No se encontraron resultados para la búsqueda: " + searchText,
                        "Búsqueda sin resultados", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            String clarification = result.getClarification();
            JOptionPane.showMessageDialog(window, "Error al cargar los datos: " + clarification, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    //FIN DEL METODO
    
    //METODOS PARA EL BOTON ELIMINAR 
    public void deleteModality(JTable table, JFrame window) {
        // Obtén el ID de la fila seleccionada
        int selectedRow = table.getSelectedRow();
        String selectedID = table.getValueAt(selectedRow, 0).toString();
        // Verifica si el ID está siendo usado en otras tablas
        boolean idUsed = modalityUsed(selectedID);

        if (idUsed) {
            // El ID está asociado a otras tablas, muestra un mensaje de error
            JOptionPane.showMessageDialog(window, "No se puede eliminar este campus porque el ID está asociado a otras tablas.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            int confirmInitial = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que deseas eliminar este campus?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
            if (confirmInitial == JOptionPane.YES_OPTION) {
                String randomText = generateRandomText();
                String userInput = JOptionPane.showInputDialog(this, "Para confirmar, ingrese el siguiente texto:\n" + randomText);
                if (userInput != null && userInput.equals(randomText)) {
                    // Si el ID no está asociado a otras tablas, procede con la eliminación
                    String query = "DELETE FROM campus WHERE id_campus = " + selectedID;
                    try {
                        ResultSetIES9021 queryResult = DDBBConnection.SendQuery(query);

                        if (queryResult.getState()) {
                            DefaultTableModel model = (DefaultTableModel) table.getModel();
                            model.removeRow(selectedRow);
                            JOptionPane.showMessageDialog(null, "Registro eliminado con éxito.");
                        } else {
                            JOptionPane.showMessageDialog(null, "No se pudo eliminar el registro: " + queryResult.getClarification());
                        }
                    } catch (Exception error) {
                        JOptionPane.showMessageDialog(window, "Error inesperado al eliminar la fila: " + error.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else if (userInput != null && !userInput.equals(randomText)) {
                    // El usuario no ingresó correctamente el texto, muestra un mensaje de error
                    JOptionPane.showMessageDialog(window, "Texto incorrecto. La fila no se ha borrado.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(window, "Ejecución terminada.", "Terminado", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(window, "Ejecución terminada.", "Terminado", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    // Metodo para verificar si una fila puede ser eliminada o no 
    public boolean modalityUsed(String idModality) {
        int id = Integer.parseInt(idModality);
        List<String> tablesToCheck = Arrays.asList("career");
        String idFieldName = "id_modality";

        for (String tableName : tablesToCheck) {
            // Construye la consulta SQL para buscar el ID en la tabla actual
            String query = "SELECT COUNT(*) FROM " + tableName + " WHERE " + idFieldName + " = " + id;

            // Ejecuta la consulta utilizando ResultSetIES9021
            ResultSetIES9021 result = DDBBConnection.SendQuery(query);

            if (result.getState()) {
                List<Integer> counts = result.getDatos();
                if (!counts.isEmpty()) {
                    int count = counts.get(0); // Resultado del recuento de filas
                    if (count > 0) {
                        // Si count es mayor que 0, significa que el ID existe en esta tabla
                        return true;
                    }
                }
            } else {
                String clarification = result.getClarification();
                System.out.println("Error al ejecutar la consulta en " + tableName + ": " + clarification);
            }
        }

        // Si llega hasta aquí, significa que el ID no se encontró en ninguna de las tablas
        return false;
    }
    //Metodo para generar un texto aleatorio 
    private String generateRandomText() {
        int length = 6; // Longitud del texto aleatorio
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder randomText = new StringBuilder();
            for (int i = 0; i < length; i++) {
                int index = (int) (Math.random() * characters.length());
                randomText.append(characters.charAt(index));
            } 
        return randomText.toString();
    }
    
    //++++++++++Metodos para el funcionamiento de la interfas de añadir++++++++++
    //METODO PARA EL BOTON GUARDAR 
    public void saveModalityBTN(String modality, String idUserCreate, JFrame window) {
        // Obtener la fecha actual
        java.util.Date currentDate = new java.util.Date();
        // Convertir la fecha actual a un formato de fecha adecuado
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String fCreateAdd = dateFormat.format(currentDate);
        //Parametros para pasar la query
        int virtualModality = 0;
        String idCreate = idUserCreate;
        String idUpdate = idCreate;
        String fUpdateAdd = fCreateAdd;
        // Crear la consulta SQL para la inserción en la tabla "campus"
        String query = "INSERT INTO modality (modality, virtual, id_user_create, id_user_update, fcreate, fupdate) " + "VALUES ('" + modality + "', '" + virtualModality + "', " + idCreate + ", " + idUpdate + ", '" + fCreateAdd + "', '" + fUpdateAdd + "')";
        //Ejecuto la consulta 
        ResultSetIES9021 result = DDBBConnection.SendQuery(query);
        // Verificar el estado del resultado
        if (result.getState()) {
            JOptionPane.showMessageDialog(window, "Campus creado con éxito.", "Actualización Exitosa", JOptionPane.INFORMATION_MESSAGE);
            window.dispose();
        } else {
            JOptionPane.showMessageDialog(window, "No se pudo crear el campus.", "Error de Creación", JOptionPane.ERROR_MESSAGE);
        }
    }

    //+++++++++++++++Metodos para el funcionamiento de la interfas de editar+++++++++++++++
    //METODO PARA EL BOTON GUARDAR 
    public void updateModality(String id_modality, String modality, String idUserUpdate, JFrame window) {
        // Obtener la fecha actual
        java.util.Date currentDate = new java.util.Date();
        // Convertir la fecha actual a un formato de fecha adecuado
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String f_update = dateFormat.format(currentDate);

        // Crear la consulta SQL para la actualización en la tabla "campus"
        String updateQuery = "UPDATE modality SET  modality = '" + modality + "', id_user_update = '" + idUserUpdate
                + "', fupdate = " + f_update + "' WHERE id_modality = " + id_modality + ";";

        // Ejecutar la consulta utilizando el método SendQuery
        ResultSetIES9021 result = DDBBConnection.SendQuery(updateQuery);

        // Verificar el estado del resultado
        if (result.getState()) {
            JOptionPane.showMessageDialog(window, "Los datos del campus se actualizaron con éxito.", "Actualización Exitosa", JOptionPane.INFORMATION_MESSAGE);
            window.dispose();
        } else {
            JOptionPane.showMessageDialog(window, "No se pudo actualizar el campus.", "Error de Actualización", JOptionPane.ERROR_MESSAGE);
        }
    }
}
