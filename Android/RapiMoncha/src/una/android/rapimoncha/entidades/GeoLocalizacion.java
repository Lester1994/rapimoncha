package una.android.rapimoncha.entidades;


import org.json.JSONObject;

import una.android.rapimoncha.interfaces.IJsonable;

/**
 * Created by Lester on 31/03/2015.
 */
public class GeoLocalizacion implements IJsonable{
    private double latitud;
    private double longitud;
    private int idLocalizacion;

    public GeoLocalizacion() {
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public int getIdLocalizacion() {
        return idLocalizacion;
    }

    public void setIdLocalizacion(int idLocalizacion) {
        this.idLocalizacion = idLocalizacion;
    }

    @Override
    public JSONObject getJson() {
        JSONObject obaux=new JSONObject();
        try {
            obaux.put("idlocalizacion", getIdLocalizacion());
            obaux.put("lalocalizacion", getLatitud());
            obaux.put("lnlocalizacion", getLongitud());
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return obaux;
    }
    
    public boolean generateFromJson(JSONObject json){
		 boolean resp=true;
		 try{
			 this.setIdLocalizacion(json.getInt("idlocalizacion"));
			 this.setLatitud(json.getDouble("latitud"));
			 this.setLongitud(json.getDouble("longitud"));
			 
		 }catch(Exception ex){
			 ex.printStackTrace();
		 }
		 return resp;
    }
}
