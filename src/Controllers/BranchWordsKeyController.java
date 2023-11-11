/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import Utils.DDBBConnection;
import Utils.JsonDataFetcher;
import java.util.List;

/**
 *
 * @author Melisa
 */
public class BranchWordsKeyController {
    
     public static List<String[]> getBranchWordsKey() {
        List<String[]> branchWordsKeyList = JsonDataFetcher.selectQuery("*", "branch_words_key", "");
        for (String[] strings : branchWordsKeyList) {
            System.out.println(strings);
        }
        return branchWordsKeyList;
    }
    
    public static void deleteBranchWordsKey(int id){
        String query = "DELETE FROM branch_words_key WHERE id_branch_word_key = "+ id;
        DDBBConnection.SendQuery(query);
    }
    
    public static void buscarPorNombreDeBranch(int id){
        String query = "select name from branch where id_branch =" + id;
        DDBBConnection.SendQuery(query);
    }
}
