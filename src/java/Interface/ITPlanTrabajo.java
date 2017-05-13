/*
 * Proyecto Gimnasio Virtual. 
 * Universidad Simón Bolívar - Barranquilla / Colombia.
 * Desarrollado por Ing. Alberto Castro Maestre
 */
package Interface;

import org.hibernate.Session;
import java.util.List;
import Pojo.GimPlanTrabajo;
import java.math.BigDecimal;

/**
 *
 * @author Alberto Castro
 */
public interface ITPlanTrabajo {
    
    int insert(Session session, GimPlanTrabajo planTrabajo)throws Exception;
    
    boolean update(Session session, GimPlanTrabajo planTrabajo)throws Exception;
    
    List<GimPlanTrabajo> getAll(Session session)throws Exception;
    
    GimPlanTrabajo getPlanTrabajoByID(Session session, BigDecimal id) throws Exception;
    
    GimPlanTrabajo getPlanTrabajoByNom(Session session, String nom) throws Exception;
    
    GimPlanTrabajo getPlanTrabajoByCod(Session session, String cod) throws Exception;
    
    List<GimPlanTrabajo> getlistPlanTrabajoByNom(Session session, String nom) throws Exception;
}
