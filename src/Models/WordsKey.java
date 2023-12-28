package Models;

//@author Enzo Rico
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonProperty;
import Utils.DDBBConnection;
import Utils.JsonDataFetcher;
import Utils.ResultSetIES9021;
import Models.Users;
import java.awt.Component;
import static java.lang.Integer.parseInt;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class WordsKey {

    @JsonProperty("id_word_key")
    private int id_word_key;
    @JsonProperty("word")
    private String word;
    @JsonProperty("id_user_create")
    private int id_user_create;
    @JsonProperty("id_user_update")
    private int id_user_update;
    @JsonProperty("f_create")
    private Date f_create;
    @JsonProperty("f_update")
    private Date f_update;

    JsonDataFetcher dataFetcher = new JsonDataFetcher();
    private List<WordsKey> wordsList;
    private int selectedUserId;

    public WordsKey() {
    }

    public WordsKey(int id_word_key, String word, int id_user_create, int id_user_update, Date f_create, Date f_update) {
        this.id_word_key = id_word_key;
        this.word = word;
        this.id_user_create = id_user_create;
        this.id_user_update = id_user_update;
        this.f_create = f_create;
        this.f_update = f_update;
    }

    public int getId_word_key() {
        return id_word_key;
    }

    public void setId_word_key(int id_words_key) {
        this.id_word_key = id_words_key;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
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

    public Date getF_create() {
        return f_create;
    }

    public void setF_create(Date f_create) {
        this.f_create = f_create;
    }

    public Date getF_update() {
        return f_update;
    }

    public void setF_update(Date f_update) {
        this.f_update = f_update;
    }

    // Metodo para traer todas las wordskey
    public void loadAllWordsKey(JTable table, int currentPage, int wordsLimit, JFrame window) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        model.setColumnIdentifiers(new String[]{"id_word_key", "word", "id_user_create", "id_user_update", "f_create", "f_update"});
        String tableName = "words_key";//Nombre de la tabla
        Class<WordsKey> returnType = WordsKey.class;  //Clase que se utiliza para mapear los resultados 

        // Calcula el límite inferior y superior de la página
        int cPage = (currentPage - 1) * wordsLimit;

        // Consulta SQL con la cláusula WHERE que filtra por los rangos anteriormente establecidos
        String whereClause = "id_word_key IS NOT NULL ORDER BY id_word_key ASC LIMIT " + cPage + "," + wordsLimit + ";";
        // Llama al método para obtener los datos
        ResultSetIES9021<WordsKey> result = dataFetcher.fetchTableData(tableName, whereClause, returnType);

        if (result.getState()) {
            //Carga los nuevos datos 
            wordsList = result.getDatos();
            //Recorre la lista de objetos de "words_key" y los agrega a la tabla 
            for (WordsKey wordskey : wordsList) {
                Object[] rowData = {wordskey.getId_word_key(), wordskey.getWord(), wordskey.getId_user_create(), wordskey.getId_user_update(), wordskey.getF_create(), wordskey.getF_update()};
                model.addRow(rowData);
            }
        } else {
            //Si no se puede muestra el mensaje de error 
            String clarification = result.getClarification();
            JOptionPane.showMessageDialog(window, "Error al cargar los datos: " + clarification, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Metodo para crear palabras claves
    public void createWord(List<String> words, int userID, JFrame window) {
        // Obtener la fecha actual
        java.util.Date currentDate = new java.util.Date();
        // Convertir la fecha actual a un formato de fecha adecuado
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String f_create = dateFormat.format(currentDate);

        // Crear la consulta SQL para la inserción en la tabla "words_key"
        StringBuilder insertQuery = new StringBuilder("INSERT INTO words_key (word, id_user_create, id_user_update, f_create, f_update) VALUES ");

        // Agregar cada palabra a la consulta SQL
        for (String word : words) {
            insertQuery.append("('").append(word).append("', ").append(userID).append(", ").append(userID).append(", '").append(f_create).append("', '").append(f_create).append("'),");
        }

        // Eliminar la coma adicional al final de la consulta
        insertQuery.deleteCharAt(insertQuery.length() - 1);
        
        System.out.println(insertQuery.toString());

        // Ejecutar la consulta utilizando el método SendQuery
        ResultSetIES9021 result = DDBBConnection.SendQuery(insertQuery.toString());

        System.out.println(result.getState());
        
        // Verificar el estado del resultado
        if (result.getState()) {
            System.out.println("Palabras clave creadas con éxito.");
            JOptionPane.showMessageDialog(window, "Palabras clave creadas con éxito.", "Actualización Exitosa", JOptionPane.INFORMATION_MESSAGE);
            window.dispose();
        } else {
            System.out.println("No se pudieron crear las palabras clave.");
            JOptionPane.showMessageDialog(window, "No se pudieron crear las palabras clave.", "Error de Creación", JOptionPane.ERROR_MESSAGE);
        }

    }

    // Metodo para editar palabras clave
    public void updateWord(String word, String idWord, JComboBox<String> comboBoxUsers, JFrame window) {
        //Obtengo el Id del usuario seleccionado en el combobox(usuario que realizara la modificacion)
        int idUserUpdate;
        idUserUpdate = WordsKey.handleUserSelection(comboBoxUsers);
        // Obtener la fecha actual
        java.util.Date currentDate = new java.util.Date();
        // Convertir la fecha actual a un formato de fecha adecuado
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String f_update = dateFormat.format(currentDate);

        // Crear la consulta SQL para la actualización en la tabla "words_key"
        String updateQuery = "UPDATE words_key SET word = '" + word + "', id_user_update = " + idUserUpdate + ", f_update = '" + f_update + "' WHERE id_word_key = " + idWord + ";";

        // Ejecutar la consulta utilizando el método SendQuery
        ResultSetIES9021 result = DDBBConnection.SendQuery(updateQuery);

        // Verificar el estado del resultado
        if (result.getState()) {
            JOptionPane.showMessageDialog(window, "La palabra clave se actualizó con éxito.", "Actualización Exitosa", JOptionPane.INFORMATION_MESSAGE);
            // Cierra la ventana de "words_key_update" después de la actualización
            window.dispose();
        } else {
            JOptionPane.showMessageDialog(window, "No se pudo actualizar la palabra clave.", "Error de Actualización", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Metodo para encontrar palabras clave
    public void findWord(String searchText, JTable table, JFrame window) {
        String whereClause = "word LIKE '%" + searchText + "%'";
        Class<WordsKey> returnType = WordsKey.class;
        ResultSetIES9021<WordsKey> result = dataFetcher.fetchTableData("words_key", whereClause, returnType);

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        model.setColumnIdentifiers(new String[]{"id_word_key", "word", "id_user_create", "id_user_update", "f_create", "f_update"});

        if (result.getState()) {
            //carga los nuevos datos
            List<WordsKey> wordsList = result.getDatos();
            //Recorre la lista de objetos de "words_key" y los agrega a la tabla
            for (WordsKey wordskey : wordsList) {
                Object[] rowData = {wordskey.getId_word_key(), wordskey.getWord(), wordskey.getId_user_create(), wordskey.getId_user_update(), wordskey.getF_create(), wordskey.getF_update()};
                model.addRow(rowData);
            }
            if (wordsList.isEmpty()) {
                // No se encontraron resultados, muestra un mensaje
                JOptionPane.showMessageDialog(window, "No se encontraron resultados para la búsqueda: " + searchText,
                        "Búsqueda sin resultados", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            // Si no se puede muestra el mensaje de error
            String clarification = result.getClarification();
            JOptionPane.showMessageDialog(window, "Error al cargar los datos: " + clarification, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Metodo para verificar si una palabra clave puede ser eliminada o no
    public boolean wordUsed(String idWordKey) {
        // Define una lista de nombres de tablas en las que deseas verificar la existencia del ID
        int id = parseInt(idWordKey);
        System.out.println(id);
        List<String> tablesToCheck = Arrays.asList("branch_words_key"); // Reemplaza con los nombres de tus tablas
        System.out.println(tablesToCheck);
        // Define el nombre del campo en esas tablas donde deseas buscar el ID
        String idFieldName = "id_word_key"; // Reemplaza con el nombre del campo correspondiente

        for (String tableName : tablesToCheck) {
            // Construye la consulta SQL para buscar el ID en la tabla actual
            System.out.println(tableName);
            String query = "SELECT COUNT(*) FROM " + tableName + " WHERE " + idFieldName + " = " + id;
            System.out.println(query);

            // Ejecuta la consulta utilizando ResultSetIES9021
            ResultSetIES9021 result = DDBBConnection.SendQuery(query);

            if (result.getState()) {
                System.out.println("Entro al if getState");
                System.out.println(result.getDatos());
                List<Integer> counts = result.getDatos();
                System.out.println(counts);
                if (!counts.isEmpty()) {
                    int count = counts.get(0); // Obtén el resultado del recuento de filas
                    System.out.println("Entro al if Empty ");
                    if (count > 0) {
                        // Si count es mayor que 0, significa que el ID existe en esta tabla
                        System.out.println("Retorna true");
                        return true;
                    }
                }
            } else {
                // Manejo de errores si la consulta no fue exitosa
                String clarification = result.getClarification();
                System.out.println("Error al ejecutar la consulta en " + tableName + ": " + clarification);
                // Puedes elegir cómo manejar este error, como lanzar una excepción
            }
            System.out.println("1 vuelta");
        }

        // Si llegas hasta aquí, significa que el ID no se encontró en ninguna de las tablas
        return false;
    }

    // Metodo para eliminar palabras clave
    public void deleteWord(JTable table, JFrame window) {
        // Obtén el ID de la fila seleccionada 
        int selectedRow = table.getSelectedRow();
        String selectedID = table.getValueAt(selectedRow, 0).toString();
        //Verifico si el id esta siendo usado en otra tabla 
        System.out.println(selectedID);
        boolean idUsed = wordUsed(selectedID);

        if (idUsed == true) {
            // El ID está asociado a otras tablas, muestra un mensaje de error
            JOptionPane.showMessageDialog(window, "No se puede eliminar esta fila porque el ID está asociado a otras tablas.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            int response = JOptionPane.showConfirmDialog(null,
                    "¿Estás seguro de que deseas eliminar esta word key?",
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                // El ID no está asociado a otras tablas, procede con la eliminación
                // Construye y envía la consulta SQL para eliminar la fila
                String query = "DELETE FROM words_key WHERE id_word_key = " + selectedID;
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