package io.dataBaseInteraction;

import items.FoodResidus;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import screens.MainScreen;
import serealize.XMLworker;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by danil on 13.06.2017.
 */
public class DBIInit extends Thread implements DataBaseInteraction{
    ObservableList<FoodResidus> data;
    String str;
    public DBIInit(String str, ObservableList<FoodResidus> data){
        this.str=str;
        this.data=data;
    }
    public void run(){
        try{
            DatagramSocket clientSocket = new DatagramSocket();
            byte[] ipAddr = DataBaseInteraction.IP;
            InetAddress IPAddress = InetAddress.getByAddress(ipAddr);
            byte[] sendData = new byte[1];
            byte[] receiveData = new byte[100000];
            sendData[0]=(byte) DataBaseInteraction.INIT_TABLE;
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
            clientSocket.send(sendPacket);
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);
            String str = new String(receiveData);
            HashSet<FoodResidus> set = XMLworker.xmlToObject(str);
            Iterator<FoodResidus> iterator = set.iterator();
            while (iterator.hasNext()) {
                data.add(iterator.next());
            }
            while(true){
                byte[] refreshData = new byte[1];
                DatagramPacket refreshPacket = new DatagramPacket(refreshData, refreshData.length);
                clientSocket.receive(refreshPacket);
                if(refreshData[0]==DataBaseInteraction.CLEAR_TABLE){
                    data.clear();
                }else if(refreshData[0]==DataBaseInteraction.CHANGE_ELEMENT){
                    byte[] collection = new byte[10000];
                    DatagramPacket collectionPacket = new DatagramPacket(collection, collection.length);
                    clientSocket.receive(collectionPacket);
                    String xml=new String(collection);
                    HashSet<FoodResidus> newData = XMLworker.xmlToObject(xml);
                    System.out.println(xml);
                    ObservableList<FoodResidus> observableData = FXCollections.observableArrayList(newData);
                    MainScreen mainScreen=MainScreen.getInstace();
                    mainScreen.getTable().setItems(observableData);
                }else if(refreshData[0]==DataBaseInteraction.ADD_ELEMENT) {
                    byte[] collection = new byte[10000];
                    DatagramPacket collectionPacket = new DatagramPacket(collection, collection.length);
                    clientSocket.receive(collectionPacket);
                    String xml = new String(collection);
                    HashSet<FoodResidus> newData = XMLworker.xmlToObject(xml);
                    System.out.println(xml);
                    ObservableList<FoodResidus> observableData = FXCollections.observableArrayList(newData);
                    MainScreen mainScreen = MainScreen.getInstace();
                    mainScreen.getTable().setItems(observableData);
                }else if(refreshData[0]==DataBaseInteraction.REMOVE_ELEMENT) {
                    byte[] collection = new byte[10000];
                    DatagramPacket collectionPacket = new DatagramPacket(collection, collection.length);
                    clientSocket.receive(collectionPacket);
                    String xml = new String(collection);
                    HashSet<FoodResidus> newData = XMLworker.xmlToObject(xml);
                    System.out.println(xml);
                    ObservableList<FoodResidus> observableData = FXCollections.observableArrayList(newData);
                    MainScreen mainScreen = MainScreen.getInstace();
                    mainScreen.getTable().setItems(observableData);
                }else if(refreshData[0]==DataBaseInteraction.REFRESH_TABLE) {
                    byte[] collection = new byte[10000];
                    DatagramPacket collectionPacket = new DatagramPacket(collection, collection.length);
                    clientSocket.receive(collectionPacket);
                    String xml = new String(collection);
                    HashSet<FoodResidus> newData = XMLworker.xmlToObject(xml);
                    System.out.println(xml);
                    ObservableList<FoodResidus> observableData = FXCollections.observableArrayList(newData);
                    MainScreen mainScreen = MainScreen.getInstace();
                    mainScreen.getTable().setItems(observableData);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
