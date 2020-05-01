/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2_edd;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author lourd
 */
public class LeerJson {
    arbolAVL avl;
    TablaHash hash;
    Usuario userlog;
    public LeerJson(arbolAVL avl, TablaHash hash){
        this.avl = avl;
        this.hash = hash;
        
    }

    public void setUserlog(Usuario userlog) {
        this.userlog = userlog;
    }
    
    public void CargaMasivaLibros(String path, Bloque bloque) throws org.json.simple.parser.ParseException {
        JSONParser parser = new JSONParser();
        try {
            Object obj =parser.parse(new FileReader("CargaLibros.json"));
            JSONObject jsonObject =  (JSONObject) obj;
            JSONArray book = (JSONArray) jsonObject.get("libros");
            for(int i = 0; i<book.size(); i++){
               JSONObject rec = (JSONObject) book.get(i);
               long ISBN = (long) rec.get("ISBN");
               int isbn = (int)ISBN;
               long ANIO = (long) rec.get("Año");
               int anio = (int)ANIO;
               String idioma = (String) rec.get("Idioma");
               String title = (String) rec.get("Titulo");
               String Editorial = (String) rec.get("Editorial");
               String Autor = (String) rec.get("Autor");
               long Edicion = (long) rec.get("Edicion");
               int edicion = (int)Edicion;
               String Categoria = (String) rec.get("Categoria");
               Libro libro = new Libro(isbn, anio, title, Autor, Editorial, edicion, Categoria,userlog.carne , idioma);
               avl.add(libro, userlog, bloque);
            }
            avl.inorden();
            
            
        } catch (FileNotFoundException e) {
                //manejo de error
        }catch (IOException e) {
                //manejo de error
        }
            //manejo de error
    }
    public void CargaMasivaUsuarios(String path, Bloque bloque)throws org.json.simple.parser.ParseException{
        JSONParser parser = new JSONParser();
        try {
            Object  obj = parser.parse(new FileReader("usuarios.json"));
            JSONObject jsonObject =  (JSONObject) obj;
            JSONArray user = (JSONArray) jsonObject.get("Usuarios");
            for(int i =0; i<user.size(); i++){
                JSONObject rec = (JSONObject) user.get(i);
                long Carnet = (long) rec.get("Carnet");
                int carnet = (int)Carnet;
                String Nombre = (String) rec.get("Nombre");
                String Apellido = (String) rec.get("Apellido");
                String Carrera = (String) rec.get("Carrera");
                String Password = (String) rec.get("Password");
                hash.insertar(carnet, Nombre, Apellido, Carrera, Password, bloque);
                
            }
            hash.print();
            CrearJsonBloque(bloque);
        } catch (IOException ex) {
            Logger.getLogger(LeerJson.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
    public void CrearJsonBloque(Bloque b){
        JSONObject json = new JSONObject();
        json.put("INDEX", b.index);
        json.put("TIMESTAMP", b.timestamp);
        json.put("NONCE", b.nonce);
        JSONObject json1 = new JSONObject();
        CrearJsonData(json1, b);
        json.put("DATA", json1);
        json.put("PREVIOUSHASH", b.PreviousHash );
        json.put("HASH", b.Hash);
        System.out.println(json);
    }
    public void CrearJsonData(JSONObject json, Bloque b){
       NodoDato aux = b.datos.primero;
       JSONObject json2= new JSONObject();
        for (int i = 0; i< b.datos.op+1; i++ ) {
           
            switch (aux.tipo) {
                case 0:
                case 1:
                    
                    json2.put("Carnet", aux.usuario.carne);
                    json2.put("Nombre", aux.usuario.Nombre);
                    json2.put("Apellido", aux.usuario.Apellido);
                    json2.put("Carrera", aux.usuario.Carrera);
                    json2.put("Password", aux.usuario.Password);
                    if (aux.tipo == 0) {
                        json.put("CREAR_USUARIO", json2);
                    } else {
                        json.put("EDITAR_USUARIO", json2);
                    }   break;
                case 2:
                    {
                        
                        json2.put("ISBN", aux.libro.ISBN);
                        json2.put("Año", aux.libro.anio);
                        json2.put("Idioma", aux.libro.idioma);
                        json2.put("Titulo", aux.libro.title);
                        json2.put("Editorial", aux.libro.editorial);
                        json2.put("Autor", aux.libro.autor);
                        json2.put("Edicion", aux.libro.edicion);
                        json2.put("Categoria", aux.libro.categoria);
                        json.put("CREAR_LIBRO", json2);
                        break;
                    }
                case 3:
                    {
                        
                        json2.put("ISBN", aux.libro.ISBN);
                        json2.put("Titulo", aux.libro.title);
                        json2.put("Categoria", aux.libro.categoria);
                        json.put("ELIMINAR_LIBRO", json2);
                        break;
                    }
                case 4:
                    {
                        
                        json2.put("NOMBRE", aux.Categoria);
                        json.put("CREAR_CATEGORIA", json2);
                        break;
                    }
                case 5:
                    {
                        
                        json2.put("NOMBRE", aux.Categoria);
                        json.put("ELIMINAR_CATEGORIA", json2);
                        break;
                    }
                default:
                    break;
            }
            aux = aux.sig;
        }
    }
    
    
}