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
    int ISBN;
    String title;
    String autor;
    String editorial;
    int anio;
    int edicion;
    String categoria;
    String idioma;
    int carnet;
    Usuario propietario;
    public abstract int compareTo(BTreeComparable paramBTreeComparable);
}
