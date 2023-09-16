
package Logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author PABLO MORERA
 */
public class TipoInstrumento {
    private List<Instrumento> instrumentos;
    private String codigo;
    private String nombre;
    private String unidad;

    public TipoInstrumento() {
        this("","","");
        instrumentos = new ArrayList<>(); 
    }  
    
    public TipoInstrumento(String codigo, String nombre, String unidad) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.unidad = unidad;
         instrumentos = new ArrayList<>(); 
    }
    
    public List<Instrumento> getInstrumentos(){
        return instrumentos;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }
    
     public String toString(){
        return this.nombre;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.codigo);
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
        final TipoInstrumento other = (TipoInstrumento) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        return true;
    }
}
