/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2_edd;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
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
             /*String charset = "UTF-8";
            BufferedReader reader = new BufferedReader(
        new InputStreamReader(
            new FileInputStream(path), charset));*/
            Object obj =parser.parse(new InputStreamReader(new FileInputStream(path), "utf-8"));
            
            JSONObject jsonObject =  (JSONObject) obj;
            JSONArray book = (JSONArray) jsonObject.get("libros");
            
            for(int i = 0; i<book.size(); i++){
                
               JSONObject rec = (JSONObject) book.get(i);
               
               long ISBN = (long) rec.get("ISBN");
               int isbn = (int)ISBN;
               
               String idioma = (String) rec.get("Idioma");
               
               
               long ANIO = (long) rec.get("Año");
               int anio = (int)ANIO;
               
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
                JOptionPane.showMessageDialog(null, "ErrorFile");
        }catch (IOException e) {
                //manejo de error
                JOptionPane.showMessageDialog(null, "ErrorIO");
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
            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new File ("dist"));
            chooser.setCurrentDirectory(new File (".").getCanonicalFile());
            chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            chooser.showSaveDialog(null);
            
            File file = chooser.getSelectedFile();
            FileWriter file2 = new  FileWriter(file);
            file2.write(json.toJSONString());
            file2.flush();
            file2.close();
            
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
       JSONArray eliU = new JSONArray();
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
                        json2.put("Anio", aux.libro.anio);
                        json2.put("Idioma", aux.libro.idioma);
                        json2.put("Titulo", aux.libro.title);
                        json2.put("Editorial", aux.libro.editorial);
                        json2.put("Autor", aux.libro.autor);
                        json2.put("Edicion", aux.libro.edicion);
                        json2.put("Categoria", aux.libro.categoria);
                        json2.put("Carnet", aux.libro.carnet);
                        creL.add(json2);
                        json1.put("CREAR_LIBRO", creL);
                        break;
                    }
                case 3:
                    {
                        json2.put("ISBN", aux.libro.ISBN);
                        json2.put("Titulo", aux.libro.title);
                        json2.put("Categoria", aux.libro.categoria);
                        json2.put("Carnet", aux.carnet);
                        eliL.add(json2);
                        json1.put("ELIMINAR_LIBRO", eliL);
                        break;
                    }
                case 4:
                    {
                        json2.put("NOMBRE", aux.Categoria);
                        json2.put("CARNET", aux.carnet);
                        creC.add(json2);
                        json1.put("CREAR_CATEGORIA", creC);
                        break;
                    }
                case 5:
                    {
                        json2.put("NOMBRE", aux.Categoria);
                        json2.put("CARNET", aux.carnet);
                        eliC.add(json2);
                        json1.put("ELIMINAR_CATEGORIA", eliC);
                        break;
                    }
                case 6:
                    {
                        json2.put("CARNET", aux.usuario.carne);
                        eliU.add(json2);
                        json1.put("ELIMINAR_USUARIO", eliU);
                    }
                default:
                    break;
            }
            aux = aux.sig;
        }
    }
    public void LeerJsonGeneral(File json) throws IOException, ParseException{
          
        JSONParser parser = new JSONParser();
            Object obj =parser.parse(new FileReader(json));
            JSONObject jsonObject =  (JSONObject) obj;
            
            JSONObject datos = (JSONObject) jsonObject.get("DATA");
            //JSONArray datos = (JSONArray) jsonObject.get("DATA");
            for(int i =0; i<datos.size(); i++){
                //JSONObject rec = (JSONObject) datos.get(i);
                JSONArray u = (JSONArray) datos.get("CREAR_USUARIO");
                JSONArray u1 = (JSONArray)datos.get("EDITAR_USUARIO");
                JSONArray u2 = (JSONArray) datos.get("CREAR_LIBRO");
                JSONArray u3 = (JSONArray) datos.get("ELIMINAR_LIBRO");
                JSONArray u4 =  (JSONArray) datos.get("CREAR_CATEGORIA");
                JSONArray u5 = (JSONArray) datos.get("ELIMINAR_CATEGORIA");
                JSONArray u7 = (JSONArray) datos.get("ELIMINAR_USUARIO");
                if(u!=null){
                   for(int j = 0; j<u.size(); j++){
                        JSONObject u6 = (JSONObject) u.get(j);
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
                        JSONObject u6 = (JSONObject) u1.get(j);
                        long Carnet = (long) u6.get("Carnet");
                        int carnet = (int)Carnet;
                        String Nombre = (String) u6.get("Nombre");
                        String Apellido = (String) u6.get("Apellido");
                        String Carrera = (String) u6.get("Carrera");
                        String Password = (String) u6.get("Password");
                        hash.Editar(carnet, Nombre, Apellido, Carrera, Password, data);
                    }
                }if(u2 != null){
                    for(int j = 0; j<u2.size(); j++){
                        JSONObject u6 = (JSONObject) u2.get(j);
                        long ISBN = (long) u6.get("ISBN");
                        int isbn = (int)ISBN;
                        long ANIO = (long) u6.get("Anio");
                        int anio = (int)ANIO;
                        String idioma = (String) u6.get("Idioma");
                        String title = (String) u6.get("Titulo");
                        String Editorial = (String) u6.get("Editorial");
                        String Autor = (String) u6.get("Autor");
                        long Edicion = (long) u6.get("Edicion");
                        int edicion = (int)Edicion;
                        String Categoria = (String) u6.get("Categoria");
                        long Carnet = (long) u6.get("Carnet");
                        int carnet = (int) Carnet;
                        Libro libro = new Libro(isbn, anio, title, Autor, Editorial, edicion, Categoria, carnet , idioma);
                        Usuario auxus = hash.buscar(carnet);
                        avl.add(libro,  auxus, data);
                    }
                }
                if(u3 != null){
                    for(int j = 0; j<u3.size(); j++){
                        JSONObject u6 = (JSONObject) u3.get(j);
                        long ISBN = (long) u6.get("ISBN");
                        int isbn = (int)ISBN;
                        String Categoria = (String) u6.get("Categoria");
                        Nodo a =avl.buscarNodo(Categoria);
                        long Carnet = (long) u6.get("Carnet");
                        int carnet = (int) Carnet;
                        Libro libro = new Libro(isbn, 0, null, null, null, 0, null, 0, null);
                        Usuario auxus = hash.buscar(carnet);
                        a.Btree.delete(libro, auxus.carne, data);
                    }
                }if(u4 != null){
                    for(int j = 0; j<u4.size(); j++){
                        JSONObject u6 = (JSONObject) u4.get(j);
                        long Carnet = (long) u6.get("CARNET");
                        String cat = (String) u6.get("NOMBRE");
                        
                        int carnet = (int) Carnet;
                        Usuario auxus = hash.buscar(carnet);
                        avl.add(cat, auxus, data);
                    }
                    
                }
                if(u5!= null){
                    for(int j = 0; j<u5.size(); j++){
                        JSONObject u6 =  (JSONObject)u5.get(j);
                        String cat = (String) u6.get("Nombre");
                        avl.remove(cat, userlog.carne, data);
                    }
                }
                if(u7!=null){
                    for(int j = 0; j<u7.size(); j++){
                        JSONObject u6 =  (JSONObject)u7.get(j);
                        long Carnet = (long) u6.get("CARNET");
                        int carnet = (int) Carnet;
                        hash.Eliminar(carnet, data);
                    }
                }
                    
                }
                
            
         
    }
    
}