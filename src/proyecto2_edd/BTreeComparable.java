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
public abstract class BTreeComparable {
    public int ISBN;
    public String title;
    public String autor;
    public String editorial;
    public int anio;
    public int edicion;
    public String categoria;
    public String idioma;
    public int carnet;
    
    
    int id;
    public abstract int compareTo(BTreeComparable paramBTreeComparable);
}
