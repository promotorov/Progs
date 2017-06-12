package main;

import items.FoodResidus;
import serealize.XMLworker;
import serverSample.DataBaseCommunication;
import serverSample.Queries;
import javax.sql.rowset.JdbcRowSet;
import java.util.HashSet;

/**
 * Created by danil on 11.06.2017.
 */
public class MainServer {
    private static final String URL = "jdbc:postgresql://127.0.0.1:5432/Musorka";
    private static final String USER = "postgres";
    private static final String PASSSWORD = "z2UjMkxX";
    private static final String DRIVER = "org.postgresql.Driver";
    private static final String NAME = "public.\"FoodResidus\"";
    public static void main(String[] args){
        try {
            DataBaseCommunication dbc = new DataBaseCommunication(URL, USER, PASSSWORD, DRIVER);
            Queries queries = new Queries();
            //JdbcRowSet jdbcRowSet = queries.getJDBCRowset(USER,PASSSWORD,URL);
            //HashSet<FoodResidus> data = queries.loadAllRows(jdbcRowSet, NAME);
            //XMLworker.saveCollection("src\\main\\resources\\DataBase.xml",data);
        }catch (Exception e){
            System.out.println("e");
        }
    }
}