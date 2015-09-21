package com.futurethought.theworldview.interfaces;

import com.futurethought.theworldview.data.Country;

import java.util.ArrayList;

import retrofit.http.GET;
import rx.Observable;

/**
 * Created by Josh Mieczkowski on 9/20/2015.
 */
public interface CountryService {
    String SERVICE_ENDPOINT = "https://restcountries.eu";

    @GET("/rest/v1/all")
    Observable<ArrayList<Country>> getCountries();
}
