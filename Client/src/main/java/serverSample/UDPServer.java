package serverSample;

import items.FoodResidus;
import serealize.XMLworker;

import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import java.io.File;
import java.net.*;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Scanner;

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
        while(true){
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);
            InetAddress IPAddress = receivePacket.getAddress();
            int port = receivePacket.getPort();
            if(receiveData[0]==1){
                RowSetFactory rsFactory = RowSetProvider.newFactory();
                JdbcRowSet jdbcRowSet = rsFactory.createJdbcRowSet();
                HashSet<FoodResidus> data = queries.loadAllRows(jdbcRowSet, NAME, statement);
                String str = XMLworker.objectToXML(data);
                sendData=str.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
                serverSocket.send(sendPacket);
            }else{
                System.out.println(receiveData.length);
                String resievedCollection = new String(receiveData);
                HashSet<FoodResidus> colectionToInsert= XMLworker.xmlToObject(resievedCollection);
                RowSetFactory rsFactory = RowSetProvider.newFactory();
                JdbcRowSet jdbcRowSet = rsFactory.createJdbcRowSet();
                queries.removeAllRows(jdbcRowSet, NAME, statement);
                queries.insertAllRows(jdbcRowSet, NAME, colectionToInsert, statement);
            }
        }
    }
}