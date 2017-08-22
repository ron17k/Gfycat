package ron.gfycat.fragment.custom;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.util.Log;

import ron.gfycat.common.G;

/**
 * Created by RoN on 8/21/2017.
 */

public class CustomProgressDialog extends ProgressDialog {
    private CharSequence currentmessage;
    private boolean TimeoutTimer=true;
    public CustomProgressDialog(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        if(timer==null);
        timer=new Handler();
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(G.TAG, "[CustomProgressDialog] Start");
        if(TimeoutTimer)
            timer.postDelayed( checker, 1000);
        counter=0;
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(G.TAG, "[CustomProgressDialog] Stop");
        counter=0;
        if(timer!=null){timer.removeCallbacks(checker);}
        TimeoutTimer=true;
    }
    @Override
    public void setMessage(CharSequence message) {
        super.setMessage(message);
        currentmessage=message;

    }
    private void setCustomMessage(CharSequence message){
        super.setMessage(message);

    }
    public void SetTimeoutTimer(boolean enable){
        TimeoutTimer=enable;
    }
    ////////////////Timer loader
    Handler timer;
    int counter=0,internal;
    final Runnable checker = new Runnable()
    {

        public synchronized  void run()
        {

            if(counter==0 || internal==4)
                internal=0;
            int timeout=(G.TIMEOUT_SOCKET+G.TIMEOUT_CONNECTION)/1000;
            counter++;
            internal++;

            Log.d(G.TAG,"Entró timer");
            if(internal==1)
                setCustomMessage(currentmessage+" .");
            else if(internal==2)
                setCustomMessage(currentmessage+" ..");
            else if(internal==3)
                setCustomMessage(currentmessage+" ...");
            else if(internal==4)
                setCustomMessage(currentmessage);
            setProgress(counter*100/timeout);
            if(counter>timeout){
                try{dismiss();}catch(Exception ex){}
            }
            else
                timer.postDelayed( checker, 1000);
        }
    };

}
