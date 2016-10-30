/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servertp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import static javax.swing.text.html.HTML.Tag.BR;
import static jdk.nashorn.internal.parser.TokenKind.IR;

/**
 *
 * @author AlanB
 */
public class ServerObjects {
    private BufferedReader BR;
    private PrintStream PS;
    private Socket SOCK;
    
    ServerObjects(Socket SOCK) throws IOException {
        this.SOCK = SOCK;
        this.BR = new BufferedReader(new InputStreamReader(SOCK.getInputStream()));
        this.PS = new PrintStream(SOCK.getOutputStream());

    }

    /**
     * @return the BR
     */
    public BufferedReader getBR() {
        return BR;
    }

    /**
     * @param BR the BR to set
     */
    public void setBR(BufferedReader BR) {
        this.BR = BR;
    }

    /**
     * @return the PS
     */
    public PrintStream getPS() {
        return PS;
    }

    /**
     * @param PS the PS to set
     */
    public void setPS(PrintStream PS) {
        this.PS = PS;
    }

    /**
     * @return the SOCK
     */
    public Socket getSOCK() {
        return SOCK;
    }

    /**
     * @param SOCK the SOCK to set
     */
    public void setSOCK(Socket SOCK) {
        this.SOCK = SOCK;
    }
}

