<?php

    class DS_Subscripcion{


        function getSubscripcionesComercio($idcomercio,$db=null){
                $SQLstr="select rm10usuarios.rm01idusuario,rm02nousuario,rm03a1usuario,rm04a2usuario,
                rm01idsubscripcion,rm02fesubscripcion,rm03enviarinfo,rm04estadosubs,rm01idcomercio
from rm09subscripciones inner join rm10usuarios
on rm09subscripciones.rm01idusuario=rm10usuarios.rm01idusuario where rm01idcomercio=".$idcomercio;
            
            $sql=mysqli_query($db,$SQLstr);
            echo mysqli_error($db);
            if($sql){
                $subscripciones=array();
             //obtener los datos
                 while ($row=mysqli_fetch_row($sql)) {
                     $promaux=new EN_Subscripcion();
                     //        
                     
                     $user=new EN_Usuario();
                     $user->setIdUsuario($row[0]);
                     $user->setNoUsuario($row[1]);
                     $user->setA1Usuario($row[2]);
                     $user->setA2Usuario($row[3]);
                     
                     $promaux->setIdSubscripcion($row[4]);
                     $promaux->setFechaSubscripcion($row[5]);
                     $promaux->setIsEnviarPromocion($row[6]);
                     $promaux->setEstadoSubscripcion($row[7]);
                     $promaux->setUsuario($user);
                    

                     array_push($subscripciones,$promaux);

                 }
                
                
                $resp=array();
                $resp['codigo']=1;
                $resp['mensaje']='Correcto';
                $resp['detalles']=$subscripciones;
                
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