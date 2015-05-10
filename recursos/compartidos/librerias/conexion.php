<?php
error_reporting(0);
$host1 = "localhost";
$usuario1 ="root";
$clave1 = "chescojavier0610";
/*require_once 'ceSQL.php';


$dbase = new ceMySQLAdap($host1,$usuario1, $clave1,"rm_dbmain");*/
$dbase=mysqli_connect($host1,$usuario1,$clave1,"rm_dbmain");
if($dbase){
   // echo "funcionoooooo";   
}else{
  //  echo mysqli_error();   
}



?>