/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2_edd;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import javax.swing.JOptionPane;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author lourd
 */
public class arbolAVL {
    private Nodo raiz;
    private int v[];
    Comparator<String> comparador;
    public arbolAVL(){	
    }
    public boolean add(Libro libro, Usuario userLog, Datos data) {
    	Nodo nodo = new Nodo(libro.categoria, userLog);
    	boolean salir = false;
    	boolean der = false;
    	Nodo raizTmp = this.getRaiz();

    	int altIzq, altDer;
        if(raizTmp == null){
    		this.raiz = nodo;
                this.raiz.Btree.add(libro, data, userLog.carne);
                NodoDato d = new NodoDato(libro.categoria, 4, userLog.carne);
                data.insertarDato(d);
                return true;
    	}else
    	if(this.contains(nodo.getcategoria())){
                Nodo a = buscarNodo(nodo.categoria);
                a.Btree.add(libro, data, userLog.carne);
                NodoDato d = new NodoDato(libro.categoria, 4, userLog.carne);
                data.insertarDato(d);
                
    		return false;
    	}
    	else{    	
    		while(!salir){
   				
                    if(this.compararcategoria(nodo.getcategoria(), raizTmp.getcategoria())>0){
                        if(raizTmp.getDerecha()!=null){
                                raizTmp = raizTmp.getDerecha();	
                        }else{
                                salir = true;
                                der = true;
                        }

                    }
                    else{
                        if(raizTmp.getIzquierda()!=null){
                                raizTmp = raizTmp.getIzquierda();
                        }else{
                                salir = true;
                        }
                    }
    		}
    		if(der){
                    raizTmp.setDerecha(nodo);
                    raizTmp.derecha.Btree.add(libro, data, userLog.carne);
                    NodoDato d = new NodoDato(libro.categoria, 4, userLog.carne);
                    data.insertarDato(d);
                    
                }
                else{
                    raizTmp.setIzquierda(nodo);
                    NodoDato d = new NodoDato(libro.categoria, 4, userLog.carne);
                     data.insertarDato(d);
                    raizTmp.izquierda.Btree.add(libro, data, userLog.carne);
                    
                    
                }
                while(equilibrado(this.getRaiz())<0){
				raizTmp = padre(raizTmp);
    		
    			if(raizTmp.getDerecha()==null){
	    			altDer = 0;
	    		}else{
	    			altDer = raizTmp.getDerecha().getAltura();
	    		}
	    		
	    		if(raizTmp.getIzquierda()==null){
	    			altIzq = 0;
	    		}else{
	    			altIzq = raizTmp.getIzquierda().getAltura();
	    		}
	    		
    			Nodo cambiar = estructurar(raizTmp, altIzq, altDer);
    			Nodo superior = padre(raizTmp);
	
    			if(compararcategoria(superior.getcategoria(), raizTmp.getcategoria())!=0){
    				if(superior.getIzquierda()!=null && compararcategoria(superior.getIzquierda().getcategoria(), raizTmp.getcategoria())==0){
	    				superior.setIzquierda(cambiar);		
		    		}
		    		else if(superior.getDerecha()!=null && compararcategoria(superior.getDerecha().getcategoria(), raizTmp.getcategoria())==0){
	    				superior.setDerecha(cambiar);
	    			}
    			}else{
    				this.raiz = cambiar;
    			}
    		}
    		return true;
    	}
    }
    public boolean add(String libro, Usuario userLog, Datos data) {
    	Nodo nodo = new Nodo(libro,userLog );
    	boolean salir = false;
    	boolean der = false;
    	Nodo raizTmp = this.getRaiz();

    	int altIzq, altDer;
        if(raizTmp == null){
    		this.raiz = nodo;
                NodoDato d = new NodoDato(libro, 4, userLog.carne);
                data.insertarDato(d);
                return true;
    	}else
    	if(this.contains(nodo.getcategoria())){
                Nodo a = buscarNodo(nodo.categoria);
                NodoDato d = new NodoDato(libro, 4, userLog.carne);
                data.insertarDato(d);
                return false;
    	}
    	else{    	
    		while(!salir){
   				
                    if(this.compararcategoria(nodo.getcategoria(), raizTmp.getcategoria())>0){
                        if(raizTmp.getDerecha()!=null){
                                raizTmp = raizTmp.getDerecha();	
                        }else{
                                salir = true;
                                der = true;
                        }

                    }
                    else{
                        if(raizTmp.getIzquierda()!=null){
                                raizTmp = raizTmp.getIzquierda();
                        }else{
                                salir = true;
                        }
                    }
    		}
    		if(der){
                    raizTmp.setDerecha(nodo);
                    NodoDato d = new NodoDato(libro, 4, userLog.carne);
                    data.insertarDato(d);
                }
                else{
                    raizTmp.setIzquierda(nodo);
                    NodoDato d = new NodoDato(libro, 4, userLog.carne);
                    data.insertarDato(d);
                    
                }
                while(equilibrado(this.getRaiz())<0){
				raizTmp = padre(raizTmp);
    		
    			if(raizTmp.getDerecha()==null){
	    			altDer = 0;
	    		}else{
	    			altDer = raizTmp.getDerecha().getAltura();
	    		}
	    		
	    		if(raizTmp.getIzquierda()==null){
	    			altIzq = 0;
	    		}else{
	    			altIzq = raizTmp.getIzquierda().getAltura();
	    		}
	    		
    			Nodo cambiar = estructurar(raizTmp, altIzq, altDer);
    			Nodo superior = padre(raizTmp);
	
    			if(compararcategoria(superior.getcategoria(), raizTmp.getcategoria())!=0){
    				if(superior.getIzquierda()!=null && compararcategoria(superior.getIzquierda().getcategoria(), raizTmp.getcategoria())==0){
	    				superior.setIzquierda(cambiar);		
		    		}
		    		else if(superior.getDerecha()!=null && compararcategoria(superior.getDerecha().getcategoria(), raizTmp.getcategoria())==0){
	    				superior.setDerecha(cambiar);
	    			}
    			}else{
    				this.raiz = cambiar;
    			}
    		}
    		return true;
    	}
    }
    public Nodo buscarNodo(String cat){
        return buscarNodo(raiz, cat);
    }
    public Nodo buscarNodo(Nodo actual, String cat){
        //actual.categoria;
        if (actual == null)
        {
            return null;
        }
            
        int comparacion = cat.compareToIgnoreCase(actual.categoria);
        if(comparacion == 0)
        {
            
            return actual;
        }
        else
        {
            return buscarNodo(comparacion < 0 ? actual.izquierda : actual.derecha, cat);
        }
    }
    private Nodo estructurar(Nodo nodo, int altIzq, int altDer){
        if(extraeFactorE(nodo)==2){
                if( extraeFactorE(nodo.getDerecha() )==1  || extraeFactorE(nodo.getDerecha()) == 0){
                        nodo = rotacionSimpleIzquierda(nodo);
                }

                else if(extraeFactorE(nodo.getDerecha() )==-1){
                        nodo = rotacionCompuestaDerecha(nodo);
                }
        }
        else if(extraeFactorE(nodo)==-2){
                if(extraeFactorE(nodo.getIzquierda() )==-1 || extraeFactorE(nodo.getDerecha())==0){
                        nodo = rotacionSimpleDerecha(nodo);
                }

                else if(extraeFactorE(nodo.getIzquierda())==1){
                        nodo = rotacionCompuestaIzquierda(nodo);
                }
        }

        return nodo;	
    }
    public int extraeFactorE(Nodo nodo){
    	if(nodo!=null){
    		return nodo.getFactorE();
    	}else{
    		return 0;
    	}
    }
    public Nodo rotacionSimpleIzquierda(Nodo nodo){
		Nodo nodoTmp = nodo;
		
    	nodo = nodoTmp.getDerecha(); //clone??
		nodoTmp.setDerecha(nodo.getIzquierda());

		nodo.setIzquierda(nodoTmp);

		return nodo;
    }
    public Nodo rotacionSimpleDerecha(Nodo nodo){
    	Nodo nodoTmp = nodo;
    	nodo = nodoTmp.getIzquierda();

		nodoTmp.setIzquierda(nodo.getDerecha());
		nodo.setDerecha(nodoTmp);

		return nodo;
    }
    public Nodo rotacionCompuestaIzquierda(Nodo nodo){
    	Nodo nodoTmp = nodo; //57

        nodoTmp = rotacionSimpleIzquierda(nodoTmp.getIzquierda()); //param 42 | sale: 54
        
		nodo.setIzquierda(nodoTmp); //param 54

		nodoTmp = rotacionSimpleDerecha(nodo); //param 54  | sale: 54
		
		return nodoTmp;
    }
    public Nodo rotacionCompuestaDerecha(Nodo nodo){
    	Nodo nodoTmp = nodo;
    	
        nodoTmp = rotacionSimpleDerecha(nodoTmp.getDerecha());
	
		nodo.setDerecha(nodoTmp);

		nodoTmp= rotacionSimpleIzquierda(nodo);

		return nodoTmp;
    }
    public int equilibrado(Nodo n){
        int hIzq = 0;
        int hDer = 0;

        if(n==null)
            return 0;
    	hIzq = equilibrado(n.getIzquierda());
    	
    	if(hIzq < 0)
    		return hIzq;
    	hDer = equilibrado(n.getDerecha());
    	
    	if(hDer <0)
    		return hDer;
    	if(Math.abs(hIzq - hDer)>1)
    		return -1;
    	
    	return Math.max(hIzq, hDer) + 1;
    }
    public Nodo padre(Nodo nodo){
	Nodo raizTmp = this.getRaiz();
        Stack<Nodo> pila = new Stack<Nodo>();
    	pila.push(raizTmp);	
    	while(raizTmp.getDerecha()!=null || raizTmp.getIzquierda()!=null){
	    	if(this.compararcategoria(nodo.getcategoria(), raizTmp.getcategoria())>0){
	    		if(raizTmp.getDerecha()!=null){   	
	    			raizTmp = raizTmp.getDerecha();
	    		}
	    	}
	    	else if(this.compararcategoria(nodo.getcategoria(), raizTmp.getcategoria())<0){	
	    		if(raizTmp.getIzquierda()!=null){   
		    		raizTmp = raizTmp.getIzquierda();
	    		}
	    	}
	    	if(this.compararcategoria(nodo.getcategoria(), raizTmp.getcategoria())==0){
	    		return pila.pop();
	    	}
                pila.push(raizTmp);	
    	}
    	return pila.pop();
    }
    
    public void clear( int carnet, Datos data){
    	Iterator iter = this.iterator();
    	
    	while(iter.hasNext()){
    		remove(iter.next(), carnet, data);
    	}
    }
    public boolean contains(Object o) throws ClassCastException, NullPointerException{
    	Nodo raizTmp = this.getRaiz();
    	if(this.isEmpty()){
    		return false;
    	}
    	
    	//si es la raiz el buscado
    	if(this.compararcategoria((String)o, raizTmp.getcategoria())==0){
	    	return true;
	    }
	    
    	while(raizTmp.getDerecha()!=null || raizTmp.getIzquierda()!=null){

	    	if(this.compararcategoria((String)o, raizTmp.getcategoria())>0){
	    		if(raizTmp.getDerecha()!=null){   		
	    			raizTmp = raizTmp.getDerecha();
	    		}else{
	    			return false;
	    		}
	    	}else if(this.compararcategoria((String)o, raizTmp.getcategoria())<0){	
	    		if(raizTmp.getIzquierda()!=null){   
		    		raizTmp = raizTmp.getIzquierda();
	    		}else{
	    			return false;
	    		}
	    	}
	    	
	    	if(this.compararcategoria((String)o, raizTmp.getcategoria())==0){
	    		return true;
	    	}
    	}
    	return false;
    }
    
    public boolean containsAll(Collection<?> c) throws ClassCastException, NullPointerException{   	
    	Iterator iter = c.iterator();
    	List listaArbol = this.inOrden();
    	String dato = null;
    	
    	if(this.isEmpty()){
    		return false;
    	}
    	
    	while(iter.hasNext()){
    		dato = (String)iter.next();
    		
    		if(!listaArbol.contains(dato)){
    			return false;
    		}
    	}
    	return true;
    }
    public boolean isEmpty(){
    	return this.size()==0;
    	//?? tal vez this.getRaiz()==null?
    }
    public Iterator iterator(){ 
    	List lista= this.inOrden();
    	Iterator iter = lista.iterator();
    	
    	return iter;
    }
    public boolean remove(Object o, int carnet, Datos data) throws ClassCastException, NullPointerException{
    	Nodo borrar=null,mirar=null,cambiar=null, nPadre = null;
    	Nodo raizTmp = this.getRaiz();
    	String c_aux, d_aux;
    	boolean salir = false;
    	int altDer = 0;
    	int altIzq = 0;
    	int a = 0;
    	
    	if(this.isEmpty()){
    		return false;
    	}

    	//el nodo a borrar es la raiz?
    	if(this.compararcategoria((String)o, raizTmp.getcategoria())==0){
            if(raizTmp.userLog.carne == carnet){
                salir = true;
	    	borrar = raizTmp;
                NodoDato d = new NodoDato(borrar.categoria, 5, carnet);
                data.insertarDato(d);
            }
            else
                JOptionPane.showMessageDialog(null,"No tiene permisos");
	    }
    	
    	//si no es la raiz, lo buscamos
        
    	while(!salir && (raizTmp.getDerecha()!=null || raizTmp.getIzquierda()!=null)){

	    	if(this.compararcategoria((String)o, raizTmp.getcategoria())>0){
	    		if(raizTmp.getDerecha()!=null){   		
	    			raizTmp = raizTmp.getDerecha();
	    		}else{
	    			return false;
	    		}
	    	}else if(this.compararcategoria((String)o, raizTmp.getcategoria())<0){
	    	
	    		if(raizTmp.getIzquierda()!=null){   
		    		raizTmp = raizTmp.getIzquierda();
	    		}else{
	    			return false;
	    		}
	    	}
	    	
	    	if(this.compararcategoria((String)o, raizTmp.getcategoria())==0){
	    		salir = true;
	    		borrar = raizTmp;
	    	}
    	}
    

    	//existe el nodo a borrar?
    	if(salir){
                if(borrar.userLog.carne == carnet){
                    NodoDato d2 = new NodoDato(borrar.categoria, 5, carnet);
                    data.insertarDato(d2);
                
                
                
                    mirar = borrar;

	    	//es una hoja?
	    	if(borrar.getIzquierda()==null && borrar.getDerecha()==null){
	    		mirar= padre(borrar);
	    		nPadre = padre(borrar);
	    		
	    		//es un arbol raiz con solo un nodo raiz?
	    		if(this.size()==1){
	    			this.raiz = null;
	    		}
	    		
	    		if(nPadre.getIzquierda()!=null && compararcategoria(nPadre.getIzquierda().getcategoria(), borrar.getcategoria())==0){
	    			nPadre.setIzquierda(null);
	    		}else if(nPadre.getDerecha()!=null && compararcategoria(nPadre.getDerecha().getcategoria(), borrar.getcategoria())==0){
	    			nPadre.setDerecha(null);
	    		}
	    		//nos lo cargamos
	    		borrar.setcategoria(null, borrar.userLog);
	    	}
	    	
	    	//solo tiene un hijo? (o 2 pero en la misma altura) entonces la altura de ese subarbol será 1 o 2 (altura raiz = 1)
	    	else if(borrar.getAltura()<=2){

	    		if(borrar.getIzquierda()!=null){
	    			borrar.setcategoria(borrar.getIzquierda().getcategoria(), borrar.userLog);
                                borrar.Btree = borrar.getIzquierda().Btree;
	    			borrar.setIzquierda(null);
	    		}
	    		
	    		else if(borrar.getDerecha()!=null){
	    			borrar.setcategoria(borrar.getDerecha().getcategoria(), borrar.userLog);
                                borrar.Btree= borrar.getDerecha().Btree;
	    			borrar.setDerecha(null);
	    		}
	    	}
	    	
	    	//cuando no es ni un hoja ni su padre. Es decir, está por medio del arbol.
	    	else{

	    		//buscamos el mayor de la izquierda
		    	if(borrar.getIzquierda()!=null){
		    		cambiar = borrar.getIzquierda();
		    		
		    		while(cambiar.getDerecha()!=null){
		    			cambiar = cambiar.getDerecha();
		    		}
		    	}
		    		
		    	//buscamos el menor de la derecha
		    	else if(borrar.getDerecha()!=null){
		    		cambiar = cambiar.getDerecha();
		    	
		    		while(cambiar.getIzquierda()!=null){
		    			cambiar = cambiar.getIzquierda();
		    		}
		    	}
	    	
		    	c_aux = cambiar.getcategoria();
		    	Nodo papa = padre(cambiar);
		    	
		    	//si el nodo que hemos cambiado se ha quedado con algún hijo...
		    	if(cambiar.getIzquierda()!=null || cambiar.getDerecha()!=null){
			    	if(cambiar.getIzquierda()!=null){
			    		cambiar.setcategoria(cambiar.getIzquierda().getcategoria(), cambiar.userLog);
                                        cambiar.Btree = cambiar.getIzquierda().Btree;
			    		cambiar.setIzquierda(null);
			    	}else if(cambiar.getDerecha()!=null){
			    		cambiar.setcategoria(cambiar.getDerecha().getcategoria(), cambiar.userLog);
                                        cambiar.Btree = cambiar.getDerecha().Btree;
			    		cambiar.setDerecha(null);
			    	}
		    	}
		    	//si no tiene hijos ya, lo eliminamos sin más
		    	else{		    	
			    	if(papa.getIzquierda()!=null && compararcategoria(papa.getIzquierda().getcategoria(), cambiar.getcategoria())==0){
			    		papa.setIzquierda(null);
			    	}else{
			    		papa.setDerecha(null);
			    	}
			    	cambiar.setcategoria(borrar.getcategoria(), cambiar.userLog);
                                cambiar.Btree = borrar.Btree;
			    	borrar.setcategoria(c_aux, borrar.userLog);
		    	}		    
	    	}
	    	
	    	while(equilibrado(this.getRaiz())<0){
    			if(mirar.getDerecha()==null){
	    			altDer = 0;
	    		}else{
	    			altDer = mirar.getDerecha().getAltura();
	    		}
	    		
	    		if(mirar.getIzquierda()==null){
	    			altIzq = 0;
	    		}else{
	    			altIzq = mirar.getIzquierda().getAltura();
	    		}
	    		
    			Nodo cambiar2 = estructurar(mirar, altIzq, altDer);
    			Nodo superior = padre(mirar);
    			
    			//si los nodos modificados tenian un padre anteriormente
    			if(compararcategoria(superior.getcategoria(), mirar.getcategoria())!=0){
    				if(superior.getIzquierda()!=null && compararcategoria(superior.getIzquierda().getcategoria(), mirar.getcategoria())==0){
	    				superior.setIzquierda(cambiar2);		
		    		}
		    		else if(superior.getDerecha()!=null && compararcategoria(superior.getDerecha().getcategoria(), mirar.getcategoria())==0){
	    				superior.setDerecha(cambiar2);
	    			}
    			}else{
    				this.raiz = cambiar2;
    			}
    			mirar = padre(mirar);
    		}
    		return true;	    	
       }else
                    JOptionPane.showMessageDialog(null, "No tiene los permisos");
        }	
    	return false;

    }

    public int size(){
    	return this.preOrden().size();
    }
    public List inOrden(){
		List lista = new ArrayList();
    	Nodo nodo = this.getRaiz();  	
    	Stack<Nodo> pila = new Stack<Nodo>();
     	
     	while((nodo!=null &&nodo.getcategoria()!=null)|| !pila.empty()){
     		if(nodo!=null){
     			pila.push(nodo);
     			nodo = nodo.getIzquierda();
     		}else{
     			nodo = pila.pop();
     			lista.add(nodo.getcategoria());
     			nodo = nodo.getDerecha();
     		}
     	} 	
    	
    	return lista;
    }
    public List preOrden(){
    	List lista = new ArrayList();
    	Nodo nodo = this.getRaiz();  	
    	Stack<Nodo> pila = new Stack<Nodo>();

     	while((nodo!=null && nodo.getcategoria()!=null) || !pila.empty()){
     		if(nodo!=null){
     			lista.add(nodo.getcategoria());
     			pila.push(nodo);
     			nodo = nodo.getIzquierda();
     		}else{
     			nodo = pila.pop();
     			nodo = nodo.getDerecha();
     		}
     	} 	
    	
    	return lista;
    }
     public int altura(String dato){
    	Nodo nodo = this.getNodo(dato);
    	if(!this.contains(dato)){
    		return -1;
    	}
    	
    	return nodo.getAltura();
    }
     public int profundidad(String dato, Usuario userLog){
    	Nodo nodo = new Nodo(dato, userLog);
    	int profundidad = 0;
    	while(compararcategoria(nodo.getcategoria(), this.getRaiz().getcategoria())!=0){
    		profundidad++;
    		nodo = padre(nodo);
    	}
    	
    	return profundidad;
    }
    public Nodo getRaiz(){
    	return this.raiz;
    }
    public Nodo getNodo(String dato){
     	Nodo raizTmp = this.getRaiz();
     	
     	if(this.isEmpty()){
     		return null;
     	}
    	
   		while(raizTmp.getDerecha()!=null || raizTmp.getIzquierda()!=null){

	    	if(this.compararcategoria(dato, raizTmp.getcategoria())>0){
	    		if(raizTmp.getDerecha()!=null){   		
	    			raizTmp = raizTmp.getDerecha();
	    		}else{
	    			return null;
	    		}
	    	}else if(this.compararcategoria(dato, raizTmp.getcategoria())<0){	
	    		if(raizTmp.getIzquierda()!=null){   
		    		raizTmp = raizTmp.getIzquierda();
	    		}else{
	    			return null;
	    		}
	    	}
	    	
	    	if(this.compararcategoria(dato, raizTmp.getcategoria())==0){
	    		return raizTmp;
	    	}
    	}
    	
    	return raizTmp;
    }
    private Comparator<String> getComparator(){
    	return this.comparador;
    }
    public String extraecategoria(Nodo nodo){
    	return nodo.getcategoria();
    }
    private int compararcategoria(String t1, String t2){
    	if(this.comparador==null){
    		return ((Comparable)t1).compareTo(t2);
    	}else{
    		return this.comparador.compare(t1,t2);
    	}
        
    }
    private boolean esLleno(Nodo SubRaiz){
        if(SubRaiz==null){
            return true;
        }
        else{
            if(altura(raiz.getIzquierda().getcategoria())!=altura(raiz.getDerecha().getcategoria())){
                return false;
            }
            return esLleno(SubRaiz.getIzquierda())&&esLleno(SubRaiz.getDerecha());
        }
    }
    public boolean esLleno(){
        return esLleno(this.getRaiz());
    }
    private int cantidad(Nodo SubRaiz){
        if(SubRaiz==null){
            return 0;
        }
        else{
            int nodosIzq=cantidad(SubRaiz.getIzquierda());
            int nodosDer=cantidad(SubRaiz.getDerecha());
            return 1+nodosIzq+nodosDer;
        }
    }
    
    public int Peso(){
        int total=cantidad(this.getRaiz());
        if(total==0){
            return total;
        }
        else{
            return total-1;
        }
        
    }
    
    private void densidad(Nodo SubRaiz,int m[],int j){
        if(SubRaiz!=null){
            m[j]=0;
            m[j]++;
            densidad(SubRaiz.getIzquierda(),m,j+1);
            densidad(SubRaiz.getDerecha(),m,j+1);
        }
    }
    
    private int[] VAltura(){
         int m[]=new int[altura(raiz.getcategoria())];
        return m;
    }
    
    public void nodosNivel(){
     densidad(this.getRaiz(),VAltura(),0);
     for(int i=0;i<VAltura().length;i++){
     System.out.println("Nivel "+i+" :"+v[i]+" Nodos");
     }
    }
    public void inorden(){
        inorden(raiz);
    }
    
    public void GinordenCat(DefaultTableModel tabla){
        GinordenCat(raiz, tabla);
    }
    public void GinordenCat(Nodo actual, DefaultTableModel tabla){
        if(actual != null){
            GinordenCat(actual.izquierda, tabla);
            tabla.addRow(new Object[]{actual.categoria});
            GinordenCat(actual.derecha, tabla);
        }
    }
    
    public void Ginorden(DefaultTableModel tabla){
        Ginorden(raiz, tabla);
    }
    public void Ginorden(Nodo actual, DefaultTableModel tabla){
        if(actual != null){
            Ginorden(actual.izquierda, tabla);
            actual.Btree.Gprint(tabla);
            Ginorden(actual.derecha, tabla);
        }
    }
    
    public void inorden(Nodo actual){
        if(actual != null){
            inorden(actual.izquierda);
            System.out.println("CATEGORIA " +actual.categoria );
            System.out.println(" ");
            actual.Btree.print();
            inorden(actual.derecha);
        }
    }
    public void PreOrden(Nodo actual){
        if(actual != null){
            System.out.print(actual.categoria+ ",");
            PreOrden(actual.izquierda);
            PreOrden(actual.derecha);
        }
    }
    public void PostOrden(Nodo actual){
        if(actual != null){
            PostOrden(actual.izquierda);
            PostOrden(actual.derecha);
            System.out.print(actual.categoria+ ",");
        }
    }
    
    public  BufferedWriter report;
    public String reporte() throws IOException{
        FileWriter file = new FileWriter("ReporteCategoria.dot");
        report = new BufferedWriter(file);
        report.write("digraph G{");
        report.write("node[shape=circle, style=filled, color = Gray70];");
        report.write("\n");
        reporte(raiz);
        report.write("}");
        report.close();
        ProcessBuilder p;
        p = new ProcessBuilder("dot", "-Tpng", "-o", "ReporteCategoria.png", "-Gcharset=latin1", "ReporteCategoria.dot");
        p.redirectErrorStream(true);
        p.start();
        return "ReporteCategoria.png";
    }
    int ent = 0;
    String num;
    public void reporte(Nodo actual) throws IOException{
        num = String.valueOf(ent);
        if(actual.getIzquierda() != null){
            report.write(actual.categoria + "_" +  Integer.toString(actual.Btree.tam) );
            report.write("->");
            report.write(actual.getIzquierda().categoria + "_" +  Integer.toString(actual.getIzquierda().Btree.tam));
            report.write("\n");
            reporte(actual.getIzquierda());
        }else if(actual.getIzquierda() == null){
            report.write(actual.categoria + "_" +  Integer.toString(actual.Btree.tam));
            report.write("->");
            report.write("NULL" + num);
            report.write("\n");
            ent++;
            num = String.valueOf(ent);
        }
        if(actual.getDerecha() !=null){
            report.write(actual.categoria + "_" +  Integer.toString(actual.Btree.tam));
            report.write("->");
            report.write(actual.getDerecha().categoria + "_" +  Integer.toString(actual.getDerecha().Btree.tam));
            report.write("\n");
            reporte(actual.getDerecha());
        }else if(actual.getDerecha() == null){
            report.write(actual.categoria + "_" +  Integer.toString(actual.Btree.tam));
            report.write("->");
            report.write("NULL" + num);
            report.write("\n");
            ent++;
            num = String.valueOf(ent);
        }
    }
     public void reportePreorden(Nodo actual) throws IOException{
	if(actual != null)
    {
    	if(actual == raiz){
    		report.write(actual.categoria);
    		
	       	reportePreorden(actual.getIzquierda());
	        reportePreorden(actual.getDerecha());
		}
		else{
                    report.write("->");
                    report.write(actual.categoria); 
       		
                    reportePreorden(actual.getIzquierda());
                    reportePreorden(actual.getDerecha());
		}
        
    }
}
     
    public String reporteRecorrido(int op) throws IOException{
            String name = "";
            if(op==0)
                name = "ReportePreorden.dot";
            else if(op==1)
                name = "ReporteInorden.dot";
            else
                name = "ReportePostorden.dot";
            FileWriter file = new FileWriter(name);
            report = new BufferedWriter(file);
            report.write("digraph G{\n");
            report.write("rankdir = LR;\n");
            report.write("node [shape= record];\n");
            String ruta = "";
            
            switch (op) {
                case 0:
                    reportePreorden(raiz);
                    ruta = "ReportePreorden.jpg";
                    break;
                case 1:
                    report.write(reporteInorden(raiz));
                    ruta = "ReporteInorden.jpg";
                    break;
                default:
                    reportePostorden(raiz);
                    ruta = "ReportePost.jpg";
                    break;
            }
            report.write("}");
            report.close();
            ProcessBuilder p;
            p = new ProcessBuilder("dot", "-Tpng", "-o", ruta, "-Gcharset=latin1", name);
            p.redirectErrorStream(true);
            p.start();
            return ruta;
    }
    public String reporteInorden(Nodo actual) throws IOException{
	String dot = " ";
        
        if(actual != null)
        {
            dot += reporteInorden(actual.getIzquierda());
            if(actual.getDerecha()!= null || actual.getIzquierda() != null)
                if(dot.charAt(dot.length()-2) == '>'){
            } else {
                    System.out.println(dot.charAt(dot.length()-2));
                    dot += " ->";
            }
            dot += actual.categoria;
            if(actual.getDerecha()!= null || actual.getIzquierda() != null)
                   if(dot.charAt(dot.length()-1) == '>'){
            } else {
                       System.out.println(dot.charAt(dot.length()-2));
                    dot += " ->";
            }
            
            dot +=reporteInorden(actual.getDerecha());
            
        }
        return dot;
        
}
    public void reportePostorden(Nodo actual) throws IOException{
	if(actual != null)
        {
            if(actual == raiz){
                reportePostorden(actual.getIzquierda());
                reportePostorden(actual.getDerecha());
                report.write(actual.categoria);

            }else{

                reportePostorden(actual.getIzquierda());
                reportePostorden(actual.getDerecha());
                report.write(actual.categoria);
                report.write("->");

            }

        }
}

}
