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
public class NodoDeRed {
    String ip1;
    Blockchain bloque;
    ListaNodoRed listNodosRed;
    NodoDeRed Sig;
    NodoDeRed primero;
    NodoDeRed ultimo;
    int index;
    NodoDeRed(String ip1){
       this.ip1 = ip1;
       this.bloque = null;
       this.index =0;
       this.listNodosRed = new ListaNodoRed();
    }
    
    
}
