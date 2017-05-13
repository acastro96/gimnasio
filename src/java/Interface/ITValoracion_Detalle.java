/*
 * Proyecto Gimnasio Virtual. 
 * Universidad Simón Bolívar - Barranquilla / Colombia.
 * Desarrollado por Ing. Alberto Castro Maestre
 */
package Interface;

import Pojo.GimValoracionDetalle;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Alberto Castro
 */
public interface ITValoracion_Detalle {
    
    int insert(Session session, GimValoracionDetalle valoracionDetalle)throws Exception;
    
    boolean update(Session session, GimValoracionDetalle valoracionDetalle)throws Exception;
    
    List<GimValoracionDetalle> getAll(Session session)throws Exception;
    
    GimValoracionDetalle getValoracion_DetalleById(Session session, BigDecimal id) throws Exception;

    List<GimValoracionDetalle> getValoracion_DetalleByIdMaestro(Session session, BigDecimal idMaestro) throws Exception;
    
}
