/*
 * Proyecto Gimnasio Virtual. 
 * Universidad Simón Bolívar - Barranquilla / Colombia.
 * Desarrollado por Ing. Alberto Castro Maestre
 */
package Interface;

import Pojo.GimModulo;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Alberto Castro
 */
public interface ITModulo {
    
    int insert(Session session, GimModulo modulo)throws Exception;
    
    boolean update(Session session, GimModulo modulo)throws Exception;
    
    List<GimModulo> getAll(Session session)throws Exception;
    
    GimModulo getModuloById(Session session, BigDecimal id) throws Exception;
}
