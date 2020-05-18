
package proyecto2_edd;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YY::HH:mm:ss");
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

 
    public String setNonce() throws InterruptedException, NoSuchAlgorithmException {
        String input =(Integer.toString(getIndex())+getTimestamp()+ getPreviousHash()+ Data()+Integer.toString(nonce));
        String cadena = new String(new char[4]).replace('\0', '0');
        String hash = create(getSHA(input));
        while(!hash.substring(0,4).equals(cadena)){
            nonce++;
            input =(Integer.toString(getIndex())+getTimestamp()+ getPreviousHash()+ Data()+Integer.toString(nonce));
            hash = create(getSHA(input));
        }
        setHash(hash);
        return hash;
        
    }
    
    public static byte[] getSHA(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
        
    }
    public static String create(byte[] bytes){
        StringBuilder hex = new StringBuilder();
        for(int j = 0; j<bytes.length; j++){
            String hexAux = Integer.toHexString(0xff & bytes[j]);
            if(hexAux.length()==1){
                hex.append('0');
               // hex.append('0');
            }
            hex.append(hexAux);
        }
        return hex.toString();
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
                        cad += "EDITAR_CATEGORIA";
                    cad += "NOMBRE" +aux.Categoria;
                    break;
                default:
                    break;
            }
        }
        return cad;
    }
    Bloque(Datos datos){
        
       this.sig = null;
       this.ant = null;
       this.datos = datos;
       
    }
}
