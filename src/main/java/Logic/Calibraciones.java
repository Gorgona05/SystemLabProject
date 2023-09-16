/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logic;

/**
 *
 * @author XPC
 */
public class Calibraciones {
    private int numero;
    private int mediciones;
    
    public Calibraciones(){
        this.numero =0;
        this.mediciones= 0;        
    }
        
    public Calibraciones(int numero, int mediciones) {
        this.numero = numero;
        this.mediciones = mediciones;
    }
    
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getMediciones() {
        return mediciones;
    }

    public void setMediciones(int mediciones) {
        this.mediciones = mediciones;
    }
    
    
    
}
