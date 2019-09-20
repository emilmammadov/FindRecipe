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

	$query = "INSERT INTO usertbl (username, password) VALUES ('".$username."', '".$password."')";
	$result = mysqli_query($conn, $query) or die ('error: ' .mysqli_error($conn));

	if(mysqli_num_rows($result) > 0) {
		echo "basarili";
	} else {
		echo " ".$username." ".$password;
	}


?>