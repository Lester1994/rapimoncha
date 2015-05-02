<?php

    class DS_Promocion{

        function agregarPromocionComercio($promocion,$idcomercio,$db=null){
                	$SQLstr = "call rm_sp_registrar_promocioncomercio
                    ('" . $idcomercio . "','" . $promocion->getFechaVencimiento() ."','" 
                        . $promocion->getDescripcion() ."','" 
                        . $promocion->getTitulo() ."',@resultado);";
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
                $mensaje="No se pudo ingresar la promoción";
            }else{
                $codigoresultado=2;
                $mensaje="Se  pudo ingresar la promocion".$msg;
            }
            
            $resultado=array('codigo'=>$codigoresultado,'msg'=>$mensaje);
           
             
           // echo json_encode($resultado).'JSON LOCALIZACION'.$msg;
            return $resultado;
        }  
        
        
        function getPromocionesComercio($idcomercio,$db=null){
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
                
                
                $resp=array();
                $resp['codigo']=1;
                $resp['mensaje']='Correcto';
                $resp['detalles']=$promociones;
                
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