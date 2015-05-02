<?php
class EN_Geolocalizacion{
    var $idlocalizacion;
    var $latitud;
    var $longitud;

    function setIdlocalizacion($idlocalizacion) { $this->idlocalizacion = $idlocalizacion; }
    function getIdlocalizacion() { return $this->idlocalizacion; }
    function setLatitud($latitud) { $this->latitud = $latitud; }
    function getLatitud() { return $this->latitud; }
    function setLongitud($longitud) { $this->longitud = $longitud; }
    function getLongitud() { return $this->longitud; }
}

/*$local=new Geolocalizacion();
    $local->setIdlocalizacion(48784545);
    echo $local->getIdlocalizacion().'aqui';*/

?>