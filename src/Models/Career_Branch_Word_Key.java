
package Models;

import static Utils.DDBBConnection.SendQuery;
import static Utils.JsonDataFetcher.selectQuery;
import java.util.ArrayList;
import java.util.Arrays;


public class Career_Branch_Word_Key {
    private int id_career_branch_word_key; 
    private int id_career_word_key; 
    private int id_branch_word_key; 
    public Career_Branch_Word_Key() {
    }
    public Career_Branch_Word_Key(int id_career_word_key, int id_branch_word_key) {
        this.id_career_word_key = id_career_word_key;
        this.id_branch_word_key = id_branch_word_key;
    }

    public int getId_career_branch_word_key() {
        return id_career_branch_word_key;
    }

    public void setId_career_branch_word_key(int id_career_branch_word_key) {
        this.id_career_branch_word_key = id_career_branch_word_key;
    }

    public int getId_career_word_key() {
        return id_career_word_key;
    }

    public void setId_career_word_key(int id_career_word_key) {
        this.id_career_word_key = id_career_word_key;
    }

    public int getId_branch_word_key() {
        return id_branch_word_key;
    }

    public void setId_branch_word_key(int id_branch_word_key) {
        this.id_branch_word_key = id_branch_word_key;
    }    
    //Methods
    public void create(){
       String query="INSERT INTO `ies9021_database`.`career_branch_word_key` (`id_career_word_key`, `id_branch_word_key`) VALUES ('"+id_career_word_key+"', '"+id_branch_word_key+"');";
       SendQuery(query);
    }
    private void guardarDatos(){
    
    }
    public void update(){
        String query="UPDATE `ies9021_database`.`career_branch_word_key` SET `id_career_word_key` = '"+id_career_word_key+"', `id_branch_word_key` = '"+id_branch_word_key+"' WHERE (`id_career_branch_word_key` = '"+id_career_branch_word_key+"');";
       SendQuery(query);
    }
  
    public void revision_CBWK(String Lista){
        String SS=" id_career_branch_work_key "
              ,TT=" ies9021_database.career_branch_word_key "
              ,WW=" id_career IN ( "+Lista+" )";
        ArrayList<String[]> Dev;
        try {
            Dev=new ArrayList<>(selectQuery(SS, TT, WW));
            if(!Dev.isEmpty()){
                Lista="";
                for (String[] strings : Dev) {
                    Lista+=Arrays.toString(strings);
                }
                Lista=Lista.substring(0, Lista.length()-1).replace("[", "").replace("]", ",");
                System.out.println("Revision_CBWK = "+Lista);
                //Delete cbwk
            }    
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

