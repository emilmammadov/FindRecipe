<?php
include "connection_encode.php";

$query = "SELECT * FROM tagtbl";
echo encodequery($query, "tags", "tag");
