/*
 * Proyecto Gimnasio Virtual. 
 * Universidad Simón Bolívar - Barranquilla / Colombia.
 * Desarrollado por Ing. Alberto Castro Maestre
 */
package Interface;

import Pojo.GimValoracionMaestro;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Alberto Castro
 */
public interface ITValoracion_Maestro {
    
    int insert(Session session, GimValoracionMaestro valoracionMaestro)throws Exception;
    
    boolean update(Session session, GimValoracionMaestro valoracionMaestro)throws Exception;
    
    List<GimValoracionMaestro> getAll(Session session)throws Exception;
    
    GimValoracionMaestro getValoracion_MaestroById(Session session, BigDecimal id) throws Exception;
    
    List<GimValoracionMaestro> getValoracionesByInstructor(Session session, BigDecimal instId) throws Exception;
    
    List<GimValoracionMaestro> getValoracionesByCliente(Session session, BigDecimal cliId) throws Exception;
    
}
