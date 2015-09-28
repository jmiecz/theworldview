package com.futurethought.theworldview.interfaces;

import com.futurethought.theworldview.data.Country;

import java.util.ArrayList;

/**
 * Created by Josh Mieczkowski on 9/27/2015.
 */
public interface ICountry {

    public ArrayList<Country> getCountries();
    public void onItemClick(Country country);
    public Country getCurrentCountry();
    public Country getSeachCountry(String search);
}
