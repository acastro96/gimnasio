/*
 * Proyecto Gimnasio Virtual. 
 * Universidad Simón Bolívar - Barranquilla / Colombia.
 * Desarrollado por Ing. Alberto Castro Maestre
 */
package Interface;

import org.hibernate.Session;
import java.util.List;
import Pojo.GimVideo;
import java.math.BigDecimal;

/**
 *
 * @author Alberto Castro
 */
public interface ITVideo {
    
    int insert(Session session, GimVideo video)throws Exception;
    
    boolean update(Session session, GimVideo video)throws Exception;
    
    List<GimVideo> getAll(Session session)throws Exception;
    
    GimVideo getVideoByID(Session session, BigDecimal id) throws Exception;
   
}
