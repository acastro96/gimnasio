/*
 * Proyecto Gimnasio Virtual. 
 * Universidad Simón Bolívar - Barranquilla / Colombia.
 * Desarrollado por Ing. Alberto Castro Maestre
 */
package Dao;

import Interface.ITRecurso;
import Pojo.GimRecurso;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Alberto Castro
 */
public class DaoRecurso implements ITRecurso{

    @Override
    public int insert(Session session, GimRecurso recurso) throws Exception {
        return Integer.parseInt(session.save(recurso).toString());
    }

    @Override
    public boolean update(Session session, GimRecurso recurso) throws Exception {
        session.update(recurso);
        return true;
    }

    @Override
    public List<GimRecurso> getAll(Session session) throws Exception {
        return session.createCriteria(GimRecurso.class).list();
    }

    @Override
    public GimRecurso getRecursoById(Session session, BigDecimal id) throws Exception {
        return (GimRecurso) session.createCriteria(GimRecurso.class)
                .add(Restrictions.eq("recid", id))
                .uniqueResult();
    }
    
}
