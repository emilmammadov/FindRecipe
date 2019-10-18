<?php

include 'connection_encode.php';

$query = "SELECT id, title FROM recipetbl";
echo encodequery($query, "titles", "title");


