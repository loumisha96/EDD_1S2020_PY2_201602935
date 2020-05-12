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
public class Usuario {
    int carne;
    String Nombre;
    String Apellido;
    String Carrera;
    String Password;
    String PassEncrip;
    Usuario sig;
    arbolAVL avlUser;
    public Usuario(int carnet, String nombre, String apellido, String carrera , String password){
        this.carne = carnet;
        this.Nombre = nombre;
        this.Apellido = apellido;
        this.Carrera = carrera;
        this.Password = password;
        this.sig = null;
        this.avlUser = new arbolAVL();
        
    }
}
