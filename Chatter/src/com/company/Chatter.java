package com.company;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * This class implements a very simple selftest of a UDP communicator.
 *
 * It is badly designed! Please figure out what bad styles regarding
 * the system resources and security can be found here!
 */
public class Chatter {

    /* First some constants */
    private static int VERSION_MAYOR = 1;
    private static int VERSION_MINOR = 0;
    private static int VERSION_BUILD = 0;
    private static String VERSION  = "" + VERSION_MAYOR + "." + VERSION_MINOR + "." + VERSION_BUILD;

    /**
     * Prints a usage message on the stdout stream.
     */
    private static void usage() {
        System.out.println( "Chatter V"+VERSION );
        System.out.println( "" );
    }

    /**
     * Selftest listener and sender.
     */
    public static void main(String[] args) {
        /* Print usage stream */
        usage();

        /* starting listener */
        System.out.println("Starting Listener");
        Listener listen = new Listener();

        /* sending a ping to localhost */
        System.out.println("Sending Ping");
        try{
            if(Sender.send(InetAddress.getByName("127.0.0.1"),Listener.Discovery_PORT,"wd;duck;testmessage")) {
                System.out.println("Ping sent successfully");
            } else {
                System.out.println("Ping NOT SENT");
            }
        } catch(UnknownHostException uhe) {
            uhe.printStackTrace();
        }

        /* sending a ping to broadcast */
        System.out.println("Sending Ping to broadcast");
        try{
            if(Sender.send(InetAddress.getByName("255.255.255.255"),Listener.SEND_PORT,"wd;duck;testmessage broadcast")) {
                System.out.println("Ping sent successfully");
            } else {
                System.out.println("Ping NOT SENT");
            }
        } catch(UnknownHostException uhe) {
            uhe.printStackTrace();
        }

        /* shutting down listener */
        System.out.println("Shutting down Chatter");
        listen.shutdown();
        System.out.println("Chatter has shutdown");
    }
}
