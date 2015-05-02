<?php
    class EN_Subscripcion{
        var $idSubscripcion;
        var $usuario;
        var $isEnviarPromocion;
        var $fechasubscripcion;
        var $estadosubscripcion;
        function setFechaSubscripcion($fechasubscripcion) { $this->fechasubscripcion = $fechasubscripcion; }
        function getFechaSubscripcion() { return $this->fechasubscripcion; }
        function setEstadoSubscripcion($estadosubscripcion) { $this->estadosubscripcion = $estadosubscripcion; }
        function getEstadoSubscripcion() { return $this->estadosubscripcion; }
        function setIdSubscripcion($idSubscripcion) { $this->idSubscripcion = $idSubscripcion; }
        function getIdSubscripcion() { return $this->idSubscripcion; }
        function setUsuario($usuario) { $this->usuario = $usuario; }
        function getUsuario() { return $this->usuario; }
        function setIsEnviarPromocion($isEnviarPromocion) { $this->isEnviarPromocion = $isEnviarPromocion; }
        function getIsEnviarPromocion() { return $this->isEnviarPromocion; }
 
      
    }
?>