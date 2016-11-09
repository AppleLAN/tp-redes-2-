/*
 * To change this license header, choose License Headers input Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template input the editor.
 */
package sockettp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author AlanB
 */
public class Cliente implements Runnable{
    public static int PuertoServerTCP;
    public static String HostnameServerTCP;
    private Socket cliente;
    private DataInputStream input;
    private DataOutputStream output;
    private JTable table;
    private boolean empezar = false;
    int random = 0 + (int)(Math.random() * 19);
    int posY = 1;
    int posX = random;
    int posYEnem = 0;
    int posXEnem = 0;
    int xbase = random;
    int xBaseEnem = 0;
    public Cliente(String HostnameServerTCP, int PuertoServerTCP, JTable table)
    {
        try {
            this.table = table;
            Cliente.HostnameServerTCP = HostnameServerTCP;
            Cliente.PuertoServerTCP = PuertoServerTCP;
            cliente = new Socket(HostnameServerTCP,PuertoServerTCP);
            input = new DataInputStream(cliente.getInputStream());
            output = new DataOutputStream(cliente.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void run(){
        table.setValueAt("[]",random,0);
        table.setValueAt("->",posX,posY);
        empezar = true;
        while(true){
            String[] accion = null;
            try {
                accion = input.readUTF().split(",");
            } catch (IOException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(accion != null){
                try {
                    switch (accion[0]) {
                        case "listo":
                            String number = Integer.toString(random);
                            {
                                try {
                                    enviarMensaje("posicionEnemigo," + number);
                                } catch (IOException ex) {
                                    Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                            {
                                try {
                                    enviarMensaje("misilEnemigo," + posX + "," + (19 - posY));
                                } catch (IOException ex) {
                                    Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                            break;
                        case "posicionEnemigo":
                            posXEnem = Integer.parseInt(accion[1]);
                            xBaseEnem = posXEnem;
                            posYEnem = 18;
                            table.setValueAt("[]",posXEnem,19);
                            table.setValueAt("<-",posXEnem,posYEnem);
                            
                            break;
                        case "movimiento":
                            table.setValueAt(null,posXEnem,posYEnem);
                            switch (accion[1]){
                                case "Up":
                                    if(posXEnem == 0)
                                        posXEnem = 19;
                                    else
                                        posXEnem--;
                                    break;
                                case "Right":
                                    if(posYEnem > 0)
                                        posYEnem--;
                                    break;
                                case "Down":
                                    if(posXEnem == 19)
                                        posXEnem = 0;
                                    else
                                        posXEnem++;
                                    break;
                                case "Ganaste":
                                    JOptionPane.showMessageDialog(null, "Ganaste", "InfoBox: " + "Fin Juego", JOptionPane.INFORMATION_MESSAGE);
                                    System.exit(0);

                                break;
                                case "Gane":
                                    JOptionPane.showMessageDialog(null, "Perdiste", "InfoBox: " + "Fin Juego", JOptionPane.INFORMATION_MESSAGE);
                                    System.exit(0);
                                break;
                                default:
                            }
                            if(posX == posXEnem && posY == posYEnem){
                                table.setValueAt(null,posX,posY);
                                table.setValueAt(null,posXEnem,posYEnem);
                                posX = xbase;
                                posY = 1;
                                posXEnem = xBaseEnem;
                                posYEnem = 18;
                                table.setValueAt("->",posX,posY);

                            }
                            if( posXEnem == xbase  && posYEnem == 0){
                                enviarMensaje("Ganaste, " );
                                JOptionPane.showMessageDialog(null, "Perdiste", "InfoBox: " + "Fin Juego", JOptionPane.INFORMATION_MESSAGE);
                                System.exit(0);

                            }
                            table.setValueAt("<-",posXEnem,posYEnem);
                            {
                                try {
                                    enviarMensaje("misilEnemigo," + posX + "," + (19 - posY));
                                } catch (IOException ex) {
                                    Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                            break;
                        default:
                            
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
                }
            }  
        } 
        
    }
    public void mover(String movement) throws IOException {
        String[] men = movement.split(",");
        table.setValueAt(null,posX,posY);
        switch (men[1]){
            case "Up":
                if(posX == 0)
                    posX = 19;
                else
                    posX--;
                break;
            case "Right":
                if(posY < 19)
                    posY++;
                break;
            case "Down":
                if(posX == 19)
                    posX = 0;
                else
                    posX++;
                break;
            default:
        }
        
        if(posX == posXEnem && posY == posYEnem){
            table.setValueAt(null,posX,posY);
            table.setValueAt(null,posXEnem,posYEnem);
            posX = xbase;
            posY = 1;
            posXEnem = xBaseEnem;
            posYEnem = 18;
            table.setValueAt("<-",posXEnem,posYEnem);
        }
        table.setValueAt("->",posX,posY);
        if( posX == xBaseEnem  && posY == 19){
            enviarMensaje("Gane, Filler" );
            JOptionPane.showMessageDialog(null, "Gane", "InfoBox: " + "Fin Juego", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }
    public void enviarMensaje (String men) throws IOException {
        output.writeUTF(men);
        output.flush();
    }
}
