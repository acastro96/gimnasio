/*
 * Proyecto Gimnasio Virtual. 
 * Universidad Simón Bolívar - Barranquilla / Colombia.
 * Desarrollado por Ing. Alberto Castro Maestre
 */
package Dao;

import Interface.ITAuditoria;
import Pojo.GimAuditoria;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Alberto Castro
 */
public class DaoAuditoria implements ITAuditoria{

    @Override
    public int insert(Session session, GimAuditoria auditoria) throws Exception {
        return Integer.parseInt(session.save(auditoria).toString());
    }

    @Override
    public boolean update(Session session, GimAuditoria auditoria) throws Exception {
        session.update(auditoria);
        return true;
    }

    @Override
    public List<GimAuditoria> getAll(Session session) throws Exception {
        return session.createCriteria(GimAuditoria.class).list();
    }
    
}
