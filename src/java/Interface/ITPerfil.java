/*
 * Proyecto Gimnasio Virtual. 
 * Universidad Simón Bolívar - Barranquilla / Colombia.
 * Desarrollado por Ing. Alberto Castro Maestre
 */
package Interface;

import Pojo.GimPerfil;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Alberto Castro
 */
public interface ITPerfil {
    
    int insert(Session session, GimPerfil perfil)throws Exception;
    
    boolean update(Session session, GimPerfil perfil)throws Exception;
    
    List<GimPerfil> getAll(Session session)throws Exception;
    
    GimPerfil getPerfilById(Session session, BigDecimal id) throws Exception;
    
}
