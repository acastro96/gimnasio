/*
 * Proyecto Gimnasio Virtual. 
 * Universidad Simón Bolívar - Barranquilla / Colombia.
 * Desarrollado por Ing. Alberto Castro Maestre
 */
package Dao;

import Interface.ITValoracion_Maestro;
import Pojo.GimValoracionMaestro;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Alberto Castro
 */
public class DaoValoracion_Maestro implements ITValoracion_Maestro{

    @Override
    public int insert(Session session, GimValoracionMaestro valoracionMaestro) throws Exception {
        return Integer.parseInt(session.save(valoracionMaestro).toString());
    }

    @Override
    public boolean update(Session session, GimValoracionMaestro valoracionMaestro) throws Exception {
        session.update(valoracionMaestro);
        return true;
    }

    @Override
    public List<GimValoracionMaestro> getAll(Session session) throws Exception {
        return session.createCriteria(GimValoracionMaestro.class).list();
    }

    @Override
    public GimValoracionMaestro getValoracion_MaestroById(Session session, BigDecimal id) throws Exception {
        return (GimValoracionMaestro) session.createCriteria(GimValoracionMaestro.class)
                .add(Restrictions.eq("valId", id))
                .uniqueResult();
    }

    @Override
    public List<GimValoracionMaestro> getValoracionesByInstructor(Session session, BigDecimal instId) throws Exception {
        return  session.createCriteria(GimValoracionMaestro.class)
                .add(Restrictions.eq("instId", instId))
                .list();
    }

    @Override
    public List<GimValoracionMaestro> getValoracionesByCliente(Session session, BigDecimal cliId) throws Exception {
        return  session.createCriteria(GimValoracionMaestro.class)
                .add(Restrictions.eq("cliId", cliId))
                .list();
    }
    
}
