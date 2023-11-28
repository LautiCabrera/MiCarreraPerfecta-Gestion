package Forms;

import Utils.DDBBConnection;
import java.sql.*;

public class InicioJFMethods {

    DDBBConnection Conectar = new DDBBConnection();
    
    public InicioJFMethods() {}
    
    public void conect(){
        try {
            Conectar.Conectar();   
        } catch (Exception e) {
            
        }
    }
    
    public boolean Existe(String Dato){
        String Query= "SELECT * FROM `ies9021_database`.users WHERE email='"+Dato.toLowerCase()+"';";
        ResultSet result = Conectar.SendAndRecibe(Query);
        try {
            while(result.next()){if(result.getString("email").equals(Dato)){return true;}}
        } catch (SQLException e) {
            System.out.println("Error en: IJFM\nError:"+e.getMessage());
        }
        
        return false;
    }
     
}
