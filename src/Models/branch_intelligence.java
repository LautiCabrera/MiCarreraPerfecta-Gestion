/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author estudiante
 */
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class branch_intelligence {
    private int id_branch_intelligence;
    private int id_branch;
    private int id_intelligence;
    private int id_user_create;
    private int id_user_update;
    private Date f_create;
    private Date f_update;
    private List<Opcion> opcionesElegidas;
    
    
    // Constructor
    public branch_intelligence(int id_branch_intelligence, int id_branch, int id_intelligence, int id_user_create, int id_user_update, Date f_create, Date f_update) {
        this.id_branch_intelligence = id_branch_intelligence;
        this.id_branch = id_branch;
        this.id_intelligence = id_intelligence;
        this.id_user_create = id_user_create;
        this.id_user_update = id_user_update;
        this.f_create = f_create;
        this.f_update = f_update;
        this.opcionesElegidas = new ArrayList<>();
    }
    public List<Opcion> getOpcionesElegidas() {
        return opcionesElegidas;
    }

    public void agregarOpcionElegida(Opcion opcion) {
        opcionesElegidas.add(opcion);
    }
   ///ESTE METODO ES PARA DEBOLVER LO QUE LE PIDO
    public String toString() {
        return "branch_intelligence [id=" + id_branch_intelligence + ", id_branch=" + id_branch + ", id_intelligence=" + id_intelligence
                + ", opcionesElegidas=" + opcionesElegidas + "]";
    }
    
   // Getter y setter}
    public int getId_branch_intelligence() {
        return id_branch_intelligence;
    }

    
    public void setId_branch_intelligence(int id_branch_intelligence) {
        this.id_branch_intelligence = id_branch_intelligence;
    }

   
    public int getId_branch() {
        return id_branch;
    }

    
    public void setId_branch(int id_branch) {
        this.id_branch = id_branch;
    }

   
    public int getId_intelligence() {
        return id_intelligence;
    }


    public void setId_intelligence(int id_intelligence) {
        this.id_intelligence = id_intelligence;
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
    