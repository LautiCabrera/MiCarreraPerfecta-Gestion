package Models;

import static Utils.DDBBConnection.SendQuery;

public class Career_Word_Key {

    private int id_career_word_key;
    private int id_career;
    private int id_word_key;
    private int id_user_update;
    private int id_user_create;
    private String f_create;
    private String f_update;

    //Constructores

    public Career_Word_Key() {}
    
    public Career_Word_Key(Object[] Datos) {
        this.id_career_word_key = Integer.parseInt(Datos[0].toString());
        this.id_career = Integer.parseInt(Datos[1].toString());
        this.id_word_key = Integer.parseInt(Datos[2].toString());
        this.id_user_update = Integer.parseInt(Datos[3].toString());
        this.id_user_create = Integer.parseInt(Datos[4].toString());
        this.f_create = Datos[5].toString();
        this.f_update = Datos[6].toString();
    }

    public Career_Word_Key(int id_Career, int id_Word_key, int idUser) {
        this.id_career = id_Career;
        this.id_word_key = id_Word_key;
        this.id_user_create =idUser;
        this.id_user_update=idUser;
        f_create="current_time()";
        f_update="current_time()";
    }
    
    public void Modify_Career_Word_key(int id_Career, int id_Word_key, int idUser) {
        this.id_career = id_Career;
        this.id_word_key = id_Word_key;
        this.id_user_update=idUser;
        f_update="current_time()";
    }

    //Get and Set

    public int getId_career_word_key() {
        return id_career_word_key;
    }

    public int getId_career() {
        return id_career;
    }

    public void setId_career(int id_career) {
        this.id_career = id_career;
    }

    public int getId_word_key() {
        return id_word_key;
    }

    public void setId_word_key(int id_word_key) {
        this.id_word_key = id_word_key;
    }

    public int getId_user_update() {
        return id_user_update;
    }

    public void setId_user_update(int id_user_update) {
        this.id_user_update = id_user_update;
    }

    public int getId_user_create() {
        return id_user_create;
    }

    public void setId_user_create(int id_user_create) {
        this.id_user_create = id_user_create;
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

    //Metodos

    public void create(){
        String query="INSERT INTO `ies9021_database`.`career_word_key` "
                + "(`id_career`, `id_word_key`, `id_user_create`, `id_user_update`, `f_create`, `f_update`) VALUES"
                + " ('"+id_career+"', '"+id_word_key+"', '"+id_user_create+"', '"+id_user_update+"', "+f_create+", "+f_update+");";
        SendQuery(query);
    }
    
     public void update(){
         String query="UPDATE `ies9021_database`.`career_word_key` SET `id_career` = '"+id_career+"',"
                 + " `id_word_key` = '"+id_word_key+"', `id_user_update` = '"+id_user_update+"',  `f_update` = '"+f_update+"' WHERE (`id_career_word_key` = '"+id_career_word_key+"');";
         SendQuery(query);
     }



}
