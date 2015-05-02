<?php
    require_once '../../recursos/compartidos/inclusiones.php';
 require_once $RM_ENTITIES_DIR.'pruebacomercio.inc';  
require_once $RM_ENTITIES_DIR.'Producto.php';   
    require_once $RM_URL_LIBRARIES.'ceSQL.php';
    require_once $RM_URL_LIBRARIES.'conexion.php';
    require_once $RM_DATAACCESS_DIR.'Producto.php';
    class LN_Producto{
        
      function agregarProductosComercio($comercio,$dbase){
               $ds_comer=new DS_Comercio();
               $ds_comer->agregarProductosComercio($comercio,$dbase);
      }
      function eliminarProductosComercio($idproducto,$idcomercio,$dbase){
               $ds_producto=new DS_Producto();
               echo $ds_producto->eliminarProductoComercio($idproducto,$idcomercio,$dbase);
      }     
        
      function getProductosComercio($idcomercio,$dbase){
          $ds_producto=new DS_Producto();
          $ds_producto->getProductosComercio($idcomercio,$dbase);
      }
    }

   // $ln_comer=new LN_Producto();
//$resp_finale=;
//echo "el id del comercio recien ingresado es ".$resp_final["comercio"]["idcomercio"];
//echo '<br><br><br><br>RESPUESTA FINAL <br><br>'. json_encode($ln_comer->getProductosComercio(1,$dbase));
?>