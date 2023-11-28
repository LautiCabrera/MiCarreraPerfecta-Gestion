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
public abstract class CareerController {

    public static List<String[]> getCareer() {
        List<String[]> careerList = JsonDataFetcher.selectQuery("*", "career", "");
        for (String[] strings : careerList) {
            System.out.println(strings);
        }
        return careerList;
    }

    public static void createCareer(String name, int title_intermediate, String description, int duration_months, int id_type_career, int id_modality, int id_branch, int id_range) {
    String query = "INSERT INTO career (name, title_intermediate, description, duration_months, id_type_career, id_modality, id_branch, id_range, id_user_create, id_user_update, f_create, f_update) VALUES ('"
            + name + "', " + title_intermediate + ", '" + description + "', " + duration_months + ", " + id_type_career + ", " + id_modality + ", " + id_branch + ", " + id_range + ", 6, 6, NOW(), NOW())";
    ResultSetIES9021 SendQuery = DDBBConnection.SendQuery(query);
}

    
    public static void modifyCareer(int id_career, String name, int title_intermediate, String description, int duration_months, int id_type_career, int id_modality, int id_branch, int id_range, int idCareer){
        String updateQuery = "UPDATE career " +
                    "SET `name` = '" + name + "', " +
                    "title_intermediate = " + title_intermediate + ", " +
                    "`description` = '" + description + "', " +
                    "`duration_months` = '" + duration_months + "', " +
                    "`id_type_career` = '" + id_type_career + "', " +
                    "`id_modality` = '" + id_modality + "', " +
                    "`id_branch` = '" + id_branch + "', " +
                    "`id_range` = '" + id_range + "', " +
                    "f_update = NOW()"+
                    " WHERE id_career = " + id_career;
        System.out.println(updateQuery);
        System.out.println(DDBBConnection.QueryVerification(updateQuery));
        DDBBConnection.SendQuery(updateQuery);
    }
    
    public static void deleteCareer(int id){
        String query = "DELETE FROM career WHERE id_career = "+ id;
        DDBBConnection.SendQuery(query);
    }
}
