package Pojo;
// Generated 18/05/2017 12:10:35 AM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * GimRutina generated by hbm2java
 */
public class GimRutina  implements java.io.Serializable {


     private BigDecimal rutId;
     private String rutDescripcion;
     private String rutNombre;
     private BigDecimal rutRepeticiones;
     private BigDecimal rutCalorias;
     private String rutCodigo;
     private Set gimPlanRuts = new HashSet(0);
     private Set gimVideos = new HashSet(0);
     private Set gimRutObjs = new HashSet(0);

    public GimRutina() {
    }

	
    public GimRutina(BigDecimal rutId, String rutDescripcion, String rutNombre, String rutCodigo) {
        this.rutId = rutId;
        this.rutDescripcion = rutDescripcion;
        this.rutNombre = rutNombre;
        this.rutCodigo = rutCodigo;
    }
    public GimRutina(BigDecimal rutId, String rutDescripcion, String rutNombre, BigDecimal rutRepeticiones, BigDecimal rutCalorias, String rutCodigo) {
       this.rutId = rutId;
       this.rutDescripcion = rutDescripcion;
       this.rutNombre = rutNombre;
       this.rutRepeticiones = rutRepeticiones;
       this.rutCalorias = rutCalorias;
       this.rutCodigo = rutCodigo;
    }
   
    public BigDecimal getRutId() {
        return this.rutId;
    }
    
    public void setRutId(BigDecimal rutId) {
        this.rutId = rutId;
    }
    public String getRutDescripcion() {
        return this.rutDescripcion;
    }
    
    public void setRutDescripcion(String rutDescripcion) {
        this.rutDescripcion = rutDescripcion;
    }
    public String getRutNombre() {
        return this.rutNombre;
    }
    
    public void setRutNombre(String rutNombre) {
        this.rutNombre = rutNombre;
    }
    public BigDecimal getRutRepeticiones() {
        return this.rutRepeticiones;
    }
    
    public void setRutRepeticiones(BigDecimal rutRepeticiones) {
        this.rutRepeticiones = rutRepeticiones;
    }
    public BigDecimal getRutCalorias() {
        return this.rutCalorias;
    }
    
    public void setRutCalorias(BigDecimal rutCalorias) {
        this.rutCalorias = rutCalorias;
    }
    public String getRutCodigo() {
        return this.rutCodigo;
    }
    
    public void setRutCodigo(String rutCodigo) {
        this.rutCodigo = rutCodigo;
    }
    public Set getGimPlanRuts() {
        return this.gimPlanRuts;
    }
    
    public void setGimPlanRuts(Set gimPlanRuts) {
        this.gimPlanRuts = gimPlanRuts;
    }
    public Set getGimVideos() {
        return this.gimVideos;
    }
    
    public void setGimVideos(Set gimVideos) {
        this.gimVideos = gimVideos;
    }
    public Set getGimRutObjs() {
        return this.gimRutObjs;
    }
    
    public void setGimRutObjs(Set gimRutObjs) {
        this.gimRutObjs = gimRutObjs;
    }




}


