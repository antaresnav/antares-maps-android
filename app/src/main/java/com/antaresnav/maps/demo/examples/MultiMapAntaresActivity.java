package com.antaresnav.maps.demo.examples;

import android.os.Bundle;

import com.antaresnav.maps.demo.R;
import com.antaresnav.maps.demo.ui.BaseExampleActivity;
import com.antaresnav.maps.MapView;

/**
 * This shows how to create a simple activity with multiple maps on screen.
 */
public class MultiMapAntaresActivity extends BaseExampleActivity {

    private MapView mapView1;
    private MapView mapView2;
    private MapView mapView3;
    private MapView mapView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mapView1 = findViewById(R.id.map1);
        mapView2 = findViewById(R.id.map2);
        mapView3 = findViewById(R.id.map3);
        mapView4 = findViewById(R.id.map4);
        mapView1.onCreate(savedInstanceState);
        mapView2.onCreate(savedInstanceState);
        mapView3.onCreate(savedInstanceState);
        mapView4.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.content_multimap_antares;
    }

    // Add the mapView lifecycle to the activity's lifecycle methods
    @Override
    protected void onStart() {
        super.onStart();
        mapView1.onStart();
        mapView2.onStart();
        mapView3.onStart();
        mapView4.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView1.onStop();
        mapView2.onStop();
        mapView3.onStop();
        mapView4.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView1.onDestroy();
        mapView2.onDestroy();
        mapView3.onDestroy();
        mapView4.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView1.onSaveInstanceState(outState);
        mapView2.onSaveInstanceState(outState);
        mapView3.onSaveInstanceState(outState);
        mapView4.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView1.onLowMemory();
        mapView2.onLowMemory();
        mapView3.onLowMemory();
        mapView4.onLowMemory();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView1.onPause();
        mapView2.onPause();
        mapView3.onPause();
        mapView4.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView1.onResume();
        mapView2.onResume();
        mapView3.onResume();
        mapView4.onResume();
    }
}
