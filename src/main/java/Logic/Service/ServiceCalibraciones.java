
package Logic.Service;

import Data.DataCalibraciones;
import Logic.Calibraciones;
import Logic.Instrumento;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


/**
 *
 * @author David
 */
public class ServiceCalibraciones {
      private static ServiceCalibraciones theInstance;
      private DataCalibraciones data;

    public static ServiceCalibraciones instance(){
        if (theInstance == null) theInstance = new ServiceCalibraciones();
        return theInstance;
    }
    
    private ServiceCalibraciones(){
    }
    
    public void uptadeData(DataCalibraciones dat){
        data = dat;
    }
    
    public void create(Calibraciones e) throws Exception{
        Calibraciones result = data.getCalibraciones().stream()
                .filter(i->i.getNumero().equals(e.getNumero())).findFirst().orElse(null);
        if (result==null) data.getCalibraciones().add(e);
        else throw new Exception("Tipo ya existe");
    }

    public Calibraciones read(Calibraciones e) throws Exception{
        Calibraciones result = data.getCalibraciones().stream()
                .filter(i->i.getNumero().equals(e.getNumero())).findFirst().orElse(null);
        if (result!=null) return result;
        else throw new Exception("Tipo no existe");
    }

    public void update(Calibraciones e) throws Exception{
        Calibraciones result;
        try{
            result = this.read(e);
            data.getCalibraciones().remove(result);
            data.getCalibraciones().add(e);
        }catch (Exception ex) {
            throw new Exception("Tipo no existe");
        }
    }

    public void delete(Calibraciones e) throws Exception{
        data.getCalibraciones().remove(e);
     }

    public List<Calibraciones> search(Calibraciones e){
        return data.getCalibraciones().stream()
                .filter(i->i.getNumero().contains(e.getNumero()))
                .sorted(Comparator.comparing(Calibraciones::getNumero))
                .collect(Collectors.toList());
    }
    public boolean ExistCalibraciones(Calibraciones e){
        Calibraciones result = data.getCalibraciones().stream()
                .filter(i->i.getNumero().equals(e.getNumero())).findFirst().orElse(null);
        if (result!=null) 
                return true;
        else
            return false;
    } 
    
    public List<Calibraciones> obtenerListaPorTipo(String tipo) {
        List<Calibraciones> calibraciones = new ArrayList<>();

        for (Calibraciones obj : data.getCalibraciones()) {
            if (obj.getTipo().equals(tipo)) {
                calibraciones.add(obj);
            }
        }

        return calibraciones;
    }
    
}
