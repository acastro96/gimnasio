package Pojo;
// Generated 16/05/2017 10:24:10 PM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * GimObjetos generated by hbm2java
 */
public class GimObjetos  implements java.io.Serializable {


     private BigDecimal objId;
     private String objCodigo;
     private String objNombre;
     private Date objFechaproceso;
     private Set gimRutObjs = new HashSet(0);

    public GimObjetos() {
    }

	
    public GimObjetos(BigDecimal objId, String objCodigo, String objNombre) {
        this.objId = objId;
        this.objCodigo = objCodigo;
        this.objNombre = objNombre;
    }
    public GimObjetos(BigDecimal objId, String objCodigo, String objNombre, Date objFechaproceso, Set gimRutObjs) {
       this.objId = objId;
       this.objCodigo = objCodigo;
       this.objNombre = objNombre;
       this.objFechaproceso = objFechaproceso;
       this.gimRutObjs = gimRutObjs;
    }
   
    public BigDecimal getObjId() {
        return this.objId;
    }
    
    public void setObjId(BigDecimal objId) {
        this.objId = objId;
    }
    public String getObjCodigo() {
        return this.objCodigo;
    }
    
    public void setObjCodigo(String objCodigo) {
        this.objCodigo = objCodigo;
    }
    public String getObjNombre() {
        return this.objNombre;
    }
    
    public void setObjNombre(String objNombre) {
        this.objNombre = objNombre;
    }
    public Date getObjFechaproceso() {
        return this.objFechaproceso;
    }
    
    public void setObjFechaproceso(Date objFechaproceso) {
        this.objFechaproceso = objFechaproceso;
    }
    public Set getGimRutObjs() {
        return this.gimRutObjs;
    }
    
    public void setGimRutObjs(Set gimRutObjs) {
        this.gimRutObjs = gimRutObjs;
    }




}


