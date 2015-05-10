<?php


    require_once $RM_DATAACCESS_DIR.'CategoriaComercio.php';
    require_once $RM_DATAACCESS_DIR.'Galeria.php';
    require_once $RM_DATAACCESS_DIR.'Geolocalizacion.php';
    require_once $RM_DATAACCESS_DIR.'Preferencias.php';
    require_once $RM_DATAACCESS_DIR.'Producto.php';
    require_once $RM_DATAACCESS_DIR.'Promocion.php';
require_once $RM_DATAACCESS_DIR.'Usuario.php';

    class DS_Comercio{
        function agregarPromocionesComercio($comercio,$db=null){
            $ds_promocion=new DS_Promocion();
            $respuestas=array();
             $sql= mysqli_query($db,"START TRANSACTION");
              while(mysqli_more_results($db)&&mysqli_next_result($db)); 
                 //registrar geolocalizacion
                 foreach($comercio->getPromociones() as $aux){
                   // $db->connect(false);
                    // echo '<br>agregar promocion<br>';
                     $res= $ds_promocion->agregarPromocionComercio($aux,$comercio->getIdComercio(),$db);
                      // echo '<br>el dump>>>'. var_dump($res).'<br>';
                    array_push($respuestas,$res);
                   
                    //$db->close();
                 } 
            
          $error_gene=false;
           //  echo "ya de hecho pase";
             foreach($respuestas as $resp){
              //   echo "<br>".var_dump($resp);
                 
                 if($resp["codigo"]!==2){
                     $error_gene=true;
                          
                 }
             }
            
            //preparar la respuesta final
              $resp_final=array();
            
             if($error_gene){
              //   echo "entreee rollback";
                  while(mysqli_more_results($db)&&mysqli_next_result($db));  
                 mysqli_query($db,"ROLLBACK");
                $resp_final["respuesta"]=array("codigo"=>3,"mensaje"=>"Proceso no completado (con errores)");
             }else{
               //   echo "entreee commit";
                while(mysqli_more_results($db)&&mysqli_next_result($db)); 
                 mysqli_query($db,"COMMIT"); 
                $resp_final["respuesta"]=array("codigo"=>2,"mensaje"=>"Proceso  completado (sin errores)");
             }
            
           //  echo "<br>Resultado finaaaaaaal................<br>";
           
           $resp_final["detalles"]=$respuestas;
            $resp_final["comercio"]=$comercio;
           // echo '<br><br><br><br>RESPUESTA FINAL <br><br>'. json_encode($resp_final);
        
        }

        function actualizarCategoriasComercio($comercio,$db=null){
            $ds_Clasificacion=new DS_CategoriaComercio();
            $respuestas=array();
             $sql= mysqli_query($db,"START TRANSACTION");
    
            $ds_Clasificacion->eliminarCategoriasComercio($comercio->getIdComercio(),$db);
               while(mysqli_more_results($db)&&mysqli_next_result($db)); 
                //  echo "<br>SECCION DE CATEGORIAS<br>";
                 //registrar categoria comercio
                 foreach($comercio->getClasificaciones() as $aux){
                  //  $db->connect(false);
                     $res=$ds_Clasificacion->agregarCategoriaComercio($aux,$comercio->getIdComercio(),$db);
                  //   echo '<br>el dump>>>'. var_dump($res).'<br>';
                    array_push($respuestas, $res);
                    
                   // $db->close();
                 } 
            
          $error_gene=false;
           //  echo "ya de hecho pase";
             foreach($respuestas as $resp){
              //   echo "<br>".var_dump($resp);
                 
                 if($resp["codigo"]!==2){
                     $error_gene=true;
                          
                 }
             }
            
            //preparar la respuesta final
              $resp_final=array();
            
             if($error_gene){
              //   echo "entreee rollback";
                  while(mysqli_more_results($db)&&mysqli_next_result($db));  
                 mysqli_query($db,"ROLLBACK");
                $resp_final["respuesta"]=array("codigo"=>3,"mensaje"=>" aqui Proceso no completado (con errores)");
             }else{
               //   echo "entreee commit";
                while(mysqli_more_results($db)&&mysqli_next_result($db)); 
                 mysqli_query($db,"COMMIT"); 
                $resp_final["respuesta"]=array("codigo"=>2,"mensaje"=>"Proceso  completado (sin errores)");
             }
            
           //  echo "<br>Resultado finaaaaaaal................<br>";
           
           $resp_final["detalles"]=$respuestas;
            $resp_final["comercio"]=$comercio;
          //  echo '<br><br><br><br>RESPUESTA FINAL <br><br>'. json_encode($resp_final);
        
        }        
        

  function actualizarGeolocalizacionComercio($comercio,$db=null){
            $ds_geolocalizacion=new DS_Geolocalizacion();
            $respuestas=array();
             $sql= mysqli_query($db,"START TRANSACTION");
    
            $ds_geolocalizacion->eliminarGeolocalizacionComercio($comercio->getIdComercio(),$db);
               while(mysqli_more_results($db)&&mysqli_next_result($db)); 
                //  echo "<br>SECCION DE CATEGORIAS<br>";
                 //registrar categoria comercio
                 foreach($comercio->getGeolocalizaciones() as $aux){
                  //  $db->connect(false);
                     $res=$ds_geolocalizacion->agregarGeolocalizacionComercio($aux,$comercio->getIdComercio(),$db);
                  //   echo '<br>el dump>>>'. var_dump($res).'<br>';
                    array_push($respuestas, $res);
                    
                   // $db->close();
                 } 
            
          $error_gene=false;
           //  echo "ya de hecho pase";
             foreach($respuestas as $resp){
              //   echo "<br>".var_dump($resp);
                 
                 if($resp["codigo"]!==2){
                     $error_gene=true;
                          
                 }
             }
            
            //preparar la respuesta final
              $resp_final=array();
            
             if($error_gene){
              //   echo "entreee rollback";
                  while(mysqli_more_results($db)&&mysqli_next_result($db));  
                 mysqli_query($db,"ROLLBACK");
                $resp_final["respuesta"]=array("codigo"=>3,"mensaje"=>" aqui Proceso no completado (con errores)");
             }else{
               //   echo "entreee commit";
                while(mysqli_more_results($db)&&mysqli_next_result($db)); 
                 mysqli_query($db,"COMMIT"); 
                $resp_final["respuesta"]=array("codigo"=>2,"mensaje"=>"Proceso geolocalizacion completado (sin errores)");
             }
            
           //  echo "<br>Resultado finaaaaaaal................<br>";
           
           $resp_final["detalles"]=$respuestas;
            $resp_final["comercio"]=$comercio;
          //  echo '<br><br><br><br>RESPUESTA FINAL <br><br>'. json_encode($resp_final);
        
        }                
        
        
        function agregarProductosComercio($comercio,$db=null){
            $ds_Producto=new DS_Producto();
            $respuestas=array();
             $sql= mysqli_query($db,"START TRANSACTION");
              while(mysqli_more_results($db)&&mysqli_next_result($db)); 
                 //registrar geolocalizacion
                 foreach($comercio->getProductos() as $aux){
                   // $db->connect(false);
                   //  echo '<br>agregarpeoducto<br>';
                     $res= $ds_Producto->agregarProductoComercio($aux,$comercio->getIdComercio(),$db);
                      // echo '<br>el dump>>>'. var_dump($res).'<br>';
                    array_push($respuestas,$res);
                   
                    //$db->close();
                 } 
            
          $error_gene=false;
           //  echo "ya de hecho pase";
             foreach($respuestas as $resp){
              //   echo "<br>".var_dump($resp);
                 
                 if($resp["codigo"]!==2){
                     $error_gene=true;
                          
                 }
             }
            
            //preparar la respuesta final
              $resp_final=array();
            
             if($error_gene){
              //   echo "entreee rollback";
                  while(mysqli_more_results($db)&&mysqli_next_result($db));  
                 mysqli_query($db,"ROLLBACK");
                 $resp_final["codigo"]=3;
				 $resp_final["mensaje"]="Proceso no completado (con errores)";
             }else{
               //   echo "entreee commit";
                while(mysqli_more_results($db)&&mysqli_next_result($db)); 
                 mysqli_query($db,"COMMIT"); 
                  $resp_final["codigo"]=2;
				 $resp_final["mensaje"]="Proceso  completado (sin errores)";
             }
            
           //  echo "<br>Resultado finaaaaaaal................<br>";
           
           $resp_final["detalles"]=$respuestas;
            $resp_final["comercio"]=$comercio;
          //  echo '<br><br><br><br>RESPUESTA FINAL <br><br>'. json_encode($resp_final);
        
        }
        
        
        
        function agregarComercio($comercio,$db=null){
       
            // $db->connect(true); 
             $ds_Galeria=new DS_Galeria();
             $ds_Clasificacion=new DS_CategoriaComercio();
             $ds_Geolocalizacion=new DS_Geolocalizacion();
             $ds_Preferencia=new DS_Preferencias();
				$ds_Usuario=new DS_Usuario();
             $respuestas=array();
            $SQLstr = "call rm_sp_registrar_comercio
                    ('" . $comercio->noComercio . "','" . $comercio->diComercio . "','" . $comercio->prComercio . "','" .
                        $comercio->t1Comercio . "','". $comercio->t2Comercio . "','" . 
                        $comercio->emComercio . "',@resultado);";
            
           // $sql= $db -> query("START TRANSACTION");
          $sql= mysqli_query($db,"START TRANSACTION");
           //  $db->cleanResult();  
           // $sql = $db -> query($SQLstr);
          $sql= mysqli_query($db,$SQLstr);
            while ($row=mysqli_fetch_row($sql)) {
                $msg = $row[0];
                $comercio->setIdComercio($msg);
               // echo "3l id del comercio es ".$comercio->getIdComercio();
                
            }  
           // $db->freeResult();          
           // $db->cleanResult();
           // $sql = $db -> query("select @resultado;");
           while(mysqli_more_results($db)&&mysqli_next_result($db)); 
           $sql= mysqli_query($db,"select @resultado;");
            /* if (!$sql) {
                    printf("Error: %s\n", mysqli_error($db));
                    exit();
             }*/
           //  echo "<br>voy por aquiiii<br>";
            while ($row = mysqli_fetch_row($sql)) {
                $msg = $row[0];
            }
             
             
           //  $db->cleanResult();
            // $db->close();
           
            $error = (substr($msg, 0, 2) === 'e:');
            if($error){
                $codigoresultado=3;
                $mensaje="No se pudo ingresar el comercio";
            }else{
                $codigoresultado=2;
                $mensaje="Se  pudo ingresar el comercio";
            }
            
           
             $xx=array("codigo"=>$codigoresultado,"msg"=>$mensaje);
            array_push($respuestas,$xx);
           //  echo var_dump($xx)."este es el primero<br><br><br>";
      
            
          
            //  echo "<br>................>>>>>".var_dump($respuestas)."----<br>este es el primero</br>";
            
             if(!$error){
               //   echo "<br>SECCION DE IMAGENES<br>";
                   //registrar galeria
                 foreach($comercio->getImagenes() as $aux){
                    // $db->connect(false);
                    // echo "respuesta imagenes".var_dump($respuestas);
                     $res= $ds_Galeria->agregarImagenComercio($aux,$comercio->getIdComercio(),$db);
                     //echo var_dump($respuestas)."<<<<<<<<<<";
                  //   echo '<br>el dump>>>'. var_dump($res).'<br>';
                     array_push($respuestas,$res);
                       // $db->close();
                    // echo "hasta aqui tenemos". count($respuestas);
                     
                 }
                //  echo "<br>SECCION DE CATEGORIAS<br>";
                 //registrar categoria comercio
                 foreach($comercio->getClasificaciones() as $aux){
                  //  $db->connect(false);
                     $res=$ds_Clasificacion->agregarCategoriaComercio($aux,$comercio->getIdComercio(),$db);
                  //   echo '<br>el dump>>>'. var_dump($res).'<br>';
                    array_push($respuestas, $res);
                    
                   // $db->close();
                 } 
               //  echo "ni aqui fue";
                //  echo "<br>SECCION DE GEOLOCALIZACION<br>";
                 //registrar geolocalizacion
                 foreach($comercio->getGeolocalizaciones() as $aux){
                   // $db->connect(false);
                     $res= $ds_Geolocalizacion->agregarGeolocalizacionComercio($aux,$comercio->getIdComercio(),$db);
                      // echo '<br>el dump>>>'. var_dump($res).'<br>';
                    array_push($respuestas,$res);
                   
                    //$db->close();
                 } 
                //  echo "<br>SECCION DE PREFERENCIAS<br>";
                  //registrar preferencia
                 foreach($comercio->getPreferencias() as $aux){
                   // $db->connect(false);
                     $res= $ds_Preferencia->agregarPreferenciaComercio($aux,$comercio->getIdComercio(),$db);
                 //    echo '<br>el dump>>>'. var_dump($res).'<br>';
                    array_push($respuestas,$res);
               //    echo "agregando preferencia >>----<<<<<br>";
                    //$db->close();
                 } 
				 
				 
				 //seccion usuario comercio
				 $userrr=$comercio->getUsuario();
                 $res= $ds_Usuario->agregarUsuarioComercio($userrr,$comercio->getIdComercio(),$db);
				 array_push($respuestas,$res);
                                
             }
             else{
           //   echo "<br>voy por aquiiii333<br>".var_dump($resultado).$msg;   
             }
             $error_gene=false;
           //  echo "ya de hecho pase";
             foreach($respuestas as $resp){
              //   echo "<br>".var_dump($resp);
                 
                 if($resp["codigo"]!==2){
                     $error_gene=true;
                          
                 }
             }
            
            //preparar la respuesta final
              $resp_final=array();
            
             if($error_gene){
              //   echo "entreee rollback";
                  while(mysqli_more_results($db)&&mysqli_next_result($db));  
                 mysqli_query($db,"ROLLBACK");
                $resp_final["codigo"]=3;
				 $resp_final["mensaje"]="Proceso no completado (con errores)";
             }else{
               //   echo "entreee commit";
                while(mysqli_more_results($db)&&mysqli_next_result($db)); 
                 mysqli_query($db,"COMMIT"); 
                $resp_final["codigo"]=2;
				 $resp_final["mensaje"]="Proceso  completado (sin errores)";
             }
            
           //  echo "<br>Resultado finaaaaaaal................<br>";
           
           $resp_final["detalles"]=$respuestas;
            $resp_final["comercio"]=$comercio;
            //echo "aqui paseeee!".var_dump($resp_final);
        
           // echo "no pude aqui ";
            
   echo json_encode($resp_final);
        } 
        
        
        
        
        
        
        
        
        
function getComercio($idcomercio,$db=null){
        $comercio_resp=new EN_Comercio();
            while(mysqli_more_results($db)&&mysqli_next_result($db)); 
            $SQLstr="SELECT  rm01idcomercio,  rm02nomco,  rm03dirco,  rm04proco,  rm05telco,  rm06te2co,  rm07emaco
                     FROM rm01comercios WHERE rm01idcomercio=".$idcomercio;
            
            $sql=mysqli_query($db,$SQLstr);
    
            while ($row=mysqli_fetch_row($sql)) {
                $comercio_resp->setIdComercio($row[0]);
                $comercio_resp->setNoComercio($row[1]);
                $comercio_resp->setDiComercio($row[2]);
                $comercio_resp->setPrComercio($row[3]);
                $comercio_resp->setT1Comercio($row[4]);
                $comercio_resp->setT2Comercio($row[5]);
                $comercio_resp->setEmComercio($row[6]);                    
            
            }
            while(mysqli_more_results($db)&&mysqli_next_result($db)); 
            $SQLstr="    SELECT  rm01idimagen,  rm02datoimagen,  rm01idcomercio FROM rm02galeriacomercio WHERE rm01idcomercio=".$idcomercio;
            
            $sql=mysqli_query($db,$SQLstr);            
    
            echo 'error 0001'.mysqli_error($db);
            while ($row=mysqli_fetch_row($sql)) {
              //  echo var_dump($imagen);
                   $imagen1=new EN_Galeria();
                   $imagen1->setIdImagen($row[0]);
                   $imagen1->setDtImagen($row[1]);
                   $comercio_resp->addImagen($imagen1);
            }
            while(mysqli_more_results($db)&&mysqli_next_result($db)); 
            $SQLstr="SELECT  rm01idlocalizacion,  rm02lacomercio,  rm03locomercio,  rm01idcomercio FROM rm13geolocalizacion
                     WHERE rm01idcomercio=".$idcomercio;
            
            $sql=mysqli_query($db,$SQLstr);            
    
            
            while ($row=mysqli_fetch_row($sql)) {
                   $geo=new EN_Geolocalizacion();
                   $geo->setIdlocalizacion($row[0]);
                   $geo->setLatitud($row[1]);
                   $geo->setLongitud($row[2]);
                   $comercio_resp->addGeolocalizacion($geo);
            }                 

            while(mysqli_more_results($db)&&mysqli_next_result($db)); 
            $SQLstr="SELECT  rm01idpreferencia,  rm03contactosinsub,  rm04producsinsub,  rm05serviexpres,  rm02idcomercio
                     FROM rm03preferenciacomercio WHERE rm02idcomercio=".$idcomercio;
            
            $sql=mysqli_query($db,$SQLstr);            
    
            echo 'error 0002'.mysqli_error($db);
            while ($row=mysqli_fetch_row($sql)) {
                   $pref1=new EN_Preferencias();
                   $pref1->setIdPreferencia($row[0]);
                   $pref1->setIsContactoSinSubscripcion($row[1]);
                   $pref1->setIsVerProductosSinSubscrib($row[2]);
                   $pref1->setHasServicioExpress($row[3]);
                   
                   $comercio_resp->addPreferencia($pref1);
            }  
    
    
            while(mysqli_more_results($db)&&mysqli_next_result($db)); 
            $SQLstr="call rm_sp_obtener_categoriascomercio(".$idcomercio.")";
            
            $sql=mysqli_query($db,$SQLstr);  
    
            while ($row=mysqli_fetch_row($sql)) {  
                       $clasificacion1=new EN_CategoriaComercio();
                       $clasificacion1->setIdCategoria($row[0]);
                       $clasificacion1->setNoCategoria($row[1]);
                  

              $comercio_resp->addClasificacion($clasificacion1);;      
            }
    
            //subscripciones
            while(mysqli_more_results($db)&&mysqli_next_result($db)); 
            $SQLstr="call rm_sp_obtener_subscripcionescomercio(".$idcomercio.")";
            
            $sql=mysqli_query($db,$SQLstr);  
            echo 'error 0003'.mysqli_error($db);
            while ($row=mysqli_fetch_row($sql)) {  
                   $subscripcion1=new EN_Subscripcion();
                   $subscripcion1->setIdSubscripcion($row[0]);
                   $subscripcion1->setIsEnviarPromocion($row[1]);   
                   $subscripcion1->setFechaSubscripcion($row[2]);   
                   $subscripcion1->setEstadoSubscripcion($row[3]);
                    $user=new EN_Usuario();
               echo 'error 0004'.mysqli_error($db);
                    $user->setIdUsuario($row[4]);
                    $user->setNoUsuario($row[5]);
                    $user->setA1Usuario($row[6]);
                    $user->setA2Usuario($row[7]);
                    $user->setFeNacimie($row[8]);
                    $user->setSeUsuario($row[9]);
                    $subscripcion1->setUsuario($user);
                   // echo var_dump($subscripcion1);
                    $comercio_resp->addSubscripcion($subscripcion1);
            }
          //productos inicio
            while(mysqli_more_results($db)&&mysqli_next_result($db)); 
               $SQLstr="SELECT rm01idproducto,
                    rm02noproducto,
                    rm03deproducto,
                    rm04peproducto
                FROM rm07productos where rm01idcomercio=".$idcomercio;
            
            $sql=mysqli_query($db,$SQLstr);
            
            if($sql){
                $productos=array();
             //obtener los datos
                 while ($row=mysqli_fetch_row($sql)) {
                     $produaux=new EN_Producto();
                     $produaux->setIdProducto($row[0]);
                     $produaux->setNoProducto($row[1]);
                     $produaux->setPrProducto($row[2]);
                     $produaux->setDeProducto($row[3]);
                     
                     array_push($productos,$produaux);

                 }
                
                
                foreach($productos as &$pr){
                    $SQLstr="SELECT rm01idimagen,    rm02datoimagen
FROM rm08galeriaproductos where rm01idproducto=".$pr->getIdProducto();
                      $sql=mysqli_query($db,$SQLstr);
            
                        if($sql){
                            $imagen=array();
                         //obtener los datos
                             while ($row=mysqli_fetch_row($sql)) {
                                  $imagen1=new EN_Galeria();
                                  $imagen1->setIdImagen($row[0]);
                                  $imagen1->setDtImagen($row[1]);
                                  $pr->addImagen($imagen1);

                             }

                        }

                }
               
                
            }
            
            $comercio_resp->setProductos($productos);
        
            //fin de productos
    
            //solicitudes inicio
           
            while(mysqli_more_results($db)&&mysqli_next_result($db)); 
            $SQLstr="CALL rm_sp_obtener_solicitudescomercio(".$idcomercio.")";
            
            $sql=mysqli_query($db,$SQLstr);
            
            if($sql){
                $solicitudes=array();
             //obtener los datos
                //      
                 while ($row=mysqli_fetch_row($sql)) {
                     $solicitud=new EN_SolicitudCliente();
                     $solicitud->setIdSolicitud($row[0]);
                     $solicitud->setFeSolicitud($row[2]);
                     $solicitud->setEsSolicitud($row[3]);
                     $solicitud->setEHastaSoli($row[4]);
                     $solicitud->setCoFInalSoli($row[5]);
                     
                     array_push($solicitudes,$solicitud);

                 }
              
                 while(mysqli_more_results($db)&&mysqli_next_result($db)); 
                foreach($solicitudes as &$sl){
                    $SQLstr="call rm_sp_obtener_usuariofromsolicitud(".$sl->getIdSolicitud().")";
                      $sql=mysqli_query($db,$SQLstr);
                          echo '56565'.mysqli_error($db);
                        if($sql){
                            $user=new EN_Usuario();
                         //obtener los datos
                             while ($row=mysqli_fetch_row($sql)) {
                                   
                                    $user->setIdUsuario($row[0]);
                                    $user->setNoUsuario($row[1]);
                                    $user->setA1Usuario($row[2]);
                                    $user->setA2Usuario($row[3]);
                                    $user->setFeNacimie($row[4]);
                                    $user->setSeUsuario($row[5]);
                                    

                             }
                            $sl->setUsuario($user);
                            echo 'pase por aqui';

                        }
                     while(mysqli_more_results($db)&&mysqli_next_result($db)); 
                    
                }
                
                foreach($solicitudes as &$sl){
                    $SQLstr="call rm_sp_obtener_productosfromsolicitud(".$sl->getIdSolicitud().")";
                      $sql=mysqli_query($db,$SQLstr);
            
                        if($sql){
                           
                                     $productos=array();
                                     //obtener los datos
                                         while ($row=mysqli_fetch_row($sql)) {
                                             $produaux=new EN_Producto();
                                             $produaux->setIdProducto($row[0]);
                                             $produaux->setNoProducto($row[1]);
                                             $produaux->setPrProducto($row[2]);
                                             $produaux->setDeProducto($row[3]);

                                             array_push($productos,$produaux);

                                         }


                                        foreach($productos as &$pr){
                                            $SQLstr="SELECT rm01idimagen,    rm02datoimagen
                        FROM rm08galeriaproductos where rm01idproducto=".$pr->getIdProducto();
                                              $sql=mysqli_query($db,$SQLstr);

                                                if($sql){
                                                    $imagen=array();
                                                 //obtener los datos
                                                     while ($row=mysqli_fetch_row($sql)) {
                                                          $imagen1=new EN_Galeria();
                                                          $imagen1->setIdImagen($row[0]);
                                                          $imagen1->setDtImagen($row[1]);
                                                          $pr->addImagen($imagen1);

                                                     }

                                                }
                                                                      while(mysqli_more_results($db)&&mysqli_next_result($db)); 

                                        }

                        }
                     while(mysqli_more_results($db)&&mysqli_next_result($db)); 
                $sl->setDetalles($productos);
                }                
               

            }
            $comercio_resp->setSolicitudes($solicitudes);
                                
                     
         

            //aqui termina solicitudes
            // inicio de promociones
    
         
    while(mysqli_more_results($db)&&mysqli_next_result($db)); 
       $SQLstr="select rm01idpromocion,rm02fevencimiento,rm03descripcion,rm04titulo,rm05fepublica 
from rm06promociones where rm01idcomercio=".$idcomercio;
            
            $sql=mysqli_query($db,$SQLstr);
            
            if($sql){
                $promociones=array();
             //obtener los datos
                 while ($row=mysqli_fetch_row($sql)) {
                     $promaux=new EN_Promocion();
                     //        
                     $promaux->setIdPromocion($row[0]);
                     $promaux->setFechaVencimiento($row[1]);
                     $promaux->setDescripcion($row[2]);
                     $promaux->setTitulo($row[3]);
                     $promaux->setFechaPublicacion($row[4]);
                     array_push($promociones,$promaux);

                 }
                
                

               $comercio_resp->setPromociones($promociones);

                
            }
            
                $resp=array();
                $resp['codigo']=2;
                $resp['mensaje']='Correcto';
                $resp['detalles']=$comercio_resp;
                
                echo json_encode($resp);
                return true;
                                
                   
        
    
}        
        
        
    }
    

?>