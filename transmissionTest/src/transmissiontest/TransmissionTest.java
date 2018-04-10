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
public class TransmissionTest
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        final TransmissionTest gm = new TransmissionTest(); // gm = getMethod
        final receivingData rd;
        
        final int port = 8080;
        final String ipToCon = "127.0.0.1";
        
        rd = new receivingData(port); // rd = receivingData
        
        //rd.run();
        
    }
    
    private void sendMsg(String msg, String ip, int port)
    {
        try
        {
            //Socket s = new Socket(ip, port);
            sendData(msg, new Socket(ip, port));
        }
        
        catch(final IOException ex)
        {
            System.out.println("IOEXCEPTION 1");
        }
        
    }
    
    private void sendData(String msg, Socket sendData)
    {
        PrintWriter PW = null;
        
        try
        {
            PW = new PrintWriter(sendData.getOutputStream());
            PW.println(msg);
            PW.close();
        }
        
        catch(final IOException ex)
        {
            System.out.println("IOEXCEPTION 2");
        }
    }
}
