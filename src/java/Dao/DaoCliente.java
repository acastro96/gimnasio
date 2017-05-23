/*
 * Proyecto Gimnasio Virtual. 
 * Universidad Simón Bolívar - Barranquilla / Colombia.
 * Desarrollado por Ing. Alberto Castro Maestre
 */
package Dao;

import Interface.ITCliente;
import Pojo.GimCliente;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Alberto Castro
 */
public class DaoCliente implements ITCliente{

    @Override
    public int insert(Session session, GimCliente cliente) throws Exception {
        return Integer.parseInt(session.save(cliente).toString());
    }

    @Override
    public boolean update(Session session, GimCliente cliente) throws Exception {
        session.update(cliente);
        return true;
    }

    @Override
    public List<GimCliente> getAll(Session session) throws Exception {
        return session.createCriteria(GimCliente.class).list();
    }

    @Override
    public GimCliente getClienteByID(Session session, BigDecimal id) throws Exception {
        // Prueba para saber si este metodo funciona para traer un registro por id
        return (GimCliente) session.get(GimCliente.class, id);
        
        /*return (GimCliente) session.createCriteria(GimCliente.class)
                .add(Restrictions.eq("cliid", id))
                .uniqueResult();*/
    }

    @Override
    public GimCliente getClienteByIdPersona(Session session, BigDecimal idPersona) throws Exception {
        return (GimCliente) session.createCriteria(GimCliente.class)
                .add(Restrictions.eq("gimPersona.persId", idPersona))
                .uniqueResult();
    }

    @Override
    public GimCliente getClienteByCod(Session session, String cod) throws Exception {
        return (GimCliente) session.createCriteria(GimCliente.class)
                .add(Restrictions.eq("cliCodigo", cod))
                .uniqueResult();
    }


    
}
