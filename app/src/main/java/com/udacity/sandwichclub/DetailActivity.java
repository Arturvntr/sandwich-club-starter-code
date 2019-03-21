package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import org.json.JSONException;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];

        Sandwich sandwich;
        try {

            sandwich = JsonUtils.parseSandwichJson(json);

        } catch (JSONException e) {
            e.printStackTrace();
            closeOnError();
            return;
        }

        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {

        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
        } else {

            LinearLayout informationLinearLayout = findViewById(R.id.information);
            TextView alsoKnownLabelTextView = findViewById(R.id.also_known_label_tv);
            TextView alsoKnownTextView = findViewById(R.id.also_known_tv);
            TextView originLabelTextView = findViewById(R.id.origin_label_tv);
            TextView originTextView = findViewById(R.id.origin_tv);
            TextView ingredientsLabelTextView = findViewById(R.id.ingredients_label_tv);
            TextView ingredientsTextView = findViewById(R.id.ingredients_tv);
            TextView descriptionLabelTextView = findViewById(R.id.description_label_tv);
            TextView descriptionTextView = findViewById(R.id.description_tv);

            /* Populating also known as */
            if(sandwich.getAlsoKnownAs().size() > 0) {
                String alsoKnown = "";
                for (String name : sandwich.getAlsoKnownAs())
                    alsoKnown += name + ", ";
                alsoKnownTextView.setText(alsoKnown.replaceAll(", $", ""));
            }else{ // removing text view which is not used
                informationLinearLayout.removeView(alsoKnownLabelTextView);
                informationLinearLayout.removeView(alsoKnownTextView);
            }

            /* Populating place of origin */
            if(!sandwich.getPlaceOfOrigin().isEmpty()) {
                originTextView.setText(sandwich.getPlaceOfOrigin());
            }else { // removing text view which is not used
                informationLinearLayout.removeView(originLabelTextView);
                informationLinearLayout.removeView(originTextView);
            }

            /* Populating description */
            if(!sandwich.getDescription().isEmpty()) {
                descriptionTextView.setText(sandwich.getDescription());
            } else { // removing text view which is not used
                informationLinearLayout.removeView(descriptionLabelTextView);
                informationLinearLayout.removeView(descriptionTextView);
            }

            /* Populating ingredients */
            if(sandwich.getIngredients().size() > 0) {
                String ingredients = "";
                for (String ingredient : sandwich.getIngredients()) {
                    ingredients += "- " + ingredient + "\n";
                }
                ingredientsTextView.setText(ingredients);
            } else { // removing text view which is not used
                informationLinearLayout.removeView(ingredientsLabelTextView);
                informationLinearLayout.removeView(ingredientsTextView);
            }
        }
    }
}
