package ron.gfycat.Https;

import android.content.Context;
import android.text.Html;
import android.util.Log;

import java.net.HttpURLConnection;

import javax.net.ssl.HttpsURLConnection;

import ron.gfycat.common.G;
import ron.gfycat.db.MySQLiteHelper;
import ron.gfycat.model.Pair;

/**
 * Created by RoN on 8/21/2017.
 */

public class HttpsGetToken extends HttpsRequestBase {
    public interface FinishAction{
        public void ResutAction(int status,String jsonResponse);
    }

    public FinishAction fa=null;

    public HttpsGetToken(Context c,FinishAction faa){
        Log.d(G.TAG,"HttpsGetToken");
        this.context=c;
        this.fa=faa;
        InitDialog();
    }

    @Override
    protected String doInBackground(String... params) {
        //0 Token Request type
        //1 User
        //2Password
        Log.d(G.TAG, "HttpLogin.doIn");


        String response="";

        try {

            publishProgress(10);
            String posData="  {\n" +
                    "    \"grant_type\":\"password\",\n" +
                    "    \"client_id\":\""+this.client_id+"\",\n" +
                    "    \"client_secret\":\""+this.client_secret+"\",\n" +
                    "    \"username\":\""+params[1]+"\",\n" +
                    "    \"password\":\""+params[2]+"\"\n" +
                    "  }";

            //MySQLiteHelper db = new MySQLiteHelper(context);
            HttpsURLConnection httpsUrlConnection=null;

            httpsUrlConnection = httpFunctions.GetHttpsRequest(this.URL_ACCESSTOKEN, "POST", posData, null);
            this.statusCode = httpsUrlConnection.getResponseCode();
            Log.d(G.TAG,"respCode: "+response);
            if(this.statusCode==HttpURLConnection.HTTP_OK){
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
        Log.d(G.TAG, "onPostExecute ");
        Log.d(G.TAG,result);

        fa.ResutAction(this.statusCode,result);
        if(progressDialogStarted){
            progressDialog.dismiss();
        }

    }

}
