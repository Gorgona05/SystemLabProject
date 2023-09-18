/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logic;

import java.util.Objects;

/**
 *
 * @author David
 */
public class Mediciones {
    private String medida;
    private String referencia;
    private String lectura;
    private String tipo;
    
      public Mediciones(){
        this.medida =" ";
        this.referencia = " ";  
        this.lectura = " ";
        this.tipo = " ";
    }
	
    public Mediciones(String medida, String referencia, String lectura,String tipo) {
        this.medida = medida;
        this.referencia = referencia;
        this.lectura = lectura;   
        this.tipo = tipo;
    }    
      
     public String getTipo(){
        return tipo;
    }
     
    public String getMedida(){
        return medida;
    }
    
    public String getReferencia() {
        return referencia;
    }

     public String getLectura() {
        return lectura;
    }
     
    public void setTipo(){
         this.tipo = tipo;
    }
     
    public void setLectura(String lectura) {
        this.lectura = lectura;
    }
    
    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public void setMedida(String medida) {
        this.medida = medida;
    }
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.medida);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Mediciones other = (Mediciones) obj;
        if (!Objects.equals(this.medida, other.medida)) {
            return false;
        }
        return true;
    }
}
