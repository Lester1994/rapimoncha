<?php
require_once '../../recursos/compartidos/inclusiones.php';
require_once $RM_ENTITIES_DIR.'pruebausuario.inc';  
 $accion=$_POST["accion"];//'agregar_usuario';//$_POST["accion"];
 $data=$_POST["data"];//$pruebavar2;//$_POST["data"];  


  require_once $RM_URL_LIBRARIES.'ceSQL.php';
  require_once $RM_URL_LIBRARIES.'conexion.php';
  require_once $RM_ENTITIES_DIR.'Usuario.php';
  require_once $RM_DATAACCESS_DIR.'Usuario.php';
 class LN_Usuario{
          function agregarUsuario($usuario,$dbase){
              
              $us=new DS_Usuario();
              $us->agregarUsuario($usuario,$dbase);
              
          }
 }

if(isset($accion)&&(strlen($accion)>3)){
		switch($accion){

			case 'agregar_usuario':
					$en_user=new EN_Usuario();
					$en_user->generarUsuario($data);
					$us=new LN_Usuario();
					$us->agregarUsuario($en_user,$dbase);
			
			break;

		}	
	
}else{
	$resp=array();
	$resp['codigo']=2;
	$resp['mensaje']='Solicitud incorrecta';
	echo json_encode($resp);
	return;
}



?>