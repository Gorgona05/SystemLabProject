/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data;

import Logic.TipoInstrumento;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PABLO MORERA
 */
public class Data {
    private List<TipoInstrumento> instrumentos; 

    public Data() {
        instrumentos = new ArrayList<>();
        instrumentos.add(new TipoInstrumento("TER","Termómetro","Grados Celcius"));
        instrumentos.add(new TipoInstrumento("BAR","Barómetro","PSI"));
    }

    public List<TipoInstrumento> getInstrumentos() {
        return instrumentos;
    }

    public void setInstrumentos(List<TipoInstrumento> instrumentos) {
        this.instrumentos = instrumentos;
    }  
}
