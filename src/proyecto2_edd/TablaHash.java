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
public class TablaHash {
    Usuarios [] Hash = new Usuarios[45];
    
    public TablaHash(){
        for(int i = 0; i<Hash.length; i++){
            Hash[i] = new Usuarios();
        }
    }
    public int funcionHash(int carne){
        return carne%45;
    }
    public void insertar(int carnet, String nombre, String apellido, String carrera , String password){
        Hash[funcionHash(carnet)].insertar(carnet, nombre, apellido, carrera, password);
    }
    public void Editar(int carnet, String nombre, String apellido, String carrera , String password){
        Hash[funcionHash(carnet)].Editar(carnet, nombre, apellido, carrera, password);
    }
    public void Eliminar(int carnet){
        Hash[funcionHash(carnet)].Eliminar(carnet);
    }
    public boolean ingreso(int carnet, String pass){
       return  Hash[funcionHash(carnet)].buscarIngreso(carnet, pass);
    }
    public void print(){
        for(int i =0; i< Hash.length; i++){
             Hash[i].print();
        }
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
      
    }
}
