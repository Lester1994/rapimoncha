<?php
class EN_Preferencias{
    var $idPreferencia;
    var $isContactoSinSubscripcion;
    var $isVerProductosSinSubscrib;
    var $hasServicioExpress;
    
    function setIdPreferencia($idPreferencia)
    {
    $this->idPreferencia = $idPreferencia;
    } 
    function setIsContactoSinSubscripcion($isContactoSinSubscripcion)
    {
    $this->isContactoSinSubscripcion = $isContactoSinSubscripcion;
    }   
    function setIsVerProductosSinSubscrib($isVerProductosSinSubscrib)
    {
    $this->isVerProductosSinSubscrib = $isVerProductosSinSubscrib;
    }
    function setHasServicioExpress($hasServicioExpress)
    {
    $this->hasServicioExpress = $hasServicioExpress;
    }
    function getIdPreferencia()
    {
        return $this->idPreferencia;
    }
    function getisContactoSinSubscripcion()
    {
        return $this->isContactoSinSubscripcion=='true'?1:2;;
    }
    function getisVerProductosSinSubscrib()
    {
        return $this->isVerProductosSinSubscrib=='true'?1:2;
    } 
    function gethasServicioExpress()
    {
        return $this->hasServicioExpress=='true'?1:2;;
    } 
    

}

?>