package servertp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AlanB
 */
public class HiloServidor implements Runnable {

    //Declaramos las variables que utiliza el hilo para estar recibiendo y mandando mensajes
    private ServerObject serverObj;
    private DataInputStream in;
    private DataOutputStream out;
    //Lista de los usuarios conectados al servidor
    private ArrayList<ServerObject> usuarios = new ArrayList<ServerObject>();

    //Constructor que recibe el socket que atendera el hilo y la lista de usuarios conectados
    public HiloServidor(ServerObject soc, ArrayList<ServerObject> users) {
        serverObj = soc;
        usuarios = users;
    }

    @Override
    public void run() {
        try {
            //Inicializamos los canales de comunicacion y mandamos un mensaje de bienvenida
            in = serverObj.getDI();
            out = serverObj.getDO();
            boolean tellReady = false;
            //Ciclo infinito para escuchar por mensajes del cliente
            while (true) {     
                Thread.sleep(0);
                if (usuarios.size() == 2) { 
                    if(!tellReady){
                         //Cuando se recibe un mensaje se envia a todos los usuarios conectados 
                        for (int i = 0; i < usuarios.size(); i++) {
                            out = usuarios.get(i).getDO();
                            out.writeUTF("ready,true");
                            out.flush();
                        }
                        tellReady = true;
                    }
                    else {
                        String men = in.readUTF();
                        if (serverObj.getSOCK() == usuarios.get(0).getSOCK()) {
                            out = usuarios.get(1).getDO();
                            out.writeUTF(men);
                            out.flush();
                        } else {
                            out = usuarios.get(0).getDO();
                            out.writeUTF(men);
                            out.flush();
                        }
                    }
                }

            }
        } catch (IOException e) {
            //Si ocurre un excepcion lo mas seguro es que sea por que el cliente se desconecto asi que lo quitamos de la lista de conectados
            for (int i = 0; i < usuarios.size(); i++) {
                if (usuarios.get(i) == serverObj) {
                    usuarios.remove(i);
                    break;
                }
            }
            System.exit(0);
        } catch (InterruptedException ex) {
            Logger.getLogger(HiloServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
