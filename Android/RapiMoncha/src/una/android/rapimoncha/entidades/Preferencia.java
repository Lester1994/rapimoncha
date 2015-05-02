package una.android.rapimoncha.entidades;


import org.json.JSONObject;

import una.android.rapimoncha.interfaces.IJsonable;

/**
 * Created by Lester on 31/03/2015.
 */
public class Preferencia implements IJsonable{
    private int idPreferencia;
    private boolean isContactoSinSubcripcion;
    private boolean isVerProductoSinSubscrib;
    private boolean hasServicioExpress;

    public Preferencia() {
    }

    public int getIdPreferencia() {
        return idPreferencia;
    }

    public void setIdPreferencia(int idPreferencia) {
        this.idPreferencia = idPreferencia;
    }

    public boolean isContactoSinSubcripcion() {
        return isContactoSinSubcripcion;
    }

    public void setContactoSinSubcripcion(boolean isContactoSinSubcripcion) {
        this.isContactoSinSubcripcion = isContactoSinSubcripcion;
    }

    public boolean isVerProductoSinSubscrib() {
        return isVerProductoSinSubscrib;
    }

    public void setVerProductoSinSubscrib(boolean isVerProductoSinSubscrib) {
        this.isVerProductoSinSubscrib = isVerProductoSinSubscrib;
    }

    public boolean isHasServicioExpress() {
        return hasServicioExpress;
    }

    public void setHasServicioExpress(boolean hasServicioExpress) {
        this.hasServicioExpress = hasServicioExpress;
    }

    @Override
    public JSONObject getJson() {
        JSONObject obaux=new JSONObject();
        try {
            obaux.put("idpreferencia", getIdPreferencia());
            obaux.put("contactosinsu", isContactoSinSubcripcion());
            obaux.put("productosinsu", isVerProductoSinSubscrib());
            obaux.put("servicioexpre", isHasServicioExpress());
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return obaux;
    }
}
