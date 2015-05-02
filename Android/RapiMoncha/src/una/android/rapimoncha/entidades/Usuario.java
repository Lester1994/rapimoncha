package una.android.rapimoncha.entidades;


import org.json.JSONArray;
import org.json.JSONObject;

import una.android.rapimoncha.interfaces.IJsonable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Lester on 31/03/2015.
 */
public class Usuario implements IJsonable {
    private int idUsuario;
    private String noUsuario;
    private String a1Usuario;
    private String a2Usuario;
    private Calendar feNacimien;
    private String seUsuario;
    private String username;
    private String password;
    public String fechanacString;

    public Usuario() {
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNoUsuario() {
        return noUsuario;
    }
    public String getDateTimeFeNacimiento(){
        /* DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
         return dateFormat.format(feNacimien.getTime());*/
    	return fechanacString;
    }

    public void setNoUsuario(String noUsuario) {
        this.noUsuario = noUsuario;
    }

    public String getA1Usuario() {
        return a1Usuario;
    }

    public void setA1Usuario(String a1Usuario) {
        this.a1Usuario = a1Usuario;
    }

    public String getA2Usuario() {
        return a2Usuario;
    }

    public void setA2Usuario(String a2Usuario) {
        this.a2Usuario = a2Usuario;
    }

    public Calendar getFeNacimien() {
        return feNacimien;
    }

    public void setFeNacimien(Calendar feNacimien) {
        this.feNacimien = feNacimien;
    }

    public String getSeUsuario() {
        return seUsuario;
    }

    public void setSeUsuario(String seUsuario) {
        this.seUsuario = seUsuario;
    }

    @Override
    public JSONObject getJson() {
        JSONObject usuariosub=new JSONObject();
        JSONObject finale=new JSONObject();
       try {

           usuariosub.put("idusuario", getIdUsuario());
           usuariosub.put("nousuario", getNoUsuario());
           usuariosub.put("a1usuario", getA1Usuario());
           usuariosub.put("a2usuario", getA2Usuario());
           usuariosub.put("fenacimiento", getDateTimeFeNacimiento());
           usuariosub.put("sexo", getSeUsuario());
           usuariosub.put("user", getUsername());
           usuariosub.put("password", getPassword());
           JSONArray json_usuario_=new JSONArray();
           json_usuario_.put(usuariosub);
           finale.put("usuario",json_usuario_);
       }catch(Exception ex){
           ex.printStackTrace();

        }

        return finale;
    }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
