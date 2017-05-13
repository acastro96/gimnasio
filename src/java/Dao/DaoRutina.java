/*
 * Proyecto Gimnasio Virtual. 
 * Universidad Simón Bolívar - Barranquilla / Colombia.
 * Desarrollado por Ing. Alberto Castro Maestre
 */
package Dao;

import Interface.ITRutina;
import Pojo.GimRutina;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Alberto Castro
 */
public class DaoRutina implements ITRutina {

    @Override
    public int insert(Session session, GimRutina rutina) throws Exception {
        return Integer.parseInt(session.save(rutina).toString());
    }

    @Override
    public boolean update(Session session, GimRutina rutina) throws Exception {
        session.update(rutina);
        return true;
    }

    @Override
    public List<GimRutina> getAll(Session session) throws Exception {
        return session.createCriteria(GimRutina.class).list();
    }

    @Override
    public GimRutina getRutinaByID(Session session, BigDecimal id) throws Exception {
        return (GimRutina) session.createCriteria(GimRutina.class)
                .add(Restrictions.eq("rutId", id))
                .uniqueResult();
    }

    @Override
    public GimRutina getRutinaByNom(Session session, String nom) throws Exception {
        return (GimRutina) session.createCriteria(GimRutina.class)
                .add(Restrictions.eq("rutNombre", nom))
                .uniqueResult();
    }   

    @Override
    public GimRutina getRutinaByCod(Session session, String cod) throws Exception {
        return (GimRutina) session.createCriteria(GimRutina.class)
                .add(Restrictions.eq("rutCodigo", cod))
                .uniqueResult();
    }
    
}
