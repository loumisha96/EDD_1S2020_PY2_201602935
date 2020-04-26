/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2_edd;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lourd
 */
public class Cliente {
    
    
    public static void main (String[] args){
        final String Host = "Lou";
        final int puerto = 5000;
        DataInputStream in;
        DataOutputStream out;
        try {
            
            
            Socket sc = new Socket(Host, puerto);
            
            in = new DataInputStream(sc.getInputStream());
            out = new DataOutputStream(sc.getOutputStream());
            out.writeUTF("hola soy cliente");
            String mensaje = in.readUTF();System.out.println(mensaje);
            sc.close();
            
        
        
        
        
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    
}
