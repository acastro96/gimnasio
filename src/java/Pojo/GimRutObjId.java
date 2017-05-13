package Pojo;
// Generated 28/04/2017 10:08:51 PM by Hibernate Tools 4.3.1


import java.math.BigDecimal;

/**
 * GimRutObjId generated by hbm2java
 */
public class GimRutObjId  implements java.io.Serializable {


     private BigDecimal rutId;
     private BigDecimal objId;

    public GimRutObjId() {
    }

    public GimRutObjId(BigDecimal rutId, BigDecimal objId) {
       this.rutId = rutId;
       this.objId = objId;
    }
   
    public BigDecimal getRutId() {
        return this.rutId;
    }
    
    public void setRutId(BigDecimal rutId) {
        this.rutId = rutId;
    }
    public BigDecimal getObjId() {
        return this.objId;
    }
    
    public void setObjId(BigDecimal objId) {
        this.objId = objId;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof GimRutObjId) ) return false;
		 GimRutObjId castOther = ( GimRutObjId ) other; 
         
		 return ( (this.getRutId()==castOther.getRutId()) || ( this.getRutId()!=null && castOther.getRutId()!=null && this.getRutId().equals(castOther.getRutId()) ) )
 && ( (this.getObjId()==castOther.getObjId()) || ( this.getObjId()!=null && castOther.getObjId()!=null && this.getObjId().equals(castOther.getObjId()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getRutId() == null ? 0 : this.getRutId().hashCode() );
         result = 37 * result + ( getObjId() == null ? 0 : this.getObjId().hashCode() );
         return result;
   }   


}

