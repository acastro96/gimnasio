/*
 * Proyecto Gimnasio Virtual. 
 * Universidad Simón Bolívar - Barranquilla / Colombia.
 * Desarrollado por Ing. Alberto Castro Maestre
 */
package Interface;

import Pojo.GimTelefono;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Alberto Castro
 */
public interface ITTelefono {
    
    int insert(Session session, GimTelefono telefono)throws Exception;
    
    boolean update(Session session, GimTelefono telefono)throws Exception;
    
    List<GimTelefono> getAll(Session session)throws Exception;
    
    List<GimTelefono> getAllByPersonaByID(Session session, BigDecimal idPersona) throws Exception;
    
    GimTelefono getByIdPersona(Session session, BigDecimal idPersona) throws Exception;
}
