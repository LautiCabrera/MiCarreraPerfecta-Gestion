
package Models;

import Utils.JsonDataFetcher;
import Utils.ResultSetIES9021;
import java.util.ArrayList;


public class Career_Branch_Word_Key_Methods {
    private ArrayList<Career_Branch_Word_Key> CBWK;
    private Branch_Words_Key BWK[];
    private Career_Word_Key CWK[];
    private ResultSetIES9021 RSIB;
    private ResultSetIES9021 RSIC;
    
    private void createCareer_Branch_Word_Key(){
        
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
              if(career_Word_Key.getId_Word_key() == branch_Words_Key.getId_words_key() ){
              Career_Branch_Word_Key CB= new Career_Branch_Word_Key(career_Word_Key.getId_Career_word_key(),branch_Words_Key.getId_branch_word_key());
              CB.create();
              }  
            }
        }
    }
}
