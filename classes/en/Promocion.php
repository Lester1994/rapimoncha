<?php 
class EN_Promocion{

var $idPromocion;
var $fechaVencimiento;
var $descripcion;
var $titulo;
var $fechaPublicacion;

function setIdPromocion($idPromocion) 
{ $this->idPromocion = $idPromocion; }
function getIdPromocion() 
{ return $this->idPromocion; }
function setFechaVencimiento($fechaVencimiento) 
{ $this->fechaVencimiento = $fechaVencimiento; }
function getFechaVencimiento() 
{ return $this->fechaVencimiento; }
function setDescripcion($descripcion) 
{ $this->descripcion = $descripcion; }
function getDescripcion() 
{ return $this->descripcion; }
function setTitulo($titulo) 
{ $this->titulo = $titulo; }
function getTitulo() 
{ return $this->titulo; }
function setFechaPublicacion($fechaPublicacion) 
{ $this->fechaPublicacion = $fechaPublicacion; }
function getFechaPublicacion() 
{ return $this->fechaPublicacion; }

    
}
?>