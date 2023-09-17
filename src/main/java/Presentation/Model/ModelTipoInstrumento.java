/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Presentation.Model;

import Data.DataTipoInstrumento;
import Logic.TipoInstrumento;
import Logic.Service.ServiceTipoInstrumento;
import XML_DOM.XMLTipoIntrumentos;
import java.util.List;
import java.util.Observer;
import javax.xml.transform.TransformerException;

/**
 *
 * @author PABLO MORERA
 */
public class ModelTipoInstrumento extends java.util.Observable{
    private DataTipoInstrumento dataTipoInstrumentos;
    private TipoInstrumento actual;
    private int changedProps;
    private XMLTipoIntrumentos XMLTipoInstrumentos;
    
//    public static int NONE=0;
//    public static int LIST=1;
//    public static int CURRENT=2;

    public ModelTipoInstrumento() {
        changedProps = 0;
        dataTipoInstrumentos = new DataTipoInstrumento();
        ServiceTipoInstrumento.instance().uptadeData(dataTipoInstrumentos);
      
    }
    
      public void CreateUserFile()
    {
        XMLTipoInstrumentos = new XMLTipoIntrumentos("TiposInstrumentos.xml");
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
    public void init(List<TipoInstrumento> list){
        dataTipoInstrumentos.setTipoInstrumentos(list);
        setActual(new TipoInstrumento());
        
    }

    public DataTipoInstrumento getDataInstrumentos() {
        return dataTipoInstrumentos;
    }

    public void setDataTipoInstrumentos(DataTipoInstrumento dataTipoInstrumentos) {
        this.dataTipoInstrumentos = dataTipoInstrumentos;
        changedProps += 1;
    }

    public TipoInstrumento getActual() {
        return actual;
    }

    public void setActual(TipoInstrumento actual) {
        changedProps += 2;
        this.actual = actual;
    }

    public int getChangedProps() {
        return changedProps;
    }

    public void setChangedProps(int changedProps) {
        this.changedProps = changedProps;
    }
 
     public void addTipoInstrumento(TipoInstrumento inst) throws Exception {
     
       if(ServiceTipoInstrumento.instance().ExistInstrumento(inst)){
           XMLTipoInstrumentos.UpdateTipoInstrumento( inst);
           ServiceTipoInstrumento.instance().update(inst);
       }
       else{
            XMLTipoInstrumentos.AddTipoInstrumento(inst);
            ServiceTipoInstrumento.instance().create(inst);
       }
    }
     
    public List<TipoInstrumento> returnList(){
        return dataTipoInstrumentos.getTiposInstrumentos();
    }
    
    public void deleteTipoInstrumento(TipoInstrumento inst) throws TransformerException, Exception{
        if(ServiceTipoInstrumento.instance().ExistInstrumento(inst)){
           XMLTipoInstrumentos.deleteTipoInstrumento(inst);
           ServiceTipoInstrumento.instance().delete(inst);
        }
    }
    public void recoverList(){
        XMLTipoInstrumentos.recoverTipoInstrumento(returnList());
    }
}

