<?php

	$conn = mysqli_connect("localhost", "root", "", "recipedb");
	mysqli_query($conn, "SET NAMES 'utf8' ");

	if (isset($_POST["username"]))
	{
  		$username = $_POST["username"];
	}
	if (isset($_POST["password"]))
	{
		$password = $_POST["password"];	
	}

	$query = "SELECT id FROM usertbl WHERE username = '".$username."' and password = '".$password."' ";
	$result = mysqli_query($conn, $query) or die ('error: ' .mysqli_error($conn));

	if(mysqli_num_rows($result)) {
		echo "basarili";
	} else {
		echo " ".$username." ".$password;
	}


?>