<?php

  require_once '../../recursos/compartidos/inclusiones.php';
  require_once $RM_URL_LIBRARIES.'ceSQL.php';
  require_once $RM_URL_LIBRARIES.'conexion.php';
  require_once $RM_ENTITIES_DIR.'Subscripcion.php';
require_once $RM_ENTITIES_DIR.'Usuario.php';
  require_once $RM_DATAACCESS_DIR.'Subscripcion.php';
 class LN_Subscripcion{
          function getSubscripcionesComercio($idcomercio,$dbase){
              
              $pro=new DS_Subscripcion();
              $pro->getSubscripcionesComercio($idcomercio,$dbase);
              //EN_Usuario
          }
 }

/*$v=new LN_Subscripcion();
$v->getSubscripcionesComercio(1,$dbase);*/


?>