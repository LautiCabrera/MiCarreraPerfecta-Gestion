/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author BTF
 */
public class DatosDeInicio {
    
    private int id_user;
    private String name;
    private int correcttoken;
    private int tokenvalid;

    public DatosDeInicio() {
    }

    public DatosDeInicio(int id_user, String name, int correcttoken, int tokenvalid) {
        this.id_user = id_user;
        this.name = name;
        this.correcttoken = correcttoken;
        this.tokenvalid = tokenvalid;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCorrecttoken() {
        return correcttoken;
    }

    public void setCorrecttoken(int correcttoken) {
        this.correcttoken = correcttoken;
    }

    public int getTokenvalid() {
        return tokenvalid;
    }

    public void setTokenvalid(int tokenvalid) {
        this.tokenvalid = tokenvalid;
    }
    
}
