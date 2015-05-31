package una.android.rapimoncha.entidades;


import java.io.Serializable;

import org.json.JSONObject;

import una.android.rapimoncha.interfaces.IJsonable;

/**
 * Created by Lester on 31/03/2015.
 */
public class Galeria implements IJsonable,Serializable {
    private int idImagen;
    private String dtImagen;

    @Override
    public JSONObject getJson() {
        JSONObject obaux=new JSONObject();
        try {
            obaux.put("idimagen", idImagen);
            obaux.put("dtimagen", dtImagen);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return obaux;
    }

    public Galeria() {
    }

    public int getIdImagen() {
        return idImagen;
    }

    public void setIdImagen(int idImagen) {
        this.idImagen = idImagen;
    }

    public String getDtImagen() {
        return dtImagen;
    }

    public void setDtImagen(String dtImagen) {
        this.dtImagen = dtImagen;
    }
    
    public boolean generateFromJson(JSONObject json){
		 boolean resp=true;
		 try{
			 this.setIdImagen(json.getInt("idimagen"));
			 this.setDtImagen(json.getString("dtimagen"));
			 
		 }catch(Exception ex){
			 ex.printStackTrace();
		 }
		 return resp;
    }
}
