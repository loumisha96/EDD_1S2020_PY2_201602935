/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2_edd;

/**
 *
 * @author lourd
 */
public class Pagina {
    
    Libro[] claves;
    Pagina[] ramas;
    boolean clavesLlenas;
    Pagina pagPadre;
    boolean tengoHijos;
    boolean clavesMin;
    
    public Pagina(int orden){
        this.claves = new Libro[orden-1];
        this.ramas = new Pagina[orden];
        clavesLlenas = false;
        pagPadre = null;
        tengoHijos = false;
        clavesMin = false;
        for(int k=0; k< orden; k++)
           this.ramas[k]=null;
    }
}
