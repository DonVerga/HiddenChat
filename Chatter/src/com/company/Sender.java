package com.company;

import java.io.IOException;
import java.net.*;

public class Sender {

    public static boolean send(InetAddress ia, int port, String txt) {
        try {
            System.out.println("Sending message \""+txt+"\" ("+ia.toString()+":"+port+")");
            DatagramSocket ds = new DatagramSocket();
            DatagramPacket dp = new DatagramPacket(txt.getBytes(), txt.length(), ia, port);
            ds.send(dp);
            ds.close();
        } catch(SocketException se) {
            System.out.println("unable to bind socket");
            se.printStackTrace();
            return false;
        } catch(IOException ie) {
            System.out.println("unable send data");
            ie.printStackTrace();
            return false;
        }
        return true;
    }

}
