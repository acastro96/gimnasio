package Pojo;
// Generated 28/04/2017 10:08:51 PM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;

/**
 * GimDireccion generated by hbm2java
 */
public class GimDireccion  implements java.io.Serializable {


     private BigDecimal dirId;
     private GimPersona gimPersona;
     private String dirDesc;
     private Date dirFechaproceso;
     private BigDecimal dirEstado;

    public GimDireccion() {
    }

	
    public GimDireccion(BigDecimal dirId, GimPersona gimPersona, BigDecimal dirEstado) {
        this.dirId = dirId;
        this.gimPersona = gimPersona;
        this.dirEstado = dirEstado;
    }
    public GimDireccion(BigDecimal dirId, GimPersona gimPersona, String dirDesc, Date dirFechaproceso, BigDecimal dirEstado) {
       this.dirId = dirId;
       this.gimPersona = gimPersona;
       this.dirDesc = dirDesc;
       this.dirFechaproceso = dirFechaproceso;
       this.dirEstado = dirEstado;
    }
   
    public BigDecimal getDirId() {
        return this.dirId;
    }
    
    public void setDirId(BigDecimal dirId) {
        this.dirId = dirId;
    }
    public GimPersona getGimPersona() {
        return this.gimPersona;
    }
    
    public void setGimPersona(GimPersona gimPersona) {
        this.gimPersona = gimPersona;
    }
    public String getDirDesc() {
        return this.dirDesc;
    }
    
    public void setDirDesc(String dirDesc) {
        this.dirDesc = dirDesc;
    }
    public Date getDirFechaproceso() {
        return this.dirFechaproceso;
    }
    
    public void setDirFechaproceso(Date dirFechaproceso) {
        this.dirFechaproceso = dirFechaproceso;
    }
    public BigDecimal getDirEstado() {
        return this.dirEstado;
    }
    
    public void setDirEstado(BigDecimal dirEstado) {
        this.dirEstado = dirEstado;
    }




}

