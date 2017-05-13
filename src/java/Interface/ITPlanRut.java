/*
 * Proyecto Gimnasio Virtual. 
 * Universidad Simón Bolívar - Barranquilla / Colombia.
 * Desarrollado por Ing. Alberto Castro Maestre
 */
package Interface;

import Pojo.GimPlanRut;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Alberto Castro
 */
public interface ITPlanRut {
    
    int insert(Session session, GimPlanRut planRut)throws Exception;
    
    boolean update(Session session, GimPlanRut planRut)throws Exception;
    
    List<GimPlanRut> getAll(Session session)throws Exception;
    
    List<GimPlanRut> getPlanRutByIdPlan(Session session, BigDecimal idPlan) throws Exception;
    
}
