package com.sagar.covid19map;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    private int positionCountry;
    TextView tvCases,tvRecovered,tvCritical,tvActive,tvTotalDeaths,tvTodayDeaths,tvTodayCases,tvCountry ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        positionCountry = intent.getIntExtra("position",0);

        getSupportActionBar().setTitle("Details Of "+AffectedCountries.countryModelList.get(positionCountry).getCountry());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        tvCases = findViewById(R.id.tvCases);
        tvRecovered = findViewById(R.id.tvRecovered);
        tvActive = findViewById(R.id.tvActive);
        tvCritical = findViewById(R.id.tvCritical);
        tvTodayCases = findViewById(R.id.tvTodayCases);
        tvTodayDeaths = findViewById(R.id.tvTodayDeaths);
        tvTotalDeaths = findViewById(R.id.tvTotalDeaths);
        tvCountry = findViewById(R.id.tvCountry);

        tvCountry.setText(AffectedCountries.countryModelList.get(positionCountry).getCountry());
        tvCases.setText(AffectedCountries.countryModelList.get(positionCountry).getCases());
        tvRecovered.setText(AffectedCountries.countryModelList.get(positionCountry).getRecovered());
        tvActive.setText(AffectedCountries.countryModelList.get(positionCountry).getActive());
        tvCritical.setText(AffectedCountries.countryModelList.get(positionCountry).getCritical());
        tvTodayCases.setText(AffectedCountries.countryModelList.get(positionCountry).getTodayCases());
        tvTodayDeaths.setText(AffectedCountries.countryModelList.get(positionCountry).getTodayDeaths());
        tvTotalDeaths.setText(AffectedCountries.countryModelList.get(positionCountry).getDeaths());
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
