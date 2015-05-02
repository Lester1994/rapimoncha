<?php
/**
 * LICENSE: This source file is subject to the BSD license
 * 
 *modify by cech 2012*/

class ceMySQLAdap
{
	private $host, $user, $pass, $db_name;

	/**
	 * MySQL connection information
	 *
	 * @var resource
	 */

	private $link;
	/**
	 * Result of last query
	 *
	 * @var resource
	 */

	private $result;

	/**
	 * Date and time
	 *
	 */
	const DATETIME = 'Y-m-d H:i:s';

	/**
	 * Date
	 *
	 */
	const DATE = 'Y-m-d';

	/**
	 * Constructor
	 *
	 * @param string $host MySQL host address
	 * @param string $user Database user
	 * @param string $password Database password
	 * @param string $db Database name
	 * @param boolean $persistant Is persistant connection
	 * @param  boolean $connect_now Connect now cech 2012
	 * @return void
	 */
	public function __construct($host, $user, $password, $db, $persistant = true, $connect_now = true)
	{
		$this->host = $host; // Host address
		$this->user = $user;	// User
		$this->pass = $password;	// Password
		$this->db_name = $db;	// Database

		if ($connect_now)
			$this->connect($persistant);

		return;
	}

	/**
	 * Destructor
	 *
	 * @return void
	 */
	public function __destruct()
	{
		$this->close();
	}

	/**
	 * Connect to the database
	 *
	 * @param boolean $persist Is persistant connection cech 2012
	 * @return boolean
	 */
	public function connect($persist = false)
	{

	$link = mysqli_connect($this->host, $this->user, $this->pass);

		if (!$link)
			trigger_error('No hay coneccion con la base de datos.', E_USER_ERROR);

		if ($link)
		{
			$this->link = $link;
			
		}

		return false;
	}

	/**
	 * Query the database
	 *
	 * @param string $query SQL query string
	 * @return resource MySQL result set
	 */
	public function query($query)
	{
		
        $this->freeResult();
        
        $this->cleanResult();
        
		$result = mysqli_query( $this->link,$query);
		
		$this->result = $result;
/*
		if ($result == false)
			trigger_error('Error en la consulta SQL  ' . $this->error() . '');*/

		return $this->result;
	}




	/**
	 * Fetch results by associative array
	 *
	 * @param mixed $query Select query or MySQL result
	 * @return array Row
	 */
	public function fetchAssoc($query = false)
	{
		$this->resCalc($query);
		return mysqli_fetch_assoc($query);
	}

	/**
	 * Fetch results by enumerated array
	 *
	 * @param mixed $query Select query or MySQL result
	 * @return array Row
	 */
	public function fetchRow($query = false)
	{
	
		return mysqli_fetch_row($query);
	}




	/**
	 * Free result memory
	 *
	 * @return boolean
	 */
	public function freeResult()
	{
		return mysqli_free_result($this->result);
	}
    public function cleanResult(){
 
         while(mysqli_more_results($this->link)&&mysqli_next_result($this->link));
        
    }
private function resCalc(&$result)
	{
		if ($result == false)
			$result = $this->result;
		else {
			if (gettype($result) != 'resource')
				$result = $this->query($result);
		}

		return;
	}

	/**
	 * Count number of rows in a result
	 *
	 * @param mixed $result Select query or MySQL result
	 * @return int Number of rows
	 */
	public function countRows($result = false)
	{
		$this->resCalc($result);
		return (int) mysqli_num_rows($result);
	}

	/**
	 * Count number of fields in a result
	 *
	 * @param mixed $result Select query or MySQL result
	 * @return int Number of fields
	 */
	public function countFields($result = false)
	{
		$this->resCalc($result);
		return (int) mysqli_num_fields($result);
	}

	/**
	 * Get last inserted id of the last query
	 *
	 * @return int Inserted in
	 */
	public function insertId()
	{
		return (int) mysqli_insert_id($this->link);
	}
	public function last_insert_Id()
	{
		return (int) mysqli_insert_id();
	}
	/**
	 * Get number of affected rows of the last query
	 *
	 * @return int Affected rows
	 */
	public function affectedRows()
	{
		return (int) mysqli_affected_rows($this->link);
	}

	/**
	 * Get the error description from the last query
	 *
	 * @return string
	 */
	public function error()
	{
		return mysqli_error($this->link);
	}

	/**
	 * Dump MySQL info to page
	 *
	 * @return void
	 */
	public function dumpInfo()
	{
		echo mysqli_info($this->link);
	}

	/**
	 * Close the link connection
	 *
	 * @return boolean
	 */
	public function close()
	{
		return mysqli_close($this->link);
	}


	
}