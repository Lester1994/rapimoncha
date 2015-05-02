<?php
 class EN_SolicitudCliente{
    var $idSolicitud;
    var $feSolicitud;
    var $esSolicitud;
    var $eHastaSoli;
    var $coFInalSoli;
    var $usuario;
    var $detalles;
     
    function setIdSolicitud($idSolicitud) { $this->idSolicitud = $idSolicitud; }
    function getIdSolicitud() { return $this->idSolicitud; }
    function setFeSolicitud($feSolicitud) { $this->feSolicitud = $feSolicitud; }
    function getFeSolicitud() { return $this->feSolicitud; }
    function setEsSolicitud($esSolicitud) { $this->esSolicitud = $esSolicitud; }
    function getEsSolicitud() { return $this->esSolicitud; }
    function setEHastaSoli($eHastaSoli) { $this->eHastaSoli = $eHastaSoli; }
    function getEHastaSoli() { return $this->eHastaSoli; }
    function setCoFInalSoli($coFInalSoli) { $this->coFInalSoli = $coFInalSoli; }
    function getCoFInalSoli() { return $this->coFInalSoli; }
    function setUsuario($usuario) { $this->usuario = $usuario; }
    function getUsuario() { return $this->usuario; }
    function setDetalles($detalles) { $this->detalles = $detalles; }
    function getDetalles() { return $this->detalles; }
          
     function addDetalle($producto=null){
            if($producto!=null){
                if($this->detalles==null){
                    $this->detalles=array();   
                }
                
                array_push($this->detalles,$producto);
                return true;
            }else{
             return false;   
            }
        }
     
 }
?>