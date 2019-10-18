<?php

function encodequery($query, $query2, $outer, $inner, $inner2)
{
    $conn = mysqli_connect("localhost", "root", "", "recipedb");
    mysqli_query($conn, "SET NAMES 'utf8' ");
    $result = mysqli_query($conn, $query) or die('error: ' . mysqli_error($conn));
    $result2 = mysqli_query($conn, $query2) or die('error: '.mysqli_error($conn));
    /* create one master array of the records */
    $objects = array();
    if (mysqli_num_rows($result) > 0) {
        while ($object = mysqli_fetch_assoc($result)) {
            $objects[] = array($inner => $object);
        }
    }

    $objects2 = array();
    if (mysqli_num_rows($result2) > 0) {
        while ($object = mysqli_fetch_assoc($result2)) {
            $objects[] = array($inner2 => $object);
        }
    }
    return json_encode(array($outer => array_merge($objects, $objects2)));
}


?>