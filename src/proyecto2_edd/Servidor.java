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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lourd
 */
public class Servidor {
    
    public static void main (String[] args){
        ServerSocket servidor = null;
        Socket sc = null;
        DataInputStream in;
        DataOutputStream out;
        final int puerto = 5000;

        try{
            servidor =new ServerSocket(puerto);
                System.out.println("servidor iniciado");
                while(true){
                    sc = servidor.accept();
                    System.out.println("cliente conectado");
                    in = new DataInputStream(sc.getInputStream());
                    out = new DataOutputStream(sc.getOutputStream());
                    String mensaje = in.readUTF();
                    System.out.println("mensaje");
                    out.writeUTF("Hola mundo Servidor");
                    sc.close();//cerrando el cliente
                    System.out.println("cliente desconectado");

                }
        } catch(IOException ex){
    }
    
    
 }
    
    
 
    
    
    
    
    
}
