/*
 * Proyecto Gimnasio Virtual. 
 * Universidad Simón Bolívar - Barranquilla / Colombia.
 * Desarrollado por Ing. Alberto Castro Maestre
 */
package Bean;

import Bo.InstructoresBO;
import Bo.InstructoresImplBO;
import Pojo.GimInstructor;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Alberto Castro
 */


@ManagedBean (name = "instructoresBean")
@ViewScoped
public class InstructoresBean {
    
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
    private List<GimInstructor> listInstructor;
    private BigDecimal id;
    private int tipo;
    
    //Información especifica: Esta es la información que se va a agregar en la tabla Instructores como tal
    private Date fechaIngresoGim;
    private int nivelEstudio;
    private int especialidad;
    private int nivelNutricion;
    private String codigo;
    private boolean detalle = false;
    
    //Información del Usuario del instructor
    private String usuNom;
    private String usuPass;
    private String usuConpass;
    
    //Listas de Parametros que se manejan en el Bean
    private Map<Integer, String> parTipoDocumento;
    private Map<Integer, String> parLugarNacimiento;
    private Map<Integer, String> parGrupoSanguineo;
    private Map<Integer, String> parSexo;
    private Map<Integer, String> parEspecialidad;
    private Map<Integer, String> parNivelEstudio;
    private Map<Integer, String> parNivelNutricion;
    
    //Campos adicionales con los que se manejan las validaciones y los mensajes
    private boolean status = true;
    private int codeMensaje;
    private String mensaje;
    
    private InstructoresBO instructoresBO;
    
    @PostConstruct
    public void init()
    {
        setInstructoresBO(new InstructoresImplBO());
        try {
            getInstructoresBO().llenarParametros(this);
        } catch (Exception e) {
            setCodeMensaje(4);
            setMensaje("Ha ocurrido un error interno, comuniquese con el administrador");
        }
    }
    
    public void ingresarInstructor()
    {   
        try {
           getInstructoresBO().agregarInstructor(this);
        } catch (Exception e) {
            
            setCodeMensaje(4);
            setMensaje("Ha ocurrido un error interno, comuniquese con el administrador");
        }
        mostrarAlerta();
    }
    
    public void actualizarInstructor()
    {   
        try {
           getInstructoresBO().actualizarInstructor(this);
        } catch (Exception e) {
            
            setCodeMensaje(4);
            setMensaje("Ha ocurrido un error interno, comuniquese con el administrador");
        }
        mostrarAlerta();
    }
    
    public void consultarInstructor(int tipo)
    {   
        try {
            setTipo(tipo);
            setListInstructor(llenarLista());
        } catch (Exception e) {
            setCodeMensaje(4);
            setMensaje("Ha ocurrido un error interno, comuniquese con el administrador");
        }
        mostrarAlerta();
    }
    
    public List<GimInstructor> llenarLista() throws Exception{
        return getInstructoresBO().listaInstructores(this);
    }
    
    public void mostrarInstructor(BigDecimal id){
        try {
            setId(id);
            getInstructoresBO().consultarInstructor(this);
            setDetalle(true);
        } catch (Exception e) {
            setCodeMensaje(4);
            setMensaje("Ha ocurrido un error interno, comuniquese con el administrador");
        }
        mostrarAlerta();
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
    
    public String getEspecialidad(int par){
        return parEspecialidad.get(par);
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
     * @return the fechaIngresoGim
     */
    public Date getFechaIngresoGim() {
        return fechaIngresoGim;
    }

    /**
     * @param fechaIngresoGim the fechaIngresoGim to set
     */
    public void setFechaIngresoGim(Date fechaIngresoGim) {
        this.fechaIngresoGim = fechaIngresoGim;
    }

    /**
     * @return the nivelEstudio
     */
    public int getNivelEstudio() {
        return nivelEstudio;
    }

    /**
     * @param nivelEstudio the nivelEstudio to set
     */
    public void setNivelEstudio(int nivelEstudio) {
        this.nivelEstudio = nivelEstudio;
    }

    /**
     * @return the especialidad
     */
    public int getEspecialidad() {
        return especialidad;
    }

    /**
     * @param especialidad the especialidad to set
     */
    public void setEspecialidad(int especialidad) {
        this.especialidad = especialidad;
    }

    /**
     * @return the nivelNutricion
     */
    public int getNivelNutricion() {
        return nivelNutricion;
    }

    /**
     * @param nivelNutricion the nivelNutricion to set
     */
    public void setNivelNutricion(int nivelNutricion) {
        this.nivelNutricion = nivelNutricion;
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
     * @return the instructoresBO
     */
    public InstructoresBO getInstructoresBO() {
        return instructoresBO;
    }

    /**
     * @param instructoresBO the instructoresBO to set
     */
    public void setInstructoresBO(InstructoresBO instructoresBO) {
        this.instructoresBO = instructoresBO;
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
     * @return the listInstructor
     */
    public List<GimInstructor> getListInstructor() {
        return listInstructor;
    }

    /**
     * @param listInstructor the listInstructor to set
     */
    public void setListInstructor(List<GimInstructor> listInstructor) {
        this.listInstructor = listInstructor;
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
     * @return the parEspecialidad
     */
    public Map<Integer, String> getParEspecialidad() {
        return parEspecialidad;
    }

    /**
     * @param parEspecialidad the parEspecialidad to set
     */
    public void setParEspecialidad(Map<Integer, String> parEspecialidad) {
        this.parEspecialidad = parEspecialidad;
    }

    /**
     * @return the parNivelEstudio
     */
    public Map<Integer, String> getParNivelEstudio() {
        return parNivelEstudio;
    }

    /**
     * @param parNivelEstudio the parNivelEstudio to set
     */
    public void setParNivelEstudio(Map<Integer, String> parNivelEstudio) {
        this.parNivelEstudio = parNivelEstudio;
    }

    /**
     * @return the parNivelNutricion
     */
    public Map<Integer, String> getParNivelNutricion() {
        return parNivelNutricion;
    }

    /**
     * @param parNivelNutricion the parNivelNutricion to set
     */
    public void setParNivelNutricion(Map<Integer, String> parNivelNutricion) {
        this.parNivelNutricion = parNivelNutricion;
    }
    
    
}
