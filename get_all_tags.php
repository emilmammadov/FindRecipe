<?php

header('Cache-Control: no-cache, must-revalidate');
header('Expires: Mon, 26 Jul 1997 05:00:00 GMT');


/* grab the posts from the db */
$query = "SELECT * FROM tagtbl";
encodequery($query);


function encodequery($query)
{
    $conn = mysqli_connect("localhost", "root", "", "recipedb");
    mysqli_query($conn, "SET NAMES 'utf8' ");
    $result = mysqli_query($conn, $query) or die('error: ' . mysqli_error($conn));
    /* create one master array of the records */
    $posts = array();
    if (mysqli_num_rows($result) > 0) {
        while ($post = mysqli_fetch_assoc($result)) {
            $posts[] = array('tag' => $post);
        }
    }
    encodearray($posts);
}


/* Encode array to JSON string */
function encodearray($posts)
{
    header('Content-type: application/json');
    echo json_encode(array('tags' => $posts));
}
