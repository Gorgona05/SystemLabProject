
package Logic;

/**
 *
 * @author XPC
 */
public class Calibraciones {
    private String numero;
    private String mediciones;
    private String fecha;

    public Calibraciones(){
        this.numero =" ";
        this.mediciones= " ";  
        this.fecha = " ";
    }
        
    public Calibraciones(String numero, String mediciones, String fecha) {
        this.numero = numero;
        this.mediciones = mediciones;
        this.fecha = fecha;
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
