/*
 * Proyecto Gimnasio Virtual. 
 * Universidad Simón Bolívar - Barranquilla / Colombia.
 * Desarrollado por Ing. Alberto Castro Maestre
 */
package Dao;

import Interface.ITValoracion_Detalle;
import Pojo.GimValoracionDetalle;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Alberto Castro
 */
public class DaoValoracion_Detalle implements ITValoracion_Detalle{

    @Override
    public int insert(Session session, GimValoracionDetalle valoracionDetalle) throws Exception {
        return Integer.parseInt(session.save(valoracionDetalle).toString());
    }

    @Override
    public boolean update(Session session, GimValoracionDetalle valoracionDetalle) throws Exception {
        session.update(valoracionDetalle);
        return true;
    }

    @Override
    public List<GimValoracionDetalle> getAll(Session session) throws Exception {
        return session.createCriteria(GimValoracionDetalle.class).list();
    }

    @Override
    public GimValoracionDetalle getValoracion_DetalleById(Session session, BigDecimal id) throws Exception {
        return (GimValoracionDetalle) session.createCriteria(GimValoracionDetalle.class)
                .add(Restrictions.eq("detvalId", id))
                .uniqueResult();
    }

    @Override
    public List<GimValoracionDetalle> getValoracion_DetalleByIdMaestro(Session session, BigDecimal idMaestro) throws Exception {
        //No se tiene claro porque no maneja la foranea con un objeto, se tiene que revisar la relación en el SQLDeveloper
        return  session.createCriteria(GimValoracionDetalle.class)
                .add(Restrictions.eq("valId", idMaestro))
                .list();
    }
    
}
