/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logic.Service;

import Data.DataMediciones;
import Logic.Mediciones;
import XML_DOM.XMLMediciones;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;

/**
 *
 * @author David
 */
public class ServiceMediciones {
     private static ServiceMediciones theInstance;
      private DataMediciones data;
      private XMLMediciones XMLMediciones;

    public static ServiceMediciones instance(){
        if (theInstance == null) theInstance = new ServiceMediciones();
        return theInstance;
    }
    
    private ServiceMediciones(){}
    
    public void uptadeData(DataMediciones dat){
        data = dat;
    }
    
     public void CreateUserFile(){
        XMLMediciones = new XMLMediciones("Mediciones.xml");
    }

    //================= TIPOS DE INSTRUMENTO ============
   public void create(int rangMinimo, int rangMaximo, int medicion,String tipo) throws TransformerException, TransformerConfigurationException, SAXException, IOException {
        int cont = 1;
        if (medicion > rangMinimo || medicion < rangMaximo) {
            int tamañoParte = (rangMaximo - rangMinimo) / medicion;
        for (int i = rangMinimo; i < rangMaximo; i += tamañoParte) {
             String referencia = String.valueOf(i);
             String medida = String.valueOf(cont);
            data.getMediciones().add(new Mediciones(medida, referencia,"",tipo));
            XMLMediciones.AddMediciones(new Mediciones(medida, referencia,"",tipo));
            cont++;   
        }
        }
    }

    public Mediciones read(Mediciones e) throws Exception{
        Mediciones result = data.getMediciones().stream()
                .filter(i->i.getMedida().equals(e.getMedida())).findFirst().orElse(null);
        if (result!=null) return result;
        else throw new Exception("Tipo no existe");
    }

    public void update(Mediciones e) throws Exception{
        Mediciones result;
        try{
            result = this.read(e);
            data.getMediciones().remove(result);
            data.getMediciones().add(e);
            this.XMLMediciones.UpdateMediciones(e);
        }catch (Exception ex) {
            throw new Exception("Tipo no existe");
        }
    }
    
    public List<Mediciones> obtenerListaPorTipo(String tipo) {
        List<Mediciones> mediciones = new ArrayList<>();

        for (Mediciones obj : data.getMediciones()) {
            if (obj.getTipo().equals(tipo)) {
                mediciones.add(obj);
            }
        }

        return mediciones;
    }
    
      public boolean ExistMedicion(Mediciones e){
        Mediciones result = data.getMediciones().stream()
                .filter(i->i.getMedida().equals(e.getMedida())).findFirst().orElse(null);
        if (result!=null) 
                return true;
        else
            return false;
    } 

    public void delete(Mediciones e) throws Exception{
        data.getMediciones().remove(e);
     }
}
