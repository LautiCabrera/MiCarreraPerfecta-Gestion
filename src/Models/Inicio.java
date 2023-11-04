
/*
package Models;

import Utils.DDBBConnection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;

public class Inicio {
    
    public void Inicio(String Email) {
        System.out.println("Email: " + Email);
        try {
            DDBBConnection Conect = new DDBBConnection();
            Conect.Conectar();
            ResultSet result = Conect.SendAndRecibe("select * from `ies9021_database`.users where email='" + Email.toLowerCase() + "';");
            ArrayList<String[]> Data= new ArrayList();
            
            while(result.next()){
                String[] fila=new String[3];
                fila[1] = result.getString("email");
                fila[2] = result.getString("password");
                if(fila[1] == null){fila[1] = "";}
                if(fila[2] == null){fila[2] = "";}
                Data.add(fila);
            }
            ResultSetMetaData resultdata = result.getMetaData();
            int cantidadDColumnas= resultdata.getColumnCount();
            for(int i = 1; i <= cantidadDColumnas; i++){
                System.out.println(resultdata.getColumnName(i)+" ");
            }
            int count=0;
            for(String[] fila:Data){
                System.out.println(fila[2]+" ("+count);
                if(fila[1].equals(Email)){
                    System.out.println("Found");
                }
                count++;
            }
        } catch (Exception e) {
            System.out.println("Fail detected\n");
            System.out.println(e.getMessage());
        }
}
    
}
*/