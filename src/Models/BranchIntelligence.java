package Models;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonProperty;
import Utils.DDBBConnection;
import Utils.JsonDataFetcher;
import Utils.ResultSetIES9021;
import Models.Users;
import java.lang.reflect.Field;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Enzo Rico and Marisa Sarmiento
 */
public class BranchIntelligence {
    
    //Declaro los atributos de la clase
    @JsonProperty("id_branch_intelligence")
    private int id_branch_intelligence;
    @JsonProperty("id_branch")
    private int id_branch;
    @JsonProperty("id_intelligence")
    private int id_intelligence;
    @JsonProperty("priority")
    private int priority;
    @JsonProperty("id_user_create")
    private int id_user_create;
    @JsonProperty("id_user_update")
    private int id_user_update;
    @JsonProperty("fcreate")
    private Date fcreate;
    @JsonProperty("fupdate")
    private Date fupdate;

    //Declaro unas variables que seran utiles para la conexión con la BBDD
    JsonDataFetcher dataFetcher = new JsonDataFetcher();
    private List<BranchIntelligence> relationsList;
    private int selectedUserId;

    //Constructor vacio de la clase
    public BranchIntelligence(){
        
    }
    
    //Constructor de la clase
    public BranchIntelligence(int id_branch_intelligence, int id_branch, int id_intelligence, int priority, int id_user_create, int id_user_update, Date f_create, Date f_update, List<BranchIntelligence> relationsList, int selectedUserId) {
        this.id_branch_intelligence = id_branch_intelligence;
        this.id_branch = id_branch;
        this.id_intelligence = id_intelligence;
        this.priority = priority;
        this.id_user_create = id_user_create;
        this.id_user_update = id_user_update;
        this.fcreate = f_create;
        this.fupdate = f_update;
        this.relationsList = relationsList;
        this.selectedUserId = selectedUserId;
    }

    //Getters
    public int getId_branch_intelligence() {
        return id_branch_intelligence;
    }

    public int getId_branch() {
        return id_branch;
    }

    public int getId_intelligence() {
        return id_intelligence;
    }

    public int getPriority() {
        return priority;
    }
    
    public int getId_user_create() {
        return id_user_create;
    }

    public int getId_user_update() {
        return id_user_update;
    }

    public Date getF_create() {
        return fcreate;
    }

    public Date getF_update() {
        return fupdate;
    }

    public JsonDataFetcher getDataFetcher() {
        return dataFetcher;
    }

    public List<BranchIntelligence> getRelationsList() {
        return relationsList;
    }

    public int getSelectedUserId() {
        return selectedUserId;
    }

    //Setters
    public void setId_branch_intelligence(int id_branch_intelligence) {
        this.id_branch_intelligence = id_branch_intelligence;
    }

    public void setId_branch(int id_branch) {
        this.id_branch = id_branch;
    }

    public void setId_intelligence(int id_intelligence) {
        this.id_intelligence = id_intelligence;
    }
    
    public void setPrioriy(int priority) {
        this.priority = priority;
    }

    public void setId_user_create(int id_user_create) {
        this.id_user_create = id_user_create;
    }

    public void setId_user_update(int id_user_update) {
        this.id_user_update = id_user_update;
    }

    public void setF_create(Date f_create) {
        this.fcreate = f_create;
    }

    public void setF_update(Date f_update) {
        this.fupdate = f_update;
    }

    public void setDataFetcher(JsonDataFetcher dataFetcher) {
        this.dataFetcher = dataFetcher;
    }

    public void setRelationsList(List<BranchIntelligence> relationsList) {
        this.relationsList = relationsList;
    }

    public void setSelectedUserId(int selectedUserId) {
        this.selectedUserId = selectedUserId;
    }
    
    // Metodo para traer todas las relaciones entre "Branch" e "Intelligences"
    public void loadAllRelations(JTable table, int currentPage, int relationsLimit, JFrame window) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        model.setColumnIdentifiers(new String[]{"id_branch_intelligence", "id_branch", "id_intelligence", "priority", "id_user_create", "id_user_update", "fcreate", "fupdate"});
        String tableName = "branch_intelligence";//Nombre de la tabla
        Class<BranchIntelligence> returnType = BranchIntelligence.class;  //Clase que se utiliza para mapear los resultados 

        // Calcula el límite inferior y superior de la página
        int cPage = (currentPage - 1) * relationsLimit;

        // Consulta SQL con la cláusula WHERE que filtra por los rangos anteriormente establecidos
        String whereClause = "id_branch_intelligence IS NOT NULL ORDER BY id_branch_intelligence ASC LIMIT " + cPage + "," + relationsLimit + ";";
        // Llama al método para obtener los datos
        ResultSetIES9021<BranchIntelligence> result = dataFetcher.fetchTableData(tableName, whereClause, returnType);

        if (result.getState()) {
            //Carga los nuevos datos 
            relationsList = result.getDatos();
            System.out.println(relationsList);
            //Recorre la lista de objetos de "branch_intelligence" y los agrega a la tabla 
            for (BranchIntelligence relation : relationsList) {
                Object[] rowData = {relation.getId_branch_intelligence(), relation.getId_branch(), relation.getId_intelligence(), relation.getPriority(), relation.getId_user_create(), relation.getId_user_update(), relation.getF_create(), relation.getF_update()};
                model.addRow(rowData);
            }
        } else {
            //Si no se puede muestra el mensaje de error 
            String clarification = result.getClarification();
            JOptionPane.showMessageDialog(window, "Error al cargar los datos: " + clarification, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // Metodo para Crear una relacion entre "Branch" e "Intelligence"
    public void createRelation(int branch, int intelligence, int selectedUserId, JFrame window) {
        // Obtener la fecha actual
        java.util.Date currentDate = new java.util.Date();
        // Convertir la fecha actual a un formato de fecha adecuado
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String f_create = dateFormat.format(currentDate);

        // Crear la consulta SQL para la inserción en la tabla "campus"
        String insertQuery = "INSERT INTO branch_intelligence (id_branch, id_intelligence, id_user_create, id_user_update, fcreate, fupdate) VALUES " +
                "(" + branch + ", '" + intelligence + "', '" + selectedUserId + ", " + selectedUserId + ", '" + f_create + "', '" + f_create + "');";

        // Ejecutar la consulta utilizando el método SendQuery
        ResultSetIES9021 result = DDBBConnection.SendQuery(insertQuery);

        // Verificar el estado del resultado
        if (result.getState()) {
            JOptionPane.showMessageDialog(window, "Relación creada con éxito.", "Actualización Exitosa", JOptionPane.INFORMATION_MESSAGE);
            window.dispose();
        } else {
            JOptionPane.showMessageDialog(window, "No se pudo crear la relación.", "Error de Creación", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // Metodo para Actualizar las relaciones entre "Branch" e "Intelligence"
    public void updateRelation(int branch, int intelligence, int selectedRelationId, int selectedUserId, JFrame window) {
        // Obtener la fecha actual
        java.util.Date currentDate = new java.util.Date();
        // Convertir la fecha actual a un formato de fecha adecuado
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String f_update = dateFormat.format(currentDate);

        // Crear la consulta SQL para la actualización en la tabla "campus"
        String updateQuery = "UPDATE branch_intelligence SET id_branch = " + branch + ", id_intelligence = '" + intelligence +
                "', id_user_update = " + selectedUserId + ", fupdate = '" + f_update + "' WHERE id_branch_intelligence = " + selectedRelationId + ";";

        // Ejecutar la consulta utilizando el método SendQuery
        ResultSetIES9021 result = DDBBConnection.SendQuery(updateQuery);

        // Verificar el estado del resultado
        if (result.getState()) {
            JOptionPane.showMessageDialog(window, "Los datos de la relación se actualizaron con éxito.", "Actualización Exitosa", JOptionPane.INFORMATION_MESSAGE);
            window.dispose();
        } else {
            JOptionPane.showMessageDialog(window, "No se pudo actualizar la relación.", "Error de Actualización", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // Metodo para Borrar una relación entre "Branch" e "Intelligence"
    public void deleteRelation(JTable table, JFrame window) {
        // Obtén el ID de la fila seleccionada 
        int selectedRow = table.getSelectedRow();
        String selectedID = table.getValueAt(selectedRow, 0).toString();
        System.out.println(selectedID);
    
        int response = JOptionPane.showConfirmDialog(null,
        "¿Estás seguro de que deseas eliminar esta relación?",
        "Confirmar eliminación",
        JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) {
            // Construye y envía la consulta SQL para eliminar la fila
            String query = "DELETE FROM branch_intelligence WHERE id_branch_intelligence = " + selectedID;
            try {
                ResultSetIES9021 queryResult = DDBBConnection.SendQuery(query);
                if (queryResult.getState()) {
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    model.removeRow(selectedRow); // Elimina la fila de la tabla si la eliminación en la BD fue exitosa
                    JOptionPane.showMessageDialog(null, "Registro eliminado con éxito.");
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo eliminar el registro: " + queryResult.getClarification());
                }
            } catch (Exception error) {
                // Manejo de excepción en caso de error en la consulta SQL o manipulación de la tabla
                JOptionPane.showMessageDialog(window, "Error inesperado al eliminar la fila: " + error.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
        public void loadComboBoxData(JComboBox<String> comboBox, String tableName, String idField, String nameField, String whereClause, Class<?> returnType, String idFieldToSelect) {
         // Obtener la lista de datos desde la base de datos
         ResultSetIES9021<?> result = dataFetcher.fetchTableData(tableName, whereClause, returnType);

         if (result.getState()) {
             // Limpiar el comboBox
             comboBox.removeAllItems();

             // Crear un mapa para asociar nombres con objetos
             Map<String, Object> objectMap = new HashMap<>();

             // Agregar cada elemento al comboBox y al mapa
             for (Object obj : result.getDatos()) {
                 String name = getNameFieldValue(obj, nameField);
                 comboBox.addItem(name);
                 objectMap.put(name, obj);
             }

             // Asignar el mapa al comboBox para acceder a los objetos más adelante
             comboBox.putClientProperty("objectMap", objectMap);

             // Seleccionar el elemento por defecto si idFieldToSelect no es nulo
             if (idFieldToSelect != null) {
                 for (int i = 0; i < comboBox.getItemCount(); i++) {
                     String itemName = comboBox.getItemAt(i);
                     Object obj = objectMap.get(itemName);
                     int id = getIdFieldValue(obj, idField);
                     if (idFieldToSelect.equals(String.valueOf(id))) {
                         comboBox.setSelectedIndex(i);
                         break;
                     }
                 }
             }
         } else {
             System.out.println("Error al cargar los datos desde la tabla " + tableName);
         }
     }

     // Función para obtener el valor del campo de nombre
     private String getNameFieldValue(Object obj, String nameField) {
         try {
             Field field = obj.getClass().getDeclaredField(nameField);
             field.setAccessible(true);
             return (String) field.get(obj);
         } catch (IllegalAccessException | IllegalArgumentException | NoSuchFieldException | SecurityException e) {
             return null;
         }
     }

     // Función para obtener el valor del campo de ID
     private int getIdFieldValue(Object obj, String idField) {
         try {
             Field field = obj.getClass().getDeclaredField(idField);
             field.setAccessible(true);
             return (int) field.get(obj);
         } catch (IllegalAccessException | IllegalArgumentException | NoSuchFieldException | SecurityException e) {
             return -1;
         }
     }
     
    
    // Metodo para cargar todos los usuarios en el combobox
    public void loadUsers(JComboBox<String> comboBoxUsers, String idUser) {
        String tableName = "users";
        String whereClause = null;
        Class<Users> returnType = Users.class;
        ResultSetIES9021<Users> result = dataFetcher.fetchTableData(tableName, whereClause, returnType);

        if (result.getState()) {
            // Limpiar el comboBox
            comboBoxUsers.removeAllItems();

            // Crear un mapa para asociar nombres de usuarios con objetos User
            Map<String, Users> userMap = new HashMap<>();

            // Agregar cada usuario al comboBox y al mapa
            for (Users user : result.getDatos()) {
                comboBoxUsers.addItem(user.getName()); // Agregar el nombre al comboBox
                userMap.put(user.getName(), user); // Asociar el nombre con el objeto User
            }

            // Asignar el mapa al comboBox para acceder a los objetos User más adelante
            comboBoxUsers.putClientProperty("userMap", userMap);

            // Se obtiene el ID del usuario seleccionado y se asigna en el combobox
            selectedUserId = handleUserSelection(comboBoxUsers);

            // Seleccionar el usuario por defecto si idUser no es nulo
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
        } else {
            System.out.println("Error al cargar los usuarios.");
        }
    }

    // Función para almacenar usuario seleccionado del combobox
    public static int handleUserSelection(JComboBox<String> comboBox) {
        int selectedUserId = -1; // Valor predeterminado si no se encuentra ningún usuario seleccionado
        int selectedIndex = comboBox.getSelectedIndex();
        if (selectedIndex != -1) {
            String selectedUserName = comboBox.getItemAt(selectedIndex);
            Map<String, Users> userMap = (Map<String, Users>) comboBox.getClientProperty("userMap");
            if (userMap != null && userMap.containsKey(selectedUserName)) {
                Users selectedUser = userMap.get(selectedUserName);
                selectedUserId = selectedUser.getId_user();
            }
        }
        return selectedUserId;
    }
}
