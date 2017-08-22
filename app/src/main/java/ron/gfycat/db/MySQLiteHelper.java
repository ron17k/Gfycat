package ron.gfycat.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import ron.gfycat.common.G;
import ron.gfycat.model.Access;

/**
 * Created by RoN on 8/21/2017.
 */

public class MySQLiteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "DB";
    private static final int DATABASE_VERSION = 1;

    private final String TABLE_ACCESS = "APIAccess";
    private final String KEY_ID = "id";
    private final String ACCESS_TOKEN = "token";
    private final String ACCESS_EXPIRES = "tokenExpires";
    //private final String ACCESS_RTOKEN = "refreshToken";
    //private final String ACCESS_RTOKENEXPIRES = "rTokenExpires";
    private final String ACCESS_DATE = "creationDate";
    private final String ACCESS_TOKENTYPE = "type";
    private final String[] COLUMNS = {KEY_ID,ACCESS_TOKEN,ACCESS_EXPIRES/*,ACCESS_RTOKEN,ACCESS_RTOKENEXPIRES*/,ACCESS_DATE,ACCESS_TOKENTYPE};



    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create book table
        String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS "+TABLE_ACCESS+" ( " +
                KEY_ID+" TEXT PRIMARY KEY, " +
                ACCESS_TOKEN+ " TEXT, "+
                ACCESS_EXPIRES+" TEXT, "+
                //ACCESS_RTOKEN+" TEXT, "+
                ACCESS_DATE+" TEXT, "+
                ACCESS_TOKENTYPE+" TEXT)";

        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        this.onCreate(db);
    }

    public Access GetAccess(String type){
        try{

            // 1. get reference to readable DB
            SQLiteDatabase db = this.getReadableDatabase();

            // 2. build query
            Cursor cursor =
                    db.query(TABLE_ACCESS, // a. table
                            COLUMNS, // b. column names
                            ACCESS_TOKENTYPE+" = ?",//" id = ?", // c. selections
                            new String[] {type },//new String[] { String.valueOf(id) }, // d. selections args
                            null, // e. group by
                            null, // f. having
                            null, // g. order by
                            null); // h. limit

            // 3. if we got results get the first one
            if (cursor != null){
                if(cursor.moveToFirst()){
                    // 4.
                    Access access = new Access(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4));
                    cursor.close();
                    db.close();
                    return access;
                }
            }
        }catch(Exception e){
            e.printStackTrace();

        }
        return null;
    }

    public void AddAccess(Access access){
        try{
            Log.d(G.TAG, "AddAccess");

            // 1. get reference to writable DB
            SQLiteDatabase db = this.getWritableDatabase();

            // 2. create ContentValues to add key "column"/value
            ContentValues values = new ContentValues();
            values.put(KEY_ID, access.getId());
            values.put(ACCESS_TOKEN,access.getAccessToken());
            values.put(ACCESS_EXPIRES, access.getAccessTokenExpire());
            values.put(ACCESS_DATE, access.getDate());
            values.put(ACCESS_TOKENTYPE,access.getType());


            // 3. insert
            db.insert(TABLE_ACCESS, // table
                    null, //nullColumnHack
                    values); // key/value -> keys = column names/ values = column values

            // 4. close
            db.close();
        }catch(Exception e){

        }

    }

    public void DeleteAccess(){
        try{
            Log.d(G.TAG, "DeleteAccess");

            // 1. get reference to writable DB
            SQLiteDatabase db = this.getWritableDatabase();

           db.delete(TABLE_ACCESS,null,null);



            // 4. close
            db.close();
        }catch(Exception e){

        }

    }

}
