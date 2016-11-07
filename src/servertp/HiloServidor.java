package servertp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import sockettp.Mensaje;

/**
 *
 * @author AlanB
 */
public class HiloServidor implements Runnable {

    //Declaramos las variables que utiliza el hilo para estar recibiendo y mandando mensajes
    private ServerObject serverObj;
    private ObjectInputStream in;
    private PrintStream out;
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
            in = serverObj.getBR();
            out = serverObj.getPS();
            out.println("Bienvenido...");
            //Ciclo infinito para escuchar por mensajes del cliente
            while (true) {
                if (usuarios.size() < 2) {
                    out.println("espera al otro...");
                } else {
                    Mensaje men = (Mensaje) in.readObject();
                    //Cuando se recibe un mensaje se envia a todos los usuarios conectados 
                    if (serverObj.getSOCK() == usuarios.get(0).getSOCK()) {
                        out = usuarios.get(1).getPS();
                        out.println(men.getKeyboard());
                    } else {
                        out = usuarios.get(0).getPS();
                        out.println(men.getKeyboard());
                    }
                    System.out.println(men);

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
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HiloServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
