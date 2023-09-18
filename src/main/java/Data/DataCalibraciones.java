/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data;

import Logic.Calibraciones;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author David
 */
public class DataCalibraciones {
        private List<Calibraciones> calibraciones; 

    public DataCalibraciones() {
        calibraciones = new ArrayList<>(); 
    }

    public List<Calibraciones> getCalibraciones() {
        return calibraciones;
    }

    public void setCalibraciones(List<Calibraciones> calibracioens) {
        this.calibraciones = calibraciones;
    }
    
}
