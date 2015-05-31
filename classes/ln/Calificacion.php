<?php
  require_once '../../recursos/compartidos/inclusiones.php';
  require_once $RM_URL_LIBRARIES.'ceSQL.php';
  require_once $RM_URL_LIBRARIES.'conexion.php';
  require_once $RM_ENTITIES_DIR.'Usuario.php';
require_once $RM_ENTITIES_DIR.'Calificacion.php';
  require_once $RM_DATAACCESS_DIR.'Calificacion.php';
  
  $accion=$_POST["accion"];
  $data=$_POST["data"];//$_POST["data"];//$prueba12;//$_POST["data"];
/*$accion='registrar_calificacion_app';
	$data='
	{
		"idccalificacion":"3",
	    "usuario": 
			{
				"idusuario": "100",
				"nousuario": "Radumanda",
				"a1usuario": "Corralila",
				"a2usuario": "Lomboide",
				"fenacimiento": "1996-12-12",
				"sexo": "M",
				"user": "key",
				"password": "ryozumo@gmail.com"
			},
		"utilidad":"5",
		"facilidad":"5",
		"estilo":"5",
		"soporte":"5",
		"notas":"mis notas actuales"
	}
	';*/


			

class LN_Calificacion{
    
      function agregarCalificacionApp($calificacion,$dbase){
          $cat=new DS_Calificacion();
          $cat->agregarCalificacionApp($calificacion,$dbase);
    }
   
        
}

if(isset($accion)&&(strlen($accion)>3)){
		switch($accion){

			case 'registrar_calificacion_app':
					$v=new LN_Calificacion();
					$calificacion=new EN_Calificacion();
				    $calificacion->generarCalificacion($data);
			///echo $data.' holaaa';
					$v->agregarCalificacionApp($calificacion,$dbase);
			
			
			break;
		}
	
}


?>