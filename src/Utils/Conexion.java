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
public class Conexion {
    String DB="ies9021_database",URL="jdbc:mysql://ies9021.edu.ar:3306/",User="ies9021_userdb",Password="Xsw23edc.127"
            ,Driver="com.mysql.jdbc.Driver";//IF ERROR ERASE .cj
    Connection conex;
    
    public Conexion(){}
    
    public Connection Conectar(){
        try{
            Class.forName(Driver);
            conex=DriverManager.getConnection(URL+DB,User,Password);
            System.out.println("Se conexto a BD"+DB);
        }catch(ClassNotFoundException |SQLException ex){
            System.out.println("No se conexto a BD"+DB);
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE,null,ex);
        }
        return conex;
    }
    
    public void Disconect(){
        try {
            conex.close();
        } catch (SQLException ex) {
            System.out.println("Conexion terminada");
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    
    public ResultSet SendAndRecibe(String Query){
        try{
            Conectar();
            Statement statement = conex.createStatement();
            ResultSet result=statement.executeQuery(Query);
            return result;
        }catch(SQLException e){
            System.out.println("Error en: SendAndRecibe \nerror: "+e.getMessage()+"\n"+e.getSQLState());
        }
        return null;
    }
    
    
}
