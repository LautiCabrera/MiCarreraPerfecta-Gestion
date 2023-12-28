package Models;

public class Branch_Words_Key {
    private int id_branch_word_key;
    private int id_branch;
    private int id_words_key;
    private int id_career;
    private int branch_words_keycol;
    private int id_user_create;
    private int id_user_update;
    private String f_create;
    private String f_update;

    //Contructores
    public Branch_Words_Key(int id_branch_word_key, int id_branch, int id_words_key, int id_career, int branch_words_keycol, int id_user_create, int id_user_update, String f_create, String f_update) {
        this.id_branch_word_key = id_branch_word_key;
        this.id_branch = id_branch;
        this.id_words_key = id_words_key;
        this.id_career = id_career;
        this.branch_words_keycol = branch_words_keycol;
        this.id_user_create = id_user_create;
        this.id_user_update = id_user_update;
        this.f_create = f_create;
        this.f_update = f_update;
    }

    //gets and sets
    public int getId_branch_word_key() {
        return id_branch_word_key;
    }

    public void setId_branch_word_key(int id_branch_word_key) {
        this.id_branch_word_key = id_branch_word_key;
    }

    public int getId_branch() {
        return id_branch;
    }

    public void setId_branch(int id_branch) {
        this.id_branch = id_branch;
    }

    public int getId_words_key() {
        return id_words_key;
    }

    public void setId_words_key(int id_words_key) {
        this.id_words_key = id_words_key;
    }

    public int getId_career() {
        return id_career;
    }

    public void setId_career(int id_career) {
        this.id_career = id_career;
    }

    public int getBranch_words_keycol() {
        return branch_words_keycol;
    }

    public void setBranch_words_keycol(int branch_words_keycol) {
        this.branch_words_keycol = branch_words_keycol;
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

    public String getF_create() {
        return f_create;
    }

    public void setF_create(String f_create) {
        this.f_create = f_create;
    }

    public String getF_update() {
        return f_update;
    }

    public void setF_update(String f_update) {
        this.f_update = f_update;
    }  
   
}