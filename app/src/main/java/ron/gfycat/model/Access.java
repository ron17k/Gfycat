package ron.gfycat.model;

/**
 * Created by RoN on 8/21/2017.
 */

public class Access {
    public static final String TYPE_ACCESSTOKEN="access";
    public static final String TYPE_REFRESHTOKEN="refresh";

    private String id;
    private String accessToken;
    private String accessTokenExpire;
    //private String refreshToken;
    //private String refreshTokenExpire;
    private String date;
    private String type;

    public Access(){

    }
    public Access(String id,String token,String tokenExpires,String data,String type){
        this.id=id;
        this.accessToken=token;
        this.accessTokenExpire=tokenExpires;
        this.date=data;
        this.type=type;


    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessTokenExpire() {
        return accessTokenExpire;
    }

    public void setAccessTokenExpire(String accessTokenExpire) {
        this.accessTokenExpire = accessTokenExpire;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "{" +
                "id: " +this.id
                +"accessToken: "+this.accessToken
                +"}";
    }
}
