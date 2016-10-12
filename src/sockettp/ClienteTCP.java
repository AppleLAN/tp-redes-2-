/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sockettp;

import common.Request;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.Socket;

/**
 *
 * @author juan.dapice
 */
public class ClienteTCP implements iCliente {
    
    public String serverName;
    public int port;
    
    public ClienteTCP(String servername, int port)
    {
       this.serverName = servername;
       this.port = port;
    }
    
    public String Handle(Request req) throws Exception {
        String response = "";
        
        try {
            Socket client = new Socket(serverName, port);
            ObjectOutputStream OOS = new ObjectOutputStream(client.getOutputStream());
            OOS.writeObject(req);
            
            InputStreamReader IR = new InputStreamReader(client.getInputStream());
            BufferedReader BR = new BufferedReader(IR);
            response = BR.readLine();
            
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return response;
    }
}
