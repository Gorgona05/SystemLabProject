/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logic.Service;

import Data.Data;
import Logic.TipoInstrumento;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Pamela
 */

public class ServiceTipoInstrumento {
    private static ServiceTipoInstrumento theInstance;

    public static ServiceTipoInstrumento instance(){
        if (theInstance == null) theInstance = new ServiceTipoInstrumento();
        return theInstance;
    }
    private Data data;

    private ServiceTipoInstrumento(){
    }
    
    public void uptadeData(Data dat){
        data = dat;
    }

    //================= TIPOS DE INSTRUMENTO ============
    public void create(TipoInstrumento e) throws Exception{
        TipoInstrumento result = data.getTiposInstrumentos().stream()
                .filter(i->i.getCodigo().equals(e.getCodigo())).findFirst().orElse(null);
        if (result==null) data.getTiposInstrumentos().add(e);
        else throw new Exception("Tipo ya existe");
    }

    public TipoInstrumento read(TipoInstrumento e) throws Exception{
        TipoInstrumento result = data.getTiposInstrumentos().stream()
                .filter(i->i.getCodigo().equals(e.getCodigo())).findFirst().orElse(null);
        if (result!=null) return result;
        else throw new Exception("Tipo no existe");
    }

    public void update(TipoInstrumento e) throws Exception{
        TipoInstrumento result;
        try{
            result = this.read(e);
            data.getTiposInstrumentos().remove(result);
            data.getTiposInstrumentos().add(e);
        }catch (Exception ex) {
            throw new Exception("Tipo no existe");
        }
    }

    public void delete(TipoInstrumento e) throws Exception{
        data.getTiposInstrumentos().remove(e);
     }

    public List<TipoInstrumento> search(TipoInstrumento e){
        return data.getTiposInstrumentos().stream()
                .filter(i->i.getNombre().contains(e.getNombre()))
                .sorted(Comparator.comparing(TipoInstrumento::getNombre))
                .collect(Collectors.toList());
    }
    public boolean ExistInstrumento(TipoInstrumento e){
        TipoInstrumento result = data.getTiposInstrumentos().stream()
                .filter(i->i.getCodigo().equals(e.getCodigo())).findFirst().orElse(null);
        if (result!=null) 
                return true;
        else
            return false;
    }
    public int getPosicionPorNombre(String nombre) {
        List<TipoInstrumento> instrumentos = data.getTiposInstrumentos();

        for (int i = 0; i < instrumentos.size(); i++) {
            if (instrumentos.get(i).getNombre().equals(nombre)) {
                return i;              }
        }
        return -1;  
    }
 }
