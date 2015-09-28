package com.futurethought.theworldview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.futurethought.theworldview.data.Country;
import com.futurethought.theworldview.data.ServiceFactory;
import com.futurethought.theworldview.fragments.CountryDetailsFragment;
import com.futurethought.theworldview.fragments.CountrySelectFragment;
import com.futurethought.theworldview.interfaces.CountryService;
import com.futurethought.theworldview.interfaces.ICountry;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements ICountry {
    private static final String COUNTRIES_TAG = "countriesTag";
    private static final String CURRENT_COUNTRY_TAG = "currentCountryTag";

    private ArrayList<Country> countries = new ArrayList<>();
    private Country currentCountry = new Country();

    @Bind(R.id.mainLayout)
    View mainLayout;

    @Bind(R.id.frameRight)
    @Nullable
    FrameLayout frameRight;

    Snackbar snackLoading;

    private boolean isTablet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        isTablet = getResources().getBoolean(R.bool.isTablet);

        if(savedInstanceState == null){
            downloadCountries();
        }else{
            countries = savedInstanceState.getParcelableArrayList(COUNTRIES_TAG);
            currentCountry = savedInstanceState.getParcelable(CURRENT_COUNTRY_TAG);

            if(countries == null || countries.size() == 0){
                downloadCountries();
            }
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(COUNTRIES_TAG, countries);
        outState.putParcelable(CURRENT_COUNTRY_TAG, currentCountry);
    }

    private void downloadCountries(){
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
                        attachCountrySelect();
                        snackLoading.dismiss();
                    }
                });
    }

    public void attachCountrySelect(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment countrySelectFragment = fragmentManager.findFragmentByTag(CountrySelectFragment.class.getName());

        if(countrySelectFragment != null){
            return;
        }

        countrySelectFragment = new CountrySelectFragment();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLeft, countrySelectFragment, CountrySelectFragment.class.getName());
        fragmentTransaction.commit();
    }

    public void attachCountryDetails(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        CountryDetailsFragment countryDetailsFragment = (CountryDetailsFragment)fragmentManager.findFragmentByTag(CountryDetailsFragment.class.getName());

        if(countryDetailsFragment != null){
            countryDetailsFragment.setupAdapter(currentCountry);
            return;
        }

        countryDetailsFragment = new CountryDetailsFragment();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if(isTablet){
            frameRight.setVisibility(View.VISIBLE);
            fragmentTransaction.replace(R.id.frameRight, countryDetailsFragment, CountryDetailsFragment.class.getName());
        }else{
            fragmentTransaction.replace(R.id.frameLeft, countryDetailsFragment, CountryDetailsFragment.class.getName());
            fragmentTransaction.addToBackStack(CountryDetailsFragment.class.getName());
        }

        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

        fragmentTransaction.commit();
    }

    @Override
    public ArrayList<Country> getCountries(){
        return countries;
    }


    @Override
    public void onItemClick(Country country) {
        currentCountry = country;
        attachCountryDetails();
    }

    public Country getCurrentCountry(){
        return currentCountry;
    }

}
