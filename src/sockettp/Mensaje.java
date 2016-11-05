/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sockettp;

import java.io.Serializable;

/**
 *
 * @author AlanB
 */
public final class Mensaje implements Serializable{

    private static iCliente socketIndex;
    private static String keyboard;

    /**
     * @return the socketIndex
     */
    public static iCliente getSocket() {
        return socketIndex;
    }

    /**
     * @param socketIndex the socketIndex to set
     */
    public static void setSocketIndex(iCliente socketIndex) {
        Mensaje.socketIndex = socketIndex;
    }

    /**
     * @return the keyboard
     */
    public static String getKeyboard() {
        return keyboard;
    }

    /**
     * @param keyboard the keyboard to set
     */
    public static void setKeyboard(String keyboard) {
        Mensaje.keyboard = keyboard;
    }
}

