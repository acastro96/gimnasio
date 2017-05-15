/*
 * Proyecto Gimnasio Virtual. 
 * Universidad Simón Bolívar - Barranquilla / Colombia.
 * Desarrollado por Ing. Alberto Castro Maestre
 */
package Bean;

import Bo.PlanTrabajoBO;
import Bo.PlanTrabajoImplBO;
import Pojo.GimPlanTrabajo;
import Pojo.GimRutina;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Alberto Castro
 */

@ManagedBean (name = "planTrabajoBean")
@ViewScoped
public class PlanTrabajoBean implements Serializable{

    //Informacion que va a insertarse en la tabla de Planes de Trabajo
    private String descripcion;
    private String nombre;
    private int tipoEjercicio;
    private String estado;
    private String codigo;
    private PlanTrabajoBO planTrabajoBO;
    private int tipo;
    private List<GimPlanTrabajo> lista;
    private BigDecimal id;
    
    @ManagedProperty(value = "#{loginBean}")
    private LoginBean loginBean;    
    
    //Lista de rutinas que pertenecen al plan de trabajo
    private List<GimRutina> listaRutinas;
    
    //Rutina que va a ser insertada en el plan de trabajo
    private String nombreRutina;
    private String codigoRutina;
    private String parametroBusqueda;
    private String descripcionRutina;
    private GimRutina rutina;
    
    //Lista de Parametros
    private Map<Integer, String> parTipoPlan;
    
    //Campos adicionales con los que se manejan las validaciones y los mensajes
    private boolean status = true;
    private int codeMensaje;
    private String mensaje;
    private boolean detalle = false;
    
    @PostConstruct
    public void init() {
        setPlanTrabajoBO(new PlanTrabajoImplBO());
        try {
            getPlanTrabajoBO().llenarParametros(this);
        } catch (Exception e) {
            setCodeMensaje(4);
            setMensaje("Ha ocurrido un error interno, comuniquese con el administrador");
        }
    }
    
    public void ingresarPlanTrabajo() {
        try {
            getPlanTrabajoBO().agregarPlanTrabajo(this);
        } catch (Exception e) {
            setCodeMensaje(4);
            setMensaje("Ha ocurrido un error interno, comuniquese con el administrador");
        }
        mostrarAlerta();
    }
    
    public void actualizarPlanTrabajo() {
        try {
            getPlanTrabajoBO().actualizarPlanTrabajo(this);
        } catch (Exception e) {
            setCodeMensaje(4);
            setMensaje("Ha ocurrido un error interno, comuniquese con el administrador");
        }
        mostrarAlerta();
    }
    
    public void consultarPlanTrabajo(int tipo) {
        try {
            setTipo(tipo);
            setLista(llenarLista());
        } catch (Exception e) {
            setCodeMensaje(4);
            setMensaje("Ha ocurrido un error interno, comuniquese con el administrador");
        }
        mostrarAlerta();
    }
    
    public void mostrarPlanTrabajo(BigDecimal id){
        try {
            setId(id);
            getPlanTrabajoBO().mostrarPlanTrabajo(this);
        } catch (Exception e) {
            setCodeMensaje(4);
            setMensaje("Ha ocurrido un error interno, comuniquese con el administrador");
        }
        mostrarAlerta();
        
    }
    
    public List<GimPlanTrabajo> llenarLista() throws Exception{
        return getPlanTrabajoBO().listarPlanTrabajo(this);
    }
    
    public void agregarRutina(){
        try {
            getPlanTrabajoBO().agregarPlanRut(this);
        } catch (Exception e) {
            setCodeMensaje(4);
            setMensaje("Ha ocurrido un error interno, comuniquese con el administrador");
        }
    }
    
    public void consultarRutina(){
        try {
            getPlanTrabajoBO().consultarRutina(this);
        } catch (Exception e) {
            setCodeMensaje(4);
            setMensaje("Ha ocurrido un error interno, comuniquese con el administrador");
        }
    }
    
    public void mostrarAlerta() {
        if (getCodeMensaje() == 1) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", getMensaje()));
        }
        if (getCodeMensaje() == 2) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Alerta", getMensaje()));
        }
        if (getCodeMensaje() == 3) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", getMensaje()));
        }
        if (getCodeMensaje() == 4) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error Fatal", getMensaje()));
        }
    }

    /**
     * @return the tipo
     */
    public int getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
    
    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the tipoEjercicio
     */
    public int getTipoEjercicio() {
        return tipoEjercicio;
    }

    /**
     * @param tipoEjercicio the tipoEjercicio to set
     */
    public void setTipoEjercicio(int tipoEjercicio) {
        this.tipoEjercicio = tipoEjercicio;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the planTrabajoBO
     */
    public PlanTrabajoBO getPlanTrabajoBO() {
        return planTrabajoBO;
    }

    /**
     * @param planTrabajoBO the planTrabajoBO to set
     */
    public void setPlanTrabajoBO(PlanTrabajoBO planTrabajoBO) {
        this.planTrabajoBO = planTrabajoBO;
    }

    /**
     * @return the listaRutinas
     */
    public List<GimRutina> getListaRutinas() {
        return listaRutinas;
    }

    /**
     * @param listaRutinas the listaRutinas to set
     */
    public void setListaRutinas(List<GimRutina> listaRutinas) {
        this.listaRutinas = listaRutinas;
    }

    /**
     * @return the status
     */
    public boolean isStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

    /**
     * @return the codeMensaje
     */
    public int getCodeMensaje() {
        return codeMensaje;
    }

    /**
     * @param codeMensaje the codeMensaje to set
     */
    public void setCodeMensaje(int codeMensaje) {
        this.codeMensaje = codeMensaje;
    }

    /**
     * @return the mensaje
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * @param mensaje the mensaje to set
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    /**
     * @return the loginBean
     */
    public LoginBean getLoginBean() {
        return loginBean;
    }

    /**
     * @param loginBean the loginBean to set
     */
    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }

    /**
     * @return the parametroBusqueda
     */
    public String getParametroBusqueda() {
        return parametroBusqueda;
    }

    /**
     * @param parametroBusqueda the parametroBusqueda to set
     */
    public void setParametroBusqueda(String parametroBusqueda) {
        this.parametroBusqueda = parametroBusqueda;
    }

    /**
     * @return the nombreRutina
     */
    public String getNombreRutina() {
        return nombreRutina;
    }

    /**
     * @param nombreRutina the nombreRutina to set
     */
    public void setNombreRutina(String nombreRutina) {
        this.nombreRutina = nombreRutina;
    }
    
    /**
     * @return the lista
     */
    public List<GimPlanTrabajo> getLista() {
        return lista;
    }

    /**
     * @param lista the lista to set
     */
    public void setLista(List<GimPlanTrabajo> lista) {
        this.lista = lista;
    }
    
    /**
     * @return the id
     */
    public BigDecimal getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(BigDecimal id) {
        this.id = id;
    }
    
    /**
     * @return the detalle
     */
    public boolean isDetalle() {
        return detalle;
    }

    /**
     * @param detalle the detalle to set
     */
    public void setDetalle(boolean detalle) {
        this.detalle = detalle;
    }

    /**
     * @return the parTipoPlan
     */
    public Map<Integer, String> getParTipoPlan() {
        return parTipoPlan;
    }

    /**
     * @param parTipoPlan the parTipoPlan to set
     */
    public void setParTipoPlan(Map<Integer, String> parTipoPlan) {
        this.parTipoPlan = parTipoPlan;
    }

    /**
     * @return the codigoRutina
     */
    public String getCodigoRutina() {
        return codigoRutina;
    }

    /**
     * @param codigoRutina the codigoRutina to set
     */
    public void setCodigoRutina(String codigoRutina) {
        this.codigoRutina = codigoRutina;
    }

    /**
     * @return the descripcionRutina
     */
    public String getDescripcionRutina() {
        return descripcionRutina;
    }

    /**
     * @param descripcionRutina the descripcionRutina to set
     */
    public void setDescripcionRutina(String descripcionRutina) {
        this.descripcionRutina = descripcionRutina;
    }

    /**
     * @return the rutina
     */
    public GimRutina getRutina() {
        return rutina;
    }

    /**
     * @param rutina the rutina to set
     */
    public void setRutina(GimRutina rutina) {
        this.rutina = rutina;
    }
    
}
