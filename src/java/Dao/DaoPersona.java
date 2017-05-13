/*
 * Proyecto Gimnasio Virtual. 
 * Universidad Simón Bolívar - Barranquilla / Colombia.
 * Desarrollado por Ing. Alberto Castro Maestre
 */
package Dao;

import Interface.ITPersona;
import Pojo.GimPersona;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Alberto Castro
 */
public class DaoPersona implements ITPersona{

    @Override
    public int insert(Session session, GimPersona persona) throws Exception {
        return Integer.parseInt(session.save(persona).toString());
    }
    
    /*
    @Override
    public GimPersona insert(Session session, GimPersona persona) throws Exception {
        return (GimPersona) session.save(persona);
    }
    */

    @Override
    public boolean update(Session session, GimPersona persona) throws Exception {
        session.update(persona);
        return true;
    }

    @Override
    public List<GimPersona> getAll(Session session) throws Exception {
        return session.createCriteria(GimPersona.class).list();
    }

    @Override
    public GimPersona getPersonaByID(Session session, BigDecimal id) throws Exception {
        return (GimPersona) session.createCriteria(GimPersona.class)
                .add(Restrictions.eq("persId", id))
                .uniqueResult();
    }

    @Override
    public GimPersona getPersonaByDoc(Session session, String doc) throws Exception {
        return (GimPersona) session.createCriteria(GimPersona.class)
                .add(Restrictions.eq("persNumerodocumento", doc))
                .uniqueResult();
    }
    
    
    
}
