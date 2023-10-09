package Utils;

import java.sql.*;
import java.time.LocalDate;

public abstract class DDBBConnection {

    private static String DB = "ies9021_database";
    private static String URL = "jdbc:mysql://ies9021.edu.ar:3306/" + DB
            + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&zeroDateTimeBehavior=round";
    private static String User = "ies9021_userdb";
    private static String Password = "Xsw23edc.127";
    private static String Driver = "com.mysql.cj.jdbc.Driver";

    private static Connection Conection;

    private DDBBConnection() {
    }

    protected static Connection Conectar() {
        try {
            Class.forName(Driver);
            Conection = DriverManager.getConnection(URL, User, Password);
            return Conection;
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Error en: DDBBConnection \nError: " + ex.getMessage());
            System.out.println("No se conectó a BD " + DB);
        }
        return null;
    }

    protected static void Disconect() {
        try {
            Conection.close();
        } catch (SQLException ex) {
            System.out.println("Conexion terminada");
        }
    }

    public static ResultSetIES9021 SendQuery(String query) {

        ResultSetIES9021 RSIES9021 = new ResultSetIES9021();
        if (/* QueryVerification(query) */ true) {
            Conection = Conectar();
            PreparedStatement statement = null;
            boolean result = false;

            try {
                Conection = Conectar();
                statement = Conection.prepareStatement(query);
                result = statement.execute();
                RSIES9021.setState(true);
                RSIES9021.setClarification("Consulta ejecutada satisfactoriamente");
                logConnection("Conexión exitosa", query);
                Disconect();

                
            } catch (SQLException e) {
                logConnection("Conexión fallida", query);
                e.printStackTrace();
                RSIES9021.setState(false);
                RSIES9021.setClarification(e.getMessage());
            }

        }
        return RSIES9021;
    }

    private static void logConnection(String title, String description) {
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

    public static ResultSet fetchData(String query) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            connection = Conectar();
            statement = connection.prepareStatement(query);
            result = statement.executeQuery();
            logConnection("Conexión exitosa", query);
            Disconect();

            return result;
        } catch (SQLException e) {
            logConnection("Conexión fallida", query);
            e.printStackTrace();
        }

        return null;
    }

    private static boolean QueryVerification(String Query) {
        String testQuery = Query.toLowerCase().substring(0, 6);
        boolean verification = false;
        if (testQuery.equals("insert") || testQuery.equals("select") ||
                testQuery.equals("update") || testQuery.equals("delete")) {
            switch (Query.toLowerCase().charAt(0)) {
                case 's':
                    verification = true;
                    break;
                case 'i':
                    break;
                case 'u':
                    verification = testQuery.matches("UPDATE\\s+\\w+\\s+SET\\s+\\w+\\s*=\\s*[^;]+WHERE\\s+[^;]+;\r\n");
                    break;
                case 'd':
                    verification = testQuery.matches("DELETE\\s+FROM\\s'+\\w+\\s+WHERE\\s+[^;]+;");
                    break;
            }
        }

        if (verification) {
            logConnection("Valid Connection attempt", testQuery);
        } else {
            logConnection("Invalid Connection attempt", testQuery);
        }

        return verification;
    }

    public static void closeResources(Connection connection, PreparedStatement statement, ResultSet result) {
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
