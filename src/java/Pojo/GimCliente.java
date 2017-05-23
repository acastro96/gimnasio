package Pojo;
// Generated 18/05/2017 12:10:35 AM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * GimCliente generated by hbm2java
 */
public class GimCliente  implements java.io.Serializable {


     private BigDecimal cliId;
     private GimPersona gimPersona;
     private BigDecimal cliTipoplanTrabajo;
     private String cliDescripcion;
     private String cliCodigo;
     private BigDecimal cliEstado;
     private Date cliFechaproceso;
     private Set gimPlanClientes = new HashSet(0);
     private Set gimInsClis = new HashSet(0);

    public GimCliente() {
    }

	
    public GimCliente(BigDecimal cliId, GimPersona gimPersona, BigDecimal cliTipoplanTrabajo, String cliCodigo, BigDecimal cliEstado) {
        this.cliId = cliId;
        this.gimPersona = gimPersona;
        this.cliTipoplanTrabajo = cliTipoplanTrabajo;
        this.cliCodigo = cliCodigo;
        this.cliEstado = cliEstado;
    }
    public GimCliente(BigDecimal cliId, GimPersona gimPersona, BigDecimal cliTipoplanTrabajo, String cliDescripcion, String cliCodigo, BigDecimal cliEstado, Date cliFechaproceso) {
       this.cliId = cliId;
       this.gimPersona = gimPersona;
       this.cliTipoplanTrabajo = cliTipoplanTrabajo;
       this.cliDescripcion = cliDescripcion;
       this.cliCodigo = cliCodigo;
       this.cliEstado = cliEstado;
       this.cliFechaproceso = cliFechaproceso;
    }
   
    public BigDecimal getCliId() {
        return this.cliId;
    }
    
    public void setCliId(BigDecimal cliId) {
        this.cliId = cliId;
    }
    public GimPersona getGimPersona() {
        return this.gimPersona;
    }
    
    public void setGimPersona(GimPersona gimPersona) {
        this.gimPersona = gimPersona;
    }
    public BigDecimal getCliTipoplanTrabajo() {
        return this.cliTipoplanTrabajo;
    }
    
    public void setCliTipoplanTrabajo(BigDecimal cliTipoplanTrabajo) {
        this.cliTipoplanTrabajo = cliTipoplanTrabajo;
    }
    public String getCliDescripcion() {
        return this.cliDescripcion;
    }
    
    public void setCliDescripcion(String cliDescripcion) {
        this.cliDescripcion = cliDescripcion;
    }
    public String getCliCodigo() {
        return this.cliCodigo;
    }
    
    public void setCliCodigo(String cliCodigo) {
        this.cliCodigo = cliCodigo;
    }
    public BigDecimal getCliEstado() {
        return this.cliEstado;
    }
    
    public void setCliEstado(BigDecimal cliEstado) {
        this.cliEstado = cliEstado;
    }
    public Date getCliFechaproceso() {
        return this.cliFechaproceso;
    }
    
    public void setCliFechaproceso(Date cliFechaproceso) {
        this.cliFechaproceso = cliFechaproceso;
    }
    public Set getGimPlanClientes() {
        return this.gimPlanClientes;
    }
    
    public void setGimPlanClientes(Set gimPlanClientes) {
        this.gimPlanClientes = gimPlanClientes;
    }
    public Set getGimInsClis() {
        return this.gimInsClis;
    }
    
    public void setGimInsClis(Set gimInsClis) {
        this.gimInsClis = gimInsClis;
    }




}


