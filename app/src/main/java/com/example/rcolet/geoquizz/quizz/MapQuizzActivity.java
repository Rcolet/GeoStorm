package com.example.rcolet.geoquizz.quizz;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.rcolet.geoquizz.R;
import com.example.rcolet.geoquizz.helper.MapQuizzSelectHelper;
import com.example.rcolet.geoquizz.score.ScoreMapQuizzActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapQuizzActivity extends FragmentActivity implements OnMapReadyCallback {

    private TextView pays;

    private GoogleMap mMap;

    private Context MQcontext;

    private String PaysSearch;

    private int score;

    MapQuizzSelectHelper MQSH = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_quizz);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        MQSH = new MapQuizzSelectHelper(this);

        pays = (TextView)findViewById(R.id.SearchMapQuizz);

        PaysSearch = MQSH.SelectQuestion();

        pays.setText("(" +score + ") " + PaysSearch);

        score=0;

        MQcontext = this;

        Handler handler = new Handler();
        handler.postDelayed(new Runnable(){
            @Override
            public void run(){
                Intent startCultureQuizz = new Intent(MQcontext, ScoreMapQuizzActivity.class);
                startCultureQuizz.putExtra("score2", score );
                startActivity(startCultureQuizz);
            }
        }, 60000); //60


    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));



        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener()
        {
            @Override
            public void onMapClick(LatLng arg0)
            {
                android.util.Log.i("onMapClick", "Horray! : " + arg0);

                //TODO : code de test si le click est sur le bon pays

                try {
                    String Pays = getAddress(arg0.latitude, arg0.longitude);


                    if(regexPays(Pays, PaysSearch))
                    {
                        score++;

                        PaysSearch = MQSH.SelectQuestion();

                        pays.setText("(" +score + ") " + PaysSearch);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


    }

    public String getAddress(double lat, double lng) throws IOException {

        String sRet = "";

        Geocoder gcd = new Geocoder(MQcontext, Locale.FRANCE /*Locale.getDefault()*/);
        List<Address> addresses = gcd.getFromLocation(lat, lng, 1);

        if (addresses.size() > 0)
        {
            sRet=addresses.get(0).getCountryName();
        }

        return sRet;
    }

    public boolean regexPays(String p1, String p2)
    {
        boolean ret = true;

        p1 = p1.toLowerCase();
        p1 = p1.replace("'", "");
        p1 = p1.replace("é", "e");
        p1 = p1.replace("è", "e");
        p1 = p1.replace("à", "a");

        p2 = p2.toLowerCase();
        p2 = p2.replace("'", "");
        p2 = p2.replace("é", "e");
        p2 = p2.replace("è", "e");
        p2 = p2.replace("à", "a");

        android.util.Log.i("onMapClick", "PAYS : " + p1 + " VS " + p2);

        return p1.equals(p2);
    }
}
