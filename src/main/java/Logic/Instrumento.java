
package Logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author PABLO MORERA
 */
public class Instrumento {
    private String serie;
    private String tipo;
    private String descripcion;
    private String minimo;
    private String maximo;
    private String tolerancia;
    
     public Instrumento() {
        this("","","","","","");
    }  
        
    public Instrumento(String serie, String tipo, String descripcion, String minimo, String maximo, String tolerancia) {
        this.serie = serie;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.minimo = minimo;
        this.maximo = maximo;
        this.tolerancia = tolerancia;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMinimo() {
        return minimo;
    }

    public void setMinimo(String minimo) {
        this.minimo = minimo;
    }

    public String getMaximo() {
        return maximo;
    }

    public void setMaximo(String maximo) {
        this.maximo = maximo;
    }

    public String getTolerancia() {
        return tolerancia;
    }

    public void setTolerancia(String tolerancia) {
        this.tolerancia = tolerancia;
    }
    
    @Override
        public String toString() {
            return "Número de Serie: " + serie + "\n" +
                   "Descripción: " + descripcion + "\n" +
                   "Mínimo: " + minimo + "\n" +
                   "Máximo: " + maximo + "\n" +
                   "Tolerancia: " + tolerancia + "\n";
}
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.serie);
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
        final Instrumento other = (Instrumento) obj;
        if (!Objects.equals(this.serie, other.serie)) {
            return false;
        }
        return true;
    }
        
}
