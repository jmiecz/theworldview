package com.futurethought.theworldview.data;

import retrofit.RestAdapter;
import retrofit.converter.JacksonConverter;

/**
 * Created by Josh Mieczkowski on 9/20/2015.
 */
public class ServiceFactory {
    public static <T> T createRetrofitService(final Class<T> clazz, final String endPoint) {
        final RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(endPoint)
                .setConverter(new JacksonConverter())
                .build();

        return restAdapter.create(clazz);
    }
}
