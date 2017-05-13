/*
 * Proyecto Gimnasio Virtual. 
 * Universidad Simón Bolívar - Barranquilla / Colombia.
 * Desarrollado por Ing. Alberto Castro Maestre
 */
package Dao;

import Interface.ITDireccion;
import Pojo.GimDireccion;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Alberto Castro
 */
public class DaoDireccion implements ITDireccion{

    @Override
    public int insert(Session session, GimDireccion direccion) throws Exception {
        return Integer.parseInt(session.save(direccion).toString());
    }

    @Override
    public boolean update(Session session, GimDireccion direccion) throws Exception {
        session.update(direccion);
        return true;
    }

    @Override
    public List<GimDireccion> getAll(Session session) throws Exception {
        return session.createCriteria(GimDireccion.class).list();
    }

    @Override
    public List<GimDireccion> getAllByPersonaByID(Session session, BigDecimal idPersona) throws Exception {
        String hql = "FROM Gim_Direccion where pers_id =:idPersona";
        Query query = session.createQuery(hql);
        query.setParameter("idPersona", idPersona);
        return query.list();
    }

    @Override
    public GimDireccion getByIdPersona(Session session, BigDecimal idPersona) throws Exception {
        return (GimDireccion) session.createCriteria(GimDireccion.class)
                .add(Restrictions.eq("gimPersona.persId", idPersona))
                .add(Restrictions.eq("dirEstado", BigDecimal.ONE))
                .uniqueResult();
    }
    
}
