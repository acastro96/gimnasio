/*
 * Proyecto Gimnasio Virtual. 
 * Universidad Simón Bolívar - Barranquilla / Colombia.
 * Desarrollado por Ing. Alberto Castro Maestre
 */
package Dao;

import Interface.ITPerfil;
import Pojo.GimPerfil;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Alberto Castro
 */
public class DaoPerfil implements ITPerfil {

    @Override
    public int insert(Session session, GimPerfil perfil) throws Exception {
        return Integer.parseInt(session.save(perfil).toString());
    }

    @Override
    public boolean update(Session session, GimPerfil perfil) throws Exception {
        session.update(perfil);
        return true;
    }

    @Override
    public List<GimPerfil> getAll(Session session) throws Exception {
        return session.createCriteria(GimPerfil.class).list();
    }

    @Override
    public GimPerfil getPerfilById(Session session, BigDecimal id) throws Exception {
        return (GimPerfil) session.createCriteria(GimPerfil.class)
                .add(Restrictions.eq("perfId", id))
                .uniqueResult();
    }
    
}
