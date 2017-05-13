/*
 * Proyecto Gimnasio Virtual. 
 * Universidad Simón Bolívar - Barranquilla / Colombia.
 * Desarrollado por Ing. Alberto Castro Maestre
 */
package Bo;

import Bean.PersonasBean;
import Dao.DaoPersona;
import Dao.DaoPersona2;
import Pojo.GimPersona;
import java.math.BigDecimal;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Alberto Castro
 */
public class PersonasImplBO implements PersonasBO{
    
    DaoPersona2 daoPersona2;
    DaoPersona daoPersona;
    
    public PersonasImplBO (){
        daoPersona2 = new DaoPersona2();
        daoPersona = new DaoPersona();
    }

    @Override
    public void consultarPersona(PersonasBean personasBean) throws Exception {
        Session session = HibernateUtil.HibernateUtil.getSessionFactory().openSession();
        
        GimPersona gimPersona;
        
        try {
            
            if(personasBean.getNumeroDocumento().equalsIgnoreCase("")){
                personasBean.setMensaje("El campo numero de documento no puede ser vacio");
                personasBean.setCodeMensaje(2);
                return;
            }
            
            gimPersona = daoPersona.getPersonaByDoc(session, personasBean.getNumeroDocumento());
            
            if(gimPersona == null)
            {
                personasBean.setMensaje("La persona no se encuentra registrada");
                personasBean.setCodeMensaje(3);
                return;
            }
            
            personasBean.setNombre1(gimPersona.getPersNombre1());
            if (gimPersona.getPersNombre2() != null) {
                personasBean.setNombre2(gimPersona.getPersNombre2());
            }
            personasBean.setApellido1(gimPersona.getPersApellido1());
            personasBean.setApellido2(gimPersona.getPersApellido2());
            personasBean.setFechaNacimiento(gimPersona.getPersFechanacimiento());
            personasBean.setLugarNacimiento(gimPersona.getPersLugarnacimiento().intValue());
            personasBean.setGrupoSanguineo(gimPersona.getPersGruposanguineo().intValue());
            personasBean.setRh(gimPersona.getPersRh());
            personasBean.setSexo(gimPersona.getPersSexo().intValue());
            
        } catch (Exception e) {
        }finally {

            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void agregarPersona(PersonasBean personasBean) throws Exception {
        
        Session session = HibernateUtil.HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        
        GimPersona gimPersona;
        
        try {
            
            //Se hacen las validaciones para la base de datos
            if (personasBean.getNumeroDocumento().equalsIgnoreCase("")) {
                personasBean.setCodeMensaje(2);
                personasBean.setMensaje("El campo Número de Documento no puede ser vacío");
                return;
            }
            if (personasBean.getNombre1().equalsIgnoreCase("")) {
                personasBean.setCodeMensaje(2);
                personasBean.setMensaje("El campo de Primer Nombre no puede ser vacío");
                return;
            }
            if (personasBean.getApellido1().equalsIgnoreCase("")) {
                personasBean.setCodeMensaje(2);
                personasBean.setMensaje("El campo de Primer Apellido no puede ser vacío");
                return;
            }
            if (personasBean.getApellido2().equalsIgnoreCase("")) {
                personasBean.setCodeMensaje(2);
                personasBean.setMensaje("El campo de Segundo Apellido no puede ser vacío");
                return;
            }
//            if (personasBean.getFechaNacimiento() == null) {
//                personasBean.setCodeMensaje(2);
//                personasBean.setMensaje("La Fecha de Nacimiento es inválida");
//                return;
//            }
            if (personasBean.getEmail().equalsIgnoreCase("")) {
                personasBean.setCodeMensaje(2);
                personasBean.setMensaje("Debe ingresar un Email");
                return;
            }
            
            gimPersona = daoPersona.getPersonaByDoc(session, personasBean.getNumeroDocumento());
            
            if(gimPersona!=null)
            {
                personasBean.setCodeMensaje(3);
                personasBean.setMensaje("La persona ya se encuentra registrada");
                return;
            }
            gimPersona = new GimPersona(BigDecimal.valueOf(2), personasBean.getNombre1(), personasBean.getNombre2(), personasBean.getApellido1(), personasBean.getApellido2(), BigDecimal.valueOf(personasBean.getTipoDocumento()), null, BigDecimal.valueOf(personasBean.getLugarNacimiento()), BigDecimal.valueOf(personasBean.getGrupoSanguineo()), personasBean.getRh(), BigDecimal.valueOf(personasBean.getSexo()), null, null, BigDecimal.ONE, personasBean.getNumeroDocumento(), personasBean.getEmail());
            daoPersona2.insert(session, gimPersona);
            
            personasBean.setCodeMensaje(1);
            personasBean.setMensaje("La persona se inserto correctamente");
            
            transaction.commit();
            
        }  catch (Exception e) {

            if (transaction != null) {
                transaction.rollback();
            }
        } finally {

            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void actualizarPersona(PersonasBean personasBean) throws Exception {
        Session session = HibernateUtil.HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        
        GimPersona gimPersona;
        
        try {
            
            //Se hacen las validaciones para la base de datos
            if (personasBean.getNumeroDocumento().equalsIgnoreCase("")) {
                personasBean.setCodeMensaje(2);
                personasBean.setMensaje("El campo Número de Documento no puede ser vacío");
                return;
            }
            if (personasBean.getNombre1().equalsIgnoreCase("")) {
                personasBean.setCodeMensaje(2);
                personasBean.setMensaje("El campo de Primer Nombre no puede ser vacío");
                return;
            }
            if (personasBean.getApellido1().equalsIgnoreCase("")) {
                personasBean.setCodeMensaje(2);
                personasBean.setMensaje("El campo de Primer Apellido no puede ser vacío");
                return;
            }
            if (personasBean.getApellido2().equalsIgnoreCase("")) {
                personasBean.setCodeMensaje(2);
                personasBean.setMensaje("El campo de Segundo Apellido no puede ser vacío");
                return;
            }
//            if (personasBean.getFechaNacimiento() == null) {
//                personasBean.setCodeMensaje(2);
//                personasBean.setMensaje("La Fecha de Nacimiento es inválida");
//                return;
//            }
            if (personasBean.getEmail().equalsIgnoreCase("")) {
                personasBean.setCodeMensaje(2);
                personasBean.setMensaje("Debe ingresar un Email");
                return;
            }
            
            gimPersona = daoPersona.getPersonaByDoc(session, personasBean.getNumeroDocumento());
            
            if(gimPersona == null)
            {
                personasBean.setCodeMensaje(3);
                personasBean.setMensaje("La persona no se encuentra registrada");
                return;
            }
            
            gimPersona.setPersNombre1(personasBean.getNombre1());
            gimPersona.setPersNombre2(personasBean.getNombre2());
            gimPersona.setPersEmail(personasBean.getEmail());
            
            daoPersona2.update(session, gimPersona);
            
            personasBean.setCodeMensaje(1);
            personasBean.setMensaje("La persona se actualizó correctamente");
            
            transaction.commit();
            
        } catch (Exception e) {

            if (transaction != null) {
                transaction.rollback();
            }
        } finally{
            
            if(session != null)
            {
                session.close();
            }
        }
    }

    @Override
    public void listaPersonas(PersonasBean personasBean) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminarPersona(PersonasBean personasBean) throws Exception {
        
        Session session = HibernateUtil.HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        
        GimPersona gimPersona;
        
        try {
            
            //Se hacen las validaciones para la base de datos
            if (personasBean.getNumeroDocumento().equalsIgnoreCase("")) {
                personasBean.setCodeMensaje(2);
                personasBean.setMensaje("El campo Número de Documento no puede ser vacío");
                return;
            }
            
            gimPersona = daoPersona.getPersonaByDoc(session, personasBean.getNumeroDocumento());
            
            if(gimPersona == null)
            {
                personasBean.setCodeMensaje(3);
                personasBean.setMensaje("La persona no se encuentra registrada");
                return;
            }
            
            
            gimPersona.setPersEstado(BigDecimal.valueOf(2));
            
            daoPersona2.update(session, gimPersona);
            
            personasBean.setCodeMensaje(1);
            personasBean.setMensaje("La persona se eliminó correctamente");
            
            transaction.commit();
            
        } catch (Exception e) {

            if (transaction != null) {
                transaction.rollback();
            }
        } finally{
            
            if(session != null)
            {
                session.close();
            }
        }
        
    }
    
}
