/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2_edd;

import java.io.File;
import java.io.IOException;

import org.json.simple.parser.ParseException;

/**
 *
 * @author lourd
 */
public class main {
    public static void main(String[] args) throws ParseException, IOException {
       Proyecto2_EDD p = new Proyecto2_EDD();
       File archivo = new File("Json");
       
        if (archivo.isDirectory()) {
            //List<String> res = new ArrayList<>();
            File[] cont = archivo.listFiles();
            
           for (File cont1 : cont) {
               
               p.read.LeerJsonGeneral(cont1);
               p.data = new Datos();
           }
        
        }
        //Thread t = new Thread(p.s);
        //t.start();
        /*Cliente cliente = new Cliente(6000,"Cliente1", p.list);
        cliente.run();*/
        /*Cliente cliente2 = new Cliente(6000,"Cliente2", list);
        cliente2.run();
        Cliente cliente3 = new Cliente(6000,"Cliente3", list);
        cliente3.run();*/
         interfazSesion ven = new interfazSesion(p);
         ven.setVisible(true);
        
    }
}
