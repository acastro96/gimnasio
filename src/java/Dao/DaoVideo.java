/*
 * Proyecto Gimnasio Virtual. 
 * Universidad Simón Bolívar - Barranquilla / Colombia.
 * Desarrollado por Ing. Alberto Castro Maestre
 */
package Dao;

import Interface.ITVideo;
import Pojo.GimVideo;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Alberto Castro
 */
public class DaoVideo implements ITVideo{

    @Override
    public int insert(Session session, GimVideo video) throws Exception {
        return Integer.parseInt(session.save(video).toString());
    }

    @Override
    public boolean update(Session session, GimVideo video) throws Exception {
        session.update(video);
        return true;
    }

    @Override
    public List<GimVideo> getAll(Session session) throws Exception {
        return session.createCriteria(GimVideo.class).list();
    }

    @Override
    public GimVideo getVideoByID(Session session, BigDecimal id) throws Exception {
        return (GimVideo) session.createCriteria(GimVideo.class)
                .add(Restrictions.eq("vidid", id))
                .uniqueResult();
    }

}
