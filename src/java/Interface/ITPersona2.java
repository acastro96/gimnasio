/*
 * Proyecto Gimnasio Virtual. 
 * Universidad Simón Bolívar - Barranquilla / Colombia.
 * Desarrollado por Ing. Alberto Castro Maestre
 */
package Interface;

import Pojo.GimPersona;
import org.hibernate.Session;

/**
 *
 * @author Alberto Castro
 */
public interface ITPersona2 {
    
    public void insert(Session session, GimPersona persona) throws Exception;
    
    public boolean update(Session session, GimPersona persona) throws Exception;
    
}
