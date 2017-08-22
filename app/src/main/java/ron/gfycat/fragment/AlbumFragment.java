package ron.gfycat.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import ron.gfycat.Detail;
import ron.gfycat.HomeActivity;
import ron.gfycat.Https.HttpsGetAlbums;
import ron.gfycat.Https.HttpsImageLoader;
import ron.gfycat.R;
import ron.gfycat.adapter.CustomAdapter;
import ron.gfycat.common.G;
import ron.gfycat.model.Album;

import static android.R.attr.tag;
import static java.net.HttpURLConnection.HTTP_OK;
import static ron.gfycat.common.G.albumes;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AlbumFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AlbumFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AlbumFragment extends Fragment {


    private OnFragmentInteractionListener mListener;

    ListView listView;
    TableLayout table;
    private static CustomAdapter adapter;
    private int orientation;
    public static boolean forcereload=false;


    public AlbumFragment() {
        // Required empty public constructor
    }


    public static AlbumFragment newInstance(String param1, String param2) {
        AlbumFragment fragment = new AlbumFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =inflater.inflate(R.layout.album_list, container, false);

        orientation=getActivity().getResources().getConfiguration().orientation;
        listView =(ListView)view.findViewById(R.id.album_list);
        table = (TableLayout) view.findViewById(R.id.mainTable);

        SharedPreferences data = getActivity().getSharedPreferences("data", 0);

       String user=data.getString("user","");


        ((TextView)view.findViewById(R.id.lista_title)).setText(user);










        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /*if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onResume() {
        super.onResume();
        //LoadAlbumes("");
        if(G.albumes==null || G.albumes.size()==0 || forcereload){

            Log.d(G.TAG," null o 0 HttpsGetAlbums");
            new HttpsGetAlbums(getActivity(), new HttpsGetAlbums.FinishAction() {
                @Override
                public void LoadResult(int statusCode, String result) {
                    if(statusCode==HTTP_OK)
                        SetAlbumArray(result);
                    else
                    {}
                }
            }).execute();
            forcereload=false;
        }
        else {
            Log.d(G.TAG,"NO  null o 0 SetAlbumArray empty");
            SetAlbumArray("");
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void SetAlbumArray(String albumesJson){
        Log.d(G.TAG,"SetAlbumArray");

        if(albumesJson.length()>0){
            G.albumes= new ArrayList<>();
            //="{\"id\":\"2\",\"published\":1,\"nodes\":[{\"id\":\"ad4e9ab2663648e1f89c6af54feec840\",\"title\":\"New Folder\",\"published\":1,\"nodes\":[{\"id\":\"49e0acaf0db12bd50ad074d5cb8e5148\",\"title\":\"inside Album\",\"description\":\"Album inside folder\",\"linkText\":\"inside_album\",\"tags\":[\"t1\",\"t2\",\"t3\"],\"folderSubType\":\"Album\",\"published\":1,\"nodes\":[]},{\"id\":\"c68263c4bf588bd0ded536933fe527f6\",\"title\":\"Inside Album2\",\"linkText\":\"inside_album2\",\"folderSubType\":\"Album\",\"published\":1,\"nodes\":[]}]},{\"id\":\"a65c8acdf9819ccd3ad8b405952d3bd2\",\"title\":\"Album1\",\"description\":\"Descripci\\u00f3n Album 1.\",\"linkText\":\"album1\",\"tags\":[\"tag1\",\"tag2\",\"tag3\"],\"folderSubType\":\"Album\",\"coverImageUrl\":\"https:\\/\\/thumbs.gfycat.com\\/JealousFreeArgali-poster.jpg\",\"coverImageUrl-mobile\":\"https:\\/\\/thumbs.gfycat.com\\/JealousFreeArgali-mobile.jpg\",\"width\":500,\"height\":362,\"mp4Url\":\"https:\\/\\/zippy.gfycat.com\\/JealousFreeArgali.mp4\",\"webmUrl\":\"https:\\/\\/zippy.gfycat.com\\/JealousFreeArgali.webm\",\"webpUrl\":\"https:\\/\\/thumbs.gfycat.com\\/JealousFreeArgali.webp\",\"mobileUrl\":\"https:\\/\\/thumbs.gfycat.com\\/JealousFreeArgali-mobile.mp4\",\"mobilePosterUrl\":\"https:\\/\\/thumbs.gfycat.com\\/JealousFreeArgali-mobile.jpg\",\"posterUrl\":\"https:\\/\\/thumbs.gfycat.com\\/JealousFreeArgali-poster.jpg\",\"thumb360Url\":\"https:\\/\\/thumbs.gfycat.com\\/JealousFreeArgali-360.mp4\",\"thumb360PosterUrl\":\"https:\\/\\/thumbs.gfycat.com\\/JealousFreeArgali-thumb360.jpg\",\"thumb100PosterUrl\":\"https:\\/\\/thumbs.gfycat.com\\/JealousFreeArgali-thumb100.jpg\",\"max5mbGif\":\"https:\\/\\/thumbs.gfycat.com\\/JealousFreeArgali-size_restricted.gif\",\"max2mbGif\":\"https:\\/\\/thumbs.gfycat.com\\/JealousFreeArgali-small.gif\",\"miniUrl\":\"https:\\/\\/thumbs.gfycat.com\\/JealousFreeArgali-mini.mp4\",\"miniPosterUrl\":\"https:\\/\\/thumbs.gfycat.com\\/JealousFreeArgali-mini.jpg\",\"mjpgUrl\":\"https:\\/\\/thumbs.gfycat.com\\/JealousFreeArgali.mjpg\",\"gifUrl\":\"https:\\/\\/zippy.gfycat.com\\/JealousFreeArgali.gif\",\"published\":1,\"nodes\":[]},{\"id\":\"6c9d36c147b0acb40437b968f1e3dc0c\",\"title\":\"Album 3\",\"description\":\"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\\u0027s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries.\",\"linkText\":\"album_3\",\"tags\":[\"abc\",\"def\",\"ghi\"],\"folderSubType\":\"Album\",\"published\":1,\"nodes\":[]},{\"id\":\"16330919cf837eadf5b867985db67228\",\"title\":\"Album 2\",\"description\":\"Album 2 descripci\\u00f3n.\",\"linkText\":\"album_2\",\"tags\":[\"tag2\",\"tag3\",\"tag4\"],\"folderSubType\":\"Album\",\"coverImageUrl\":\"https:\\/\\/thumbs.gfycat.com\\/UnrulyBigheartedDuckling-poster.jpg\",\"coverImageUrl-mobile\":\"https:\\/\\/thumbs.gfycat.com\\/UnrulyBigheartedDuckling-mobile.jpg\",\"width\":498,\"height\":280,\"mp4Url\":\"https:\\/\\/zippy.gfycat.com\\/UnrulyBigheartedDuckling.mp4\",\"webmUrl\":\"https:\\/\\/zippy.gfycat.com\\/UnrulyBigheartedDuckling.webm\",\"webpUrl\":\"https:\\/\\/thumbs.gfycat.com\\/UnrulyBigheartedDuckling.webp\",\"mobileUrl\":\"https:\\/\\/thumbs.gfycat.com\\/UnrulyBigheartedDuckling-mobile.mp4\",\"mobilePosterUrl\":\"https:\\/\\/thumbs.gfycat.com\\/UnrulyBigheartedDuckling-mobile.jpg\",\"posterUrl\":\"https:\\/\\/thumbs.gfycat.com\\/UnrulyBigheartedDuckling-poster.jpg\",\"thumb360Url\":\"https:\\/\\/thumbs.gfycat.com\\/UnrulyBigheartedDuckling-360.mp4\",\"thumb360PosterUrl\":\"https:\\/\\/thumbs.gfycat.com\\/UnrulyBigheartedDuckling-thumb360.jpg\",\"thumb100PosterUrl\":\"https:\\/\\/thumbs.gfycat.com\\/UnrulyBigheartedDuckling-thumb100.jpg\",\"max5mbGif\":\"https:\\/\\/thumbs.gfycat.com\\/UnrulyBigheartedDuckling-size_restricted.gif\",\"max2mbGif\":\"https:\\/\\/thumbs.gfycat.com\\/UnrulyBigheartedDuckling-small.gif\",\"miniUrl\":\"https:\\/\\/thumbs.gfycat.com\\/UnrulyBigheartedDuckling-mini.mp4\",\"miniPosterUrl\":\"https:\\/\\/thumbs.gfycat.com\\/UnrulyBigheartedDuckling-mini.jpg\",\"mjpgUrl\":\"https:\\/\\/thumbs.gfycat.com\\/UnrulyBigheartedDuckling.mjpg\",\"gifUrl\":\"https:\\/\\/zippy.gfycat.com\\/UnrulyBigheartedDuckling.gif\",\"published\":0,\"nodes\":[]},{\"id\":\"38123dcdfe8c64b90619bffa2d5a1468\",\"title\":\"Empty Album\",\"description\":\"Empty\",\"linkText\":\"empty_album\",\"tags\":[\"album\",\"empty\",\"test\"],\"folderSubType\":\"Album\",\"published\":1,\"nodes\":[]}]}";
            try {
                if(albumesJson.length()>3)
                    albumesJson=albumesJson.substring(1,albumesJson.length()-1);

                JSONObject mainobj = new JSONObject(albumesJson);
                JSONArray albumesArray = mainobj.getJSONArray("nodes");
                LoadAlbumes(albumesArray,"");
            }catch(Exception e){e.printStackTrace();}
        }
        else{


        }


        Log.d(G.TAG,"albumes: "+albumes.size());



        Log.d(G.TAG,"getActivity()"+(getActivity()==null));
        if(getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
        {
            adapter= new CustomAdapter(albumes,getActivity());
            listView.setAdapter(adapter);

        } else {
            // Landscape Mode
            table.removeAllViews();
            TableRow row = new TableRow(getActivity());
            row.setGravity(Gravity.CENTER_HORIZONTAL);
            int optionscounter = 0;
            LayoutInflater inflater =getActivity().getLayoutInflater();
            final HomeActivity ha = (HomeActivity) getActivity();


            int index=0;
            for(Album a : G.albumes){
                View cell = inflater.inflate(R.layout.partial_table_cell, row, false);
                ((TextView)cell.findViewById(R.id.tablecell_text_title)).setText(a.getTitle());
                ((TextView)cell.findViewById(R.id.tablecell_text_tags)).setText(a.getTagsString());

                final ImageView img= (ImageView) cell.findViewById(R.id.tablecell_image);
                String imgurl=a.getThumb100PosterUrl();
                if(imgurl!=null && imgurl.trim().length()>5) {
                    Log.d(G.TAG,"Load from adapter");
                    new HttpsImageLoader(ha, new HttpsImageLoader.LoadImage() {
                        @Override
                        public void LoadImage(Bitmap bm) {

                            Log.d(G.TAG,"LoadImage ");
                            if (bm != null)
                                img.setImageBitmap(Bitmap.createScaledBitmap(bm, 100, 50, true));
                        }
                    }).execute(imgurl);
                }
                else{
                    Log.d(G.TAG,"NO Load from adapter");
                }
                cell.setTag(index);
                cell.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        int position=(Integer) v.getTag();
                        Log.d(G.TAG,"Click "+position);
                        Intent det=new Intent(ha,Detail.class);

                        det.putExtra("position",position);
                        ha.startActivity(det);
                    }
                });

                row.addView(cell);
                optionscounter++;
                index++;

                if(optionscounter!=0 && optionscounter%3==0){
                    table.addView(row);
                    row = new TableRow(getActivity());
                    row.setGravity(Gravity.CENTER_HORIZONTAL);
                    optionscounter=0;
                }

            }



        }


    }

    private void LoadAlbumes(JSONArray albumesArray,String path/*String albumes*/){

        try {

            for(int i=0;i<albumesArray.length();i++){

                JSONObject _album = albumesArray.getJSONObject(i);
                String folder="folder",linktext="",description="",title="",imagemobile="";

                JSONArray tagss=null;
                String [] _tags=null;

                try{folder=_album.getString("folderSubType");} catch(Exception e){}
                linktext=getString(_album,"linkText"); //try{linktext=_album.getString("linkText");} catch(Exception e){}
                try{tagss=_album.getJSONArray("tags");_tags=gatTags(tagss);} catch(Exception e){}
                description=getString(_album,"description"); //try{description=_album.getString("description");} catch(Exception e){}


                if(!folder.equals("folder")){
                    try{imagemobile=_album.getString("coverImageUrl-mobile");} catch(Exception e){}
                    //try{imagemobile=_album.getString("coverImageUrl");} catch(Exception e){}

                }



                title=_album.getString("title");
                Album a= new Album(_album.getString("id"),description,folder,title,_tags,linktext,_album.getInt("published"),path);


                if(!folder.equals("folder")){
                    a.setCoverImageUrl_mobile(imagemobile);
                    a.setCoverImageUrl(getString(_album,"coverImageUrl"));
                    a.setMp4Url(getString(_album,"mp4Url"));
                    a.setWebmUrl(getString(_album,"webmUrl"));
                    a.setWebpUrl(getString(_album,"webpUrl"));
                    a.setMobileUrl(getString(_album,"mobileUrl"));
                    a.setMobilePosterUrl(getString(_album,"mobilePosterUrl"));
                    a.setPosterUrl(getString(_album,"posterUrl"));
                    a.setThumb360Url(getString(_album,"thumb360Url"));
                    a.setThumb360PosterUrl(getString(_album,"thumb360PosterUrl"));
                    a.setThumb100PosterUrl(getString(_album,"thumb100PosterUrl"));
                    a.setMax5mbGif(getString(_album,"max5mbGif"));
                    a.setMax2mbGif(getString(_album,"max2mbGif"));
                    a.setMiniUrl(getString(_album,"miniUrl"));
                    a.setMiniPosterUrl(getString(_album,"miniPosterUrl"));
                    a.setMjpgUrl(getString(_album,"mjpgUrl"));
                    a.setGifUrl(getString(_album,"gifUrl"));
                    a.setWidth(getInt(_album,"width"));
                    a.setHeight(getInt(_album,"height"));


                    G.albumes.add(a);
                }

                Log.d(G.TAG,a.toString());
                if(folder.equals("folder")){
                    JSONArray insideAlbums = _album.getJSONArray("nodes");
                    LoadAlbumes(insideAlbums,path+"/"+title);
                }

            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }


    }
    private String [] gatTags(JSONArray tagsArray){
        String[] arr = new String[tagsArray.length()];

        try {
            for (int i = 0; i < tagsArray.length(); i++) {
                arr[i] = tagsArray.getString(i);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return  arr;
    }
    private String getString(JSONObject obj,String key){
        try{return obj.has(key)?obj.getString(key):"";} catch(Exception e){e.printStackTrace();}
        return "";
    }
    private int getInt(JSONObject obj,String key){
        try{return obj.has(key)?obj.getInt(key):0;} catch(Exception e){e.printStackTrace();}
        return 0;
    }
}
