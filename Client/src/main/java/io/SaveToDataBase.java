package io;

import items.FoodResidus;
import javafx.collections.ObservableList;

import java.io.File;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.HashSet;
import java.util.Iterator;

import static serealize.XMLworker.objectToXML;
import static serealize.XMLworker.saveCollection;

/**
 * Created by danil on 12.06.2017.
 */
public class SaveToDataBase extends Thread {
    private ObservableList data;
    private String stringXML;
    public SaveToDataBase(String str, ObservableList data){
        super(str);
        this.data=data;
    }
    public void run(){
        try {
            DatagramSocket clientSocket = new DatagramSocket();
            byte[] ipAddr = new byte[]{(byte) 192, (byte)168, 1, (byte)129};
            InetAddress IPAddress = InetAddress.getByAddress(ipAddr);
            byte[] sendData = new byte[2048];
            byte[] receiveData = new byte[1];//TODO удостовериться в получении сервером данных
            ObservableList<FoodResidus> ob=data;
            HashSet<FoodResidus> set=new HashSet<>();
            Iterator<FoodResidus> iterator=ob.iterator();
            while(iterator.hasNext()){
                set.add(iterator.next());
            }
            stringXML=objectToXML(set);
            sendData=stringXML.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
            clientSocket.send(sendPacket);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}
