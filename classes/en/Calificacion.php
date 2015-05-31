<?php
class EN_Calificacion{

var $idccalificacion;
var $usuario;
var $utilidad;
var $facilidad;
var $estilo;
var $soporte;
var $notas;

function setIdccalificacion($idccalificacion) { $this->idccalificacion = $idccalificacion; }
function getIdccalificacion() { return $this->idccalificacion; }
function setUsuario($usuario) { $this->usuario = $usuario; }
function getUsuario() { return $this->usuario; }
function setUtilidad($utilidad) { $this->utilidad = $utilidad; }
function getUtilidad() { return $this->utilidad; }
function setFacilidad($facilidad) { $this->facilidad = $facilidad; }
function getFacilidad() { return $this->facilidad; }
function setEstilo($estilo) { $this->estilo = $estilo; }
function getEstilo() { return $this->estilo; }
function setSoporte($soporte) { $this->soporte = $soporte; }
function getSoporte() { return $this->soporte; }
function setNotas($notas) { $this->notas = $notas; }
function getNotas() { return $this->notas; }
function getNombreUsuario(){
  return $this->usuario->getUser();	
}
	
			function generarCalificacion($json2){
				echo $json2.'es la cadena';
				$json=json_decode($json2,true);
			 $this->setIdccalificacion($json['idccalificacion']);
				
			 $useraux=new EN_Usuario();
			$useraux->setIdUsuario($json['usuario']['user']);

		    $useraux->setUser($json['usuario']['user']);
			 
			 $this->usuario=$useraux;
				
			 $this->setUtilidad($json['utilidad']);
			 $this->setFacilidad($json['facilidad']);
			 $this->setEstilo($json['estilo']);
			 $this->setSoporte($json['soporte']);
			 $this->setNotas($json['notas']);
			echo json_encode($this);
		}

}

?>