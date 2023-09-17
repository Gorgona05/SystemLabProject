/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Presentation.Controller;

import Logic.Instrumento;
import Presentation.Model.ModelInstrumento;
import Presentation.View;
import java.util.List;

/**
 *
 * @author David
 */
public class ControllerInstrumento {
    private ModelInstrumento mod;
    private View vista; 
    
    public ControllerInstrumento(View view){
        vista = view;
        mod = new ModelInstrumento();
        mod.CreateUserFile();   
    }
    
    public void addInstrumento(String serie,String tipo,String descripcion,String minimo,
            String maximo,String tolerancia) throws Exception{
        mod.addInstrumento(new Instrumento(serie,tipo,descripcion,minimo,maximo,tolerancia));
        uptadeTable();
        vista.limpiarLabelsTipoInst();
    }
    
    public void uptadeTable(){
        vista.UptadeTableInstrumento(mod.returnListInstrumento());
    }
    
   public List<Instrumento> returnList(){
        return mod.returnListInstrumento();
   }
   
    public void deleteInstrumento(Instrumento inst) throws Exception{
        mod.deleteInstrumento(inst);
        uptadeTable();
        vista.limpiarLabelsInstr();
    }
    public void recoverList(){
        mod.recoverList();
    }
}
