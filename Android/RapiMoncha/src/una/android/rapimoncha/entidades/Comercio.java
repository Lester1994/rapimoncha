package una.android.rapimoncha.entidades;

import android.util.Log;
import org.json.JSONArray;
import org.json.JSONObject;

import una.android.rapimoncha.interfaces.IJsonable;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Lester on 31/03/2015.
 */
public class Comercio implements IJsonable {
	private int idComercio;
	private String noComerncio;
	private String diComercio;
	private String prComercio;
	private String t1Comercio;
	private String t2Comercio;
	private String emComercio;
	private ArrayList<CategoriaComercio> caComercio;
	private ArrayList<Galeria> imagenes;
	private ArrayList<Subcripcion> subscriptores;
	private ArrayList<GeoLocalizacion> geolocalizaciones;
	private ArrayList<Promocion> promociones;
	private ArrayList<Producto> productos;
	private ArrayList<SolicitudCliente> solicitudes;
	private ArrayList<Preferencia> preferencias;
	private Usuario usuario;

	public Comercio() {
	}

	public int getIdComercio() {
		return idComercio;
	}

	public void setIdComercio(int idComercio) {
		this.idComercio = idComercio;
	}

	public String getNoComerncio() {
		return noComerncio;
	}

	public void limpiarImagenes() {
		if (this.imagenes != null) {

			this.imagenes.clear();
		}

	}

	public void setNoComerncio(String noComerncio) {
		this.noComerncio = noComerncio;
	}

	public String getDiComercio() {
		return diComercio;
	}

	public void setDiComercio(String diComercio) {
		this.diComercio = diComercio;
	}

	public String getPrComercio() {
		return prComercio;
	}

	public void setPrComercio(String prComercio) {
		this.prComercio = prComercio;
	}

	public String getT1Comercio() {
		return t1Comercio;
	}

	public void setT1Comercio(String t1Comercio) {
		this.t1Comercio = t1Comercio;
	}

	public String getT2Comercio() {
		return t2Comercio;
	}

	public void setT2Comercio(String t2Comercio) {
		this.t2Comercio = t2Comercio;
	}

	public String getEmComercio() {
		return emComercio;
	}

	public void setEmmComercio(String emmComercio) {
		this.emComercio = emmComercio;
	}

	public ArrayList<CategoriaComercio> getCaComercio() {
		return caComercio;
	}

	public void setCaComercio(ArrayList<CategoriaComercio> caComercio) {
		this.caComercio = caComercio;
	}

	public ArrayList<Galeria> getImagenes() {
		return imagenes;
	}

	public void setImagenes(ArrayList<Galeria> imagenes) {
		this.imagenes = imagenes;
	}

	public ArrayList<Subcripcion> getSubscriptores() {
		return subscriptores;
	}

	public void setSubscriptores(ArrayList<Subcripcion> subscriptores) {
		this.subscriptores = subscriptores;
	}

	public ArrayList<GeoLocalizacion> getUbicacionmapa() {
		return geolocalizaciones;
	}

	public void setUbicacionmapa(ArrayList<GeoLocalizacion> ubicacionmapa) {
		this.geolocalizaciones = ubicacionmapa;
	}

	public ArrayList<Promocion> getPromocion() {
		return promociones;
	}

	public void setPromocion(ArrayList<Promocion> promocion) {
		this.promociones = promocion;
	}

	public ArrayList<Producto> getProductos() {
		return productos;
	}

	public void setProductos(ArrayList<Producto> productos) {
		this.productos = productos;
	}

	public ArrayList<SolicitudCliente> getSolicitudes() {
		return solicitudes;
	}

	public void setSolicitudes(ArrayList<SolicitudCliente> solicitudes) {
		this.solicitudes = solicitudes;
	}

	public void addPromocion(Promocion promocion) {
		if (promociones == null) {
			promociones = new ArrayList<Promocion>();
		}

		promociones.add(promocion);
	}

	public void addProducto(Producto producto) {
		if (productos == null) {
			productos = new ArrayList<Producto>();
		}

		productos.add(producto);
	}

	public void addSubscripcion(Subcripcion subcripcion) {
		if (subscriptores == null) {
			subscriptores = new ArrayList<Subcripcion>();
		}

		subscriptores.add(subcripcion);
	}

	public void addSolicitud(SolicitudCliente solicitud) {
		if (solicitudes == null) {
			solicitudes = new ArrayList<SolicitudCliente>();
		}

		solicitudes.add(solicitud);
	}

	public void addClasificacion(CategoriaComercio clasificacion) {
		if (this.caComercio == null) {
			caComercio = new ArrayList<CategoriaComercio>();
		}

		caComercio.add(clasificacion);
	}

	public void limpiarClasificaciones() {

		if (this.caComercio == null) {
			caComercio = new ArrayList<CategoriaComercio>();
		}

		caComercio.clear();

	}

	public void addImagen(Galeria imagen) {
		if (imagenes == null) {
			imagenes = new ArrayList<Galeria>();
		}

		imagenes.add(imagen);
	}

	public void limpiarPreferencias() {

		if (this.preferencias == null) {
			preferencias = new ArrayList<Preferencia>();
		}

		preferencias.clear();

	}

	public void addPreferencia(Preferencia preferencia) {
		if (preferencias == null) {
			preferencias = new ArrayList<Preferencia>();
		}

		preferencias.add(preferencia);
	}

	public ArrayList<Preferencia> getPreferenciascomercio() {
		return this.preferencias;
	}

	public void addGeolocalizacion(GeoLocalizacion geolocalizacion) {
		if (geolocalizaciones == null) {
			geolocalizaciones = new ArrayList<GeoLocalizacion>();
		}

		geolocalizaciones.add(geolocalizacion);
	}

	@Override
	public String toString() {
		String resp = this.t2Comercio
				+ (this.geolocalizaciones != null ? this.geolocalizaciones
						.size() : "geofalso")
				+ (this.caComercio != null ? this.caComercio.size()
						: "categorias falso") + (noComerncio);

		return resp;
	}

	@Override
	public JSONObject getJson() {
		JSONObject finale = new JSONObject();

		try {
			JSONObject json_comercio = new JSONObject();
			json_comercio.put("idcomercio", this.idComercio);
			json_comercio.put("nocomercio", this.noComerncio);
			json_comercio.put("dicomercio", this.diComercio);
			json_comercio.put("prcomercio", this.prComercio);
			json_comercio.put("t1comercio", this.t1Comercio);
			json_comercio.put("t2comercio", this.t2Comercio);
			json_comercio.put("emcomercio", this.emComercio);

			// usuario	
			if (usuario != null) {
				JSONArray js_usuariocomer = new JSONArray();
				JSONObject obauxa = usuario.getJson();
				js_usuariocomer.put(obauxa);
				json_comercio.put("usuario", js_usuariocomer);
			}
			// insertar imagenes
			Iterator<Galeria> iter_galeria = this.imagenes != null ? this.imagenes
					.iterator() : null;
			JSONArray js_imagenescomer = new JSONArray();
			if (iter_galeria != null) {
				Log.i("Mi nota", "Se agregaron se agrego imagenes");
				while (iter_galeria.hasNext()) {
					Galeria aux = iter_galeria.next();
					JSONObject obaux = aux.getJson();
					js_imagenescomer.put(obaux);
				}
			} else {
				Log.i("Mi nota", "No se agrego imagenes");
			}
			json_comercio.put("imagenes", js_imagenescomer);

			// insertar geolozcalizacion
			Iterator<GeoLocalizacion> iter_geolocalizacion = this.geolocalizaciones != null ? this.geolocalizaciones
					.iterator() : null;
			JSONArray js_geolocalizacioncomer = new JSONArray();
			if (iter_geolocalizacion != null) {

				while (iter_geolocalizacion.hasNext()) {
					GeoLocalizacion aux = iter_geolocalizacion.next();
					JSONObject obaux = aux.getJson();
					js_geolocalizacioncomer.put(obaux);
				}
			}
			json_comercio.put("geolocalizaciones", js_geolocalizacioncomer);

			// insertar preferencias
			Iterator<Preferencia> iter_preferencia = this.preferencias != null ? this.preferencias
					.iterator() : null;
			JSONArray js_preferencias = new JSONArray();
			if (iter_preferencia != null) {

				while (iter_preferencia.hasNext()) {
					Preferencia aux = iter_preferencia.next();
					JSONObject obaux = aux.getJson();
					js_preferencias.put(obaux);
				}
			}
			json_comercio.put("preferencias", js_preferencias);

			// insertar clasificaciones
			Iterator<CategoriaComercio> iter_clasificaciones = this.caComercio != null ? this.caComercio
					.iterator() : null;
			JSONArray js_clasificaciones = new JSONArray();
			if (iter_clasificaciones != null) {

				while (iter_clasificaciones.hasNext()) {
					CategoriaComercio aux = iter_clasificaciones.next();
					JSONObject obaux = aux.getJson();
					js_clasificaciones.put(obaux);
				}
			}
			json_comercio.put("clasificaciones", js_clasificaciones);

			// insertar subscripciones
			Iterator<Subcripcion> iter_subscripciones = this.subscriptores != null ? this.subscriptores
					.iterator() : null;
			JSONArray js_subscripciones = new JSONArray();
			if (iter_subscripciones != null) {
				while (iter_subscripciones.hasNext()) {
					Subcripcion aux = iter_subscripciones.next();
					JSONObject obaux = aux.getJson();
					js_subscripciones.put(obaux);
				}
			}
			json_comercio.put("subscripciones", js_subscripciones);

			// insertar productos
			Iterator<Producto> iter_productos = this.productos != null ? this.productos
					.iterator() : null;
			JSONArray js_productos = new JSONArray();
			if (iter_productos != null) {
				while (iter_productos.hasNext()) {
					Producto aux = iter_productos.next();
					JSONObject obaux = aux.getJson();
					js_productos.put(obaux);
				}
			}
			json_comercio.put("productos", js_productos);

			// insertar solicitudes cliente
			Iterator<SolicitudCliente> iter_solicitudes = this.solicitudes != null ? this.solicitudes
					.iterator() : null;
			JSONArray js_solicitudes = new JSONArray();
			if (iter_solicitudes != null) {

				while (iter_solicitudes.hasNext()) {
					SolicitudCliente aux = iter_solicitudes.next();
					JSONObject obaux = aux.getJson();
					js_solicitudes.put(obaux);
				}
			}
			json_comercio.put("solicitudes", js_solicitudes);

			// insertar clasificaciones
			Iterator<Promocion> iter_promociones = this.promociones != null ? this.promociones
					.iterator() : null;
			JSONArray js_promociones = new JSONArray();
			if (iter_promociones != null) {
				while (iter_promociones.hasNext()) {
					Promocion aux = iter_promociones.next();
					JSONObject obaux = aux.getJson();
					js_promociones.put(obaux);
				}
			}
			json_comercio.put("promociones", js_promociones);
			JSONArray json_comercio_ = new JSONArray();
			json_comercio_.put(json_comercio);
			finale.put("comercio", json_comercio_);

			Log.i("resultado", finale.toString());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return finale;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
