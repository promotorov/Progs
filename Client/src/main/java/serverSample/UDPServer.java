package serverSample;

import io.dataBaseInteraction.DataBaseInteraction;
import items.FoodResidus;
import serealize.XMLworker;
import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import java.net.*;
import java.sql.Array;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;

class UDPServer{
    private static final String URL = "jdbc:postgresql://127.0.0.1:5432/Musorka";
    private static final String USER = "postgres";
    private static final String PASSSWORD = "z2UjMkxX";
    private static final String DRIVER = "org.postgresql.Driver";
    private static final String NAME = "public.\"FoodResidus\"";
    private static ArrayList<InetAddress> currentClientsIPs= new ArrayList();
    private static ArrayList<Integer> currentClientsPorts= new ArrayList();
    public static void main(String args[]) throws Exception{
        DataBaseCommunication dbc = new DataBaseCommunication(URL, USER, PASSSWORD, DRIVER);
        Statement statement = dbc.getStatement();
        Queries queries = new Queries();
        DatagramSocket serverSocket = new DatagramSocket(9876);
        byte[] receiveData = new byte[4096];
        byte[] sendData;
        System.out.print("Server is ready: ");
        System.out.println(InetAddress.getLocalHost());
        while(true){//TODO обнавлять бд на открытие пользователем xml файла
            DatagramPacket receiveCommand = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receiveCommand);
            InetAddress IPAddress = receiveCommand.getAddress();
            int port = receiveCommand.getPort();
            if(!currentClientsIPs.contains(IPAddress)){
                currentClientsIPs.add(IPAddress);
                currentClientsPorts.add(port);
            }
            System.out.println(IPAddress.getHostName()+" was connected");
            if(receiveData[0]== DataBaseInteraction.INIT_TABLE){
                RowSetFactory rsFactory = RowSetProvider.newFactory();
                JdbcRowSet jdbcRowSet = rsFactory.createJdbcRowSet();
                HashSet<FoodResidus> data = queries.loadAllRows(jdbcRowSet, NAME, dbc.getPooledConnection().getConnection());
                String str = XMLworker.objectToXML(data);
                sendData=str.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
                serverSocket.send(sendPacket);
            }else if(receiveData[0]== DataBaseInteraction.CLEAR_TABLE){
                RowSetFactory rsFactory = RowSetProvider.newFactory();
                JdbcRowSet jdbcRowSet = rsFactory.createJdbcRowSet();
                queries.removeAllRows(jdbcRowSet, NAME,  dbc.getPooledConnection().getConnection());
                for(int i=0; i<currentClientsIPs.size(); i++){
                    if(currentClientsIPs.get(i).toString().equals(receiveCommand.getAddress().toString())){
                        continue;
                    }else{
                        byte[] sentToClient=new  byte[1];
                        //sentToClient=(DataBaseInteraction.CLEAR_TABLE+"").getBytes();
                        sentToClient[0]=DataBaseInteraction.CLEAR_TABLE;
                        DatagramPacket sendPacket = new DatagramPacket(sentToClient, sentToClient.length, currentClientsIPs.get(i), currentClientsPorts.get(i));
                        serverSocket.send(sendPacket);
                    }
                }
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
                queries.replaceRow(jdbcRowSet, NAME, dbc.getPooledConnection().getConnection(), oldObject, newObject);
                for(int i=0; i<currentClientsIPs.size(); i++){
                    if(currentClientsIPs.get(i).toString().equals(receiveCommand.getAddress().toString())){
                        continue;
                    }else{
                        byte[] sentToClient=new  byte[1];
                        //sentToClient=(DataBaseInteraction.CLEAR_TABLE+"").getBytes();
                        sentToClient[0]=DataBaseInteraction.CHANGE_ELEMENT;
                        DatagramPacket sendPacket = new DatagramPacket(sentToClient, sentToClient.length, currentClientsIPs.get(i), currentClientsPorts.get(i));
                        serverSocket.send(sendPacket);

                        byte[] sendCollection;
                        //sentToClient=(DataBaseInteraction.CLEAR_TABLE+"").getBytes();
                        HashSet<FoodResidus> hs=queries.loadAllRows(jdbcRowSet, NAME, dbc.getPooledConnection().getConnection());
                        //sentToClient[0]=DataBaseInteraction.CHANGE_ELEMENT;
                        String xml=XMLworker.objectToXML(hs);
                        sendCollection=xml.getBytes();
                        DatagramPacket collectionPacket = new DatagramPacket(sendCollection, sendCollection.length, currentClientsIPs.get(i), currentClientsPorts.get(i));
                        serverSocket.send(collectionPacket);
                    }
                }
            }else if(receiveData[0]== DataBaseInteraction.ADD_ELEMENT){
                byte[] receiveNewByte = new byte[4096];
                RowSetFactory rsFactory = RowSetProvider.newFactory();
                JdbcRowSet jdbcRowSet = rsFactory.createJdbcRowSet();
                DatagramPacket receiveNew = new DatagramPacket(receiveNewByte, receiveData.length);
                serverSocket.receive(receiveNew);
                String newXml=new String(receiveNewByte);
                HashSet newObject=XMLworker.xmlToObject(newXml);
                queries.insertRow(jdbcRowSet, NAME, dbc.getPooledConnection().getConnection(), newObject);
                for(int i=0; i<currentClientsIPs.size(); i++){
                    if(currentClientsIPs.get(i).toString().equals(receiveCommand.getAddress().toString())){
                        continue;
                    }else{
                        byte[] sentToClient=new  byte[1];
                        //sentToClient=(DataBaseInteraction.CLEAR_TABLE+"").getBytes();
                        sentToClient[0]=DataBaseInteraction.ADD_ELEMENT;
                        DatagramPacket sendPacket = new DatagramPacket(sentToClient, sentToClient.length, currentClientsIPs.get(i), currentClientsPorts.get(i));
                        serverSocket.send(sendPacket);

                        byte[] sendCollection;
                        //sentToClient=(DataBaseInteraction.CLEAR_TABLE+"").getBytes();
                        HashSet<FoodResidus> hs=queries.loadAllRows(jdbcRowSet, NAME, dbc.getPooledConnection().getConnection());
                        //sentToClient[0]=DataBaseInteraction.CHANGE_ELEMENT;
                        String xml=XMLworker.objectToXML(hs);
                        sendCollection=xml.getBytes();
                        DatagramPacket collectionPacket = new DatagramPacket(sendCollection, sendCollection.length, currentClientsIPs.get(i), currentClientsPorts.get(i));
                        serverSocket.send(collectionPacket);
                    }
                }
            }else if(receiveData[0]== DataBaseInteraction.REMOVE_ELEMENT){
                byte[] receiveOldByte = new byte[4096];
                RowSetFactory rsFactory = RowSetProvider.newFactory();
                JdbcRowSet jdbcRowSet = rsFactory.createJdbcRowSet();
                DatagramPacket receiveOld = new DatagramPacket(receiveOldByte, receiveData.length);
                serverSocket.receive(receiveOld);
                String newXml=new String(receiveOldByte);
                HashSet oldObject=XMLworker.xmlToObject(newXml);
                queries.deleteRow(jdbcRowSet, NAME, dbc.getPooledConnection().getConnection(), oldObject);
                for(int i=0; i<currentClientsIPs.size(); i++){
                    if(currentClientsIPs.get(i).toString().equals(receiveCommand.getAddress().toString())){
                        continue;
                    }else{
                        //System.out.println(currentClientsIPs.get(i));
                        byte[] sentToClient=new  byte[1];
                        //sentToClient=(DataBaseInteraction.CLEAR_TABLE+"").getBytes();
                        sentToClient[0]=DataBaseInteraction.REMOVE_ELEMENT;
                        DatagramPacket sendPacket = new DatagramPacket(sentToClient, sentToClient.length, currentClientsIPs.get(i), currentClientsPorts.get(i));
                        serverSocket.send(sendPacket);

                        byte[] sendCollection;
                        //sentToClient=(DataBaseInteraction.CLEAR_TABLE+"").getBytes();
                        HashSet<FoodResidus> hs=queries.loadAllRows(jdbcRowSet, NAME, dbc.getPooledConnection().getConnection());
                        //sentToClient[0]=DataBaseInteraction.CHANGE_ELEMENT;
                        String xml=XMLworker.objectToXML(hs);
                        sendCollection=xml.getBytes();
                        DatagramPacket collectionPacket = new DatagramPacket(sendCollection, sendCollection.length, currentClientsIPs.get(i), currentClientsPorts.get(i));
                        serverSocket.send(collectionPacket);
                    }
                }
            }else if(receiveData[0]== DataBaseInteraction.REFRESH_TABLE){
                byte[] receiveNewByte = new byte[4096];
                RowSetFactory rsFactory = RowSetProvider.newFactory();
                JdbcRowSet jdbcRowSet = rsFactory.createJdbcRowSet();
                DatagramPacket receiveNew = new DatagramPacket(receiveNewByte, receiveData.length);
                serverSocket.receive(receiveNew);
                String newXml=new String(receiveNewByte);
                HashSet newObject=XMLworker.xmlToObject(newXml);
                queries.refreshTable(jdbcRowSet, NAME, newObject, dbc.getPooledConnection().getConnection());
                for(int i=0; i<currentClientsIPs.size(); i++){//TODO занести в отдельный метод обратную отсылку данных из бд
                    if(currentClientsIPs.get(i).toString().equals(receiveCommand.getAddress().toString())){
                        continue;
                    }else{
                        byte[] sentToClient=new  byte[1];
                        //sentToClient=(DataBaseInteraction.CLEAR_TABLE+"").getBytes();
                        sentToClient[0]=DataBaseInteraction.REFRESH_TABLE;
                        DatagramPacket sendPacket = new DatagramPacket(sentToClient, sentToClient.length, currentClientsIPs.get(i), currentClientsPorts.get(i));
                        serverSocket.send(sendPacket);

                        byte[] sendCollection;
                        //sentToClient=(DataBaseInteraction.CLEAR_TABLE+"").getBytes();
                        HashSet<FoodResidus> hs=queries.loadAllRows(jdbcRowSet, NAME, dbc.getPooledConnection().getConnection());
                        //sentToClient[0]=DataBaseInteraction.CHANGE_ELEMENT;
                        String xml=XMLworker.objectToXML(hs);
                        sendCollection=xml.getBytes();
                        DatagramPacket collectionPacket = new DatagramPacket(sendCollection, sendCollection.length, currentClientsIPs.get(i), currentClientsPorts.get(i));
                        serverSocket.send(collectionPacket);
                    }
                }
            }
        }
    }
}