/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data;

import Logic.Instrumento;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author David
 */
public class DataInstrumento {
    private List<Instrumento> instrumentos; 

    public DataInstrumento() {
        instrumentos = new ArrayList<>(); 
    }

    public List<Instrumento> getInstrumento() {
        return instrumentos;
    }

    public void setInstrumentos(List<Instrumento> instrumentos) {
        this.instrumentos = instrumentos;
    } 
}
