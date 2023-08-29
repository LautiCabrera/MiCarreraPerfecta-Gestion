/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import java.util.logging.Logger;
import java.util.logging.Level;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author BTF
 */
public class DDBBConnection {
    String  DB="ies9021_database",
            URL="jdbc:mysql://ies9021.edu.ar:3306/",
            User="ies9021_userdb",
            Password="Xsw23edc.127",
            Driver="com.mysql.jdbc.Driver";//IF ERROR ERASE .cj
    Connection Conection;

    public DDBBConnection() {
    }
    
    public Connection Conectar(){
        try {
            Class.forName(Driver);
            Conection=DriverManager.getConnection(URL+DB,User,Password);
            return Conection;
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Error en: "+this.toString()+"\nError: "+ex.getMessage());
             System.out.println("No se conexto a BD"+DB);
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE,null,ex);
        }
        return null;
    }
    
    public ResultSetIES9021 SendQuery(String Query){
        ResultSetIES9021 RSIES9021=null;
        Conection=Conectar();
        PreparedStatement statement=null;
        try {
            statement=Conection.prepareStatement(Query);
            RSIES9021.RS=statement.executeQuery();
            RSIES9021.State=true;
        } catch (Exception e) {
            e.printStackTrace();
            RSIES9021.RS=null;
            RSIES9021.State=false;
            RSIES9021.Clarification=e.getMessage();
        } finally{
            try {
                if(statement!=null){
                    statement.close();
                }
                if(Conection!=null){
                    Conection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return RSIES9021;
    }
    
    private boolean QueryVerification(String Query){
        String VQuery=Query.toLowerCase().substring(0, 6);
        if(VQuery.equals("insert")||VQuery.equals("select")||
           VQuery.equals("update")||VQuery.equals("delete")){
            switch(Query.toLowerCase().charAt(0)){
                case 's':
                    break;
                case 'i':
                    break;
                case 'u':
                    break;
                case 'd':
                    break;
            }
        }
        return false;
    }
    
    
    
}

