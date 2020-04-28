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
        // TODO code application logic here
       Usuario userLog =  new Usuario(201602935, "lourdes", "Lorenzana","ingenieria","2121");
       arbolAVL avl = new arbolAVL();
       TablaHash hash = new TablaHash();
       LeerJson read = new LeerJson(avl, hash, userLog);
       read.CargaMasivaLibros("kc");
       Interfaz i = new Interfaz(read, userLog);
       i.setVisible(true);
       //interfazSesion ven = new interfazSesion(read, userLog);
       //ven.setVisible(true);
       /*Interfaz i = new Interfaz(read);
       i.setVisible(true);*/
       
       
        /*Libro book = new Libro(5281,2018,  "title1", "UNO", "editorial", 8, "Novela", 201602935);
        Libro book1 = new Libro(5282,2020, "title2", "DOS", "editorial",9, "Terror", 201602936);
        Libro book3 = new Libro(5283,2020, "title3", "TRES", "editorial", 7,"Terror", 201602937);
        Libro book4 = new Libro(5285, 2020,"title4", "cuatro", "editorial",3,"Arroz", 201602937);*/
        //
       /* avl.insertar(book);
        avl.insertar(book1);
        avl.insertar(book3);
        avl.insertar(book4);
        System.out.println("prueba");
        avl.inorden(avl.raiz);*/
        
    }
    
}
