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
public class Nodo {
    public ArbolB Btree;
    
    public String categoria;
    public int fe;
    public Nodo izq, der;
    public int h;
    public Nodo(String categoria){
        this.categoria = categoria;
        this.fe =0;
        this.izq = this.der = null;
        this.Btree = new ArbolB(5);
    }
    
}
