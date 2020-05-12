/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2_edd;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author lourd
 */
public class Cliente {
    
    private int puerto;
    private String mensaje;
    private String ip;
    public ListaNodoRed list;
    public Cliente(int puerto, String mensaje,ListaNodoRed list) throws UnknownHostException{
        this.puerto = puerto;
        this.mensaje = mensaje;
        this.ip = InetAddress.getLocalHost().getHostAddress();
        this.list = list;
    }
    public void run (){
        DataInputStream in;
        DataOutputStream out;
        try {
            
            Socket sc = new Socket(ip, puerto);
            in = new DataInputStream(sc.getInputStream());
            out = new DataOutputStream(sc.getOutputStream());
            out.writeUTF(mensaje.toString());
            list.RegistrarNodo(ip);
           // String mensaje1 = in.readUTF();
           // System.out.println(mensaje1);
            sc.close();
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void enviarBloque(String bloque){
        
    }
    public static  void main(String[] args){
        
    }
    

    
}
