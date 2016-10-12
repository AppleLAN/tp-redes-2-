/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sockettp;

import common.Request;

/**
 *
 * @author juan.dapice
 */
public interface iCliente {
 
    public String Handle(Request req) throws Exception;
}
