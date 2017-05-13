/*
 * Proyecto Gimnasio Virtual. 
 * Universidad Simón Bolívar - Barranquilla / Colombia.
 * Desarrollado por Ing. Alberto Castro Maestre
 */
package Interface;

import Pojo.GimMemUsu;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Alberto Castro
 */
public interface ITMemUsu {
    
    int insert(Session session, GimMemUsu memUsu)throws Exception;
    
    boolean update(Session session, GimMemUsu memUsu)throws Exception;
    
    List<GimMemUsu> getAll(Session session)throws Exception;
    
    List<GimMemUsu> getMemUsuByIdUsuario(Session session, BigDecimal idUsuario) throws Exception;
    
    List<GimMemUsu> getMemUsuByIdMembresia(Session session, BigDecimal idMembresia) throws Exception;
    
}
