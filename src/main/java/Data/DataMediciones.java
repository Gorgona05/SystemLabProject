/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data;

import Logic.Mediciones;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author David
 */
public class DataMediciones {
     private List<Mediciones> mediciones; 

    public DataMediciones() {
        mediciones = new ArrayList<>(); 
    }

    public List<Mediciones> getMediciones() {
        return mediciones;
    }

    public void setMediciones(List<Mediciones> mediciones) {
        this.mediciones = mediciones;
    } 
}
