package Models;

public class University {

    private int id_university;
    private String name;
    private int id_management;
    private int id_user_create;
    private int id_user_update;
    private String f_create;
    private String f_update;

    public University() {
    }

    public University(String name, int id_management, int id_user_create, int id_user_update, String f_create, String f_update) {
        this.name = name;
        this.id_management = id_management;
        this.id_user_create = id_user_create;
        this.id_user_update = id_user_update;
        this.f_create = f_create;
        this.f_update = f_update;
    }

    public int getId_university() {
        return id_university;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId_management() {
        return id_management;
    }

    public void setId_management(int id_management) {
        this.id_management = id_management;
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
    
    public void Create(){}
    
    public void Update(){}
    
    public void Delete(){}
}
