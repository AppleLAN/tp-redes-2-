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
 * @author AlanB
 */
public class ServerTP {
    private final ArrayList<ServerObject> ConnectionArray = new ArrayList<ServerObject>();
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws Exception {
            ServerTP SERVER = new ServerTP();
            SERVER.run();
    }
    public void run() throws IOException, ClassNotFoundException {
        try{
            final int port = 3000;
            ServerSocket server = new ServerSocket(port);
            System.out.println("Servidor Escuchando por puerto " + String.valueOf(port));
            String player = "";
            while(true) {
                if(ConnectionArray.size() < 2){
                    Socket SOCK = server.accept();
                    ServerObject serverObject = new ServerObject(SOCK);

                    ConnectionArray.add(serverObject);
                
                    System.out.println("Client connected from: " + serverObject.getSOCK().getLocalAddress().getHostName());
                    Runnable  run = new HiloServidor(serverObject,ConnectionArray);
                    Thread hilo = new Thread(run);
                    hilo.start();
                    
                }
            }
        }
        catch (IOException x){ System.out.println("error");
            
        }
    }
}
