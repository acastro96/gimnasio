/*
 * Proyecto Gimnasio Virtual. 
 * Universidad Simón Bolívar - Barranquilla / Colombia.
 * Desarrollado por Ing. Alberto Castro Maestre
 */
package Interface;

import Pojo.GimGrupos;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Alberto Castro
 */
public interface ITGrupo {
    
    int insert(Session session, GimGrupos grupos)throws Exception;
    
    boolean update(Session session, GimGrupos grupos)throws Exception;
    
    List<GimGrupos> getAll(Session session)throws Exception;
    
    GimGrupos getGruposById(Session session, BigDecimal id) throws Exception;
    
}
