/*
 * Proyecto Gimnasio Virtual. 
 * Universidad Simón Bolívar - Barranquilla / Colombia.
 * Desarrollado por Ing. Alberto Castro Maestre
 */
package Dao;

import Interface.ITMembresia;
import Pojo.GimMembresia;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Alberto Castro
 */
public class DaoMembresia implements ITMembresia{

    @Override
    public int insert(Session session, GimMembresia membresia) throws Exception {
        return Integer.parseInt(session.save(membresia).toString());
    }

    @Override
    public boolean update(Session session, GimMembresia membresia) throws Exception {
        session.update(membresia);
        return true;
    }

    @Override
    public List<GimMembresia> getAll(Session session) throws Exception {
        return session.createCriteria(GimMembresia.class).list();
    }

    @Override
    public GimMembresia getMembresiaByID(Session session, BigDecimal id) throws Exception {
        return (GimMembresia) session.createCriteria(GimMembresia.class)
                .add(Restrictions.eq("memid", id))
                .uniqueResult();
    }
    
}
