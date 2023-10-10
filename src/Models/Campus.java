package Models;

import Utils.DDBBConnection;
import Utils.JsonDataFetcher;
import Utils.ResultSetIES9021;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Campus {
    
    // *******************************
    // Sección: Campos de la Clase
    // *******************************
    
    private int id_campus;
    private int id_university;
    private String name;
    private String location;
    private float latitude;
    private float longitude;
    private int main;
    private String www;
    private String email;
    private int id_user_create;
    private int id_user_update;
    @JsonProperty("f_create")
    private Date f_create;
    @JsonProperty("f_update")
    private Date f_update;
    
    // *******************************
    // Sección: Constructores
    // *******************************
    
    public Campus() {
        
    }

    public Campus(int id_campus, int id_university, String name, String location, float latitude, float longitude, int main, String www, String email, int id_user_create, int id_user_update, Date f_create, Date f_update) {
        this.id_campus = id_campus;
        this.id_university = id_university;
        this.name = name;
        this.location = location;
        this.latitude = latitude;
        this.longitude = longitude;
        this.main = main;
        this.www = www;
        this.email = email;
        this.id_user_create = id_user_create;
        this.id_user_update = id_user_update;
        this.f_create = f_create;
        this.f_update = f_update;
    }
    
    // *******************************
    // Sección: Métodos de Acceso
    // *******************************

    public int getId_campus() {
        return id_campus;
    }

    public void setId_campus(int id_campus) {
        this.id_campus = id_campus;
    }

    public int getId_university() {
        return id_university;
    }

    public void setId_university(int id_university) {
        this.id_university = id_university;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public int getMain() {
        return main;
    }

    public void setMain(int main) {
        this.main = main;
    }

    public String getWww() {
        return www;
    }

    public void setWww(String www) {
        this.www = www;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Date getFcreate() {
        return f_create;
    }

    public void setFcreate(Date f_create) {
        this.f_create = f_create;
    }

    public Date getFupdate() {
        return f_update;
    }

    public void setFupdate(Date f_update) {
        this.f_update = f_update;
    }
    
    // *******************************
    // Sección: Métodos de Carga de Datos
    // *******************************
    
    JsonDataFetcher dataFetcher = new JsonDataFetcher();
    
    public void loadTableData(JTable table, int currentPage, int pageSize, JFrame window) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        model.setColumnIdentifiers(new String[]{"id_campus", "id_university", "name", "location", "latitude", "longitude", "main", "www", "email", "id_user_create", "id_user_update", "f_create", "f_update"});
        String tableName = "campus"; // Nombre de la tabla
        Class<Campus> returnType = Campus.class; // Clase que se utiliza para mapear los resultados

         // Calcula el límite inferior y superior de la página
        int cPage = (currentPage - 1) * pageSize;

        // Consulta SQL con la cláusula WHERE que filtra por los rangos anteriormente establecidos
        String whereClause = "id_campus IS NOT NULL ORDER BY id_campus ASC LIMIT " + cPage + "," + pageSize + ";";

        // Llama al método para obtener los datos
        ResultSetIES9021<Campus> result = dataFetcher.fetchTableData(tableName, whereClause, returnType);

        if (result.getState()) {
            // Carga los nuevos datos
            List<Campus> campusList = result.getDatos();
            // Recorre la lista de objetos de campus y los agrega a la tabla
            for (Campus campus : campusList) {
                Object[] rowData = {
                    campus.getId_campus(),
                    campus.getId_university(),
                    campus.getName(),
                    campus.getLocation(),
                    campus.getLatitude(),
                    campus.getLongitude(),
                    campus.getMain(),
                    campus.getWww(),
                    campus.getEmail(),
                    campus.getId_user_create(),
                    campus.getId_user_update(),
                    campus.getFcreate(),
                    campus.getFupdate()
                };
                model.addRow(rowData);
            }
        } else {
            // Si no se puede, muestra el mensaje de error
            String clarification = result.getClarification();
            JOptionPane.showMessageDialog(window, "Error al cargar los datos: " + clarification, "Error", JOptionPane.ERROR_MESSAGE);
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
     
     // *******************************
     // Sección: Métodos de Manipulación de ComboBox
     // *******************************

     // Función para almacenar si es sede pincipal o no
     public int handleSedeSelection(JComboBox<String> comboBox) {
         String selectedOption = comboBox.getSelectedItem().toString();
         if ("Si".equals(selectedOption)) {
             return 1; // SI
         } else if ("No".equals(selectedOption)) {
             return 0; // NO
         }
         return -1;
     }
     
    // *******************************
    // Sección: Métodos de Busqueda
    // *******************************
     
     public void searchCampus(String searchText, JTable table,JFrame window) {
        // Utiliza JsonDataFetcher para buscar campus por nombre
        String whereClause = "name LIKE '%" + searchText + "%'";
        Class<Campus> returnType = Campus.class;
        ResultSetIES9021<Campus> result = dataFetcher.fetchTableData("campus", whereClause, returnType);

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        model.setColumnIdentifiers(new String[]{"id_campus", "id_university", "name", "location", "latitude", "longitude", "main", "www", "email", "id_user_create", "id_user_update", "f_create", "f_update"});

        if (result.getState()) {
            // Carga los nuevos datos
            List<Campus> campusList = result.getDatos();
            // Recorre la lista de objetos de campus y los agrega a la tabla
            for (Campus campus : campusList) {
                Object[] rowData = {campus.getId_campus(), campus.getId_university(), campus.getName(), campus.getLocation(), campus.getLatitude(), campus.getLongitude(), campus.getMain(), campus.getWww(), campus.getEmail(), campus.getId_user_create(), campus.getId_user_update(), campus.getFcreate(), campus.getFupdate()};
                model.addRow(rowData);
            }
            if (campusList.isEmpty()) {
                JOptionPane.showMessageDialog(window, "No se encontraron resultados para la búsqueda: " + searchText,
                    "Búsqueda sin resultados", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            String clarification = result.getClarification();
            JOptionPane.showMessageDialog(window, "Error al cargar los datos: " + clarification, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
     
    // *******************************
    // Sección: Crear
    // *******************************
     
    public void saveCampus(String name, String location, String latitude, String longitude, String www, String email, int selectedUniversityId, int selectedUserId, int selectedMainValue, JFrame window) {
        // Obtener la fecha actual
        java.util.Date currentDate = new java.util.Date();
        // Convertir la fecha actual a un formato de fecha adecuado
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String f_create = dateFormat.format(currentDate);

        // Crear la consulta SQL para la inserción en la tabla "campus"
        String insertQuery = "INSERT INTO campus (id_university, name, location, latitude, longitude, main, www, email, id_user_create, id_user_update, f_create, f_update) VALUES " +
                "(" + selectedUniversityId + ", '" + name + "', '" + location + "', " + Float.parseFloat(latitude) + ", " + Float.parseFloat(longitude) + ", " +
                selectedMainValue + ", '" + www + "', '" + email + "', " + selectedUserId + ", " + selectedUserId + ", '" + f_create + "', '" + f_create + "');";

        // Ejecutar la consulta utilizando el método SendQuery
        ResultSetIES9021 result = DDBBConnection.SendQuery(insertQuery);

        // Verificar el estado del resultado
        if (result.getState()) {
            JOptionPane.showMessageDialog(window, "Campus creado con éxito.", "Actualización Exitosa", JOptionPane.INFORMATION_MESSAGE);
            window.dispose();
        } else {
            JOptionPane.showMessageDialog(window, "No se pudo crear el campus.", "Error de Creación", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // *******************************
    // Sección: Actualizar
    // *******************************
    
    public void updateCampus(String name, String location, String latitude, String longitude, String www, String email, int selectedUniversityId, int selectedUserId, int selectedMainValue, String idCampus, JFrame window) {
        // Obtener la fecha actual
        java.util.Date currentDate = new java.util.Date();
        // Convertir la fecha actual a un formato de fecha adecuado
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String f_update = dateFormat.format(currentDate);

        // Crear la consulta SQL para la actualización en la tabla "campus"
        String updateQuery = "UPDATE campus SET id_university = " + selectedUniversityId + ", name = '" + name + "', location = '" + location +
                "', latitude = " + Float.parseFloat(latitude) + ", longitude = " + Float.parseFloat(longitude) +
                ", main = " + selectedMainValue + ", www = '" + www + "', email = '" + email +
                "', id_user_update = " + selectedUserId + ", f_update = '" + f_update + "' WHERE id_campus = " + idCampus + ";";

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
    
    // *******************************
    // Sección: Eliminar
    // *******************************

    // Función para eliminar campus
    public void deleteCampus(JTable table, JFrame window) {
        // Obtén el ID de la fila seleccionada
        int selectedRow = table.getSelectedRow();
        String selectedID = table.getValueAt(selectedRow, 0).toString();
        // Verifica si el ID está siendo usado en otras tablas
        boolean idUsed = campusUsed(selectedID);

        if (idUsed) {
            // El ID está asociado a otras tablas, muestra un mensaje de error
            JOptionPane.showMessageDialog(window, "No se puede eliminar este campus porque el ID está asociado a otras tablas.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            int response = JOptionPane.showConfirmDialog(null,
                "¿Estás seguro de que deseas eliminar este campus?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                // El ID no está asociado a otras tablas, procede con la eliminación
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
            }
        }
    }

    // Función para verificar si un campus puede ser eliminado o no
    public boolean campusUsed(String idCampus) {
        int id = Integer.parseInt(idCampus);
        List<String> tablesToCheck = Arrays.asList("campus_career", "university"); 
        String idFieldName = "id_campus"; 

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

        // Si llegas hasta aquí, significa que el ID no se encontró en ninguna de las tablas
        return false;
    }
    
}
