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
public class DBIClear extends Thread implements DataBaseInteraction {
    ObservableList<FoodResidus> data;
    String str;
    public DBIClear(String str, ObservableList<FoodResidus> data){
        this.str=str;
        this.data=data;
    }
    public void run(){
        try{
            //TODO undo/redo for db
            DatagramSocket clientSocket = new DatagramSocket();
            byte[] ipAddr = new byte[]{(byte) 192, (byte)168, 1, (byte)129};
            InetAddress IPAddress = InetAddress.getByAddress(ipAddr);
            byte[] sendData = new byte[1];
            sendData[0]=(byte) DataBaseInteraction.CLEAR_TABLE;
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
            clientSocket.send(sendPacket);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
