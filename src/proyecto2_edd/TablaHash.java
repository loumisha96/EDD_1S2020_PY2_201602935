/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2_edd;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author lourd
 */
public class TablaHash {
    Usuarios [] Hash = new Usuarios[45];
    
    public TablaHash(){
        for(int i = 0; i<Hash.length; i++){
            Hash[i] = new Usuarios();
        }
    }
    public int funcionHash(int carne){
        return carne%45;
    }
    public void insertar(int carnet, String nombre, String apellido, String carrera , String password, Datos data){
        Hash[funcionHash(carnet)].insertar(carnet, nombre, apellido, carrera, password,data);
    }
    public void Editar(Usuario u, Datos data){
        Hash[funcionHash(u.carne)].Editar(u, data);
    }
    public void Editar(int carnet, String nombre, String apellido, String carrera , String password, Datos data){
        Hash[funcionHash(carnet)].Editar(carnet, nombre, apellido, carrera, password,data);
    }
    public void Eliminar(int carnet){
        Hash[funcionHash(carnet)].Eliminar(carnet);
    }
    public Usuario ingreso(int carnet, String pass){
       return  Hash[funcionHash(carnet)].buscarIngreso(carnet, pass);
    }
    public Usuario buscar(int carnet){
        return Hash[funcionHash(carnet)].buscar(carnet);
    }
    public void print(){
        for(int i =0; i< Hash.length; i++){
             Hash[i].print();
        }
    }
    public  BufferedWriter report;
    public String reporte () throws IOException{
        FileWriter file = new FileWriter("ReporteUsuarios.dot");
        report = new BufferedWriter(file);
        report.write("digraph G{");
        report.write("node[shape=record, width =.1, height = .1, style=filled, color = Gray70];\n");
        report.write("rankdir=LR\n");
        report.write("\n");
        
        for(int i = 0; i<Hash.length; i++){
            if(Hash[i].primero != null){
                report.write(i + "[label = \"{<ref>|<data>" + i+" |}\"]\n");
                report.write(Integer.toString(i));
                report.write("->");
                report.write("U" +Integer.toString(j) + "\n");
                listaReporte(Hash[i]);
                
            }
        }
        report.write("}");
        report.close();
        ProcessBuilder p;
            p = new ProcessBuilder("dot", "-Tpng", "-o", "ReporteUsuarios.jpg", "ReporteUsuarios.dot");
            p.redirectErrorStream(true);
            p.start();
            return "ReporteUsuarios.jpg";
    }
    int j=0;
    public void listaReporte(Usuarios user) throws IOException{
        Usuario aux = user.primero;
        for(int i =0; i<=user.tam; i++){
            if(aux.sig != null){
                report.write("U" +j + "[label = < NOMBRE: " + aux.Nombre + " " + aux.Apellido+"<BR/>");
                report.write("CARNET: " +  Integer.toString(aux.carne) + "<BR/>");
                report.write("PASSWORD: " + aux.PassEncrip + ">]\n");
                aux = aux.sig;
                report.write("U" + Integer.toString(j));
                report.write("->");
                report.write("U" + Integer.toString(j+1) + "\n");
                j++;
                
            }else{
                report.write("U" +j + "[label = < NOMBRE: " + aux.Nombre + " " + aux.Apellido+"<BR/>");
                report.write("CARNET: " +  Integer.toString(aux.carne) + "<BR/>");
                report.write("PASSWORD: " + aux.PassEncrip + ">]\n");
                j++;
            }
        }
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
      
    }
}
