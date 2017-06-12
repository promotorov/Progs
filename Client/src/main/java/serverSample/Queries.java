package serverSample;

import items.FoodResidus;
import items.Whine;

import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by danil on 11.06.2017.
 */
public class Queries {
    public Queries(){}
    public HashSet<FoodResidus> loadAllRows(JdbcRowSet jrs, String tableName, Statement statement){
        try{
            System.out.println("- Load (initial) all rows from database table: " + tableName);
            String sql = "SELECT * FROM " + tableName;
            ResultSet resultSet = statement.executeQuery(sql);
            HashSet<FoodResidus> data= new HashSet<FoodResidus>();
            while(resultSet.next()){
                data.add(new Whine(resultSet.getString("Name"),resultSet.getInt("Weight")));
            }
            return data;
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }
    public void removeAllRows(JdbcRowSet jrs, String tableName){
        try{
            System.out.println("- Remove all rows from database table: " + tableName);
            String sql = "SELECT * FROM " + tableName;
            jrs.beforeFirst();
            while (jrs.next()){
                jrs.deleteRow();
            }
            System.out.println("Remove successfull");
        }catch(Exception e){
            System.out.println(e);
        }
    }
    public void insertAllRows(JdbcRowSet jrs, String tableName, HashSet<FoodResidus> hs){
        try{
            System.out.println("- Insert rows into database table: " + tableName);
            Iterator<FoodResidus> iterator = hs.iterator();
            while (iterator.hasNext()) {
                FoodResidus fr = iterator.next();
                jrs.moveToInsertRow();
                jrs.updateString("Name", fr.getName());
                jrs.updateInt("Weight", fr.getWeight());
                jrs.insertRow();
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
    private int getRowCount(JdbcRowSet jrs){
        try {
            jrs.last();
            return jrs.getRow();
        }catch(Exception e){
            System.out.println(e);
            return  -1;
        }
    }
}
