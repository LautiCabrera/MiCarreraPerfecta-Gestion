package Models;

import static Utils.DDBBConnection.SendQuery;
import Utils.JsonDataFetcher;
import static Utils.JsonDataFetcher.selectQuery;
import Utils.ResultSetIES9021;
import java.util.ArrayList;
import java.util.Arrays;

public class Career_Branch_Word_Key {
    private int id_career_branch_word_key; 
    private int id_career_word_key; 
    private int id_branch_word_key; 
    private ArrayList<Career_Branch_Word_Key> CBWK;
    private Branch_Words_Key BWK[];
    private Career_Word_Key CWK[];
    
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
    
    private void settearBranch_Words_Key(int id_branch){
        ResultSetIES9021<Branch_Words_Key> RSIB = new JsonDataFetcher<Branch_Words_Key>().fetchTableData(
            "branch_words_key","id_branch ="+id_branch,Branch_Words_Key.class);
        
       BWK= new Branch_Words_Key[RSIB.getDatos().size()];
        for(int i=0;i<RSIB.getDatos().size();i++){
            BWK[i]=RSIB.getDatos().get(i);    
        }
    }
    
    private void settearCareer_Word_Key(int id_career){
        ResultSetIES9021<Career_Word_Key> RSIC = new JsonDataFetcher<Career_Word_Key>().fetchTableData(
            "career_word_key","id_career ="+id_career ,Career_Word_Key.class);
        CWK= new Career_Word_Key[RSIC.getDatos().size()];
        for(int i=0;i<RSIC.getDatos().size();i++){
            CWK[i]=RSIC.getDatos().get(i);    
        }
    }
    
    private void comparar(){
        for (Career_Word_Key career_Word_Key : CWK) {
            for (Branch_Words_Key branch_Words_Key : BWK) {
              if(career_Word_Key.getId_word_key() == branch_Words_Key.getId_words_key() ){
              Career_Branch_Word_Key CB= new Career_Branch_Word_Key(career_Word_Key.getId_career_word_key(),branch_Words_Key.getId_branch_word_key());
              CB.create();
              }  
            }
        }
    }
}