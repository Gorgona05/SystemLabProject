/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Presentation.Model;

import Data.DataCalibraciones;
import Logic.Calibraciones;
import Logic.Service.ServiceCalibraciones;
import XML_DOM.XMLCalibraciones;
import java.util.List;
import java.util.Observer;
import javax.xml.transform.TransformerException;

/**
 *
 * @author David
 */
public class ModelCalibraciones extends java.util.Observable{
    private DataCalibraciones dataCalibraciones;
    private Calibraciones actual;
    private int changedProps;
    private XMLCalibraciones XMLCalibraciones;
    
    public ModelCalibraciones() {
        changedProps = 0;
        dataCalibraciones = new DataCalibraciones();
        ServiceCalibraciones.instance().uptadeData(dataCalibraciones);
    }
    
    public void CreateUserFile(){
        XMLCalibraciones = new XMLCalibraciones("Calibraciones.xml");
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
    public void init(List<Calibraciones> list){
        dataCalibraciones.setCalibraciones(list);
        setActual(new Calibraciones());
        
    }
    
     public DataCalibraciones getDataCalibraciones() {
        return dataCalibraciones;
    }

    public void setDataCalibraciones(DataCalibraciones dataCalibraciones) {
        this.dataCalibraciones = dataCalibraciones;
        changedProps += 1;
    }

    public Calibraciones getActual() {
        return actual;
    }

    public void setActual(Calibraciones actual) {
        changedProps += 2;
        this.actual = actual;
    }

    public int getChangedProps() {
        return changedProps;
    }

    public void setChangedProps(int changedProps) {
        this.changedProps = changedProps;
    }
    
     public void addCalibracion(Calibraciones calibracion) throws Exception{
        if(ServiceCalibraciones.instance().ExistCalibraciones(calibracion)){
           XMLCalibraciones.UpdateCalibracion(calibracion);
           ServiceCalibraciones.instance().update(calibracion);
       }
       else{
            XMLCalibraciones.AddCalibracion(calibracion);
            ServiceCalibraciones.instance().create(calibracion);
       }
    }
     
    public void deleteCalibracion(Calibraciones calibracion) throws TransformerException, Exception{
       if(ServiceCalibraciones.instance().ExistCalibraciones(calibracion)){
           XMLCalibraciones.deleteCalibracion(calibracion);
           ServiceCalibraciones.instance().delete(calibracion);
        }
    }   
      
    public List<Calibraciones> returnListCalibraciones(){
        return dataCalibraciones.getCalibraciones();
    }
    
    public void recoverList(){
        XMLCalibraciones.recoverCalibracion(returnListCalibraciones());
    }
    
    public List<Calibraciones> listEspecifica(String tipo){
        return  ServiceCalibraciones.instance().obtenerListaPorTipo(tipo);
    }
}
