/*
 * Proyecto Gimnasio Virtual. 
 * Universidad Simón Bolívar - Barranquilla / Colombia.
 * Desarrollado por Ing. Alberto Castro Maestre
 */
package Interface;

import Pojo.GimPlanCliente;
import Pojo.GimPlanClienteId;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Alberto Castro
 */
public interface ITPlanCliente {
    
    void insert(Session session, GimPlanCliente planCliente)throws Exception;
    
    boolean update(Session session, GimPlanCliente planCliente)throws Exception;
    
    List<GimPlanCliente> getAll(Session session) throws Exception;
    
    List<GimPlanCliente> getPlanCliByIDCliente(Session session, BigDecimal idCliente) throws Exception;
    
    List<GimPlanCliente> getPlanCliByIDPlan(Session session, BigDecimal idPlan) throws Exception;
    
    GimPlanCliente getPlanCliByIDPlanIDCli (Session session, BigDecimal idPlan, BigDecimal idCliente) throws Exception;
    
    GimPlanCliente getPlanClienteByID (Session session, GimPlanClienteId id) throws Exception;
    
    GimPlanCliente getPlanCliByIDClienteActivo(Session session, BigDecimal idCliente) throws Exception;
    
}
