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
public class Libro extends BTreeComparable {
    
    public Libro(int ISBN, int anio, String title, String autor, String editorial,  int edicion, String categoria, int carnet , String idioma){
        this.id = ISBN;
        this.ISBN = ISBN;
        this.anio = anio;
        this.title = title;
        this.autor = autor;
        this.editorial = editorial;
        this.edicion = edicion;
        this.categoria = categoria;
        this.idioma = idioma;
        this.carnet = carnet;
        
        
    }
    public Libro(){
        //this.book = new Libro();
    }
    
    

    @Override
    public int compareTo(BTreeComparable paramBTreeComparable) {
        int i = paramBTreeComparable.id;
        if(this.ISBN == i)
            return 0;
        if(this.ISBN <i)
            return -1;
        return 1;
        
    }
}
