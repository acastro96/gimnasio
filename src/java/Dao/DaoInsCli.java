/*
 * Proyecto Gimnasio Virtual. 
 * Universidad Simón Bolívar - Barranquilla / Colombia.
 * Desarrollado por Ing. Alberto Castro Maestre
 */
package Dao;

import Interface.ITInsCli;
import Pojo.GimInsCli;
import Pojo.GimInsCliId;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Alberto Castro
 */
public class DaoInsCli implements ITInsCli{

    @Override
    public void insert(Session session, GimInsCli insCli) throws Exception {
        session.save(insCli);
    }

    @Override
    public boolean update(Session session, GimInsCli insCli) throws Exception {
        session.update(insCli);
        return true;
    }

    @Override
    public List<GimInsCli> getAll(Session session) throws Exception {
        return session.createCriteria(GimInsCli.class).list();
    }

    @Override
    public List<GimInsCli> getInsCliByIDInstructor(Session session, BigDecimal idInstructor) throws Exception {
        return  session.createCriteria(GimInsCli.class)
                .add(Restrictions.eq("id.instId", idInstructor))
                .list();
    }

    @Override
    public List<GimInsCli> getInsCliByIDCliente(Session session, BigDecimal idCliente) throws Exception {
        return  session.createCriteria(GimInsCli.class)
                .add(Restrictions.eq("id.cliId", idCliente))
                .list();
    }

    @Override
    public GimInsCli getInsCliByIDInstructorIDCli(Session session, BigDecimal idInstructor, BigDecimal idCliente) throws Exception {
        return (GimInsCli) session.createCriteria(GimInsCli.class)
                .add(Restrictions.eq("id.instId", idInstructor))
                .add(Restrictions.eq("id.cliId", idCliente))
                .uniqueResult();
    }

    @Override
    public GimInsCli getInsCliByID(Session session, GimInsCliId id) throws Exception {
        return (GimInsCli)  session.createCriteria(GimInsCli.class)
                .add(Restrictions.eq("id", id)).uniqueResult();
    }
    
}
