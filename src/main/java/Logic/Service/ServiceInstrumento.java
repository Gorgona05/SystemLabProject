
package Logic.Service;

import Data.DataInstrumento;
import Logic.Instrumento;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author David
 */
public class ServiceInstrumento {
     private static ServiceInstrumento theInstance;
      private DataInstrumento data;

    public static ServiceInstrumento instance(){
        if (theInstance == null) theInstance = new ServiceInstrumento();
        return theInstance;
    }
    
    private ServiceInstrumento(){
    }
    
    public void uptadeData(DataInstrumento dat){
        data = dat;
    }

    //================= TIPOS DE INSTRUMENTO ============
   public void create(Instrumento e) throws Exception{
        Instrumento result = data.getInstrumento().stream()
                .filter(i->i.getSerie().equals(e.getSerie())).findFirst().orElse(null);
        if (result==null) data.getInstrumento().add(e);
        else throw new Exception("Tipo ya existe");
    }

    public Instrumento read(Instrumento e) throws Exception{
        Instrumento result = data.getInstrumento().stream()
                .filter(i->i.getSerie().equals(e.getSerie())).findFirst().orElse(null);
        if (result!=null) return result;
        else throw new Exception("Tipo no existe");
    }

    public void update(Instrumento e) throws Exception{
        Instrumento result;
        try{
            result = this.read(e);
            data.getInstrumento().remove(result);
            data.getInstrumento().add(e);
        }catch (Exception ex) {
            throw new Exception("Tipo no existe");
        }
    }

    public void delete(Instrumento e) throws Exception{
        data.getInstrumento().remove(e);
     }

    public List<Instrumento> search(Instrumento e){
        return data.getInstrumento().stream()
                .filter(i->i.getSerie().contains(e.getSerie()))
                .sorted(Comparator.comparing(Instrumento::getSerie))
                .collect(Collectors.toList());
    }
    public boolean ExistInstrumento(Instrumento e){
        Instrumento result = data.getInstrumento().stream()
                .filter(i->i.getSerie().equals(e.getSerie())).findFirst().orElse(null);
        if (result!=null) 
                return true;
        else
            return false;
    } 

}
