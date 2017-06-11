package serverSample;

import items.FoodResidus;
import items.Whine;

import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import java.util.HashSet;

/**
 * Created by danil on 11.06.2017.
 */
public class Queries {
    public Queries(){}
    public JdbcRowSet getJDBCRowset(String usr, String pas, String url){
        try{
            RowSetFactory rsFactory = RowSetProvider.newFactory();
            JdbcRowSet jRowset = rsFactory.createJdbcRowSet();
            jRowset.setUsername(usr);
            jRowset.setPassword(pas);
            jRowset.setUrl(url);
            jRowset.setReadOnly(false);
            return jRowset;
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }
    public HashSet<FoodResidus> loadAllRows(JdbcRowSet jrs, String tableName){
        try{
            System.out.println("- Load (initial) all rows from database table: " + tableName);
            String sql = "SELECT * FROM " + tableName;
            jrs.setCommand(sql);
            jrs.execute();
            HashSet<FoodResidus> data= new HashSet<FoodResidus>();
            while(jrs.next()){
                data.add(new Whine(jrs.getString("Name"),jrs.getInt("Weight")));
                System.out.println(jrs.getString("Name")+" "+jrs.getInt("Weight"));
            }
            return data;
        }catch(Exception e){
            System.out.println(e);
            return null;
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
