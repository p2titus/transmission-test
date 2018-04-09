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
import java.io.*;
import java.net.*;
public class SendingInfo extends Thread
{
    private String serverAd = "127.0.0.1";
    private int port = 0;
    private Socket s = null;
    //private String msg = null;
    
    public SendingInfo(int port)
    {
        this.port = port;
        instantiateSocket();
        //sendData("Hello there!");
    }
    
    /*@Override
    public void run()
    {
        sendData(msg);
    }//*/
    
    public void SendData(String msg)
    {
        //this.msg = msg; // cheeky little workaround
        //run();
        sendData(msg);
    }
    
    private void sendData(String msg)
    {
        PrintWriter PW = null;
        
        try
        {
            PW = new PrintWriter(s.getOutputStream(), true);
            PW.println(msg);
        }
        
        catch(final IOException ex)
        {
            System.out.println("IOEXCEPTION B2");
        }
        
        finally
        {
            PW.close();
        }
    }
    
    private void instantiateSocket()
    {
        try
        {
            this.s = new Socket(serverAd, port);
        }
        
        catch(final UnknownHostException ex)
        {
            System.out.println("UNKNOWNHOSTEXCEPTION B1");
        }
        
        catch(final IOException ex)
        {
            System.out.println("IOEXCEPTION B1: " + ex);
        }
    }
}
