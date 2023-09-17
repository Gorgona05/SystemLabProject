/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
    
    public void addCalibracion(String numero,String fecha, String mediciones,String tipo) throws Exception{
        mod.addCalibracion(new Calibraciones(numero,fecha,mediciones,tipo));
        uptadeTable();
        vista.limpiarLabelsTipoInst();
    }
    
    public void uptadeTable(){
        vista.UptadeTableCalibraciones(mod.returnListCalibraciones());
    }
    
   public List<Calibraciones> returnList(){
        return mod.returnListCalibraciones();
   }
   
    public void deleteCalibracion(Calibraciones calibracion) throws Exception{
        mod.deleteCalibracion(calibracion);
        //uptadeTable();
        vista.limpiarLabelsInstr();
    }
    public void recoverList(){
        mod.recoverList();
    }
}