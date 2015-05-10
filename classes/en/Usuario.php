<?php
class EN_Usuario{
    var $idUsuario;
    var $noUsuario;
    var $a1Usuario;
    var $a2Usuario;
    var $feNacimie;
    var $seUsuario;
	var $user;
	var $password;

function setIdUsuario($idUsuario) { $this->idUsuario = $idUsuario; }
function getIdUsuario() { return $this->idUsuario; }
function setNoUsuario($noUsuario) { $this->noUsuario = $noUsuario; }
function getNoUsuario() { return $this->noUsuario; }
function setA1Usuario($a1Usuario) { $this->a1Usuario = $a1Usuario; }
function getA1Usuario() { return $this->a1Usuario; }
function setA2Usuario($a2Usuario) { $this->a2Usuario = $a2Usuario; }
function getA2Usuario() { return $this->a2Usuario; }
function setFeNacimie($feNacimie) { $this->feNacimie = $feNacimie; }
function getFeNacimie() { return $this->feNacimie; }
function setSeUsuario($seUsuario) { $this->seUsuario = $seUsuario; }
function getSeUsuario() { return $this->seUsuario; }
	
function setUser($user) { $this->user = $user; }
function getUser() { return $this->user; }
	
function setPassword($password) { $this->password = $password; }
function getPassword() { return $this->password; }	
	
function generarUsuario($json){
	  if(isset($json)&&!empty($json)){
         
             $data = json_decode($json,true);
         
            $user=$data;
            
            $this->setIdUsuario($user['idusuario']);
            $this->setNoUsuario($user['nousuario']);
            $this->setA1Usuario($user['a1usuario']);
            $this->setA2Usuario($user['a2usuario']);
            $this->setFeNacimie($user['fenacimiento']);
            $this->setSeUsuario($user['sexo']);
		    $this->setUser($user['user']);
            $this->setPassword($user['password']);
           
	  }
	
}
}

// getIdUsuario getNoUsuario getA1Usuario getA2Usuario getFeNacimie getSeUsuario getUser getPassword
?>