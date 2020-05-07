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
           // primero.sig = nuevo;
            op = 0;
        }else{
            NodoDato aux = primero;
            while(aux.sig != null){
                aux = aux.sig;
            }
            aux.sig = nuevo;
            op++;
        }
          System.out.println("******************"); 
          //print();
    }
    public void print(){
        NodoDato aux = primero;
        while (aux.sig != null){
            System.out.println(aux.tipo);
            aux = aux.sig;
        }
        System.out.println(aux.tipo);
        
    }
    
    
}
    