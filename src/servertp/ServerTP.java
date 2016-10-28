/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servertp;

import java.io.*;
import java.net.*;
import java.util.*;
/**
 *
 * @author gaston.mira
 */
public class ServerTP {
    public static ArrayList<Socket> ConnectionArray = new ArrayList<Socket>();
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws Exception {
            ServerTP SERVER = new ServerTP();
            SERVER.run();
    }
    public void run() throws IOException {
        try{
            final int port = 444;
            ServerSocket server = new ServerSocket(port);
            System.out.println("Servidor Escuchando por puerto " + String.valueOf(port));
            
            while(true) {
                Socket SOCK = server.accept();
                ConnectionArray.add(SOCK);
                
                System.out.println("Client connected from: " + SOCK.getLocalAddress().getHostName());

                // reading from keyboard (keyRead object)
                InputStreamReader IR = new InputStreamReader(SOCK.getInputStream());
                BufferedReader BR = new BufferedReader(IR);
                // sending to client (pwrite object)       
                String Message = BR.readLine();
                System.out.println(Message);
                    PrintStream PS = new PrintStream(SOCK.getOutputStream());
                    PS.println(Message + " tomado con exito por parte del server");
            }
        }
        catch (IOException x){ System.out.println("error");
            
        }
    }
}
