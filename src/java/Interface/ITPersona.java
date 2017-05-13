/*
 * Proyecto Gimnasio Virtual. 
 * Universidad Simón Bolívar - Barranquilla / Colombia.
 * Desarrollado por Ing. Alberto Castro Maestre
 */
package Interface;

import org.hibernate.Session;
import Pojo.GimPersona;
import java.math.BigDecimal;
import java.util.List;
/**
 *
 * @author Alberto Castro
 */
public interface ITPersona {
    
    int insert(Session session, GimPersona persona)throws Exception;
    
    boolean update(Session session, GimPersona persona)throws Exception;
    
    List<GimPersona> getAll(Session session)throws Exception;
    
    GimPersona getPersonaByID(Session session, BigDecimal id) throws Exception;
    
    GimPersona getPersonaByDoc(Session session, String doc) throws Exception;
    
}
