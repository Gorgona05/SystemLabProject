
package Presentation.Controller;

import Logic.Calibraciones;
import Presentation.Model.ModelCalibraciones;
import Presentation.View;
import java.util.List;

/**
 *
 * @author David
 */
public class ControllerCalibraciones {
       private ModelCalibraciones mod;
    private View vista; 
    
    public ControllerCalibraciones(View view){
        vista = view;
        mod = new ModelCalibraciones();
        mod.CreateUserFile();   
    }
    
    public void addCalibracion(String numero,String fecha, String mediciones, String tipo) throws Exception{
        mod.addCalibracion(new Calibraciones(numero,fecha,mediciones,tipo));
        uptadeTable(tipo);
    }
    
    public void uptadeTable(String tipo){
        vista.UptadeTableCalibraciones(mod.listEspecifica(tipo));
    }
    
   public List<Calibraciones> returnList(){
        return mod.returnListCalibraciones();
   }
   
    public void deleteCalibracion(Calibraciones calibracion) throws Exception{
        mod.deleteCalibracion(calibracion);
        uptadeTable(calibracion.getTipo());
    }
    public void recoverList(){
        mod.recoverList();
    }
}
