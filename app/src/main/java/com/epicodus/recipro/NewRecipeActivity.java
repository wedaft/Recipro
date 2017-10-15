package com.epicodus.recipro;

import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NewRecipeActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.addIngredientButton) Button mAddIngredientButton;
    @Bind(R.id.addInstructionsButton) Button mAddInstructionsButton;
    @Bind(R.id.ingredientCount) EditText mIngredientCount;
    @Bind(R.id.ingredientMeasurement) Spinner mIngredientMeasurement;
    @Bind(R.id.ingredientName) EditText mIngredientName;
    @Bind(R.id.listView) ListView mListView;
    private ArrayList<String> ingredientList;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_recipe);
        ButterKnife.bind(this);
        ingredientList = new ArrayList<String>();
        mAddInstructionsButton.setOnClickListener(this);
        mAddIngredientButton.setOnClickListener(this);
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, ingredientList);
        mListView.setAdapter(adapter);
    }

    public void onClick(View v) {
        if(v == mAddIngredientButton) {
            if(mIngredientName.getText().toString().trim().equalsIgnoreCase("") || mIngredientMeasurement.getSelectedItem().toString().trim().equalsIgnoreCase("") || mIngredientCount.getText().toString().trim().equalsIgnoreCase("")) {
                Toast answerStatus = Toast.makeText(NewRecipeActivity.this, "Fill out all fields", Toast.LENGTH_LONG);
                answerStatus.show();
            } else {
                String ingredient = createIngredientString();
                ingredientList.add(ingredient);
                adapter.notifyDataSetChanged();
                clearIngredientInputs();
                Log.i("NewRecipeActivity", "List includes: " + ingredientList);
            }
        }
        if(v == mAddInstructionsButton) {
            FragmentManager fm = getFragmentManager();
            InstructionsFragment instructionsFragment = new InstructionsFragment();
            instructionsFragment.show(fm, "Sample Fragment");
        }
    }

    public String createIngredientString() {
        String name = mIngredientName.getText().toString();
        String measurement = mIngredientMeasurement.getSelectedItem().toString();
        String count = mIngredientCount.getText().toString();
        String ingredient = String.format("%s %s %s", count, measurement, name);
        return ingredient;

    }

    public void clearIngredientInputs() {
        mIngredientName.setText("");
        mIngredientCount.setText("");
        mIngredientMeasurement.setSelection(0);
    }

}