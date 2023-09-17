/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Presentation.Model;

import Data.DataInstrumento;
import Logic.Instrumento;
import Logic.Service.ServiceInstrumento;
import XML_DOM.XMLInstrumento;
import java.util.List;
import java.util.Observer;
import javax.xml.transform.TransformerException;


/**
 *
 * @author David
 */
public class ModelInstrumento extends java.util.Observable {
    private DataInstrumento dataInstrumentos;
    private Instrumento actual;
    private int changedProps;
    private XMLInstrumento XMLInstrumentos;
    
    public ModelInstrumento() {
        changedProps = 0;
        dataInstrumentos = new DataInstrumento();
        ServiceInstrumento.instance().uptadeData(dataInstrumentos);
    }
    
    public void CreateUserFile(){
        XMLInstrumentos = new XMLInstrumento("Instrumentos.xml");
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
    public void init(List<Instrumento> list){
        dataInstrumentos.setInstrumentos(list);
        setActual(new Instrumento());
        
    }
    
     public DataInstrumento getDataInstrumentos() {
        return dataInstrumentos;
    }

    public void setDataInstrumentos(DataInstrumento dataInstrumentos) {
        this.dataInstrumentos = dataInstrumentos;
        changedProps += 1;
    }

    public Instrumento getActual() {
        return actual;
    }

    public void setActual(Instrumento actual) {
        changedProps += 2;
        this.actual = actual;
    }

    public int getChangedProps() {
        return changedProps;
    }

    public void setChangedProps(int changedProps) {
        this.changedProps = changedProps;
    }
    
     public void addInstrumento(Instrumento instrumento) throws Exception{
        if(ServiceInstrumento.instance().ExistInstrumento(instrumento)){
           XMLInstrumentos.UpdateInstrumento( instrumento);
           ServiceInstrumento.instance().update(instrumento);
       }
       else{
            XMLInstrumentos.AddInstrumento(instrumento);
            ServiceInstrumento.instance().create(instrumento);
       }
    }
     
    public void deleteInstrumento(Instrumento instrumento) throws TransformerException, Exception{
       if(ServiceInstrumento.instance().ExistInstrumento(instrumento)){
           XMLInstrumentos.deleteInstrumento(instrumento);
           ServiceInstrumento.instance().delete(instrumento);
        }
    }   
      
    public List<Instrumento> returnListInstrumento(){
        return dataInstrumentos.getInstrumento();
    }
    
     public void recoverList(){
        XMLInstrumentos.recoverInstrumento(returnListInstrumento());
    }
    
}
