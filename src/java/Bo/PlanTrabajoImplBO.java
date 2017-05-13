/*
 * Proyecto Gimnasio Virtual. 
 * Universidad Simón Bolívar - Barranquilla / Colombia.
 * Desarrollado por Ing. Alberto Castro Maestre
 */
package Bo;

import Bean.PlanTrabajoBean;
import Dao.DaoParametros;
import Dao.DaoPlanRut;
import Dao.DaoPlanTrabajo;
import Dao.DaoRutina;
import Dao.DaoUsuario;
import Interface.ITParametros;
import Interface.ITPlanRut;
import Interface.ITPlanTrabajo;
import Interface.ITRutina;
import Interface.ITUsuario;
import Pojo.GimParametros;
import Pojo.GimPlanRut;
import Pojo.GimPlanRutId;
import Pojo.GimPlanTrabajo;
import Pojo.GimRutina;
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
public class PlanTrabajoImplBO implements PlanTrabajoBO {

    private ITPlanTrabajo daoPlanTrabajo;
    private ITRutina daoRutina;
    private ITUsuario daoUsuario;
    private ITPlanRut daoPlanRut;
    private ITParametros daoParametros;
    
    public PlanTrabajoImplBO(){
        daoPlanTrabajo = new DaoPlanTrabajo();
        daoRutina = new DaoRutina();
        daoPlanRut = new DaoPlanRut();
        daoUsuario = new DaoUsuario();
        daoParametros = new DaoParametros();
    }

    @Override
    public List<GimPlanTrabajo> listarPlanTrabajo(PlanTrabajoBean planTrabajoBean) throws Exception {
        //Esta sesión se declara porque es la que se va a enviar a la hora de hacer las consultas y las inserciones
        Session session = HibernateUtil.HibernateUtil.getSessionFactory().openSession();
        GimPlanTrabajo gimPlanTrabajo;
//        GimRutina gimRutina;
//        List<GimPlanRut> listaPlanRuts;
//        List<GimRutina> listaRutinas = new LinkedList<>();
        List<GimPlanTrabajo> listPlanTrabajo = new LinkedList<>();

        try {
            switch (planTrabajoBean.getTipo()) {
                case 1://Consulta por codigo
                    if (planTrabajoBean.getCodigo().equalsIgnoreCase("")) {
                        planTrabajoBean.setCodeMensaje(2);
                        planTrabajoBean.setMensaje("Digite el parametro de busqueda");
                        return null;
                    }
                    gimPlanTrabajo = getDaoPlanTrabajo().getPlanTrabajoByCod(session, planTrabajoBean.getCodigo());
                    if (gimPlanTrabajo == null) {
                        planTrabajoBean.setCodeMensaje(3);
                        planTrabajoBean.setMensaje("Plan de trabajo no encontrado");
                        return null;
                    }
                    listPlanTrabajo.add(gimPlanTrabajo);

//                    listaPlanRuts = daoPlanRut.getPlanRutByIdPlan(session, gimPlanTrabajo.getPlanId());
//
//                    for (GimPlanRut objPlanRut : listaPlanRuts) {
//                        gimRutina = daoRutina.getRutinaByID(session, objPlanRut.getGimRutina().getRutId());
//                        listaRutinas.add(gimRutina);
//                    }
//
//                    planTrabajoBean.setListaRutinas(listaRutinas);
//                    planTrabajoBean.setCodigo(gimPlanTrabajo.getPlanCodigo());
//                    planTrabajoBean.setDescripcion(gimPlanTrabajo.getPlanDescripcion());
//                    planTrabajoBean.setNombre(gimPlanTrabajo.getPlanNombre());
//                    planTrabajoBean.setEstado(gimPlanTrabajo.getPlanEstado().intValue()==1?"ACTIVO":"INACTIVO");
//                    planTrabajoBean.setTipoEjercicio(gimPlanTrabajo.getPlanTipoEjercicio().intValue());
                    break;

                case 2: // Consulta por Nombre de Plan de Trabajo
                    if (planTrabajoBean.getNombre().equalsIgnoreCase("")) {
                        planTrabajoBean.setCodeMensaje(2);
                        planTrabajoBean.setMensaje("Digite el parametro de busqueda");
                        return null;
                    }
                    listPlanTrabajo = getDaoPlanTrabajo().getlistPlanTrabajoByNom(session, planTrabajoBean.getNombre());
                    break;
            }
            planTrabajoBean.setCodeMensaje(1);
            planTrabajoBean.setMensaje("Consulta exitosa");
            return listPlanTrabajo;

        } catch (Exception e) {
            planTrabajoBean.setMensaje("Ha ocurrido un error interno, comuniquese con el administrador");
            planTrabajoBean.setCodeMensaje(4);
            return null;
        } finally {

            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void agregarPlanTrabajo(PlanTrabajoBean planTrabajoBean) throws Exception {

        //Esta sesión se declara porque es la que se va a enviar a la hora de hacer las consultas y las inserciones
        Session session = HibernateUtil.HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        //Todas las tablas que se van a utilizar en insercion y consulta, cabe resaltar que las de solo insercion no es necesario instanciarlas
        GimPlanTrabajo gimPlanTrabajo;
        GimUsuario gimUsuario;

        try {

            //Se hacen las validaciones para que la base de datos no se reviente
            if (planTrabajoBean.getDescripcion().equalsIgnoreCase("")) {
                planTrabajoBean.setCodeMensaje(2);
                planTrabajoBean.setMensaje("El campo Descripción no puede ser vacío");
                return;
            }

            if (planTrabajoBean.getCodigo().equalsIgnoreCase("")) {
                planTrabajoBean.setCodeMensaje(2);
                planTrabajoBean.setMensaje("El campo Código no puede ser vacío");
                return;
            }

            if (planTrabajoBean.getNombre().equalsIgnoreCase("")) {
                planTrabajoBean.setCodeMensaje(2);
                planTrabajoBean.setMensaje("El campo Nombre no puede ser vacío");
                return;
            }

            gimPlanTrabajo = getDaoPlanTrabajo().getPlanTrabajoByCod(session, planTrabajoBean.getCodigo());

            if (gimPlanTrabajo != null) {
                planTrabajoBean.setCodeMensaje(3);
                planTrabajoBean.setMensaje("¡Error en el Código del Plan de Trabajo!, ya se encuentra registrado");
                return;
            }

            gimPlanTrabajo = getDaoPlanTrabajo().getPlanTrabajoByNom(session, planTrabajoBean.getNombre());

            if (gimPlanTrabajo != null) {
                planTrabajoBean.setCodeMensaje(3);
                planTrabajoBean.setMensaje("¡Error en el Nombre del Plan de Trabajo!, ya se encuentra registrado");
                return;
            }

            gimUsuario = getDaoUsuario().getUsuarioByID(session, BigDecimal.valueOf(planTrabajoBean.getLoginBean().getIdusuario()));

            getDaoPlanTrabajo().insert(session, new GimPlanTrabajo(BigDecimal.ZERO, gimUsuario, planTrabajoBean.getDescripcion(), planTrabajoBean.getNombre(), BigDecimal.valueOf(planTrabajoBean.getTipoEjercicio()), null, BigDecimal.ONE, planTrabajoBean.getCodigo()));

            transaction.commit();

            planTrabajoBean.setCodeMensaje(1);
            planTrabajoBean.setMensaje("El Plan de Trabajo se registró satisfactoriamente");

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
    public void actualizarPlanTrabajo(PlanTrabajoBean planTrabajoBean) throws Exception {

        //Esta sesión se declara porque es la que se va a enviar a la hora de hacer las consultas y las inserciones
        Session session = HibernateUtil.HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        //Todas las tablas que se van a utilizar en insercion y consulta, cabe resaltar que las de solo insercion no es necesario instanciarlas
        GimPlanTrabajo gimPlanTrabajo;

        try {
            //Se hacen las validaciones para que la base de datos no se reviente
            if (planTrabajoBean.getDescripcion().equalsIgnoreCase("")) {
                planTrabajoBean.setCodeMensaje(2);
                planTrabajoBean.setMensaje("El campo Descripción no puede ser vacío");
                return;
            }

            if (planTrabajoBean.getCodigo().equalsIgnoreCase("")) {
                planTrabajoBean.setCodeMensaje(2);
                planTrabajoBean.setMensaje("El campo Código no puede ser vacío");
                return;
            }

            if (planTrabajoBean.getNombre().equalsIgnoreCase("")) {
                planTrabajoBean.setCodeMensaje(2);
                planTrabajoBean.setMensaje("El campo Nombre no puede ser vacío");
                return;
            }

            gimPlanTrabajo = getDaoPlanTrabajo().getPlanTrabajoByCod(session, planTrabajoBean.getCodigo());

            if (gimPlanTrabajo == null) {
                planTrabajoBean.setCodeMensaje(3);
                planTrabajoBean.setMensaje("¡Error en el Código del Plan de Trabajo!, no se encuentra registrado");
                return;
            }

            gimPlanTrabajo.setPlanDescripcion(planTrabajoBean.getDescripcion());
            gimPlanTrabajo.setPlanTipoEjercicio(BigDecimal.valueOf(planTrabajoBean.getTipoEjercicio()));
            gimPlanTrabajo.setPlanNombre(planTrabajoBean.getNombre());

            getDaoPlanTrabajo().update(session, gimPlanTrabajo);

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
    public void agregarPlanRut(PlanTrabajoBean planTrabajoBean) throws Exception {

        //Esta sesión se declara porque es la que se va a enviar a la hora de hacer las consultas y las inserciones
        Session session = HibernateUtil.HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        //Todas las tablas que se van a utilizar en insercion y consulta, cabe resaltar que las de solo insercion no es necesario instanciarlas
        GimPlanTrabajo gimPlanTrabajo;
        GimPlanRut gimPlanRut;
        GimPlanRutId gimPlanRutId;
        GimRutina gimRutina;
        List<GimRutina> listaRutinas = new LinkedList<>();

        try {

            //Como se va buscar por nombre o codigo el parametro de busqueda es uno solo pero se hacen las dos busquedas a la vez, si una es nula entonces
            //se procede con la otra y si las dos son nulas quiere decir que la Rutina no existe y manda error
            if (planTrabajoBean.getParametroBusqueda().equalsIgnoreCase("")) {
                planTrabajoBean.setCodeMensaje(2);
                planTrabajoBean.setMensaje("Digite el parametro de busqueda");
                return;
            }

            gimRutina = getDaoRutina().getRutinaByCod(session, planTrabajoBean.getParametroBusqueda());

            if (gimRutina == null) {
                gimRutina = getDaoRutina().getRutinaByNom(session, planTrabajoBean.getParametroBusqueda());
            }

            if (gimRutina == null) {
                planTrabajoBean.setCodeMensaje(3);
                planTrabajoBean.setMensaje("Rutina no encontrada. Verifique la información");
                return;
            }

            planTrabajoBean.setNombreRutina(gimRutina.getRutNombre());

            if (!planTrabajoBean.getNombre().equalsIgnoreCase("")) {
                gimPlanTrabajo = getDaoPlanTrabajo().getPlanTrabajoByNom(session, planTrabajoBean.getNombre());
            } else {
                gimPlanTrabajo = getDaoPlanTrabajo().getPlanTrabajoByCod(session, planTrabajoBean.getCodigo());
            }

            gimPlanRutId = new GimPlanRutId(gimPlanTrabajo.getPlanId(), gimRutina.getRutId());

            daoPlanRut.insert(session, new GimPlanRut(gimPlanRutId,gimPlanTrabajo, gimRutina));
            
            if (planTrabajoBean.getListaRutinas() == null) {
                listaRutinas.add(gimRutina);
                planTrabajoBean.setListaRutinas(listaRutinas);
            } else {
                planTrabajoBean.getListaRutinas().add(gimRutina);
            }

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
    public void mostrarPlanTrabajo(PlanTrabajoBean planTrabajoBean) throws Exception {
        //Esta sesión se declara porque es la que se va a enviar a la hora de hacer las consultas
        Session session = HibernateUtil.HibernateUtil.getSessionFactory().openSession();
        
        GimPlanTrabajo gimPlanTrabajo;
        GimRutina gimRutina;
        List<GimPlanRut> listaPlanRuts;
        List<GimRutina> listaRutinas = new LinkedList<>();

        try {
            gimPlanTrabajo = getDaoPlanTrabajo().getPlanTrabajoByID(session, planTrabajoBean.getId());

            listaPlanRuts = getDaoPlanRut().getPlanRutByIdPlan(session, gimPlanTrabajo.getPlanId());

            for (GimPlanRut objPlanRut : listaPlanRuts) {
                gimRutina = getDaoRutina().getRutinaByID(session, objPlanRut.getGimRutina().getRutId());
                listaRutinas.add(gimRutina);
            }

            planTrabajoBean.setListaRutinas(listaRutinas);
            planTrabajoBean.setCodigo(gimPlanTrabajo.getPlanCodigo());
            planTrabajoBean.setDescripcion(gimPlanTrabajo.getPlanDescripcion());
            planTrabajoBean.setNombre(gimPlanTrabajo.getPlanNombre());
            planTrabajoBean.setEstado(gimPlanTrabajo.getPlanEstado().intValue() == 1 ? "ACTIVO" : "INACTIVO");
            planTrabajoBean.setTipoEjercicio(gimPlanTrabajo.getPlanTipoEjercicio().intValue());

            planTrabajoBean.setCodeMensaje(1);
            planTrabajoBean.setMensaje("Consulta exitosa");

        } catch (Exception e) {
        } finally {

            if (session != null) {
                session.close();
            }
        }
    }
    
    @Override
    public void llenarParametros(PlanTrabajoBean planTrabajoBean) throws Exception {
        
        Session session = HibernateUtil.HibernateUtil.getSessionFactory().openSession();
        
        List<GimParametros> listParametros;
        
        try {

            planTrabajoBean.setParTipoPlan(new LinkedHashMap<Integer, String>());
            
            //Tipos de Planes de trabajo
            listParametros = getDaoParametros().getParametroByGrupo(session, BigDecimal.valueOf(8));
            for (GimParametros objParametro : listParametros) {
                planTrabajoBean.getParTipoPlan().put(objParametro.getParCodigo().intValue(), objParametro.getParNombre());
            }
        } catch (Exception e) {
        } finally {

            if (session != null) {
                session.close();
            }
        }
    }
    
    /**
     * @return the daoPlanTrabajo
     */
    public ITPlanTrabajo getDaoPlanTrabajo() {
        return daoPlanTrabajo;
    }

    /**
     * @param daoPlanTrabajo the daoPlanTrabajo to set
     */
    public void setDaoPlanTrabajo(ITPlanTrabajo daoPlanTrabajo) {
        this.daoPlanTrabajo = daoPlanTrabajo;
    }

    /**
     * @return the daoRutina
     */
    public ITRutina getDaoRutina() {
        return daoRutina;
    }

    /**
     * @param daoRutina the daoRutina to set
     */
    public void setDaoRutina(ITRutina daoRutina) {
        this.daoRutina = daoRutina;
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
     * @return the daoPlanRut
     */
    public ITPlanRut getDaoPlanRut() {
        return daoPlanRut;
    }

    /**
     * @param daoPlanRut the daoPlanRut to set
     */
    public void setDaoPlanRut(ITPlanRut daoPlanRut) {
        this.daoPlanRut = daoPlanRut;
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
