package com.futurethought.theworldview.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.futurethought.theworldview.R;
import com.futurethought.theworldview.adapters.CountryAdapter;
import com.futurethought.theworldview.data.Country;
import com.futurethought.theworldview.interfaces.ICountry;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Josh Mieczkowski on 9/27/2015.
 */
public class CountrySelectFragment extends Fragment {
    @Bind(R.id.searchView)
    SearchView searchView;

    @Bind(R.id.countriesList)
    RecyclerView countriesList;

    ICountry iCountry;

    LinearLayoutManager linearLayoutManager;

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
        linearLayoutManager = new LinearLayoutManager(getContext());
        countriesList.setLayoutManager(linearLayoutManager);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(newText.length() > 0){
                    Country country = iCountry.getSeachCountry(newText);
                    if(country != null){
                        int countryPosition = iCountry.getCountries().indexOf(country);
                        linearLayoutManager.scrollToPositionWithOffset(countryPosition, 0);
                    }else{
                        Snackbar.make(searchView, "Could not find " + newText, Snackbar.LENGTH_SHORT)
                                .show();
                    }
                }

                return false;
            }
        });


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        searchView.setIconifiedByDefault(false);

        setupCountriesAdapter();
    }

    public void setupCountriesAdapter(){
        CountryAdapter countryAdapter = new CountryAdapter(iCountry, iCountry.getCountries());
        countriesList.setAdapter(countryAdapter);
    }


}
