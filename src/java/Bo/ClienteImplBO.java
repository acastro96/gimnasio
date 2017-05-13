/*
 * Proyecto Gimnasio Virtual. 
 * Universidad Simón Bolívar - Barranquilla / Colombia.
 * Desarrollado por Ing. Alberto Castro Maestre
 */
package Bo;

import Bean.ClientesBean;
import Dao.DaoCliente;
import Dao.DaoDireccion;
import Dao.DaoParametros;
import Dao.DaoPerfil;
import Dao.DaoPersona;
import Dao.DaoTelefono;
import Dao.DaoUsuario;
import Interface.ITCliente;
import Interface.ITDireccion;
import Interface.ITParametros;
import Interface.ITPerfil;
import Interface.ITPersona;
import Interface.ITTelefono;
import Interface.ITUsuario;
import Pojo.GimCliente;
import Pojo.GimDireccion;
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
public class ClienteImplBO implements ClienteBO{

   
    private ITUsuario daoUsuario;
    private ITTelefono daoTelefono;
    private ITDireccion daoDireccion;
    private ITCliente daoCliente;
    private ITPerfil daoPerfil;
    private ITPersona daoPersona;
    private ITParametros daoParametros;
    
    public ClienteImplBO(){
        daoUsuario = new DaoUsuario();
        daoTelefono = new DaoTelefono();
        daoDireccion = new DaoDireccion();
        daoCliente = new DaoCliente();
        daoPerfil = new DaoPerfil();
        daoPersona = new DaoPersona();
        daoParametros = new DaoParametros();
    }

    @Override
    public void mostrarCliente(ClientesBean clientesBean) throws Exception {
        
        //Esta sesión se declara porque es la que se va a enviar a la hora de hacer las consultas y las inserciones
        Session session = HibernateUtil.HibernateUtil.getSessionFactory().openSession();
        
        //Traemos todas las tablas que estamos utilizando
        GimCliente gimCliente;
        GimPersona gimPersona;
        GimTelefono gimTelefono;
        GimDireccion gimDireccion;
        GimUsuario gimUsuario;
        
        try {
            
            gimCliente = getDaoCliente().getClienteByID(session, clientesBean.getId());
            
            gimPersona = getDaoPersona().getPersonaByID(session, gimCliente.getGimPersona().getPersId());
            
            //Consultamos a ver si el cliente tiene algun teléfono o dirección asociados
            gimTelefono = getDaoTelefono().getByIdPersona(session, gimPersona.getPersId());
            gimDireccion = getDaoDireccion().getByIdPersona(session, gimPersona.getPersId());
            
            //Traemos todos los datos basicos que se sacan de la tabla persona, telefonos y direcciones
            clientesBean.setCodigo(gimCliente.getCliCodigo());
            clientesBean.setNombre1(gimPersona.getPersNombre1());
            if (gimPersona.getPersNombre2() != null) {
                clientesBean.setNombre2(gimPersona.getPersNombre2());
            }
            clientesBean.setApellido1(gimPersona.getPersApellido1());
            clientesBean.setApellido2(gimPersona.getPersApellido2());
            clientesBean.setFechaNacimiento(gimPersona.getPersFechanacimiento());
            clientesBean.setLugarNacimiento(gimPersona.getPersLugarnacimiento().intValue());
            clientesBean.setGrupoSanguineo(gimPersona.getPersGruposanguineo().intValue());
            clientesBean.setRh(gimPersona.getPersRh());
            clientesBean.setSexo(gimPersona.getPersSexo().intValue());
            if (gimTelefono != null) {
                clientesBean.setTelefono(gimTelefono.getTelTelefono());
                clientesBean.setCelular(gimTelefono.getTelCelular());
            }
            if (gimDireccion != null) {
                clientesBean.setDireccion(gimDireccion.getDirDesc());
            }
            
            clientesBean.setDescripcion(gimCliente.getCliDescripcion());
            clientesBean.setTipoPlanPreferencia(gimCliente.getCliTipoplanTrabajo().intValue());
            clientesBean.setEstado(gimCliente.getCliEstado().intValue()==1?"ACTIVO":"INACTIVO");
            
            //Información de Usuario
            gimUsuario = getDaoUsuario().getUsuarioByIdPesona(session, gimPersona.getPersId());
            clientesBean.setUsuNom(gimUsuario.getUsuNombre());
            
            clientesBean.setCodeMensaje(1);
            clientesBean.setMensaje("Consulta exitosa");

        } catch (Exception e) {
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void agregarCliente(ClientesBean clientesBean) throws Exception {
        
        //Se prepara la session con la que se va a insertar en las tablas
        Session session = HibernateUtil.HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        
        //Todas las tablas que se van a utilizar en insercion y consulta, cabe resaltar que las de solo insercion no es necesario instanciarlas
        GimCliente gimCliente;
        GimPersona gimPersona;
        GimUsuario gimUsuario;
        GimPerfil gimPerfil;
        int fkpersona;
        
        try {
            
            //Se hacen las validaciones para que la base de datos no se reviente
            if (clientesBean.getNumeroDocumento().equalsIgnoreCase("")) {
                clientesBean.setCodeMensaje(2);
                clientesBean.setMensaje("El campo Número de Documento no puede ser vacío");
                return;
            }
            if (clientesBean.getNombre1().equalsIgnoreCase("")) {
                clientesBean.setCodeMensaje(2);
                clientesBean.setMensaje("El campo de Primer Nombre no puede ser vacío");
                return;
            }
            if (clientesBean.getApellido1().equalsIgnoreCase("")) {
                clientesBean.setCodeMensaje(2);
                clientesBean.setMensaje("El campo de Primer Apellido no puede ser vacío");
                return;
            }
            if (clientesBean.getApellido2().equalsIgnoreCase("")) {
                clientesBean.setCodeMensaje(2);
                clientesBean.setMensaje("El campo de Segundo Apellido no puede ser vacío");
                return;
            }
            if (clientesBean.getCodigo().equalsIgnoreCase("")) {
                clientesBean.setCodeMensaje(2);
                clientesBean.setMensaje("El campo Código no puede ser vacío");
                return;
            }
//            if (instructoresBean.getFechaNacimiento() == null) {
//                instructoresBean.setCodeMensaje(2);
//                instructoresBean.setMensaje("La Fecha de Nacimiento es inválida");
//                return;
//            }
            if (clientesBean.getTelefono().equalsIgnoreCase("") && clientesBean.getCelular().equalsIgnoreCase("")) {
                clientesBean.setCodeMensaje(2);
                clientesBean.setMensaje("Inserte al menos un telefono o un celular");
                return;
            }
            if (!(clientesBean.getUsuPass().equals(clientesBean.getUsuConpass()))) {
                clientesBean.setCodeMensaje(2);
                clientesBean.setMensaje("Las contraseñas ingresadas no son iguales");
                return;
            }

            if (clientesBean.getEmail().equalsIgnoreCase("")) {
                clientesBean.setCodeMensaje(2);
                clientesBean.setMensaje("Debe ingresar un Email");
                return;
            }
            
            //Se consulta a ver si esta registrado en la tabla PERSONA
            gimPersona = getDaoPersona().getPersonaByDoc(session, clientesBean.getNumeroDocumento());

//            if (gimPersona == null) {
//                gimPersona = new GimPersona(BigDecimal.ZERO, clientesBean.getNombre1(), clientesBean.getNombre2(), clientesBean.getApellido1(), clientesBean.getApellido2(), BigDecimal.valueOf(clientesBean.getTipoDocumento()), clientesBean.getFechaNacimiento(), BigDecimal.valueOf(clientesBean.getLugarNacimiento()), BigDecimal.valueOf(clientesBean.getGrupoSanguineo()), clientesBean.getRh(), BigDecimal.valueOf(clientesBean.getSexo()), null, null, BigDecimal.ONE, clientesBean.getNumeroDocumento(), clientesBean.getEmail());
//                fkpersona = daoPersona.insert(session, gimPersona);
//                gimPersona.setPersId(BigDecimal.valueOf(fkpersona));
//            } else {
//                gimCliente = daoCliente.getClienteByIdPersona(session, daoPersona.getPersonaByDoc(session, clientesBean.getNumeroDocumento()).getPersId());
//
//                if (gimCliente != null) {
//                    clientesBean.setCodeMensaje(3);
//                    clientesBean.setMensaje("El Cliente ya se encuentra registrado");
//                    return;
//                }
//            }
            
            if(gimPersona!=null){
                clientesBean.setCodeMensaje(3);
                clientesBean.setMensaje("El Cliente ya se encuentra registrado");
                return;
            }
            
            gimCliente = getDaoCliente().getClienteByCod(session, clientesBean.getCodigo());
            
            if(gimCliente!=null){
                clientesBean.setCodeMensaje(3);
                clientesBean.setMensaje("El Código de Cliente ya se encuentra registrado");
                return;
            }
            
            //Consulta a ver si el usuario existe
            gimUsuario = getDaoUsuario().getUsuarioByNom(session, clientesBean.getUsuNom());

            if (gimUsuario != null) {
                clientesBean.setCodeMensaje(3);
                clientesBean.setMensaje("El Nombre de Usuario ya se encuentra registrado");
                return;
            }
            
            gimPersona = new GimPersona(BigDecimal.ZERO, clientesBean.getNombre1(), clientesBean.getNombre2(), clientesBean.getApellido1(), clientesBean.getApellido2(), BigDecimal.valueOf(clientesBean.getTipoDocumento()), clientesBean.getFechaNacimiento(), BigDecimal.valueOf(clientesBean.getLugarNacimiento()), BigDecimal.valueOf(clientesBean.getGrupoSanguineo()), clientesBean.getRh(), BigDecimal.valueOf(clientesBean.getSexo()), null, null, BigDecimal.ONE, clientesBean.getNumeroDocumento(), clientesBean.getEmail());
            fkpersona = getDaoPersona().insert(session, gimPersona);
            gimPersona.setPersId(BigDecimal.valueOf(fkpersona));
            
            transaction.commit();
            transaction = session.beginTransaction();
            
            //Se manda como parametro el numero 3 porque ese en la tabla PERFILES el cliente es de id = 3
            gimPerfil = getDaoPerfil().getPerfilById(session, BigDecimal.valueOf(3));
            getDaoUsuario().insert(session, new GimUsuario(BigDecimal.ZERO, gimPerfil, gimPersona, clientesBean.getUsuNom(), clientesBean.getUsuPass(), null, null, null, BigDecimal.ONE));
            
            if (!clientesBean.getDireccion().equalsIgnoreCase("")) {
                getDaoDireccion().insert(session, new GimDireccion(BigDecimal.ONE, gimPersona, clientesBean.getDireccion(), null, BigDecimal.ONE));
            }

            getDaoTelefono().insert(session, new GimTelefono(BigDecimal.ZERO, gimPersona, clientesBean.getTelefono(), clientesBean.getCelular(), null, BigDecimal.ONE));
            
            getDaoCliente().insert(session, new GimCliente(BigDecimal.ZERO, gimPersona, BigDecimal.valueOf(clientesBean.getTipoPlanPreferencia()), clientesBean.getDescripcion(), clientesBean.getCodigo(), BigDecimal.ONE, null));
            
            //Si todo termina exitosamente se guarda la transacción
            clientesBean.setCodeMensaje(1);
            clientesBean.setMensaje("El Cliente se registró correctamente");
            
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
    public void actualizarCliente(ClientesBean clientesBean) throws Exception {
        Session session = HibernateUtil.HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        
        //Todas las tablas que se van a utilizar en insercion y consulta, cabe resaltar que las de solo insercion no es necesario instanciarlas
        GimCliente gimCliente;
        GimPersona gimPersona;
        GimTelefono gimTelefono;
        GimDireccion gimDireccion;
        
        try {
            
            //Se hacen las validaciones para que la base de datos no se reviente
            if (clientesBean.getNumeroDocumento().equalsIgnoreCase("")) {
                clientesBean.setCodeMensaje(2);
                clientesBean.setMensaje("El campo Número de Documento no puede ser vacío");
                return;
            }
            if (clientesBean.getNombre1().equalsIgnoreCase("")) {
                clientesBean.setCodeMensaje(2);
                clientesBean.setMensaje("El campo de Primer Nombre no puede ser vacío");
                return;
            }
            if (clientesBean.getApellido1().equalsIgnoreCase("")) {
                clientesBean.setCodeMensaje(2);
                clientesBean.setMensaje("El campo de Primer Apellido no puede ser vacío");
                return;
            }
            if (clientesBean.getApellido2().equalsIgnoreCase("")) {
                clientesBean.setCodeMensaje(2);
                clientesBean.setMensaje("El campo de Segundo Apellido no puede ser vacío");
                return;
            }
            if (clientesBean.getCodigo().equalsIgnoreCase("")) {
                clientesBean.setCodeMensaje(2);
                clientesBean.setMensaje("El campo Código no puede ser vacío");
                return;
            }
//            if (instructoresBean.getFechaNacimiento() == null) {
//                instructoresBean.setCodeMensaje(2);
//                instructoresBean.setMensaje("La Fecha de Nacimiento es inválida");
//                return;
//            }
            if (clientesBean.getTelefono().equalsIgnoreCase("") && clientesBean.getCelular().equalsIgnoreCase("")) {
                clientesBean.setCodeMensaje(2);
                clientesBean.setMensaje("Inserte al menos un telefono o un celular");
                return;
            }
            if (!(clientesBean.getUsuPass().equals(clientesBean.getUsuConpass()))) {
                clientesBean.setCodeMensaje(2);
                clientesBean.setMensaje("Las contraseñas ingresadas no son iguales");
                return;
            }

            if (clientesBean.getEmail().equalsIgnoreCase("")) {
                clientesBean.setCodeMensaje(2);
                clientesBean.setMensaje("Debe ingresar un Email");
                return;
            }
            
            gimPersona = getDaoPersona().getPersonaByDoc(session, clientesBean.getNumeroDocumento());
            
            if (gimPersona == null) {
                clientesBean.setCodeMensaje(2);
                clientesBean.setMensaje("El Cliente no está registrado, Compruebe el numero de documento");
                return;
            }
            
            gimCliente = getDaoCliente().getClienteByIdPersona(session, gimPersona.getPersId());
            
            if (gimCliente == null) {
                clientesBean.setCodeMensaje(2);
                clientesBean.setMensaje("El Cliente no está registrado");
                return;
            }
            
            gimPersona.setPersNombre1(clientesBean.getNombre1());
            gimPersona.setPersNombre2(clientesBean.getNombre2());
            gimPersona.setPersEmail(clientesBean.getEmail());
            gimPersona.setPersGruposanguineo(BigDecimal.valueOf(clientesBean.getGrupoSanguineo()));
            gimPersona.setPersRh(clientesBean.getRh());
            
            
            gimCliente.setCliDescripcion(clientesBean.getDescripcion());
            gimCliente.setCliTipoplanTrabajo(BigDecimal.valueOf(clientesBean.getTipoPlanPreferencia()));
            
            gimDireccion = getDaoDireccion().getByIdPersona(session, gimPersona.getPersId());
            
            if(gimDireccion!=null){
                if(!gimDireccion.getDirDesc().equalsIgnoreCase(clientesBean.getDireccion())){
                    gimDireccion.setDirEstado(BigDecimal.valueOf(2));
                    getDaoDireccion().update(session, gimDireccion);
                    getDaoDireccion().insert(session, new GimDireccion(BigDecimal.ZERO, gimPersona, clientesBean.getDireccion(), null, BigDecimal.ONE));
                }
            }
            
            gimTelefono = getDaoTelefono().getByIdPersona(session, gimPersona.getPersId());
            
            if(!gimTelefono.getTelTelefono().equalsIgnoreCase(clientesBean.getTelefono()) || !gimTelefono.getTelCelular().equalsIgnoreCase(clientesBean.getCelular())){
                gimTelefono.setTelEstado(BigDecimal.valueOf(2));
                getDaoTelefono().update(session, gimTelefono);
                getDaoTelefono().insert(session, new GimTelefono(BigDecimal.ZERO, gimPersona, clientesBean.getTelefono(), clientesBean.getCelular(), null, BigDecimal.ONE)); 
            }
            
            getDaoPersona().update(session, gimPersona);
            getDaoCliente().update(session, gimCliente);
            
            //Si todo termina exitosamente se guarda la transacción
            clientesBean.setCodeMensaje(1);
            clientesBean.setMensaje("El Cliente se actualizó correctamente");
            
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
    public List<GimCliente> listaClientes(ClientesBean clientesBean) throws Exception {
        Session session = HibernateUtil.HibernateUtil.getSessionFactory().openSession();
        
        List<GimCliente> listCliente = new LinkedList<>();
        GimCliente gimCliente;
        GimPersona gimPersona;
        
        try {
            
            switch(clientesBean.getTipo()){
                
                case 1:// Consulta por codigo de cliente
                    
                    if(clientesBean.getCodigo().equalsIgnoreCase("")){
                        clientesBean.setCodeMensaje(2);
                        clientesBean.setMensaje("Digite el parametro de busqueda");
                        return null;
                    }
                    
                    gimCliente = getDaoCliente().getClienteByCod(session, clientesBean.getCodigo());
                    
                    if(gimCliente==null){
                        clientesBean.setCodeMensaje(2);
                        clientesBean.setMensaje("El Cliente no está registrado");
                        return null;
                    }
                    
                    listCliente.add(gimCliente);
                    
                    
                    break;
                case 2:// Consulta por numero de documento
                    
                    if(clientesBean.getNumeroDocumento().equalsIgnoreCase("")){
                        clientesBean.setCodeMensaje(2);
                        clientesBean.setMensaje("Digite el parametro de busqueda");
                        return null;
                    }
                    
                    gimPersona = getDaoPersona().getPersonaByDoc(session, clientesBean.getNumeroDocumento());
                    
                    if(gimPersona == null){
                        clientesBean.setCodeMensaje(2);
                        clientesBean.setMensaje("El Cliente no está registrado");
                        return null;
                    }
                    
                    gimCliente = getDaoCliente().getClienteByIdPersona(session, gimPersona.getPersId());
                    
                    listCliente.add(gimCliente);
                    
                    break;
                case 3://Consulta por Nombre de cliente -Retorna una lista con todos los clientes con ese nombre-
                    
                    break;
            }
            return listCliente;
            
        } catch (Exception e) {
            clientesBean.setCodeMensaje(4);
            clientesBean.setMensaje("Ha ocurrido un error interno, comuniquese con el administrador");
            return null;
        }
    }
    
    @Override
    public void llenarParametros(ClientesBean clientesBean) throws Exception {
        Session session = HibernateUtil.HibernateUtil.getSessionFactory().openSession();
        
        List<GimParametros> listParametros;
        
        try {

            clientesBean.setParTipoDocumento(new LinkedHashMap<Integer, String>());
            clientesBean.setParLugarNacimiento(new LinkedHashMap<Integer, String>());
            clientesBean.setParGrupoSanguineo(new LinkedHashMap<Integer, String>());
            clientesBean.setParPreferencias(new LinkedHashMap<Integer, String>());
            clientesBean.setParSexo(new LinkedHashMap<Integer, String>());
            
            //Tipos de Documentos
            listParametros = getDaoParametros().getParametroByGrupo(session, BigDecimal.ONE);
            for (GimParametros objParametro : listParametros) {
                clientesBean.getParTipoDocumento().put(objParametro.getParCodigo().intValue(), objParametro.getParNombre());
            }

            //Lugares de Nacimiento
            listParametros = getDaoParametros().getParametroByGrupo(session, BigDecimal.valueOf(2));
            for (GimParametros objParametro : listParametros) {
                clientesBean.getParLugarNacimiento().put(objParametro.getParCodigo().intValue(), objParametro.getParNombre());
            }
            
            //Grupos Sanguineos
            listParametros = getDaoParametros().getParametroByGrupo(session, BigDecimal.valueOf(3));
            for (GimParametros objParametro : listParametros) {
                clientesBean.getParGrupoSanguineo().put(objParametro.getParCodigo().intValue(), objParametro.getParNombre());
            }
            
            //Sexos
            listParametros = getDaoParametros().getParametroByGrupo(session, BigDecimal.valueOf(5));
            for (GimParametros objParametro : listParametros) {
                clientesBean.getParSexo().put(objParametro.getParCodigo().intValue(), objParametro.getParNombre());
            }
            
            //Preferencias del Cliente
            listParametros = getDaoParametros().getParametroByGrupo(session, BigDecimal.valueOf(8));
            for (GimParametros objParametro : listParametros) {
                clientesBean.getParPreferencias().put(objParametro.getParCodigo().intValue(), objParametro.getParNombre());
            }
            
        } catch (Exception e) {
        } finally {

            if (session != null) {
                session.close();
            }
        }
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
     * @return the daoCliente
     */
    public ITCliente getDaoCliente() {
        return daoCliente;
    }

    /**
     * @param daoCliente the daoCliente to set
     */
    public void setDaoCliente(ITCliente daoCliente) {
        this.daoCliente = daoCliente;
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