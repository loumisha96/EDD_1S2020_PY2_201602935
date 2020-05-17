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
public class NodoDato {
    Usuario usuario;
    Libro libro;
    String Categoria;
    NodoDato sig;
    int carnet;
    int tipo;
    
    public NodoDato(Usuario usuario, int tipo){
        this.usuario = usuario;
        this.tipo = tipo;
        
    }
    public NodoDato(Libro libro, int tipo, int carnet){
        this.libro = libro;
        this.tipo = tipo;
        this.carnet = carnet;
        
    }
    public NodoDato(String cat, int tipo, int carnet){
        this.Categoria = cat;
        this.tipo = tipo;
        this.carnet = carnet;
    }
}
