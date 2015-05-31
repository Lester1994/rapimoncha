package una.android.rapimoncha.entidades;


import org.json.JSONObject;

import una.android.rapimoncha.interfaces.IJsonable;

/**
 * Created by Lester on 31/03/2015.
 */
public class Subcripcion implements IJsonable{
    private int idSuscribcion;
    private boolean isEnviarPromocion;
    private Usuario usuario;

    public Subcripcion() {
    }

    public int getIdSuscribcion() {
        return idSuscribcion;
    }

    public void setIdSuscribcion(int idSuscribcion) {
        this.idSuscribcion = idSuscribcion;
    }

    public boolean isEnviarPromocion() {
        return isEnviarPromocion;
    }

    public void setEnviarPromocion(boolean isEnviarPromocion) {
        this.isEnviarPromocion = isEnviarPromocion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public JSONObject getJson() {
        JSONObject obaux=new JSONObject();
        try {
            obaux.put("idsubscripcion", getIdSuscribcion());
            obaux.put("enviarpromocion", isEnviarPromocion());
            JSONObject usuariosub=new JSONObject();
            Usuario usaux=getUsuario();
            if(usaux!=null){
                usuariosub=usaux.getJson();
            }
            obaux.put("usuario",usuariosub);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return obaux;
    }
    
    public boolean generateFromJson(JSONObject json){
		 boolean resp=true;
		 try{
			setIdSuscribcion(json.getInt("idSubscripcion"));
			//por completar
			
			 
		 }catch(Exception ex){
			 ex.printStackTrace();
		 }
		 return resp;
   }
}
