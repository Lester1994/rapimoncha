<?php

  require_once '../../recursos/compartidos/inclusiones.php';
  require_once $RM_URL_LIBRARIES.'ceSQL.php';
  require_once $RM_URL_LIBRARIES.'conexion.php';
  require_once $RM_ENTITIES_DIR.'SolicitudCliente.php';
require_once $RM_ENTITIES_DIR.'Usuario.php';
require_once $RM_ENTITIES_DIR.'Producto.php';
require_once $RM_ENTITIES_DIR.'Usuario.php';
  require_once $RM_DATAACCESS_DIR.'SolicitudCliente.php';
 class LN_SolicitudCliente{
          function getSolicitudesClientes($idcomercio,$dbase){
              
              $pro=new DS_SolicitudCliente();
              $pro->getSolicitudesClientes($idcomercio,$dbase);
              //EN_Usuario
          }
 }

/*$v=new LN_SolicitudCliente();
$v->getSolicitudesClientes(1,$dbase);*/


?>