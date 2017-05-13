/*
 * Proyecto Gimnasio Virtual. 
 * Universidad Simón Bolívar - Barranquilla / Colombia.
 * Desarrollado por Ing. Alberto Castro Maestre
 */
package Dao;

import Interface.ITPerfilRecurso;
import Pojo.GimPerfilrecurso;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Alberto Castro
 */
public class DaoPerfilRecurso implements ITPerfilRecurso{

    @Override
    public int insert(Session session, GimPerfilrecurso perfilrecurso) throws Exception {
        return Integer.parseInt(session.save(perfilrecurso).toString());
    }

    @Override
    public boolean update(Session session, GimPerfilrecurso perfilrecurso) throws Exception {
        session.update(perfilrecurso);
        return true; 
   }

    @Override
    public List<GimPerfilrecurso> getAll(Session session) throws Exception {
        return session.createCriteria(GimPerfilrecurso.class).list();
    }

    @Override
    public List<GimPerfilrecurso> getPerfilRecursoByIdPerfil(Session session, BigDecimal idPerfil) throws Exception {
        return  session.createCriteria(GimPerfilrecurso.class)
                .add(Restrictions.eq("id.perfId", idPerfil))
                .list();
    }
    
}
