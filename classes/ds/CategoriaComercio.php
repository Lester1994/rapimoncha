<?php


    class DS_CategoriaComercio{
        
         var $categoriasarray;
             function agregarCategoriaComercio($categoria,$idcomercio,$db=null){
                	$SQLstr = "call rm_sp_registrar_categoriacomercio
                    ('" . $categoria->getIdCategoria() . "','" . $idcomercio ."',@resultado);";
           while(mysqli_more_results($db)&&mysqli_next_result($db)); 
            $resultado=array();    
            $sql = mysqli_query($db,$SQLstr);
            // $db -> freeResult();   
            while(mysqli_more_results($db)&&mysqli_next_result($db)); 
            $sql = mysqli_query($db,"select @resultado;");

            while ($row = mysqli_fetch_row($sql)) {
                $msg = $row[0];
            }
            $error = (substr($msg, 0, 2) === 'e:');
            if($error){
                $codigoresultado=3;
                $mensaje="No se pudo ingresar la categoria del comercio";
            }else{
                $codigoresultado=2;
                $mensaje="Se  pudo ingresar la categoria del comercio";
            }
            
           $resultado=array('codigo'=>$codigoresultado,'msg'=>$mensaje);
           // $db -> freeResult($sql);
           //  $db->cleanResult();
            return $resultado;
        }  
        
             function eliminarCategoriasComercio($idcomercio,$db=null){
                	$SQLstr = "call rm_sp_eliminar_categoriascomercio
                    ('" . $idcomercio ."',@resultado);";
               //  echo $SQLstr;
           while(mysqli_more_results($db)&&mysqli_next_result($db)); 
            $resultado=array();    
            $sql = mysqli_query($db,$SQLstr);
            // $db -> freeResult();   
            while(mysqli_more_results($db)&&mysqli_next_result($db)); 
            $sql = mysqli_query($db,"select @resultado;");

            while ($row = mysqli_fetch_row($sql)) {
                $msg = $row[0];
            }
            $error = (substr($msg, 0, 2) === 'e:');
            if($error){
                $codigoresultado=3;
                $mensaje="No se pudo eliminar las categorias del comercio";
            }else{
                $codigoresultado=2;
                $mensaje="Se  pudo eliminar las categorias del comercio";
            }
            
           $resultado=array('codigo'=>$codigoresultado,'msg'=>$mensaje);
           // $db -> freeResult($sql);
           //  $db->cleanResult();
            return $resultado;
        }
        
        function getCategoriasComercio($idcomercio,$bd){
            $this->categoriasarray=array();
            $SQLstr = "call rm_sp_obtener_categoriascomercio('" . $idcomercio ."');";
                $sql=mysqli_query($bd,$SQLstr);
          //  echo 'entre'.mysqli_error();
                    if($sql){
                          // echo 'entre';
                         //obtener los datos
                        $resp['codigo']=1;
                        $resp['mensaje']='Entro a ver categorías';
                             while ($row=mysqli_fetch_row($sql)) {
                                  $categoria=new EN_CategoriaComercio();
                                  $categoria->setIdCategoria ($row[0]);
                                  $categoria->setNoCategoria($row[1]);
                               //  echo 'entre';
                                 array_push( $this->categoriasarray,$categoria);
                             }
                        $resp['detalles']=$this->categoriasarray;

                    }else{
                         $resp['codigo']=2;
                        $resp['mensaje']='No entro a ver categorías';                       
                    }
            
            
            echo json_encode($resp);

        }
        
        function getNoCategoriasComercio($idcomercio,$bd){
             $this->categoriasarray=array();
            $SQLstr = "call rm_sp_obtener_nocategoriascomercio('" . $idcomercio ."');";
                $sql=mysqli_query($bd,$SQLstr);
          //  echo 'entre'.mysqli_error();
                    if($sql){
                          // echo 'entre';
                         //obtener los datos
                        $resp['codigo']=1;
                        $resp['mensaje']='Entro a ver categorias';
                             while ($row=mysqli_fetch_row($sql)) {
                                  $categoria=new EN_CategoriaComercio();
                                  $categoria->setIdCategoria ($row[0]);
                                  $categoria->setNoCategoria($row[1]);
                               //  echo 'entre';
                                 array_push( $this->categoriasarray,$categoria);
                             }
                        $resp['detalles']=$this->categoriasarray;

                    }else{
                         $resp['codigo']=2;
                        $resp['mensaje']='No entro a ver categorías';                       
                    }
            
            
            echo json_encode($resp);

        }        
    }

?>