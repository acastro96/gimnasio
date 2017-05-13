/*
 * Proyecto Gimnasio Virtual. 
 * Universidad Simón Bolívar - Barranquilla / Colombia.
 * Desarrollado por Ing. Alberto Castro Maestre
 */
package Dao;

import Interface.ITInstructor2;
import Pojo.GimInstructor;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Alberto Castro
 */
public class DaoInstructor2 implements ITInstructor2{

    @Override
    public int insert(Session session, GimInstructor instructor) throws Exception {
        Query query = session.createSQLQuery("CALL GIM_INS_INSTRUCTOR(:ID,:FECHAINGRESO,:FECHADESVINCULACION,:ESPECIALIDAD,:NIVELESTUDIO,\n" +
"    :FECHAPROCESO,:ESTADO,:ID_PERSONA,:NIVELNUTRICION,:CODIGO)")
                .setParameter("ID", instructor.getInstId())
                .setParameter("FECHAINGRESO", instructor.getInstFechaingreso())
                .setParameter("FECHADESVINCULACION", null)
                .setParameter("ESPECIALIDAD", instructor.getInstEspecialidad())
                .setParameter("NIVELESTUDIO", instructor.getInstNivelestudio())
                .setParameter("FECHAPROCESO", null)
                .setParameter("ESTADO", instructor.getInstEstado())
                .setParameter("ID_PERSONA", instructor.getGimPersona().getPersId())
                .setParameter("NIVELNUTRICION", instructor.getInstNivelnutricion())
                .setParameter("CODIGO", instructor.getInstCodigo());
        
        int id = query.executeUpdate();
        session.close();
        return id;
    }

    @Override
    public boolean update(Session session, GimInstructor instructor) throws Exception {
        Query query = session.createSQLQuery("CALL GIM_UPD_INSTRUCTOR(:ID, :ESPECIALIDAD, :ESTADO)")
                .setParameter("ID", instructor.getInstId())
                .setParameter("ESPECIALIDAD", instructor.getInstEspecialidad())
                .setParameter("ESTADO", instructor.getInstEstado());
        
        query.executeUpdate();
        session.close();
        return true;
    }
    
}
