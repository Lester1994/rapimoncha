<?php

    class DS_Usuario{

        function agregarUsuario($usuario,$db=null){
			
			//getIdUsuario getNoUsuario getA1Usuario getA2Usuario getFeNacimie getSeUsuario getUser getPassword
                	$SQLstr = "call rm_sp_registrar_usuario
                    ('" . $usuario->getNoUsuario() . "','" . $usuario->getA1Usuario() ."','" 
                        . $usuario->getA2Usuario() ."','" 
                        . $usuario->getFeNacimie() ."','" 
                        . $usuario->getSeUsuario() ."','" 
                        . $usuario->getPassword() ."','" 
                        . $usuario->getUser() ."',@resultado);";
             
			$sql= mysqli_query($db,"START TRANSACTION");
            $resultado=array();
             while(mysqli_more_results($db)&&mysqli_next_result($db)); 
            $sql = mysqli_query($db,$SQLstr);
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
                $mensaje="No se pudo ingresar el usuario";
            }else{
                $codigoresultado=2;
                $mensaje="Se  pudo ingresar el usuario".$msg;
            }
            
            $resultado=array('codigo'=>$codigoresultado,'msg'=>$mensaje);
           
             $error_gene=$resultado['codigo']!=2;

            
            //preparar la respuesta final
              $resp_final=array();
            
             if($error_gene){
              //   echo "entreee rollback";
                  while(mysqli_more_results($db)&&mysqli_next_result($db));  
                 mysqli_query($db,"ROLLBACK");
                $resp_final["codigo"]=3;
				 $resp_final["mensaje"]="Proceso no completado (con errores)";
             }else{
 
                while(mysqli_more_results($db)&&mysqli_next_result($db)); 
                 mysqli_query($db,"COMMIT"); 
                $resp_final["codigo"]=2;
				 $resp_final["mensaje"]="Proceso  completado (sin errores)";
             }
            
   
           
           $resp_final["detalles"]=$resultado;
            $resp_final["usuario"]=$usuario;
           
            
   echo json_encode($resp_final);
        
        } 
		
		
	}

?>