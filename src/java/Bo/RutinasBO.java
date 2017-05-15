/*
 * Proyecto Gimnasio Virtual. 
 * Universidad Simón Bolívar - Barranquilla / Colombia.
 * Desarrollado por Ing. Alberto Castro Maestre
 */
package Bo;

import Bean.RutinasBean;
import Pojo.GimRutina;
import java.util.List;

/**
 *
 * @author Alberto Castro
 */
public interface RutinasBO {
    
    void mostrarRutina(RutinasBean rutinasBean) throws Exception;
    
    void agregarRutina(RutinasBean rutinasBean) throws Exception;
    
    void actualizarRutina(RutinasBean rutinasBean) throws Exception;
    
    void mostrarVideo(RutinasBean rutinasBean) throws Exception;
    
    //void mostrarVideolist(RutinasBean rutinasBean) throws Exception;
    
    List<GimRutina> listaRutinas(RutinasBean rutinasBean) throws Exception;
    
    
}
