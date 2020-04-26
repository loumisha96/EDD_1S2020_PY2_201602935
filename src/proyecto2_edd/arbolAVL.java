/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2_edd;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author lourd
 */
public class arbolAVL {
    public Nodo raiz;
    public arbolAVL(){
        raiz = null;
    }
    
    public Nodo buscar(String d, Nodo actual){
        if(raiz == null)
            return null;
        else if(actual.categoria.compareToIgnoreCase(d)==0)
            return actual;
        else if(actual.categoria.compareToIgnoreCase(d)<0)
            return buscar(d, actual.der);
        else
            return buscar(d,actual.izq);
    }
    public int geth(Nodo actual){
        if(actual==null)
            return -1;
        else
            return actual.h;
    }
    public void insertar(Libro libro){
        Nodo nuevo = new Nodo(libro.categoria);
        if(raiz == null){
            raiz = nuevo;
            raiz.Btree.insert(libro);
        }
        else
            raiz = insertar(nuevo, raiz,libro);
        
    }
    private Nodo insertar(Nodo nuevo, Nodo subA, Libro libro){
        Nodo nuevoPadre = subA;
        if(nuevo.categoria.compareToIgnoreCase(subA.categoria)<0){
            if(subA.izq == null)
               subA.izq = nuevo;
            else{
                subA.izq = insertar(nuevo, subA.izq, libro);
                if(geth(subA.izq)-geth(subA.der)==2){
                    if(nuevo.categoria.compareToIgnoreCase(subA.izq.categoria)<0)
                        nuevoPadre = RotacionSimpleIzquierda(subA);
                    else
                        nuevoPadre = RotacionDobleIzquierda(subA);
                }
                subA.izq.Btree.insert(libro);
            }
           
        }else if(nuevo.categoria.compareToIgnoreCase(subA.categoria)>0){
            if(subA.der == null){
                subA.der = nuevo;
                subA.der.Btree.insert(libro);
            }
                
            else{
                subA.der = insertar(nuevo, subA.der, libro);
                if(geth(subA.der)-geth(subA.izq)==2){
                    if(nuevo.categoria.compareToIgnoreCase(subA.der.categoria)>0)
                        nuevoPadre = RotacionSimpleDerecha(subA);
                    else
                        nuevoPadre = RotacionDobleDerecha(subA);
                }
            }
             
        }else
            subA.Btree.insert(libro);
        //actualizar alturas{
         if(subA.izq == null && subA.der !=null)
             subA.h = subA.der.h+1;
         else if(subA.der == null && subA.izq != null)
             subA.h = subA.izq.h +1;
         else
             subA.h = Math.max(geth(subA.izq), geth(subA.der))+1;
         return nuevoPadre;
             
            
    }
    public boolean Eliminar(Libro libro){
        Nodo aux = raiz;
        Nodo padre = raiz;
        boolean esHIzq = true;
        while(!aux.categoria.equals(libro.categoria)){
            padre = aux;
            if(libro.categoria.compareToIgnoreCase(aux.categoria)<0){
                esHIzq = true;
                aux = aux.izq;
            }else{
                esHIzq = false;
                aux = aux.der;
            }
            if(aux == null){
                return false;
            }
        }
        if(aux.izq == null && aux.der == null){
            if(aux == raiz)
                raiz = null;
            else if(esHIzq)
                padre.izq =null;
            else
                padre.der =null;
            
        }else if(aux.der == null){
            if(aux == raiz)
                raiz = aux.izq;
            else if(esHIzq)
                padre.izq =aux.izq;
            else
                padre.der =aux.izq;
        }else if(aux.izq == null){
            if(aux == raiz)
                raiz = aux.der;
            else if(esHIzq)
                padre.izq =aux.der;
            else
                padre.der =aux.der;
        }else{
            Nodo reemplazo = getReemplazo(aux);
            if(aux == raiz)
                raiz = reemplazo;
            else if(esHIzq)
                padre.izq = reemplazo;
            else
                padre.der = reemplazo;
            reemplazo.izq = aux.izq;
        }
        
        actualizarh(raiz);
        return true;
    }
    public void actualizarh(Nodo actual){
      if(actual != null){
            actualizarh(actual.izq);
            actualizarh(actual.der);
            if(actual.der == null && actual.izq == null){
                actual.h = 0;
            }
            else if(actual.der == null){
                actual.h =actual.izq.h +1;
            }
            else if(actual.izq == null){
                actual.h =actual.der.h +1;
            }
            else{
                if(actual.der.h >actual.izq.h)
                    actual.h = actual.der.h +1;
                else
                    actual.h = actual.izq.h +1;
            }
            balanceo(actual);
            
        }  
    }
    public int getFe(Nodo actual){
        if(actual.der == null && actual.izq == null){
            actual.fe = 0;
        }
        else if(actual.der == null){
            actual.fe =geth(actual.izq)+1;
        }
        else if(actual.izq == null){
            actual.fe =0-geth(actual.der) ;
        }
        else{
            actual.fe = (geth(actual.izq)+1)-(geth(actual.der)+1);
        }
        return actual.fe;
    }
    public void balanceo(Nodo actual){
        Nodo aux;
        if((geth(actual.izq)-geth(actual.der))==2){//positivo
            if(getFe(actual.izq)==-1 && getFe(actual.izq.der)==0)
                RotacionDobleIzquierda(actual);
            else if(getFe(actual.izq)==1 && getFe(actual.izq.izq)==0){
                RotacionSimpleIzquierda(actual);
            }
        }else if(geth(actual.der)-geth(actual.izq)==2){
            if(getFe(actual.der) ==(-1) && getFe(actual.der.izq) == 0){
                RotacionDobleDerecha(actual);//listo
            }
            else if(getFe(actual.der)==-1 && getFe(actual.der.der)==0)
                RotacionSimpleDerecha(actual);
            else if(getFe(actual.der)==0 && getFe(actual.der.der)==0)
                RotacionSimpleDerecha(actual);
        }
        
    }
    public Nodo getReemplazo(Nodo actual){
        Nodo reemPadre = actual;
        Nodo reemplazo = actual;
        Nodo aux = actual.der;
        while(aux != null){
            reemPadre = reemplazo;
            reemplazo = aux;
            aux = aux.izq;
        }
        if(reemplazo != actual.der){
            reemPadre.izq = reemplazo.der;
            reemplazo.der = actual.der;
        }
        System.out.println("Reemplazo"+ reemplazo);
        return reemplazo;
    }
    public Nodo RotacionSimpleDerecha(Nodo actual){
        Nodo aux = actual.der;
        actual.der = aux.izq;
        aux.izq = actual;
        actual = aux;
        actual.h = Math.max(geth(actual.izq), geth(actual.der))+1;
        aux.h=Math.max(geth(aux.izq), geth(aux.der))+1;
        return aux;
    }
    
    public Nodo RotacionSimpleIzquierda(Nodo actual){
        Nodo aux = actual.izq;
        actual.izq = aux.der;
        aux.der = actual;
        actual = aux;
        actual.h = Math.max(geth(actual.izq), geth(actual.der))+1;
        aux.h=Math.max(geth(aux.izq), geth(aux.der))+1;
        return aux;
    }
    public Nodo RotacionDobleDerecha(Nodo actual){
        Nodo aux;
        actual.der = RotacionSimpleIzquierda(actual.der);
        aux = RotacionSimpleDerecha(actual);
        if(actual == raiz)
            raiz = aux;
        else
            actual = aux;
        return aux;
    }
    public Nodo RotacionDobleIzquierda(Nodo actual){
        Nodo aux;
        actual.izq = RotacionSimpleDerecha(actual.izq);
        aux = RotacionSimpleIzquierda(actual);
        return aux;
    }
    public void inorden(){
        inorden(raiz);
    }
    public void Ginorden(DefaultTableModel tabla, int k){
        Ginorden(raiz, tabla, k);
    }
    public void Ginorden(Nodo actual, DefaultTableModel tabla, int k){
        if(actual != null){
            Ginorden(actual.izq, tabla, k);
            
            //System.out.println("CATEGORIA " +actual.categoria );
          //System.out.println(" ");
            actual.Btree.Gprint(tabla, k);
            Ginorden(actual.der, tabla, k);
        }
    }
    public void inorden(Nodo actual){
        if(actual != null){
            inorden(actual.izq);
            System.out.println("CATEGORIA " +actual.categoria );
            System.out.println(" ");
            actual.Btree.print();
            inorden(actual.der);
        }
    }
    public void PreOrden(Nodo actual){
        if(actual != null){
            System.out.print(actual.categoria+ ",");
            PreOrden(actual.izq);
            PreOrden(actual.der);
        }
    }
    public void PostOrden(Nodo actual){
        if(actual != null){
            PostOrden(actual.izq);
            PostOrden(actual.der);
            System.out.print(actual.categoria+ ",");
        }
    }
    public  BufferedWriter report;
    public void reporte() throws IOException{
        FileWriter file = new FileWriter("Reporte.dot");
        report = new BufferedWriter(file);
        
        report.write("digraph G{");
        report.write("node[shape=circle, style=filled, color = Gray95];");
        report.write("\n");
        reporte(raiz);
        report.write("}");
        report.close();
    }
    int ent = 0;
    String num;
    public void reporte(Nodo actual) throws IOException{
        num = String.valueOf(ent);
        
        if(actual.izq != null){
            report.write(actual.categoria);
            report.write("->");
            report.write(actual.izq.categoria);
            report.write("\n");
            reporte(actual.izq);
        }else if(actual.izq == null){
            report.write(actual.categoria);
            report.write("->");
            report.write("NULL" + num);
            report.write("\n");
            ent++;
            num = String.valueOf(ent);
        }
        if(actual.der !=null){
            report.write(actual.categoria);
            report.write("->");
            report.write(actual.der.categoria);
            report.write("\n");
            reporte(actual.der);
        }else if(actual.der == null){
            report.write(actual.categoria);
            report.write("->");
            report.write("NULL" + num);
            report.write("\n");
            ent++;
            num = String.valueOf(ent);
        }
    }
}
