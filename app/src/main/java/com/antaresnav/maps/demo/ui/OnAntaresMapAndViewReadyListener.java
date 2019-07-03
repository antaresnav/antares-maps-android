package com.antaresnav.maps.demo.ui;

import android.annotation.SuppressLint;
import android.os.Build;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;

import com.antaresnav.maps.AntaresMap;
import com.antaresnav.maps.MapView;
import com.antaresnav.maps.OnMapReadyCallback;

/**
 * Helper class that will delay triggering the OnMapReady callback until both the AntaresMap and the
 * View having completed initialization. This is only necessary if a developer wishes to immediately
 * invoke any method on the AntaresMap that also requires the View to have finished layout
 * (ie. anything that needs to know the View's true size like snapshotting).
 */
public class OnAntaresMapAndViewReadyListener implements OnGlobalLayoutListener, OnMapReadyCallback {

    /** A listener that needs to wait for both the AntaresMap and the View to be initialized. */
    public interface OnGlobalLayoutAndMapReadyListener {
        void onMapReady(AntaresMap AntaresMap);
    }

    private final MapView mapView;
    private final OnGlobalLayoutAndMapReadyListener devCallback;

    private boolean isViewReady;
    private boolean isMapReady;
    private AntaresMap antaresMap;

    public OnAntaresMapAndViewReadyListener(
            MapView mapView, OnGlobalLayoutAndMapReadyListener devCallback) {
        this.mapView = mapView;
        this.devCallback = devCallback;
        isViewReady = false;
        isMapReady = false;
        antaresMap = null;

        registerListeners();
    }

    private void registerListeners() {
        // View layout.
        if ((mapView.getWidth() != 0) && (mapView.getHeight() != 0)) {
            // View has already completed layout.
            isViewReady = true;
        } else {
            // Map has not undergone layout, register a View observer.
            mapView.getViewTreeObserver().addOnGlobalLayoutListener(this);
        }

        // AntaresMap. Note if the AntaresMap is already ready it will still fire the callback later.
        mapView.getMapAsync(this);
    }

    @Override
    public void onMapReady(AntaresMap AntaresMap) {
        // NOTE: The AntaresMap API specifies the listener is removed just prior to invocation.
        this.antaresMap = AntaresMap;
        isMapReady = true;
        fireCallbackIfReady();
    }

    @SuppressWarnings("deprecation")  // We use the new method when supported
    @SuppressLint("NewApi")  // We check which build version we are using.
    @Override
    public void onGlobalLayout() {
        // Remove our listener.
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            mapView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
        } else {
            mapView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        }
        isViewReady = true;
        fireCallbackIfReady();
    }

    private void fireCallbackIfReady() {
        if (isViewReady && isMapReady) {
            devCallback.onMapReady(antaresMap);
        }
    }
}
