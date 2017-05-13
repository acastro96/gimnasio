/*
 * Proyecto Gimnasio Virtual. 
 * Universidad Simón Bolívar - Barranquilla / Colombia.
 * Desarrollado por Ing. Alberto Castro Maestre
 */
package Interface;

import Pojo.GimRecurso;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Alberto Castro
 */
public interface ITRecurso {
    
    int insert(Session session, GimRecurso recurso)throws Exception;
    
    boolean update(Session session, GimRecurso recurso)throws Exception;
    
    List<GimRecurso> getAll(Session session)throws Exception;
    
    GimRecurso getRecursoById(Session session, BigDecimal id) throws Exception;
}
