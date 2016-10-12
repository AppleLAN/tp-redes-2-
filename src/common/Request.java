/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.io.Serializable;

/**
 *
 * @author juan.dapice
 */
public class Request implements Serializable{
    private int idOperacion;
    private Object payload;
    
    public Request(int id, Object payload){
        this.idOperacion = id;
        this.payload = payload;
    }

    /**
     * @return the idOperacion
     */
    public int getIdOperacion() {
        return idOperacion;
    }

    /**
     * @param idOperacion the idOperacion to set
     */
    public void setIdOperacion(int idOperacion) {
        this.idOperacion = idOperacion;
    }

    /**
     * @return the payload
     */
    public Object getPayload() {
        return payload;
    }

    /**
     * @param payload the payload to set
     */
    public void setPayload(Object payload) {
        this.payload = payload;
    }
}
