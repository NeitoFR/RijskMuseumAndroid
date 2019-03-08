package com.example.rijskviewer;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.example.rijskviewer.Interfaces.VolleyCallback;
import com.example.rijskviewer.api.MuseumApi;
import com.example.rijskviewer.models.ArtWork;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private MuseumApi museumApi;
    private ArtWork artWork;
    private List<ArtWork> artWorkList = new ArrayList<>();

    private ViewPager viewPager;
    private ListFragmentCollections adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        museumApi = new MuseumApi();

        ListView artWorkListView = (ListView) findViewById(R.id.studentsListView);

//        artWorkList.add(new ArtWork("firstAuthor", "firstTitle", "10-12-2016", "testUrl", 3));
//        artWorkList.add(new ArtWork("secondAuthor", "secondTitle", "06-03-2018", "testUrl", 4));


        artWorkList = museumApi.readFromJson(callVolleyCallback(), this.getApplicationContext());
        System.out.println("main activity : " + artWorkList);
        ArrayAdapter<ArtWork> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, artWorkList);

        artWorkListView.setAdapter(arrayAdapter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        viewPager = findViewById(R.id.pager);
        adapter = new ListFragmentCollections(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
    }

    private VolleyCallback callVolleyCallback(){
        return new VolleyCallback() {
            @Override
            public void onSuccess(final JSONObject response) {
                museumApi.getArtist(artWorkList, response);
            }
        };
    }

//    public static <T> void addToRequestQueue(Request<T> req, String tag) {
//
//        // set the default tag if tag is empty
//        req.setTag(TextUtils.isEmpty(tag) ? LOG_TAG : tag);
//        mInstance.mRequestQueue.add(req);
//    }

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
        getMenuInflater().inflate(R.menu.main, menu);
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

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

//    public static void getString(
//            int method,
//            String url,
//            final Map<String, String> headers,
//            Response.Listener<String> listener,
//            Response.ErrorListener errorListener,
//            boolean putInCache)
//    {
//        System.out.println("getJSONObject url: " + url);
//
//        StringRequest jsonObjReq = new StringRequest(method, url, listener, errorListener) {
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                if (headers == null) {
//                    return new LinkedHashMap<>(0);
//                } else {
//                    return headers;
//                }
//            }
//        };
//
//        jsonObjReq.setShouldCache(putInCache);
//        addRequestPolicy(jsonObjReq);
//
//        // Adding request to request queue
//        addToRequestQueue(jsonObjReq, "req_object_" + url.hashCode());
//    }

    public static void addRequestPolicy(Request<String> request)
    {
        request.setRetryPolicy(
                new DefaultRetryPolicy(
                        10000, // 10 000
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
                )
        );
    }
}
