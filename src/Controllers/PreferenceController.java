/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import Utils.DDBBConnection;
import Utils.JsonDataFetcher;
import Utils.ResultSetIES9021;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author wirix
 */
public abstract class PreferenceController {

    public static List<String[]> getPreferences() {
        List<String[]> preferenceList = JsonDataFetcher.selectQuery("*", "preference", "");
        for (String[] strings : preferenceList) {
            System.out.println(strings);
        }
        return preferenceList;
    }

    public static void createPreference(String group, String name, int id_wk) {
        String query = "INSERT INTO preference (`group`, `name`, id_word_key, id_user_create, id_user_update, f_create, f_update) VALUES ('"
                + group + "', '" + name + "', " + id_wk + ", 6, 6, NOW(), NOW())";
        ResultSetIES9021 SendQuery = DDBBConnection.SendQuery(query);
    }
    
    public static void modifyPreference(String group, String name, int id_wk, int id){
        String updateQuery = "UPDATE preference " +
                    "SET `group` = '" + group + "', " +
                    "`name` = '" + name + "', " +
                    "id_word_key = " + id_wk + ", " +
                    "f_update = NOW()"+
                    " WHERE id_preference = " + id;
        System.out.println(updateQuery);
        System.out.println(DDBBConnection.QueryVerification(updateQuery));
        DDBBConnection.SendQuery(updateQuery);
    }
    
    public static void deletePreference(int id){
        String query = "DELETE FROM preference WHERE id_preference = "+ id;
        DDBBConnection.SendQuery(query);
    }
}
