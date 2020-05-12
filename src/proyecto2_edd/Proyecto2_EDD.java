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
     
    
       public arbolAVL avl;
       public TablaHash hash;
       public Datos data;
       public Blockchain chain;
       public LeerJson read;
       public Servidor s ;
       public ListaNodoRed list;
    
     public Proyecto2_EDD(){
         avl = new arbolAVL();
         hash = new TablaHash();
         data = new Datos();
         chain = new Blockchain();
         read = new LeerJson(avl, hash, data);
         s  = new Servidor(5000, read);
         list = new ListaNodoRed();
     }
    public static void main(String[] args) throws ParseException, IOException {
       Proyecto2_EDD p = new Proyecto2_EDD();
       
       Thread t = new Thread(p.s);
      // p.read.CargaMasivaUsuarios("fd"); 
       t.start();
       //Cliente cliente = new Cliente();
       interfazSesion ven = new interfazSesion(p);
       ven.setVisible(true);
    }

    @Override
    public void update(Observable o, Object o1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
