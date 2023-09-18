/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Presentation.Controller;

import Presentation.Model.ModelMediciones;
import Presentation.View;
import java.io.IOException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;

/**
 *
 * @author David
 */
public class ControllerMediciones {
     private ModelMediciones mod;
    private View vista; 
    
    public ControllerMediciones(View view){
        vista = view;
        mod = new ModelMediciones(); 
    }
    
    public void addMediciones(int rangMinimo, int rangMaximo, int medicion,
            String tipo) throws TransformerException, TransformerConfigurationException, SAXException, IOException{
        mod.addMediciones(rangMinimo,rangMaximo,medicion,tipo);
        uptadeTable(tipo);
    }
    
    public void uptadeTable(String tipo){
        vista.UptadeTableMediciones(mod.listEspecifica(tipo));
    }
    
}
