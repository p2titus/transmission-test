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

import java.io.BufferedReader; // allows for message being received to be read
import java.io.IOException; // allows for ioexceptions to be caught and dealt with
import java.io.InputStreamReader; // allows for the input stream to be read
import java.net.Socket; // allows for a connection to be established to the client
import java.net.ServerSocket; // allows for a connection to be established to the client

public class receivingData extends Thread // allows for threads to be used in this class
{
    private String msg = null; // storing the message that will be received
    private int port = 8080; // storing the port used - default same as main class
    
    public receivingData(int port) // allows user to pass in their own custom port
    {
        this.port = port; // assigns user's port value to value of port in class
    }
    
    @Override
    public void run() // all code that will be executed in a separate thread to the main
    {
        msg = receiveData(); // variable msg will hold the message
        // as run has a void return type, this was the simplest workaround
    }
    
    private String receiveData()
    {
        String msgToRet = null; // messageToReturn
        //ServerSocket listener = null;
        try
        {
            msgToRet = recvData(new ServerSocket(port)); // passing on server object to receiveData method
        }
        
        catch(final IOException ex)
        {
            System.out.println("IOEXCEPTION A1");
        }
        
        return msgToRet; // returns the message that was received by the server
    }
    
    private String recvData(ServerSocket listener) // code that will receive the message sent by the user
    {
        String msgToRet = null; // the message to be returned
        Socket s = null; // socket that 
        InputStreamReader ISR = null;
        BufferedReader BR = null;
        
        try
        {
            /*while(true)
            {
                s = listener.accept(); // accepts any incoming connections
                break; // breaks out of loop when accepted
                // as accept() will hang unti a connection is found, this loop will end when a connection is found
            }*/
            
            s = listener.accept(); // accepts any incoming connections
            // thread will hang until connection found
        }
        
        catch(final IOException ex)
        {
            System.out.println("IOEXCEPTION A2");
        }
        
        try
        {
            ISR = new InputStreamReader(s.getInputStream());
            BR = new BufferedReader(ISR); // allows for data sent to be read
            msgToRet = BR.readLine(); // msg sent stored in variable
        }
        
        catch(final IOException ex)
        {
            System.out.println("IOEXCEPTION A3");
        }
        
        finally
        {
            try // closing all objects
            {
                BR.close();
                ISR.close();
                s.close();
                listener.close();
            }
            
            catch(final IOException ex)
            {
                System.out.println("IOEXCEPTION A4");
            }
        }
        
        return msgToRet; // returns sent message
    }
        
    public String msg() // public interface - returns the message sent
    {
        return msg;
    }
}
