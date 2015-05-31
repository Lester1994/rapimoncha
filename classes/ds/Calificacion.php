<?php


    class DS_Calificacion{
        
 	
		
         function agregarCalificacionApp($calificacion,$db=null){
                   
          $sql= mysqli_query($db,"START TRANSACTION");
              while(mysqli_more_results($db)&&mysqli_next_result($db)); 
             
   
           $SQLstr = "CALL rm_sp_registrar_calificacion(
					'".$calificacion->getNombreUsuario()."',
					'".$calificacion->getUtilidad()."',
					'".$calificacion->getFacilidad()."',
					'".$calificacion->getEstilo()."',
					'".$calificacion->getSoporte()."',
					'".$calificacion->getNotas()."',@resultado)";
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
                $mensaje="No se pudo ingresar la calificación";
            }else{
                $codigoresultado=2;
                $mensaje="Se  pudo ingresar la calificación";
            }
            
            $resultado=array('codigo'=>$codigoresultado,'msg'=>$mensaje);
			array_push($respuestas,$resultado);
          
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
            $resp_final["calificacion"]=$calificacion;
            //echo "aqui paseeee!".var_dump($resp_final);
        
           // echo "no pude aqui ";
            
   echo json_encode($resp_final);
        }		
      
		
		
		
	}

?>