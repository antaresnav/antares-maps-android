package com.antaresnav.maps.demo.examples;

import android.os.Bundle;

import com.antaresnav.maps.AntaresMap;
import com.antaresnav.maps.CameraUpdateFactory;
import com.antaresnav.maps.MapView;
import com.antaresnav.maps.OnMapReadyCallback;
import com.antaresnav.maps.demo.ui.BaseExampleActivity;
import com.antaresnav.maps.demo.R;
import com.antaresnav.maps.model.LatLng;
import com.antaresnav.maps.model.MarkerOptions;

/**
 * This shows how to create a simple activity with a map and a marker on the map.
 */
public class BasicMapAntaresActivity extends BaseExampleActivity {

    private MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mapView = findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(new OnMapReadyCallback() {

            /**
             * This is where we can add markers or lines, add listeners or move the camera. In this case,
             * we just add a marker and move the camera to Budapest.
             */
            @Override
            public void onMapReady(AntaresMap map) {
                LatLng budapestPosition = new LatLng(47.5, 19.04);
                map.addMarker(new MarkerOptions().position(budapestPosition));
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(budapestPosition, 15));
            }
        });
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.content_basic_map_antares;
    }

    // Add the mapView lifecycle to the activity's lifecycle methods
    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

}
