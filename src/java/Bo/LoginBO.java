/*
 * Proyecto Gimnasio Virtual. 
 * Universidad Simón Bolívar - Barranquilla / Colombia.
 * Desarrollado por Ing. Alberto Castro Maestre
 */
package Bo;

import Bean.LoginBean;
import Util.Modulo;
import java.util.List;

/**
 *
 * @author Alberto Castro
 */
public interface LoginBO {
    
    void logIn(LoginBean blogin) throws Exception;
    
    List<Modulo> listarModulos(LoginBean blogin) throws Exception;
    
    List<String> listarRecursos(LoginBean obj) throws Exception;
}
