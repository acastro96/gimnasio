/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author acastro
 */
public class Modulo implements Serializable{
    
    private int codigo;
    private String nombre;
    private String icon;
    private List<Recurso> listRecursos;
    
    /**
     * @return the codigo
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the icon
     */
    public String getIcon() {
        return icon;
    }

    /**
     * @param icon the icon to set
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * @return the listRecursos
     */
    public List<Recurso> getListRecursos() {
        return listRecursos;
    }

    /**
     * @param listRecursos the listRecursos to set
     */
    public void setListRecursos(List<Recurso> listRecursos) {
        this.listRecursos = listRecursos;
    }

}
