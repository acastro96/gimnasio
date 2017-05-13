/*
 * Proyecto Gimnasio Virtual. 
 * Universidad Simón Bolívar - Barranquilla / Colombia.
 * Desarrollado por Ing. Alberto Castro Maestre
 */
package Interface;

import org.hibernate.Session;
import java.util.List;
import Pojo.GimInstructor;
import java.math.BigDecimal;

/**
 *
 * @author Alberto Castro
 */
public interface ITInstructor {
    
    int insert(Session session, GimInstructor instructor)throws Exception;
    
    boolean update(Session session, GimInstructor instructor)throws Exception;
    
    List<GimInstructor> getAll(Session session)throws Exception;
    
    GimInstructor getInstructorById(Session session, BigDecimal id) throws Exception;
    
    GimInstructor getInstructorByIdPersona(Session session, BigDecimal idPersona) throws Exception;
    
    GimInstructor getInstructorByCod(Session session, String cod)throws Exception;
    
    List<GimInstructor> getlistByNombre(Session session, String nom)throws Exception;
    
}
