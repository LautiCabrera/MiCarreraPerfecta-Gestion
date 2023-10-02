package Models;
<<<<<<<
import com.fasterxml.jackson.annotation.JsonProperty;
import java.sql.Timestamp;
import java.sql.Date;
import java.util.Date;

public class University {
	
	private final static String TABLENAME = "University";
	
	public static String getTABLENAME() {return TABLENAME;}	
}


public class University {
    
    @JsonProperty("id_university")
    private int id_university;
    @JsonProperty("name")
    private String name;
    @JsonProperty("id_management")
    private int id_management;
    private int id_user_create;
    private int id_user_update;
    @JsonProperty("f_create")
    private Date f_create;
    @JsonProperty("f_update")
    private Date f_update;

    public University() {
        
    }

    public University(int id_university, String name, int id_management, int id_user_create, int id_user_update, Date f_create, Date f_update) {
        this.id_university = id_university;
        this.name = name;
        this.id_management = id_management;
        this.id_user_create = id_user_create;
        this.id_user_update = id_user_update;
        this.f_create = f_create;
        this.f_update = f_update;
    }
                 
    public int getId() {
        return id_university;
    }
    
    public void setId(int id) {
        this.id_university = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public int getId_university() {
        return id_university;
    }

    public void setId_university(int id_university) {
        this.id_university = id_university;
    }

    public int getId_managment() {
        return id_management;
    }

    public void setId_managment(int id_managment) {
        this.id_management = id_managment;
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
        return f_create;
    }

    public void setF_create(Date f_create) {
        this.f_create = f_create;
    }

    public Date getF_update() {
        return f_update;
    }

    public void setF_update(Date f_update) {
        this.f_update = f_update;
    }
    
}
>>>>>>>
