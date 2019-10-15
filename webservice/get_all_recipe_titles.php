<?php

include 'connection_encode.php';

$query = "SELECT id, title FROM recipetbl";
encodequery($query, "titles", "title");


