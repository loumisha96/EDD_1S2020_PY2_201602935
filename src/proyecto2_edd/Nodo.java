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
    Usuario userLog;
    public int factorE;
    public Nodo izquierda, derecha;
    
    public ArbolB getBtree() {
        return Btree;
    }

    public void setBtree(ArbolB Btree) {
        this.Btree = Btree;
    }
    public ArbolB getCat(Nodo actual){
        if(actual.categoria == categoria)
            return this.Btree;
        else return null;
    }

    public String getcategoria() {
        return categoria;
    }

    public void setcategoria(String categoria) {
        this.categoria = categoria;
    }
    public int getFactorE(){
        int altDer = 0;
        int altIzq = 0;
        if(this.getDerecha()!=null){
        altDer = this.getDerecha().getAltura();
        }
        if(this.getIzquierda()!=null){		    
                altIzq = this.getIzquierda().getAltura();
        }
        return (altDer - altIzq);
    }
    

    public void setFactorE(int factorE) {
        this.factorE = factorE;
    }

    public Nodo getIzquierda() {
        return izquierda;
    }

    public void setIzquierda(Nodo izquierda) {
        this.izquierda = izquierda;
    }

    public Nodo getDerecha() {
        return derecha;
    }

    public void setDerecha(Nodo derecha) {
        this.derecha = derecha;
    }
    public int getAltura(){
        int hIzq = 0;
        int hDer = 0;

        if(this.getcategoria()==null)
          return 0;
        if(this.getIzquierda()!=null){	
                hIzq = this.getIzquierda().getAltura();
        }else{
                hIzq = 0;
        }

        if(this.getDerecha()!=null){   
                hDer = this.getDerecha().getAltura();
        }else{
                hDer = 0;
        }
        return Math.max(hIzq, hDer) + 1;
    }
    
    
    public Nodo(){	
        categoria = null;
        izquierda = null;
        derecha = null;
        factorE = 0;
    }
    public Nodo(String categoria, Usuario userLog){
        this.categoria = categoria;
        this.factorE =0;
        this.izquierda = this.derecha = null;
        this.Btree = new ArbolB(2);
        this.userLog = userLog;
    }
    
    
}
