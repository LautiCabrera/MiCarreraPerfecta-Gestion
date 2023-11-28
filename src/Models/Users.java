package Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;

public class Users {
    @JsonProperty("id_user")
    private int id_user;
    @JsonProperty("name")
    private String name;
    @JsonProperty("email")
    private String email;
    @JsonProperty("password")
    private String password;
    @JsonProperty("Last_token")
    private String Last_token;
    @JsonProperty("f_token")
    private Date f_token;
    @JsonProperty("id_type_user")
    private int id_type_user;
    @JsonProperty("enabled_state")
    private int enabled_state;
    @JsonProperty("id_user_create")
    private int id_user_create;
    @JsonProperty("id_user_update")
    private int id_user_update;
    @JsonProperty("fcreate")
    private Date fcreate;
    @JsonProperty("fupdate")
    private Date fupdate;

    public Users() {
    }

    public Users(int id_user, String name, String email, String password, String Last_token, Date f_token, int id_type_user, int enabled_state, int id_user_create, int id_user_update, Date fcreate, Date fupdate) {
        this.id_user = id_user;
        this.name = name;
        this.email = email;
        this.password = password;
        this.Last_token = Last_token;
        this.f_token = f_token;
        this.id_type_user = id_type_user;
        this.enabled_state = enabled_state;
        this.id_user_create = id_user_create;
        this.id_user_update = id_user_update;
        this.fcreate = fcreate;
        this.fupdate = fupdate;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLast_token() {
        return Last_token;
    }

    public void setLast_token(String Last_token) {
        this.Last_token = Last_token;
    }

    public Date getF_token() {
        return f_token;
    }

    public void setF_token(Date f_token) {
        this.f_token = f_token;
    }

    public int getId_type_use() {
        return id_type_user;
    }

    public void setId_type_use(int id_type_use) {
        this.id_type_user = id_type_use;
    }

    public int getEnabled_state() {
        return enabled_state;
    }

    public void setEnabled_state(int enabled_state) {
        this.enabled_state = enabled_state;
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
        return fcreate;
    }

    public void setF_create(Date fcreate) {
        this.fcreate = fcreate;
    }

    public Date getF_update() {
        return fupdate;
    }

    public void setF_update(Date fupdate) {
        this.fupdate = fupdate;
    }
    
    
    
}