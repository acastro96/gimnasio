/*
 * Proyecto Gimnasio Virtual. 
 * Universidad Simón Bolívar - Barranquilla / Colombia.
 * Desarrollado por Ing. Alberto Castro Maestre
 */
package Bo;

import Bean.ClientesBean;
import Pojo.GimCliente;
import java.util.List;

/**
 *
 * @author Alberto Castro
 */
public interface ClienteBO {
    
    void mostrarCliente(ClientesBean clientesBean) throws Exception;
    
    void agregarCliente(ClientesBean clientesBean) throws Exception;
    
    void actualizarCliente(ClientesBean clientesBean) throws Exception;
    
    List<GimCliente> listaClientes(ClientesBean clientesBean) throws Exception;
    
    void llenarParametros (ClientesBean clientesBean) throws Exception;
    
    void planesDisponibles (ClientesBean clientesBean) throws Exception;
    
    void asignarPlan (ClientesBean clientesBean) throws Exception;
    
}