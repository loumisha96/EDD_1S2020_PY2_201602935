
package proyecto2_edd;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author lourd
 */
public class Blockchain {
    Bloque Genesis;
    
    int index=0;
    Blockchain(){ 
      this.Genesis = null; 
      
    }
  public void NuevoBloque(Bloque bloque) throws InterruptedException, NoSuchAlgorithmException{
      if(Genesis == null){
          Genesis = bloque;
          Genesis.setPreviousHash("0000");
          Genesis.setTimestamp();
          Genesis.setNonce();
          index++;
      }else{
         
            Bloque aux= Genesis;
            while(aux.sig != null)
                aux= aux.sig;
            aux.sig = bloque;
            bloque.ant = aux;
            bloque.setIndex(index);
            bloque.setTimestamp();
            bloque.setPreviousHash(bloque.ant.getHash());
            bloque.setNonce();
            index++;
         
      }
  }
  public String Reporte() throws IOException{
      FileWriter file = new FileWriter("ReporteBloque.dot");
        try (BufferedWriter reporte = new BufferedWriter(file)) {
            reporte.write("digraph G{\n");
            reporte.write("rankdir = LR;\n");
            reporte.write("node [shape= record];\n");
            Bloque aux = Genesis;
            for(int i = 0; i< index; i++){
                if(aux.sig != null){
                    reporte.write(Integer.toString(i));
                    reporte.write("[label =<");
                    reporte.write("INDEX: " +Integer.toString(aux.index)+ "<BR/>");
                    reporte.write("TIMESTAMP: "+ aux.getTimestamp()+ "<BR/>");
                    reporte.write("NONCE: "+  Integer.toString(aux.getNonce()) + "<BR/>");
                    reporte.write("PREVIOUSHASH: " + aux.getPreviousHash() + "<BR/>");
                    reporte.write("HASH: " + aux.getHash());
                    reporte.write(" >]\n");
                    reporte.write(Integer.toString(i) + "->" + Integer.toString(i+1)+ "\n");
                    reporte.write(Integer.toString(i+1) + "->" + Integer.toString(i)+ "\n");
                    aux = aux.sig;
                }
                else{
                    reporte.write(Integer.toString(i));
                    reporte.write("[label =<");
                    reporte.write("INDEX: " +Integer.toString(aux.index)+ "<BR/>");
                    reporte.write("TIMESTAMP: "+ aux.getTimestamp()+ "<BR/>");
                    reporte.write("NONCE: "+  Integer.toString(aux.getNonce()) + "<BR/>");
                    reporte.write("PREVIOUSHASH: " + aux.getPreviousHash() + "<BR/>");
                    reporte.write("HASH: " + aux.getHash());
                    reporte.write(" >]\n");
                }
            }
            reporte.write("}");
            reporte.close();
            ProcessBuilder p;
            p = new ProcessBuilder("dot", "-Tpng", "-o", "ReporteBloque.png", "ReporteBloque.dot");
            p.redirectErrorStream(true);
            p.start();
            
        }
        return "ReporteBloque.png";
    }
}