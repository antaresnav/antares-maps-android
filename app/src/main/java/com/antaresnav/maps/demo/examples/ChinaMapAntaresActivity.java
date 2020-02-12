package com.antaresnav.maps.demo.examples;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;

import com.antaresnav.maps.AntaresMap;
import com.antaresnav.maps.CameraUpdateFactory;
import com.antaresnav.maps.MapView;
import com.antaresnav.maps.OnMapReadyCallback;
import com.antaresnav.maps.china.ChinaCoordinateShifter;
import com.antaresnav.maps.demo.R;
import com.antaresnav.maps.demo.ui.BaseExampleActivity;
import com.antaresnav.maps.model.LatLng;
import com.antaresnav.maps.model.PolygonOptions;

/**
 * This shows how annotations works on a china map.
 */
public class ChinaMapAntaresActivity extends BaseExampleActivity {

    private MapView mapView;
    private AntaresMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mapView = findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(new OnMapReadyCallback() {

            @Override
            public void onMapReady(final AntaresMap antaresMap) {
                map =  antaresMap;
                map.setStyleUrl("https://www.dropbox.com/s/8hmakghpcw13jpv/gaode-china.json?dl=1");
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(39.9157743, 116.39360164), 15));
                map.addPolygon(new PolygonOptions()
                        .fillColor(0x88C2A83E)
                        .strokeColor(0xFFCD533B)
                        .add(new LatLng(39.92106609023439, 116.385894224215221),
                            new LatLng(39.912011392274721, 116.386332965169146),
                            new LatLng(39.912351642876217, 116.395615097438579),
                            new LatLng(39.921388843484642, 116.395161792469608),
                            new LatLng(39.92106609023439, 116.385894224215221)));
            }
        });

        Switch shifterSwitch = findViewById(R.id.shifterSwitch);
        shifterSwitch.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (map != null) {
                    map.setCoordinateShifter(isChecked ? new ChinaCoordinateShifter() : null);
                }
            }
        });
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.content_china_map_antares;
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
