<?php

	$conn = mysqli_connect("localhost", "root", "", "recipedb");
	mysqli_query($conn, "SET NAMES 'utf8' ");

	if (isset($_POST["id"]))
	{
          $idd = $_POST["id"];
    }

	$query = "SELECT * FROM recipetbl WHERE id = '".$idd."'";
	$result = mysqli_query($conn, $query) or die ('error: ' .mysqli_error($conn));

	if(mysqli_num_rows($result)) {
		echo json_encode(( $result->fetch_assoc() ));
	}


?>