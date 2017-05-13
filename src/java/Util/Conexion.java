/*
 * Proyecto Gimnasio Virtual.
 * Universidad Simón Bolívar - Barranquilla / Colombia.
 * Desarrollado por Ing. Alberto Castro Maestre
 */

package Util;

import org.hibernate.Session;

/**
 *
 * @author Alberto Castro
 */
public class Conexion {
    
    private static Conexion conexion = new Conexion();
    private static Session session;

    public static void setSession(Session aSession) {
        session = aSession;
    }
    private Conexion(){}
    
    public static Conexion getInstance(){
        return getConexion();
    }

    public static void existeConexion(){
        try {
            if(getSession() == null){
                setSession(HibernateUtil.HibernateUtil.getSessionFactory().openSession());
            }
            if(!getSession().isOpen()){
                setSession(HibernateUtil.HibernateUtil.getSessionFactory().openSession());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
     /**
     * @return the conexion
     */
    public static Conexion getConexion() {
        return conexion;
    }

    /**
     * @param aConexion the conexion to set
     */
    public static void setConexion(Conexion aConexion) {
        conexion = aConexion;
    }

    /**
     * @return the session
     */
    public static Session getSession() {
        return session;
    }

    /**
     * @param aSession the session to set
     */
    
}

