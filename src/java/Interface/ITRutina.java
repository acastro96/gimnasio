/*
 * Proyecto Gimnasio Virtual. 
 * Universidad Simón Bolívar - Barranquilla / Colombia.
 * Desarrollado por Ing. Alberto Castro Maestre
 */
package Interface;

import org.hibernate.Session;
import java.util.List;
import Pojo.GimRutina;
import java.math.BigDecimal;

/**
 *
 * @author Alberto Castro
 */
public interface ITRutina {
    
    int insert(Session session, GimRutina rutina)throws Exception;
    
    boolean update(Session session, GimRutina rutina)throws Exception;
    
    List<GimRutina> getAll(Session session)throws Exception;
    
    GimRutina getRutinaByID(Session session, BigDecimal id) throws Exception;
    
    GimRutina getRutinaByNom(Session session, String nom) throws Exception;
    
    GimRutina getRutinaByCod(Session session, String cod) throws Exception;
    
}
