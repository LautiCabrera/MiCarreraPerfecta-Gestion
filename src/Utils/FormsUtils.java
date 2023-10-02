package Utils;

import Models.University;
import Models.Users;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JComboBox;

public class FormsUtils {
    
    private int selectedUniversityId; // Declaración de la variable
    private int selectedUserId; // Declaración de la variable
    private int selectedMainValue;
    
    // Función para cargar todas las universidades en el combobox
    public void loadUniversities(JsonDataFetcher dataFetcher, JComboBox<String> comboBoxUniversity, String idUniversity) {
        String tableName = "university";
        String whereClause = null;
        Class<University> returnType = University.class;
        ResultSetIES9021<University> result = dataFetcher.fetchTableData(tableName, whereClause, returnType);

        if (result.getState()) {
            // Limpiar el comboBox
            comboBoxUniversity.removeAllItems();

            // Crear un mapa para asociar nombres de universidades con objetos University
            Map<String, University> universityMap = new HashMap<>();

            // Agregar cada universidad al comboBox y al mapa
            for (University university : result.getDatos()) {
                comboBoxUniversity.addItem(university.getName()); // Agregar el nombre al comboBox
                universityMap.put(university.getName(), university); // Asociar el nombre con el objeto University
            }

            // Asignar el mapa al comboBox para acceder a los objetos University más adelante
            comboBoxUniversity.putClientProperty("universityMap", universityMap);
            
            // Se obtiene el ID de la universidad seleccionada y se asigna a selectedUniversityId
            selectedUniversityId = handleUniversitySelection(comboBoxUniversity);
            
            // Seleccionar el usuario por defecto si idUser no es nulo
            if (idUniversity != null) {
                for (int i = 0; i < comboBoxUniversity.getItemCount(); i++) {
                    String universityName = comboBoxUniversity.getItemAt(i);
                    University university = universityMap.get(universityName); // Obtén el objeto Users desde el mapa
                    if (idUniversity.equals(String.valueOf(university.getId_university()))) {
                        comboBoxUniversity.setSelectedIndex(i);
                        break;
                    }
                }
            }
            
        } else {
            System.out.println("Error al cargar las universidades.");
        }
    }
    
    // Función para cargar todos los usuarios en el combobox
    public void loadUsers(JsonDataFetcher dataFetcher, JComboBox<String> comboBoxUsers, String idUser) {
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
            
            // Se obtiene el ID de la universidad seleccionada y se asigna a selectedUniversityId
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
    
    // Función para almacenar si es sede pincipal o no
    public static int handleComboBoxSelection(JComboBox<String> comboBox) {
        String selectedOption = comboBox.getSelectedItem().toString();
        if ("Si".equals(selectedOption)) {
            return 1; // SI
        } else if ("No".equals(selectedOption)) {
            return 0; // NO
        }
        return -1;
    }
    
    // Función para almacenar universidad seleccionada del combobox
    public static int handleUniversitySelection(JComboBox<String> comboBox) {
        int selectedUniversityId = -1; // Valor predeterminado si no se encuentra ninguna universidad seleccionada
        int selectedIndex = comboBox.getSelectedIndex();
        if (selectedIndex != -1) {
            String selectedUniversityName = comboBox.getItemAt(selectedIndex);
            Map<String, University> universityMap = (Map<String, University>) comboBox.getClientProperty("universityMap");
            if (universityMap != null && universityMap.containsKey(selectedUniversityName)) {
                University selectedUniversity = universityMap.get(selectedUniversityName);
                selectedUniversityId = selectedUniversity.getId();
            }
        }
        return selectedUniversityId;
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
