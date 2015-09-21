package com.futurethought.theworldview;

import android.app.Activity;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.futurethought.theworldview.adapters.CountryAdapter;
import com.futurethought.theworldview.data.Country;
import com.futurethought.theworldview.data.ServiceFactory;
import com.futurethought.theworldview.interfaces.CountryService;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private static final String COUNTRIES_TAG = "countriesTag";

    private ArrayList<Country> countries = new ArrayList<>();

    @Bind(R.id.mainLayout) View mainLayout;
    @Bind(R.id.countriesList) RecyclerView countriesList;

    Snackbar snackLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        countriesList.setHasFixedSize(false);
        countriesList.setLayoutManager(new LinearLayoutManager(this));

        if(savedInstanceState == null){
            snackLoading = Snackbar.make(mainLayout, "Please wait, currently grabbing countries", Snackbar.LENGTH_INDEFINITE);
            snackLoading.show();

            CountryService countryService = ServiceFactory.createRetrofitService(CountryService.class, CountryService.SERVICE_ENDPOINT);
            countryService.getCountries()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<ArrayList<Country>>() {
                        @Override
                        public void onCompleted() {
                            snackLoading.dismiss();
                        }

                        @Override
                        public void onError(Throwable e) {
                            snackLoading.setText("Error in downloading countries")
                                    .setDuration(Snackbar.LENGTH_SHORT)
                                    .show();
                            Log.wtf("CountryOnError", e);
                        }

                        @Override
                        public void onNext(ArrayList<Country> countries) {
                            MainActivity.this.countries = countries;
                            setupCountriesAdapter();
                        }
                    });
        }else{
            countries = savedInstanceState.getParcelableArrayList(COUNTRIES_TAG);
            setupCountriesAdapter();
        }


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(COUNTRIES_TAG, countries);
    }

    public void setupCountriesAdapter(){
        CountryAdapter countryAdapter = new CountryAdapter(countries);
        countriesList.setAdapter(countryAdapter);
    }
}
