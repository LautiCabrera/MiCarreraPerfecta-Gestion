package Models;

import Utils.DDBBConnection;
import Utils.JsonDataFetcher;
import Utils.ResultSetIES9021;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class Branch {
    
    private final static String TABLENAME = "branch";

    public static String getTABLENAME() {
        return TABLENAME;
    }
     //revisar 
    @JsonProperty("id_branch")
    private int idbranch;
    private String name;
    private String description;
    @JsonProperty("id_user_create")
    private String idUserCreate;
    @JsonProperty("id_user_update")
    private String idUserUpdate;
    @JsonProperty("f_create")
    private String fCreate;
    @JsonProperty("f_update")
    private String fUpdate;
    

    public int getIdBranch() {
        return idbranch;
    }
    public void setIdBranch(int idbranch) {
        this.idbranch = idbranch;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
   
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getIdUserCreate() {
        return idUserCreate;
    }
    public void setIdUserCreate(String idUserCreate) {
        this.idUserCreate = idUserCreate;
    }

    public String getIdUserUpdate() {
        return idUserUpdate;
    }
    public void setIdUserUpdate(String idUserUpdate) {
        this.idUserUpdate = idUserUpdate;
    }

    public String getFCreate() {
        return fCreate;
    }
    public void setFCreate(String fCreate) {
        this.fCreate = fCreate;
    }

    public String getFUpdate() {
        return fUpdate;
    }
    public void setFUpdate(String fUpdate) {
        this.fUpdate = fUpdate;
    }
    
    public Branch(){
        
    }
    public Branch(String name, String description, String idUserUpdate){
        this.name = name;
        this.description = description;
        this.idUserUpdate = idUserUpdate;
    }      
    
    // Método - verifica existencia de una rama ()
    private static boolean exist(String nameBranch){
        String name = nameBranch+"%";
        String query = String.format("`name` LIKE '%s';",name);
        try {                        
            ResultSetIES9021<Branch> getBranch = new JsonDataFetcher<Branch>().fetchTableData("branch",query, Branch.class);
            List<Branch> branchsList = getBranch.getDatos();                        
            if(branchsList.isEmpty()){
                return false;
            }            
        }catch (Exception e){
            System.err.println("Error finding branch: " + e.getMessage());            
        }
        return true;
    }
   
    
    public static  ResultSetIES9021 create(Branch branch){
        String name = branch.getName();
        String desc = branch.getDescription();
        String user = branch.getIdUserUpdate();
        // Objeto para manejar las respuestas (mismas que verá el usuario)
        ResultSetIES9021 result = new ResultSetIES9021();       
        
        if(exist(name) == false){
            String query = String.format("INSERT INTO branch (`name`, `description`, id_user_create, id_user_update, f_create, f_update ) VALUES ( '%s', '%s', '%s', '%s', NOW(), NOW());",
                    name, desc,user,user);
            try{
                ResultSetIES9021 createRegister = DDBBConnection.SendQuery(query);
                if(createRegister.getState()){
                    result.setState(true);
                    result.setClarification("New branch created!");
                    return result;
                }else{
                    System.err.println("Could not execute query : " + createRegister.getClarification()); // Muestra el error en la terminal
                    result.setState(false);
                    result.setClarification("Could not create branch");
                    return result;
                }
            }catch (Exception e){
                System.err.println("Error creating branch: " + e.getMessage());
            }
        }
        result.setState(false);
        result.setClarification("The branch already exist");
        return result;
    }
    
 
    public static ResultSetIES9021 update(Branch branchModify, String originalName){
        
        String name = originalName + "%"; 
        String query = String.format("`name` LIKE '%s';",name);
        ResultSetIES9021 result = new ResultSetIES9021();
      
        if(exist(originalName)){
            try{
                //  Obtiene registro original
                ResultSetIES9021<Branch> getBranch = new JsonDataFetcher<Branch>().fetchTableData("branch",query, Branch.class);                
                List<Branch> updateBranch = getBranch.getDatos();
                Branch branch = updateBranch.get(0);
                // Modificacion de datos                
                branch.setName(branchModify.getName());
                branch.setDescription(branchModify.getDescription());
                branch.setIdUserUpdate(branchModify.getIdUserUpdate());
                
                //construccion de consulta
                String queryUpdate = String.format("UPDATE branch SET `name` = '%s' , `description` = '%s' , id_user_update = '%s' , f_update = NOW() WHERE id_branch = %d ",
                        branch.getName() ,branch.getDescription(),branch.getIdUserUpdate(), branch.getIdBranch());        
                ResultSetIES9021 updateRegister = DDBBConnection.SendQuery(queryUpdate);

                if(updateRegister.getState()){
                    result.setState(true);
                    result.setClarification("Updated branch!");
                    return result;
                }else{
                    System.err.println("Could not execute query : " + updateRegister.getClarification()); // Muestra el error en la terminal
                    result.setState(false);
                    result.setClarification("Failed to update branch");
                    return result;
                }
            }catch (Exception e){
                System.err.println("Error updating branch: " + e.getMessage());
            }
        }        
        result.setState(false);
        result.setClarification("Non-existent branch");
        return result ;
    }

    public static ResultSetIES9021 delete(int id_branch, String nameBranch){
        String query = String.format("DELETE FROM branch WHERE id_branch = %d;",id_branch);
        ResultSetIES9021 result = new ResultSetIES9021();
        
        if (exist(nameBranch)){
            try{
                ResultSetIES9021 deleteRegister = DDBBConnection.SendQuery(query);
                if(deleteRegister.getState()){
                    result.setState(true);
                    result.setClarification("Deleted branch!");
                    return result;
                }else{
                    System.err.println("Could not execute query: " + deleteRegister.getClarification()); // Muestra el error en la terminal
                    result.setState(false);
                    result.setClarification("Could not delete branch");
                    return result; 
                }
            }catch (Exception e){
                System.err.println("Error deleting branch: " + e.getMessage());
            }
        }        
        result.setState(false);
        result.setClarification("Non-existent branch");
        return result;
    }    

    // Método - Llena la tabla      
    public static void completeTable(JTable table){     
        DefaultTableModel tableName = (DefaultTableModel) table.getModel();
        tableName.setRowCount(0);                                     
        try{
            // Obtiene todos los registros
            ResultSetIES9021<Branch> branchs = new JsonDataFetcher<Branch>().fetchTableData("branch", Branch.class);
            List<Branch> branchesList = branchs.getDatos();
                       
            for(int i = 0; i < branchesList.size(); i++) {                               
                Branch branch = branchesList.get(i);     
                // Falta optimizar (demora en la cara de datos)
                //String queryWhere = String.format("id_branch = %d ;",branch.getIdBranch());                                            
                //int countWK = DDBBConnection.getCount("branch_words_key", queryWhere);
                //int countCareer= DDBBConnection.getCount("career", queryWhere);
                
                Object[] row = {branch.getIdBranch(), branch.getName(), branch.getDescription(), "0", "0"};
                tableName.addRow(row);
            }            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Data could not be displayed");
        } 
    }        
}
