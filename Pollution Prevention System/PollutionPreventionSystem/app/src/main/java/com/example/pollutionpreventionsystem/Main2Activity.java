package com.example.pollutionpreventionsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class Main2Activity extends FragmentActivity implements OnMapReadyCallback, TaskLoadedCallback {

    private Button getDirectionButton,leastPolluted;
    private EditText source;
    Button t1;
    Location mLocation;
    GoogleMap map;
    MarkerOptions place1, place2;
    FusedLocationProviderClient fusedLocationProviderContent;
    private static final int REQUEST_CODE = 101;

    Polyline currentPolyline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        final SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        getDirectionButton = (Button) findViewById(R.id.getDirectionButton);
        leastPolluted = (Button) findViewById(R.id.leastPolluted);
        source = (EditText) findViewById(R.id.source);
        t1=(Button) findViewById(R.id.aqiLevel);
        // MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        //supportMapFragment.getMapAsync(this);
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(Main2Activity.this,Main3Activity.class));
            }
        });
        place1 = new MarkerOptions().position(new LatLng(28.7209, 77.1415)).title("Location 1");
        place2 = new MarkerOptions().position(new LatLng(28.7209, 77.3434)).title("Location 2");

        String url = getUrl(place1.getPosition(), place2.getPosition(), "driving");
        new FetchURL(Main2Activity.this).execute(url, "driving");

        getDirectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //fusedLocationProviderContent = LocationServices.getFusedLocationProviderClient(MainActivity.this);
                //fusedLocationProviderContent.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                //   @Override
                //   public void onSuccess(Location location) {
                //   if(location != null)
                source.setText("Vivekanand Institute of Professional Studies");
            }
        });

        leastPolluted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                supportMapFragment.getMapAsync(Main2Activity.this);
            }
        });



        //fusedLocationProviderContent = LocationServices.getFusedLocationProviderClient(this);
        //GetLastLocation();
    }

    private String getUrl(LatLng position, LatLng position1, String directionMode) {

        String str_origin = "origin=" + position.latitude + "," + position.longitude;
        String str_dest = "destination=" + position1.latitude + "," + position1.longitude;
        String mode = "mode=" + directionMode;
        String parameters = str_origin + "&" + str_dest + "&" + mode;
        String output = "json";
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters + "&key=" + getString(R.string.map_key);
        return url;
    }

    private void GetLastLocation() {

        Task<Location> task = fusedLocationProviderContent.getLastLocation();

        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    mLocation = location;
                    Toast.makeText(getApplicationContext(), mLocation.getLatitude() + " " + mLocation.getLongitude(), Toast.LENGTH_LONG).show();
                    SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
                    supportMapFragment.getMapAsync(Main2Activity.this);
                }
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        map = googleMap;
        map.addMarker(place1);
        map.addMarker(place2);

        //LatLng Delhi = new LatLng(28.7209, 77.1415);
        //MarkerOptions markerOptions = new MarkerOptions().position(Delhi).title("Your current location");
        //googleMap.animateCamera(CameraUpdateFactory.newLatLng(Delhi));
        //googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Delhi,6));
        //googleMap.addMarker(new MarkerOptions().position(Delhi).title("delhi"));
        //googleMap.moveCamera(CameraUpdateFactory.newLatLng(Delhi));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    GetLastLocation();
                }
                break;
        }
    }

    @Override
    public void onTaskDone(Object... values) {
        //if (currentPolyline != null)
        //   currentPolyline.remove();
        //currentPolyline = map.addPolyline((PolylineOptions) values[0]);
        PolylineOptions rectOptions = new PolylineOptions();

        rectOptions =rectOptions.add(new LatLng(24.009115, -104.699933));
        rectOptions =rectOptions.add(new LatLng(24.009115, -104.699933));
        rectOptions =rectOptions.width(5);
        rectOptions =rectOptions.color(Color.RED);
        //Polyline polyline = map.addPolyline(rectOptions);
        currentPolyline = map.addPolyline(rectOptions);

    }
}
