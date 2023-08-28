/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author BTF
 */
public class Career_Word_Key {
    

    private int id_Career_word_key;
    private int id_Career;
    private int id_Word_key;
    private int id_User_Update;
    private int id_User_Create;
    private String f_Create;
    private String f_Update;


    //Constructores

    public Career_Word_Key() {
    }

    public Career_Word_Key(int id_Career_word_key, int id_Career, int id_Word_key,
                           int id_User_Update, int id_User_Create, String f_Create,
                           String f_Update) {
        this.id_Career_word_key = id_Career_word_key;
        this.id_Career = id_Career;
        this.id_Word_key = id_Word_key;
        this.id_User_Update = id_User_Update;
        this.id_User_Create = id_User_Create;
        this.f_Create = f_Create;
        this.f_Update = f_Update;
    }

    public Career_Word_Key(int id_Career, int id_Word_key, int id_User_Update, int id_User_Create, String f_Create, String f_Update) {
        this.id_Career = id_Career;
        this.id_Word_key = id_Word_key;
        this.id_User_Update = id_User_Update;
        this.id_User_Create = id_User_Create;
        this.f_Create = f_Create;
        this.f_Update = f_Update;
    }

    public Career_Word_Key(int id_Career, int id_Word_key, int idUser) {
        this.id_Career = id_Career;
        this.id_Word_key = id_Word_key;
        this.id_User_Create =idUser;
        this.id_User_Update=idUser;
    }

    //Get and Set

    public int getId_Career_word_key() {
        return id_Career_word_key;
    }

    public int getId_Career() {
        return id_Career;
    }

    public void setId_Career(int id_Career) {
        this.id_Career = id_Career;
    }

    public int getId_Word_key() {
        return id_Word_key;
    }

    public void setId_Word_key(int id_Word_key) {
        this.id_Word_key = id_Word_key;
    }

    public int getId_User_Update() {
        return id_User_Update;
    }

    public void setId_User_Update(int id_User_Update) {
        this.id_User_Update = id_User_Update;
    }

    public int getId_User_Create() {
        return id_User_Create;
    }

    public void setId_User_Create(int id_User_Create) {
        this.id_User_Create = id_User_Create;
    }

    public String getF_Create() {
        return f_Create;
    }

    public void setF_Create(String f_Create) {
        this.f_Create = f_Create;
    }

    public String getF_Update() {
        return f_Update;
    }

    public void setF_Update(String f_Update) {
        this.f_Update = f_Update;
    }

    //Metodos





}
