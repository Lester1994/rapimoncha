package una.android.rapimoncha.entidades;


import org.json.JSONArray;
import org.json.JSONObject;

import una.android.rapimoncha.interfaces.IJsonable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

/**
 * Created by Lester on 31/03/2015.
 */
public class SolicitudCliente implements IJsonable {
    private int idSolicitud;
    private Calendar feSolicitud;
    private int esSolicitud;
    private Calendar eHastaSoli;
    private double coFinalSoli;
    private Usuario usuario;
    ArrayList<Producto> detalles;

    public SolicitudCliente() {
    }
    public String getDateTimeSolicitud(){
         DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         return dateFormat.format(feSolicitud.getTime());
    }
     public String getDateTimeEsperoHasta(){
         DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         return dateFormat.format(eHastaSoli.getTime());
    }
    public int getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(int idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public Calendar getFeSolicitud() {
        return feSolicitud;
    }

    public void setFeSolicitud(Calendar feSolicitud) {
        this.feSolicitud = feSolicitud;
    }

    public int getEsSolicitud() {
        return esSolicitud;
    }

    public void setEsSolicitud(int esSolicitud) {
        this.esSolicitud = esSolicitud;
    }

    public Calendar geteHastaSoli() {
        return eHastaSoli;
    }

    public void seteHastaSoli(Calendar eHastaSoli) {
        this.eHastaSoli = eHastaSoli;
    }

    public double getCoFinalSoli() {
        return coFinalSoli;
    }

    public void setCoFinalSoli(double coFinalSoli) {
        this.coFinalSoli = coFinalSoli;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public ArrayList<Producto> getDetalles() {
        return detalles;
    }

    public void setDetalles(ArrayList<Producto> detalles) {
        this.detalles = detalles;
    }

    @Override
    public JSONObject getJson() {
        JSONObject obaux=new JSONObject();
        try {
            obaux.put("idsolicitud", getIdSolicitud());
            obaux.put("fechasolicitud", getDateTimeSolicitud());
            obaux.put("estadosolicitud", getEsSolicitud());
            obaux.put("esperohasta", getDateTimeEsperoHasta());
            obaux.put("costofinal", getCoFinalSoli());

            JSONObject usuariosub=new JSONObject();
            Usuario usaux=getUsuario();
            if(usaux!=null){
               usuariosub=usaux.getJson();
            }
            obaux.put("usuario",usuariosub);

            JSONArray detallesolicitudsub=new JSONArray();
            ArrayList<Producto> desaux=getDetalles();
            Iterator<Producto> iter_detallesoli=usaux!=null?desaux.iterator():null;
            while(iter_detallesoli!=null&&iter_detallesoli.hasNext()){
                Producto aux2=iter_detallesoli.next();
                JSONObject objson=aux2.getJson();
                detallesolicitudsub.put(objson);
            }
            obaux.put("detallessolicitud",detallesolicitudsub);


        }catch(Exception ex){
            ex.printStackTrace();
        }

        return obaux;
    }
}
