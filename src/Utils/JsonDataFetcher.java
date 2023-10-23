package Utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonDataFetcher<T> {

    /**
     * Devuelve una instancia de la clase especificada. La instancia devuelta es la
     * primera de la tabla correspondiente a la clase que cumple con la condición de
     * la cláusula WHERE.
     *
     * @param tableName   El nombre de la tabla en la cual se encuentran los datos
     *                    del objeto.
     * @param whereClause Condición de búsqueda en la tabla. La palabra "WHERE" está
     *                    implícita, solo se debe poner el resto de la consulta.
     * @param returnType  Es la clase a la cual se le van a asignar los datos
     *                    traídos de la tabla. La lista se creará con objetos de
     *                    esta clase dentro.
     */

    public ResultSetIES9021<T> fetchTableData(String tableName, String whereClause, Class<T> returnType) {
        String query = "SELECT * FROM " + tableName;
        if (whereClause != null && !whereClause.isEmpty()) {
            query += " WHERE " + whereClause;
        }
        ResultSet resultSet = null;

        ResultSetIES9021<T> result = new ResultSetIES9021<>();

        try {
            resultSet = DDBBConnection.fetchData(query);

            ObjectMapper mapper = new ObjectMapper();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            ObjectNode rowNode = mapper.createObjectNode();
            String jsonResult = null;

            while (resultSet != null && resultSet.next()) {

                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    Object columnValue = resultSet.getObject(i);
                    rowNode.put(columnName, columnValue.toString());
                }

                jsonResult = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(rowNode);
                result.addObject(mapper.readValue(jsonResult, returnType));

            }
            result.setState(true);
            result.setClarification("Consulta resuelta satisfactoriamente");
            return result;

        } catch (Exception e) {
            e.printStackTrace();
            result.setClarification(e.getMessage());
        } finally {
            DDBBConnection.closeResources(null, null, resultSet);
        }

        return result;
    }

    /**
     * Devuelve una instancia de la clase especificada. La instancia devuelta es la
     * primera de la tabla correspondiente a la clase.
     *
     * @param tableName  El nombre de la tabla en la cual se encuentran los datos
     *                   del objeto.
     * @param returnType Clase a la cual se le van a asignar los datos traídos de la
     *                   tabla. La lista se creará con objetos de esta clase dentro.
     */
    public ResultSetIES9021<T> fetchTableData(String tableName, Class<T> returnType) {
        String query = "SELECT * FROM " + tableName;

        ResultSet resultSet = null;

        ResultSetIES9021<T> result = new ResultSetIES9021<>();

        try {
            resultSet = DDBBConnection.fetchData(query);

            ObjectMapper mapper = new ObjectMapper();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            ObjectNode rowNode = mapper.createObjectNode();
            String jsonResult = null;

            while (resultSet != null && resultSet.next()) {

                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    Object columnValue = resultSet.getObject(i);
                    rowNode.put(columnName, columnValue.toString());
                }

                jsonResult = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(rowNode);
                result.addObject(mapper.readValue(jsonResult, returnType));

            }
            result.setState(true);
            result.setClarification("Consulta resuelta satisfactoriamente");

        } catch (Exception e) {
            e.printStackTrace();
            result.setState(false);
            result.setClarification(e.getMessage());
        }

        return result;
    }

    /**
     * La función obtiene datos de una tabla singleton en una base de datos
     * basándose en un nombre de
     * tabla determinado y una cláusula WHERE, y devuelve el resultado como un
     * objeto del tipo de
     * retorno especificado.
     * 
     * @param tableName   El nombre de la tabla de la que desea obtener los datos.
     * @param whereClause El parámetro WhereClause es una cadena que representa la
     *                    condición que se
     *                    utilizará en la cláusula WHERE de la consulta SQL. Se
     *                    utiliza para filtrar las filas devueltas
     *                    por la tabla.
     * @param returnType  El parámetro `returnType` es el tipo de clase del objeto
     *                    que desea devolver
     *                    desde el método. Se utiliza para determinar el tipo de
     *                    objeto que devolverá el método.
     * @return El método devuelve un objeto de tipo T, que se especifica mediante el
     *         parámetro
     *         returnType.
     */
    public ResultSetIES9021<T> fetchSingletonTableData(String tableName, String whereClause, Class<T> returnType) {
        String query = "SELECT * FROM " + tableName;
        if (whereClause != null && !whereClause.isEmpty()) {
            query += " WHERE " + whereClause;
        }

        ResultSet resultSet = null;

        ResultSetIES9021<T> result = new ResultSetIES9021<>();

        try {
            resultSet = DDBBConnection.fetchData(query);

            ObjectMapper mapper = new ObjectMapper();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            ObjectNode rowNode = mapper.createObjectNode();
            String jsonResult = null;

            while (resultSet != null && resultSet.next()) {

                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    Object columnValue = resultSet.getObject(i);
                    rowNode.put(columnName, columnValue.toString());
                }

                jsonResult = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(rowNode);
                result.addObject(mapper.readValue(jsonResult, returnType));

            }
            result.setState(true);
            result.setClarification("Consulta resuelta satisfactoriamente");
            result.addObject((T) mapper.readValue(jsonResult, returnType.getMethod("getInstance").getReturnType()));

        } catch (Exception e) {
            e.printStackTrace();
            result.setState(false);
            result.setClarification(e.getMessage());
        }

        return result;
    }

    
    /**
     * La función `selectQuery` toma los parámetros de selección, el nombre de la tabla y una cláusula
     * WHERE, y devuelve una lista de matrices de cadenas que representan los datos seleccionados de la
     * base de datos.
     * 
     * @param selectParams Una cadena que representa las columnas que se seleccionarán en la consulta.
     * Por ejemplo, "columna1, columna2, columna3".
     * @param tableName El parámetro tableName es el nombre de la tabla de la que desea seleccionar
     * datos.
     * @param whereClause El parámetro `whereClause` es una cadena que representa la condición que se
     * aplicará en la cláusula WHERE de la consulta SQL. Se utiliza para filtrar las filas devueltas
     * por la consulta según criterios específicos. Por ejemplo, si desea seleccionar sólo las filas
     * donde la columna "nombre" es igual a "John
     * @return El método devuelve una lista de matrices de cadenas. Cada matriz de cadenas representa
     * una fila de datos recuperados de la base de datos.
     */
    public static List<String[]> selectQuery(String selectParams, String tableName, String whereClause) {
        String query = "SELECT " + selectParams + " FROM " + tableName;
        if (whereClause != null && !whereClause.isEmpty()) {
            query += " WHERE " + whereClause;
        }
    
        List<String[]> dataList = new ArrayList<>();
        ResultSet resultSet = DDBBConnection.fetchData(query);
    
        try {
            ResultSetMetaData metaData = resultSet.getMetaData();
    
            while (resultSet.next()) {
                String[] rowData = new String[metaData.getColumnCount()];
    
                for (int i = 1; i <= metaData.getColumnCount(); i++) {
                    String columnValue = resultSet.getString(i);
                    rowData[i - 1] = columnValue;
                }
    
                dataList.add(rowData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return dataList;
    }
    
    
}