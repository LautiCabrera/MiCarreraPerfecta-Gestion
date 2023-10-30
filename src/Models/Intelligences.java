package Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.sql.Timestamp;
import java.sql.Date;

public class Intelligences {
    
    //Declaro los atributos de la clase
    @JsonProperty("id_intelligences")
    private int id_intelligences;
    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;
    @JsonProperty("text_user")
    private String text_user;
    @JsonProperty("id_user_create")
    private int id_user_create;
    @JsonProperty("id_user_update")
    private int id_user_update;
    @JsonProperty("create")
    private java.util.Date create;
    @JsonProperty("update")
    private java.util.Date update;

    public Intelligences() {
    }

    public Intelligences(int id_intelligences, String name, String description, String text_user, int id_user_create, int id_user_update, java.util.Date create, java.util.Date update) {
        this.id_intelligences = id_intelligences;
        this.name = name;
        this.description = description;
        this.text_user = text_user;
        this.id_user_create = id_user_create;
        this.id_user_update = id_user_update;
        this.create = create;
        this.update = update;
    }

    public int getId_intelligences() {
        return id_intelligences;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getText_user() {
        return text_user;
    }

    public int getId_user_create() {
        return id_user_create;
    }

    public int getId_user_update() {
        return id_user_update;
    }

    public java.util.Date getCreate() {
        return create;
    }

    public java.util.Date getUpdate() {
        return update;
    }

    public void setId_intelligences(int id_intelligences) {
        this.id_intelligences = id_intelligences;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setText_user(String text_user) {
        this.text_user = text_user;
    }

    public void setId_user_create(int id_user_create) {
        this.id_user_create = id_user_create;
    }

    public void setId_user_update(int id_user_update) {
        this.id_user_update = id_user_update;
    }

    public void setCreate(java.util.Date create) {
        this.create = create;
    }

    public void setUpdate(java.util.Date update) {
        this.update = update;
    }
    
    
    
}
