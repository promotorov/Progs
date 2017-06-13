package serverSample;

import items.FoodResidus;
import items.Whine;

import javax.sql.PooledConnection;
import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import java.sql.*;
import java.util.HashSet;
import java.util.Iterator;
//TODO исправить баг сос стрелочками
//TODO инсерт на добавление щелчком по розовой области
/**
 * Created by danil on 11.06.2017.
 */
public class Queries {
    public Queries(){}
    public HashSet<FoodResidus> loadAllRows(JdbcRowSet jrs, String tableName, Connection connection){
        try{
            System.out.println("- Load (initial) all rows from database table: " + tableName);
            Statement statement=connection.createStatement();
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
    public void removeAllRows(JdbcRowSet jrs, String tableName, Connection connection) throws SQLException{
        connection.setAutoCommit(false);
        Savepoint savepointOne = connection.setSavepoint("SavepointOne");
        try{
            System.out.println("- Remove all rows from database table: " + tableName);
            Statement statement=connection.createStatement();
            String sql = "DELETE FROM " + tableName;
            int count=statement.executeUpdate(sql);
            connection.commit();
            System.out.println("Remove successfull: "+count);
        }catch(SQLException e){
            if (connection != null) {
                try {
                    System.err.print("Transaction is being rolled back:r");
                    connection.rollback(savepointOne);
                } catch(SQLException excep) {
                    excep.printStackTrace();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void insertAllRows(JdbcRowSet jrs, String tableName, HashSet<FoodResidus> hs, Connection connection) throws SQLException{
        connection.setAutoCommit(false);
        Savepoint savepointOne = connection.setSavepoint("SavepointOne");
        try{
            System.out.println("- Insert rows into database table: " + tableName);
            Iterator<FoodResidus> iterator = hs.iterator();
            PreparedStatement statement = null;
            String sql = "\"INSERT INTO "+tableName+"(\"Name\", \"Weight\") VALUES ('?',?);";
            statement=connection.prepareStatement(sql);
            int count=0;
            while (iterator.hasNext()) {
                FoodResidus fr = iterator.next();
                statement.setString(1, fr.getName());
                statement.setInt(2, fr.getWeight());
                statement.executeUpdate(sql);
                count++;
                System.out.println("IN WHILE");
                connection.commit();
            };
            System.out.println("Inset was commited: "+count);
        }catch(SQLException e){
            if (connection != null) {
                try {
                    System.err.print("Transaction is being rolled back:i");
                    connection.rollback(savepointOne);
                } catch(SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void replaceRow(JdbcRowSet jrs, String tableName,Statement statement, HashSet<FoodResidus> oldObject,  HashSet<FoodResidus> newObject){
        try{
            System.out.println("- Replce row in database table: " + tableName);
            Iterator<FoodResidus> iteratorOld=oldObject.iterator();
            Iterator<FoodResidus> iteratorNew=newObject.iterator();
            FoodResidus oldOb;
            FoodResidus newOb;
            oldOb=iteratorOld.next();
            newOb=iteratorNew.next();
            String sql = "";
            sql= "UPDATE "+tableName+"  SET \"Name\"='"+newOb.getName()+"', \"Weight\"="+newOb.getWeight()+"   WHERE \"Name\"='"+oldOb.getName()+"' AND \"Weight\"="+oldOb.getWeight()+";";
            statement.executeUpdate(sql);
            System.out.println("Replace was commited");
        }catch(Exception e){
            System.out.println(e);
        }
    }
    public void insertRow(JdbcRowSet jrs, String tableName,Statement statement, HashSet<FoodResidus> newObject){
        try{
            System.out.println("- Insert row in database table: " + tableName);
            Iterator<FoodResidus> iteratorNew=newObject.iterator();
            FoodResidus newOb;
            newOb=iteratorNew.next();
            String sql = "";
            sql= "INSERT INTO "+tableName+"(\"Name\", \"Weight\") VALUES ('"+newOb.getName()+"',"+ newOb.getWeight()+");";
            statement.executeUpdate(sql);
            System.out.println("Insert was commited");
        }catch(Exception e){
            System.out.println(e);
        }
    }
    public void deleteRow(JdbcRowSet jrs, String tableName,Statement statement, HashSet<FoodResidus> oldObject){
        try{
            System.out.println("- Delete row in database table: " + tableName);
            Iterator<FoodResidus> iteratorOld=oldObject.iterator();
            FoodResidus oldOb;
            oldOb=iteratorOld.next();
            String sql = "";
            sql= "DELETE FROM "+tableName+" WHERE \"Name\"='"+oldOb.getName()+"' AND \"Weight\"="+oldOb.getWeight()+";";
            System.out.println(sql);
            statement.executeUpdate(sql);
            System.out.println("Delete was commited");
        }catch(Exception e){
            System.out.println(e);
        }
    }
    public void refreshTable(JdbcRowSet jrs, String tableName, HashSet<FoodResidus> hs, Connection connection) throws SQLException{
        connection.setAutoCommit(false);
        Savepoint savepointOne = connection.setSavepoint("SavepointOne");
        try{
            System.out.println("- Refresh rows in database table: " + tableName);
            Statement statement=connection.createStatement();
            String sql = "DELETE FROM " + tableName;
            int count=statement.executeUpdate(sql);
            Iterator<FoodResidus> iterator = hs.iterator();
            String psql = "\"INSERT INTO "+tableName+"(\"Name\", \"Weight\") VALUES ('?',?);";
            int count2=0;
            while (iterator.hasNext()) {
                FoodResidus fr = iterator.next();
                sql= "INSERT INTO "+tableName+"(\"Name\", \"Weight\") VALUES ('"+fr.getName()+"',"+ fr.getWeight()+");";
                statement.executeUpdate(sql);
                count++;
            };
            connection.commit();
            System.out.println("Refresh was commited: "+count);
        }catch(SQLException e){
            if (connection != null) {
                try {
                    System.err.print("Transaction is being rolled back:i");
                    connection.rollback(savepointOne);
                } catch(SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
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
