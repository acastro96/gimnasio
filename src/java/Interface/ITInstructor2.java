/*
 * Proyecto Gimnasio Virtual. 
 * Universidad Simón Bolívar - Barranquilla / Colombia.
 * Desarrollado por Ing. Alberto Castro Maestre
 */
package Interface;

import Pojo.GimInstructor;
import org.hibernate.Session;

/**
 *
 * @author Alberto Castro
 */
public interface ITInstructor2 {
    
    public int insert(Session session, GimInstructor instructor) throws Exception;
    
    public boolean update(Session session, GimInstructor instructor) throws Exception;
    
}
