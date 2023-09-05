package Models;

import Utils.DDBBConnection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CWK_Methods {

    public void amethod() {

    }
/*
    public void erasebyCareer(int idcareer) {
        MySQLQuery MSQLQ = new MySQLQuery();
        ResultSet result = MSQLQ.CareerWK_career(idcareer);
        if (result != null) {
            ArrayList<Integer> id_cwk = new ArrayList<>();
            try {
                while (result.next()) {
                    id_cwk.add(result.getInt("id_career_word_key"));
                }
                result.close();
                //preguntar si se desea eliminar tal cantidad de ID's
                //Conectarse a CRUD y eliminar las ID's
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        //"select * from `ies9021_database`.`career_word_key` where id_career is"+id_career+";";
        //un arreglo de las filas que va a eliminar

    }
*/
    /*
    public void erasebyWordK(int idwordk) {
        MySQLQuery MSQLQ = new MySQLQuery();
        ResultSet result = MSQLQ.Consultar("idwordk");
        if (result != null) {
            ArrayList<Integer> id_cwk = new ArrayList<>();
            try {
                while (result.next()) {
                    id_cwk.add(result.getInt("id_career_word_key"));
                }
                result.close();
                //preguntar si se desea eliminar tal cantidad de ID's
                //Conectarse a CRUD y eliminar las ID's
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        //"select * from `ies9021_database`.`career_word_key` where id_career is"+id_career+";";
        //un arreglo de las filas que va a eliminar

    }

    */
    /*
    public void InsertCWK(int idcareer, int[] idwordKey, int IDUser) {
        Career_Word_Key[] CWK;
        //que busque si hay una lista con el idCarrer
        int discarted = 0;
        MySQLQuery MSQLQ = new MySQLQuery();
        ResultSet result = MSQLQ.CareerWK_career(idcareer);
        if (result != null) {
            try {

                ArrayList<Career_Word_Key> listdeCWK = new ArrayList<>();

                while (result.next()) {
                    Career_Word_Key career_word_key = new Career_Word_Key();
                    career_word_key.setId_Career(result.getInt("id_Career"));
                    career_word_key.setId_Career(result.getInt("id_word_key"));
                    listdeCWK.add(career_word_key);
                }
                result.close();

                for (int i = 0; i < listdeCWK.size(); i++) {
                    for (int j = 0; j < idwordKey.length; i++) {
                        if (listdeCWK.get(i).getId_Word_key() == idwordKey[j]) {
                            idwordKey[j] = -1;
                            discarted++;
                        }
                    }

                }
                CWK = new Career_Word_Key[(idwordKey.length - discarted)];
                int OverflowLimiter = 0;
                for (int i = 0; i < idwordKey.length; i++) {
                    if (idwordKey[i] != -1) {
                        CWK[i - OverflowLimiter] = new Career_Word_Key(idcareer, idwordKey[i], IDUser);
                    } else {
                        OverflowLimiter++;
                    }
                }

                //Enviar la lista al CRUD y Devolver la cantidad que fueron descartadas
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    */
    /*
    public ArrayList exist(int id_objeto, int modo) {
        ArrayList<Object> Something = new ArrayList<>();
        String Query = "select * from `ies9021_database`.`career_word_key`";
        MySQLQuery MSQLQ = new MySQLQuery();
        ResultSet result = MSQLQ.CareerWK_career(id_objeto);

        switch (0) {
            case 1:
            case 2:
            case 3:
            case 4:
            default:
                break;
        }

        switch (modo) {
            case 0: //Select General, Insert
                break;
            case 1: //by career
                Query += "where id_career=" + id_objeto;
                //
                break;
            case 2:// by wordk
                Query += "where id_word_key=" + id_objeto;
                break;
            case 3: //by user
                Query = "id_user=" + id_objeto;
                break;
            default:
                return null;
        }
        result = MSQLQ.Consultar(Query);
        //ResulSetIES9021 resulSetIES9021 = DBCCConnection.executeQuery(query);
        //ResulSetIES9021 resulSetIES9021 = DBCCConnection.selectQuery(query);
        try {
            while (result.next()) {
                Career_Word_Key career_word_key = new Career_Word_Key(result.getInt(1), result.getInt(2), result.getInt(3), result.getInt(4), result.getString(5), result.getString(6));
                Something.add(career_word_key);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    */
    //Metodos que si sirven
    //Sector de Querys
    private String CreateQuery(String Action, int Ubication, String Values, int Where, int id_object, int id_object2) {
        return CreateQuery(Action, Ubication, Values, Where, id_object, id_object2, "");
    }

    private String CreateQuery(String Action, int Ubication, String Values, int Where, int id_object, String ids_objects) {
        return CreateQuery(Action, Ubication, Values, Where, id_object, -1, ids_objects);
    }

    private String CreateQuery(String Action, int Ubication, String Values, int Where, int id_object) {
        return CreateQuery(Action, Ubication, Values, Where, id_object, -1, "");
    }

    private String CreateQuery(String Action, int Ubication, String Values, int Where, int id_object, int id_object2, String ids_objects) {
        String Query;
        switch (Ubication) {
            case 1 -> Query = " `ies9021_database`.`career_word_key` ";
            case 2 -> Query = " `ies9021_database`.`career_branch_word_key` ";
            default -> {
                return "Error, bad Ubication request";
            }
        }
        //Aciones al principio de todo
        switch (Action.toUpperCase()) {
            case "S":
                Query = "SELECT * FROM" + Query;
                break;
            case "I":
                Query = "INSERT INTO" + Query;
                break;
            case "D":
                Query = "DELETE FROM" + Query;
                break;
            case "U":
                Query = "UPDATE" + Query;
                break;
            default:
                break;
        }
        //values estamos hasta la madre

        if (Action.toUpperCase().equals("I")) {
            //aun no sabes como insertar fecha
            Query = Query + "(`id_career`,`id_word_key`,`id_user_create`,`id_user_update`) VALUES "
                    + Values;
        } else if (Action.toUpperCase().equals("U")) {
            Query = Query + " SET " + Values;
        }
        //Where al final de todo
        if (!Action.toUpperCase().equals("I")) {
            switch (Where) {
                case 1:
                    Query = Query + "WHERE id_career=" + id_object;
                    break;
                case 2:
                    Query = Query + "WHERE id_word_key=" + id_object;
                    break;
                case 3:
                    Query = Query + "WHERE id_career_word_key=" + id_object;
                    break;
                case 4:
                    Query = Query + "WHERE id_career=" + id_object + " AND id_word_key=" + id_object2;
                    break;
                case 5:
                    Query = Query + "WHERE id_career IN (" + ids_objects + ")";
                    break;
                case 6:
                    Query = Query + "WHERE id_word_key IN (" + ids_objects + ")";
                    break;
                case 7:
                    Query = Query + "WHERE id_career_word_key IN (" + ids_objects + ")";
                    break;
                case 8:
                    Query = Query + "WHERE id_career IN (" + id_object + ") AND id_word_key IN (" + id_object2 + ")";
                    break;
                case 0:
                default:
                    break;
            }
        }
        Query = Query + "; ";
        return Query;
    }

    //Sector Insert
    private void InsertCareerWordKey(int id_career, int id_wordk, int user) {
        String Values = "'" + String.valueOf(id_career) + "', '" + String.valueOf(id_wordk)
                + "', '" + String.valueOf(user) + "', '" + String.valueOf(user) + "'";
        String insert = CreateQuery("I", 1, Values, 0, 0);
    }

    //Sector Update
    private void SelectQueryUpdate(int id_career, int[] old_id_wordk, int[] new_id_wordk) {
        String ids = String.valueOf(old_id_wordk[0]);
        for (int i = 1; i < old_id_wordk.length; i++) {
            ids = ids + ", " + old_id_wordk[i];
        }
        String U = CreateQuery("S", 1, "", 8, id_career, ids);
        ResultSet B = executeQuery(U);
        int[][] D = {getCareerWordKey(B, 3), getCareerWordKey(B, 2)};
        for (int[] D1 : D) {
            for (int j = 0; j < old_id_wordk.length; j++) {
                if (D1[1] == old_id_wordk[j]) {
                    D1[1] = new_id_wordk[j];
                }
            }
        }
        String Update = "";
        for (int[] D1 : D) {
            Update = Update + CreateQuery("U", 1, "'id_word_key'" + D1[1], 3, D1[0]);
        }
        executeQuery(Update);
    }

    private void SelectQueryUpdate(int id_career, int old_id_wordk, int new_id_wordk) {
        String Upd = CreateQuery("U", 1, "id_word_key=" + new_id_wordk, 4, id_career, old_id_wordk);
        executeQuery(Upd);
    }

    //Sector Delete
    public void SelectQueryEraseByCareer(int id_career) {
        String A = CreateQuery("S", 1, "", 1, id_career);
        ResultSet B = executeQuery(A);
        int[] D = getCareerWordKey(B, 3);
        A = "";
        for (int i = 0; i < D.length; i++) {
            A = A + CreateQuery("D", 2, "", 3, D[i]);
        }
        executeQuery(CreateQuery("D", 1, "", 1, id_career));
        executeQuery(A);//Elimina Career Branch Word Key

    }

    public void SelectQueryEraseByWordKey(int id_wordK) {
        String A = CreateQuery("S", 1, "", 2, id_wordK);
        ResultSet B = executeQuery(A);
        int[] D = getCareerWordKey(B, 3);
        A = "";
        for (int i = 0; i < D.length; i++) {
            A = A + CreateQuery("D", 2, "", 3, D[i]);
        }
        executeQuery(CreateQuery("D", 1, "", 2, id_wordK));
        executeQuery(A);//Elimina Career Branch Word Key

    }

    public void EraseByCareerBranchWordKey(int id_career_branch_word_key) {
        executeQuery(CreateQuery("D", 1, "", 3, id_career_branch_word_key));
        executeQuery(CreateQuery("D", 2, "", 3, id_career_branch_word_key));
    }

    public void eraseCareerBranchWordKey() {

    }

    //Obtener los id_career_word_key
    private int[] getCareerWordKey(ResultSet restl, int ubication) {
        String object;
        switch (ubication) {
            case 1:
                object = "id_career";
                break;
            case 2:
                object = "id_word_key";
                break;
            case 3:
                object = "id_career_word_key";
                break;
            default:
                object = "";
                break;
        }
        int send[] = new int[0], Counter = 0;
        try {
            while (restl.next()) {
                Counter++;
            }
            send = new int[Counter];
            Counter = 0;
            while (restl.next()) {
                send[Counter] = restl.getInt(object);
                Counter++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return send;
    }

    //Cambiar ResultSet a ResultSetIES9021 para obtener respuesta boolean
    public ResultSet executeQuery(String Querys) {
            
        DDBBConnection conect = new DDBBConnection();
        conect.Conectar();
        ResultSet result=conect.SendAndRecibe(Querys);
        //ResulSetIES9021 resulSetIES9021 = DBCCConnection.executeQuery(Querys);
        /*
        *  if(resulSetIES9021.getState){
        *   if(resulSetIES9021.getresulset!=null){
        *
        *
        *   }
        *
        * }
        *
        *
        *
        * */
        return result;
    }

}
