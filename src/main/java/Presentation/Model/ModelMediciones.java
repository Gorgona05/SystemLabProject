/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Presentation.Model;

import Data.DataMediciones;
import Logic.Mediciones;
import Logic.Service.ServiceMediciones;
import java.io.IOException;
import java.util.List;
import java.util.Observer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;

/**
 *
 * @author David
 */
public class ModelMediciones extends java.util.Observable  {
    private DataMediciones dataMediciones;
    private Mediciones actual;
    private int changedProps;
    
    
    public ModelMediciones() {
        changedProps = 0;
        dataMediciones = new DataMediciones();
        ServiceMediciones.instance().uptadeData(dataMediciones);
        ServiceMediciones.instance().CreateUserFile();
    }
    
 
    @Override
    public synchronized void addObserver(Observer o) {
        super.addObserver(o); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
    public void commit(){
        setChanged();
        notifyObservers(changedProps);
        changedProps = 0;    
    }
    public void init(List<Mediciones> list){
        dataMediciones.setMediciones(list);
        setActual(new Mediciones());
        
    }
    
     public DataMediciones getMediciones() {
        return dataMediciones;
    }

    public void setDataMediciones(DataMediciones dataInstrumentos) {
        this.dataMediciones = dataInstrumentos;
        changedProps += 1;
    }

    public Mediciones getActual() {
        return actual;
    }

    public void setActual(Mediciones actual) {
        changedProps += 2;
        this.actual = actual;
    }

    public int getChangedProps() {
        return changedProps;
    }

    public void setChangedProps(int changedProps) {
        this.changedProps = changedProps;
    }
    
    public void addMediciones(int rangMinimo, int rangMaximo, int medicion,String tipo) throws TransformerException, TransformerConfigurationException, SAXException, IOException {
         ServiceMediciones.instance().create(rangMinimo,rangMaximo,medicion,tipo);
    }
    
     public List<Mediciones> returnMediciones(){
        return dataMediciones.getMediciones();
    }
    
     public void recoverList(){
        //XMLInstrumentos.recoverInstrumento(returnListInstrumento());
    }
     public List<Mediciones> listEspecifica(String tipo){
        return  ServiceMediciones.instance().obtenerListaPorTipo(tipo);
    }
}
