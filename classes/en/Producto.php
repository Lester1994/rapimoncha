<?php
    require_once '../../recursos/compartidos/inclusiones.php';
    require_once $RM_ENTITIES_DIR.'Galeria.php';

    class EN_Producto{
        var $idProducto;
        var $noProducto;
        var $prProducto;
        var $deProducto;
        var $imagenes;

        function setIdProducto($idProducto) { $this->idProducto = $idProducto; }
        function getIdProducto() { return $this->idProducto; }
        function setNoProducto($noProducto) { $this->noProducto = $noProducto; }
        function getNoProducto() { return $this->noProducto; }
        function setPrProducto($prProducto) { $this->prProducto = $prProducto; }
        function getPrProducto() { return $this->prProducto; }
        function setDeProducto($deProducto) { $this->deProducto = $deProducto; }
        function getDeProducto() { return $this->deProducto; }
        function setImagenes($imagenes) { $this->imagenes = $imagenes; }
        function getImagenes() { return $this->imagenes; }

        function addImagen($imagen=null){
            if($imagen!=null){
                if($this->imagenes==null){
                    $this->imagenes=array();   
                }
               // echo "entro a ver cuantas imagenes ingresooo";
                array_push($this->imagenes,$imagen);
                return true;
            }else{
             return false;   
            }
        }
        
    }

      /*  $imagen1=new EN_Galeria();
        $imagen1->setIdImagen(45648979);
        $imagen1->setDtImagen('imagen45648979');
       $imagen2=new EN_Galeria();
        $imagen2->setIdImagen(459);
        $imagen2->setDtImagen('imagen4');
        
        $produc=new EN_Producto();
        $produc->addImagen($imagen1);
        $produc->addImagen($imagen2);
        
        foreach($produc->getImagenes() as $imagen){
         echo 'ENTREEEEEEE<br>';
            echo $imagen->getIdImagen().'<br>';
            echo $imagen->getDtImagen().' COMO EL DATO DE LA IMAGEN<br>';
        }
       // echo var_dump($produc->getImagenes());*/
?>