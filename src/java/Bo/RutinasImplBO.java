/*
 * Proyecto Gimnasio Virtual. 
 * Universidad Simón Bolívar - Barranquilla / Colombia.
 * Desarrollado por Ing. Alberto Castro Maestre
 */
package Bo;

import Bean.RutinasBean;
import Dao.DaoRutina;
import Dao.DaoVideo;
import Interface.ITRutina;
import Interface.ITVideo;
import Pojo.GimRutina;
import Pojo.GimTelefono;
import Pojo.GimVideo;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Alberto Castro
 */
public class RutinasImplBO implements RutinasBO {

    private ITRutina daoRutina;
    private ITVideo daoVideo;

    public RutinasImplBO() {
        daoRutina = new DaoRutina();
        daoVideo = new DaoVideo();
    }

    @Override
    public void mostrarRutina(RutinasBean rutinasBean) throws Exception {
        Session session = HibernateUtil.HibernateUtil.getSessionFactory().openSession();
        GimRutina gimRutina;

        try {
            if (rutinasBean.getCodigo().equalsIgnoreCase("") && rutinasBean.getNombre().equalsIgnoreCase("")) {
                rutinasBean.setCodeMensaje(2);
                rutinasBean.setMensaje("Ingrese el parametro de busqueda");
                return;
            }

            if (!rutinasBean.getCodigo().equalsIgnoreCase("")) {
                gimRutina = getDaoRutina().getRutinaByCod(session, rutinasBean.getCodigo());
            } else {
                gimRutina = getDaoRutina().getRutinaByNom(session, rutinasBean.getNombre());
            }

            if (gimRutina == null) {
                rutinasBean.setCodeMensaje(3);
                rutinasBean.setMensaje("La Rutina no se encuentra registrada");
                return;
            }

            rutinasBean.setNombre(gimRutina.getRutNombre());
            rutinasBean.setDescripcion(gimRutina.getRutDescripcion());
            rutinasBean.setRepeticiones(gimRutina.getRutRepeticiones().intValue());
            rutinasBean.setCalorias(gimRutina.getRutCalorias().intValue());

        } catch (Exception e) {

        } finally {

            if (session != null) {
                session.close();
            }

        }
    }

    @Override
    public void agregarRutina(RutinasBean rutinasBean) throws Exception {

        OutputStream outputStream;
        Session session = HibernateUtil.HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        GimRutina gimRutina;

        try {
            if (rutinasBean.getCodigo().equalsIgnoreCase("")) {
                rutinasBean.setCodeMensaje(2);
                rutinasBean.setMensaje("El Código no puede ser vacío");
                return;
            }

            if (rutinasBean.getNombre().equalsIgnoreCase("")) {
                rutinasBean.setCodeMensaje(2);
                rutinasBean.setMensaje("El Nombre de la Rutina no puede ser vacío");
                return;
            }

            if (rutinasBean.getDescripcion().equalsIgnoreCase("")) {
                rutinasBean.setCodeMensaje(2);
                rutinasBean.setMensaje("El campo de Descripción no puede ser vacío");
                return;
            }

            gimRutina = getDaoRutina().getRutinaByCod(session, rutinasBean.getCodigo());

            if (gimRutina != null) {
                rutinasBean.setCodeMensaje(3);
                rutinasBean.setMensaje("¡Codigo de Rutina inválido! La Rutina ya se encuentra registrada");
                return;
            }

            gimRutina = getDaoRutina().getRutinaByNom(session, rutinasBean.getNombre());

            if (gimRutina != null) {
                rutinasBean.setCodeMensaje(3);
                rutinasBean.setMensaje("¡Nombre de Rutina Inválido! La Rutina ya se encuentra registrada");
                return;
            }

            //Aqui se guarda el video que se va a asociar a la rutina, en este momento la dirección es estatica pero la idea general es que sea en un 
            //servidor que sea unicamente de videos
            String dir = "E:\\Documentos\\Desarrollos\\Gimnasio\\web\\videos\\";
            File dr = new File(dir);
            if (!dr.exists()) {
                dr.mkdirs();
            }

            dr = new File(dir + rutinasBean.getNombre() + ".mp4");
            InputStream inputStream = rutinasBean.getArchivo();
            outputStream = new FileOutputStream(dr);
            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
            outputStream.close();

            gimRutina = new GimRutina(BigDecimal.ZERO, rutinasBean.getDescripcion(), rutinasBean.getNombre(), BigDecimal.valueOf(rutinasBean.getRepeticiones()), BigDecimal.valueOf(rutinasBean.getCalorias()), rutinasBean.getCodigo());

            int rut = getDaoRutina().insert(session, gimRutina);

            gimRutina.setRutId(BigDecimal.valueOf(rut));

            transaction.commit();
            transaction = session.beginTransaction();

            getDaoVideo().insert(session, new GimVideo(BigDecimal.ZERO, gimRutina, rutinasBean.getDescripcion(), dr.getAbsolutePath(), null, BigDecimal.ONE));

            rutinasBean.setCodeMensaje(1);
            rutinasBean.setMensaje("La rutina se registró correctamente");

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {

            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void actualizarRutina(RutinasBean rutinasBean) throws Exception {

        Session session = HibernateUtil.HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        GimRutina gimRutina;
        GimTelefono gimTelefono;

        try {

            if (rutinasBean.getCodigo().equalsIgnoreCase("")) {
                rutinasBean.setCodeMensaje(2);
                rutinasBean.setMensaje("El Código no puede ser vacío");
                return;
            }

            if (rutinasBean.getNombre().equalsIgnoreCase("")) {
                rutinasBean.setCodeMensaje(2);
                rutinasBean.setMensaje("El Nombre de la Rutina no puede ser vacío");
                return;
            }

            if (rutinasBean.getDescripcion().equalsIgnoreCase("")) {
                rutinasBean.setCodeMensaje(2);
                rutinasBean.setMensaje("El campo de Descripción no puede ser vacío");
                return;
            }

            gimRutina = getDaoRutina().getRutinaByCod(session, rutinasBean.getCodigo());

            gimRutina.setRutCalorias(BigDecimal.valueOf(rutinasBean.getCalorias()));
            gimRutina.setRutRepeticiones(BigDecimal.valueOf(rutinasBean.getRepeticiones()));
            gimRutina.setRutDescripcion(rutinasBean.getDescripcion());

            getDaoRutina().update(session, gimRutina);

            rutinasBean.setCodeMensaje(1);
            rutinasBean.setMensaje("La rutina se actualizó correctamente");

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {

            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void mostrarVideo(RutinasBean rutinasBean) throws Exception {

        try {

            FacesContext face = FacesContext.getCurrentInstance();

            String x = " window.open('video.xhtml','mywindow', 'resizable=no,toolbar=no,scrollbars=yes,height=450,width=530,top=145,left=235');";
            face.getExternalContext().getSessionMap().put("video", rutinasBean.getNombre() + ".mp4");
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.execute(x);
            FacesMessage msg = new FacesMessage("", rutinasBean.getNombre() + " está cargado");
            FacesContext.getCurrentInstance().addMessage(null, msg);

        } catch (Exception e) {
        }

    }

    @Override
    public List<GimRutina> listaRutinas(RutinasBean rutinasBean) throws Exception {

        Session session = HibernateUtil.HibernateUtil.getSessionFactory().openSession();

        List<GimRutina> listRutinas = new LinkedList<>();
        GimRutina gimRutina;

        try {

            switch (rutinasBean.getTipo()) {

                case 1://Consulta de Rutina por Codigo

                    if (rutinasBean.getCodigo().equalsIgnoreCase("")) {
                        rutinasBean.setCodeMensaje(2);
                        rutinasBean.setMensaje("El Código no puede ser vacío");
                        return null;
                    }

                    gimRutina = getDaoRutina().getRutinaByCod(session, rutinasBean.getCodigo());

                    if (gimRutina == null) {
                        rutinasBean.setCodeMensaje(3);
                        rutinasBean.setMensaje("La rutina no está registrada");
                        return null;
                    }

                    listRutinas.add(gimRutina);

                    break;

                case 2:// Consulta por nombre

                    if (rutinasBean.getNombre().equalsIgnoreCase("")) {
                        rutinasBean.setCodeMensaje(2);
                        rutinasBean.setMensaje("El Nombre no puede ser vacío");
                        return null;
                    }

                    gimRutina = getDaoRutina().getRutinaByNom(session, rutinasBean.getNombre());

                    if (gimRutina == null) {
                        rutinasBean.setCodeMensaje(3);
                        rutinasBean.setMensaje("La rutina no está registrada");
                        return null;
                    }

                    listRutinas.add(gimRutina);

                    break;
            }

            return listRutinas;

        } catch (Exception e) {
            rutinasBean.setCodeMensaje(4);
            rutinasBean.setMensaje("Ha ocurrido un error interno, comuniquese con el administrador");
            return null;
        }
    }
    
//    @Override
//    public void mostrarVideolist(RutinasBean rutinasBean) throws Exception {
//        try {
//
//            FacesContext face = FacesContext.getCurrentInstance();
//
//            String x = " window.open('video.xhtml','mywindow', 'resizable=no,toolbar=no,scrollbars=yes,height=450,width=530,top=145,left=235');";
//            face.getExternalContext().getSessionMap().put("video", rutinasBean.getNombre() + ".mp4");
//            RequestContext requestContext = RequestContext.getCurrentInstance();
//            requestContext.execute(x);
//            FacesMessage msg = new FacesMessage("", rutinasBean.getNombre() + " está cargado");
//            FacesContext.getCurrentInstance().addMessage(null, msg);
//
//        } catch (Exception e) {
//        }
//    }

    /**
     * @return the daoRutina
     */
    public ITRutina getDaoRutina() {
        return daoRutina;
    }

    /**
     * @param daoRutina the daoRutina to set
     */
    public void setDaoRutina(ITRutina daoRutina) {
        this.daoRutina = daoRutina;
    }

    /**
     * @return the daoVideo
     */
    public ITVideo getDaoVideo() {
        return daoVideo;
    }

    /**
     * @param daoVideo the daoVideo to set
     */
    public void setDaoVideo(ITVideo daoVideo) {
        this.daoVideo = daoVideo;
    }

    

}
