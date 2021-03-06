package Pojo;
// Generated 18/05/2017 12:10:35 AM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * GimPerfil generated by hbm2java
 */
public class GimPerfil  implements java.io.Serializable {


     private BigDecimal perfId;
     private String perfNombre;
     private Set gimUsuarios = new HashSet(0);

    public GimPerfil() {
    }

	
    public GimPerfil(BigDecimal perfId, String perfNombre) {
        this.perfId = perfId;
        this.perfNombre = perfNombre;
    }
    public GimPerfil(BigDecimal perfId, String perfNombre, Set gimUsuarios) {
       this.perfId = perfId;
       this.perfNombre = perfNombre;
       this.gimUsuarios = gimUsuarios;
    }
   
    public BigDecimal getPerfId() {
        return this.perfId;
    }
    
    public void setPerfId(BigDecimal perfId) {
        this.perfId = perfId;
    }
    public String getPerfNombre() {
        return this.perfNombre;
    }
    
    public void setPerfNombre(String perfNombre) {
        this.perfNombre = perfNombre;
    }
    public Set getGimUsuarios() {
        return this.gimUsuarios;
    }
    
    public void setGimUsuarios(Set gimUsuarios) {
        this.gimUsuarios = gimUsuarios;
    }




}


