/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Presentation;

import Data.Data;
import Logic.Calibraciones;
import Logic.Service.ServiceCalibraciones;
import Logic.Instrumento;
import Logic.Service.ServiceInstrumento;
import Logic.TipoInstrumento;
import Logic.Service.ServiceTipoInstrumento;
import XML_DOM.XMLTipoIntrumentos;
import java.io.IOException;
import java.util.List;
import java.util.Observer;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;

/**
 *
 * @author PABLO MORERA
 */
public class Model extends java.util.Observable{
    private Data dataInstrumentos;
    private TipoInstrumento actual;
    private int changedProps;
    private XMLTipoIntrumentos XMLInst;
  //  private XMLCalibraciones XMLCali;
    
//    public static int NONE=0;
//    public static int LIST=1;
//    public static int CURRENT=2;

    public Model() {
        changedProps = 0;
        dataInstrumentos = new Data();
        ServiceTipoInstrumento.instance().uptadeData(dataInstrumentos);
        ServiceInstrumento.instance().uptadeData(dataInstrumentos);
        ServiceCalibraciones.instance().uptadeData(dataInstrumentos);
    }
    
      public void CreateUserFile()
    {
        XMLInst = new XMLTipoIntrumentos("LaboratorioIndustrial.xml");
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
        dataInstrumentos.setInstrumentos(list);
        setActual(new TipoInstrumento());
        
    }

    public Data getDataInstrumentos() {
        return dataInstrumentos;
    }

    public void setDataInstrumentos(Data dataInstrumentos) {
        this.dataInstrumentos = dataInstrumentos;
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
           XMLInst.UpdateTipoInstrumento( inst);
           ServiceTipoInstrumento.instance().update(inst);
       }
       else{
            XMLInst.AddTipoInstrumento(inst);
            ServiceTipoInstrumento.instance().create(inst);
       }
    }
     
    public List<TipoInstrumento> returnList(){
        return dataInstrumentos.getTiposInstrumentos();
    }
    
    public void deleteInstrumento(TipoInstrumento inst) throws TransformerException, Exception{
        if(ServiceTipoInstrumento.instance().ExistInstrumento(inst)){
           XMLInst.deleteTipoInstrumento(inst);
           ServiceTipoInstrumento.instance().delete(inst);
        }
    }
    public void recoverList(){
        XMLInst.recoverTipoInstrumento(returnList());
    }
    public void addInstrumento(Instrumento instrumento) throws Exception{
        int pos = ServiceTipoInstrumento.instance().getPosicionPorNombre(instrumento.getTipo());
        if(pos != -1){
        if(ServiceInstrumento.instance().ExistInstrumento(instrumento,pos)){
//           XMLInst.UpdateInstrumento( instrumento);
           ServiceInstrumento.instance().update(instrumento,pos);
       }
       else{
//            XMLInst.AddInstrumento(instrumento);
            ServiceInstrumento.instance().create(instrumento,pos);
       }
       }
    }
    public List<Instrumento> returnListInstrumento(String tipo){
        return dataInstrumentos.getInstrumentos( ServiceTipoInstrumento.instance().getPosicionPorNombre(tipo));
    }
  /*  public void addCalibracion(Calibraciones calibracion) throws Exception{
         if(ServiceCalibraciones.instance().ExistCalibraciones(calibracion)){
           XMLCali.UpdateTipoInstrumento( calibracion);
           ServiceCalibraciones.instance().update(calibracion);
       }
       else{
            XMLCali.AddCalibracion(calibracion);
            ServiceCalibraciones.instance().create(calibracion);
       }
    }*/
        
       
}

