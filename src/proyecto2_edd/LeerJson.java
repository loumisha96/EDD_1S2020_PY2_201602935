/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2_edd;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author lourd
 */
public class LeerJson {
    arbolAVL avl;
    TablaHash hash;
    Usuario userlog;
    Datos data;
    public LeerJson(arbolAVL avl, TablaHash hash, Datos data){
        this.avl = avl;
        this.hash = hash;
        this.data = data;
    }
    public void setUserlog(Usuario userlog) {
        this.userlog = userlog;
    }
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
               Libro libro = new Libro(isbn, anio, title, Autor, Editorial, edicion, Categoria,userlog.carne , idioma);
               avl.add(libro, userlog, data);
            }
           // avl.inorden();
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
                hash.insertar(carnet, Nombre, Apellido, Carrera, Password, data);
            }
           // hash.print();
        } catch (IOException ex) {
            Logger.getLogger(LeerJson.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void CrearJsonBloque(Bloque b){
        
        JSONObject json = new JSONObject();
        JSONObject json2 = new JSONObject();
        json.put("INDEX", b.index);
        json.put("TIMESTAMP", b.timestamp);
        json.put("NONCE", b.nonce);
        JSONObject json1 = new JSONObject();
        CrearJsonData(json1, b);
        json.put("PREVIOUSHASH", b.PreviousHash );
        json.put("HASH", b.Hash);
        //json.put("PRueba", json1);
        json.put("DATA", json1);
        json2.put("",json2);
        System.out.println(json.toJSONString());
        
        try {
            FileWriter file = new  FileWriter("Bloque.json");
            file.write(json.toJSONString());
            file.flush();
            file.close();
            
        } catch (IOException ex) {
            Logger.getLogger(LeerJson.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void CrearJsonData( JSONObject json1,Bloque b){
       NodoDato aux = b.datos.primero;
       JSONArray creU = new JSONArray();
       JSONArray editU = new JSONArray();
       JSONArray creL = new JSONArray();
       JSONArray eliL = new JSONArray();
       JSONArray creC = new JSONArray();
       JSONArray eliC = new JSONArray();
        JSONObject json2;
        for (int i = 0; i< b.datos.op+1; i++ ) {
           json2 = new JSONObject();
            switch (aux.tipo) {
                case 0:
                case 1:
                    json2.put("Carnet", aux.usuario.carne);
                    json2.put("Nombre", aux.usuario.Nombre);
                    json2.put("Apellido", aux.usuario.Apellido);
                    json2.put("Carrera", aux.usuario.Carrera);
                    json2.put("Password", aux.usuario.Password);
                    if (aux.tipo == 0) {
                        creU.add(json2);
                        json1.put("CREAR_USUARIO", creU);
                    } else {
                        editU.add(json2);
                        json1.put("EDITAR_USUARIO", editU);
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
                        creL.add(json2);
                        json1.put("CREAR_LIBRO", creL);
                        break;
                    }
                case 3:
                    {
                        json2.put("ISBN", aux.libro.ISBN);
                        json2.put("Titulo", aux.libro.title);
                        json2.put("Categoria", aux.libro.categoria);
                        eliL.add(json2);
                        json1.put("ELIMINAR_LIBRO", eliL);
                        break;
                    }
                case 4:
                    {
                        json2.put("NOMBRE", aux.Categoria);
                        creC.add(json2);
                        json1.put("CREAR_CATEGORIA", creC);
                        break;
                    }
                case 5:
                    {
                        json2.put("NOMBRE", aux.Categoria);
                        eliC.add(json2);
                        json1.put("ELIMINAR_CATEGORIA", eliC);
                        break;
                    }
                default:
                    break;
            }
            aux = aux.sig;
        }
    }
    public void LeerJsonGeneral(String json) throws IOException, ParseException{
        JSONParser parser = new JSONParser();
        
            Object  obj = parser.parse(new FileReader("Bloque.json"));
            JSONObject jsonObject =  (JSONObject) obj;
            JSONArray datos = (JSONArray) jsonObject.get("DATA");
            System.out.println("lkj");
            for(int i =0; i<datos.size(); i++){
                JSONObject rec = (JSONObject) datos.get(i);
                JSONArray u = (JSONArray) rec.get("CREAR_USUARIO");
                JSONArray u1 = (JSONArray) rec.get("EDITAR_USUARIO");
                JSONArray u2 = (JSONArray) rec.get("CREAR_LIBRO");
                JSONArray u3 = (JSONArray) rec.get("ELIMINAR_LIBRO");
                JSONArray u4 =  (JSONArray) rec.get("CREAR_CATEGORIA");
                JSONArray u5 = (JSONArray) rec.get("ELIMINAR_CATEGORIA");
                if(u!=null){
                   for(int j = 0; j<u.size(); j++){
                        JSONObject u6 = (JSONObject) u.get(i);
                        long Carnet = (long) u6.get("Carnet");
                        int carnet = (int)Carnet;
                        String Nombre = (String) u6.get("Nombre");
                        String Apellido = (String) u6.get("Apellido");
                        String Carrera = (String) u6.get("Carrera");
                        String Password = (String) u6.get("Password");
                        hash.insertar(carnet, Nombre, Apellido, Carrera, Password, data);
                    }
                }
                 if(u1!=null){
                    for(int j = 0; j<u1.size(); j++){
                        JSONObject u6 = (JSONObject) u1.get(i);
                        long Carnet = (long) rec.get("Carnet");
                        int carnet = (int)Carnet;
                        String Nombre = (String) u6.get("Nombre");
                        String Apellido = (String) u6.get("Apellido");
                        String Carrera = (String) u6.get("Carrera");
                        String Password = (String) u6.get("Password");
                        hash.Editar(carnet, Nombre, Apellido, Carrera, Password, data);
                    }
                }if(u2 != null){
                    for(int j = 0; j<u2.size(); j++){
                        JSONObject u6 = (JSONObject) u2.get(i);
                        long ISBN = (long) u6.get("ISBN");
                        int isbn = (int)ISBN;
                        long ANIO = (long) u6.get("Año");
                        int anio = (int)ANIO;
                        String idioma = (String) u6.get("Idioma");
                        String title = (String) u6.get("Titulo");
                        String Editorial = (String) u6.get("Editorial");
                        String Autor = (String) u6.get("Autor");
                        long Edicion = (long) u6.get("Edicion");
                        int edicion = (int)Edicion;
                        String Categoria = (String) u6.get("Categoria");
                        Libro libro = new Libro(isbn, anio, title, Autor, Editorial, edicion, Categoria,userlog.carne , idioma);
                        avl.add(libro, userlog, data);
                    }
                }
                if(u3 != null){
                    for(int j = 0; j<u3.size(); j++){
                        JSONObject u6 = (JSONObject) u3.get(i);
                        long ISBN = (long) u6.get("ISBN");
                        int isbn = (int)ISBN;
                        String Categoria = (String) u6.get("Categoria");
                        Nodo a =avl.buscarNodo(Categoria);
                        Libro libro = new Libro(isbn, 0, null, null, null, 0, null, 0, null);
                        a.Btree.delete(libro, userlog.carne, data);
                    }
                }if(u4 != null){
                    for(int j = 0; j<u4.size(); j++){
                        JSONObject u6 = (JSONObject) u4.get(i);
                        String cat = (String) u6.get("Nombre");
                        avl.add(cat, userlog, data);
                    }
                    
                }
                if(u5!= null){
                    for(int j = 0; j<u5.size(); j++){
                        JSONObject u6 =  (JSONObject)u5.get(j);
                        String cat = (String) u6.get("Nombre");
                        avl.remove(cat, userlog.carne, data);
                    }
                }
                    
                }
                
            
         
    }
    
}