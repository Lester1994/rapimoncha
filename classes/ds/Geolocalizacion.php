<?php

    class DS_Geolocalizacion{

        function agregarGeolocalizacionComercio($geolocalizacion,$idcomercio,$db=null){
                	$SQLstr = "call rm_sp_registrar_geolocalizacioncomercio
                    ('" . $geolocalizacion->getLatitud() . "','" . $geolocalizacion->getLongitud() ."','" . $idcomercio ."',@resultado);";
            //echo $SQLstr;
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
                $mensaje="No se pudo ingresar la ubicacion del producto";
            }else{
                $codigoresultado=2;
                $mensaje="Se  pudo ingresar la ubicacion del producto".$msg;
            }
            
            $resultado=array('codigo'=>$codigoresultado,'msg'=>$mensaje);
           
             
           // echo json_encode($resultado).'JSON LOCALIZACION'.$msg;
            return $resultado;
        }  
        
        
        function eliminarGeolocalizacionComercio($idcomercio,$db=null){
                	$SQLstr = "call rm_sp_eliminar_geolocalizacioncomercio
                    ('"  . $idcomercio ."',@resultado);";
          
            $resultado=array();
             while(mysqli_more_results($db)&&mysqli_next_result($db)); 
            $sql = mysqli_query($db,$SQLstr);
			//  echo $SQLstr.mysqli_error($db);
         if(!$sql){
          echo mysqli_error($db);
             exit();
         }
           while(mysqli_more_results($db)&&mysqli_next_result($db)); 
            $sql =mysqli_query($db,"select @resultado;");

            while ($row = mysqli_fetch_row($sql)) {
                $msg = $row[0];
            }
		//	echo 'se borraron de geolocalizacion '.mysqli_affected_rows($db);
            $error = (substr($msg, 0, 2) === 'e:');
            if($error){
                $codigoresultado=3;
                $mensaje="No se pudo eliminar la ubicacion ";
            }else{
                $codigoresultado=2;
                $mensaje="Se  pudo eliminar la ubicacion ".$msg;
            }
            
            $resultado=array('codigo'=>$codigoresultado,'msg'=>$mensaje);
           
             
           // echo json_encode($resultado).'JSON LOCALIZACION'.$msg;
            return $resultado;
        }  
        
        
        
        
    }

?>