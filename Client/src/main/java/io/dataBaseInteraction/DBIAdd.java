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
public class DBIAdd extends Thread implements DataBaseInteraction{
    String str;
    FoodResidus newObject;
    public DBIAdd(String str, FoodResidus newObject){
        this.str=str;
        this.newObject=newObject;
    }
    public void run(){
        try{
            DatagramSocket clientSocket = new DatagramSocket();
            byte[] ipAddr = DataBaseInteraction.IP;
            InetAddress IPAddress = InetAddress.getByAddress(ipAddr);
            byte[] sendData = new byte[1];
            sendData[0]=(byte) DataBaseInteraction.ADD_ELEMENT;
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
            clientSocket.send(sendPacket);
            HashSet<FoodResidus> hsNew=new HashSet<FoodResidus>();
            hsNew.add(newObject);
            String newXml= XMLworker.objectToXML(hsNew);
            sendData=newXml.getBytes();
            DatagramPacket sendNewObject = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
            clientSocket.send(sendNewObject);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
