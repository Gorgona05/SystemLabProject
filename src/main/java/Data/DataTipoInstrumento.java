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
public class DataTipoInstrumento {
    private List<TipoInstrumento> tiposInstrumentos; 

    public DataTipoInstrumento() {
        tiposInstrumentos = new ArrayList<>(); 
    }

    public List<TipoInstrumento> getTiposInstrumentos() {
        return tiposInstrumentos;
    }

    public void setTipoInstrumentos(List<TipoInstrumento> tipoInstrumentos) {
        this.tiposInstrumentos = tipoInstrumentos;
    } 

}
