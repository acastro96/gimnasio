/*
 * Proyecto Gimnasio Virtual. 
 * Universidad Simón Bolívar - Barranquilla / Colombia.
 * Desarrollado por Ing. Alberto Castro Maestre
 */
package Interface;

import Pojo.GimAuditoria;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Alberto Castro
 */
public interface ITAuditoria {
    
    int insert(Session session, GimAuditoria auditoria)throws Exception;
    
    boolean update(Session session, GimAuditoria auditoria)throws Exception;
    
    List<GimAuditoria> getAll(Session session)throws Exception;
    
}
