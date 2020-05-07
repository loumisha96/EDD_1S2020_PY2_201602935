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
public class Usuarios {
    Usuario primero;
    int tam;
    public Usuarios() {
        this.primero = null;
    }
    public void insertar(int carnet, String nombre, String apellido, String carrera , String password, Datos data){
        Usuario nuevo = new  Usuario(carnet, nombre, apellido, carrera, password);
        if(primero == null){
            primero = nuevo;
            tam=0;
        }else{
            nuevo.sig = primero;
            primero = nuevo;
            tam++;
        }
        NodoDato d = new NodoDato(nuevo, 0);
        data.insertarDato(d);
        
    }
    public Usuario buscar(int carnet){
        Usuario aux = primero;
        while(aux.carne !=  carnet && aux.sig != null){
            aux = aux.sig;
        }
        if(aux.carne == carnet)
            return aux;
        else return null;
    }
    public void Editar(int carnet, String nombre, String apellido, String carrera , String password, Datos data){
        Usuario aux = buscar(carnet);
        if(aux!=null){
            aux.carne = carnet;
            aux.Nombre = nombre;
            aux.Apellido = apellido;
            aux.Carrera = carrera;
            aux.Password = password;
            NodoDato d = new NodoDato(aux, 1);
            data.insertarDato(d);
            
        }
    }
    public void Editar(Usuario u, Datos data){
        Usuario aux = buscar(u.carne);
        if(aux!=null){
            NodoDato d = new NodoDato(aux, 1);
            data.insertarDato(d);
            
        }
    }
    public Usuario buscarIngreso(int carnet, String pass){
        Usuario aux = primero;
        while(aux.carne !=  carnet && aux.sig != null){
            aux = aux.sig;
        }
        if(aux.carne == carnet && aux.Password.equals(pass))
            return aux;
        else return null;
                
    }
    public void Eliminar(int carnet){
        Usuario aux = primero;
        Usuario aux1 = primero;
        while(aux.carne !=  carnet && aux.sig != null){
            aux1 = aux;
            aux = aux.sig;
        }
        if(aux.carne == carnet){
            if(aux == primero){
                primero = aux.sig;
                aux.sig = null;
                tam--;
            }else{
                aux1.sig = aux.sig;
                tam--;
            }
        }
            
    }
    public void print(){
        Usuario aux = primero;
        if(primero == null)
            System.out.println("lista vacia");
        else{

            while(aux.sig != null){
                System.out.println(aux.carne);
                aux = aux.sig;
            }
            System.out.println(aux.carne);
        }    
        
    }
}
