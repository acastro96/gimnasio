/*
 * Proyecto Gimnasio Virtual. 
 * Universidad Simón Bolívar - Barranquilla / Colombia.
 * Desarrollado por Ing. Alberto Castro Maestre
 */
package Bean;

import Bo.RutinasBO;
import Bo.RutinasImplBO;
import Pojo.GimRutina;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Alberto Castro
 */
@ManagedBean(name = "rutinasBean")
@ViewScoped
public class RutinasBean implements Serializable {
    
    @ManagedProperty(value = "#{loginBean}")
    private LoginBean loginBean;

    //Esta es la informacion que se va a agregar en la tabla rutina
    private String nombre;
    private String codigo;
    private String descripcion;
    private int repeticiones;
    private int calorias;

    //Información del video
    private UploadedFile video;
    private InputStream archivo;

    //Campos adicionales con los que se manejan las validaciones y los mensajes
    private boolean status = true;
    private int codeMensaje;
    private String mensaje;
    private boolean detalle = false;
    private int tipo;
    private BigDecimal id;
    private List<GimRutina> listRutinas;

    private RutinasBO rutinasBO;

    @PostConstruct
    public void init() {
        setRutinasBO(new RutinasImplBO());
    }

    public void ingresarRutina() {
        try {
            getRutinasBO().agregarRutina(this);
        } catch (Exception e) {
            setCodeMensaje(4);
            setMensaje("Ha ocurrido un error interno, comuniquese con el administrador");
        }
        mostrarAlerta();
    }

    public void consultarRutina(int tipo) {
        try {
            setTipo(tipo);
            setListRutinas(listaRutinas());
        } catch (Exception e) {
            setCodeMensaje(4);
            setMensaje("Ha ocurrido un error interno, comuniquese con el administrador");
        }
        mostrarAlerta();
    }
    
    public List<GimRutina> listaRutinas(){
        try {
            return getRutinasBO().listaRutinas(this);
        } catch (Exception e) {
            setCodeMensaje(4);
            setMensaje("Ha ocurrido un error interno, comuniquese con el administrador");
        }
        return null;
        
    }

    public void actualizarRutina() {
        try {
            getRutinasBO().actualizarRutina(this);
        } catch (Exception e) {
            setCodeMensaje(4);
            setMensaje("Ha ocurrido un error interno, comuniquese con el administrador");
        }
        mostrarAlerta();
    }
    
    public void upload() throws IOException {
        
        if (getVideo() != null) {
            setArchivo(getVideo().getInputstream());
            ingresarRutina();
        }
        else
        {
            setCodeMensaje(4);
            setMensaje("Error al cargar el archivo");
        }
        
    }
    
    public void mostrarRutina(BigDecimal id){
        try {
            setId(id);
            getRutinasBO().mostrarRutina(this);
            setDetalle(true);
        } catch (Exception e) {
            setCodeMensaje(4);
            setMensaje("Ha ocurrido un error interno, comuniquese con el administrador");
        }
        mostrarAlerta();
    }
    
    public void mostrarVideo()
    {
        try {
            getRutinasBO().mostrarVideo(this);
        } catch (Exception e) {
            setCodeMensaje(4);
            setMensaje(e.getMessage());
        }
        mostrarAlerta();
    }
    
    public void mostrarVideolista(String nombre)
    {
        try {
            setNombre(nombre);
            getRutinasBO().mostrarVideo(this);
        } catch (Exception e) {
            setCodeMensaje(4);
            setMensaje(e.getMessage());
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
     * @return the repeticiones
     */
    public int getRepeticiones() {
        return repeticiones;
    }

    /**
     * @param repeticiones the repeticiones to set
     */
    public void setRepeticiones(int repeticiones) {
        this.repeticiones = repeticiones;
    }

    /**
     * @return the calorias
     */
    public int getCalorias() {
        return calorias;
    }

    /**
     * @param calorias the calorias to set
     */
    public void setCalorias(int calorias) {
        this.calorias = calorias;
    }

    /**
     * @return the video
     */
    public UploadedFile getVideo() {
        return video;
    }

    /**
     * @param video the video to set
     */
    public void setVideo(UploadedFile video) {
        this.video = video;
    }

    /**
     * @return the archivo
     */
    public InputStream getArchivo() {
        return archivo;
    }

    /**
     * @param archivo the archivo to set
     */
    public void setArchivo(InputStream archivo) {
        this.archivo = archivo;
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
     * @return the rutinasBO
     */
    public RutinasBO getRutinasBO() {
        return rutinasBO;
    }

    /**
     * @param rutinasBO the rutinasBO to set
     */
    public void setRutinasBO(RutinasBO rutinasBO) {
        this.rutinasBO = rutinasBO;
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
     * @return the listRutinas
     */
    public List<GimRutina> getListRutinas() {
        return listRutinas;
    }

    /**
     * @param listRutinas the listRutinas to set
     */
    public void setListRutinas(List<GimRutina> listRutinas) {
        this.listRutinas = listRutinas;
    }

}
