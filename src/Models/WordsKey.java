package Models;

//@author Enzo Rico

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonProperty;


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
    
    
    
    
}
