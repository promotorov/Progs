package serverSample;

import io.dataBaseInteraction.DataBaseInteraction;
import items.FoodResidus;
import serealize.XMLworker;

import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import java.net.*;
import java.sql.Statement;
import java.util.HashSet;

class UDPServer{
    private static final String URL = "jdbc:postgresql://127.0.0.1:5432/Musorka";
    private static final String USER = "postgres";
    private static final String PASSSWORD = "z2UjMkxX";
    private static final String DRIVER = "org.postgresql.Driver";
    private static final String NAME = "public.\"FoodResidus\"";
    public static void main(String args[]) throws Exception{
        DataBaseCommunication dbc = new DataBaseCommunication(URL, USER, PASSSWORD, DRIVER);
        Statement statement = dbc.getStatement();
        Queries queries = new Queries();
        DatagramSocket serverSocket = new DatagramSocket(9876);
        byte[] receiveData = new byte[4096];
        byte[] sendData;
        System.out.println("Server is ready");
        System.out.println(InetAddress.getLocalHost());
        while(true){
            DatagramPacket receiveCommand = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receiveCommand);
            InetAddress IPAddress = receiveCommand.getAddress();
            int port = receiveCommand.getPort();
            if(receiveData[0]== DataBaseInteraction.INIT_TABLE){
                RowSetFactory rsFactory = RowSetProvider.newFactory();
                JdbcRowSet jdbcRowSet = rsFactory.createJdbcRowSet();
                HashSet<FoodResidus> data = queries.loadAllRows(jdbcRowSet, NAME, statement);
                String str = XMLworker.objectToXML(data);
                sendData=str.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
                serverSocket.send(sendPacket);
            }else if(receiveData[0]== DataBaseInteraction.CLEAR_TABLE){
                /*DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);//TODO в отдельный метод
                serverSocket.receive(receivePacket);
                String resievedCollection = new String(receiveData);
                HashSet<FoodResidus> colectionToInsert= XMLworker.xmlToObject(resievedCollection);*/
                RowSetFactory rsFactory = RowSetProvider.newFactory();
                JdbcRowSet jdbcRowSet = rsFactory.createJdbcRowSet();
                queries.removeAllRows(jdbcRowSet, NAME, statement);
                //queries.insertAllRows(jdbcRowSet, NAME, colectionToInsert, statement);
            }else if(receiveData[0]== DataBaseInteraction.CHANGE_ELEMENT) {
                byte[] receiveOldByte = new byte[4096];
                byte[] receiveNewByte = new byte[4096];
                RowSetFactory rsFactory = RowSetProvider.newFactory();
                JdbcRowSet jdbcRowSet = rsFactory.createJdbcRowSet();
                DatagramPacket receiveOld = new DatagramPacket(receiveOldByte, receiveData.length);
                serverSocket.receive(receiveOld);
                String oldXml=new String(receiveOldByte);
                HashSet oldObject=XMLworker.xmlToObject(oldXml);
                DatagramPacket receiveNew = new DatagramPacket(receiveNewByte, receiveData.length);
                serverSocket.receive(receiveNew);
                String newXml=new String(receiveNewByte);
                HashSet newObject=XMLworker.xmlToObject(newXml);
                queries.replaceRow(jdbcRowSet, NAME, statement, oldObject, newObject);

            }
        }
    }
}