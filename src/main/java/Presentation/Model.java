/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Presentation;

import Data.Data;
import Logic.TipoInstrumento;
import Logic.Service;
import XML_DOM.XMLIntrumentos;
import java.util.List;
import java.util.Observer;
import javax.xml.transform.TransformerException;

/**
 *
 * @author PABLO MORERA
 */
public class Model extends java.util.Observable{
    private Data dataInstrumentos;
    private TipoInstrumento actual;
    private int changedProps;
    private XMLIntrumentos XMLInst;
    
//    public static int NONE=0;
//    public static int LIST=1;
//    public static int CURRENT=2;

    public Model() {
        changedProps = 0;
        dataInstrumentos = new Data();
        Service.instance().uptadeData(dataInstrumentos);
    }
    
      public void CreateUserFile()
    {
        XMLInst = new XMLIntrumentos("Instrumentos.xml");
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
 
     public void addInstrumento(TipoInstrumento inst) throws Exception {
     
       if(Service.instance().ExistInstrumento(inst)){
           XMLInst.UpdateInstrumento( inst);
           Service.instance().update(inst);
       }
       else{
            XMLInst.AddInstrumento(inst);
            Service.instance().create(inst);
       }
    }
     
    public List<TipoInstrumento> returnList(){
        return dataInstrumentos.getInstrumentos();
    }
    
    public void deleteInstrumento(TipoInstrumento inst) throws TransformerException, Exception{
        if(Service.instance().ExistInstrumento(inst)){
           XMLInst.deleteTipoInstrumento(inst);
           Service.instance().delete(inst);
        }
        else
            return;
    }
}
