
package Logic.Service;

import Data.Data;
import Logic.Calibraciones;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author David
 */
public class ServiceCalibraciones {
      private static ServiceCalibraciones theInstance;
      private Data data;

    public static ServiceCalibraciones instance(){
        if (theInstance == null) theInstance = new ServiceCalibraciones();
        return theInstance;
    }
    
    private ServiceCalibraciones(){
    }
    
    public void uptadeData(Data dat){
        data = dat;
    }

    
}
