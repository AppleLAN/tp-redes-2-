/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sockettp;

import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
//Codigos de operacion para server y cliente: 
//1 - Mostrar Titulos
//2 - Mostrar noticia elegida
//3 - Cargar Noticia
/**
 *
 * @author AlanB
 */
public class SocketTP implements Runnable{

    /**
     */
    public static int PuertoServerTCP = 3000;
    public static String HostnameServerTCP = "localhost";
    
    public static void main(String[] args) throws Exception {
           SocketTP CLIENT = new SocketTP();
           CLIENT.run();
    }
    @Override
    public void run(){
        try {
            // TODO code application logic here
            Mensaje mensajeSocket = new Mensaje();
            Socket SOCK = new Socket(HostnameServerTCP,PuertoServerTCP);
            PrintStream PS = new PrintStream(SOCK.getOutputStream());
            Scanner keyboard = new Scanner(System.in);
            System.out.println("Escribi guachin");
            String keyboardString = keyboard.nextLine();
            mensajeSocket.setSocketIndex(SOCK);
            mensajeSocket.setKeyboard(keyboardString);
            PS.println(mensajeSocket);
            
            InputStreamReader IR = new InputStreamReader(SOCK.getInputStream());
            BufferedReader BR = new BufferedReader(IR);
            String Message = BR.readLine();
            
            System.out.println(Message);
        } catch (IOException ex) {
            Logger.getLogger(SocketTP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
