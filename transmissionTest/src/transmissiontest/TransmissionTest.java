/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transmissiontest;

/**
 *
 * @author p2titus
 */

import java.io.IOException; // used to catch errors that could occur
import java.io.PrintWriter; // used to send data over the socket object
import java.net.Socket; // used to establish a connection between client and server

public class TransmissionTest
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        
        /*
        ** exceptions are numbered allowing exception triggered to be easily identified
        ** subclass receivingData receives data sent in the main()
        ** runs as a separate thread to allow for data to be sent and received in same program (and also cos it's cool)
        */
        
        final TransmissionTest gm = new TransmissionTest(); // gm = getMethod
        final receivingData rd; // rd = receivingData
        
        final int port = 8080; // port to be used for communications. Left as a default of 8080, but users can change this
        // since this program wasn't intended to be more than sending data using sockets, I'm leaving it on 8080
        // all parts of this program will use this port variable, so feel free to change it
        final String ipToCon = "127.0.0.1"; // home ip address. probably possible to connect to external ip address, but I haven't tested yet
        String msg = "Failure"; // variable storing message. If program has failed in any way, failure will be output
        
        rd = new receivingData(port); // passing port variable to class, so it knows which port to be listening on
        System.out.println("FLAG 1"); // flags are present, allowing me to easily see where the program may be hanging
        rd.start(); // program hangs here - fixed (issue was using run() instead of start()
        // starts the code written under the overridden run() method in a new thread
        // as this is running in a separate thread, it will be able to receive the data at the same time
        System.out.println("FLAG 2");
        gm.sendMsg("Hello There!", ipToCon, port); // sending the message Hello There!
        msg = rd.msg(); // storing the received message in the msg variable
        System.out.println("FLAG 3");
        System.out.println(msg); // outputting correct response, but only some of the time?
        // probably because sending the message and receiving it has to take place in the time it takes to execute one line of code
    }
    
    private void sendMsg(String msg, String ip, int port) // prepares data to be sent by method sendData
    {
        try
        {
            //Socket s = new Socket(ip, port);
            sendData(msg, new Socket(ip, port)); // passes onto next method with socket object instantiated
        }
        
        catch(final IOException ex)
        {
            System.out.println("IOEXCEPTION 1");
        }
        
    }
    
    private void sendData(String msg, Socket sendData) // method that sends data over a network
    {
        PrintWriter PW = null; // will send message over network
        
        try
        {
            PW = new PrintWriter(sendData.getOutputStream()); // instantiating object with socket output stream
            // allows for data to be sent via the socket object
            PW.println(msg); // sending the message
            // it could be adapted using an arraylist to send more data
            PW.close(); // closes printwriter object
        }
        
        catch(final IOException ex)
        {
            System.out.println("IOEXCEPTION 2");
        }
        
        try
        {
            sendData.close(); // closes socket object, closing the connection between client and server
        }
        
        catch(final IOException ex)
        {
            System.out.println("IOEXCEPTION 3");
        }
    }
}
