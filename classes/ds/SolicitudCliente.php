<?php
    class DS_SolicitudCliente{
        
		function resolverSolicitudCliente($solicitud,$db){
			   $sql= mysqli_query($db,"START TRANSACTION");
              while(mysqli_more_results($db)&&mysqli_next_result($db)); 

            $SQLstr="CALL rm_sp_resolver_solicitudcomercio('".$solicitud->getIdSolicitud()."',@resultado)";
			 $sql=mysqli_query($db,$SQLstr);

			
			           while(mysqli_more_results($db)&&mysqli_next_result($db)); 
            $sql =mysqli_query($db,"select @resultado;");
  while ($row = mysqli_fetch_row($sql)) {
                $msg = $row[0];
            }
            $error = (substr($msg, 0, 2) === 'e:');
            if($error){
                $codigoresultado=3;
                $mensaje="No se pudo resolver la solicitud";
            }else{
                $codigoresultado=2;
                $mensaje="Se  pudo  resolver ";
            }
            
           
			$resp_final=array();
 if($codigoresultado!=2){
                echo "entreee rollback";
                  while(mysqli_more_results($db)&&mysqli_next_result($db));  
                 mysqli_query($db,"ROLLBACK");
                $resp_final["codigo"]=3;
				 $resp_final["mensaje"]="Proceso no completado (con errores)";
             }else{
                // echo "entreee commit";
                while(mysqli_more_results($db)&&mysqli_next_result($db)); 
                 mysqli_query($db,"COMMIT"); 
                $resp_final["codigo"]=2;
				 $resp_final["mensaje"]="Proceso  completado (sin errores)";
             }
            

           
            $resp_final["detalles"]=$solicitud;

            
      echo json_encode($resp_final);			
			
		}
		
		
	
			function eliminarSolicitudCliente($solicitud,$db){
			   $sql= mysqli_query($db,"START TRANSACTION");
              while(mysqli_more_results($db)&&mysqli_next_result($db)); 

            $SQLstr="CALL rm_sp_eliminar_solicitudcomercio('".$solicitud->getIdSolicitud()."',@resultado)";
			 $sql=mysqli_query($db,$SQLstr);

			
			           while(mysqli_more_results($db)&&mysqli_next_result($db)); 
            $sql =mysqli_query($db,"select @resultado;");
  while ($row = mysqli_fetch_row($sql)) {
                $msg = $row[0];
            }
            $error = (substr($msg, 0, 2) === 'e:');
            if($error){
                $codigoresultado=3;
                $mensaje="No se pudo eliminar la solicitud";
            }else{
                $codigoresultado=2;
                $mensaje="Se  pudo  eliminar ";
            }
            
           
			$resp_final=array();
 if($codigoresultado!=2){
                echo "entreee rollback";
                  while(mysqli_more_results($db)&&mysqli_next_result($db));  
                 mysqli_query($db,"ROLLBACK");
                $resp_final["codigo"]=3;
				 $resp_final["mensaje"]="Proceso no completado (con errores)";
             }else{
                // echo "entreee commit";
                while(mysqli_more_results($db)&&mysqli_next_result($db)); 
                 mysqli_query($db,"COMMIT"); 
                $resp_final["codigo"]=2;
				 $resp_final["mensaje"]="Proceso  completado (sin errores)";
             }
            

           
            $resp_final["detalles"]=$solicitud;

            
      echo json_encode($resp_final);			
			
		}
		
		
        function getSolicitudesClientes($idcomercio,$db=null){
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
                     $solicitud->setEHastaSoli($row[3]);
                     $solicitud->setEsSolicitud($row[4]);
                     $solicitud->setCoFInalSoli($row[5]);
                     
                     array_push($solicitudes,$solicitud);

                 }
              
                 while(mysqli_more_results($db)&&mysqli_next_result($db)); 
                foreach($solicitudes as &$sl){
                    $SQLstr="call rm_sp_obtener_usuariofromsolicitud(".$sl->getIdSolicitud().")";
                      $sql=mysqli_query($db,$SQLstr);
                      //    echo '56565'.mysqli_error($db);
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
                          //  echo 'pase por aqui';

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
                                             $produaux->setDeProducto($row[2]);
                                             $produaux->setPrProducto($row[3]);

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
               // echo '0000'.mysqli_error($db);
                $resp=array();
                $resp['codigo']=2;
                $resp['mensaje']='Correcto';
                $resp['detalles']=$solicitudes;
                
                echo json_encode($resp);
                return true;
                
            }
            
                $resp=array();
                $resp['codigo']=3;
                $resp['mensaje']='In Correcto';
                
                echo json_encode($resp);
                return false;
                                
            
        }
        
    }

?>