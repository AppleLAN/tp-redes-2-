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
public class Server {
    private final ArrayList<Socket> ConnectionArray = new ArrayList<Socket>();
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws Exception {
            Server SERVER = new Server();
            SERVER.run();
    }
    public void run() throws IOException, ClassNotFoundException {
        try{
            final int port = 3000;
            ServerSocket server = new ServerSocket(port);
            System.out.println("Servidor Escuchando por puerto " + String.valueOf(port));
            while(true) {
                if(ConnectionArray.size() < 2){
                    Socket cliente = server.accept();
                    DataInputStream input;
                    DataOutputStream output;
                    input = new DataInputStream(cliente.getInputStream());
                    output = new DataOutputStream(cliente.getOutputStream());
                    ConnectionArray.add(cliente);
                    Runnable  run = new ManejoHilos(cliente,input,output,ConnectionArray);
                    Thread hilo = new Thread(run);
                    hilo.start();
                    
                }
            }
        }
        catch (IOException x){ System.out.println("error");
            
        }
    }
}
