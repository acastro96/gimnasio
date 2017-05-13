/*
 * Proyecto Gimnasio Virtual. 
 * Universidad Simón Bolívar - Barranquilla / Colombia.
 * Desarrollado por Ing. Alberto Castro Maestre
 */
package Bo;

import Bean.InstructoresBean;
import Pojo.GimInstructor;
import java.util.List;

/**
 *
 * @author Alberto Castro
 */
public interface InstructoresBO {
    
    void consultarInstructor(InstructoresBean instructoresBean) throws Exception;
    
    void agregarInstructor(InstructoresBean instructoresBean) throws Exception;
    
    void actualizarInstructor(InstructoresBean instructoresBean) throws Exception;
    
    List<GimInstructor> listaInstructores(InstructoresBean instructoresBean) throws Exception;
    
    void llenarParametros(InstructoresBean instructoresBean) throws Exception;
    
    void mostrarInstructor(InstructoresBean instructoresBean) throws Exception;
}
