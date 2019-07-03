package com.antaresnav.maps.demo.examples;

import android.os.Bundle;
import android.widget.TextView;

import com.antaresnav.maps.demo.R;
import com.antaresnav.maps.demo.ui.BaseExampleActivity;
import com.antaresnav.maps.AntaresMap;
import com.antaresnav.maps.AntaresMap.OnCameraIdleListener;
import com.antaresnav.maps.AntaresMap.OnMapClickListener;
import com.antaresnav.maps.AntaresMap.OnMapLongClickListener;
import com.antaresnav.maps.MapView;
import com.antaresnav.maps.OnMapReadyCallback;
import com.antaresnav.maps.model.LatLng;

/**
 * This shows how to listen to some {@link AntaresMap} events.
 */
public class EventsAntaresActivity extends BaseExampleActivity
        implements OnMapClickListener, OnMapLongClickListener, OnCameraIdleListener,
        OnMapReadyCallback {

    private TextView mTapTextView;
    private TextView mCameraTextView;
    private MapView mapView;
    private AntaresMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mTapTextView = findViewById(R.id.tap_text);
        mCameraTextView = findViewById(R.id.camera_text);

        mapView = findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.content_events_antares;
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

    @Override
    public void onMapReady(AntaresMap map) {
        mMap = map;
        mMap.setOnMapClickListener(this);
        mMap.setOnMapLongClickListener(this);
        mMap.setOnCameraIdleListener(this);
    }

    @Override
    public void onMapClick(LatLng point) {
        mTapTextView.setText("tapped, point=" + point);
    }

    @Override
    public void onMapLongClick(LatLng point) {
        mTapTextView.setText("long pressed, point=" + point);
    }

    @Override
    public void onCameraIdle() {
        mCameraTextView.setText(mMap.getCameraPosition().toString());
    }
}
