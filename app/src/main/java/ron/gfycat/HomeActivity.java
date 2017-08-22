package ron.gfycat;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.SubMenu;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Calendar;

import ron.gfycat.Https.HttpsGetAlbums;
import ron.gfycat.common.G;
import ron.gfycat.common.Utils;
import ron.gfycat.fragment.AlbumFragment;
import ron.gfycat.fragment.Login;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(G.TAG,"HomeActivity.onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Refrescando albumes...", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                if(Utils.checkActiveSession(getApplicationContext())) {
                    LoadAlbumsFragment();
                }
                else{
                    LoadLoginFragment();

                }


            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //new HttpsGetToken(this).execute();

       if(Utils.checkActiveSession(this)){
            //LoadAlbumMenu();
            LoadAlbumsFragment();
        }
        else{
            LoadLoginFragment();
            //drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        }


        Calendar c = Calendar.getInstance();
        Log.d(G.TAG,"mill "+c.getTimeInMillis());
        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Log.d(G.TAG,"id: "+id);

        /*if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void LoadLoginFragment(){
        try {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, new Login());
            ft.commit();
        }
        catch(IllegalStateException iise){
            Log.d(G.TAG,"IllegalStateException "+iise.getMessage());
            //TODO: Mostrar alert de recarga o intentar recargar pantalla.
        }

    }
    public void LoadAlbumsFragment(){
        /*new HttpsGetAlbums(this, new HttpsGetAlbums.FinishAction() {
            @Override
            public void LoadResult(int statusCode, String result) {

            }
        }).execute();*/
        try {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            AlbumFragment af= AlbumFragment.newInstance("force","");
            af.forcereload=true;
            ft.replace(R.id.content_frame,af );
            ft.commit();
        }
        catch(IllegalStateException iise){
            Log.d(G.TAG,"IllegalStateException "+iise.getMessage());
            //TODO: Mostrar alert de recarga o intentar recargar pantalla.
        }

    }

    public void LoadAlbumMenu(){
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        final Menu menu = navigationView.getMenu();
        SubMenu sm= menu.addSubMenu("Albumes");
        for (int i = 1; i <= 13; i++) {
            //MenuItem mi= menu.add("Runtime item "+);
            MenuItem mi =sm.add(0,19982+i,i,"item "+i);
            mi.setIcon(R.drawable.ic_menu_gallery);
        }
    }
}
