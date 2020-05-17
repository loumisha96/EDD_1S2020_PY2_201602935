/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2_edd;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
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
       public  static ListaNodoRed list;
    
     public Proyecto2_EDD(){
         avl = new arbolAVL();
         hash = new TablaHash();
         data = new Datos();
         chain = new Blockchain();
         read = new LeerJson(avl, hash, data);
         
         list = new ListaNodoRed();
         s  = new Servidor(6000, list);
     }
    public static void main(String[] args) throws ParseException, IOException {
       /*Proyecto2_EDD p = new Proyecto2_EDD();
       File archivo = new File("Json");
        if (archivo.isDirectory()) {
            //List<String> res = new ArrayList<>();
            File[] cont = archivo.listFiles();
            for(int i = 0; i< cont.length; i++){
                p.read.LeerJsonGeneral(cont[i]);
                p.data = new Datos();
            }
        
        }
        Thread t = new Thread(p.s);
        t.start();
        Cliente cliente = new Cliente(6000,"Cliente1", list);
        cliente.run();
        /*Cliente cliente2 = new Cliente(6000,"Cliente2", list);
        cliente2.run();
        Cliente cliente3 = new Cliente(6000,"Cliente3", list);
        cliente3.run();*/
         //interfazSesion ven = new interfazSesion(p);
         //ven.setVisible(true);
        
    }

    @Override
    public void update(Observable o, Object o1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
