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
        SendingInfo si = null;
        BufferedReader BR = null;
        InputStreamReader ISR = null;
        int port = 8080;
        ServerSocket listener = null;
        Socket s = null;
        String inputLine = "";
        short counter = 0;
        si = new SendingInfo(port);
        String outboundMsg = "Hello There!";
        
        try
        {
            System.out.println("FLAG 1");
            listener = new ServerSocket(port);
            System.out.println("FLAG 2");
        }
        
        catch(final IOException ex)
        {
            System.out.println("IOEXCEPTION 1");
        }
        
        si.SendData(outboundMsg); // connectionexception: no service listening
        // probably because it only starts listening a few lines below this...
        
        try
        {
            System.out.println("FLAG 3");
            try
            {
                System.out.println("FLAG 4");
                s = listener.accept(); // halting on this line, probably because nothing is sending data...
                System.out.println("FLAG 5");
                ISR = new InputStreamReader(s.getInputStream());
                BR = new BufferedReader(ISR);
                while((inputLine = BR.readLine()) != null)
                {
                    counter++;
                    System.out.println(inputLine);
                    if(counter > 1000)
                        break;
                }
            }
            
            catch(final IOException ex)
            {
                System.out.println("IOEXCEPTION 2");
            }
            
            finally
            {
                try
                {
                    s.close();
                }
                
                catch(final IOException ex)
                {
                    System.out.println("IOEXCEPTION 3");
                }
            }
        }
        
        finally
        {
            try
            {
                listener.close();
            }
            
            catch(final IOException ex)
            {
                System.out.println("IOEXCEPTION 1");
            }
        }
    }
    
}
