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
public class CountryInfoRowViewHolder extends RecyclerView.ViewHolder{

    @Bind(R.id.txtInfo)
    public TextView txtInfo;

    public CountryInfoRowViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
