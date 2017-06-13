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
    public void removeAllRows(JdbcRowSet jrs, String tableName, Statement statement){
        try{
            System.out.println("- Remove all rows from database table: " + tableName);
            String sql = "DELETE FROM " + tableName;
            int count=statement.executeUpdate(sql);
            System.out.println("Remove successfull: "+count);
        }catch(Exception e){
            System.out.println(e);
        }
    }
    public void insertAllRows(JdbcRowSet jrs, String tableName, HashSet<FoodResidus> hs, Statement statement){
        try{
            System.out.println("- Insert rows into database table: " + tableName);
            Iterator<FoodResidus> iterator = hs.iterator();
            String sql = "";
            int count=0;
            System.out.println(hs.size()+"Размер коллекции");
            while (iterator.hasNext()) {
                FoodResidus fr = iterator.next();
                sql="INSERT INTO "+tableName+"(\"Name\", \"Weight\") VALUES ('"+fr.getName()+"',"+ fr.getWeight()+");";
                statement.executeUpdate(sql);
                count++;
            }
            System.out.println("Inset was commited: "+count);
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
