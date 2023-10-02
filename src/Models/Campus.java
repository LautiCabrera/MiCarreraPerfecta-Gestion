package Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;

public class Campus {
    
    private int id_campus;
    private int id_university;
    private String name;
    private String location;
    private float latitude;
    private float longitude;
    private int main;
    private String www;
    private String email;
    private int id_user_create;
    private int id_user_update;
    @JsonProperty("f_create")
    private Date f_create;
    @JsonProperty("f_update")
    private Date f_update;
    
    public Campus() {
        
    }

    public Campus(int id_campus, int id_university, String name, String location, float latitude, float longitude, int main, String www, String email, int id_user_create, int id_user_update, Date f_create, Date f_update) {
        this.id_campus = id_campus;
        this.id_university = id_university;
        this.name = name;
        this.location = location;
        this.latitude = latitude;
        this.longitude = longitude;
        this.main = main;
        this.www = www;
        this.email = email;
        this.id_user_create = id_user_create;
        this.id_user_update = id_user_update;
        this.f_create = f_create;
        this.f_update = f_update;
    }

    public int getId_campus() {
        return id_campus;
    }

    public void setId_campus(int id_campus) {
        this.id_campus = id_campus;
    }

    public int getId_university() {
        return id_university;
    }

    public void setId_university(int id_university) {
        this.id_university = id_university;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public int getMain() {
        return main;
    }

    public void setMain(int main) {
        this.main = main;
    }

    public String getWww() {
        return www;
    }

    public void setWww(String www) {
        this.www = www;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Date getFcreate() {
        return f_create;
    }

    public void setFcreate(Date f_create) {
        this.f_create = f_create;
    }

    public Date getFupdate() {
        return f_update;
    }

    public void setFupdate(Date f_update) {
        this.f_update = f_update;
    }
    
}
