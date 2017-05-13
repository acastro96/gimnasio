/*
 * Proyecto Gimnasio Virtual. 
 * Universidad Simón Bolívar - Barranquilla / Colombia.
 * Desarrollado por Ing. Alberto Castro Maestre
 */
package Interface;

import Pojo.GimCliente;
import java.math.BigDecimal;
import org.hibernate.Session;
import java.util.List;
/**
 *
 * @author Alberto Castro
 */
public interface ITCliente {
    
    int insert(Session session, GimCliente cliente)throws Exception;
    
    boolean update(Session session, GimCliente cliente)throws Exception;
    
    List<GimCliente> getAll(Session session)throws Exception;
    
    GimCliente getClienteByID(Session session, BigDecimal id) throws Exception;
    
    GimCliente getClienteByIdPersona(Session session, BigDecimal idPersona) throws Exception;
    
    GimCliente getClienteByCod(Session session, String cod)throws Exception;
    
}
