<?php

function encodequery($query, $outer, $inner)
{
    $conn = mysqli_connect("localhost", "root", "", "recipedb");
    mysqli_query($conn, "SET NAMES 'utf8' ");
    $result = mysqli_query($conn, $query) or die('error: ' . mysqli_error($conn));

    /* create one master array of the records */
    $objects = array();
    if (mysqli_num_rows($result) > 0) {
        while ($object = mysqli_fetch_assoc($result)) {
            $objects[] = array($inner => $object);
        }
    }
    return json_encode(array($outer => $objects));
}


?>