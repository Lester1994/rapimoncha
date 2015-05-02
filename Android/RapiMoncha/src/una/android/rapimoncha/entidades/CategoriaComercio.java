package una.android.rapimoncha.entidades;


import org.json.JSONObject;

import una.android.rapimoncha.interfaces.IJsonable;

/**
 * Created by Lester on 31/03/2015.
 */
public class CategoriaComercio implements IJsonable{
    @Override
    public JSONObject getJson() {
        JSONObject obaux=new JSONObject();
        try {
            obaux.put("idclasificacion", getIdTipoCategoria());
            obaux.put("noclasificacion", getNoTipoCategoria());
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return obaux;
    }

    private int idTipoCategoria;
    private String noTipoCategoria;
    
    public boolean seleccionado;


    public CategoriaComercio() {
    }

    public String getNoTipoCategoria() {
        return noTipoCategoria;
    }

    public void setNoTipoCategoria(String noTipoCategoria) {
        this.noTipoCategoria = noTipoCategoria;
    }

    public int getIdTipoCategoria() {
        return idTipoCategoria;
    }

    public void setIdTipoCategoria(int idTipoCategoria) {
        this.idTipoCategoria = idTipoCategoria;
    }
}
