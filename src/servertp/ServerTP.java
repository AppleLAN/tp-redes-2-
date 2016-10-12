/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servertp;

import common.Juego;
import common.Request;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;

/**
 *
 * @author gaston.mira
 */
public class ServerTP {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {

        int port = 6006;
        RepoJuegos repo = new RepoJuegos();
        ServerSocket server = new ServerSocket(port);
        System.out.println("Servidor Escuchando por puerto " + String.valueOf(port));

        while (true) {
            Socket clientSocket = server.accept();
            ObjectInputStream OIS = new ObjectInputStream(clientSocket.getInputStream());
            Request req = (Request) OIS.readObject();

            PrintStream PS = new PrintStream(clientSocket.getOutputStream());

            if (req != null) {
                switch (req.getIdOperacion()) {
                    case 1:
                        PS.println(repo.MostrarJuegos());
                        break;
                    case 2:
                        if (req.getPayload() != null) {
                            if (false == repo.IndexExists((Juego) req.getPayload())) {
                                repo.AgregarJuego((Juego) req.getPayload());
                                PS.println("Juego agregado con exito");
                            } else {
                                PS.println("juego ya existe");
                            }
                            
                        } else {
                            PS.println("Error agregando juego");
                        }
                        break;
                    default:
                        PS.println("Codigo de operacion invalido");
                        break;
                }
            } else {
                PS.println("Request invalido");
            }
        }
    }
}
