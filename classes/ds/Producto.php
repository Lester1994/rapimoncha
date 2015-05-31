<?php

    class DS_Producto{

        function agregarProductoComercio($producto,$idcomercio,$db=null){
                   
          $sql= mysqli_query($db,"START TRANSACTION");
              while(mysqli_more_results($db)&&mysqli_next_result($db)); 
              $ds_Galeria=new DS_Galeria();
          // echo "el precio es ".$producto->getPrProducto();
            $SQLstr = "call rm_sp_registrar_productocomercio
                    ('" .$idcomercio  . "','" . $producto->getNoProducto() ."','". 
              $producto->getDeProducto() . "','" . $producto->getPrProducto() ."',@resultado);";
            //echo $SQLstr;
            $respuestas=array();
             while(mysqli_more_results($db)&&mysqli_next_result($db)); 
            $sql= mysqli_query($db,$SQLstr);
            if(!$sql){
                echo mysqli_error($db);   
            }
            while ($row=mysqli_fetch_row($sql)) {
                $msg = $row[0];
                $producto->setIdProducto($msg);   
            }  

           while(mysqli_more_results($db)&&mysqli_next_result($db)); 
           $sql= mysqli_query($db,"select @resultado;");

            while ($row = mysqli_fetch_row($sql)) {
                $msg = $row[0];
            }
            $error = (substr($msg, 0, 2) === 'e:');
            if($error){
                $codigoresultado=3;
                $mensaje="No se pudo ingresar el producto";
            }else{
                $codigoresultado=2;
                $mensaje="Se  pudo ingresar el producto";
            }
            
            $resultado=array('codigo'=>$codigoresultado,'msg'=>$mensaje);
			array_push($respuestas,$resultado);
           if(!$error){
               //   echo "<br>SECCION DE IMAGENES<br>";
                   //registrar galeria
                 foreach($producto->getImagenes() as $aux){
                    // $db->connect(false);
                    // echo "respuesta imagenes".var_dump($respuestas);
                     $res= $ds_Galeria->agregarImagenProducto($aux,$producto->getIdProducto(),$db);
                     //echo var_dump($respuestas)."<<<<<<<<<<";
                  //   echo '<br>el dump>>>'. var_dump($res).'<br>';
                     array_push($resultado,$res);
                       // $db->close();
                    // echo "hasta aqui tenemos". count($respuestas);
                     
                 }  
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
        
        
     function eliminarProductoComercio($idproducto,$idcomercio,$db=null){
		 
		    $sql= mysqli_query($db,"START TRANSACTION");
		 
                	$SQLstr = "call rm_sp_eliminar_productocomercio
                    ('"  . $idcomercio ."','"  . $idproducto ."',@resultado);";
		 
            //echo $SQLstr;
            $resultado=array();
             while(mysqli_more_results($db)&&mysqli_next_result($db)); 
            $sql = mysqli_query($db,$SQLstr);
		 //echo $idcomercio.'......'.$idproducto;
         if(!$sql){
          echo mysqli_error($db);
             exit();
         }
           while(mysqli_more_results($db)&&mysqli_next_result($db)); 
            $sql =mysqli_query($db,"select @resultado;");

            while ($row = mysqli_fetch_row($sql)) {
                $msg = $row[0];
            }
            $error = (substr($msg, 0, 2) === 'e:');
            if($error){
                $codigoresultado=3;
                $mensaje="No se pudo eliminar el producto ".$idproducto;
            }else{
                $codigoresultado=2;
                $mensaje="Se  pudo eliminar el  producto ".$msg;
            }
            
            $resultado=array('codigo'=>$codigoresultado,'mensaje'=>$mensaje);
           
		  $resp_final=array();
		 
		 
             if($codigoresultado!=2){
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
             
		 $resp_final["detalles"]=$resultado;
           // echo json_encode($resultado).'JSON LOCALIZACION'.$msg;
          //  return $resultado;
		 echo json_encode($resp_final);
        }
        
        function getProductosComercio($idcomercio,$db=null){
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

                }
                $resp=array();
                $resp['codigo']=2;
                $resp['mensaje']='Correcto';
                $resp['detalles']=$productos;
                
                echo json_encode($resp);
                return true;
                
            }
            
                $resp=array();
                $resp['codigo']=1;
                $resp['mensaje']='In Correcto';
                
                echo json_encode($resp);
                return false;
                                
            
        }

        
        function getProductoComercio($idcomercio,$idproducto,$db=null){
            $SQLstr="SELECT rm01idproducto,
                    rm02noproducto,
                    rm03deproducto,
                    rm04peproducto
                FROM rm07productos where rm01idcomercio=".$idcomercio." and rm01idproducto=".$idproducto." limit 1";
            
            $sql=mysqli_query($db,$SQLstr);
             $produaux=new EN_Producto();
			//echo mysqli_error($db).'aquiii';
            if($sql){
               
             //obtener los datos
                 while ($row=mysqli_fetch_row($sql)) {
                   
                     $produaux->setIdProducto($row[0]);
                     $produaux->setNoProducto($row[1]);
                     $produaux->setDeProducto($row[2]);
                     $produaux->setPrProducto($row[3]);
                     
                     

                 }
                
                
            
                    $SQLstr="SELECT rm01idimagen,    rm02datoimagen
FROM rm08galeriaproductos where rm01idproducto=".$produaux->getIdProducto();
                      $sql=mysqli_query($db,$SQLstr);
            
                        if($sql){
                            $imagen=array();
                         //obtener los datos
                             while ($row=mysqli_fetch_row($sql)) {
                                  $imagen1=new EN_Galeria();
                                  $imagen1->setIdImagen($row[0]);
                                  $imagen1->setDtImagen($row[1]);
                                  $produaux->addImagen($imagen1);

                             }

                        }

                
                $resp=array();
                $resp['codigo']=2;
                $resp['mensaje']='Correcto';
                $resp['detalles']=$produaux;
                
                echo json_encode($resp);
                return true;
                
            }
            
                $resp=array();
                $resp['codigo']=1;
                $resp['mensaje']='In Correcto';
                
                echo json_encode($resp);
                return false;
                                
            
        }	
		
		
		
        function actualizarProductoComercio($producto,$idcomercio,$db=null){
                   
        
              $ds_Galeria=new DS_Galeria();
          // echo "el precio es ".$producto->getPrProducto();//se actualiza y elimina imagenes
			
			     $sql= mysqli_query($db,"START TRANSACTION");
              while(mysqli_more_results($db)&&mysqli_next_result($db)); 
			
            $SQLstr = "call rm_sp_actualizar_productocomercio
                    ('" .$idcomercio  . "','" . $producto->getNoProducto() ."','". 
              $producto->getDeProducto() . "','" . $producto->getPrProducto() . "','" . $producto->getIdProducto() ."',@resultado);";
            //echo $SQLstr;
            $respuestas=array();
             while(mysqli_more_results($db)&&mysqli_next_result($db)); 
            $sql= mysqli_query($db,$SQLstr);
            if(!$sql){
                echo mysqli_error($db);   
            }

           while(mysqli_more_results($db)&&mysqli_next_result($db)); 
           $sql= mysqli_query($db,"select @resultado;");

            while ($row = mysqli_fetch_row($sql)) {
                $msg = $row[0];
            }
            $error = (substr($msg, 0, 2) === 'e:');
            if($error){
                $codigoresultado=3;
                $mensaje="No se pudo actualizar el producto";
            }else{
                $codigoresultado=2;
                $mensaje="Se  pudo actualizar el producto";
            }
            
            $resultado=array('codigo'=>$codigoresultado,'msg'=>$mensaje);
			array_push($respuestas,$resultado);
           if(!$error){
               //   echo "<br>SECCION DE IMAGENES<br>";
                   //registrar galeria
                 foreach($producto->getImagenes() as $aux){
                    // $db->connect(false);
                    // echo "respuesta imagenes".var_dump($respuestas);
                     $res= $ds_Galeria->agregarImagenProducto($aux,$producto->getIdProducto(),$db);
                     //echo var_dump($respuestas)."<<<<<<<<<<";
                  //   echo '<br>el dump>>>'. var_dump($res).'<br>';
                     array_push($resultado,$res);
                       // $db->close();
                    // echo "hasta aqui tenemos". count($respuestas);
                     
                 }  
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
        
    }

?>