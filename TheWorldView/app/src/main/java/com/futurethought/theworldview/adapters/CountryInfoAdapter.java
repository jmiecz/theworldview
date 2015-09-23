package com.futurethought.theworldview.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.futurethought.theworldview.R;
import com.futurethought.theworldview.data.Country;
import com.futurethought.theworldview.helpers.ImageLoader;
import com.futurethought.theworldview.interfaces.ICountry;
import com.futurethought.theworldview.viewHolders.CountryInfoRowViewHolder;
import com.futurethought.theworldview.viewHolders.CountryRowViewHolder;

import java.util.ArrayList;

/**
 * Created by Josh Mieczkowski on 9/21/2015.
 */
public class CountryInfoAdapter extends RecyclerView.Adapter<CountryInfoRowViewHolder>{
    private ArrayList<String> countryInfo;

    public CountryInfoAdapter(ArrayList<String> countryInfo) {
        this.countryInfo = countryInfo;
    }

    @Override
    public CountryInfoRowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.country_info_row, parent, false);

        return new CountryInfoRowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CountryInfoRowViewHolder holder, int position) {
        String info = countryInfo.get(position);
        holder.txtInfo.setText(info);

    }

    @Override
    public int getItemCount() {
        return countryInfo.size();
    }
}
