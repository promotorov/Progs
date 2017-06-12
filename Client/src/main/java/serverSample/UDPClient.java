package serverSample;

import java.io.*;
import java.net.*;

class UDPClient{
    public static void main(String args[]) throws Exception{
        System.out.println("Client is ready");
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        DatagramSocket clientSocket = new DatagramSocket();
        byte[] ipAddr = new byte[]{(byte) 192, (byte)168, 1, (byte)129};
        InetAddress IPAddress = InetAddress.getByAddress(ipAddr);
        System.out.println(InetAddress.getLocalHost());
        byte[] sendData = new byte[1024];
        byte[] receiveData = new byte[1024];
        String sentence = inFromUser.readLine();
        sendData = sentence.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
        clientSocket.send(sendPacket);
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        clientSocket.receive(receivePacket);
        String modifiedSentence = new String(receivePacket.getData());
        System.out.println("FROM SERVER:" + modifiedSentence);
        clientSocket.close();
    }
}