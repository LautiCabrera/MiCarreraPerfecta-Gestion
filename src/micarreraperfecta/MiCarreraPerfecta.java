package micarreraperfecta;

import Models.WordsKey;
import Utils.DDBBConnection;
import Utils.JavaFileGenerator;
import Utils.JsonDataFetcher;
import Utils.ResultSetIES9021;

public class MiCarreraPerfecta {

    public static void main(String[] args) {
        
    }

    public void ejemplo(){
        ResultSetIES9021<WordsKey> words = new JsonDataFetcher<WordsKey>().fetchTableData(
            "words_key", "id_words_key < 10",WordsKey.class);

        System.out.println(words.getState());
        System.out.println(words.getClarification());
        System.out.println("-------------------------");

        for (WordsKey wordskey : words.getDatos()) {
            System.out.print("ID: " + wordskey.getId_word_key());
            System.out.print(" WordsKey: " + wordskey.getWord());
            System.out.print(" ID User Create: " + wordskey.getId_user_create());
            System.out.println("");
            System.out.println("-------------------------");
        }

        ResultSetIES9021 query = new ResultSetIES9021<>();
        query = DDBBConnection.SendQuery("UPDATE career SET id_career = 1 WHERE id_career = 1;");

        System.out.println("State: " + query.getState());
        System.out.println("Clarification: " + query.getClarification());
        System.out.println("-------------------------");
    } 
}
