package com.futurethought.theworldview.viewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.futurethought.theworldview.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Josh Mieczkowski on 9/21/2015.
 */
public class CountryRowViewHolder extends RecyclerView.ViewHolder{
    @Bind(R.id.imgCountryFlag)
    public ImageView imgCountryFlag;

    @Bind(R.id.txtCountryName)
    public TextView txtCountryName;

    public CountryRowViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
