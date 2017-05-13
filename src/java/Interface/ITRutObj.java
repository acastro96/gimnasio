/*
 * Proyecto Gimnasio Virtual. 
 * Universidad Simón Bolívar - Barranquilla / Colombia.
 * Desarrollado por Ing. Alberto Castro Maestre
 */
package Interface;

import Pojo.GimRutObj;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Alberto Castro
 */
public interface ITRutObj {
    
    int insert(Session session, GimRutObj planRut)throws Exception;
    
    boolean update(Session session, GimRutObj planRut)throws Exception;
    
    List<GimRutObj> getAll(Session session)throws Exception;
    
    List<GimRutObj> getPlanRutObjIdRutina(Session session, BigDecimal idRutina) throws Exception;
    
}
