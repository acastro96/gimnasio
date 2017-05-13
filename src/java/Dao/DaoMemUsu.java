/*
 * Proyecto Gimnasio Virtual. 
 * Universidad Simón Bolívar - Barranquilla / Colombia.
 * Desarrollado por Ing. Alberto Castro Maestre
 */
package Dao;

import Interface.ITMemUsu;
import Pojo.GimMemUsu;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Alberto Castro
 */
public class DaoMemUsu implements ITMemUsu{

    @Override
    public int insert(Session session, GimMemUsu memUsu) throws Exception {
        return Integer.parseInt(session.save(memUsu).toString());
    }

    @Override
    public boolean update(Session session, GimMemUsu memUsu) throws Exception {
        session.update(memUsu);
        return true;
    }

    @Override
    public List<GimMemUsu> getAll(Session session) throws Exception {
        return session.createCriteria(GimMemUsu.class).list();
    }

    @Override
    public List<GimMemUsu> getMemUsuByIdUsuario(Session session, BigDecimal idUsuario) throws Exception {
//        String hql = "FROM Videos WHERE id.usuId =:idUsuario ";
//        Query query = session.createQuery(hql);
//        query.setParameter("idUsuario", idUsuario);
//        return query.list();
        return  session.createCriteria(GimMemUsu.class)
                .add(Restrictions.eq("id.usuId", idUsuario))
                .list();
    }

    @Override
    public List<GimMemUsu> getMemUsuByIdMembresia(Session session, BigDecimal idMembresia) throws Exception {
        String hql = "FROM Videos WHERE id.memId =:idMembresia ";
        Query query = session.createQuery(hql);
        query.setParameter("idMembresia", idMembresia);
        return query.list();
    }
    
}
