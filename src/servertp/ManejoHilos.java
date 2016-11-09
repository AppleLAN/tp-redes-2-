package servertp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AlanB
 */
public class ManejoHilos implements Runnable {

    //Declaramos las variables que utiliza el hilo para estar recibiendo y mandando mensajes
    private Socket cliente;
    private DataInputStream in;
    private DataOutputStream out;
    private DataOutputStream clienteOut;
    private DataInputStream clienteIn;
    //Lista de los usuarios conectados al servidor
    private ArrayList<Socket> usuarios = new ArrayList<Socket>();

    //Constructor que recibe el socket que atendera el hilo y la lista de usuarios conectados
    public ManejoHilos(Socket soc,DataInputStream input,DataOutputStream output, ArrayList<Socket> users) {
        cliente = soc;
        usuarios = users;
        clienteIn = input;
        clienteOut = output;
    }

    @Override
    public void run() {
        try {
            //Inicializamos los canales de comunicacion y mandamos un mensaje de bienvenida
            in = clienteIn;
            out = clienteOut;
            boolean listos = false;
            //Ciclo infinito para escuchar por mensajes del cliente
            while (true) {     
                Thread.sleep(0);
                if (usuarios.size() == 2) { 
                    if(!listos){
                         //Cuando se recibe un mensaje se envia a todos los usuarios conectados 
                        for (int i = 0; i < usuarios.size(); i++) {
                            out =  new DataOutputStream(usuarios.get(i).getOutputStream());
                            out.writeUTF("listo,true");
                            out.flush();
                        }
                        listos = true;
                    }
                    else {
                        String men = in.readUTF();
                        if (cliente == usuarios.get(0)) {
                            out = new DataOutputStream(usuarios.get(1).getOutputStream());
                            out.writeUTF(men);
                            out.flush();
                        } else {
                            out = new DataOutputStream(usuarios.get(0).getOutputStream());
                            out.writeUTF(men);
                            out.flush();
                        }
                    }
                }

            }
        } catch (IOException e) {
            //Si ocurre un excepcion lo mas seguro es que sea por que el cliente se desconecto asi que lo quitamos de la lista de conectados
            for (int i = 0; i < usuarios.size(); i++) {
                if (usuarios.get(i) == cliente) {
                    usuarios.remove(i);
                    break;
                }
            }
            System.exit(0);
        } catch (InterruptedException ex) {
            Logger.getLogger(ManejoHilos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
