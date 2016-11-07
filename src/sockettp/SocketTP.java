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
            ClienteTP cliente = new ClienteTP(HostnameServerTCP, PuertoServerTCP);
            // TODO code application logic here
            Mensaje mensajeSocket = new Mensaje();
            Scanner keyboard = new Scanner(System.in);
            System.out.println("Escribi ");
            String keyboardString = keyboard.nextLine();
            mensajeSocket.setKeyboard(keyboardString);
            String Handle;
            cliente.Handle(mensajeSocket);
        } catch (Exception ex) {
            Logger.getLogger(SocketTP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
