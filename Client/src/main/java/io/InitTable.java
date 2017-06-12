package io;

import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import items.FoodResidus;
import serealize.XMLworker;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by vladp on 09.05.2017.
 */
public class InitTable extends Thread{
    private ObservableList<FoodResidus> data;
    private TableView table;
    public InitTable(String str, ObservableList<FoodResidus> data, TableView table){
        super(str);
        this.data=data;
        this.table=table;
    }
    public void run() {
        try {
            DatagramSocket clientSocket = new DatagramSocket();
            byte[] ipAddr = new byte[]{(byte) 192, (byte)168, 1, (byte)129};
            InetAddress IPAddress = InetAddress.getByAddress(ipAddr);
            byte[] sendData = new byte[1];
            byte[] receiveData = new byte[100000];
            sendData[0]=1;
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
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}
