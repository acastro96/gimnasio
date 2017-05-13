/*
 * Proyecto Gimnasio Virtual. 
 * Universidad Simón Bolívar - Barranquilla / Colombia.
 * Desarrollado por Ing. Alberto Castro Maestre
 */
package Dao;

import Interface.ITPersona2;
import Pojo.GimPersona;
import java.util.Date;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Alberto Castro
 */
public class DaoPersona2 implements ITPersona2{

    @Override
    public void insert(Session session, GimPersona persona) throws Exception {
        Query query = session.createSQLQuery("CALL GIM_INS_PERSONA(:ID,:NOMBRE1,:NOMBRE2,:APELLIDO1,:APELLIDO2," +
    ":TIPODOCUMENTO,:FECHANACIMIENTO,:LUGARNACIMIENTO,:GRUPOSANGUINEO,:RH ,:SEXO,:FECHAFINAL ," +
    ":FECHAINICIAL ,:ESTADO ,:NUMERODOCUMENTO ,:EMAIL )")
                .setParameter("ID", persona.getPersId())
                .setParameter("NOMBRE1", persona.getPersNombre1())
                .setParameter("NOMBRE2", persona.getPersNombre2())
                .setParameter("APELLIDO1", persona.getPersApellido1())
                .setParameter("APELLIDO2", persona.getPersApellido2())
                .setParameter("TIPODOCUMENTO", persona.getPersTipodocumento())
                .setParameter("FECHANACIMIENTO", new Date())
                .setParameter("LUGARNACIMIENTO", persona.getPersLugarnacimiento())
                .setParameter("GRUPOSANGUINEO", persona.getPersGruposanguineo())
                .setParameter("RH", persona.getPersRh())
                .setParameter("SEXO", persona.getPersSexo())
                .setParameter("FECHAFINAL", new Date())
                .setParameter("FECHAINICIAL", new Date())
                .setParameter("ESTADO", persona.getPersEstado())
                .setParameter("NUMERODOCUMENTO", persona.getPersNumerodocumento())
                .setParameter("EMAIL", persona.getPersEmail());
        
        query.executeUpdate();
    }

    @Override
    public boolean update(Session session, GimPersona persona) throws Exception {
        Query query = session.createSQLQuery("CALL GIM_UPD_PERSONA(:ID, :NOM1, :NOM2, :EMAIL, :ESTADO)")
                .setParameter("ID", persona.getPersId().intValue())
                .setParameter("NOM1", persona.getPersNombre1())
                .setParameter("NOM2", persona.getPersNombre2())
                .setParameter("EMAIL", persona.getPersEmail())
                .setParameter("ESTADO", persona.getPersEstado());
        
        query.executeUpdate();
        return true;
    }
    
}
