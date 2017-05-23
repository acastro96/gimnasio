/*
 * Proyecto Gimnasio Virtual. 
 * Universidad Simón Bolívar - Barranquilla / Colombia.
 * Desarrollado por Ing. Alberto Castro Maestre
 */
package Bean;

import Bo.ClienteBO;
import Bo.ClienteImplBO;
import Pojo.GimCliente;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Alberto Castro
 */
@ManagedBean(name = "clientesBean")
@ViewScoped
public class ClientesBean {

    //Informacion basica: Esta es la informacion que se va a agregar en la tabla persona
    private String nombre1;
    private String nombre2;
    private String apellido1;
    private String apellido2;
    private int tipoDocumento;
    private String numeroDocumento;
    private Date fechaNacimiento;
    private int lugarNacimiento;
    private int grupoSanguineo;
    private String rh;
    private int sexo;
    private String telefono;
    private String celular;
    private String direccion;
    private String email;

    //Información del Usuario
    private String usuNom;
    private String usuPass;
    private String usuConpass;

    //Campos adicionales con los que se manejan las validaciones y los mensajes
    private boolean status = true;
    private int codeMensaje;
    private String mensaje;
    private boolean encabezado = true;
    private boolean detalle = false;
    private boolean planAsignar = false;
    private int tipo;
    private BigDecimal id;
    private List<GimCliente> listClientes;

    //Listas de Parametros
    private Map<Integer, String> parTipoDocumento;
    private Map<Integer, String> parLugarNacimiento;
    private Map<Integer, String> parGrupoSanguineo;
    private Map<Integer, String> parSexo;
    private Map<Integer, String> parPreferencias;

    //Lista de Instructores para asignarlo al cliente
    private Map<BigDecimal, String> listInstructores;
    private BigDecimal idInstructor;

    //Listas de Planes de Trabajos para asignar y Plan de Trabajo escogido
    private Map<BigDecimal, String> planesAsignar;
    private BigDecimal idPlan;

    //Informacion que se va a insertar en cliente
    private String descripcion;
    private int tipoPlanPreferencia;
    private ClienteBO clienteBO;
    private String codigo;
    private String estado;

    @PostConstruct
    public void init() {
        setClienteBO(new ClienteImplBO());
        try {
            getClienteBO().llenarParametros(this);
        } catch (Exception e) {
            setCodeMensaje(4);
            setMensaje("Ha ocurrido un error interno, comuniquese con el administrador");
        }
    }

    public void ingresarCliente() {
        try {
            getClienteBO().agregarCliente(this);
        } catch (Exception e) {
            setCodeMensaje(4);
            setMensaje("Ha ocurrido un error interno, comuniquese con el administrador");
        }
        mostrarAlerta();
    }

    public void actualizarCliente() {
        try {
            getClienteBO().actualizarCliente(this);
        } catch (Exception e) {
            setCodeMensaje(4);
            setMensaje("Ha ocurrido un error interno, comuniquese con el administrador");
        }
        mostrarAlerta();
    }

    //Metodo que llama a la lista de clientes y asigna que tipo de busqueda voy a hacer (Codigo, Documento o Nombre)
    public void consultarCliente(int tipo) {
        try {
            setTipo(tipo);
            setListClientes(listaClientes());
        } catch (Exception e) {
            setCodeMensaje(4);
            setMensaje("Ha ocurrido un error interno, comuniquese con el administrador");
        }
        mostrarAlerta();
    }

    //Devolvemos una lista con la que vamos a llenar una tabla en la que se hacen las consultas
    public List<GimCliente> listaClientes() {
        try {
            return getClienteBO().listaClientes(this);
        } catch (Exception e) {
            setCodeMensaje(4);
            setMensaje("Ha ocurrido un error interno, comuniquese con el administrador");
        }
        return null;
    }

    public void mostrarCliente(BigDecimal idCliente) {
        try {
            setId(idCliente);
            getClienteBO().mostrarCliente(this);
            setDetalle(true);
            setEncabezado(false);
        } catch (Exception e) {
            setCodeMensaje(4);
            setMensaje("Ha ocurrido un error interno, comuniquese con el administrador");
        }
        mostrarAlerta();
    }

    public void cargarPlanesAsignar(BigDecimal idCliente) {
        try {
            setId(idCliente);
            getClienteBO().planesDisponibles(this);
        } catch (Exception e) {
            setCodeMensaje(4);
            setMensaje("Ha ocurrido un error interno, comuniquese con el administrador");
        }
    }

    public void mostrarAsignarPlan(BigDecimal idCliente) {
        cargarPlanesAsignar(idCliente);
        mostrarAlerta();
    }

    public void asignarPlan() {
        try {
            getClienteBO().asignarPlan(this);
        } catch (Exception e) {
            setCodeMensaje(4);
            setMensaje("Ha ocurrido un error interno, comuniquese con el administrador");
        }
        mostrarAlerta();
    }

    public String getTipoDocumento(int par) {
        return parTipoDocumento.get(par);
    }

    public String getTipoPreferencia(int par) {
        return parPreferencias.get(par);
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
     * @return the nombre1
     */
    public String getNombre1() {
        return nombre1;
    }

    /**
     * @param nombre1 the nombre1 to set
     */
    public void setNombre1(String nombre1) {
        this.nombre1 = nombre1;
    }

    /**
     * @return the nombre2
     */
    public String getNombre2() {
        return nombre2;
    }

    /**
     * @param nombre2 the nombre2 to set
     */
    public void setNombre2(String nombre2) {
        this.nombre2 = nombre2;
    }

    /**
     * @return the apellido1
     */
    public String getApellido1() {
        return apellido1;
    }

    /**
     * @param apellido1 the apellido1 to set
     */
    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    /**
     * @return the apellido2
     */
    public String getApellido2() {
        return apellido2;
    }

    /**
     * @param apellido2 the apellido2 to set
     */
    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    /**
     * @return the tipoDocumento
     */
    public int getTipoDocumento() {
        return tipoDocumento;
    }

    /**
     * @param tipoDocumento the tipoDocumento to set
     */
    public void setTipoDocumento(int tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    /**
     * @return the numeroDocumento
     */
    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    /**
     * @param numeroDocumento the numeroDocumento to set
     */
    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    /**
     * @return the fechaNacimiento
     */
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * @param fechaNacimiento the fechaNacimiento to set
     */
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * @return the lugarNacimiento
     */
    public int getLugarNacimiento() {
        return lugarNacimiento;
    }

    /**
     * @param lugarNacimiento the lugarNacimiento to set
     */
    public void setLugarNacimiento(int lugarNacimiento) {
        this.lugarNacimiento = lugarNacimiento;
    }

    /**
     * @return the grupoSanguineo
     */
    public int getGrupoSanguineo() {
        return grupoSanguineo;
    }

    /**
     * @param grupoSanguineo the grupoSanguineo to set
     */
    public void setGrupoSanguineo(int grupoSanguineo) {
        this.grupoSanguineo = grupoSanguineo;
    }

    /**
     * @return the rh
     */
    public String getRh() {
        return rh;
    }

    /**
     * @param rh the rh to set
     */
    public void setRh(String rh) {
        this.rh = rh;
    }

    /**
     * @return the sexo
     */
    public int getSexo() {
        return sexo;
    }

    /**
     * @param sexo the sexo to set
     */
    public void setSexo(int sexo) {
        this.sexo = sexo;
    }

    /**
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the celular
     */
    public String getCelular() {
        return celular;
    }

    /**
     * @param celular the celular to set
     */
    public void setCelular(String celular) {
        this.celular = celular;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the usuNom
     */
    public String getUsuNom() {
        return usuNom;
    }

    /**
     * @param usuNom the usuNom to set
     */
    public void setUsuNom(String usuNom) {
        this.usuNom = usuNom;
    }

    /**
     * @return the usuPass
     */
    public String getUsuPass() {
        return usuPass;
    }

    /**
     * @param usuPass the usuPass to set
     */
    public void setUsuPass(String usuPass) {
        this.usuPass = usuPass;
    }

    /**
     * @return the usuConpass
     */
    public String getUsuConpass() {
        return usuConpass;
    }

    /**
     * @param usuConpass the usuConpass to set
     */
    public void setUsuConpass(String usuConpass) {
        this.usuConpass = usuConpass;
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
     * @return the tipoPlanPreferencia
     */
    public int getTipoPlanPreferencia() {
        return tipoPlanPreferencia;
    }

    /**
     * @param tipoPlanPreferencia the tipoPlanPreferencia to set
     */
    public void setTipoPlanPreferencia(int tipoPlanPreferencia) {
        this.tipoPlanPreferencia = tipoPlanPreferencia;
    }

    /**
     * @return the clienteBO
     */
    public ClienteBO getClienteBO() {
        return clienteBO;
    }

    /**
     * @param clienteBO the clienteBO to set
     */
    public void setClienteBO(ClienteBO clienteBO) {
        this.clienteBO = clienteBO;
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
     * @return the parTipoDocumento
     */
    public Map<Integer, String> getParTipoDocumento() {
        return parTipoDocumento;
    }

    /**
     * @param parTipoDocumento the parTipoDocumento to set
     */
    public void setParTipoDocumento(Map<Integer, String> parTipoDocumento) {
        this.parTipoDocumento = parTipoDocumento;
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
     * @return the listaClientes
     */
    public List<GimCliente> getListClientes() {
        return listClientes;
    }

    /**
     * @param listaClientes the listaClientes to set
     */
    public void setListClientes(List<GimCliente> listaClientes) {
        this.listClientes = listaClientes;
    }

    /**
     * @return the parLugarNacimiento
     */
    public Map<Integer, String> getParLugarNacimiento() {
        return parLugarNacimiento;
    }

    /**
     * @param parLugarNacimiento the parLugarNacimiento to set
     */
    public void setParLugarNacimiento(Map<Integer, String> parLugarNacimiento) {
        this.parLugarNacimiento = parLugarNacimiento;
    }

    /**
     * @return the parGrupoSanguineo
     */
    public Map<Integer, String> getParGrupoSanguineo() {
        return parGrupoSanguineo;
    }

    /**
     * @param parGrupoSanguineo the parGrupoSanguineo to set
     */
    public void setParGrupoSanguineo(Map<Integer, String> parGrupoSanguineo) {
        this.parGrupoSanguineo = parGrupoSanguineo;
    }

    /**
     * @return the parSexo
     */
    public Map<Integer, String> getParSexo() {
        return parSexo;
    }

    /**
     * @param parSexo the parSexo to set
     */
    public void setParSexo(Map<Integer, String> parSexo) {
        this.parSexo = parSexo;
    }

    /**
     * @return the parPreferencias
     */
    public Map<Integer, String> getParPreferencias() {
        return parPreferencias;
    }

    /**
     * @param parPreferencias the parPreferencias to set
     */
    public void setParPreferencias(Map<Integer, String> parPreferencias) {
        this.parPreferencias = parPreferencias;
    }

    /**
     * @return the planesAsignar
     */
    public Map<BigDecimal, String> getPlanesAsignar() {
        return planesAsignar;
    }

    /**
     * @param planesAsignar the planesAsignar to set
     */
    public void setPlanesAsignar(Map<BigDecimal, String> planesAsignar) {
        this.planesAsignar = planesAsignar;
    }

    /**
     * @return the idPlan
     */
    public BigDecimal getIdPlan() {
        return idPlan;
    }

    /**
     * @param idPlan the idPlan to set
     */
    public void setIdPlan(BigDecimal idPlan) {
        this.idPlan = idPlan;
    }

    /**
     * @return the listInstructores
     */
    public Map<BigDecimal, String> getListInstructores() {
        return listInstructores;
    }

    /**
     * @param listInstructores the listInstructores to set
     */
    public void setListInstructores(Map<BigDecimal, String> listInstructores) {
        this.listInstructores = listInstructores;
    }

    /**
     * @return the idInstructor
     */
    public BigDecimal getIdInstructor() {
        return idInstructor;
    }

    /**
     * @param idInstructor the idInstructor to set
     */
    public void setIdInstructor(BigDecimal idInstructor) {
        this.idInstructor = idInstructor;
    }

    /**
     * @return the planAsignar
     */
    public boolean isPlanAsignar() {
        return planAsignar;
    }

    /**
     * @param planAsignar the planAsignar to set
     */
    public void setPlanAsignar(boolean planAsignar) {
        this.planAsignar = planAsignar;
    }

    /**
     * @return the encabezado
     */
    public boolean isEncabezado() {
        return encabezado;
    }

    /**
     * @param encabezado the encabezado to set
     */
    public void setEncabezado(boolean encabezado) {
        this.encabezado = encabezado;
    }
}
