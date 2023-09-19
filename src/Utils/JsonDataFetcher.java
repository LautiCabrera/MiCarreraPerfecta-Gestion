package Utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class JsonDataFetcher<T> {

    /**
     * Devuelve una instancia de la clase especificada. La instancia devuelta es la primera de la tabla correspondiente a la clase que cumple con la condición de la cláusula WHERE.
     *
     * @param tableName   El nombre de la tabla en la cual se encuentran los datos del objeto.
     * @param whereClause Condición de búsqueda en la tabla. La palabra "WHERE" está implícita, solo se debe poner el resto de la consulta.
     * @param returnType  Es la clase a la cual se le van a asignar los datos traídos de la tabla. La lista se creará con objetos de esta clase dentro.
     */
    
    public static <T> T fetchTableData(String tableName, String whereClause, Class<T> returnType) {
        String query = "SELECT * FROM " + tableName;
        if (whereClause != null && !whereClause.isEmpty()) {
            query += " WHERE " + whereClause;
        }
        ResultSet resultSet = null;

        try {
            resultSet = DDBBConnection.SendQuery(query).RS;

            if (resultSet != null && resultSet.next()) {
                ObjectMapper mapper = new ObjectMapper();
                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();
                ObjectNode rowNode = mapper.createObjectNode();

                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    Object columnValue = resultSet.getObject(i);
                    rowNode.put(columnName, columnValue.toString());
                }

                String jsonResult = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(rowNode);

                return mapper.readValue(jsonResult, returnType);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Devuelve una instancia de la clase especificada. La instancia devuelta es la primera de la tabla correspondiente a la clase.
     *
     * @param tableName  El nombre de la tabla en la cual se encuentran los datos del objeto.
     * @param returnType Clase a la cual se le van a asignar los datos traídos de la tabla. La lista se creará con objetos de esta clase dentro.
     */
    public static <T> T fetchTableData(String tableName, Class<T> returnType) {
        String query = "SELECT * FROM " + tableName;

        ResultSet resultSet = null;

        try {
            resultSet = DDBBConnection.SendQuery(query).RS;

            if (resultSet != null && resultSet.next()) {
                ObjectMapper mapper = new ObjectMapper();
                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();
                ObjectNode rowNode = mapper.createObjectNode();

                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    Object columnValue = resultSet.getObject(i);
                    rowNode.put(columnName, columnValue.toString());
                }

                String jsonResult = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(rowNode);

                return mapper.readValue(jsonResult, returnType);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * La función obtiene datos de una tabla singleton en una base de datos basándose en un nombre de
     * tabla determinado y una cláusula WHERE, y devuelve el resultado como un objeto del tipo de
     * retorno especificado.
     * 
     * @param tableName El nombre de la tabla de la que desea obtener los datos.
     * @param whereClause El parámetro WhereClause es una cadena que representa la condición que se
     * utilizará en la cláusula WHERE de la consulta SQL. Se utiliza para filtrar las filas devueltas
     * por la tabla.
     * @param returnType El parámetro `returnType` es el tipo de clase del objeto que desea devolver
     * desde el método. Se utiliza para determinar el tipo de objeto que devolverá el método.
     * @return El método devuelve un objeto de tipo T, que se especifica mediante el parámetro
     * returnType.
     */
    public static <T> T fetchSingletonTableData(String tableName, String whereClause, Class<T> returnType) {
        String query = "SELECT * FROM " + tableName;
        if (whereClause != null && !whereClause.isEmpty()) {
            query += " WHERE " + whereClause;
        }

        ResultSet resultSet = null;

        try {
            resultSet = DDBBConnection.SendQuery(query).RS;

            if (resultSet != null && resultSet.next()) {
                ObjectMapper mapper = new ObjectMapper();
                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();
                ObjectNode rowNode = mapper.createObjectNode();

                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    Object columnValue = resultSet.getObject(i);
                    rowNode.put(columnName, columnValue.toString());
                }

                String jsonResult = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(rowNode);

                return (T) mapper.readValue(jsonResult, returnType.getMethod("getInstance").getReturnType());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}