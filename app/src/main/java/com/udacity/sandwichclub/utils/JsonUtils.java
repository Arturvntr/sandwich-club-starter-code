package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    /* JSON structure */
    static final private String JSON_NAME = "name";
    static final private String JSON_MAIN_NAME = "mainName";
    static final private String JSON_ALSO_KNOWN_AS = "alsoKnownAs";
    static final private String JSON_PLACE_OF_ORIGIN = "placeOfOrigin";
    static final private String JSON_DESCRIPTION = "description";
    static final private String JSON_IMAGE = "image";
    static final private String JSON_INGREDIENTS = "ingredients";

    public static Sandwich parseSandwichJson(String json) throws JSONException {
        Sandwich sandwichReturned = new Sandwich();

        JSONObject sandwichJson = new JSONObject(json);

        /* getting the sandwich names */
        /* getting the sandwich main name */
        JSONObject sandwichJSONName = sandwichJson.getJSONObject(JSON_NAME);
        String sandwichMainName = sandwichJSONName.getString(JSON_MAIN_NAME);
        sandwichReturned.setMainName(sandwichMainName);

        /* getting the sandwich also known as */
        JSONArray sandwichJSONAlsoKnownAs = sandwichJSONName.getJSONArray(JSON_ALSO_KNOWN_AS);
        List<String> sandwichAlsoKnownAs = new ArrayList<>();
        for( int i = 0; i < sandwichJSONAlsoKnownAs.length(); i++)
            sandwichAlsoKnownAs.add(sandwichJSONAlsoKnownAs.getString(i));
        sandwichReturned.setAlsoKnownAs(sandwichAlsoKnownAs);

        /* getting the sandwich place of origin */
        String sandwichPlaceOfOrigin = sandwichJson.getString(JSON_PLACE_OF_ORIGIN);
        sandwichReturned.setPlaceOfOrigin(sandwichPlaceOfOrigin);

        /* getting the sandwich description */
        String sandwichDescription = sandwichJson.getString(JSON_DESCRIPTION);
        sandwichReturned.setDescription(sandwichDescription);

        /* getting the sandwich image */
        String sandwichImage = sandwichJson.getString(JSON_IMAGE);
        sandwichReturned.setImage(sandwichImage);

        /* getting the sandwich Ingredients */
        JSONArray sandwichJSONIngredients = sandwichJson.getJSONArray(JSON_INGREDIENTS);
        List<String> sandwichIngedients = new ArrayList<>();
        for( int i = 0; i < sandwichJSONIngredients.length(); i++)
            sandwichIngedients.add(sandwichJSONIngredients.getString(i));
        sandwichReturned.setIngredients(sandwichIngedients);

        return sandwichReturned;
    }
}
