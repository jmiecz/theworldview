package com.futurethought.theworldview.adapters;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.futurethought.theworldview.R;
import com.futurethought.theworldview.data.Country;
import com.futurethought.theworldview.viewHolders.CountryRowViewHolder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Josh Mieczkowski on 9/21/2015.
 */
public class CountryAdapter extends RecyclerView.Adapter<CountryRowViewHolder>{
    private ArrayList<Country> countries;

    public CountryAdapter(ArrayList<Country> countries) {
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
        Country country = countries.get(position);
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("http")
                .authority("www.geonames.org")
                .appendPath("flags")
                .appendPath("x")
                .appendPath(country.getAlpha2Code().toLowerCase() + ".gif");
        String imgUrl = builder.build().toString();

        holder.txtCountryName.setText(country.getName());
        Picasso.with(holder.itemView.getContext())
                .load(imgUrl)
                .error(R.drawable.error)
                .placeholder(R.drawable.progress_img)
                .into(holder.imgCountryFlag);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Create new activity displaying country info
            }
        });
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }
}
