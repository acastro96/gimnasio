/*
 * Proyecto Gimnasio Virtual. 
 * Universidad Simón Bolívar - Barranquilla / Colombia.
 * Desarrollado por Ing. Alberto Castro Maestre
 */
package Bean;

import Bo.LoginBO;
import Bo.LoginImplBO;
import Util.Modulo;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Alberto Castro
 */

@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {
    
    private int idusuario;
    private String usuario;
    private String password;
    private String url;
    private LoginBO loginImpl;
    private String mensaje;
    private List<String> listRecursos;
    private List<Modulo> listModulos;
    
    @PostConstruct
    public void LoginBean(){
        setLoginImpl(new LoginImplBO());
    }
    
    public String login(){
        
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            getLoginImpl().logIn(this);
            
            if(this.getUrl().equalsIgnoreCase("")){
                FacesMessage facesMessage = new FacesMessage(getMensaje());
                facesContext.addMessage(null, facesMessage);
                return "";
            }
            
            setListModulos(getLoginImpl().listarModulos(this));
            setListRecursos(getLoginImpl().listarRecursos(this));
            FacesMessage facesMessage = new FacesMessage(getMensaje());
            facesContext.addMessage(null, facesMessage);
            return getUrl();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @return the idusuario
     */
    public int getIdusuario() {
        return idusuario;
    }

    /**
     * @param idusuario the idusuario to set
     */
    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    /**
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the loginImpl
     */
    public LoginBO getLoginImpl() {
        return loginImpl;
    }

    /**
     * @param loginImpl the loginImpl to set
     */
    public void setLoginImpl(LoginBO loginImpl) {
        this.loginImpl = loginImpl;
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
     * @return the listRecursos
     */
    public List<String> getListRecursos() {
        return listRecursos;
    }

    /**
     * @param listRecursos the listRecursos to set
     */
    public void setListRecursos(List<String> listRecursos) {
        this.listRecursos = listRecursos;
    }

    /**
     * @return the listModulos
     */
    public List<Modulo> getListModulos() {
        return listModulos;
    }

    /**
     * @param listModulos the listModulos to set
     */
    public void setListModulos(List<Modulo> listModulos) {
        this.listModulos = listModulos;
    }
}
