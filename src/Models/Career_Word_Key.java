package Models;

import static Utils.DDBBConnection.SendQuery;
import static Utils.JsonDataFetcher.selectQuery;
import Utils.ResultSetIES9021;
import java.util.ArrayList;
import java.util.Arrays;

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
    
    public void create(String id_career,String id_word_key,String id_user_create){
        String query="INSERT INTO `ies9021_database`.`career_word_key` "
                + "(`id_career`, `id_word_key`, `id_user_create`, `id_user_update`, `f_create`, `f_update`) VALUES"
                + " ('"+id_career+"', '"+id_word_key+"', '"+id_user_create+"', '"+id_user_create+"', current_time(), current_time());";
        SendQuery(query);
    }
    
    public ResultSetIES9021 Delete(String ID){
        ResultSetIES9021 RSI= new ResultSetIES9021();
        try {
            String Lista=revision_CWK(ID),Query;
            if(!(Lista.isEmpty()||Lista.isBlank())){
                Query="DELETE FROM ies9021_database.career_word_key WHERE (`id_career_word_key` IN ("+Lista+"));";
                return SendQuery(Query);
            }
            RSI.setState(false);
            RSI.setClarification("No se pudo comprobar la base de datos");
            return RSI;
        } catch (Exception e) {
            e.printStackTrace();
        }
        RSI.setState(false);
        RSI.setClarification("No se pudo comprobar la base de datos");
        return RSI;
    }
    
    private String revision_CWK(String ID){
        String SS =" id_career_word_key "
              ,TT =" ies9021_database.career_word_key "
              ,WW =" id_career = "+ID+"; "
              ,List="";
        try {
            ArrayList<String[]> S=ObtenerDatos(SS, TT, WW);
        for (String[] strings : S) {
            List+=Arrays.toString(strings);
        }
        List=List.substring(0, List.length()-1).replace("[", "").replace("]", ",");
        System.out.println("Revision_CWK "+List);
        revision_CBWK(List);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return List;
    }
    
    private void revision_CBWK(String Lista){
        String SS=" id_career_branch_work_key "
              ,TT=" ies9021_database.career_branch_word_key "
              ,WW=" id_career IN ( "+Lista+" )";
        ArrayList<String[]> Dev;
        try {
            Dev=ObtenerDatos(SS, TT, WW);
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
    
    private ArrayList<String[]> ObtenerDatos(String Select,String Table, String Where){
        return new ArrayList<>(selectQuery(Select, Table, Where));
    }

}
