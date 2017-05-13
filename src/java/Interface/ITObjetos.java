/*
 * Proyecto Gimnasio Virtual. 
 * Universidad Simón Bolívar - Barranquilla / Colombia.
 * Desarrollado por Ing. Alberto Castro Maestre
 */
package Interface;

import org.hibernate.Session;
import java.util.List;
import Pojo.GimObjetos;
import java.math.BigDecimal;

/**
 *
 * @author Alberto Castro
 */
public interface ITObjetos {
    
    int insert(Session session, GimObjetos objetos)throws Exception;
    
    boolean update(Session session, GimObjetos objetos)throws Exception;
    
    List<GimObjetos> getAll(Session session)throws Exception;
    
    GimObjetos getObjetoById(Session session, BigDecimal id) throws Exception;
    
    GimObjetos getObjetoByCod(Session session, String cod) throws Exception;
    
    GimObjetos getObjetoByNom(Session session, String nom)throws Exception;
    
}
