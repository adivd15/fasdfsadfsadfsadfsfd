package com.mihai.wander;

import androidx.fragment.app.FragmentActivity;

import android.app.AlertDialog;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mihai.wander.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private Button buttonOpenPopup;

    private EditText popup_centername, popup_centeraddress, popup_centertype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        buttonOpenPopup = findViewById(R.id.button);
        buttonOpenPopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNewDialog();
            }
        });
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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

        // Add a marker in Sydney and move the camera
        LatLng cluj = new LatLng(46.770439, 23.591423);
        //mMap.addMarker(new MarkerOptions().position(cluj).title("Marker in Cluj-Napoca"));
        mMap.animateCamera(
                CameraUpdateFactory.newLatLngZoom(
                        cluj, 13f
                ));


        LatLng centru1 = new LatLng(46.7643211,23.5676296);
        mMap.addMarker((new MarkerOptions().position(centru1).title("Centru Colectare Materiale Reciclabile")));

        LatLng centru2 = new LatLng(46.7863536,23.5983725);
        mMap.addMarker((new MarkerOptions().position(centru2).title("Recycle International")));

        LatLng centru3 = new LatLng(46.7672833,23.5984033);
        mMap.addMarker((new MarkerOptions().position(centru3).title("Rematinvest")));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Change the map type based on the user's selection.
        switch (item.getItemId()) {
            case R.id.normal_map:
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                return true;
            case R.id.hybrid_map:
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                return true;
            case R.id.satellite_map:
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                return true;
            case R.id.terrain_map:
                mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void createNewDialog(){
        dialogBuilder = new AlertDialog.Builder(this);
        final View popupView = getLayoutInflater().inflate(R.layout.popup, null);

        popup_centeraddress = (EditText) popupView.findViewById(R.id.txtCenterName);
        popup_centername = (EditText) popupView.findViewById(R.id.txtCenterName);
        popup_centertype = (EditText) popupView.findViewById(R.id.txtCenterType);
        dialogBuilder.setView(popupView);
        dialog = dialogBuilder.create();
        dialog.show();
    }
}