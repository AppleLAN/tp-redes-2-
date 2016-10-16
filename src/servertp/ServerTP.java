/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servertp;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author gaston.mira
 */
public class ServerTP {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {

        int port = 6006;
        ServerSocket server = new ServerSocket(port);
        System.out.println("Servidor Escuchando por puerto " + String.valueOf(port));
        
        while (true) {
            Socket clientSocket = server.accept();
             // reading from keyboard (keyRead object)
            BufferedReader keyRead = new BufferedReader(new InputStreamReader(System.in));
            // sending to client (pwrite object)
            OutputStream ostream = clientSocket.getOutputStream(); 
            PrintWriter pwrite = new PrintWriter(ostream, true);
            
            // receiving from server ( receiveRead  object)
            InputStream istream = clientSocket.getInputStream();
            BufferedReader receiveRead = new BufferedReader(new InputStreamReader(istream));

            String receiveMessage, sendMessage;               
            while(true)
            {
              if((receiveMessage = receiveRead.readLine()) != null)  
              {
                 System.out.println(receiveMessage);         
              }         
              sendMessage = keyRead.readLine(); 
              pwrite.println(sendMessage);             
              pwrite.flush();
            }               
          }                    
        }
    }
