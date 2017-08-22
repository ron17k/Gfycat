package ron.gfycat.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.Calendar;

import ron.gfycat.db.MySQLiteHelper;
import ron.gfycat.model.Access;

/**
 * Created by RoN on 8/21/2017.
 */

public class Utils {

    public static boolean checkActiveSession(Context c){
        MySQLiteHelper db = new MySQLiteHelper(c);
        try {

            Access a = db.GetAccess(Access.TYPE_ACCESSTOKEN);
            if (a == null) {
                return false;

            } else {
                //TODO: validar tiempo del token y refrescar de ser necesario.

                Calendar current = Calendar.getInstance();
                Calendar created = Calendar.getInstance();
                created.setTimeInMillis(Long.parseLong(a.getDate()));

                created.add(Calendar.SECOND,Integer.parseInt(a.getAccessTokenExpire()));

                Log.d(G.TAG,current.getTimeInMillis()+" "+created.getTimeInMillis());

                return current.getTimeInMillis()<created.getTimeInMillis();
                //return true;

            }
        }catch(Exception ex){
            ex.printStackTrace();

        }
        finally {
            db.close();
        }





        return false;
    }
}
