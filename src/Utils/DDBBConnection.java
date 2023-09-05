package Utils;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;

public class DDBBConnection {
    
    String DB = "ies9021_database";
    String URL = "jdbc:mysql://ies9021.edu.ar:3306/" + DB + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    String User = "ies9021_userdb";
    String Password = "Xsw23edc.127";
    String Driver = "com.mysql.cj.jdbc.Driver"; 

    Connection Conection;

    public DDBBConnection() {}

    public Connection Conectar(){
        try {
            Class.forName(Driver);
            Conection = DriverManager.getConnection(URL, User, Password);
            return Conection;
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Error en: " + this.toString() + "\nError: " + ex.getMessage());
            System.out.println("No se conectó a BD " + DB);
        }
        return null;
    }

    public void Disconect(){
        try {
            Conection.close();
        } catch (SQLException ex) {
            System.out.println("Conexion terminada");
        }
    }
    
    public ResultSetIES9021 SendQuery(String Query){
        ResultSetIES9021 RSIES9021 = null;
        Conection = Conectar();
        PreparedStatement statement = null;
        try {
            statement = Conection.prepareStatement(Query);
            RSIES9021.RS = statement.executeQuery();
            RSIES9021.State = true;
        } catch (Exception e) {
            e.printStackTrace();
            RSIES9021.RS = null;
            RSIES9021.State = false;
            RSIES9021.Clarification = e.getMessage();
        } finally{
            try {
                if(statement!= null){
                    statement.close();
                }
                if(Conection!= null){
                    Conection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return RSIES9021;
    }
    
    public ResultSet SendAndRecibe(String Query){
        try{
            Conectar();
            Statement statement = Conection.createStatement();
            ResultSet result=statement.executeQuery(Query);
            return result;
        }catch(SQLException e){
            System.out.println("Error en: SendAndRecibe \nerror: "+e.getMessage()+"\n"+e.getSQLState());
        }
        return null;
    }
    
    public void logConnection(String title, String description) {
        String insertQuery = "INSERT INTO logs (title, description, id_user, date) VALUES (?, ?, ?, ?)";

        try (Connection connection = Conectar();
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

            preparedStatement.setString(1, title);
            preparedStatement.setString(2, description);
            preparedStatement.setInt(3, 6);
            preparedStatement.setString(4, LocalDate.now().toString());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public ResultSet executeQuery(String query) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            connection = Conectar();
            statement = connection.prepareStatement(query);
            result = statement.executeQuery();
            logConnection("Conexión exitosa", query);

            return result;
        } catch (SQLException e) {
            logConnection("Conexión fallida", query);
            e.printStackTrace();
            closeResources(connection, statement, result);
        }

        return null;
    }
    
    private boolean QueryVerification(String Query){
        String VQuery = Query.toLowerCase().substring(0, 6);
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

    public void closeResources(Connection connection, PreparedStatement statement, ResultSet result) {
        try {
            if (result != null) {
                result.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
