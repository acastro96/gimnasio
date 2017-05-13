/*
 * Proyecto Gimnasio Virtual. 
 * Universidad Simón Bolívar - Barranquilla / Colombia.
 * Desarrollado por Ing. Alberto Castro Maestre
 */
package Dao;

import Interface.ITPlanTrabajo;
import Pojo.GimPlanTrabajo;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Alberto Castro
 */
public class DaoPlanTrabajo implements ITPlanTrabajo{

    @Override
    public int insert(Session session, GimPlanTrabajo planTrabajo) throws Exception {
        return Integer.parseInt(session.save(planTrabajo).toString());
    }

    @Override
    public boolean update(Session session, GimPlanTrabajo planTrabajo) throws Exception {
        session.update(planTrabajo);
        return true;
    }

    @Override
    public List<GimPlanTrabajo> getAll(Session session) throws Exception {
        return session.createCriteria(GimPlanTrabajo.class).list();
    }

    @Override
    public GimPlanTrabajo getPlanTrabajoByID(Session session, BigDecimal id) throws Exception {
        return (GimPlanTrabajo) session.createCriteria(GimPlanTrabajo.class)
                .add(Restrictions.eq("planid", id))
                .uniqueResult();
    }

    @Override
    public GimPlanTrabajo getPlanTrabajoByNom(Session session, String nom) throws Exception {
        return (GimPlanTrabajo) session.createCriteria(GimPlanTrabajo.class)
                .add(Restrictions.eq("planNombre", nom))
                .uniqueResult();
    }

    @Override
    public GimPlanTrabajo getPlanTrabajoByCod(Session session, String cod) throws Exception {
        return (GimPlanTrabajo) session.createCriteria(GimPlanTrabajo.class)
                .add(Restrictions.eq("planCodigo", cod))
                .uniqueResult();
    }
    
    @Override
    public List<GimPlanTrabajo> getlistPlanTrabajoByNom(Session session, String nom) throws Exception {
        String hql = "from GimPlanTrabajo where planNombre = %:name%";
        Query query = session.createQuery(hql);
        query.setParameter("name", nom);
        return query.list();
    }
    
}
