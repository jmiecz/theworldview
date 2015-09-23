package com.futurethought.theworldview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.futurethought.theworldview.adapters.CountryInfoAdapter;
import com.futurethought.theworldview.data.Country;
import com.futurethought.theworldview.helpers.ImageLoader;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by josh.mieczkowski on 9/23/2015.
 */
public class CountryActivity extends Activity {
    public static final String COUNTRY = "country";

    @Bind(R.id.imgCountryFlag)
    ImageView imgCountryFlag;

    @Bind(R.id.countryInfo)
    RecyclerView countryInfo;

    public static Intent getNewInstance(Context context, Country country){
        Intent intent = new Intent(context, CountryActivity.class);
        intent.putExtra(COUNTRY, country);

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.country_layout);
        ButterKnife.bind(this);

        countryInfo.setHasFixedSize(false);
        countryInfo.setLayoutManager(new LinearLayoutManager(this));

        Country country = getIntent().getParcelableExtra(COUNTRY);
        ImageLoader.getImage(this, country.getImgUrl(), imgCountryFlag);
        setupAdapter(country);
    }

    private void setupAdapter(Country country){
        ArrayList<String> values = new ArrayList<>();
        values.add("alpha2Code: " + country.getAlpha2Code());
        values.add("alpha3Code: " + country.getAlpha3Code());
        values.add("Name: " + country.getName());
        values.add("Native name: " + country.getNativeName());
        values.add("Capital: " + country.getCapital());
        values.add("Region: " + country.getRegion());
        values.add("Sub-region: " + country.getSubRegion());
        values.add("Population: " + country.getPopulation());
        values.add("Area: " + country.getArea());

        //TODO: Add alt spelling and borders

        CountryInfoAdapter countryInfoAdapter = new CountryInfoAdapter(values);
        countryInfo.setAdapter(countryInfoAdapter);
    }
}
