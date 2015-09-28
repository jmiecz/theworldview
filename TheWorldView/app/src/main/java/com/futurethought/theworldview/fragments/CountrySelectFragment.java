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

import com.futurethought.theworldview.R;
import com.futurethought.theworldview.adapters.CountryAdapter;
import com.futurethought.theworldview.interfaces.ICountry;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Josh Mieczkowski on 9/27/2015.
 */
public class CountrySelectFragment extends Fragment {
    @Bind(R.id.countriesList)
    RecyclerView countriesList;

    ICountry iCountry;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        iCountry = (ICountry) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.country_select, container, false);
        ButterKnife.bind(this, view);

        countriesList.setHasFixedSize(false);
        countriesList.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setupCountriesAdapter();
    }

    public void setupCountriesAdapter(){
        CountryAdapter countryAdapter = new CountryAdapter(iCountry, iCountry.getCountries());
        countriesList.setAdapter(countryAdapter);
    }


}
