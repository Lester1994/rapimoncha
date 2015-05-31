<?php

    class DS_Galeria{
        function agregarImagenProducto($galeria,$idproducto,$db=null){
      	$SQLstr = "call rm_sp_registrar_imagenproducto
                    ('" .$idproducto ."','" .  $galeria->dtimagen ."',@resultado);";
             $resultado=array();
            while(mysqli_more_results($db)&&mysqli_next_result($db)); 
            $sql = mysqli_query($db,$SQLstr);
            // $db -> freeResult();
            // $db->cleanResult();
            while(mysqli_more_results($db)&&mysqli_next_result($db)); 
            $sql = mysqli_query($db,"select @resultado;");

            while ($row = mysqli_fetch_row($sql)) {
                $msg = $row[0];
               // echo $msg;
            }
            $error = (substr($msg, 0, 2) === 'e:');
            if($error){
              //  echo "el valor del error..........................................................".$error;
                $codigoresultado=3;
                $mensaje="No se pudo ingresar la imagen del comercio";
            }else{
                $codigoresultado=2;
                $mensaje="Se  pudo ingresar la imagen del comercio";
            }
            
             $resultado=array('codigo'=>$codigoresultado,'msg'=>$mensaje);
           // $db -> freeResult($sql);
           //  $db->cleanResult();
            
            return $resultado;
        }
        
        function agregarImagenComercio($galeria,$idcomercio,$db=null){
                	$SQLstr = "call rm_sp_registrar_imagencomercio
                    ('" .$idcomercio ."','" .  $galeria->dtimagen ."',@resultado);";
             $resultado=array();
            while(mysqli_more_results($db)&&mysqli_next_result($db)); 
            $sql = mysqli_query($db,$SQLstr);
            // $db -> freeResult();
            // $db->cleanResult();
            while(mysqli_more_results($db)&&mysqli_next_result($db)); 
            $sql = mysqli_query($db,"select @resultado;");

            while ($row = mysqli_fetch_row($sql)) {
                $msg = $row[0];
               // echo $msg;
            }
            $error = (substr($msg, 0, 2) === 'e:');
            if($error){
              //  echo "el valor del error..........................................................".$error;
                $codigoresultado=3;
                $mensaje="No se pudo ingresar la imagen del comercio";
            }else{
                $codigoresultado=2;
                $mensaje="Se  pudo ingresar la imagen del comercio";
            }
            
             $resultado=array('codigo'=>$codigoresultado,'msg'=>$mensaje);
           // $db -> freeResult($sql);
           //  $db->cleanResult();
            
            return $resultado;
        }    
		
        function eliminarImagenComercio($idcomercio,$db=null){
                	$SQLstr = "call rm_sp_eliminar_imagencomercio
                    ('" .$idcomercio ."',@resultado);";
             $resultado=array();
            while(mysqli_more_results($db)&&mysqli_next_result($db)); 
            $sql = mysqli_query($db,$SQLstr);
            // $db -> freeResult();
            // $db->cleanResult();
            while(mysqli_more_results($db)&&mysqli_next_result($db)); 
            $sql = mysqli_query($db,"select @resultado;");

            while ($row = mysqli_fetch_row($sql)) {
                $msg = $row[0];
               // echo $msg;
            }
            $error = (substr($msg, 0, 2) === 'e:');
            if($error){
              //  echo "el valor del error..........................................................".$error;
                $codigoresultado=3;
                $mensaje="No se pudo eliminar la imagen del comercio";
            }else{
                $codigoresultado=2;
                $mensaje="Se  pudo eliminar la imagen del comercio";
            }
            
             $resultado=array('codigo'=>$codigoresultado,'msg'=>$mensaje);
           // $db -> freeResult($sql);
           //  $db->cleanResult();
            
            return $resultado;
        }    		
    }

?>