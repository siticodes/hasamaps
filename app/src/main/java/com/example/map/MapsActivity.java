package com.example.map;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    MarkerOptions marker;
    LatLng centerlocation;
    Vector<MarkerOptions> markerOptions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        centerlocation = new LatLng(5.7679,102.2154);

        markerOptions = new Vector<>();

        markerOptions.add(new MarkerOptions().title("Marker in Machang")
                .position(new LatLng(5.7679,102.2154))
                .snippet("I'm here")
        );
        markerOptions.add(new MarkerOptions().title("Marker in Melor")
                .position(new LatLng(5.9639,102.2954))
                .snippet("I'm here")
        );
        markerOptions.add(new MarkerOptions().title("Marker in Tanah Merah")
                .position(new LatLng(5.8089,102.1471))
                .snippet("I'm here")
        );
        markerOptions.add(new MarkerOptions().title("Marker in Kuala Krai")
                .position(new LatLng(5.5308,102.2019))
                .snippet("I'm here")
        );
        markerOptions.add(new MarkerOptions().title("Marker in Pasir Puteh")
                .position(new LatLng(5.836163,102.407741))
                .snippet("I'm here")
        );
        markerOptions.add(new MarkerOptions().title("Marker in Bachok")
                .position(new LatLng(6.0477,102.3945))
                .snippet("I'm here")
        );
        markerOptions.add(new MarkerOptions().title("Marker in Tumpat")
                .position(new LatLng(6.1991,102.1694))
                .snippet("I'm here")
        );
        markerOptions.add(new MarkerOptions().title("Marker in Pasir Mas")
                .position(new LatLng(6.0424,102.1428))
                .snippet("I'm here")
        );
        markerOptions.add(new MarkerOptions().title("Marker in Rantau Panjang")
                .position(new LatLng(6.0116,101.9784))
                .snippet("I'm here")
        );
        markerOptions.add(new MarkerOptions().title("Marker in Kota Bharu")
                .position(new LatLng(6.1248,102.2544))
                .snippet("I'm here")
        );
        markerOptions.add(new MarkerOptions().title("Marker in Kubang Kerian")
                .position(new LatLng(6.0870,102.2763))
                .snippet("I'm here")
        );
        markerOptions.add(new MarkerOptions().title("Marker in Pengkalan Chepa")
                .position(new LatLng(6.1525,102.3063))
                .snippet("I'm here")
        );
        markerOptions.add(new MarkerOptions().title("Marker in Ketereh")
                .position(new LatLng(5.9580,102.2499))
                .snippet("I'm here")
        );
        markerOptions.add(new MarkerOptions().title("Marker in Selising")
                .position(new LatLng(5.8942,102.3350))
                .snippet("I'm here")
        );
        markerOptions.add(new MarkerOptions().title("Marker in Temangan")
                .position(new LatLng(5.7019,102.1511))
                .snippet("I'm here")
        );
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(-34, 151);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));

        for (MarkerOptions mark: markerOptions) {
            mMap.addMarker(mark);
        }

        enableMyLocation();

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(centerlocation,8));
    }
    /**
     * Enables the My Location layer if the fine location permission has been granted.
     */
    private void enableMyLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            if (mMap != null) {
                mMap.setMyLocationEnabled(true);
            }
        } else {
            String perms[] = {"android.permission.ACCESS_FINE_LOCATION"};
            // Permission to access the location is missing. Show rationale and request permission
            ActivityCompat.requestPermissions(this, perms,200);
        }
    }

    public void onMapSearch(View view) {
        EditText locationSearch = (EditText) findViewById(R.id.editText);
        String location = locationSearch.getText().toString();
        List<Address> addressList = null;
        if (location != null || !location.equals("")) {
            Geocoder geocoder = new Geocoder(this);
            try {
                addressList = geocoder.getFromLocationName(location, 1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Address address = addressList.get(0);
            LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
            mMap.addMarker(new MarkerOptions().position(latLng).title("Marker"));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 18));
        }
    }

}