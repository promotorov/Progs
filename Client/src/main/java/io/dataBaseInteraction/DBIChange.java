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
public class DBIChange extends Thread implements DataBaseInteraction {
    FoodResidus oldObject;
    FoodResidus newObject;
    String str;
    public DBIChange(String str, FoodResidus oldObject, FoodResidus newObject){
        this.str=str;
        this.oldObject=oldObject;
        this.newObject=newObject;
    }
    public void run(){
        try{
            DatagramSocket clientSocket = new DatagramSocket();
            byte[] ipAddr = new byte[]{(byte) 192, (byte)168, 1, (byte)129};
            InetAddress IPAddress = InetAddress.getByAddress(ipAddr);
            byte[] sendData = new byte[1000];
            sendData[0]=(byte) DataBaseInteraction.CHANGE_ELEMENT; //TODO int to byte
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
            clientSocket.send(sendPacket);
            HashSet<FoodResidus> hsOld=new HashSet<FoodResidus>();
            hsOld.add(oldObject);
            String oldXml=XMLworker.objectToXML(hsOld);
            sendData=oldXml.getBytes();
            DatagramPacket sendOldObject = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
            clientSocket.send(sendOldObject);
            HashSet<FoodResidus> hsNew=new HashSet<FoodResidus>();
            hsNew.add(newObject);
            String newXml=XMLworker.objectToXML(hsNew);;
            sendData=newXml.getBytes();
            DatagramPacket sendNewObject = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
            clientSocket.send(sendNewObject);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
