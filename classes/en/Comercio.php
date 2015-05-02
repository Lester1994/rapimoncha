<?php 
   // require_once '../../recursos/compartidos/inclusiones.php';
    require_once $RM_ENTITIES_DIR.'Galeria.php';
    require_once $RM_ENTITIES_DIR.'Geolocalizacion.php';
    require_once $RM_ENTITIES_DIR.'Preferencias.php';
    require_once $RM_ENTITIES_DIR.'CategoriaComercio.php';
    require_once $RM_ENTITIES_DIR.'Usuario.php';
    require_once $RM_ENTITIES_DIR.'Subscripcion.php';
    require_once $RM_ENTITIES_DIR.'Producto.php';
    require_once $RM_ENTITIES_DIR.'Promocion.php';
    require_once $RM_ENTITIES_DIR.'SolicitudCliente.php';


class EN_Comercio{
var $idComercio;
var $noComercio;
var $diComercio;
var $prComercio;
var $t1Comercio;
var $t2Comercio;
var $emComercio;
var $imagenes;
var $geolocalizaciones;
var $preferencias;
var $clasificaciones;
var $subcripciones;
var $promociones;
var $productos;
var $solicitudes;
    
function setImagenes($imagenes) { $this->imagenes = $imagenes; }
function getImagenes() { return $this->imagenes; }
function setGeolocalizaciones($geolocalizaciones) { $this->geolocalizaciones = $geolocalizaciones; }
function getGeolocalizaciones() { return $this->geolocalizaciones; }
function setPreferencias($preferencias) { $this->preferencias = $preferencias; }
function getPreferencias() { return $this->preferencias; }
function setClasificaciones($clasificaciones) { $this->clasificaciones = $clasificaciones; }
function getClasificaciones() { return $this->clasificaciones; }
function setSubcripciones($subcripciones) { $this->subcripciones = $subcripciones; }
function getSubcripciones() { return $this->subcripciones; }
function setIdComercio($idComercio) { $this->idComercio = $idComercio; }
function getIdComercio() { return $this->idComercio; }
function setNoComercio($noComercio) { $this->noComercio = $noComercio; }
function getNoComercio() { return $this->noComercio; }
function setDiComercio($diComercio) { $this->diComercio = $diComercio; }
function getDiComercio() { return $this->diComercio; }
function setPrComercio($prComercio) { $this->prComercio = $prComercio; }
function getPrComercio() { return $this->prComercio; }
function setT1Comercio($t1Comercio) { $this->t1Comercio = $t1Comercio; }
function getT1Comercio() { return $this->t1Comercio; }
function setT2Comercio($t2Comercio) { $this->t2Comercio = $t2Comercio; }
function getT2Comercio() { return $this->t2Comercio; }
function setEmComercio($emComercio) { $this->emComercio = $emComercio; }
function getEmComercio() { return $this->emComercio; }
function setPromociones($promociones) { $this->promociones = $promociones; }
function getPromociones() { return $this->promociones; }
function setProductos($productos) { $this->productos = $productos; }
function getProductos() { return $this->productos; }
function setSolicitudes($solicitudes) { $this->solicitudes = $solicitudes; }
function getSolicitudes() { return $this->solicitudes; }    

 function addPromocion($promocion=null){
     if($promocion!=null){
          if($this->promociones==null){
              $this->promociones=array();   
          }
                
        array_push($this->promociones,$promocion);
          return true;
        }else{
             return false;   
        }
 } 
 function addProducto($producto=null){
     if($producto!=null){
          if($this->productos==null){
              $this->productos=array();   
          }
                
        array_push($this->productos,$producto);
          return true;
        }else{
             return false;   
        }
 } 
 function addSolicitud($solicitud=null){
     if($solicitud!=null){
          if($this->solicitudes==null){
              $this->solicitudes=array();   
          }
                
        array_push($this->solicitudes,$solicitud);
          return true;
        }else{
             return false;   
        }
 }     
function addClasificacion($clasificacion){
  //  echo '<br>llamado agregar clasificacion<br>';
     if($clasificacion!=null){
          if($this->clasificaciones==null){
              $this->clasificaciones=array();   
          }
                
        array_push($this->clasificaciones,$clasificacion);
          return true;
        }else{
             return false;   
        }
 } 

 function addImagen($imagen=null){
     if($imagen!=null){
          if($this->imagenes==null){
              $this->imagenes=array();   
          }
                
        array_push($this->imagenes,$imagen);
          return true;
        }else{
             return false;   
        }
 }     
 function addPreferencia($preferencia=null){
     if($preferencia!=null){
          if($this->preferencias==null){
              $this->preferencias=array();   
          }
                
        array_push($this->preferencias,$preferencia);
          return true;
        }else{
             return false;   
        }
 }     
 function addGeolocalizacion($geolocalizacion=null){
   //  echo '<br>llamado agregar geo<br>';
     if($geolocalizacion!=null){
          if($this->geolocalizaciones==null){
              $this->geolocalizaciones=array();   
          }
                
        array_push($this->geolocalizaciones,$geolocalizacion);
          return true;
        }else{
             return false;   
        }
 }
    
 function addSubscripcion($subscripcion){
     if($subscripcion!=null){
          if($this->subcripciones==null){
             $this->subcripciones=array();   
          }
                
        array_push($this->subcripciones,$subscripcion);
          return true;
        }else{
             return false;   
        }
 }    
    
function generarComercio($json){
    if(isset($json)&&!empty($json)){
         
             $data = json_decode($json,true);
         
            $comer=$data['comercio'];
            
            $this->setIdComercio($comer[0]['idcomercio']);
            $this->setNoComercio($comer[0]['nocomercio']);
            $this->setDiComercio($comer[0]['dicomercio']);
            $this->setPrComercio($comer[0]['prcomercio']);
            $this->setT1Comercio($comer[0]['t1comercio']);
            $this->setT2Comercio($comer[0]['t2comercio']);
            $this->setEmComercio($comer[0]['emcomercio']);
           
            $imgaux=$comer[0]['imagenes'];
            
            foreach($imgaux as $imagen){
              //  echo var_dump($imagen);
                   $imagen1=new EN_Galeria();
                   $imagen1->setIdImagen($imagen['idimagen']);
                   $imagen1->setDtImagen($imagen['dtimagen']);
                   $this->addImagen($imagen1);
            }
            $geolocalizaci=$comer[0]['geolocalizaciones'];
            
            foreach($geolocalizaci as $geolocaliza){
                   $geo=new EN_Geolocalizacion();
                   $geo->setIdlocalizacion($geolocaliza['idlocalizacion']);
                   $geo->setLatitud($geolocaliza['lalocalizacion']);
                   $geo->setLongitud($geolocaliza['lnlocalizacion']);
                   $this->addGeolocalizacion($geo);
            }                 

            $prefaux=$comer[0]['preferencias'];
            
            foreach($prefaux as $pref){
                   $pref1=new EN_Preferencias();
                   $pref1->setIdPreferencia($pref['idpreferencia']);
                   $pref1->setIsContactoSinSubscripcion($pref['contactosinsu']);
                   $pref1->setIsVerProductosSinSubscrib($pref['productosinsu']);
                   $pref1->setHasServicioExpress($pref['servicioexpre']);
                   
                   $this->addPreferencia($pref1);
            }  
                 
            $clasifaux=$comer[0]['clasificaciones'];
            
            foreach($clasifaux as $element){
              
                       $clasificacion1=new EN_CategoriaComercio();
                       $clasificacion1->setIdCategoria($element['idclasificacion']);
                       $clasificacion1->setNoCategoria($element['noclasificacion']);
                  

              $this->addClasificacion($clasificacion1);;    
                    
                
                
            }
            //subscripciones
            $suscrifaux=$comer[0]['subscripciones'];
            
            foreach($suscrifaux as $subscripcion){
                   $subscripcion1=new EN_Subscripcion();
                   $subscripcion1->setIdSubscripcion($subscripcion['idsubscripcion']);
                   $subscripcion1->setIsEnviarPromocion($subscripcion['enviarpromocion']);
                    $user=new EN_Usuario();
               // echo var_dump($subscripcion['usuario']['idusuario']);
                    $user->setIdUsuario($subscripcion['usuario']['idusuario']);
                    $user->setNoUsuario($subscripcion['usuario']['nousuario']);
                    $user->setA1Usuario($subscripcion['usuario']['a1usuario']);
                    $user->setA2Usuario($subscripcion['usuario']['a2usuario']);
                    $user->setFeNacimie($subscripcion['usuario']['fenacimiento']);
                    $user->setSeUsuario($subscripcion['usuario']['sexo']);
                    $subscripcion1->setUsuario($user);
                   // echo var_dump($subscripcion1);
                    $this->addSubscripcion($subscripcion1);
            }
          //productos
            $prodaux=$comer[0]['productos'];
            
            foreach($prodaux as $producto){
              //  echo var_dump($imagen);
                   $producto1=new EN_Producto();
                   $producto1->setIdProducto($producto['idproducto']);
                   $producto1->setNoProducto($producto['noproducto']);
                   $producto1->setDeProducto($producto['deproducto']);
                   $producto1->setPrProducto($producto['precio']);
                   
                   $imgaux=$producto['imagenes'];

                    foreach($imgaux as $imagen){
                      //  echo var_dump($imagen);
                           $imagen1=new EN_Galeria();
                           $imagen1->setIdImagen($imagen['idimagen']);
                           $imagen1->setDtImagen($imagen['dtimagen']);
                           $producto1->addImagen($imagen1);
                    }      
             //   echo count($producto1->getImagenes()).'es la cantidad de imagenes de este producto';
                   $this->addProducto($producto1);
            }
        
            //solicitudes
            $solicitaux=$comer[0]['solicitudes'];
            
            foreach($solicitaux as $solicitud){
              //  echo var_dump($imagen);
                   $solicitud1=new EN_SolicitudCliente();
                   $solicitud1->setIdSolicitud($solicitud['idsolicitud']);
                   $solicitud1->setFeSolicitud($solicitud['fechasolicitud']);
                   $solicitud1->setEsSolicitud($solicitud['estadosolicitud']);
                   $solicitud1->setEHastaSoli($solicitud['esperohasta']);
                   $solicitud1->setCoFInalSoli($solicitud['costofinal']);
                
                    $user=new EN_Usuario();
                    $user->setIdUsuario($solicitud['usuario']['idusuario']);
                    $user->setNoUsuario($solicitud['usuario']['nousuario']);
                    $user->setA1Usuario($solicitud['usuario']['a1usuario']);
                    $user->setA2Usuario($solicitud['usuario']['a2usuario']);
                    $user->setFeNacimie($solicitud['usuario']['fenacimiento']);
                    $user->setSeUsuario($solicitud['usuario']['sexo']);
                    $solicitud1->setUsuario($user);
                    
                    $detalles=$solicitud["detallessolicitud"];
                    
                    foreach($detalles as $producto){
                      //  echo var_dump($imagen);
                           $producto1=new EN_Producto();
                           $producto1->setIdProducto($producto['idproducto']);
                           $producto1->setNoProducto($producto['noproducto']);
                           $producto1->setPrProducto($producto['deproducto']);
                           $producto1->setDeProducto($producto['precio']);
                           $solicitud1->addDetalle($producto1);
                    }
                  
                
                   $this->addSolicitud($solicitud1);
            }

            $promaux=$comer[0]['promociones'];
            
            foreach($promaux as $promocion){
                   $promocion1=new EN_Promocion();
                   $promocion1->setIdPromocion($promocion['idpromocion']);
                   $promocion1->setFechaVencimiento($promocion['fechavencimiento']);
                   $promocion1->setDescripcion($promocion['descripcion']);
                   $promocion1->setTitulo($promocion['titulo']);
                  $promocion1->setFechaPublicacion($promocion['fechapublicacion']);
                 $this->addPromocion($promocion1);
            }          
        
    }
}
}


//$comer=new EN_Comercio();
//$comer->generarComercio($var2);
/*echo '<br>Iniciando ..........................</br>';
//echo $comer->getPrComercio();
foreach($comer->getClasificaciones() as $aux){
  echo '<br>'.$aux->getNoCategoria().'<<<<<br>';   
}

foreach($comer->getImagenes() as $aux){
  echo '<br>imagen '.$aux->getDtImagen().'<<<<<br>';   
}
echo '<br>probando subscriptores<br>';

foreach($comer->getSubcripciones() as $aux){
    echo '<br>id:  '.$aux->getIdSubscripcion();
    echo '<br>enviarpromo:  '.$aux->getIsEnviarPromocion();
    $usuario=$aux->getUsuario();
    echo '<br>user:<br>';
    echo '<br>>:  '.$usuario->getIdUsuario();
    echo '<br>>:  '.$usuario->getNoUsuario();
    echo '<br>>:  '.$usuario->getA1Usuario();
    echo '<br>>:  '.$usuario->getA2Usuario();
    echo '<br>>:  '.$usuario->getFeNacimie();
      
}

echo '<br>probando solicitudes<br>';

foreach($comer->getSolicitudes() as $aux){
    echo '<br>id:  '.$aux->getIdSolicitud();
    echo '<br>fesolicitud:  '.$aux->getFeSolicitud();
    $usuario=$aux->getUsuario();
    echo '<br>user:<br>';
    echo '<br>>:  '.$usuario->getIdUsuario();
    echo '<br>>:  '.$usuario->getNoUsuario();
    echo '<br>>:  '.$usuario->getA1Usuario();
    echo '<br>>:  '.$usuario->getA2Usuario();
    echo '<br>>:  '.$usuario->getFeNacimie();
      
}

echo '<br>Promociones ..........................</br>';
//echo $comer->getPrComercio();
foreach($comer->getPromociones() as $aux){
  echo '<br>'.$aux->getDescripcion().'<<<<<br>';   
}*/


//echo utf8_encode(json_encode($comer));
                 
?>