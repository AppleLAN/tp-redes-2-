/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sockettp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;

/**
 *
 * @author AlanB
 */
public class ClienteTP implements Runnable{
    public static int PuertoServerTCP;
    public static String HostnameServerTCP;
    private Socket cliente;
    private DataInputStream in;
    private DataOutputStream out;
    private JTable table;
    private boolean started = false;
    public ClienteTP(String HostnameServerTCP, int PuertoServerTCP, JTable table)
    {
        try {
            this.table = table;
            ClienteTP.HostnameServerTCP = HostnameServerTCP;
            ClienteTP.PuertoServerTCP = PuertoServerTCP;
            cliente = new Socket(HostnameServerTCP,PuertoServerTCP);
            in = new DataInputStream(cliente.getInputStream());
            out = new DataOutputStream(cliente.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(ClienteTP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void run(){
        int randomNum = 0 + (int)(Math.random() * 14);
        table.setValueAt("B",randomNum,0);
        int y = 1;
        int x = randomNum;
        table.setValueAt("M",x,y);
        started = true;
        int y2 = 0;
        int x2 = 0;
        while(true){
            String[] men = null;
            try {
                men = in.readUTF().split(",");
            } catch (IOException ex) {
                Logger.getLogger(ClienteTP.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(men != null){
                System.out.println(men[0] + " " + men[1]);
                switch (men[0]) {
                    case "ready":
                        String number = Integer.toString(randomNum);
                {
                    try {
                        sendMessage("pos2," + number);
                    } catch (IOException ex) {
                        Logger.getLogger(ClienteTP.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                {
                    try {
                        sendMessage("missile2," + x + "," + (19 - y));
                    } catch (IOException ex) {
                        Logger.getLogger(ClienteTP.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                        break;
                    case "pos2":
                        x2 = Integer.parseInt(men[1]) -1;
                        y2 = 19;
                        table.setValueAt("B",x2 + 1,y2);
                        table.setValueAt("E",x2,y2);
                        
                        break;
                    case "missile2":
                        table.setValueAt(null, x2, y2);
                        x2 = Integer.parseInt(men[1]);
                        y2 = Integer.parseInt(men[2]);
                        table.setValueAt("E",x2,y2);
                        break;
                    case "move":
                        table.setValueAt(null,x,y);
                        switch (men[1]){
                            case "Up":
                                if(x == 0)
                                    x = 19;
                                else
                                    x--;
                                break;
                            case "Right":
                                if(y < 19)
                                    y++;
                                break;
                            case "Down":
                                if(x == 19)
                                    x = 0;
                                else
                                    x++;
                                break;
                            default:
                        }
                        table.setValueAt("M1",x,y);
                {
                    try {
                        sendMessage("missile2," + x + "," + (19 - y));
                    } catch (IOException ex) {
                        Logger.getLogger(ClienteTP.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                        break;
                    default:

                }
            }  
        } 
        
    }
    public void sendMessage (String men) throws IOException {
        out.writeUTF(men);
    }
}
