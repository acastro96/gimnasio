/*
 * Proyecto Gimnasio Virtual. 
 * Universidad Simón Bolívar - Barranquilla / Colombia.
 * Desarrollado por Ing. Alberto Castro Maestre
 */
package Bo;

import Bean.PersonasBean;

/**
 *
 * @author Alberto Castro
 */
public interface PersonasBO {
    
    void consultarPersona(PersonasBean personasBean) throws Exception;
    
    void agregarPersona(PersonasBean personasBean) throws Exception;
    
    void actualizarPersona(PersonasBean personasBean) throws Exception;
    
    void listaPersonas(PersonasBean personasBean) throws Exception;
    
    void eliminarPersona(PersonasBean personasBean) throws Exception;
    
}
