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
import java.net.*;
import java.io.*;
public class receivingData extends Thread
{
    private String msg = null;
    private int port = 8080;
    
    public receivingData(int port)
    {
        this.port = port;
    }
    
    @Override
    public void run()
    {
        msg = receiveData();
    }
    
    private String receiveData()
    {
        String msgToRet = null; // messageToReturn
        //ServerSocket listener = null;
        try
        {
            msgToRet = recvData(new ServerSocket(port));
        }
        
        catch(final IOException ex)
        {
            System.out.println("IOEXCEPTION A1");
        }
        
        return msgToRet;
    }
    
    private String recvData(ServerSocket listener)
    {
        String msgToRet = null;
        Socket s = null;
        InputStreamReader ISR = null;
        BufferedReader BR = null;
        
        try
        {
            while(true)
            {
                s = listener.accept();
                break;
            }
        }
        
        catch(final IOException ex)
        {
            System.out.println("IOEXCEPTION A2");
        }
        
        try
        {
            ISR = new InputStreamReader(s.getInputStream());
            BR = new BufferedReader(ISR);
            msgToRet = BR.readLine();
        }
        
        catch(final IOException ex)
        {
            System.out.println("IOEXCEPTION A3");
        }
        
        finally
        {
            try
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
        
        return msgToRet;
    }
        
    public String msg()
    {
        return msg;
    }
}
