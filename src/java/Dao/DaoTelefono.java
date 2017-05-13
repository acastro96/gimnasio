/*
 * Proyecto Gimnasio Virtual. 
 * Universidad Simón Bolívar - Barranquilla / Colombia.
 * Desarrollado por Ing. Alberto Castro Maestre
 */
package Dao;

import Interface.ITTelefono;
import Pojo.GimTelefono;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Alberto Castro
 */
public class DaoTelefono implements ITTelefono{

    @Override
    public int insert(Session session, GimTelefono telefono) throws Exception {
        return Integer.parseInt(session.save(telefono).toString());
    }

    @Override
    public boolean update(Session session, GimTelefono telefono) throws Exception {
        session.update(telefono);
        return true;
    }

    @Override
    public List<GimTelefono> getAll(Session session) throws Exception {
        return session.createCriteria(GimTelefono.class).list();
    }

    @Override
    public List<GimTelefono> getAllByPersonaByID(Session session, BigDecimal idPersona) throws Exception {
        String hql = "FROM Gim_Telefono where pers_id =:idPersona";
        Query query = session.createQuery(hql);
        query.setParameter("idPersona", idPersona);
        return query.list();
    }

    @Override
    public GimTelefono getByIdPersona(Session session, BigDecimal idPersona) throws Exception {
         return (GimTelefono) session.createCriteria(GimTelefono.class)
                .add(Restrictions.eq("gimPersona.persId", idPersona))
                .add(Restrictions.eq("telEstado", BigDecimal.ONE))
                .uniqueResult();
    }
    
}
