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
public class Libro {
    int ISBN;
    String title;
    String autor;
    String editorial;
    int anio;
    int edicion;
    public String categoria;
    String idioma;
    int carnet;
    
    public Libro(int ISBN, int anio, String title, String autor, String editorial,  int edicion, String categoria, int carnet){
        this.ISBN =  ISBN;
        this.title = title;
        this.autor = autor;
        this.editorial = editorial;
        this.anio = anio;
        this.categoria = categoria;
        this.carnet = carnet;
        this.edicion = edicion;
    }
}
