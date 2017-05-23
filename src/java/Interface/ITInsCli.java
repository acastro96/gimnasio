/*
 * Proyecto Gimnasio Virtual. 
 * Universidad Simón Bolívar - Barranquilla / Colombia.
 * Desarrollado por Ing. Alberto Castro Maestre
 */
package Interface;

import Pojo.GimInsCli;
import Pojo.GimInsCliId;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Alberto Castro
 */
public interface ITInsCli {
    
    void insert(Session session, GimInsCli insCli)throws Exception;
    
    boolean update(Session session, GimInsCli insCli)throws Exception;
    
    List<GimInsCli> getAll(Session session) throws Exception;
    
    List<GimInsCli> getInsCliByIDInstructor(Session session, BigDecimal idInstructor) throws Exception;
    
    List<GimInsCli> getInsCliByIDCliente(Session session, BigDecimal idCliente) throws Exception;
    
    GimInsCli getInsCliByIDInstructorIDCli(Session session, BigDecimal idInstructor, BigDecimal idCliente) throws Exception;
    
    GimInsCli getInsCliByID(Session session, GimInsCliId id) throws Exception;
    
}
