/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2_edd;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import org.json.simple.parser.ParseException;

/**
 *
 * @author lourd
 */
public class Proyecto2_EDD implements Observer{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException, IOException {
        
       arbolAVL avl = new arbolAVL();
       TablaHash hash = new TablaHash();
       Datos data = new Datos();
       LeerJson read = new LeerJson(avl, hash, data);
       Servidor s  = new Servidor(5000, read);
       Thread t = new Thread(s);
       t.start();
       ListaNodoRed list = new ListaNodoRed();
       interfazSesion ven = new interfazSesion(read, avl,hash, data, list);
       ven.setVisible(true);
    }

    @Override
    public void update(Observable o, Object o1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
