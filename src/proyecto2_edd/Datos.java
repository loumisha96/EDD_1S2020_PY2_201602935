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
public class Datos {
    NodoDato primero;
    NodoDato ultimo;
    int op;
    Datos(){
        this.primero = null;
    }
    public void insertarDato(NodoDato nuevo){
        if(primero == null){
            primero = nuevo;
            primero.sig = nuevo;
            ultimo = nuevo;
            op = 0;
        }else{
            ultimo.sig = nuevo;
            ultimo = nuevo;
            op++;
        }
            
    }
    
    
}
    