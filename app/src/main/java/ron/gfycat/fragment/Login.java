package ron.gfycat.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Calendar;

import ron.gfycat.HomeActivity;
import ron.gfycat.Https.HttpsGetToken;
import ron.gfycat.R;
import ron.gfycat.common.G;
import ron.gfycat.db.MySQLiteHelper;
import ron.gfycat.model.Access;

import static android.R.attr.data;
import static android.R.attr.id;
import static android.R.attr.type;
import static android.R.attr.versionCode;
import static java.net.HttpURLConnection.HTTP_OK;
import static java.net.HttpURLConnection.HTTP_UNAUTHORIZED;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Login.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Login# newInstance} factory method to
 * create an instance of this fragment.
 */
public class Login extends Fragment {


    View mainView;

    private OnFragmentInteractionListener mListener;

    public Login() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            // = getArguments().getString(ARG_PARAM1);
           // mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.fragment_login, container, false);
        mainView=v;

        final EditText loginEdit=((EditText)v.findViewById(R.id.login_user));
        final EditText passwordEdit=((EditText)v.findViewById(R.id.login_password));
        final TextView errorLabel =((TextView) v.findViewById(R.id.login_error));


        final HomeActivity ha= (HomeActivity)getActivity();

        ((Button)v.findViewById(R.id.login_loginbutton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user=loginEdit.getText().toString();
                String pass=passwordEdit.getText().toString();
                errorLabel.setVisibility(View.GONE);
                boolean error=false;

                if(user.trim().length()==0){
                    loginEdit.setError(getActivity().getResources().getString(R.string.login_errorLogin));
                    error=true;
                    loginEdit.requestFocus();
                }

                if(pass.trim().length()==0){
                    passwordEdit.setError(getActivity().getResources().getString(R.string.login_errorPassword));
                    error=true;
                    passwordEdit.requestFocus();
                }
                if(!error){
                    new HttpsGetToken(getActivity(), new HttpsGetToken.FinishAction() {
                        @Override
                        public void ResutAction(int status, String jsonResponse) {

                            if (status == HTTP_OK) {

                                MySQLiteHelper db= new MySQLiteHelper(getActivity());
                                try {
                                    db.DeleteAccess();
                                    Calendar c = Calendar.getInstance();
                                    JSONObject res = new JSONObject(jsonResponse);
                                    Access at= new Access(Access.TYPE_ACCESSTOKEN,res.getString("access_token"),res.getString("expires_in"),c.getTimeInMillis()+"",Access.TYPE_ACCESSTOKEN);
                                    db.AddAccess(at);
                                    Access rt= new Access(Access.TYPE_REFRESHTOKEN,res.getString("refresh_token"),res.getString("refresh_token_expires_in"),c.getTimeInMillis()+"",Access.TYPE_REFRESHTOKEN);
                                    db.AddAccess(rt);
                                    //ha.LoadAlbumMenu();
                                    String n=res.getString("resource_owner");;
                                    Log.d(G.TAG,n);
                                    G.albumes= new ArrayList<>();


                                    SharedPreferences data = getActivity().getSharedPreferences("data", 0);
                                    SharedPreferences.Editor editor=data.edit();
                                    editor.putString("user", n);
                                    editor.commit();

                                    ha.LoadAlbumsFragment();
                                }
                                catch(Exception e){
e.printStackTrace();

                                }
                                finally {
                                    db.close();
                                }

                            } else {
                                errorLabel.setVisibility(View.VISIBLE);
                                if (status == HTTP_UNAUTHORIZED){
                                    errorLabel.setText(getActivity().getResources().getString(R.string.login_errorLoginResponse));
                                }
                                else{
                                    errorLabel.setText("Error http."+status);
                                }
                            }
                        }
                    }).execute(G.HTTP_REQ.TOKEN_PASSWORD,user,pass);
                }

            }
        });
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /*if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
