/*
 * Proyecto Gimnasio Virtual. 
 * Universidad Simón Bolívar - Barranquilla / Colombia.
 * Desarrollado por Ing. Alberto Castro Maestre
 */
package Dao;

import Interface.ITGrupo;
import Pojo.GimGrupos;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Alberto Castro
 */
public class DaoGrupo implements ITGrupo {

    @Override
    public int insert(Session session, GimGrupos grupos) throws Exception {
        return Integer.parseInt(session.save(grupos).toString());
    }

    @Override
    public boolean update(Session session, GimGrupos grupos) throws Exception {
        session.update(grupos);
        return true;
    }

    @Override
    public List<GimGrupos> getAll(Session session) throws Exception {
        return session.createCriteria(GimGrupos.class).list();
    }

    @Override
    public GimGrupos getGruposById(Session session, BigDecimal id) throws Exception {
        return (GimGrupos) session.createCriteria(GimGrupos.class)
                .add(Restrictions.eq("gruid", id))
                .uniqueResult();
    }
    
}
