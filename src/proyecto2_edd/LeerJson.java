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
    arbolAVL avl = new arbolAVL();
    TablaHash hash = new TablaHash();
    public void CargaMasivaLibros(String path) throws org.json.simple.parser.ParseException {
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
               Libro libro = new Libro(isbn, anio, title, Autor, Editorial, edicion, Categoria,20166);
               
               avl.insertar(libro);
               
                   
               
            }
            avl.inorden();
        } catch (FileNotFoundException e) {
                //manejo de error
        }catch (IOException e) {
                //manejo de error
        }
            //manejo de error
    }
    public void CargaMasivaUsuarios(String path)throws org.json.simple.parser.ParseException{
        JSONParser parser = new JSONParser();
        try {
            Object  obj = parser.parse(new FileReader(path));
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
                Usuario usuario = new Usuario(carnet, Nombre, Apellido, Carrera, Password);
                hash.insertar(carnet, Nombre, Apellido, Carrera, Password);
                
            }
            hash.print();
        } catch (IOException ex) {
            Logger.getLogger(LeerJson.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
    
    
}