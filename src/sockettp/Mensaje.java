/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sockettp;

import java.net.Socket;

/**
 *
 * @author AlanB
 */
public class Mensaje {
    private Socket socketIndex;
    private String keyboard;

    /**
     * @return the socketIndex
     */
    public Socket getSocket() {
        return socketIndex;
    }

    /**
     * @param socketIndex the socketIndex to set
     */
    public void setSocketIndex(Socket socketIndex) {
        this.socketIndex = socketIndex;
    }

    /**
     * @return the keyboard
     */
    public String getKeyboard() {
        return keyboard;
    }

    /**
     * @param keyboard the keyboard to set
     */
    public void setKeyboard(String keyboard) {
        this.keyboard = keyboard;
    }
}
