/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2_edd;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.parser.ParseException;

/**
 *
 * @author lourd
 */
public class Servidor extends Observable implements Runnable{
    
    
    private int puerto;
    private LeerJson read;
    public ListaNodoRed list;
    public Servidor(int puerto , ListaNodoRed list){
        this.puerto = puerto;
        this.list = list;
       // this.read = read;
        
    }
    public void run (){
        ServerSocket servidor = null;
        Socket sc = null;
        DataInputStream in;
        DataOutputStream out;
        try{
            servidor =new ServerSocket(puerto);
            System.out.println("servidor iniciado");
                while(true){
                    sc = servidor.accept();
                    System.out.println("Cliente conectado");
                    in = new DataInputStream((sc.getInputStream()));
                    //out = new DataOutputStream(sc.getOutputStream());
                    String mensaje = in.readUTF();
                  //  read.LeerJsonGeneral(mensaje);
                    System.out.println(mensaje);
                    this.setChanged();
                    this.notifyObservers(mensaje);
                    sc.close();
                    list.print();
                    System.out.println("Cliente desconectado");
                    
                }
        } catch(IOException ex){
    }
    
    
 }
 public void enviarInfo(){
     /*for(Socket sock: clientes){
         try {
             DataOutputStream dos = new DataOutputStream(sock.getOutputStream());
             //enviar info
             
         } catch (IOException ex) {
             Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
         }
     }*/
 }
 public static  void main(String[] args){
//     Servidor s = new Servidor(5000);
  //   s.run();
        
    }

    
    
}
