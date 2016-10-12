/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servertp;

import common.Noticia;
import java.util.ArrayList;

/**
 *
 * @author juan.dapice
 */
public class RepoNoticias {

    private ArrayList<Noticia> listaNoticias = new ArrayList<Noticia>();

    public void AgregarNoticia(Noticia noti) {
        listaNoticias.add(noti);
    }

    public String GetTitulos() {

        String titulos = "";
        for (int i = 0; i < listaNoticias.size(); i++) {
            titulos += i+1 + " - " + listaNoticias.get(i).getTitulo() + "$;";
        }

        return titulos;
    }

    public Boolean IndexExists(int id) {
        return id>0 && id<=listaNoticias.size();
    }

    public String GetNoticiaByIndex(int id) {
        return listaNoticias.get(id-1).getCuerpo();
    }
}
