package ron.gfycat.Https;

import android.content.Context;
import android.util.Log;

import java.net.HttpURLConnection;

import javax.net.ssl.HttpsURLConnection;

import ron.gfycat.common.G;
import ron.gfycat.db.MySQLiteHelper;
import ron.gfycat.model.Access;
import ron.gfycat.model.Pair;

/**
 * Created by RoN on 8/21/2017.
 */

public class HttpsGetAlbums extends HttpsRequestBase {

    public interface FinishAction{
        public void LoadResult(int statusCode,String result);
    }
     FinishAction fa= null;

    public HttpsGetAlbums(Context c,FinishAction fa){
        this.context=c;
        this.fa=fa;
        InitDialog();
    }
    @Override
    protected String doInBackground(String... params) {

        Log.d(G.TAG, "HttpLogin.doIn");


        String response="";

        try {

            publishProgress(10);


            MySQLiteHelper db = new MySQLiteHelper(context);
            Access acc=db.GetAccess(Access.TYPE_ACCESSTOKEN);
            Log.d(G.TAG,acc.toString());

            HttpsURLConnection httpsUrlConnection=null;

            httpsUrlConnection = httpFunctions.GetHttpsRequest(this.URL_ALBUM, "GET", null, new Pair[]{new Pair("Authorization","Bearer "+acc.getAccessToken())});
            this.statusCode = httpsUrlConnection.getResponseCode();
            Log.d(G.TAG,"respCode: "+response);
            if(this.statusCode== HttpURLConnection.HTTP_OK){
                httpFunctions.GetInputStream(httpsUrlConnection);
                response=httpFunctions.GetPage(httpsUrlConnection);
                return response;
            }



            publishProgress(50);


        }
        catch (Exception e) {
            Log.d(G.TAG, "Exception. " + e.getMessage());
            e.printStackTrace();
            //message=setErrorMesage(G.Status.Exception,1,e.getMessage());
            //this.statusCode=G.Status.Exception;

        }

        return "";
    }

    @Override
    protected void onPostExecute(String result) {



        Log.d(G.TAG, "HttpsGetAlbums.onPostExecute ");
        Log.d(G.TAG,result);
        fa.LoadResult(this.statusCode,result);


        if(progressDialogStarted){
            progressDialog.dismiss();
        }/**/

    }

}
