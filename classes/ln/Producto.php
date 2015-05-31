<?php
    require_once '../../recursos/compartidos/inclusiones.php';
 require_once $RM_ENTITIES_DIR.'pruebacomercio.inc';  
require_once $RM_ENTITIES_DIR.'Producto.php';   
    require_once $RM_URL_LIBRARIES.'ceSQL.php';
    require_once $RM_URL_LIBRARIES.'conexion.php';
    require_once $RM_DATAACCESS_DIR.'Producto.php';
require_once $RM_DATAACCESS_DIR.'Galeria.php';
   $accion=$_POST["accion"];//$_POST["accion"];//'agregar_comercio';//$_POST["accion"];
   $data=$_POST["data"];//$_POST["data"];//$prueba12;//$_POST["data"];

    class LN_Producto{
        
      function agregarProductosComercio($comercio,$dbase){
               $ds_comer=new DS_Comercio();
               $ds_comer->agregarProductosComercio($comercio,$dbase);
      }
      function eliminarProductoComercio($idproducto,$idcomercio,$dbase){
               $ds_producto=new DS_Producto();
               $ds_producto->eliminarProductoComercio($idproducto,$idcomercio,$dbase);
      }     
        
      function getProductosComercio($idcomercio,$dbase){
          $ds_producto=new DS_Producto();
          $ds_producto->getProductosComercio($idcomercio,$dbase);
      }
      function getProductoComercio($idcomercio,$idproducto,$dbase){
          $ds_producto=new DS_Producto();
          $ds_producto->getProductoComercio($idcomercio,$idproducto,$dbase);
      }	
		
	function actualizarProductoComercio($producto,$idcomercio,$dbase){
		 $ds_producto=new DS_Producto();
		 $ds_producto->actualizarProductoComercio($producto,$idcomercio,$dbase);
    }
}

   // $ln_comer=new LN_Producto();
//$resp_finale=;
//echo "el id del comercio recien ingresado es ".$resp_final["comercio"]["idcomercio"];
//echo '<br><br><br><br>RESPUESTA FINAL <br><br>'. json_encode($ln_comer->getProductosComercio(1,$dbase));

if(isset($accion)&&(strlen($accion)>3)){
		switch($accion){

			case 'agregar_producto':
					$en_comer=new EN_Comercio();
					$en_comer->generarComercio($data);
					$comer=new LN_Comercio();
					$comer->agregarProductosComercio($en_comer,$dbase);			
			break;

			case 'obtener_productos_comercio':
					$pro=new LN_Producto();
					$pro->getProductosComercio($data,$dbase);
			break;
			case 'obtener_producto_comercio':
					$pro=new LN_Producto();
					  $extra=$_POST["extra"];
					$pro->getProductoComercio($data,$extra,$dbase);
			break;		
			case 'actualizar_producto':
			$pro=new LN_Producto();
			$en=new EN_Producto();
			$en->generarProducto($data);
			  $extra=$_POST["extra"];
			$pro->actualizarProductoComercio($en,$extra,$dbase);
			break;
			case 'eliminar_producto':
			$pro=new LN_Producto();
			  $extra=$_POST["extra"];
			$pro->eliminarProductoComercio($data,$extra,$dbase);
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
}
?>