package Models;

import static Utils.DDBBConnection.SendQuery;

public class Career_Word_Key {

    private int id_Career_word_key;
    private int id_Career;
    private int id_Word_key;
    private int id_User_Update;
    private int id_User_Create;
    private String f_Create;
    private String f_Update;

    //Constructores

    public Career_Word_Key() {}
    
    public Career_Word_Key(Object[] Datos) {
        this.id_Career_word_key = Integer.parseInt(Datos[0].toString());
        this.id_Career = Integer.parseInt(Datos[1].toString());
        this.id_Word_key = Integer.parseInt(Datos[2].toString());
        this.id_User_Update = Integer.parseInt(Datos[3].toString());
        this.id_User_Create = Integer.parseInt(Datos[4].toString());
        this.f_Create = Datos[5].toString();
        this.f_Update = Datos[6].toString();
    }

    public Career_Word_Key(int id_Career, int id_Word_key, int idUser) {
        this.id_Career = id_Career;
        this.id_Word_key = id_Word_key;
        this.id_User_Create =idUser;
        this.id_User_Update=idUser;
        f_Create="current_time()";
        f_Update="current_time()";
    }
    
    public void Modify_Career_Word_key(int id_Career, int id_Word_key, int idUser) {
        this.id_Career = id_Career;
        this.id_Word_key = id_Word_key;
        this.id_User_Update=idUser;
        f_Update="current_time()";
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

    public void create(){
        String query="INSERT INTO `ies9021_database`.`career_word_key` "
                + "(`id_career`, `id_word_key`, `id_user_create`, `id_user_update`, `f_create`, `f_update`) VALUES"
                + " ('"+id_Career+"', '"+id_Word_key+"', '"+id_User_Create+"', '"+id_User_Update+"', "+f_Create+", "+f_Update+");";
        SendQuery(query);
    }
    
     public void update(){
         String query="UPDATE `ies9021_database`.`career_word_key` SET `id_career` = '"+id_Career+"',"
                 + " `id_word_key` = '"+id_Word_key+"', `id_user_update` = '"+id_User_Update+"',  `f_update` = '"+f_Update+"' WHERE (`id_career_word_key` = '"+id_Career_word_key+"');";
         SendQuery(query);
     }



}
