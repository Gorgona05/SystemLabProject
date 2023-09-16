
package Logic.Service;

import Data.Data;
import Logic.Instrumento;
import Logic.TipoInstrumento;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author David
 */
public class ServiceInstrumento {
     private static ServiceInstrumento theInstance;
      private Data data;

    public static ServiceInstrumento instance(){
        if (theInstance == null) theInstance = new ServiceInstrumento();
        return theInstance;
    }
    
    private ServiceInstrumento(){
    }
    
    public void uptadeData(Data dat){
        data = dat;
    }

    //================= TIPOS DE INSTRUMENTO ============
   public void create(Instrumento e, int pos) throws Exception {
     Instrumento result = data.getInstrumentos(pos).stream()
                .filter(i->i.getSerie().equals(e.getSerie())).findFirst().orElse(null);
        if (result==null) data.getInstrumentos(pos).add(e);
        else throw new Exception("Instrumento ya existe");
   }

    public Instrumento read(Instrumento e, int pos) throws Exception{
        Instrumento result = data.getInstrumentos(pos).stream()
                .filter(i->i.getSerie().equals(e.getSerie())).findFirst().orElse(null);
        if (result!=null) return result;
        else throw new Exception("Instrumento no existe");
    }

    public void update(Instrumento e,int pos) throws Exception{
        Instrumento result;
        try{
            result = this.read(e, pos);
            data.getInstrumentos(pos).remove(result);
            data.getInstrumentos(pos).add(e);
        }catch (Exception ex) {
            throw new Exception("Instrumento no existe");
        }
    }

    public void delete(Instrumento e,int pos) throws Exception{
        data.getInstrumentos(pos).remove(e);
     }

    public List<Instrumento> search(Instrumento e, int pos){
        return data.getInstrumentos(pos).stream()
                .filter(i->i.getSerie().contains(e.getSerie()))
                .sorted(Comparator.comparing(Instrumento::getSerie))
                .collect(Collectors.toList());
    }
    public boolean ExistInstrumento(Instrumento e, int pos){
        Instrumento result = data.getInstrumentos(pos).stream()
                .filter(i->i.getSerie().equals(e.getSerie())).findFirst().orElse(null);
        if (result!=null) 
                return true;
        else
            return false;
    }
}
