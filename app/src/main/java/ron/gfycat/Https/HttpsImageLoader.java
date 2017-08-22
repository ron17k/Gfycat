package ron.gfycat.Https;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

import javax.net.ssl.HttpsURLConnection;

import ron.gfycat.common.G;
import ron.gfycat.db.MySQLiteHelper;
import ron.gfycat.model.Access;
import ron.gfycat.model.Pair;

/**
 * Created by RoN on 8/21/2017.
 */

public class HttpsImageLoader extends HttpsRequestBase {
    Bitmap image;
    public interface  LoadImage{
        public void LoadImage(Bitmap bm);
    }
    public LoadImage li=null;

    public HttpsImageLoader(Context context,LoadImage li){
        this.context=context;
        this.li=li;

    }

    @Override
    protected String doInBackground(String... params) {

        Log.d(G.TAG, "HttpsImageLoader.HttpLogin.doIn");


        String response="";

        try {

            publishProgress(10);


            MySQLiteHelper db = new MySQLiteHelper(context);
            Access acc=db.GetAccess(Access.TYPE_ACCESSTOKEN);
            Log.d(G.TAG,acc.toString());

            HttpsURLConnection httpsUrlConnection=null;

            httpsUrlConnection = httpFunctions.GetHttpsRequest(params[0], "GET", null, null);
            this.statusCode = httpsUrlConnection.getResponseCode();
            Log.d(G.TAG,"respCode: "+response);
            if(this.statusCode== HttpURLConnection.HTTP_OK){
                InputStream result=null;
                result=httpFunctions.GetInputStream(httpsUrlConnection);
                Log.d(G.TAG,(result==null)+"_result");

                byte[] bytes = readBytes(result);
                Log.d(G.TAG,bytes.length+"_bytes");
                if(bytes.length==0){
                    bytes = readBytes(result);
                    Log.d(G.TAG,bytes.length+"_bytes Intento 2");

                }
                //bitmap[0]= BitmapFactory.decodeStream(inputStream);
                image= BitmapFactory.decodeByteArray(bytes, 0,bytes.length);

                return "";
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


        Log.d(G.TAG, "HttpsImageLoader.onPostExecute ");
        Log.d(G.TAG,result);
        li.LoadImage(image);

        /*fa.ResutAction(this.statusCode,result);
        if(progressDialogStarted){
            progressDialog.dismiss();
        }*/

    }
    private byte[] readBytes(InputStream inputStream)  {
        // this dynamically extends to take the bytes you read
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        try {


            // this is storage overwritten on each iteration with bytes
            int bufferSize = 16384;
            byte[] buffer = new byte[bufferSize];

            // we need to know how may bytes were read to write them to the byteBuffer
            int len = 0;

            Log.d(G.TAG,"inputStream.available() "+inputStream.available());
            /*while ((len = inputStream.read(buffer)) != -1) {
                Log.d(G.TAG,"Readaed "+len);
                byteBuffer.write(buffer, 0, len);
            }*/
            while ((len = inputStream.read(buffer, 0, buffer.length)) != -1) {
                byteBuffer.write(buffer, 0, len);
            }

            // and then we can return your byte array.
            return byteBuffer.toByteArray();
        } catch (IOException e) {
            Log.d(G.TAG," EX readBytes. "+e.getMessage());
            e.printStackTrace();
        }
        return byteBuffer.toByteArray();
        //return new byte[0];
    }
}
