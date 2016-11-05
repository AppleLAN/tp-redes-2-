/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sockettp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author AlanB
 */
public class ClienteTP implements iCliente{
    public static int PuertoServerTCP;
    public static String HostnameServerTCP;
    public ClienteTP(String HostnameServerTCP, int PuertoServerTCP)
    {
       ClienteTP.HostnameServerTCP = HostnameServerTCP;
       ClienteTP.PuertoServerTCP = PuertoServerTCP;
    }
    @Override
    public String Handle(Mensaje men) throws Exception {
        String response = "";
        
        try {
                Socket client = new Socket(HostnameServerTCP, PuertoServerTCP);
                ObjectOutputStream OOS = new ObjectOutputStream(client.getOutputStream());
                OOS.writeObject(men);
                
                InputStreamReader IR = new InputStreamReader(client.getInputStream());
                BufferedReader BR = new BufferedReader(IR);
                response = BR.readLine();
            
        } catch (IOException e) {
            System.out.println(e);
        }
        
        return response;
    }
}
