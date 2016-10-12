/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servertp;

import common.Juego;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author juan.dapice
 */
public class RepoJuegos {

    private ArrayList<Juego> listaJuegos = new ArrayList<Juego>();

    public void AgregarJuego(Juego jueg) {
        listaJuegos.add(jueg);
    }

    public String MostrarJuegos() {

        String titulos = "";
        for (int i = 0; i < listaJuegos.size(); i++) {
            titulos += i+1 + " - " + listaJuegos.get(i).getTitulo() + "$;";
        }

        return titulos;
    }

    public Boolean IndexExists(Juego jueg) {
        return Arrays.asList(listaJuegos).contains(jueg);
    }
}
