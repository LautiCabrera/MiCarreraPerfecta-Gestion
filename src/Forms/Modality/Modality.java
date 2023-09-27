/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Forms.Modality;

import java.util.Date;

/**
 *
 * @author Agustin Coria
 */
public class Modality {

    private int id_modality;
    private String modality;
    private int virtual;
    private int id_user_create;
    private int id_user_update;
    private Date fcreate;
    private Date fupdate;
    
    //Constructor por defecto
    public Modality() {
    }

    //Constructor 
    public Modality(int id_modality, String modality, int virtual, int id_user_create, int id_user_update, Date fcreate, Date fupdate) {
        this.id_modality = id_modality;
        this.modality = modality;
        this.virtual = virtual;
        this.id_user_create = id_user_create;
        this.id_user_update = id_user_update;
        this.fcreate = fcreate;
        this.fupdate = fupdate;
    }

    //Getters and Setters
    public int getId_modality() {
        return id_modality;
    }

    public void setId_modality(int id_modality) {
        this.id_modality = id_modality;
    }

    public String getmodality() {
        return modality;
    }

    public void setmodality(String modality) {
        this.modality = modality;
    }

    public int getvirtual() {
        return virtual;
    }

    public void setvirtual(int virtual) {
        this.virtual = virtual;
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
    
    public Date getfcreate() {
        return fcreate;
    }
    
    public void setfcreate (Date fcreate) {
        this.fcreate = fcreate;
    }

    public Date getfupdate() {
        return fupdate;
    }

    public void setfupdate(Date fupdate) {
        this.fupdate = fupdate;
    }
}
