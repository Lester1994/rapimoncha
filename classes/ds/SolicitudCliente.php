<?php
    class DS_SolicitudCliente{
        
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
                     $solicitud->setEsSolicitud($row[3]);
                     $solicitud->setEHastaSoli($row[4]);
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
               // echo '0000'.mysqli_error($db);
                $resp=array();
                $resp['codigo']=1;
                $resp['mensaje']='Correcto';
                $resp['detalles']=$solicitudes;
                
                echo json_encode($resp);
                return true;
                
            }
            
                $resp=array();
                $resp['codigo']=2;
                $resp['mensaje']='In Correcto';
                
                echo json_encode($resp);
                return false;
                                
            
        }
        
    }

?>