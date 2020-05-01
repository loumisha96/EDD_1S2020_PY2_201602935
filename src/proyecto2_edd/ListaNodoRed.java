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
public class ListaNodoRed {
    NodoDeRed primero;
    NodoDeRed ultimo;
    int tam;
    ListaNodoRed(){
        this.primero = null;
        this.ultimo = null;
    }
    public void RegistrarNodo(String ip1){
        NodoDeRed nuevo = new NodoDeRed(ip1);
        NodoDeRed aux = primero;
        if(primero == null){
            primero = nuevo;
            ultimo = nuevo;
            tam++;
        }else{
            primero.listNodosRed.RegistrarNodo(ip1);//inserto la ip en el primero nodo
            NodoDeRed ant = primero; 
            while(aux.Sig != null){
                ant = aux;
                aux = aux.Sig;
                aux.listNodosRed = ant.listNodosRed;//lista de aux = a la lista del anterior
            }
            aux.Sig = nuevo;
            nuevo.listNodosRed = aux.listNodosRed;
            tam++;
        }
        
    }
    public void eliminarIP(String ip){
        EliminarNodo(ip);
        primero.listNodosRed.EliminarNodo(ip);//elimino la ip de la lista del primer nodo
        NodoDeRed aux = primero;
        NodoDeRed ant = primero; 
            while(aux.Sig != null){
                ant = aux;
                aux = aux.Sig;
                aux.listNodosRed = ant.listNodosRed;//empiezo a actualizar las listas
            }
            aux.listNodosRed = ant.listNodosRed;//lista de aux = a la lista del anterior
        
    }
    public void EliminarNodo(String ip){
        NodoDeRed aux = primero;
        NodoDeRed aux2 = primero;
        while ((aux.ip1 == null ? ip != null : !aux.ip1.equals(ip)) && aux.Sig != null){
            aux2 = aux;
            aux = aux.Sig;
        }
        if(aux==primero){
            primero = aux.Sig;
            aux = null;
        }
         else if(aux == ultimo){
             aux2.Sig = null;
             ultimo = aux2;
        }
         else
             aux2.Sig = aux.Sig;
        tam--;
    }
    public void InsertarBloqueNuevo(Bloque bloque){
        NodoDeRed aux = primero;
        while(aux != null){
            aux.bloque.NuevoBloque(bloque);
            aux = aux.Sig;
        }
        aux.bloque.NuevoBloque(bloque);
            
    }
}
