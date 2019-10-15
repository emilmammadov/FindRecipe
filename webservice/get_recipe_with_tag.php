<?php

include 'connection_encode.php';

if (isset($_POST["recipes"])) {
    
    $den = str_replace('[','',$_POST["recipes"]);
    $den = str_replace(']','',$den);
    $arr = explode(', ', $den);

    $query = "SELECT * FROM recipetbl WHERE id IN (SELECT t1.recipe_id FROM (SELECT recipe_id, COUNT(*) as counts FROM tagrelationtbl WHERE tag_id IN (".implode(',', $arr).") GROUP BY recipe_id) t1 JOIN (SELECT recipe_id,COUNT(*) as countGeneral FROM tagrelationtbl GROUP BY recipe_id) t2 ON t1.recipe_id=t2.recipe_id AND t1.counts >= t2.countGeneral)";

    encodequery($query, "recipes","recipe");

} else {
    echo json_encode(array('recipes' => "recipes"));
}