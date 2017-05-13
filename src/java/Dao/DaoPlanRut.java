/*
 * Proyecto Gimnasio Virtual. 
 * Universidad Simón Bolívar - Barranquilla / Colombia.
 * Desarrollado por Ing. Alberto Castro Maestre
 */
package Dao;

import Interface.ITPlanRut;
import Pojo.GimPlanRut;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Alberto Castro
 */
public class DaoPlanRut implements ITPlanRut{

    @Override
    public int insert(Session session, GimPlanRut planRut) throws Exception {
        return Integer.parseInt(session.save(planRut).toString());
    }

    @Override
    public boolean update(Session session, GimPlanRut planRut) throws Exception {
        session.update(planRut);
        return true;
    }

    @Override
    public List<GimPlanRut> getAll(Session session) throws Exception {
        return session.createCriteria(GimPlanRut.class).list();
    }

    @Override
    public List<GimPlanRut> getPlanRutByIdPlan(Session session, BigDecimal idPlan) throws Exception {
        return  session.createCriteria(GimPlanRut.class)
                .add(Restrictions.eq("id.planId", idPlan))
                .list();
    }
    
}
