<?php
include "connection_encode.php";

$query = "SELECT * FROM tagtbl";
encodequery($query, "tags", "tag");
