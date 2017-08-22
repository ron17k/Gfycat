package ron.gfycat.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import ron.gfycat.Detail;
import ron.gfycat.HomeActivity;
import ron.gfycat.Https.HttpsImageLoader;
import ron.gfycat.R;
import ron.gfycat.common.G;
import ron.gfycat.model.Album;

import static android.R.attr.data;
import static android.R.drawable.presence_online;

/**
 * Created by RoN on 8/21/2017.
 */

public class CustomAdapter extends ArrayAdapter<Album> implements View.OnClickListener{
    private ArrayList<Album> dataSet;
    Context mContext;



    public CustomAdapter(ArrayList<Album> data, Context context) {
        super(context, R.layout.row_item, data);
        this.dataSet = data;
        this.mContext=context;

    }

    @Override
    public void onClick(View v) {
        int position=(Integer) v.getTag();
        Log.d(G.TAG,"Click "+position);
        Intent det=new Intent(mContext,Detail.class);

        det.putExtra("position",position);
        mContext.startActivity(det);


    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Album album = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view


        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView = inflater.inflate(R.layout.row_item, parent, false);
        ((TextView) convertView.findViewById(R.id.album_title)).setText(album.getTitle());
        ((TextView) convertView.findViewById(R.id.album_tags)).setText(album.getTagsString());
        final ImageView ai=(ImageView) convertView.findViewById(R.id.album_image);
        if(album.getPublished()==0)
            ((ImageView) convertView.findViewById(R.id.album_published)).setImageResource(android.R.drawable.presence_offline);

        String imgurl=album.getThumb100PosterUrl();
        if(imgurl!=null && imgurl.trim().length()>5) {
            Log.d(G.TAG,"Load from adapter");
            new HttpsImageLoader(mContext, new HttpsImageLoader.LoadImage() {
                @Override
                public void LoadImage(Bitmap bm) {

                    Log.d(G.TAG,"LoadImage ");
                    if (bm != null)
                        ai.setImageBitmap(Bitmap.createScaledBitmap(bm, 100, 50, true));
                }
            }).execute(imgurl);
        }
        else{
            Log.d(G.TAG,"NO Load from adapter");
        }

        convertView.setOnClickListener(this);
        convertView.setTag(position);
        /*convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(G.TAG,"click");
               mContext.startActivity(new Intent(mContext,Detail.class));
            }
        });*/



        return convertView;
    }


}
