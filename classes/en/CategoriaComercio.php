<?php
    class EN_CategoriaComercio{
        var $idCategoria;
        var $noCategoria;
        
        function setIdCategoria($idCategoria){
            $this->idCategoria=$idCategoria;   
        }
        function setNoCategoria($noCategoria){
            $this->noCategoria=$noCategoria;   
        }      
        
        function getIdCategoria(){
            return $this->idCategoria;
        }
        function getNoCategoria(){
            return $this->noCategoria;
        }     
        // setIdCategoria  setNoCategoria
    }

 /*   $categoria=new CategoriaComercio();
    $categoria->setIdCategoria(48784545);
    echo $categoria->getIdCategoria().'aqui';*/
?>