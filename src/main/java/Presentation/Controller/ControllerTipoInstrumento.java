
package Presentation.Controller;

import Logic.TipoInstrumento;
import Presentation.Model.ModelTipoInstrumento;
import Presentation.View;
import java.util.List;

/**
 *
 * @author PABLO MORERA
 */
public class ControllerTipoInstrumento {
    private ModelTipoInstrumento mod;
     private View vista; 
    
    public ControllerTipoInstrumento(View view){
        vista = view;
        mod = new ModelTipoInstrumento();
        mod.CreateUserFile();   
    }
    
    public void addTipoInstrumento(String cod,String nom,String uni) throws Exception{
        mod.addTipoInstrumento(new TipoInstrumento(cod,nom,uni));
        uptadeTable();
        vista.limpiarLabelsTipoInst();
    }
    
    public void uptadeTable(){
        vista.UptadeTableTipoInstrumento(mod.returnList());
    }
    
   public List<TipoInstrumento> returnList(){
        return mod.returnList();
   }
   
    public void deleteTipoInstrumento(TipoInstrumento inst) throws Exception{
        mod.deleteTipoInstrumento(inst);
        uptadeTable();
        vista.limpiarLabelsTipoInst();
    }
    public void recoverList(){
        mod.recoverList();
    }
}

