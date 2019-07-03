package com.antaresnav.maps.demo.examples;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Spinner;

import com.antaresnav.maps.demo.R;
import com.antaresnav.maps.demo.ui.BaseExampleActivity;
import com.antaresnav.maps.CameraUpdateFactory;
import com.antaresnav.maps.AntaresMap;
import com.antaresnav.maps.MapView;
import com.antaresnav.maps.OnMapReadyCallback;
import com.antaresnav.maps.model.Dash;
import com.antaresnav.maps.model.Dot;
import com.antaresnav.maps.model.Gap;
import com.antaresnav.maps.model.JointType;
import com.antaresnav.maps.model.LatLng;
import com.antaresnav.maps.model.PatternItem;
import com.antaresnav.maps.model.Polygon;
import com.antaresnav.maps.model.PolygonOptions;

import java.util.Arrays;
import java.util.List;

/**
 * This shows how to draw polygons on a map.
 */
public class PolygonAntaresActivity extends BaseExampleActivity
        implements OnSeekBarChangeListener, OnItemSelectedListener, OnMapReadyCallback {

    private static final LatLng CENTER = new LatLng(-20, 130);
    private static final int MAX_WIDTH_PX = 100;
    private static final int MAX_HUE_DEGREES = 360;
    private static final int MAX_ALPHA = 255;

    private static final int PATTERN_DASH_LENGTH_PX = 50;
    private static final int PATTERN_GAP_LENGTH_PX = 10;
    private static final Dot DOT = new Dot();
    private static final Dash DASH = new Dash(PATTERN_DASH_LENGTH_PX);
    private static final Gap GAP = new Gap(PATTERN_GAP_LENGTH_PX);
    private static final List<PatternItem> PATTERN_DOTTED = Arrays.asList(DOT, GAP);
    private static final List<PatternItem> PATTERN_DASHED = Arrays.asList(DASH, GAP);
    private static final List<PatternItem> PATTERN_MIXED = Arrays.asList(DOT, GAP, DOT, DASH, GAP);

    private MapView mapView;
    private Polygon mMutablePolygon;
    private SeekBar mFillHueBar;
    private SeekBar mFillAlphaBar;
    private SeekBar mStrokeWidthBar;
    private SeekBar mStrokeHueBar;
    private SeekBar mStrokeAlphaBar;
    private Spinner mStrokeJointTypeSpinner;
    private Spinner mStrokePatternSpinner;

    // These are the options for polygon stroke joints and patterns. We use their
    // string resource IDs as identifiers.

    private static final int[] JOINT_TYPE_NAME_RESOURCE_IDS = {
            R.string.joint_type_default, // Default
            R.string.joint_type_bevel,
            R.string.joint_type_round,
    };

    private static final int[] PATTERN_TYPE_NAME_RESOURCE_IDS = {
            R.string.pattern_solid, // Default
            R.string.pattern_dashed,
            R.string.pattern_dotted,
            R.string.pattern_mixed,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mFillHueBar = findViewById(R.id.fillHueSeekBar);
        mFillHueBar.setMax(MAX_HUE_DEGREES);
        mFillHueBar.setProgress(MAX_HUE_DEGREES / 2);

        mFillAlphaBar = findViewById(R.id.fillAlphaSeekBar);
        mFillAlphaBar.setMax(MAX_ALPHA);
        mFillAlphaBar.setProgress(MAX_ALPHA / 2);

        mStrokeWidthBar = findViewById(R.id.strokeWidthSeekBar);
        mStrokeWidthBar.setMax(MAX_WIDTH_PX);
        mStrokeWidthBar.setProgress(MAX_WIDTH_PX / 3);

        mStrokeHueBar = findViewById(R.id.strokeHueSeekBar);
        mStrokeHueBar.setMax(MAX_HUE_DEGREES);
        mStrokeHueBar.setProgress(0);

        mStrokeAlphaBar = findViewById(R.id.strokeAlphaSeekBar);
        mStrokeAlphaBar.setMax(MAX_ALPHA);
        mStrokeAlphaBar.setProgress(MAX_ALPHA);

        mStrokeJointTypeSpinner = findViewById(R.id.strokeJointTypeSpinner);
        mStrokeJointTypeSpinner.setAdapter(new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item,
                getResourceStrings(JOINT_TYPE_NAME_RESOURCE_IDS)));

        mStrokePatternSpinner = findViewById(R.id.strokePatternSpinner);
        mStrokePatternSpinner.setAdapter(new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item,
                getResourceStrings(PATTERN_TYPE_NAME_RESOURCE_IDS)));

        mapView = findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.content_polygon_antares;
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

    private String[] getResourceStrings(int[] resourceIds) {
        String[] strings = new String[resourceIds.length];
        for (int i = 0; i < resourceIds.length; i++) {
            strings[i] = getString(resourceIds[i]);
        }
        return strings;
    }

    @Override
    public void onMapReady(AntaresMap map) {
        int fillColorArgb = Color.HSVToColor(
                mFillAlphaBar.getProgress(), new float[]{mFillHueBar.getProgress(), 1, 1});
        int strokeColorArgb = Color.HSVToColor(
                mStrokeAlphaBar.getProgress(), new float[]{mStrokeHueBar.getProgress(), 1, 1});

        // Create a rectangle with two rectangular holes.
        mMutablePolygon = map.addPolygon(new PolygonOptions()
                .addAll(createRectangle(CENTER, 5, 5))
                .fillColor(fillColorArgb)
                .strokeColor(strokeColorArgb)
                .strokeWidth(mStrokeWidthBar.getProgress()));

        mFillHueBar.setOnSeekBarChangeListener(this);
        mFillAlphaBar.setOnSeekBarChangeListener(this);

        mStrokeWidthBar.setOnSeekBarChangeListener(this);
        mStrokeHueBar.setOnSeekBarChangeListener(this);
        mStrokeAlphaBar.setOnSeekBarChangeListener(this);

        mStrokeJointTypeSpinner.setOnItemSelectedListener(this);
        mStrokePatternSpinner.setOnItemSelectedListener(this);

        mMutablePolygon.setStrokeJointType(getSelectedJointType(mStrokeJointTypeSpinner.getSelectedItemPosition()));
        mMutablePolygon.setStrokePattern(getSelectedPattern(mStrokePatternSpinner.getSelectedItemPosition()));

        // Move the map so that it is centered on the mutable polygon.
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(CENTER, 4));
    }

    /**
     * Creates a List of LatLngs that form a rectangle with the given dimensions.
     */
    private List<LatLng> createRectangle(LatLng center, double halfWidth, double halfHeight) {
        return Arrays.asList(new LatLng(center.latitude - halfHeight, center.longitude - halfWidth),
                new LatLng(center.latitude - halfHeight, center.longitude + halfWidth),
                new LatLng(center.latitude + halfHeight, center.longitude + halfWidth),
                new LatLng(center.latitude + halfHeight, center.longitude - halfWidth),
                new LatLng(center.latitude - halfHeight, center.longitude - halfWidth));
    }

    private int getSelectedJointType(int pos) {
        switch (JOINT_TYPE_NAME_RESOURCE_IDS[pos]) {
            case R.string.joint_type_bevel:
                return JointType.BEVEL;
            case R.string.joint_type_round:
                return JointType.ROUND;
            case R.string.joint_type_default:
                return JointType.DEFAULT;
        }
        return 0;
    }

    private List<PatternItem> getSelectedPattern(int pos) {
        switch (PATTERN_TYPE_NAME_RESOURCE_IDS[pos]) {
            case R.string.pattern_solid:
                return null;
            case R.string.pattern_dotted:
                return PATTERN_DOTTED;
            case R.string.pattern_dashed:
                return PATTERN_DASHED;
            case R.string.pattern_mixed:
                return PATTERN_MIXED;
            default:
                return null;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        switch (parent.getId()) {
            case R.id.strokeJointTypeSpinner:
                mMutablePolygon.setStrokeJointType(getSelectedJointType(pos));
                break;
            case R.id.strokePatternSpinner:
                mMutablePolygon.setStrokePattern(getSelectedPattern(pos));
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Don't do anything here.
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        // Don't do anything here.
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        // Don't do anything here.
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (mMutablePolygon == null) {
            return;
        }

        if (seekBar == mFillHueBar) {
            mMutablePolygon.setFillColor(Color.HSVToColor(
                    Color.alpha(mMutablePolygon.getFillColor()), new float[]{progress, 1, 1}));
        } else if (seekBar == mFillAlphaBar) {
            int prevColor = mMutablePolygon.getFillColor();
            mMutablePolygon.setFillColor(Color.argb(
                    progress, Color.red(prevColor), Color.green(prevColor),
                    Color.blue(prevColor)));
        } else if (seekBar == mStrokeHueBar) {
            mMutablePolygon.setStrokeColor(Color.HSVToColor(
                    Color.alpha(mMutablePolygon.getStrokeColor()), new float[]{progress, 1, 1}));
        } else if (seekBar == mStrokeAlphaBar) {
            int prevColorArgb = mMutablePolygon.getStrokeColor();
            mMutablePolygon.setStrokeColor(Color.argb(
                    progress, Color.red(prevColorArgb), Color.green(prevColorArgb),
                    Color.blue(prevColorArgb)));
        } else if (seekBar == mStrokeWidthBar) {
            mMutablePolygon.setStrokeWidth(progress);
        }
    }
}