package una.android.rapimoncha.recursos;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.util.Log;

import una.android.rapimoncha.Main;
import una.android.rapimoncha.R;
import una.android.rapimoncha.activities.WelcomeScreen;
import una.android.rapimoncha.activities.modulos.configuracion.ListadoConfiguracionesScreen;
import una.android.rapimoncha.activities.modulos.otros.AcercaDeScreen;
import una.android.rapimoncha.activities.modulos.otros.ExitScreen;
import una.android.rapimoncha.activities.modulos.producto.ListadoProductosScreen;
import una.android.rapimoncha.activities.modulos.solicitudes.ListadoSolicitudesScreen;
import una.android.rapimoncha.activities.modulos.usuario.LogoutScreen;
import una.android.rapimoncha.entidades.MenuVItem;

public class Recursos {
	public static String SW_IP_ADRESS = "192.168.10.138";
	public static String SW_NU_PORT = ":8080/";
	public static String SW_NO_PROTOCOL = "http://";
	public static String SW_URL_WEBSERVICESERVER = "sites/Hidden_pages_chesdev/rapimoncha/trunk/classes/ln/";
	public static int SW_CO_EXITO = 2;
	public static int SW_CO_ERROR = 3;

	private static ArrayList<MenuVItem> menuprincipal;
	private static ArrayList<MenuVItem> menu_itemssecundarios;
	private static ArrayList<MenuVItem> menu_itemsterciarios;

	public static String getWebServerConnection() {
		return SW_NO_PROTOCOL + SW_IP_ADRESS + SW_NU_PORT
				+ SW_URL_WEBSERVICESERVER;
	}

	public static String getWebServiceComercio() {
		return SW_NO_PROTOCOL + SW_IP_ADRESS + SW_NU_PORT
				+ SW_URL_WEBSERVICESERVER + "Comercio.php";
	}

	public static String getWebServiceProducto() {
		return SW_NO_PROTOCOL + SW_IP_ADRESS + SW_NU_PORT
				+ SW_URL_WEBSERVICESERVER + "Producto.php";
	}

	public static String getWebServiceUsuario() {
		return SW_NO_PROTOCOL + SW_IP_ADRESS + SW_NU_PORT
				+ SW_URL_WEBSERVICESERVER + "Usuario.php";
	}

	public static String getWebServiceCategoriasComercio() {
		return SW_NO_PROTOCOL + SW_IP_ADRESS + SW_NU_PORT
				+ SW_URL_WEBSERVICESERVER + "CategoriaComercio.php";
	}
	public static String getWebServiceCalificacion() {
		return SW_NO_PROTOCOL + SW_IP_ADRESS + SW_NU_PORT
				+ SW_URL_WEBSERVICESERVER + "Calificacion.php";
	}	

	public static String getWebServiceSolicitudesComercio() {
		return SW_NO_PROTOCOL + SW_IP_ADRESS + SW_NU_PORT
				+ SW_URL_WEBSERVICESERVER + "SolicitudCliente.php";
	}

	public static void validateSession(Activity activity) {
		if (activity != null) {
			SharedPreferences sharedPref = activity.getApplication()
					.getSharedPreferences("rapimoncha", Context.MODE_PRIVATE);
			int idcomercio = sharedPref.getInt("idcomercio", 0);
			Log.i("Valor a qui",idcomercio+" <<<<<<<");
			if (idcomercio == 0) {
				Log.i("Valor a quiuser",idcomercio+" <<<<<<<entrado");
				activity.finish();
			}
			String username = sharedPref.getString(activity.getResources()
					.getString(R.string.function_userloginstatus_usernamekey),
					null);
			String status = sharedPref.getString(activity.getResources()
					.getString(R.string.function_userloginstatus_statuskey),
					null);
			String exitstatus = sharedPref.getString(activity.getResources()
					.getString(R.string.function_userloginstatus_exitkey),
					null);

			Log.i("Valor a quiuser",username+status+" <<<<<<<");
			if ((username == null || status == null)&&exitstatus!=null) {
				Log.i("Valor a quiuser",username+status+" <<<<<<< entrado");
				activity.finish();
			}
			if(exitstatus!=null){
				activity.finish();
			}

		}else{
			Log.i("Valor a qui"," *<<<<<<< es nula");
		}
	}

	public static ArrayList<MenuVItem> getMenuPrincipal(Activity activity) {
		if (menuprincipal == null) {
			Resources r = activity.getResources();
			menuprincipal = new ArrayList<MenuVItem>();
			MenuVItem menu_home = new MenuVItem(
					r.getString(R.string.menu_principal_vertical_home),
					R.drawable.icon_menu_home, Main.class);
			menuprincipal.add(menu_home);
			MenuVItem menu_promociones = new MenuVItem(
					r.getString(R.string.menu_principal_vertical_promociones),
					R.drawable.icon_menu_promotion, null);
			menuprincipal.add(menu_promociones);
			MenuVItem menu_mipagina = new MenuVItem(
					r.getString(R.string.menu_principal_vertical_mipagina),
					R.drawable.icon_menu_homepage, null);
			menuprincipal.add(menu_mipagina);
			MenuVItem menu_productos = new MenuVItem(
					r.getString(R.string.menu_principal_vertical_productos),
					R.drawable.icon_menu_product, ListadoProductosScreen.class);
			menuprincipal.add(menu_productos);
			MenuVItem menu_subscriptores = new MenuVItem(
					r.getString(R.string.menu_principal_vertical_subscriptores),
					R.drawable.icon_menu_subscribers, null);
			menuprincipal.add(menu_subscriptores);
			MenuVItem menu_solicitudes = new MenuVItem(
					r.getString(R.string.menu_principal_vertical_solicitudes),
					R.drawable.icon_menu_solicitud,
					ListadoSolicitudesScreen.class);
			menuprincipal.add(menu_solicitudes);
			MenuVItem menu_configuracion = new MenuVItem(
					r.getString(R.string.menu_principal_vertical_configuracion),
					R.drawable.icon_tool, ListadoConfiguracionesScreen.class);
			menuprincipal.add(menu_configuracion);

			MenuVItem menu_switch = new MenuVItem(
					r.getString(R.string.menu_principal_vertical_cambiarcuenta),
					R.drawable.icon_switch, WelcomeScreen.class);
			menuprincipal.add(menu_switch);
			MenuVItem menu_acercade = new MenuVItem(
					r.getString(R.string.menu_principal_vertical_acercade),
					R.drawable.icon_info, AcercaDeScreen.class);
			menuprincipal.add(menu_acercade);
			MenuVItem menu_logout = new MenuVItem(
					r.getString(R.string.menu_principal_vertical_logout),
					R.drawable.icon_logout, LogoutScreen.class);
			menuprincipal.add(menu_logout);
			MenuVItem menu_exit = new MenuVItem(
					r.getString(R.string.menu_principal_vertical_exit),
					R.drawable.icon_exit, ExitScreen.class);
			menuprincipal.add(menu_exit);

		}

		return menuprincipal;
	}
	
	public static ArrayList<MenuVItem> getMenuItemsSecundarios(Activity activity) {
		if (menu_itemssecundarios == null) {
			Resources r = activity.getResources();
			menu_itemssecundarios = new ArrayList<MenuVItem>();
			MenuVItem menu_home = new MenuVItem(
					r.getString(R.string.menu_principal_vertical_home),
					R.drawable.icon_menu_home, Main.class);
			menu_itemssecundarios.add(menu_home);
			MenuVItem menu_productos = new MenuVItem(
					r.getString(R.string.menu_principal_vertical_productos),
					R.drawable.icon_menu_product, ListadoProductosScreen.class);
			menu_itemssecundarios.add(menu_productos);
			MenuVItem menu_solicitudes = new MenuVItem(
					r.getString(R.string.menu_principal_vertical_solicitudes),
					R.drawable.icon_menu_solicitud,
					ListadoSolicitudesScreen.class);
			menu_itemssecundarios.add(menu_solicitudes);

			MenuVItem menu_switch = new MenuVItem(
					r.getString(R.string.menu_principal_vertical_cambiarcuenta),
					R.drawable.icon_switch, WelcomeScreen.class);
			menu_itemssecundarios.add(menu_switch);
			MenuVItem menu_acercade = new MenuVItem(
					r.getString(R.string.menu_principal_vertical_acercade),
					R.drawable.icon_info, AcercaDeScreen.class);
			menu_itemssecundarios.add(menu_acercade);
			MenuVItem menu_logout = new MenuVItem(
					r.getString(R.string.menu_principal_vertical_logout),
					R.drawable.icon_logout, LogoutScreen.class);
			menu_itemssecundarios.add(menu_logout);
			MenuVItem menu_exit = new MenuVItem(
					r.getString(R.string.menu_principal_vertical_exit),
					R.drawable.icon_exit, ExitScreen.class);
			menu_itemssecundarios.add(menu_exit);

		}

		return menu_itemssecundarios;
	}	
	public static ArrayList<MenuVItem> getMenuItemsTerciarios(Activity activity) {
		if (menu_itemsterciarios == null) {
			Resources r = activity.getResources();
			menu_itemsterciarios = new ArrayList<MenuVItem>();
			MenuVItem menu_home = new MenuVItem(
					r.getString(R.string.menu_principal_vertical_home),
					R.drawable.icon_menu_home, Main.class);
			menu_itemsterciarios.add(menu_home);
			MenuVItem menu_productos = new MenuVItem(
					r.getString(R.string.menu_principal_vertical_productos),
					R.drawable.icon_menu_product, ListadoProductosScreen.class);
			menu_itemsterciarios.add(menu_productos);
			MenuVItem menu_solicitudes = new MenuVItem(
					r.getString(R.string.menu_principal_vertical_solicitudes),
					R.drawable.icon_menu_solicitud,
					ListadoSolicitudesScreen.class);
			menu_itemsterciarios.add(menu_solicitudes);
			MenuVItem menu_logout = new MenuVItem(
					r.getString(R.string.menu_principal_vertical_logout),
					R.drawable.icon_logout, LogoutScreen.class);
			menu_itemsterciarios.add(menu_logout);
			MenuVItem menu_exit = new MenuVItem(
					r.getString(R.string.menu_principal_vertical_exit),
					R.drawable.icon_exit, ExitScreen.class);
			menu_itemsterciarios.add(menu_exit);

		}

		return menu_itemsterciarios;
	}		
}
