
package proyecto2_edd;

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
  public void NuevoBloque(Bloque bloque){
      if(Genesis == null){
          Genesis = bloque;
          Genesis.setIndex(index);
          Genesis.setTimestamp();
          Genesis.setPreviousHash("0000");
          Genesis.asignar(index);
          index++;
      }else{
         if(bloque.Hash.substring(0, 4).equals("0000")){
            Bloque aux= Genesis;
            while(aux.sig != null)
                aux= aux.sig;
            aux.sig = bloque;
            bloque.ant = aux;
            bloque.setIndex(index);
            bloque.setTimestamp();
            bloque.setPreviousHash(bloque.ant.getPreviousHash());
            bloque.asignar(index);
            index++;
         }
      }
      
      
      
  }
}
