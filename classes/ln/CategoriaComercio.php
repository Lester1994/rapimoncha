<?php
  require_once '../../recursos/compartidos/inclusiones.php';
  require_once $RM_URL_LIBRARIES.'ceSQL.php';
  require_once $RM_URL_LIBRARIES.'conexion.php';
  require_once $RM_ENTITIES_DIR.'CategoriaComercio.php';
  require_once $RM_DATAACCESS_DIR.'CategoriaComercio.php';
$accion=$_POST["accion"];
class LN_CategoriaComercio{
    
    function getCategoriasComercio($idcomercio,$dbase){
          $cat=new DS_CategoriaComercio();
          $cat->getCategoriasComercio($idcomercio,$dbase);
    }
    function getNoCategoriasComercio($idcomercio,$dbase){
          $cat=new DS_CategoriaComercio();
          $cat->getNoCategoriasComercio($idcomercio,$dbase);
    }
    
    function getCategoriasComercios($dbase){
          $cat=new DS_CategoriaComercio();
          $cat->getCategoriasComercios($dbase);
    }
        
}

if(isset($accion)&&(strlen($accion)>3)){
		switch($accion){

			case 'obtener_categoriascomercios':
					$v=new LN_CategoriaComercio();
					$v->getCategoriasComercios($dbase);
			
			break;
		}
	
}


?>