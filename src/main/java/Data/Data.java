/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data;

import Logic.Instrumento;
import Logic.TipoInstrumento;
import Logic.Calibraciones;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PABLO MORERA
 */
public class Data {
    private List<TipoInstrumento> tiposInstrumentos; 
    private List <Calibraciones> calibracion;

    public Data() {
        tiposInstrumentos = new ArrayList<>(); 
    }

    public List<TipoInstrumento> getTiposInstrumentos() {
        return tiposInstrumentos;
    }

    public void setInstrumentos(List<TipoInstrumento> instrumentos) {
        this.tiposInstrumentos = instrumentos;
    } 
    public List<Instrumento> getInstrumentos(int i){
        return tiposInstrumentos.get(i).getInstrumentos();
    }
    public List<Calibraciones> getCalibracion(){
        return calibracion;
    }
}
