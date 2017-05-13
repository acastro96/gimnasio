/*
 * Proyecto Gimnasio Virtual. 
 * Universidad Simón Bolívar - Barranquilla / Colombia.
 * Desarrollado por Ing. Alberto Castro Maestre
 */
package Dao;

import Interface.ITRutObj;
import Pojo.GimRutObj;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Alberto Castro
 */
public class DaoRutObj implements ITRutObj{

    @Override
    public int insert(Session session, GimRutObj planRut) throws Exception {
        return Integer.parseInt(session.save(planRut).toString());
    }

    @Override
    public boolean update(Session session, GimRutObj planRut) throws Exception {
        session.update(planRut);
        return true;
    }

    @Override
    public List<GimRutObj> getAll(Session session) throws Exception {
        return session.createCriteria(GimRutObj.class).list();
    }

    @Override
    public List<GimRutObj> getPlanRutObjIdRutina(Session session, BigDecimal idRutina) throws Exception {
        return  session.createCriteria(GimRutObj.class)
                .add(Restrictions.eq("id.rutId", idRutina))
                .list();
    }
    
}
