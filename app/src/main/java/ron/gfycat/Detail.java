package ron.gfycat;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import ron.gfycat.Https.HttpsImageLoader;
import ron.gfycat.common.G;
import ron.gfycat.model.Album;

import static java.util.ResourceBundle.getBundle;

public class Detail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Bundle data = getIntent().getExtras();
        if(data!=null){

            int pos=data.getInt("position");
            Log.d(G.TAG,"pos "+pos);
            Album album=G.albumes.get(pos);

            ((TextView)findViewById(R.id.detail_title)).setText(album.getPath()+"/"+album.getTitle());

            ((TextView)findViewById(R.id.detail_object)).setText(Html.fromHtml(album.toString2()));

            final ImageView iv= (ImageView)findViewById(R.id.detail_image);

            String imgurl=album.getCoverImageUrl_mobile();
            if(imgurl!=null && imgurl.trim().length()>5) {
                Log.d(G.TAG,"Load from adapter");
                new HttpsImageLoader(this, new HttpsImageLoader.LoadImage() {
                    @Override
                    public void LoadImage(Bitmap bm) {

                        Log.d(G.TAG,"LoadImage ");
                        if (bm != null)
                            iv.setImageBitmap(Bitmap.createScaledBitmap(bm, 200, 100, true));
                    }
                }).execute(imgurl);
            }
            else{
                Log.d(G.TAG,"NO Loaded from activity");
            }
        }

    }


}
