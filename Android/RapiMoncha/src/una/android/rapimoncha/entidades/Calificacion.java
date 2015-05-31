package una.android.rapimoncha.entidades;

import org.json.JSONObject;

import una.android.rapimoncha.interfaces.IJsonable;

public class Calificacion implements IJsonable{
	private int idclasificacion;
	private Usuario usuario;
	private int utilidad;
	private int facilidad;
	private int estilo;
	private int soporte;
	private String notas;
	
	public int getIdclasificacion() {
		return idclasificacion;
	}
	public void setIdclasificacion(int idclasificacion) {
		this.idclasificacion = idclasificacion;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public int getUtilidad() {
		return utilidad;
	}
	public void setUtilidad(int utilidad) {
		this.utilidad = utilidad;
	}
	public int getFacilidad() {
		return facilidad;
	}
	public void setFacilidad(int facilidad) {
		this.facilidad = facilidad;
	}
	public int getEstilo() {
		return estilo;
	}
	public void setEstilo(int estilo) {
		this.estilo = estilo;
	}
	public int getSoporte() {
		return soporte;
	}
	public void setSoporte(int soporte) {
		this.soporte = soporte;
	}
	public String getNotas() {
		return notas;
	}
	public void setNotas(String notas) {
		this.notas = notas;
	}
	
    @Override
    public JSONObject getJson() {
        JSONObject obaux=new JSONObject();
        try {
        	obaux.put("idccalificacion", idclasificacion);
            obaux.put("usuario", usuario.getJson());
            obaux.put("utilidad",utilidad );
            obaux.put("facilidad",facilidad );
            obaux.put("estilo",estilo );
            obaux.put("soporte", soporte);
            obaux.put("notas",notas );
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return obaux;
    }


}
