/*
 * Proyecto Gimnasio Virtual. 
 * Universidad Simón Bolívar - Barranquilla / Colombia.
 * Desarrollado por Ing. Alberto Castro Maestre
 */
package Bo;

import Bean.InstructoresBean;
import Dao.DaoDireccion;
import Dao.DaoInstructor;
import Dao.DaoParametros;
import Dao.DaoPerfil;
import Dao.DaoPersona;
import Dao.DaoTelefono;
import Dao.DaoUsuario;
import Interface.ITDireccion;
import Interface.ITInstructor;
import Interface.ITParametros;
import Interface.ITPerfil;
import Interface.ITPersona;
import Interface.ITTelefono;
import Interface.ITUsuario;
import Pojo.GimDireccion;
import Pojo.GimInstructor;
import Pojo.GimParametros;
import Pojo.GimPerfil;
import Pojo.GimPersona;
import Pojo.GimTelefono;
import Pojo.GimUsuario;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Alberto Castro
 */
public class InstructoresImplBO implements InstructoresBO {

    private ITUsuario daoUsuario;
    private ITInstructor daoInstructor;
    private ITPersona daoPersona;
    private ITPerfil daoPerfil;
    private ITTelefono daoTelefono;
    private ITDireccion daoDireccion;
    private ITParametros daoParametros;

    public InstructoresImplBO() {
        daoUsuario = new DaoUsuario();
        daoInstructor = new DaoInstructor();
        daoPerfil = new DaoPerfil();
        daoPersona = new DaoPersona();
        daoParametros = new DaoParametros();
        daoTelefono = new DaoTelefono();
        daoDireccion = new DaoDireccion();
    }

    @Override
    public void consultarInstructor(InstructoresBean instructoresBean) throws Exception {

        //Esta sesión se declara porque es la que se va a enviar a la hora de hacer las consultas y las inserciones
        Session session = HibernateUtil.HibernateUtil.getSessionFactory().openSession();

        //Traemos todas las tablas que estamos utilizando
        GimInstructor gimInstructor;
        GimTelefono gimTelefono;
        GimDireccion gimDireccion;
        GimUsuario gimUsuario;

        try {
            //Consultamos el instructor a ver si se encuentra en la base de datos
            gimInstructor = getDaoInstructor().getInstructorById(session, instructoresBean.getId());

            //Si el objeto viene nulo quiere decir que no está el instructor en la tabla
            if (gimInstructor == null) {
                instructoresBean.setCodeMensaje(3);
                instructoresBean.setMensaje("El instructor no se encuentra registrado");
                return;
            }

            //Consultamos a ver si el instructor tiene algun teléfono o dirección asociados
            gimTelefono = getDaoTelefono().getByIdPersona(session, gimInstructor.getGimPersona().getPersId());
            gimDireccion = getDaoDireccion().getByIdPersona(session, gimInstructor.getGimPersona().getPersId());

            //Traemos todos los datos basicos que se sacan de la tabla persona, telefonos y direcciones
            instructoresBean.setCodigo(gimInstructor.getInstCodigo());
            instructoresBean.setNombre1(gimInstructor.getGimPersona().getPersNombre1());
            if (gimInstructor.getGimPersona().getPersNombre2() != null) {
                instructoresBean.setNombre2(gimInstructor.getGimPersona().getPersNombre2());
            }
            instructoresBean.setApellido1(gimInstructor.getGimPersona().getPersApellido1());
            instructoresBean.setApellido2(gimInstructor.getGimPersona().getPersApellido2());
            instructoresBean.setFechaNacimiento(gimInstructor.getGimPersona().getPersFechanacimiento());
            instructoresBean.setLugarNacimiento(gimInstructor.getGimPersona().getPersLugarnacimiento().intValue());
            instructoresBean.setGrupoSanguineo(gimInstructor.getGimPersona().getPersGruposanguineo().intValue());
            instructoresBean.setRh(gimInstructor.getGimPersona().getPersRh());
            instructoresBean.setSexo(gimInstructor.getGimPersona().getPersSexo().intValue());
            if (gimTelefono != null) {
                instructoresBean.setTelefono(gimTelefono.getTelTelefono());
                instructoresBean.setCelular(gimTelefono.getTelCelular());
            }
            if (gimDireccion != null) {
                instructoresBean.setDireccion(gimDireccion.getDirDesc());
            }

            //Ahora se llenan todos los datos especificos 
            instructoresBean.setFechaIngresoGim(gimInstructor.getInstFechaingreso());
            instructoresBean.setNivelEstudio(gimInstructor.getInstNivelestudio().intValue());
            instructoresBean.setNivelNutricion(gimInstructor.getInstNivelnutricion().intValue());
            instructoresBean.setEspecialidad(gimInstructor.getInstEspecialidad().intValue());

            gimUsuario = getDaoUsuario().getUsuarioByIdPesona(session, gimInstructor.getGimPersona().getPersId());

            //Ahora se llena la información de usuario
            instructoresBean.setUsuNom(gimUsuario.getUsuNombre());

            instructoresBean.setCodeMensaje(1);
            instructoresBean.setMensaje("Consulta exitosa");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (session != null) {
                session.close();
            }
        }

    }

    @Override
    public void agregarInstructor(InstructoresBean instructoresBean) throws Exception {

        //Se prepara la session con la que se va a insertar en las tablas
        Session session = HibernateUtil.HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        //Todas las tablas que se van a utilizar en insercion y consulta, cabe resaltar que las de solo insercion no es necesario instanciarlas
        GimInstructor gimInstructor;
        GimPersona gimPersona;
        GimUsuario gimUsuario;
        GimPerfil gimPerfil;
        int fkpersona;
        try {

            //Se hacen las validaciones para que la base de datos no se reviente
            if (instructoresBean.getNumeroDocumento().equalsIgnoreCase("")) {
                instructoresBean.setCodeMensaje(2);
                instructoresBean.setMensaje("El campo Número de Documento no puede ser vacío");
                return;
            }
            if (instructoresBean.getNombre1().equalsIgnoreCase("")) {
                instructoresBean.setCodeMensaje(2);
                instructoresBean.setMensaje("El campo de Primer Nombre no puede ser vacío");
                return;
            }
            if (instructoresBean.getApellido1().equalsIgnoreCase("")) {
                instructoresBean.setCodeMensaje(2);
                instructoresBean.setMensaje("El campo de Primer Apellido no puede ser vacío");
                return;
            }
            if (instructoresBean.getApellido2().equalsIgnoreCase("")) {
                instructoresBean.setCodeMensaje(2);
                instructoresBean.setMensaje("El campo de Segundo Apellido no puede ser vacío");
                return;
            }
            if (instructoresBean.getCodigo().equalsIgnoreCase("")) {
                instructoresBean.setCodeMensaje(2);
                instructoresBean.setMensaje("El campo Código no puede ser vacío");
                return;
            }
//            if (instructoresBean.getFechaNacimiento() == null) {
//                instructoresBean.setCodeMensaje(2);
//                instructoresBean.setMensaje("La Fecha de Nacimiento es inválida");
//                return;
//            }
            if (instructoresBean.getTelefono().equalsIgnoreCase("") && instructoresBean.getCelular().equalsIgnoreCase("")) {
                instructoresBean.setCodeMensaje(2);
                instructoresBean.setMensaje("Inserte al menos un telefono o un celular");
                return;
            }
            if (!(instructoresBean.getUsuPass().equals(instructoresBean.getUsuConpass()))) {
                instructoresBean.setCodeMensaje(2);
                instructoresBean.setMensaje("Las contraseñas ingresadas no son iguales");
                return;
            }

            if (instructoresBean.getEmail().equalsIgnoreCase("")) {
                instructoresBean.setCodeMensaje(2);
                instructoresBean.setMensaje("Debe ingresar un Email");
                return;
            }
            
            //Consulta a ver si el usuario existe
            gimUsuario = getDaoUsuario().getUsuarioByNom(session, instructoresBean.getUsuNom());

            if (gimUsuario != null) {
                instructoresBean.setCodeMensaje(3);
                instructoresBean.setMensaje("El nombre de usuario es inválido");
                return;
            }

            //Se consulta a ver si esta registrado en la tabla PERSONA
            gimPersona = getDaoPersona().getPersonaByDoc(session, instructoresBean.getNumeroDocumento());

            if (gimPersona == null) {
                gimPersona = new GimPersona(BigDecimal.ZERO, instructoresBean.getNombre1(), instructoresBean.getNombre2(), instructoresBean.getApellido1(), instructoresBean.getApellido2(), BigDecimal.valueOf(instructoresBean.getTipoDocumento()), instructoresBean.getFechaNacimiento(), BigDecimal.valueOf(instructoresBean.getLugarNacimiento()), BigDecimal.valueOf(instructoresBean.getGrupoSanguineo()), instructoresBean.getRh(), BigDecimal.valueOf(instructoresBean.getSexo()), null, null, BigDecimal.ONE, instructoresBean.getNumeroDocumento(), instructoresBean.getEmail());
                fkpersona = getDaoPersona().insert(session, gimPersona);
                gimPersona.setPersId(BigDecimal.valueOf(fkpersona));
            } else {
                gimInstructor = getDaoInstructor().getInstructorByIdPersona(session, getDaoPersona().getPersonaByDoc(session, instructoresBean.getNumeroDocumento()).getPersId());

                if (gimInstructor != null) {
                    instructoresBean.setCodeMensaje(3);
                    instructoresBean.setMensaje("El Instructor ya se encuentra registrado");
                    return;
                }
            }

            //Consulta a ver si el usuario existe
            gimUsuario = getDaoUsuario().getUsuarioByIdPesona(session, gimPersona.getPersId());

            if (gimUsuario != null) {
                instructoresBean.setCodeMensaje(3);
                instructoresBean.setMensaje("La persona ya tiene un usuario asignado");
                return;
            }

            transaction.commit();
            transaction = session.beginTransaction();

            //Se manda como parametro el numero 2 porque ese en la tabla PERFILES el instructor es de id = 2
            gimPerfil = getDaoPerfil().getPerfilById(session, BigDecimal.valueOf(2));
            getDaoUsuario().insert(session, new GimUsuario(BigDecimal.ZERO, gimPerfil, gimPersona, instructoresBean.getUsuNom(), instructoresBean.getUsuPass(), null, null, null, BigDecimal.ONE));

            if (!instructoresBean.getDireccion().equalsIgnoreCase("")) {
                getDaoDireccion().insert(session, new GimDireccion(BigDecimal.ONE, gimPersona, instructoresBean.getDireccion(), null, BigDecimal.ONE));
            }

            getDaoTelefono().insert(session, new GimTelefono(BigDecimal.ZERO, gimPersona, instructoresBean.getTelefono(), instructoresBean.getCelular(), null, BigDecimal.ONE));

            getDaoInstructor().insert(session, new GimInstructor(BigDecimal.ZERO, gimPersona, instructoresBean.getFechaIngresoGim(), null, BigDecimal.valueOf(instructoresBean.getEspecialidad()), BigDecimal.valueOf(instructoresBean.getNivelEstudio()), null, BigDecimal.ONE, BigDecimal.valueOf(instructoresBean.getNivelNutricion()), instructoresBean.getCodigo()));

            //Si todo termina exitosamente se guarda la transacción
            instructoresBean.setCodeMensaje(1);
            instructoresBean.setMensaje("El Instructor se registró correctamente");

            transaction.commit();

        } catch (Exception e) {

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
    public void actualizarInstructor(InstructoresBean instructoresBean) throws Exception {

        Session session = HibernateUtil.HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        //Todas las tablas que se van a utilizar en insercion y consulta, cabe resaltar que las de solo insercion no es necesario instanciarlas
        GimInstructor gimInstructor;
        GimPersona gimPersona;

        try {
            //Se hacen las validaciones para que la base de datos no se reviente
            if (instructoresBean.getNumeroDocumento().equalsIgnoreCase("")) {
                instructoresBean.setCodeMensaje(2);
                instructoresBean.setMensaje("El campo Número de Documento no puede ser vacío");
                return;
            }
            if (instructoresBean.getNombre1().equalsIgnoreCase("")) {
                instructoresBean.setCodeMensaje(2);
                instructoresBean.setMensaje("El campo de Primer Nombre no puede ser vacío");
                return;
            }
            if (instructoresBean.getApellido1().equalsIgnoreCase("")) {
                instructoresBean.setCodeMensaje(2);
                instructoresBean.setMensaje("El campo de Primer Apellido no puede ser vacío");
                return;
            }
            if (instructoresBean.getApellido2().equalsIgnoreCase("")) {
                instructoresBean.setCodeMensaje(2);
                instructoresBean.setMensaje("El campo de Segundo Apellido no puede ser vacío");
                return;
            }
            if (instructoresBean.getFechaNacimiento() == null) {
                instructoresBean.setCodeMensaje(2);
                instructoresBean.setMensaje("La Fecha de Nacimiento es inválida");
                return;
            }
            if (instructoresBean.getTelefono().equalsIgnoreCase("") && instructoresBean.getCelular().equalsIgnoreCase("")) {
                instructoresBean.setCodeMensaje(2);
                instructoresBean.setMensaje("Inserte al menos un telefono o un celular");
                return;
            }
            if (!(instructoresBean.getUsuPass().equals(instructoresBean.getUsuConpass()))) {
                instructoresBean.setCodeMensaje(2);
                instructoresBean.setMensaje("Las contraseñas ingresadas no son iguales");
                return;
            }
            if (instructoresBean.getEmail().equalsIgnoreCase("")) {
                instructoresBean.setCodeMensaje(2);
                instructoresBean.setMensaje("Debe ingresar un Email");
                return;
            }

            gimPersona = getDaoPersona().getPersonaByDoc(session, instructoresBean.getNumeroDocumento());

            if (gimPersona == null) {
                instructoresBean.setCodeMensaje(2);
                instructoresBean.setMensaje("El instructor no está registrado, Compruebe el numero de documento");
                return;
            }

            gimInstructor = getDaoInstructor().getInstructorByIdPersona(session, gimPersona.getPersId());

            if (gimInstructor == null) {
                instructoresBean.setCodeMensaje(2);
                instructoresBean.setMensaje("El instructor no está registrado, Compruebe el numero de documento");
                return;
            }

            gimPersona.setPersNombre1(instructoresBean.getNombre1());
            gimPersona.setPersNombre2(instructoresBean.getNombre2());
            gimPersona.setPersEmail(instructoresBean.getEmail());

            gimInstructor.setInstEspecialidad(BigDecimal.valueOf(instructoresBean.getEspecialidad()));

            getDaoPersona().update(session, gimPersona);
            getDaoInstructor().update(session, gimInstructor);

            instructoresBean.setCodeMensaje(1);
            instructoresBean.setMensaje("El Instructor se actualizó correctamente");

            transaction.commit();

        } catch (Exception e) {

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
    public List<GimInstructor> listaInstructores(InstructoresBean instructoresBean) throws Exception {
        Session session = HibernateUtil.HibernateUtil.getSessionFactory().openSession();

        GimInstructor gimInstructor;
        GimPersona gimPersona;
        List<GimInstructor> listInst = new LinkedList<>();

        try {

            switch (instructoresBean.getTipo()) {
                case 1: // Busqueda por Codigo

                    if (instructoresBean.getCodigo().equalsIgnoreCase("")) {
                        instructoresBean.setCodeMensaje(2);
                        instructoresBean.setMensaje("Digite el parametro de busqueda");
                        return null;
                    }
                    
                    gimInstructor = getDaoInstructor().getInstructorByCod(session, instructoresBean.getCodigo());
                    
                    if (gimInstructor == null) {
                        instructoresBean.setCodeMensaje(3);
                        instructoresBean.setMensaje("El Instructor no se encuentra registrado");
                        return null;
                    }
                    listInst.add(gimInstructor);
                    break;

                case 2: //Busqueda por numero de documento

                    if (instructoresBean.getNumeroDocumento().equalsIgnoreCase("")) {
                        instructoresBean.setCodeMensaje(2);
                        instructoresBean.setMensaje("Digite el parametro de busqueda");
                        return null;
                    }
                    gimPersona = getDaoPersona().getPersonaByDoc(session, instructoresBean.getNumeroDocumento());
                    
                    if (gimPersona==null) {
                        instructoresBean.setCodeMensaje(3);
                        instructoresBean.setMensaje("El Instructor no se encuentra registrado");
                        return null;
                    }

                    gimInstructor = getDaoInstructor().getInstructorByIdPersona(session, gimPersona.getPersId());

                    if (gimInstructor == null) {
                        instructoresBean.setCodeMensaje(3);
                        instructoresBean.setMensaje("El Instructor no se encuentra registrado");
                        return null;
                    }

                    listInst.add(gimInstructor);
                    break;

                case 3: //Consulta por Nombre de instructor

                    if (instructoresBean.getNombre1().equalsIgnoreCase("")) {
                        instructoresBean.setCodeMensaje(2);
                        instructoresBean.setMensaje("Digite el parametro de busqueda");
                        return null;
                    }

                    listInst = getDaoInstructor().getlistByNombre(session, instructoresBean.getNombre1());

                    break;
            }

            instructoresBean.setCodeMensaje(1);
            instructoresBean.setMensaje("Consulta Exitosa");

            return listInst;

        } catch (Exception e) {
            instructoresBean.setCodeMensaje(4);
            instructoresBean.setMensaje("Ha ocurrido un error interno, comuniquese con el administrador");
            return null;
        } finally {

            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void llenarParametros(InstructoresBean instructoresBean) throws Exception {
        Session session = HibernateUtil.HibernateUtil.getSessionFactory().openSession();

        try {

            instructoresBean.setParTipoDocumento(new LinkedHashMap<Integer, String>());
            instructoresBean.setParLugarNacimiento(new LinkedHashMap<Integer, String>());
            instructoresBean.setParGrupoSanguineo(new LinkedHashMap<Integer, String>());
            instructoresBean.setParEspecialidad(new LinkedHashMap<Integer, String>());
            instructoresBean.setParNivelEstudio(new LinkedHashMap<Integer, String>());
            instructoresBean.setParSexo(new LinkedHashMap<Integer, String>());
            instructoresBean.setParNivelNutricion(new LinkedHashMap<Integer, String>());

            //Tipos de Documentos
            List<GimParametros> listParametros = getDaoParametros().getParametroByGrupo(session, BigDecimal.ONE);
            for (GimParametros objParametro : listParametros) {
                instructoresBean.getParTipoDocumento().put(objParametro.getParCodigo().intValue(), objParametro.getParNombre());
            }

            //Lugares de Nacimiento
            listParametros = getDaoParametros().getParametroByGrupo(session, BigDecimal.valueOf(2));
            for (GimParametros objParametro : listParametros) {
                instructoresBean.getParLugarNacimiento().put(objParametro.getParCodigo().intValue(), objParametro.getParNombre());
            }
            
            //Grupos Sanguineos
            listParametros = getDaoParametros().getParametroByGrupo(session, BigDecimal.valueOf(3));
            for (GimParametros objParametro : listParametros) {
                instructoresBean.getParGrupoSanguineo().put(objParametro.getParCodigo().intValue(), objParametro.getParNombre());
            }
            
            //Sexos
            listParametros = getDaoParametros().getParametroByGrupo(session, BigDecimal.valueOf(5));
            for (GimParametros objParametro : listParametros) {
                instructoresBean.getParSexo().put(objParametro.getParCodigo().intValue(), objParametro.getParNombre());
            }
            
            //Especialidad del Instructor
            listParametros = getDaoParametros().getParametroByGrupo(session, BigDecimal.valueOf(4));
            for (GimParametros objParametro : listParametros) {
                instructoresBean.getParEspecialidad().put(objParametro.getParCodigo().intValue(), objParametro.getParNombre());
            }
            
            //Nivel de estudio
            listParametros = getDaoParametros().getParametroByGrupo(session, BigDecimal.valueOf(7));
            for (GimParametros objParametro : listParametros) {
                instructoresBean.getParNivelEstudio().put(objParametro.getParCodigo().intValue(), objParametro.getParNombre());
            }
            
            //Nivel de experiencia y conocimientos de Nutricion
            listParametros = getDaoParametros().getParametroByGrupo(session, BigDecimal.valueOf(6));
            for (GimParametros objParametro : listParametros) {
                instructoresBean.getParNivelNutricion().put(objParametro.getParCodigo().intValue(), objParametro.getParNombre());
            }

        } catch (Exception e) {
        } finally {

            if (session != null) {
                session.close();
            }
        }

    }

    @Override
    public void mostrarInstructor(InstructoresBean instructoresBean) throws Exception {

    }

    /**
     * @return the daoUsuario
     */
    public ITUsuario getDaoUsuario() {
        return daoUsuario;
    }

    /**
     * @param daoUsuario the daoUsuario to set
     */
    public void setDaoUsuario(ITUsuario daoUsuario) {
        this.daoUsuario = daoUsuario;
    }

    /**
     * @return the daoInstructor
     */
    public ITInstructor getDaoInstructor() {
        return daoInstructor;
    }

    /**
     * @param daoInstructor the daoInstructor to set
     */
    public void setDaoInstructor(ITInstructor daoInstructor) {
        this.daoInstructor = daoInstructor;
    }

    /**
     * @return the daoPersona
     */
    public ITPersona getDaoPersona() {
        return daoPersona;
    }

    /**
     * @param daoPersona the daoPersona to set
     */
    public void setDaoPersona(ITPersona daoPersona) {
        this.daoPersona = daoPersona;
    }

    /**
     * @return the daoPerfil
     */
    public ITPerfil getDaoPerfil() {
        return daoPerfil;
    }

    /**
     * @param daoPerfil the daoPerfil to set
     */
    public void setDaoPerfil(ITPerfil daoPerfil) {
        this.daoPerfil = daoPerfil;
    }

    /**
     * @return the daoTelefono
     */
    public ITTelefono getDaoTelefono() {
        return daoTelefono;
    }

    /**
     * @param daoTelefono the daoTelefono to set
     */
    public void setDaoTelefono(ITTelefono daoTelefono) {
        this.daoTelefono = daoTelefono;
    }

    /**
     * @return the daoDireccion
     */
    public ITDireccion getDaoDireccion() {
        return daoDireccion;
    }

    /**
     * @param daoDireccion the daoDireccion to set
     */
    public void setDaoDireccion(ITDireccion daoDireccion) {
        this.daoDireccion = daoDireccion;
    }

    /**
     * @return the daoParametros
     */
    public ITParametros getDaoParametros() {
        return daoParametros;
    }

    /**
     * @param daoParametros the daoParametros to set
     */
    public void setDaoParametros(ITParametros daoParametros) {
        this.daoParametros = daoParametros;
    }

}
