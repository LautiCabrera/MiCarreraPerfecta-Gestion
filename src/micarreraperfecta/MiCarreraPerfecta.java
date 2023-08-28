/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package micarreraperfecta;

import Utils.Conexion;
import java.sql.*;
import java.util.ArrayList;


/**
 *
 * @author BTF
 */
public class MiCarreraPerfecta {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
    public void Inicio(String Email) {
        System.out.println("Email: "+Email);
        try {
            Conexion Conect=new Conexion();
            Conect.Conectar();
            ResultSet result=Conect.SendAndRecibe("select * from `ies9021_database`.users where email='"+Email.toLowerCase()+"';");
            ArrayList<String[]> Data= new ArrayList();
            
            while(result.next()){
                String[] fila=new String[3];
                
                fila[1]=result.getString("email");
                fila[2]=result.getString("password");
                if(fila[1]==null){fila[1]="";}
                if(fila[2]==null){fila[2]="";}
                Data.add(fila);
            }
            ResultSetMetaData resultdata = result.getMetaData();
            int cantidadDColumnas= resultdata.getColumnCount();
            for(int i=1;i<=cantidadDColumnas;i++){
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
