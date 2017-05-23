/*
 * Proyecto Gimnasio Virtual. 
 * Universidad Simón Bolívar - Barranquilla / Colombia.
 * Desarrollado por Ing. Alberto Castro Maestre
 */
package Dao;

import Interface.ITPlanCliente;
import Pojo.GimPlanCliente;
import Pojo.GimPlanClienteId;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Alberto Castro
 */
public class DaoPlanCliente implements ITPlanCliente{

    @Override
    public void insert(Session session, GimPlanCliente planCliente) throws Exception {
        session.save(planCliente);
    }

    @Override
    public boolean update(Session session, GimPlanCliente planCliente) throws Exception {
        session.update(planCliente);
        return true;
    }

    @Override
    public List<GimPlanCliente> getAll(Session session) throws Exception {
        return session.createCriteria(GimPlanCliente.class).list();
    }

    @Override
    public List<GimPlanCliente> getPlanCliByIDCliente(Session session, BigDecimal idCliente) throws Exception {
        return  session.createCriteria(GimPlanCliente.class)
                .add(Restrictions.eq("id.cliId", idCliente))
                .list();
    }

    @Override
    public List<GimPlanCliente> getPlanCliByIDPlan(Session session, BigDecimal idPlan) throws Exception {
        return  session.createCriteria(GimPlanCliente.class)
                .add(Restrictions.eq("id.planId", idPlan))
                .list();
    }

    @Override
    public GimPlanCliente getPlanCliByIDPlanIDCli(Session session, BigDecimal idPlan, BigDecimal idCliente) throws Exception {
        return (GimPlanCliente) session.createCriteria(GimPlanCliente.class)
                .add(Restrictions.eq("id.planId", idPlan))
                .add(Restrictions.eq("id.cliId", idCliente))
                .uniqueResult();
    }

    @Override
    public GimPlanCliente getPlanClienteByID(Session session, GimPlanClienteId id) throws Exception {
        return (GimPlanCliente)  session.createCriteria(GimPlanCliente.class)
                .add(Restrictions.eq("id", id)).uniqueResult();
    }
    
    @Override
    public GimPlanCliente getPlanCliByIDClienteActivo(Session session, BigDecimal idCliente) throws Exception {
        return  (GimPlanCliente) session.createCriteria(GimPlanCliente.class)
                .add(Restrictions.eq("id.cliId", idCliente))
                .add(Restrictions.eq("plclEstado", BigDecimal.valueOf(1)))
                .uniqueResult();
    }
    
}
