package Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.sql.Timestamp;
import java.sql.Date;

public class Branch {
    
    //Declaro los atributos de la clase
    @JsonProperty("id_branch")
    private int id_branch;
    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;
    @JsonProperty("id_user_create")
    private int id_user_create;
    @JsonProperty("id_user_update")
    private int id_user_update;
    @JsonProperty("f_create")
    private java.util.Date f_create;
    @JsonProperty("f_update")
    private java.util.Date f_update;

    public Branch() {
    }

    public Branch(int id_branch, String name, String description, int id_user_create, int id_user_update, java.util.Date f_create, java.util.Date f_update) {
        this.id_branch = id_branch;
        this.name = name;
        this.description = description;
        this.id_user_create = id_user_create;
        this.id_user_update = id_user_update;
        this.f_create = f_create;
        this.f_update = f_update;
    }

    public int getId_branch() {
        return id_branch;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getId_user_create() {
        return id_user_create;
    }

    public int getId_user_update() {
        return id_user_update;
    }

    public java.util.Date getF_create() {
        return f_create;
    }

    public java.util.Date getF_update() {
        return f_update;
    }

    public void setId_branch(int id_branch) {
        this.id_branch = id_branch;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId_user_create(int id_user_create) {
        this.id_user_create = id_user_create;
    }

    public void setId_user_update(int id_user_update) {
        this.id_user_update = id_user_update;
    }

    public void setF_create(java.util.Date f_create) {
        this.f_create = f_create;
    }

    public void setF_update(java.util.Date f_update) {
        this.f_update = f_update;
    }

    
    

}
