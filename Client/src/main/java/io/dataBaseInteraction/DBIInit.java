package io.dataBaseInteraction;

import items.FoodResidus;
import javafx.collections.ObservableList;
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
            byte[] ipAddr = new byte[]{(byte) 192, (byte)168, 1, (byte)129};
            InetAddress IPAddress = InetAddress.getByAddress(ipAddr);
            byte[] sendData = new byte[1];
            byte[] receiveData = new byte[100000];
            sendData[0]=(byte) DataBaseInteraction.INIT_TABLE;
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
            clientSocket.send(sendPacket);
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);
            String str = new String(receiveData);
            System.out.println(str.lastIndexOf(">"));
            HashSet<FoodResidus> set = XMLworker.xmlToObject(str);
            Iterator<FoodResidus> iterator = set.iterator();
            while (iterator.hasNext()) {
                data.add(iterator.next());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
