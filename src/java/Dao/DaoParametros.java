/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Interface.ITParametros;
import Pojo.GimParametros;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author acastro
 */
public class DaoParametros implements ITParametros{

    @Override
    public int insert(Session session, GimParametros perfil) throws Exception {
        return Integer.parseInt(session.save(perfil).toString());
    }

    @Override
    public boolean update(Session session, GimParametros perfil) throws Exception {
        session.update(perfil);
        return true;
    }

    @Override
    public List<GimParametros> getAll(Session session) throws Exception {
        return session.createCriteria(GimParametros.class).list();
    }

    @Override
    public GimParametros getParametroById(Session session, BigDecimal id) throws Exception {
        return (GimParametros) session.createCriteria(GimParametros.class)
                .add(Restrictions.eq("parId", id))
                .uniqueResult();
    }

    @Override
    public List<GimParametros> getParametroByGrupo(Session session, BigDecimal idGrupo) throws Exception {
        return  session.createCriteria(GimParametros.class)
                .add(Restrictions.eq("gimGrupos.gruId", idGrupo))
                .list();
    }
    
}
