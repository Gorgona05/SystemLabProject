
package Presentation;

import Logic.Instrumento;
import Logic.TipoInstrumento;
import Logic.Calibraciones;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

/**
 *
 * @author PABLO MORERA
 */
public class Controller {
    private Model mod;
     private View vista; 
    
    public Controller(View view){
        vista = view;
        mod = new Model();
        mod.CreateUserFile();   
    }
    
    public void addTipoInstrumento(String cod,String nom,String uni) throws Exception{
        mod.addTipoInstrumento(new TipoInstrumento(cod,nom,uni));
        uptadeTable();
        vista.limpiarLabelsTipoInst();
    }
    
    public void uptadeTable(){
        vista.UptadeTable(mod.returnList());
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

    public void addCalibracion(String num, String fech, String medicion)  throws Exception{
   //     mod.addCalibracion(new Calibraciones(/*numero, mediciones, fecha*/));
        uptadeTable();
        vista.limpiarLabelsCalib();
    }
    
    public void addInstrumento(String serie ,String tipo ,String descripcion,String minimo,String maximo,String tolerancia) throws Exception{
        mod.addInstrumento(new Instrumento(serie,tipo,descripcion,minimo,maximo,tolerancia));
        uptadeTableInstrumento(tipo);
    }
    
     public void deleteInstrumento(Instrumento inst) throws Exception{
      mod.deleteInstrumento(inst);
      uptadeTableInstrumento(inst.getTipo());
      vista.limpiarLabelsInstr();
    }
   
    public void uptadeTableInstrumento(String tipo){
        vista.UptadeTableInstrumento(mod.returnListInstrumento(tipo));
    }
    
    public void deleteCalibracion(Calibraciones inst) throws Exception{
        //mod.deleteCalibracion(inst);
        uptadeTable();
        vista.limpiarLabelsCalib();
    }
}

