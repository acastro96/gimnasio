/*
 * Proyecto Gimnasio Virtual. 
 * Universidad Simón Bolívar - Barranquilla / Colombia.
 * Desarrollado por Ing. Alberto Castro Maestre
 */
package Dao;

import Interface.ITModulo;
import Pojo.GimModulo;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Alberto Castro
 */
public class DaoModulo implements ITModulo{

    @Override
    public int insert(Session session, GimModulo modulo) throws Exception {
        return Integer.parseInt(session.save(modulo).toString());
    }

    @Override
    public boolean update(Session session, GimModulo modulo) throws Exception {
        session.update(modulo);
        return true;
    }

    @Override
    public List<GimModulo> getAll(Session session) throws Exception {
        return session.createCriteria(GimModulo.class).list();
    }

    @Override
    public GimModulo getModuloById(Session session, BigDecimal id) throws Exception {
        return (GimModulo) session.createCriteria(GimModulo.class)
                .add(Restrictions.eq("modId", id))
                .uniqueResult();
    }
    
}
