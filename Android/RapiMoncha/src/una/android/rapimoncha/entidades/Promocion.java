package una.android.rapimoncha.entidades;


import org.json.JSONObject;

import una.android.rapimoncha.interfaces.IJsonable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Lester on 31/03/2015.
 */
public class Promocion implements IJsonable {
    private int idPromocion;
    private Calendar fechaVencimiento;
    private String descripcion;
    private String titulo;
    private Calendar fechaPublicacion;

    public Promocion() {
    }

    public Promocion(int idPromocion,
                     Calendar fechaVencimiento,
                     String descripcion,
                     String titulo,
                     Calendar fechaPublicacion) {
        this.idPromocion = idPromocion;
        this.fechaVencimiento = fechaVencimiento;
        this.descripcion = descripcion;
        this.titulo = titulo;
        this.fechaPublicacion = fechaPublicacion;
    }

    public int getIdPromocion() {
        return idPromocion;
    }

    public void setIdPromocion(int idPromocion) {
        this.idPromocion = idPromocion;
    }

    public Calendar getFechaVencimiento() {
        return fechaVencimiento;
    }
    public String getDateTimeVencimiento(){
         DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         return dateFormat.format(fechaVencimiento.getTime());
    }
    public void setFechaVencimiento(Calendar fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }
    public void setFechaVencimiento(String fechaVencimiento) {
        //por terminar
    }
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Calendar getFechaPublicacion() {
        return fechaPublicacion;
    }
    public String getDateTimePublicacion(){
         DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         return dateFormat.format(fechaPublicacion.getTime());
    }
    public void setFechaPublicacion(Calendar fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }
    
    public void setFechaPublicacion(String fechaPublicacion) {
       //por terminar
    }    

    @Override
    public JSONObject getJson() {
        JSONObject obaux=new JSONObject();
        try {
            obaux.put("idpromocion", getIdPromocion());
            obaux.put("fechavencimiento", getDateTimeVencimiento());
            obaux.put("descripcion", getDescripcion());
            obaux.put("titulo", getTitulo());
            obaux.put("fechapublicacion", getDateTimePublicacion());
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return obaux;
    }
    
    public boolean generateFromJson(JSONObject json){
		 boolean resp=true;
		 try{
			 this.setIdPromocion(json.getInt("idPromocion"));
			 this.setFechaPublicacion(json.getString("fechaPublicacion"));
			 this.setFechaVencimiento(json.getString("fechaVencimiento"));
			 this.setTitulo(json.getString("titulo"));
			 this.setDescripcion(json.getString("descripcion"));
		 }catch(Exception ex){
			 ex.printStackTrace();
		 }
		 return resp;
   }
}
