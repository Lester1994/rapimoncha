<?php

  require_once '../../recursos/compartidos/inclusiones.php';
  require_once $RM_URL_LIBRARIES.'ceSQL.php';
  require_once $RM_URL_LIBRARIES.'conexion.php';
  require_once $RM_ENTITIES_DIR.'Promocion.php';
  require_once $RM_DATAACCESS_DIR.'Promocion.php';
 class LN_Promocion{
          function getPromocionesComercio($idcomercio,$dbase){
              
              $pro=new DS_Promocion();
              $pro->getPromocionesComercio($idcomercio,$dbase);
              
          }
 }

/*$v=new LN_Promocion();
$v->getPromocionesComercio(1,$dbase);*/


?>