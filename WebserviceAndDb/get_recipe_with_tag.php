<?php

include 'connection_encode_array.php';

if (isset($_POST["recipes"])) {
    
    $den = str_replace('[','',$_POST["recipes"]);
    $den = str_replace(']','',$den);
    $arr = explode(', ', $den);

    $query = "SELECT * FROM recipetbl WHERE id IN (SELECT t1.recipe_id FROM (SELECT recipe_id, COUNT(*) as counts FROM tagrelationtbl WHERE tag_id IN (".implode(',', $arr).") GROUP BY recipe_id) t1 JOIN (SELECT recipe_id,COUNT(*) as countGeneral FROM tagrelationtbl GROUP BY recipe_id) t2 ON t1.recipe_id=t2.recipe_id";
    $q1=" AND t1.counts >= t2.countGeneral)";
    $q2=" AND t1.counts < t2.countGeneral)";
    $justIngredient = encodequery($query.$q1, $query.$q2, "recipes","recipe", "deneme");
    //$maybeIngredient = encodequery($query.")", "recipes","deneme");
    //echo $maybeIngredient;
    //echo json_encode(array_merge(json_decode($justIngredient, true),json_decode($maybeIngredient, true)));

    //echo json_encode(array("recipes" => $maybeIngredient));

    echo $justIngredient;

} else {
    echo json_encode(array('recipes' => "recipes"));
}