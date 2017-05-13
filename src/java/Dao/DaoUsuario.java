/*
 * Proyecto Gimnasio Virtual. 
 * Universidad Simón Bolívar - Barranquilla / Colombia.
 * Desarrollado por Ing. Alberto Castro Maestre
 */
package Dao;

import Interface.ITUsuario;
import Pojo.GimRecurso;
import Pojo.GimUsuario;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Alberto Castro
 */
public class DaoUsuario implements ITUsuario{

    @Override
    public int insert(Session session, GimUsuario usuario) throws Exception {
        return Integer.parseInt(session.save(usuario).toString());
    }

    @Override
    public boolean update(Session session, GimUsuario usuario) throws Exception {
        session.update(usuario);
        return true;
    }

    @Override
    public List<GimUsuario> getAll(Session session) throws Exception {
        return session.createCriteria(GimUsuario.class).list();
    }

    @Override
    public GimUsuario getUsuarioByID(Session session, BigDecimal id) throws Exception {
        return (GimUsuario) session.createCriteria(GimUsuario.class)
                .add(Restrictions.eq("usuId", id))
                .uniqueResult();
    }

    @Override
    public GimUsuario getUsuarioByNom(Session session, String nom) throws Exception {
        return (GimUsuario) session.createCriteria(GimUsuario.class)
                .add(Restrictions.eq("usuNombre", nom))
                .uniqueResult();
    }

    @Override
    public GimUsuario getUsuarioByNamePass(Session session, String name, String pass) throws Exception {
        String hql = "from GimUsuario where usuNombre=:name and usuPassword=:pass";
        Query query = session.createQuery(hql);
        query.setParameter("name", name);
        query.setParameter("pass", pass);
        return (GimUsuario)query.uniqueResult();
    }

    @Override
    public GimUsuario getUsuarioByIdPesona(Session session, BigDecimal idPersona) throws Exception {
        return (GimUsuario) session.createCriteria(GimUsuario.class)
                .add(Restrictions.eq("gimPersona.persId", idPersona))
                .uniqueResult();
    }

    @Override
    public List<GimRecurso> listarRecursos(Session session, BigDecimal usu_id) throws Exception {
        String hql = "Select distinct r.* from GIM_RECURSO r\n"
                + "inner join GIM_PERFILRECURSO pr\n"
                + "on pr.rec_id = r.rec_id\n"
                + "inner join GIM_USUARIO u\n"
                + "on u.PERF_ID = pr.PERF_ID and u.USU_ID =:usu_id";
        SQLQuery query = session.createSQLQuery(hql);
        query.addEntity(GimRecurso.class);
        query.setInteger("usu_id", usu_id.intValue());
        if (query.list().size() > 0) {
            return query.list();
        }
        return null;
    }
    
}
