
package servertp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

/**
 *
 * @author AlanB
 */
public class HiloServidor implements Runnable{
    //Declaramos las variables que utiliza el hilo para estar recibiendo y mandando mensajes
    private ServerObject serverObj;
    private BufferedReader in;
    private PrintStream out;
    //Lista de los usuarios conectados al servidor
    private ArrayList<ServerObject> usuarios = new ArrayList<ServerObject>();
    
    //Constructor que recibe el socket que atendera el hilo y la lista de usuarios conectados
    public HiloServidor(ServerObject soc,ArrayList<ServerObject> users){
        serverObj = soc;
        usuarios = users;
    }
    
    
    @Override
    public void run() {
        try {
            //Inicializamos los canales de comunicacion y mandamos un mensaje de bienvenida
            in = serverObj.getBR();
            out = serverObj.getPS();
            out.println("<h2>Bienvenido....</h2>");
            //Ciclo infinito para escuchar por mensajes del cliente
            while(true){
               String recibido = in.readLine();
               //Cuando se recibe un mensaje se envia a todos los usuarios conectados 
                for (int i = 0; i < usuarios.size(); i++) {
                    out = usuarios.get(i).getPS();
                    out.println(recibido);
                }
            }
        } catch (IOException e) {
            //Si ocurre un excepcion lo mas seguro es que sea por que el cliente se desconecto asi que lo quitamos de la lista de conectados
            for (int i = 0; i < usuarios.size(); i++) {
                if(usuarios.get(i) == serverObj){
                    usuarios.remove(i);
                    break;
                } 
            }
        }
    }
}
