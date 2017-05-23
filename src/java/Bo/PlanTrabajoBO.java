/*
 * Proyecto Gimnasio Virtual. 
 * Universidad Simón Bolívar - Barranquilla / Colombia.
 * Desarrollado por Ing. Alberto Castro Maestre
 */
package Bo;

import Bean.PlanTrabajoBean;
import Pojo.GimPlanTrabajo;
import java.util.List;

/**
 *
 * @author Alberto Castro
 */
public interface PlanTrabajoBO {
    
    List<GimPlanTrabajo> listarPlanTrabajo(PlanTrabajoBean planTrabajoBean) throws Exception;
    
    void agregarPlanTrabajo(PlanTrabajoBean planTrabajoBean) throws Exception;
    
    void actualizarPlanTrabajo(PlanTrabajoBean planTrabajoBean) throws Exception;
    
    void agregarPlanRut(PlanTrabajoBean planTrabajoBean) throws Exception;
    
    void mostrarPlanTrabajo(PlanTrabajoBean planTrabajoBean) throws Exception;
    
    void llenarParametros (PlanTrabajoBean planTrabajoBean) throws Exception;
    
    void consultarRutina(PlanTrabajoBean planTrabajoBean) throws Exception;
    
    void cargarPlanCliente(PlanTrabajoBean planTrabajoBean) throws Exception;
    
    
}
