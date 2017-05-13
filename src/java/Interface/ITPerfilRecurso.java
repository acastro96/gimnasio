/*
 * Proyecto Gimnasio Virtual. 
 * Universidad Simón Bolívar - Barranquilla / Colombia.
 * Desarrollado por Ing. Alberto Castro Maestre
 */
package Interface;

import Pojo.GimPerfilrecurso;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Alberto Castro
 */
public interface ITPerfilRecurso {
    
    int insert(Session session, GimPerfilrecurso perfilrecurso)throws Exception;
    
    boolean update(Session session, GimPerfilrecurso perfilrecurso)throws Exception;
    
    List<GimPerfilrecurso> getAll(Session session)throws Exception;
    
    List<GimPerfilrecurso> getPerfilRecursoByIdPerfil(Session session, BigDecimal idPerfil) throws Exception;
    
}
