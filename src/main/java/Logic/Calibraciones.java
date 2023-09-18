
package Logic;

import java.util.Objects;

/**
 *
 * @author XPC
 */
public class Calibraciones {
    private String numero;
    private String mediciones;
    private String fecha;
    private String tipo;

     public Calibraciones(){
        this.numero =" ";
        this.mediciones= " ";  
        this.fecha = " ";
        this.tipo = " ";
    }
	
    public Calibraciones(String numero, String fecha, String mediciones,String tipo) {
        this.numero = numero;
        this.fecha = fecha;
        this.mediciones = mediciones;     
        this.tipo = tipo;
    }    
      
    public String getTipo(){
        return tipo;
    }
    
    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getMediciones() {
        return mediciones;
    }

    public void setMediciones(String mediciones) {
        this.mediciones = mediciones;
    }
    
     public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.numero);
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
        final Calibraciones other = (Calibraciones) obj;
        if (!Objects.equals(this.numero, other.numero)) {
            return false;
        }
        return true;
    }
}
