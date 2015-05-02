package una.android.rapimoncha.recursos;

public class Recursos {
	public static String SW_IP_ADRESS="192.168.43.127";
	public static String SW_NU_PORT=":8080/";
	public static String SW_NO_PROTOCOL="http://";
	public static String SW_URL_WEBSERVICESERVER="sites/Hidden_pages_chesdev/rapimoncha/trunk/classes/ln/";
	public static int SW_CO_EXITO=2;
	public static int SW_CO_ERROR=3;
	public static String getWebServerConnection(){
		return SW_NO_PROTOCOL+SW_IP_ADRESS+SW_NU_PORT+SW_URL_WEBSERVICESERVER;
	}
	public static String getWebServiceComercio(){
		return SW_NO_PROTOCOL+SW_IP_ADRESS+SW_NU_PORT+SW_URL_WEBSERVICESERVER+"Comercio.php";
	}	
	public static String getWebServiceProducto(){
		return SW_NO_PROTOCOL+SW_IP_ADRESS+SW_NU_PORT+SW_URL_WEBSERVICESERVER+"Producto.php";
	}	
	public static String getWebServiceUsuario(){
		return SW_NO_PROTOCOL+SW_IP_ADRESS+SW_NU_PORT+SW_URL_WEBSERVICESERVER+"Usuario.php";
	}		
}
