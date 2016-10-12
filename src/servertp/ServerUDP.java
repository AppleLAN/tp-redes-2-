/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servertp;

import common.Juego;
import common.Request;
import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.io.PrintStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import servertp.RepoJuegos;

/**
 *
 * @author gaston.mira
 */
public class ServerUDP {
    
    public ServerUDP() {
    
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        
        int port = 6007;
        Request req = null;
        RepoJuegos repo = new RepoJuegos();
        DatagramSocket serverUDP = new DatagramSocket(port);
        byte[] incomingData = new byte[1024];
        System.out.println("Servidor Escuchando por puerto " + String.valueOf(port));
        
        while(true) {
            DatagramPacket incomingPacket = new DatagramPacket(incomingData, incomingData.length);
            serverUDP.receive(incomingPacket);
            byte[] data = incomingPacket.getData();
            ByteArrayInputStream in = new ByteArrayInputStream(data);
            ObjectInputStream is = new ObjectInputStream(in);
            try { 
                req = (Request) is.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            InetAddress IPAddress = incomingPacket.getAddress();
            int portToSend = incomingPacket.getPort();
            String reply = "";
                if (req != null) {
                switch (req.getIdOperacion()) {
                    case 1:
                        reply = repo.MostrarJuegos();
                        break;
                    case 2:
                        if (req.getPayload() != null) {
                            repo.AgregarJuego((Juego) req.getPayload());
                            reply = "Juego agregado con exito";
                        } else {
                            reply = "Error agregando juego";
                        }
                        break;
                    default:
                        reply = "Codigo de operacion invalido";
                        break;
                }
                
                byte[] replyBytes = reply.getBytes();
                DatagramPacket replyPacket = new DatagramPacket(replyBytes, replyBytes.length, IPAddress, portToSend);
                serverUDP.send(replyPacket);
                
            } else {
                System.out.println("Request invalido");
            }
        }
    }
    
}
