<?php
  require_once '../../recursos/compartidos/inclusiones.php';
  require_once $RM_URL_LIBRARIES.'ceSQL.php';
  require_once $RM_URL_LIBRARIES.'conexion.php';
  require_once $RM_ENTITIES_DIR.'CategoriaComercio.php';
  require_once $RM_DATAACCESS_DIR.'CategoriaComercio.php';

class LN_CategoriaComercio{
    
    function getCategoriasComercio($idcomercio,$dbase){
          $cat=new DS_CategoriaComercio();
          $cat->getCategoriasComercio($idcomercio,$dbase);
    }
    function getNoCategoriasComercio($idcomercio,$dbase){
          $cat=new DS_CategoriaComercio();
          $cat->getNoCategoriasComercio($idcomercio,$dbase);
    }
    
    
}

$v=new LN_CategoriaComercio();
$v->getNoCategoriasComercio(1,$dbase);
?>