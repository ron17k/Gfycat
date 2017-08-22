package ron.gfycat.Https;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.util.Log;

import ron.gfycat.common.G;
import ron.gfycat.common.HttpFunctions;
import ron.gfycat.fragment.custom.CustomProgressDialog;

/**
 * Created by RoN on 8/21/2017.
 */

public class HttpsRequestBase extends AsyncTask<String, Integer, String> {

    protected Context context;
    protected CustomProgressDialog progressDialog;
    protected boolean progressDialogStarted=false;
    protected int statusCode= 200;//OK cambiar valor por defecto
    protected String message="";
    protected final String URL_ACCESSTOKEN="https://api.gfycat.com/v1/oauth/token";
    protected final String URL_ALBUM="https://api.gfycat.com/v1/me/album-folders";

    protected  final String client_id="2_mcWbS7";
    protected  final String client_secret="C4Y7zIj0LGl0P2LiobYJYM5FGHdbWDEzuQahzU5tUkzc00UxuNoR1aPTaxp0WP9A";





    protected HttpFunctions httpFunctions;


    HttpsRequestBase() {
        Log.d(G.TAG,"HttpRequestBase");
        httpFunctions = new HttpFunctions();
    }

    protected  void InitDialog(){
        progressDialog =  new CustomProgressDialog(context);
        progressDialog.setMessage("Cargando...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setCancelable(false);
        progressDialog.setOnCancelListener(new  DialogInterface.OnCancelListener() {
            public  void  onCancel(DialogInterface dialog) {
                //Toast
            }
        });

        progressDialog.show();
        progressDialog.setProgress(5);
        progressDialogStarted=true;

    }





    @Override
    protected String doInBackground(String... params) {

        Log.d(G.TAG, "doIn");


        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        Log.d(G.TAG,"Base.onPostExecute");
        Log.d(G.TAG,"result: "+result);



    }


}

