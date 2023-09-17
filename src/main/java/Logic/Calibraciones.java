
package Logic;

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
  
    
    public Calibraciones(String numero, String mediciones, String fecha, String tipo) {
        this.numero = numero;
        this.mediciones = mediciones;
        this.fecha = fecha;
        this.tipo = tipo;
    }
    
      
    public String getTipo() {
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
        
}
