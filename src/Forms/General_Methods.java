package Forms;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import Utils.DDBBConnection;

public class General_Methods {

    public String[] Columns(String Table) {
        DDBBConnection DBC = new DDBBConnection();
        try {
            DatabaseMetaData metaData = DBC.Conectar().getMetaData();
            ResultSet Columns = metaData.getColumns(null, null, Table, null);
            ArrayList<String> ColumnsNames = new ArrayList<>();
            while (Columns.next()) {
                if (!UnWantedColumnsNames(Columns.getString("COLUMN_NAME"))) {
                    ColumnsNames.add(Columns.getString("COLUMN_NAME"));
                }
            }
            Columns.close();
            String[] Colum = new String[ColumnsNames.size()];
            int count = 0;
            for (String CN : ColumnsNames) {
                Colum[count] = CN;
                count++;
            }
            return Colum;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBC.Disconect();
        }
        return null;
    }

    public void PintarTabla(String Table, JTable jTable) {
        DefaultTableModel Tabla = (DefaultTableModel) jTable.getModel();
        DDBBConnection DBC = new DDBBConnection();
        try {
            DatabaseMetaData metaData = DBC.Conectar().getMetaData();
            ResultSet Columns = metaData.getColumns(null, null, Table, null);
            ArrayList<String> ColumnsNames = new ArrayList<>();
            while (Columns.next()) {
                if (!UnWantedColumnsNames(Columns.getString("COLUMN_NAME"))) {
                    ColumnsNames.add(Columns.getString("COLUMN_NAME"));
                }
            }
            Columns.close();
            Tabla.setColumnCount(ColumnsNames.size());
            int count = 0;
            for (String CN : ColumnsNames) {
                JTableHeader tableHeader = jTable.getTableHeader();
                TableColumnModel tableColumnModel = tableHeader.getColumnModel();
                TableColumn tableColumn = tableColumnModel.getColumn(count);
                tableColumn.setHeaderValue(CN);
                tableHeader.repaint();
                count++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBC.Disconect();
        }
    }

    private boolean UnWantedColumnsNames(String Name) {
        String[] unwantedColumnNames = {
            "USER", "CURRENT_CONNECTIONS", "TOTAL_CONNECTIONS",
            "MAX_SESSION_CONTROLLED_MEMORY", "MAX_SESSION_TOTAL_MEMORY"
        };
        for (String unwantedName : unwantedColumnNames) {
            if (unwantedName.equalsIgnoreCase(Name)) {
                return true;
            }
        }
        return false;
    }

    public void PintarComboBox(String Table, JComboBox<String> jCombo) {
        DDBBConnection DBC = new DDBBConnection();
        try {
            DatabaseMetaData metaData = DBC.Conectar().getMetaData();
            ResultSet Columns = metaData.getColumns(null, null, Table, null);
            ArrayList<String> ColumnsNames = new ArrayList<>();
            while (Columns.next()) {
                if (!UnWantedColumnsNames(Columns.getString("COLUMN_NAME"))) {
                    ColumnsNames.add(Columns.getString("COLUMN_NAME"));
                }
            }
            Columns.close();
            String[] Colum = new String[ColumnsNames.size()];
            int count = 0;
            for (String CN : ColumnsNames) {
                Colum[count] = CN;
                count++;
            }
            jCombo.setModel(new DefaultComboBoxModel<>(Colum));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBC.Disconect();
        }
    }
}
