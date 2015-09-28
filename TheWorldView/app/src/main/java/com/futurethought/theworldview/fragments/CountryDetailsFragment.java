package com.futurethought.theworldview.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.futurethought.theworldview.R;
import com.futurethought.theworldview.adapters.CountryInfoAdapter;
import com.futurethought.theworldview.data.Country;
import com.futurethought.theworldview.helpers.ImageLoader;
import com.futurethought.theworldview.interfaces.ICountry;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Josh Mieczkowski on 9/27/2015.
 */
public class CountryDetailsFragment extends Fragment {
    @Bind(R.id.imgCountryFlag)
    ImageView imgCountryFlag;

    @Bind(R.id.countryInfo)
    RecyclerView countryInfo;

    ICountry iCountry;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        iCountry = (ICountry) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.country_layout, container, false);
        ButterKnife.bind(this, view);

        countryInfo.setHasFixedSize(false);
        countryInfo.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupAdapter(iCountry.getCurrentCountry());
    }

    public void setupAdapter(Country country){
        ImageLoader.getImage(getContext(), country.getImgUrl(), imgCountryFlag);

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
