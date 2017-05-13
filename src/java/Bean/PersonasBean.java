/*
 * Proyecto Gimnasio Virtual. 
 * Universidad Simón Bolívar - Barranquilla / Colombia.
 * Desarrollado por Ing. Alberto Castro Maestre
 */
package Bean;

import Bo.PersonasImplBO;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Alberto Castro
 */

@ManagedBean (name = "personasBean")
@ViewScoped
public class PersonasBean {
    
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
    
    //Campos adicionales con los que se manejan las validaciones y los mensajes
    private boolean status = true;
    private int codeMensaje;
    private String mensaje;
    
    private PersonasImplBO personasImplBO;
    
    @PostConstruct
    public void init()
    {
        setPersonasImplBO(new PersonasImplBO());
    }
    
    public void ingresarPersona()
    {
        try {
            getPersonasImplBO().agregarPersona(this);
        } catch (Exception e) {
            
            setCodeMensaje(4);
            setMensaje("Ha ocurrido un error interno, por favor comuniquese con el Administrador");
        }
        mostrarAlerta();
    }
    public void actualizarPersona()
    {
        try {
            getPersonasImplBO().actualizarPersona(this);
        } catch (Exception e) {
            
            setCodeMensaje(4);
            setMensaje("Ha ocurrido un error interno, por favor comuniquese con el Administrador");
        }
        mostrarAlerta();
    }
    public void eliminarPersona()
    {
        try {
            getPersonasImplBO().eliminarPersona(this);
        } catch (Exception e) {
            
            setCodeMensaje(4);
            setMensaje("Ha ocurrido un error interno, por favor comuniquese con el Administrador");
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
     * @return the personasImplBO
     */
    public PersonasImplBO getPersonasImplBO() {
        return personasImplBO;
    }

    /**
     * @param personasImplBO the personasImplBO to set
     */
    public void setPersonasImplBO(PersonasImplBO personasImplBO) {
        this.personasImplBO = personasImplBO;
    }
    
}
