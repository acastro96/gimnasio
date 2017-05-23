package Pojo;
// Generated 18/05/2017 12:10:35 AM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * GimUsuario generated by hbm2java
 */
public class GimUsuario  implements java.io.Serializable {


     private BigDecimal usuId;
     private GimPerfil gimPerfil;
     private GimPersona gimPersona;
     private String usuNombre;
     private String usuPassword;
     private Date usuFechainicial;
     private Date usuFechafinal;
     private Date usuFechaproceso;
     private BigDecimal usuEstado;
     private Set gimPlanTrabajos = new HashSet(0);

    public GimUsuario() {
    }

	
    public GimUsuario(BigDecimal usuId, GimPerfil gimPerfil, GimPersona gimPersona, String usuNombre, String usuPassword, BigDecimal usuEstado) {
        this.usuId = usuId;
        this.gimPerfil = gimPerfil;
        this.gimPersona = gimPersona;
        this.usuNombre = usuNombre;
        this.usuPassword = usuPassword;
        this.usuEstado = usuEstado;
    }
    public GimUsuario(BigDecimal usuId, GimPerfil gimPerfil, GimPersona gimPersona, String usuNombre, String usuPassword, Date usuFechainicial, Date usuFechafinal, Date usuFechaproceso, BigDecimal usuEstado) {
       this.usuId = usuId;
       this.gimPerfil = gimPerfil;
       this.gimPersona = gimPersona;
       this.usuNombre = usuNombre;
       this.usuPassword = usuPassword;
       this.usuFechainicial = usuFechainicial;
       this.usuFechafinal = usuFechafinal;
       this.usuFechaproceso = usuFechaproceso;
       this.usuEstado = usuEstado;
    }
   
    public BigDecimal getUsuId() {
        return this.usuId;
    }
    
    public void setUsuId(BigDecimal usuId) {
        this.usuId = usuId;
    }
    public GimPerfil getGimPerfil() {
        return this.gimPerfil;
    }
    
    public void setGimPerfil(GimPerfil gimPerfil) {
        this.gimPerfil = gimPerfil;
    }
    public GimPersona getGimPersona() {
        return this.gimPersona;
    }
    
    public void setGimPersona(GimPersona gimPersona) {
        this.gimPersona = gimPersona;
    }
    public String getUsuNombre() {
        return this.usuNombre;
    }
    
    public void setUsuNombre(String usuNombre) {
        this.usuNombre = usuNombre;
    }
    public String getUsuPassword() {
        return this.usuPassword;
    }
    
    public void setUsuPassword(String usuPassword) {
        this.usuPassword = usuPassword;
    }
    public Date getUsuFechainicial() {
        return this.usuFechainicial;
    }
    
    public void setUsuFechainicial(Date usuFechainicial) {
        this.usuFechainicial = usuFechainicial;
    }
    public Date getUsuFechafinal() {
        return this.usuFechafinal;
    }
    
    public void setUsuFechafinal(Date usuFechafinal) {
        this.usuFechafinal = usuFechafinal;
    }
    public Date getUsuFechaproceso() {
        return this.usuFechaproceso;
    }
    
    public void setUsuFechaproceso(Date usuFechaproceso) {
        this.usuFechaproceso = usuFechaproceso;
    }
    public BigDecimal getUsuEstado() {
        return this.usuEstado;
    }
    
    public void setUsuEstado(BigDecimal usuEstado) {
        this.usuEstado = usuEstado;
    }
    public Set getGimPlanTrabajos() {
        return this.gimPlanTrabajos;
    }
    
    public void setGimPlanTrabajos(Set gimPlanTrabajos) {
        this.gimPlanTrabajos = gimPlanTrabajos;
    }




}


