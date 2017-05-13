package Pojo;
// Generated 28/04/2017 10:08:51 PM by Hibernate Tools 4.3.1


import java.math.BigDecimal;

/**
 * GimMemUsuId generated by hbm2java
 */
public class GimMemUsuId  implements java.io.Serializable {


     private BigDecimal memId;
     private BigDecimal usuId;

    public GimMemUsuId() {
    }

    public GimMemUsuId(BigDecimal memId, BigDecimal usuId) {
       this.memId = memId;
       this.usuId = usuId;
    }
   
    public BigDecimal getMemId() {
        return this.memId;
    }
    
    public void setMemId(BigDecimal memId) {
        this.memId = memId;
    }
    public BigDecimal getUsuId() {
        return this.usuId;
    }
    
    public void setUsuId(BigDecimal usuId) {
        this.usuId = usuId;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof GimMemUsuId) ) return false;
		 GimMemUsuId castOther = ( GimMemUsuId ) other; 
         
		 return ( (this.getMemId()==castOther.getMemId()) || ( this.getMemId()!=null && castOther.getMemId()!=null && this.getMemId().equals(castOther.getMemId()) ) )
 && ( (this.getUsuId()==castOther.getUsuId()) || ( this.getUsuId()!=null && castOther.getUsuId()!=null && this.getUsuId().equals(castOther.getUsuId()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getMemId() == null ? 0 : this.getMemId().hashCode() );
         result = 37 * result + ( getUsuId() == null ? 0 : this.getUsuId().hashCode() );
         return result;
   }   


}


