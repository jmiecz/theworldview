package com.futurethought.theworldview.adapters;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.futurethought.theworldview.R;
import com.futurethought.theworldview.data.Country;
import com.futurethought.theworldview.helpers.ImageLoader;
import com.futurethought.theworldview.interfaces.ICountry;
import com.futurethought.theworldview.viewHolders.CountryRowViewHolder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Josh Mieczkowski on 9/21/2015.
 */
public class CountryAdapter extends RecyclerView.Adapter<CountryRowViewHolder>{
    private ICountry iCountry;
    private ArrayList<Country> countries;

    public CountryAdapter(ICountry iCountry, ArrayList<Country> countries) {
        this.iCountry = iCountry;
        this.countries = countries;
    }

    @Override
    public CountryRowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.country_row, parent, false);

        return new CountryRowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CountryRowViewHolder holder, int position) {
        final Country country = countries.get(position);

        holder.txtCountryName.setText(country.getName());
        ImageLoader.getImage(holder.itemView.getContext(),
                country.getImgUrl(),
                holder.imgCountryFlag);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iCountry.onItemClick(country);
            }
        });
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }
}
