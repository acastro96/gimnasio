/*
 * Proyecto Gimnasio Virtual. 
 * Universidad Simón Bolívar - Barranquilla / Colombia.
 * Desarrollado por Ing. Alberto Castro Maestre
 */
package Interface;

/**
 *
 * @author Alberto Castro
 */

import Pojo.GimMembresia;
import java.math.BigDecimal;
import org.hibernate.Session;
import java.util.List;

public interface ITMembresia {
    
    int insert(Session session, GimMembresia membresia)throws Exception;
    
    boolean update(Session session, GimMembresia membresia)throws Exception;
    
    List<GimMembresia> getAll(Session session)throws Exception;
    
    GimMembresia getMembresiaByID(Session session, BigDecimal id) throws Exception;
    
    
}
