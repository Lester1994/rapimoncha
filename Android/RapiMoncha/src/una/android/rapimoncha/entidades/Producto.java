package una.android.rapimoncha.entidades;



import org.json.JSONArray;
import org.json.JSONObject;

import una.android.rapimoncha.interfaces.IJsonable;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Lester on 31/03/2015.
 */
public class Producto implements IJsonable {
    private int idProducto;
    private String noProducto;
    private Double prProducto;
    private String deProducto;
    private ArrayList<Galeria> imagenes;

    public Producto() {
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNoProducto() {
        return noProducto;
    }

    public void setNoProducto(String noProducto) {
        this.noProducto = noProducto;
    }

    public Double getPrProducto() {
        return prProducto;
    }

    public void setPrProducto(Double prProducto) {
        this.prProducto = prProducto;
    }

    public String getDeProducto() {
        return deProducto;
    }

    public void setDeProducto(String deProducto) {
        this.deProducto = deProducto;
    }

    public ArrayList<Galeria> getImagenes() {
        return imagenes;
    }

    public void setImagenes(ArrayList<Galeria> imagenes) {
        this.imagenes = imagenes;
    }

    public void addImagen(Galeria imagen){
        if(imagenes==null){
            imagenes=new ArrayList<Galeria>();
        }

        imagenes.add(imagen);
    }
    public void limpiarImagenes(){
    	if(this.imagenes!=null){
    		
    	this.imagenes.clear();
    	}
    
    }
    @Override
    public JSONObject getJson() {
        JSONObject obaux=new JSONObject();
        try {
            obaux.put("idproducto", getIdProducto());
            obaux.put("noproducto", getNoProducto());
            obaux.put("deproducto", getDeProducto());
            obaux.put("precio", getPrProducto());

            JSONArray imagensub=new JSONArray();
            ArrayList<Galeria> usaux=getImagenes();
            Iterator<Galeria> iter_galproducto=usaux!=null?usaux.iterator():null;
            while(iter_galproducto!=null&&iter_galproducto.hasNext()){
                Galeria aux2=iter_galproducto.next();
                JSONObject objson=aux2.getJson();
                imagensub.put(objson);
            }
            obaux.put("imagenes",imagensub);

        }catch(Exception ex){
            ex.printStackTrace();
        }

        return obaux;
    }
    
}
