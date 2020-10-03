package Application;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.AbstractTableModel;

public class ResultSetTabbleModel extends AbstractTableModel {

    private ResultSet rs;

    public ResultSetTabbleModel(ResultSet rs) {
        this.rs = rs;
        fireTableDataChanged();
    }

    //fonction getColumncount
    public int getColumnCount() {
        try {
            if (rs == null) {
                return 0;
            } else {
                return rs.getMetaData().getColumnCount();
            }
        } catch (SQLException e) {
            System.out.println("getColumnCount resultat generating error while getting column count");
            System.out.println(e.getMessage());
            return 0;
        }
    }

    // fonction getrowcount
    public int getRowCount() {
        try {
            if (rs == null) {
                return 0;
            } else {
                return rs.getRow();
            }
        } catch (SQLException e) {
            System.out.println("getrowCount resultat generating error while getting rows count");
            System.out.println(e.getMessage());
            return 0;
        }
    }

    // gonction getvalue at 
    public Object getValueAt(int rowIndex, int columnIndex) {

        if (rowIndex < 0 || rowIndex > getRowCount()
                || columnIndex < 0 || columnIndex > getColumnCount()) {
            return null;
        }

        try {
            if (rs == null) {
                return null;
            } else {
                rs.absolute(rowIndex + 1);
                return rs.getObject(columnIndex + 1);
            }
        } catch (SQLException e) {
            System.out.println("getValueat resultat generating error whilefetching rows count");
            System.out.println(e.getMessage());
            return null;
        }
    }

    //fction getcolumn name
    @Override
    public String getColumnName(int columnIndex) {

        try {

            return rs.getMetaData().getColumnName(columnIndex + 1);

        } catch (SQLException e) {
            System.out.println("getcolumnname resultat generating error whilefetching name");
            System.out.println(e.getMessage());
        }
        return null;
    }
}
