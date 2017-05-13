/*
 * Proyecto Gimnasio Virtual. 
 * Universidad Simón Bolívar - Barranquilla / Colombia.
 * Desarrollado por Ing. Alberto Castro Maestre
 */
package Interface;

import Pojo.GimRecurso;
import org.hibernate.Session;
import java.util.List;
import Pojo.GimUsuario;
import java.math.BigDecimal;

/**
 *
 * @author Alberto Castro
 */
public interface ITUsuario {
    
    int insert(Session session, GimUsuario usuario)throws Exception;
    
    boolean update(Session session, GimUsuario usuario)throws Exception;
    
    List<GimUsuario> getAll(Session session)throws Exception;
    
    GimUsuario getUsuarioByID(Session session, BigDecimal id) throws Exception;
    
    GimUsuario getUsuarioByNom(Session session, String nom) throws Exception;
    
    GimUsuario getUsuarioByNamePass(Session session, String name, String pass) throws Exception;
    
    GimUsuario getUsuarioByIdPesona(Session session, BigDecimal idPersona) throws Exception;
    
    List<GimRecurso> listarRecursos(Session session, BigDecimal usu_id) throws Exception;
}
