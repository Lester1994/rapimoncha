<?php

  require_once '../../recursos/compartidos/inclusiones.php';
  require_once $RM_URL_LIBRARIES.'ceSQL.php';
  require_once $RM_URL_LIBRARIES.'conexion.php';
  require_once $RM_ENTITIES_DIR.'SolicitudCliente.php';
require_once $RM_ENTITIES_DIR.'Usuario.php';
require_once $RM_ENTITIES_DIR.'Producto.php';
require_once $RM_ENTITIES_DIR.'Usuario.php';
  require_once $RM_DATAACCESS_DIR.'SolicitudCliente.php';
   $accion=$_POST["accion"];//$_POST["accion"];//'agregar_comercio';//$_POST["accion"];
   $data=$_POST["data"];//$_POST["data"];//$prueba12;//$_POST["data"];

 class LN_SolicitudCliente{
          function getSolicitudesClientes($idcomercio,$dbase){
              
              $pro=new DS_SolicitudCliente();
              $pro->getSolicitudesClientes($idcomercio,$dbase);
              //EN_Usuario
          }
	 
	 		function resolverSolicitudCliente($solicitud,$dbase){
				  
              $pro=new DS_SolicitudCliente();
              $pro->resolverSolicitudCliente($solicitud,$dbase);
			}
	 		function eliminarSolicitudCliente($solicitud,$dbase){
				  
              $pro=new DS_SolicitudCliente();
              $pro->eliminarSolicitudCliente($solicitud,$dbase);
			}	 
 }

if(isset($accion)&&(strlen($accion)>3)){
		switch($accion){

			case 'obtener_solicitudes_comercio':
					$pro=new LN_SolicitudCliente();
					$pro->getSolicitudesClientes($data,$dbase);
			break;
			case 'resolver_solicitud_comercio':
				$soli=new EN_SolicitudCliente();
				$soli->generarSolicitudCliente($data);
			    $pro=new LN_SolicitudCliente();
				$pro->resolverSolicitudCliente($soli,$dbase);
			
			break;
			case 'eliminar_solicitud_comercio':
				$soli=new EN_SolicitudCliente();
				$soli->generarSolicitudCliente($data);
			    $pro=new LN_SolicitudCliente();
				$pro->eliminarSolicitudCliente($soli,$dbase);			
			break;
			default:
					$resp=array();
					$resp['codigo']=3;
					$resp['mensaje']='Ninguna opcion correcta';
					echo json_encode($resp);
			break;

		}	
	
}else{
	$resp=array();
	$resp['codigo']=3;
	$resp['mensaje']='Solicitud incorrecta';
	echo json_encode($resp);
	return;
		/*$pro=new LN_SolicitudCliente();
					$pro->getSolicitudesClientes(31,$dbase);*/
}


?>