/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2_edd;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

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
           /* NodoDeRed ant = primero; 
            while(aux.Sig != null){
                ant = aux;
                aux = aux.Sig;
                aux.listNodosRed = ant.listNodosRed;//lista de aux = a la lista del anterior
            }*/
            
            ultimo.Sig = nuevo;
            nuevo.listNodosRed = primero.listNodosRed;
            ultimo = nuevo;
            tam++;
        }
        
    }
    public void eliminarIP(String ip){
        EliminarNodo(ip);
        primero.listNodosRed.EliminarNodo(ip);//elimino la ip de la lista del primer nodo
        /*NodoDeRed aux = primero;
        NodoDeRed ant = primero; 
            while(aux.Sig != null){
                ant = aux;
                aux = aux.Sig;
                aux.listNodosRed = ant.listNodosRed;//empiezo a actualizar las listas
            }
            aux.listNodosRed = ant.listNodosRed;//lista de aux = a la lista del anterior*/
        
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
    public void InsertarBloqueNuevo(Bloque bloque) throws InterruptedException{
        NodoDeRed aux = primero;
        while(aux != null){
            aux.bloque.NuevoBloque(bloque);
            aux = aux.Sig;
        }
        aux.bloque.NuevoBloque(bloque);
            
    }
    public String Reporte() throws IOException{
        FileWriter file = new FileWriter("ReporteIp.dot");
        try (BufferedWriter reporte = new BufferedWriter(file)) {
            reporte.write("digraph G{\n");
            reporte.write("rankdir = LR;\n");
            reporte.write("node [shape= record];\n");
            NodoDeRed aux = primero;
            for(int i= 0; i<=tam; i++){
                if(aux.Sig != null){
                    reporte.write(i);
                    reporte.write("[label = \"{<ref> | <data>");
                    reporte.write(aux.ip1);
                    reporte.write(" | }\"]\n");
                    reporte.write(i+1);
                    reporte.write("[label = \"{<ref> | <data>");
                    reporte.write(aux.Sig.ip1);
                    reporte.write(" | }\"]\n");
                    reporte.write(i);
                    reporte.write("->");
                    reporte.write(i+1+ "\n");
                    aux = aux.Sig;
                }else  if(i==tam && aux != null && aux.Sig == null){
                    reporte.write(i);
                    reporte.write("[label = \"{<ref> | <data>");
                    reporte.write(aux.ip1);
                    reporte.write(" | }\"]\n");
                    
                }
            }
            reporte.write("}");
            reporte.close();
            ProcessBuilder p;
            p = new ProcessBuilder("dot", "-Tpng", "-o", "ReporteIp.png", "ReporteIp.dot");
            p.redirectErrorStream(true);
            p.start();
            return "ReporteIp.png";
        }
    }
}


