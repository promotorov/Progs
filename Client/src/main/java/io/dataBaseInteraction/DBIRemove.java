package io.dataBaseInteraction;

import items.FoodResidus;
import serealize.XMLworker;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.HashSet;

/**
 * Created by danil on 13.06.2017.
 */
public class DBIRemove extends Thread implements DataBaseInteraction{
    String str;
    FoodResidus oldObject;
    public DBIRemove(String str, FoodResidus oldObject){
        this.str=str;
        this.oldObject=oldObject;
    }
    public void run(){
        try{
            DatagramSocket clientSocket = new DatagramSocket();
            byte[] ipAddr = DataBaseInteraction.IP;
            InetAddress IPAddress = InetAddress.getByAddress(ipAddr);
            byte[] sendData = new byte[1];
            sendData[0]=(byte) DataBaseInteraction.REMOVE_ELEMENT;
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
            clientSocket.send(sendPacket);
            HashSet<FoodResidus> hsOld=new HashSet<FoodResidus>();
            hsOld.add(oldObject);
            String oldXml= XMLworker.objectToXML(hsOld);
            sendData=oldXml.getBytes();
            DatagramPacket sendOldObject = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
            clientSocket.send(sendOldObject);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
