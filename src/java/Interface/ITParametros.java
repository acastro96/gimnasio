/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Pojo.GimParametros;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author acastro
 */
public interface ITParametros {
    
    int insert(Session session, GimParametros perfil)throws Exception;
    
    boolean update(Session session, GimParametros perfil)throws Exception;
    
    List<GimParametros> getAll(Session session)throws Exception;
    
    GimParametros getParametroById(Session session, BigDecimal id) throws Exception;
    
    List<GimParametros> getParametroByGrupo(Session session, BigDecimal idGrupo) throws Exception;
    
}
