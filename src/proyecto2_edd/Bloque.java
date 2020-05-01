/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2_edd;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author lourd
 */
public class Bloque {
    int index;
    int op;
    String timestamp;
    Datos datos;
    String PreviousHash;
    String Hash;
    int nonce;
    Bloque sig;
    Bloque ant;
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("DD-MM-YY::HH:MM:SS");
        this.timestamp = sdf.format(cal.getTime());
        
    }

    public Datos getDatos() {
        return datos;
    }

    public void setDatos(Datos datos) {
        this.datos = datos;
    }

    public String getPreviousHash() {
        return PreviousHash;
    }

    public void setPreviousHash(String PreviousHash) {
        if(PreviousHash.substring(0, 4).equals("0000"))
            this.PreviousHash = PreviousHash;
        
    }

    public String getHash() {
        return Hash;
    }

    public void setHash(String Hash) {
        this.Hash = Hash;
    }

    public int getNonce() {
        return nonce;
    }

    public void setNonce(String input) {
        int nonce=0;
        String cadena;
        while(true){
            cadena = SHA256(input+String.valueOf(nonce));
            
            if(cadena.substring(0, 4).equals("0000"))
                break;
            nonce++;
        }
        setHash(cadena);
        this.nonce = nonce;
    }
    public  String SHA256(String input) {
       String toReturn = null;
       try{
           MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.reset();
            digest.update(input.getBytes("utf8"));
            toReturn = String.format("%064x", new BigInteger(1, digest.digest()));
       }
          catch (Exception e) {
	    e.printStackTrace();
	}
        return toReturn;
    }
    public String Data(){
        String cad="";
        NodoDato aux = datos.primero;
        for(int i = 0; i<datos.op+1; i++){
            cad += "DATA";
            switch (aux.tipo) {
                case 0:
                case 1:
                    if(aux.tipo ==0 )
                        cad += "CREAR_USUARIO";
                    else
                        cad += "EDITAR_USUARIO";
                    cad += "Carnet"+Integer.toString(aux.usuario.carne)
                            +"Nombre" + aux.usuario.Nombre
                            +"Apellido" + aux.usuario.Apellido
                            +"Carrera" + aux.usuario.Carrera
                            +"Password" + aux.usuario.Password;
                    break;
                case 2:
                case 3:
                    if(aux.tipo ==2 )
                        cad += "CREAR_LIBRO"
                                +"ISBN" + Integer.toString( aux.libro.ISBN)
                                +"Año"+ Integer.toString( aux.libro.anio)
                                +"Idioma" +aux.libro.idioma
                                +"Titulo" +aux.libro.title
                                +"Editorial"+aux.libro.editorial
                                +"Autor"+aux.libro.autor
                                +"Edición" +Integer.toString(aux.libro.edicion)
                                +"Categoria" +aux.libro.categoria;
                    else
                        cad += "ELIMINAR_LIBRO"
                                +"ISBN" + Integer.toString( aux.libro.ISBN)
                                +"Titulo" +aux.libro.title
                                +"Categoria" +aux.libro.categoria;
                    break;
                case 4:
                case 5:
                    if(aux.tipo ==4 )
                        cad += "CREAR_CATEGORIA";
                    else
                        cad += "EDITAR_USUARIO";
                    cad += "NOMBRE" +aux.libro.categoria;
                    break;
                default:
                    break;
            }
        }
        return cad;
    }
    public void asignar(int index){
        
        String input =(Integer.toString(getIndex())+getTimestamp()+ getPreviousHash()+ Data()+Integer.toString(0));
        setNonce(input);
    }
    Bloque(){
       this.sig = null;
       this.ant = null;
       this.datos = new Datos();
    }
}
