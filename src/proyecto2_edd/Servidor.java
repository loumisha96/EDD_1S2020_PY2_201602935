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
import java.util.ArrayList;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lourd
 */
public class Servidor implements Runnable {
    
    private ArrayList<Socket>clientes;
    private int puerto;
    public Servidor(int puerto){
        this.puerto = puerto;
        this.clientes = new ArrayList();
    }
    public void run (){
        ServerSocket servidor = null;
        Socket sc = null;
        DataInputStream in;
        try{
            servidor =new ServerSocket(puerto);
            System.out.println("servidor iniciado");
                while(true){
                    sc = servidor.accept();
                    System.out.println("Cliente conectado");
                    clientes.add(sc);
                }
        } catch(IOException ex){
    }
    
    
 }
 public void enviarInfo(){
     for(Socket sock: clientes){
         try {
             DataOutputStream dos = new DataOutputStream(sock.getOutputStream());
             //enviar info
             
         } catch (IOException ex) {
             Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
         }
     }
 }
    
}
