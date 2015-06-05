<?php

    require_once '../../recursos/compartidos/inclusiones.php';
 require_once $RM_ENTITIES_DIR.'pruebacomercio.inc';   
require_once $RM_ENTITIES_DIR.'Comercio.php';   
    require_once $RM_URL_LIBRARIES.'ceSQL.php';
    require_once $RM_URL_LIBRARIES.'conexion.php';
    require_once $RM_DATAACCESS_DIR.'Comercio.php';

   $accion=$_POST["accion"];//'agregar_comercio';//$_POST["accion"];
   $data=$_POST["data"];//$prueba12;//$_POST["data"];
	//echo $data;
    class LN_Comercio {
        function agregarComercio($comercio,$dbase){
               $ds_comer=new DS_Comercio();
               $ds_comer->agregarComercio($comercio,$dbase);
        }
        function actualizarComercio($comercio,$dbase){
               $ds_comer=new DS_Comercio();
               $ds_comer->actualizarComercio($comercio,$dbase);
        }		
		
        
       function agregarProductosComercio($comercio,$dbase){
               $ds_comer=new DS_Comercio();
               $ds_comer->agregarProductosComercio($comercio,$dbase);
        }
       function agregarPromocionesComercio($comercio,$dbase){
               $ds_comer=new DS_Comercio();
               $ds_comer->agregarPromocionesComercio($comercio,$dbase);
        }    
        
        function actualizarCategoriasComercio($comercio,$dbase){
               $ds_comer=new DS_Comercio();
               $ds_comer->actualizarCategoriasComercio($comercio,$dbase);
        }   
              function actualizarGeolocalizacionComercio($comercio,$dbase){
               $ds_comer=new DS_Comercio();
               $ds_comer->actualizarGeolocalizacionComercio($comercio,$dbase);
        }   
   function getComercio($idcomercio,$dbase){
               $ds_comer=new DS_Comercio();
               $ds_comer->getComercio($idcomercio,$dbase);
        }   
   function getComercios($dbase){
               $ds_comer=new DS_Comercio();
               $ds_comer->getComercio2($dbase);
    }   		
        
    }

if(isset($accion)&&(strlen($accion)>3)){
		switch($accion){
			case 'obtener_comercios':
					$comer=new LN_Comercio();
					$comer->getComercios($dbase);
			break;
			case 'agregar_comercio':
					$en_comer=new EN_Comercio();
					$en_comer->generarComercio($data);
					$comer=new LN_Comercio();
					$comer->agregarComercio($en_comer,$dbase);
			
			break;
			case 'modificar_comercio':
			$en_comer=new EN_Comercio();
					$en_comer->generarComercio($data);
					$comer=new LN_Comercio();
					$comer->actualizarComercio($en_comer,$dbase);
			break;
			case 'agregar_producto':
					$en_comer=new EN_Comercio();
					$en_comer->generarComercio($data);
					$comer=new LN_Comercio();
					$comer->agregarProductosComercio($en_comer,$dbase);			
			break;
			case 'modificar_categorias':
					$en_comer=new EN_Comercio();
					$en_comer->generarComercio($data);
					$comer=new LN_Comercio();
					$comer->actualizarCategoriasComercio($en_comer,$dbase);			
			break;
			case 'modificar_geolocalizacion':
					$en_comer=new EN_Comercio();
					$en_comer->generarComercio($data);
					$comer=new LN_Comercio();
					$comer->actualizarGeolocalizacionComercio($en_comer,$dbase);			
			break;
			case 'obtener_comercio':
					$comer=new LN_Comercio();
					$comer->getComercio($data,$dbase);
			break;
			default:
					$resp=array();
					$resp['codigo']=2;
					$resp['mensaje']='Ninguna opcion correcta';
					echo json_encode($resp);
			break;

		}	
	
}else{
	/*$resp=array();
	$resp['codigo']=2;
	$resp['mensaje']='Solicitud incorrecta';
	echo json_encode($resp);*/
	$comer=new LN_Comercio();
	$comer->getComercios($dbase);
	return;
}



//$ln_comer=new LN_Comercio();
//$resp_finale=;
//echo "el id del comercio recien ingresado es ".$resp_final["comercio"]["idcomercio"];
//echo '<br><br><br><br>RESPUESTA FINAL <br><br>'. json_encode($ln_comer->actualizarGeolocalizacionComercio($comer,$dbase));
//$ln_comer->getComercio(1,$dbase);

?>