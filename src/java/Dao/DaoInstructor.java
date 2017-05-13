/*
 * Proyecto Gimnasio Virtual. 
 * Universidad Simón Bolívar - Barranquilla / Colombia.
 * Desarrollado por Ing. Alberto Castro Maestre
 */
package Dao;

import Interface.ITInstructor;
import Pojo.GimInstructor;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Alberto Castro
 */
public class DaoInstructor implements ITInstructor{

    @Override
    public int insert(Session session, GimInstructor instructor) throws Exception {
        return Integer.parseInt(session.save(instructor).toString());
    }

    @Override
    public boolean update(Session session, GimInstructor instructor) throws Exception {
        session.update(instructor);
        return true;
    }

    @Override
    public List<GimInstructor> getAll(Session session) throws Exception {
        return session.createCriteria(GimInstructor.class).list();
    }

    @Override
    public GimInstructor getInstructorById(Session session, BigDecimal id) throws Exception {
        return (GimInstructor) session.createCriteria(GimInstructor.class)
                .add(Restrictions.eq("instId", id))
                .uniqueResult();
    }
    
    @Override
    public GimInstructor getInstructorByIdPersona(Session session, BigDecimal idPersona) throws Exception {
        return (GimInstructor) session.createCriteria(GimInstructor.class)
                .add(Restrictions.eq("gimPersona.persId", idPersona))
                .uniqueResult();
    }
    
    @Override
    public GimInstructor getInstructorByCod(Session session, String cod) throws Exception {
        return (GimInstructor) session.createCriteria(GimInstructor.class)
                .add(Restrictions.eq("instCodigo", cod))
                .uniqueResult();
    }

    @Override
    public List<GimInstructor> getlistByNombre(Session session, String nom) throws Exception {
        String hql = "FROM GimInstructor as instructor where instructor.gimPersona.persNombre1 = :nom ";
        Query query = session.createQuery(hql);
        query.setParameter("nom", nom);
        return query.list();
    }

    
    
}
