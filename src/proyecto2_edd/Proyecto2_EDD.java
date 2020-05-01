/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2_edd;

import org.json.simple.parser.ParseException;

/**
 *
 * @author lourd
 */
public class Proyecto2_EDD {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {
        arbolAVL avl = new arbolAVL();
        TablaHash hash = new TablaHash();
       
       LeerJson read = new LeerJson(avl, hash);
       ListaNodoRed list = new ListaNodoRed();
       
       list.RegistrarNodo("ip1");
       Bloque bloque = new Bloque();
       interfazSesion ven = new interfazSesion(read, avl,hash, bloque);
       ven.setVisible(true);
       
        
    }
    
}
