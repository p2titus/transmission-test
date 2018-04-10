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
        rd = new receivingData(port); // rd = receivingData
        
        rd.run();
        
    }
    
}
