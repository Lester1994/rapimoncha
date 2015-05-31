<?php

    class DS_Preferencias{
    
    

        function agregarPreferenciaComercio($preferencia,$idcomercio,$db=null){
          //  echo "hemos entrado a reg preferencias".$preferencia->getisContactoSinSubscripcion()."<br>";
                	$SQLstr = "call rm_sp_registrar_preferenciacomercio
                    ('" . $preferencia->getisContactoSinSubscripcion() . "','" . $preferencia->getisVerProductosSinSubscrib() ."','" .
                        $preferencia->gethasServicioExpress() ."','" . $idcomercio ."',@resultado);";
            //echo $SQLstr;
            $resultado=array();
             while(mysqli_more_results($db)&&mysqli_next_result($db)); 
            $sql = mysqli_query($db,$SQLstr);
         if(!$sql){
        //  echo mysqli_error($db);
             exit();
         }
             // echo "<br>".mysqli_error($db)."<br>";
           while(mysqli_more_results($db)&&mysqli_next_result($db)); 
            $sql =mysqli_query($db,"select @resultado;");

            while ($row = mysqli_fetch_row($sql)) {
                $msg = $row[0];
            }
            $error = (substr($msg, 0, 2) === 'e:');
            if($error){
                $codigoresultado=3;
               //  echo mysqli_error($db);
                $mensaje="No se pudo ingresar la preferencia del comercio";
            }else{
                $codigoresultado=2;
                $mensaje="Se  pudo ingresar la preferencia del comercio".$msg;
            }
            
            $resultado=array('codigo'=>$codigoresultado,'msg'=>$mensaje);
           
             
           // echo json_encode($resultado).'JSON LOCALIZACION'.$msg;
            return $resultado;
        }
		
	function eliminarPreferenciaComercio($idcomercio,$db=null){
		$SQLstr = "call rm_sp_eliminar_preferenciacomercio
                    ('"  . $idcomercio ."',@resultado);";
           // echo $SQLstr;
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
                $mensaje="No se pudo eliminar la preferencia ";
            }else{
                $codigoresultado=2;
                $mensaje="Se  pudo eliminar la preferencia ".$msg;
            }
            
            $resultado=array('codigo'=>$codigoresultado,'msg'=>$mensaje);
           
             
           // echo json_encode($resultado).'JSON LOCALIZACION'.$msg;
            return $resultado;
    }
		
	}

?>