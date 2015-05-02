<?php
    class ENConsulta{  
        var $idConsulta;
        var $tiConsulta;
        var $teConsulta;
        var $feConsulta;
        var $respondeA;
        var $suscripcion;

        function setIdConsulta($idConsulta) { $this->idConsulta = $idConsulta; }
        function getIdConsulta() { return $this->idConsulta; }
        function setTiConsulta($tiConsulta) { $this->tiConsulta = $tiConsulta; }
        function getTiConsulta() { return $this->tiConsulta; }
        function setTeConsulta($teConsulta) { $this->teConsulta = $teConsulta; }
        function getTeConsulta() { return $this->teConsulta; }
        function setFeConsulta($feConsulta) { $this->feConsulta = $feConsulta; }
        function getFeConsulta() { return $this->feConsulta; }
        function setRespondeA($respondeA) { $this->respondeA = $respondeA; }
        function getRespondeA() { return $this->respondeA; }
        function setSuscripcion($suscripcion) { $this->suscripcion = $suscripcion; }
        function getSuscripcion() { return $this->suscripcion; }
   
    }
?>