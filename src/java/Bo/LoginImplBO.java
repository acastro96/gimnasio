/*
 * Proyecto Gimnasio Virtual. 
 * Universidad Simón Bolívar - Barranquilla / Colombia.
 * Desarrollado por Ing. Alberto Castro Maestre
 */
package Bo;

import Bean.LoginBean;
import Dao.DaoModulo;
import Dao.DaoUsuario;
import Interface.ITModulo;
import Interface.ITUsuario;
import Pojo.GimModulo;
import Pojo.GimRecurso;
import Pojo.GimUsuario;
import Util.Modulo;
import Util.Recurso;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Alberto Castro
 */
public class LoginImplBO implements LoginBO {

    private ITUsuario usuarioDao;
    private ITModulo moduloDao;
    
    public LoginImplBO (){
        usuarioDao = new DaoUsuario();
        moduloDao = new DaoModulo();
    }

    @Override
    public void logIn(LoginBean blogin) throws Exception {

        try {

            Session session = HibernateUtil.HibernateUtil.getSessionFactory().openSession();

            setUsuarioDao(new DaoUsuario());

            GimUsuario gimUsuario = getUsuarioDao().getUsuarioByNamePass(session, blogin.getUsuario(), blogin.getPassword());

            if (gimUsuario == null) {
                blogin.setUrl("");
                blogin.setMensaje("Usuario y/o contraseña invalidos");
                return;
            }

            blogin.setIdusuario(gimUsuario.getUsuId().intValue());
            blogin.setMensaje("Bienvenido");
            blogin.setUrl("contenido/inicio");

            if (session != null) {
                session.close();
            }

        } catch (Exception e) {

        }
    }

    @Override
    public List<Modulo> listarModulos(LoginBean blogin) throws Exception {

        Session session = HibernateUtil.HibernateUtil.getSessionFactory().openSession();
        List<GimRecurso> listRecurso = getUsuarioDao().listarRecursos(session, BigDecimal.valueOf(blogin.getIdusuario()));
        boolean sw;
        List<Modulo> listModulo = new LinkedList<>();
        for (GimRecurso objRec : listRecurso) {
            sw = true;
            
            GimModulo modulo = getModuloDao().getModuloById(session, objRec.getGimModulo().getModId());

            for (Modulo listm : listModulo) {
                if (listm.getNombre().equalsIgnoreCase(modulo.getModNombre())) {
                    sw = false;
                }
            }
            if (sw) {
                Modulo mod = new Modulo();
                mod.setCodigo(modulo.getModId().intValue());
                mod.setIcon(modulo.getIcono());
                mod.setNombre(modulo.getModNombre());
                listModulo.add(mod);
            }
        }
        
        for (Modulo objMod : listModulo) {
            List<Recurso> listRec = new LinkedList<>();
            for (GimRecurso objRec : listRecurso) {
                if (objRec.getGimModulo().getModId().intValue() == objMod.getCodigo()) {
                    Recurso recu = new Recurso();
                    recu.setCodigo(objRec.getRecId().intValue());
                    recu.setNombre(objRec.getRecNombre());
                    recu.setDescripcion(objRec.getRecDescripcion());
                    listRec.add(recu);
                }
            }
            objMod.setListRecursos(listRec);
        }
        return listModulo;
    }

    @Override
    public List<String> listarRecursos(LoginBean blogin) throws Exception {
        Session session = HibernateUtil.HibernateUtil.getSessionFactory().openSession();

        List<GimRecurso> listGimRecurso = getUsuarioDao().listarRecursos(session, BigDecimal.valueOf(blogin.getIdusuario()));
        List<String> listRecurso = new LinkedList<>();
        if (listGimRecurso == null) {
            return null;
        }
        for (GimRecurso rec : listGimRecurso) {
            listRecurso.add(rec.getRecDescripcion());
        }
        return listRecurso;
    }

    /**
     * @return the usuarioDao
     */
    public ITUsuario getUsuarioDao() {
        return usuarioDao;
    }

    /**
     * @param usuarioDao the usuarioDao to set
     */
    public void setUsuarioDao(ITUsuario usuarioDao) {
        this.usuarioDao = usuarioDao;
    }

    /**
     * @return the moduloDao
     */
    public ITModulo getModuloDao() {
        return moduloDao;
    }

    /**
     * @param moduloDao the moduloDao to set
     */
    public void setModuloDao(ITModulo moduloDao) {
        this.moduloDao = moduloDao;
    }

}
