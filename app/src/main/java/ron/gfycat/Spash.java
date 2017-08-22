package ron.gfycat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ron.gfycat.common.Utils;

public class Spash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spash);

        //final Intent m = new Intent(this,LoginActivity.class);
        Thread timer = new Thread(){

            public void run(){
                try{
                    sleep(2000);

                }
                catch(Exception e){

                }
                Utils util = new Utils();
                if(util.checkActiveSession(getApplicationContext())){
                    startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                }
                else{
                    //startActivity(m);
                    startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                }

                finish();;
            }
        };
        timer.start();
    }
}
