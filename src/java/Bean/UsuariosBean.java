/*
 * Proyecto Gimnasio Virtual. 
 * Universidad Simón Bolívar - Barranquilla / Colombia.
 * Desarrollado por Ing. Alberto Castro Maestre
 */
package Bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Alberto Castro
 */

@ManagedBean (name = "usuariosBean")
@ViewScoped
public class UsuariosBean {
    
    private int codeMensaje;
    private String mensaje;
    
}
