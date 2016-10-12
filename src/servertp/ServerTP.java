/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servertp;

import common.Noticia;
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
        RepoNoticias repo = new RepoNoticias();
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
                        PS.println(repo.GetTitulos());
                        break;
                    case 2:
                        if (req.getPayload() != null) {
                            int id = (Integer)req.getPayload();
                            if (repo.IndexExists(id)) {
                                PS.println(repo.GetNoticiaByIndex(id));
                            } else {
                                PS.println("Indice invalido");
                            }
                        }else{
                            PS.println("Error mostrando noticia");
                        }
                        break;
                    case 3:
                        if (req.getPayload() != null) {
                            repo.AgregarNoticia((Noticia) req.getPayload());
                            PS.println("Noticia agregada con exito");
                        } else {
                            PS.println("Error agregando noticia");
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
