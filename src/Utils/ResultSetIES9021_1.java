package Utils;

import java.util.ArrayList;
import java.util.List;

public class ResultSetIES9021<T> {
    private List<T> datos = new ArrayList<T>();
    private boolean State = false;
    private String Clarification = null;

    public ResultSetIES9021() {

    }

    public void setState(boolean state) {
        this.State = state;
    }

    public void setClarification(String clarification) {
        this.Clarification = clarification;
    }

    public void addObject(T objectInstance) {
        this.datos.add(objectInstance);
    }

    public List<T> getDatos() {
        return this.datos;
    }

    public boolean getState() {
        return this.State;
    }

    public String getClarification() {
        return this.Clarification;
    }
}
