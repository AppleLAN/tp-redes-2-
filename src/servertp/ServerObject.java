/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servertp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintStream;
import java.io.Serializable;
import java.net.Socket;
import static javax.swing.text.html.HTML.Tag.BR;

/**
 *
 * @author AlanB
 */
public class ServerObject implements Serializable{
    private DataInputStream DI;
    private DataOutputStream DO;
    private Socket SOCK;
    
    ServerObject(Socket SOCK) throws IOException {
        this.SOCK = SOCK;
        this.DI = new DataInputStream(SOCK.getInputStream());
        this.DO = new DataOutputStream(SOCK.getOutputStream());
    }

    public DataInputStream getDI() {
        return DI;
    }

    public void setDI(DataInputStream DI) {
        this.DI = DI;
    }

    public DataOutputStream getDO() {
        return DO;
    }

    public void setDO(DataOutputStream DO) {
        this.DO = DO;
    }

    public Socket getSOCK() {
        return SOCK;
    }

    public void setSOCK(Socket SOCK) {
        this.SOCK = SOCK;
    }

    
}

