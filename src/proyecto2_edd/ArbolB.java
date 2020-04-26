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
public class ArbolB {
    public  Pagina raiz;
    public  int orden;
    public ArbolB(int orden){
        this.raiz = null;
        this.orden = 5;
    }
    public  void insertar(Libro libro){
        if(buscar(libro.ISBN)==null){//si el ISBN no existe
            if(raiz == null)
                raiz = new Pagina(orden);
            if(raiz.claves[0] == null){//si la raiz es nula ingresar el ISBN en el
                insertarEnClave(libro, raiz);
            }else{
                Pagina pagInsert = buscarClave(libro.ISBN);
                if(pagInsert != null){
                    if(pagInsert.clavesLlenas == false)//si no tiene las claves llenas insertar en ella
                        insertarEnClave(libro, pagInsert);
                    else{//si no tiene espacio hacer un split
                      split(pagInsert, libro);
                      
                    }
                }
            }
        }
        PrintNivel(raiz);
    }
    public  Pagina split(Pagina pagInsert, Libro libro){
        Pagina pagAux = new Pagina(orden);
        Libro [] aux = new Libro[orden];
        pagAux.claves = aux;//le asigno un array con una posición más
        for(int i = 0; i<pagInsert.claves.length; i++){//le paso los ISBNs que ya tiene
            pagAux.claves[i]= pagInsert.claves[i];
            pagInsert.claves[i].ISBN=0;
        }
        insertarEnClave(libro, pagAux);//le inserto el nuevo ISBN
        double m =Math.ceil((pagAux.claves.length/2));
        int n=(int)m;
        /*if(m-1==0)
            n=(int)m;
        else
            n=(int)m-1;*/
        Pagina  nuevoPadre = new Pagina(orden);
        Pagina pagNueva = new Pagina(orden);
        Pagina nodoP;
        int ISBNPadre;
        Pagina auxp =null;//para ordenar los ramas
        Pagina aux2;
        int j=0;
        for(int i=0; i<pagAux.claves.length; i++){
            if(i<n)
                pagInsert.claves[i]=pagAux.claves[i];
            else if(i>n){
                pagNueva.claves[j]=pagAux.claves[i];
                j++;
            } 
        } 
        if(pagInsert.pagPadre==null){//si nno tiene padre las nuevas pag creadas pasan a ser sus hijos
            nuevoPadre.claves[0] = pagAux.claves[n];
            nuevoPadre.ramas[0]=pagInsert;
            nuevoPadre.ramas[1]=pagNueva;
            raiz = nuevoPadre;
            pagInsert.pagPadre = raiz;//asignar padre
            pagNueva.pagPadre=raiz;
            raiz.tengoHijos = true;//confirmar que tiene hijos
        }else{//si tiene un padre
            if(pagInsert.pagPadre.clavesLlenas==false){//si el padre tiene espacio
                ISBNPadre =pagAux.claves[n].ISBN;
                int pos=  insertarEnClave(libro,pagInsert.pagPadre);
                if(pagInsert.pagPadre.ramas[pos]!=null && pagInsert !=pagInsert.pagPadre.ramas[pos])//si en la posicion que va insert no está vacio hay que correr los ramas
                    asigRamas(pagInsert, pos);
                else//si está vacio solo se ingresa
                    pagInsert.pagPadre.ramas[pos]= pagInsert;
                pos++;
                if(pagInsert.pagPadre.ramas[pos]!=null){//si en la posicion que va la nueva pag no está vacio hay que correr los ramas
                    asigRamas(pagNueva, pos);
                }
                else//si está vacio solo se ingresa
                    pagInsert.pagPadre.ramas[pos]= pagNueva;
            pagNueva.pagPadre =pagInsert.pagPadre;
            }else{//si no tiene espacio hacer nuevamente split
                Pagina padre =split(pagInsert.pagPadre, pagAux.claves[n]);
                int i =0;
                while(i<pagInsert.claves.length&&pagInsert.claves[i].ISBN!=0)
                    i++;
                i++;
                int k;
                for(k =0; k<pagInsert.claves.length; k++){
                    if(i<pagInsert.pagPadre.ramas.length){
                        padre.ramas[k]= pagInsert.pagPadre.ramas[i];
                        pagInsert.pagPadre.ramas[i] =null;
                    }
                    i++;
                }
                pagInsert.pagPadre= padre;
                padre.ramas[k-1]=pagNueva;
                padre.tengoHijos = true;
                pagNueva.pagPadre = padre;
            }
        }
        validarPag(pagNueva);
        validarPag(pagInsert);
        return pagNueva;
    }
    public  void asigRamas(Pagina pagAux, int pos){
        Pagina Aux = null;
        Pagina aux1=null;
        for(int i = pos; i<pagAux.pagPadre.ramas.length;i++){
            if(i==pos){
                Aux = pagAux.pagPadre.ramas[pos];
                pagAux.pagPadre.ramas[pos]=pagAux;
            }else{
                aux1 =pagAux.ramas[i];
                pagAux.pagPadre.ramas[i]=Aux;
                Aux = aux1;
            }
        }
    }
    public Pagina buscarClave(int ISBN){
        return buscarClave(raiz, ISBN);
    }
    public  Pagina buscarClave(Pagina actual, int ISBN){//busca la clave en la que se debe ingresar el ISBN
        int posClaves =0;
        while(actual.claves[posClaves]!=null&&posClaves<actual.claves.length&&ISBN>actual.claves[posClaves].ISBN ){
            if(actual.claves[posClaves]==null)
                break;
            else
                posClaves++;
        }
        if(actual.tengoHijos==false)//si no tiene hijos retorna actual
            return actual;
        else if(posClaves ==actual.claves.length-1){//si es mayor que el ultimo ISBN
            if(actual.claves[actual.claves.length-1]==null)//si la posicion está vacia retornar esa pag
                return buscarClave(actual.ramas[posClaves], ISBN);
            else
                return buscarClave(actual.ramas[posClaves+1], ISBN); //retornar a la derecha
        }
        else{
            return buscarClave(actual.ramas[posClaves], ISBN);
        }
    }
    public  int insertarEnClave(Libro libro, Pagina actual){
       int posicion =0;
        if(actual.claves[0]==null){//si la priemra casilla está null
            actual.claves[0]= libro;
            posicion= 0;
        }
        else if(actual.claves[actual.claves.length-2]!=null&&libro.ISBN>actual.claves[actual.claves.length-2].ISBN ){//si es mayor que el último ISBN
            actual.claves[actual.claves.length-1]= libro;
            posicion = actual.claves.length-1;
            
        }else{
            int k=0;
            while(actual.claves[k] != null && libro.ISBN>actual.claves[k].ISBN && k<actual.claves.length){
                if(actual.claves[k]==null)
                    break;
                else
                    k++;
            }
            Libro aux=null;
            for(int j = k; j<actual.claves.length; j++){//empezar a llenar desde i posición
                if(j==k){
                    aux = actual.claves[j];
                    actual.claves[j]=libro;
                    posicion = k;
                }else if(aux !=null){
                    Libro aux2 = actual.claves[j];
                    actual.claves[j]=aux;
                    aux = aux2;
                }                    
            }
        }
        validarPag(actual);
        
       return posicion;
    }
    public  void validarPag(Pagina actual){
        if(actual.claves[actual.claves.length-1]!=null)//si la ultima posición está ocupada entonces está llena
            actual.clavesLlenas = true;
        else
            actual.clavesLlenas = false;
        if(actual.claves[(orden/2)-1]!= null && actual != raiz)//si la posición orden /2 - 1 está ocupada entonces la página es válida
            actual.clavesMin=true;
        else
            actual.clavesMin=false;
    }
    public  Pagina buscar(int ISBN){
        return buscar(raiz, ISBN);
    }
    public  Pagina buscar(Pagina actual, int ISBN){
        int posClaves=0;
        if(actual == null){
            posClaves=0;
            return null;
        }
        if( actual.claves[posClaves]!= null && actual.claves[posClaves].ISBN==ISBN){//valor encontrado
            posClaves =0;
            return actual;
        }
        else if(actual.tengoHijos==true){
            
            if(actual.claves[posClaves]==null){//si la posición está vacía
                posClaves =0;
                return buscar(actual.ramas[posClaves], ISBN);//retorna el hijo nodo de la derecha
            }
            else if(ISBN < actual.claves[posClaves].ISBN&& posClaves <= actual.claves.length){//si el ISBN es menor
                posClaves=0;
                return buscar(actual.ramas[posClaves], ISBN);//retorna hijo nodo de la izquierda
            }
            else if(posClaves == actual.claves.length && ISBN > actual.claves.length){//si es la última posición y el ISBN es mayor
                posClaves=0;
                return buscar(actual.ramas[posClaves+1], ISBN);//retorna el hijo nodo de la derecha
            }
            else{//si el ISBN no es menor pero tampo es la última posición
                    posClaves++;
                if(posClaves<actual.claves.length)
                    return recorrerNodo(actual, ISBN);
                else
                    return null;
            }

        }else{
            posClaves++;
            if(posClaves<actual.claves.length)
                return recorrerNodo(actual, ISBN);
            else
                return null;
            
        }
    }
    public  Pagina recorrerNodo(Pagina actual, int ISBN){
        int i =0;
        if(actual == null)
            return null;
        while(i<actual.claves.length && actual.claves[i]!= null&& ISBN != actual.claves[i].ISBN){
            i++;
        }
        if(actual.claves[i-1].ISBN== ISBN)
            return actual;
        else
            return null;
    }
    public void print(){
        PrintNivel(raiz);
    }
    public void PrintNivel(Pagina actual){
        if(raiz == null)
            System.out.println("Arbol Vacío");
        else{
            if(actual!= null){
            for(int i=0; i<actual.ramas.length; i++){
                PrintNivel(actual.ramas[i]);
            }
            for(int i=0; i<actual.claves.length; i++){
                if(actual.claves[i] != null)
                    System.out.print(actual.claves[i].ISBN+ ",");
            }
            System.out.println("");
            }
        }
        
    }
    public void Eliminar(Libro libro){
        if(buscar(libro.ISBN) != null){
            Pagina delete = buscarClave(libro.ISBN);
            
        }
    }
}
