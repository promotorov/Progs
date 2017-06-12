package serverSample;

import items.FoodResidus;
import serealize.XMLworker;

import javax.sql.rowset.JdbcRowSet;
import java.io.File;
import java.net.*;
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
        Queries queries = new Queries();
        JdbcRowSet jdbcRowSet = queries.getJDBCRowset(USER,PASSSWORD,URL);
        DatagramSocket serverSocket = new DatagramSocket(9876);
        byte[] receiveData = new byte[1024];
        byte[] sendData;
        System.out.println("Server is ready");
        while(true){
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);
            InetAddress IPAddress = receivePacket.getAddress();
            int port = receivePacket.getPort();
            if(receiveData[0]==1){
                HashSet<FoodResidus> data = queries.loadAllRows(jdbcRowSet, NAME);
                String str = XMLworker.objectToXML(data);
                sendData=str.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
                serverSocket.send(sendPacket);
            }else{
                System.out.println(receiveData.length);
                String resievedCollection = new String(receiveData);
                HashSet<FoodResidus> colectionToInsert= XMLworker.xmlToObject(resievedCollection);
                queries.removeAllRows(jdbcRowSet, NAME);
                queries.insertAllRows(jdbcRowSet, NAME, colectionToInsert);
                System.out.println("Successful insert into table");
            }
        }
    }
}