/*
 * Proyecto Gimnasio Virtual. 
 * Universidad Simón Bolívar - Barranquilla / Colombia.
 * Desarrollado por Ing. Alberto Castro Maestre
 */
package Dao;

import Interface.ITObjetos;
import Pojo.GimObjetos;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Alberto Castro
 */
public class DaoObjetos implements ITObjetos{

    @Override
    public int insert(Session session, GimObjetos objetos) throws Exception {
        return Integer.parseInt(session.save(objetos).toString());
    }

    @Override
    public boolean update(Session session, GimObjetos objetos) throws Exception {
        session.update(objetos);
        return true;
    }

    @Override
    public List<GimObjetos> getAll(Session session) throws Exception {
        return session.createCriteria(GimObjetos.class).list();
    }

    @Override
    public GimObjetos getObjetoById(Session session, BigDecimal id) throws Exception {
        return (GimObjetos) session.createCriteria(GimObjetos.class)
                .add(Restrictions.eq("objid", id))
                .uniqueResult();
    }

    @Override
    public GimObjetos getObjetoByCod(Session session, String cod) throws Exception {
        return (GimObjetos) session.createCriteria(GimObjetos.class)
                .add(Restrictions.eq("objcod", cod))
                .uniqueResult();
    }

    @Override
    public GimObjetos getObjetoByNom(Session session, String nom) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
