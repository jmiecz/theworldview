package com.futurethought.theworldview.helpers;

import android.content.Context;
import android.widget.ImageView;

import com.futurethought.theworldview.R;
import com.squareup.picasso.Picasso;

/**
 * Created by josh.mieczkowski on 9/23/2015.
 */
public class ImageLoader {

    public static void getImage(Context context, String imgUrl, ImageView imageView){
        Picasso.with(context)
                .load(imgUrl)
                .error(R.drawable.error)
                .placeholder(R.drawable.progress_img)
                .into(imageView);
    }
}
