/*
 * Proyecto Gimnasio Virtual. 
 * Universidad Simón Bolívar - Barranquilla / Colombia.
 * Desarrollado por Ing. Alberto Castro Maestre
 */
package Interface;

import Pojo.GimDireccion;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Alberto Castro
 */
public interface ITDireccion {
    
    int insert(Session session, GimDireccion direccion)throws Exception;
    
    boolean update(Session session, GimDireccion direccion)throws Exception;
    
    List<GimDireccion> getAll(Session session)throws Exception;
    
    List<GimDireccion> getAllByPersonaByID(Session session, BigDecimal idPersona) throws Exception;
    
    GimDireccion getByIdPersona(Session session, BigDecimal idPersona) throws Exception;
}
