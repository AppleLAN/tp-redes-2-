/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sockettp;

import java.io.*;
import java.net.*;
import java.util.Scanner;

//Codigos de operacion para server y cliente: 
//1 - Mostrar Titulos
//2 - Mostrar noticia elegida
//3 - Cargar Noticia
/**
 *
 * @author gastonmira
 */
public class SocketTP implements Runnable{

    /**
     */
    public static int PuertoServerTCP = 444;
    public static String HostnameServerTCP = "localhost";
    
    public static void main(String[] args) throws Exception {
           SocketTP CLIENT = new SocketTP();
           CLIENT.run();
    }
    public void run() throws Exception {
        // TODO code application logic here
        Socket SOCK = new Socket(HostnameServerTCP,PuertoServerTCP);
        PrintStream PS = new PrintStream(SOCK.getOutputStream());
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Escribi guachin");
        String myint = keyboard.nextLine();

        PS.println(myint);
        
        InputStreamReader IR = new InputStreamReader(SOCK.getInputStream());
        BufferedReader BR = new BufferedReader(IR);
        String Message = BR.readLine();

        System.out.println(Message);
    }
}
