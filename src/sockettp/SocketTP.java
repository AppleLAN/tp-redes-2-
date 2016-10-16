/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sockettp;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

//Codigos de operacion para server y cliente: 
//1 - Mostrar Titulos
//2 - Mostrar noticia elegida
//3 - Cargar Noticia
/**
 *
 * @author gastonmira
 */
public class SocketTP {

    /**
     */
    public static int PuertoServerTCP = 6006;
    public static String HostnameServerTCP = "localhost";
    
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
         Socket sock = new Socket(HostnameServerTCP, PuertoServerTCP);
                               // reading from keyboard (keyRead object)
        BufferedReader keyRead = new BufferedReader(new InputStreamReader(System.in));
                              // sending to client (pwrite object)
        OutputStream ostream = sock.getOutputStream(); 
        PrintWriter pwrite = new PrintWriter(ostream, true);

                              // receiving from server ( receiveRead  object)
        InputStream istream = sock.getInputStream();
        BufferedReader receiveRead = new BufferedReader(new InputStreamReader(istream));

        String receiveMessage, sendMessage;               
        while(true)
        {
           sendMessage = keyRead.readLine();  // keyboard reading
           pwrite.println(sendMessage);       // sending to server
           pwrite.flush();                    // flush the data
           if((receiveMessage = receiveRead.readLine()) != null) //receive from server
           {
               System.out.println(receiveMessage); // displaying at DOS prompt
           }         
         }                                   
    }
    
    public static boolean tryParseInt(String value) {  
     try {  
         Integer.parseInt(value);  
         return true;  
      } catch (NumberFormatException e) {  
         return false;  
      }  
    }
}
