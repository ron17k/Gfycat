package ron.gfycat.common;

import android.util.Log;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;


import ron.gfycat.model.Pair;

/**
 * Created by RoN on 8/21/2017.
 */

public class HttpFunctions {

    private String H_UserAgent = "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:52.0) Gecko/20100101 Firefox/52.0";
    private String H_Accept = "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8";
    private String H_AcceptL = "en-US,en;q=0.5";
    private String H_AcceptE = "gzip, deflate";
    private String H_NoCache = "no-cache";






    public HttpsURLConnection GetHttpsRequest(String urlS, String method, String urlParameters, Pair[] headers) {
        Log.d(G.TAG, "-----------------------------------------------");
        Log.d(G.TAG, "GetHttpsRequest " + method + urlS + " " + urlParameters);
        //InputStream inputStream=null;

        HttpsURLConnection httpsUrlConnection = null;
        try {
            String urlString = System.getProperty("url", urlS);
            URL url = new URL(urlString);
            URLConnection urlConnection = url.openConnection();
            httpsUrlConnection = (HttpsURLConnection) urlConnection;
            httpsUrlConnection.setRequestMethod(method);
            httpsUrlConnection.setInstanceFollowRedirects(false);
            httpsUrlConnection.setRequestProperty("User-Agent", H_UserAgent);
            httpsUrlConnection.setRequestProperty("Accept", H_Accept);
            httpsUrlConnection.setRequestProperty("Accept-Language", H_AcceptL);
            //httpsUrlConnection.setRequestProperty("Accept-Encoding", H_AcceptE);
            httpsUrlConnection.setRequestProperty("Pragma", H_NoCache);
            httpsUrlConnection.setRequestProperty("Cache-Control", H_NoCache);
            httpsUrlConnection.setRequestProperty("Upgrade-Insecure-Requests", "1");
            httpsUrlConnection.setRequestProperty("Connection", "keep-alive");

            httpsUrlConnection.setConnectTimeout(15000);
            httpsUrlConnection.setRequestMethod(method);



            if (headers != null) {
                for (Pair h : headers) {
                    httpsUrlConnection.setRequestProperty(h.Key, h.Value);
                }

            }
            /*if(referer!="")
                httpsUrlConnection.setRequestProperty("Referer", referer);*/

            if (method == "POST") {
                byte[] postData = urlParameters.getBytes();
                int postDataLength = postData.length;

                httpsUrlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                httpsUrlConnection.setRequestProperty("charset", "utf-8");
                httpsUrlConnection.setRequestProperty("Content-Length", Integer.toString(postDataLength));
                httpsUrlConnection.setUseCaches(false);
                httpsUrlConnection.setDoOutput(true);
                httpsUrlConnection.getOutputStream().write(postData);
            }


            //Log.d(G.TAG,"before getInputStream ");
            //inputStream[0] = httpsUrlConnection.getInputStream();

            // cookies = httpsUrlConnection.getHeaderFields().get("Set-Cookie");
            ///*Log.d(G.TAG,"Get Cookies Size: "+cookies.size());
            // for(int i=0;i<cookies.size();i++){
            //     Log.d(G.TAG,cookies.get(i));

            // }*/
            //Log.d(G.TAG,"Response Code: "+httpsUrlConnection.getResponseCode());


        } catch (MalformedURLException mue) {
            Log.d(G.TAG, "Malformedurl ex" + mue.getMessage());
        } catch (IOException ioe) {
            ioe.printStackTrace();
            Log.d(G.TAG, "IOException ex" + ioe.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(G.TAG, "Exception ex" + e.getMessage());
        }


        //return inputStream;
        return httpsUrlConnection;
    }

    public HttpsURLConnection GetHttpsRequest(String urlS, String method) {
        return GetHttpsRequest(urlS, method, "", null);

    }




    public InputStream GetInputStream(HttpURLConnection httpURLConection) {

        InputStream result = null;
        try {
            Log.d(G.TAG, "Connect");
            httpURLConection.connect();
            Log.d(G.TAG, "before getInputStream ");
            result = httpURLConection.getInputStream();

            /**/
            List<String> ccookies = httpURLConection.getHeaderFields().get("Set-Cookie");

            Log.d(G.TAG, "Response Code: " + httpURLConection.getResponseCode());


            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String GetPage(HttpURLConnection httpURLConection) {
        Log.d(G.TAG, "GetPage: " + httpURLConection.getURL().toString());
        InputStream inStreamResult = GetInputStream(httpURLConection);
        String line = null;
        StringBuilder sb = new StringBuilder();


        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inStreamResult,"iso-8859-1"));
            while ((line = reader.readLine()) != null) {
                // System.out.println(line);
                //Log.d("RoN",line);
                sb.append(line);
            }
        } catch (IOException e) {
            Log.d(G.TAG,"IOException Catch"+ e.getMessage());
            e.printStackTrace();

        }
        //Log.d("RoN", sb.toString());
        return sb.toString();
    }


}

