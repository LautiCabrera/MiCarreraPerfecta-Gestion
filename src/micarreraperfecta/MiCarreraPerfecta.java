package micarreraperfecta;

import Models.Career;
import Utils.DDBBConnection;
import Utils.JavaFileGenerator;
import Utils.JsonDataFetcher;
import Utils.ResultSetIES9021;

public class MiCarreraPerfecta {

    public static void main(String[] args) {
        
    }

    public void ejemplo(){
        ResultSetIES9021<Career> carreras = new JsonDataFetcher<Career>().fetchTableData(
            "career", "id_career < 10",Career.class);

        System.out.println(carreras.getState());
        System.out.println(carreras.getClarification());
        System.out.println("-------------------------");

        for (Career carrer : carreras.getDatos()) {
            System.out.print("Id: " + carrer.getIdCareer());
            System.out.print(" Nombre: " + carrer.getName());
            System.out.print(" Descipción: " + carrer.getDescription());
            System.out.print(" Range: " + carrer.getIdRange());
            System.out.println("");
            System.out.println("-------------------------");
        }

        ResultSetIES9021 query = new ResultSetIES9021<>();
        query = DDBBConnection.SendQuery("UPDATE career SET id_career = 1 WHERE id_career = 1;");

        System.out.println("State: " + query.getState());
        System.out.println("Clarification: " + query.getClarification());
        System.out.println("-------------------------");
    }
}
