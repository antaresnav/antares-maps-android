/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.antaresnav.maps.demo.ui;

import com.antaresnav.maps.demo.R;
import com.antaresnav.maps.demo.examples.BasicMapAntaresActivity;
import com.antaresnav.maps.demo.examples.BasicMapGoogleActivity;
import com.antaresnav.maps.demo.examples.CameraAntaresActivity;
import com.antaresnav.maps.demo.examples.CameraGoogleActivity;
import com.antaresnav.maps.demo.examples.ChinaMapAntaresActivity;
import com.antaresnav.maps.demo.examples.EventsAntaresActivity;
import com.antaresnav.maps.demo.examples.EventsGoogleActivity;
import com.antaresnav.maps.demo.examples.MarkerAntaresActivity;
import com.antaresnav.maps.demo.examples.MarkerGoogleActivity;
import com.antaresnav.maps.demo.examples.MultiMapAntaresActivity;
import com.antaresnav.maps.demo.examples.MultiMapGoogleActivity;
import com.antaresnav.maps.demo.examples.PolygonAntaresActivity;
import com.antaresnav.maps.demo.examples.PolygonGoogleActivity;
import com.antaresnav.maps.demo.examples.PolylineAntaresActivity;
import com.antaresnav.maps.demo.examples.PolylineGoogleActivity;

import java.util.Arrays;
import java.util.List;

/**
 * A list of all the examples.
 */
public final class Examples {

    private Examples() {
    }

    public static final List<ExampleDetails> EXAMPLES = Arrays.asList(
            new ExampleDetails(R.string.basic_map_title,
                    R.string.basic_map_description,
                    ExampleDetails.SDK_TYPE_ANTARES,
                    BasicMapAntaresActivity.class),
            new ExampleDetails(R.string.basic_map_title,
                    R.string.basic_map_description,
                    ExampleDetails.SDK_TYPE_GOOGLE,
                    BasicMapGoogleActivity.class),
            new ExampleDetails(R.string.camera_demo_label,
                    R.string.camera_demo_description,
                    ExampleDetails.SDK_TYPE_ANTARES,
                    CameraAntaresActivity.class),
            new ExampleDetails(R.string.camera_demo_label,
                    R.string.camera_demo_description,
                    ExampleDetails.SDK_TYPE_GOOGLE,
                    CameraGoogleActivity.class),
            new ExampleDetails(R.string.events_demo_label,
                    R.string.events_demo_description,
                    ExampleDetails.SDK_TYPE_ANTARES,
                    EventsAntaresActivity.class),
            new ExampleDetails(R.string.events_demo_label,
                    R.string.events_demo_description,
                    ExampleDetails.SDK_TYPE_GOOGLE,
                    EventsGoogleActivity.class),
            new ExampleDetails(R.string.marker_demo_label,
                    R.string.marker_demo_description,
                    ExampleDetails.SDK_TYPE_ANTARES,
                    MarkerAntaresActivity.class),
            new ExampleDetails(R.string.marker_demo_label,
                    R.string.marker_demo_description,
                    ExampleDetails.SDK_TYPE_GOOGLE,
                    MarkerGoogleActivity.class),
            new ExampleDetails(R.string.multi_map_demo_label,
                    R.string.multi_map_demo_description,
                    ExampleDetails.SDK_TYPE_ANTARES,
                    MultiMapAntaresActivity.class),
            new ExampleDetails(R.string.multi_map_demo_label,
                    R.string.multi_map_demo_description,
                    ExampleDetails.SDK_TYPE_GOOGLE,
                    MultiMapGoogleActivity.class),
            new ExampleDetails(R.string.polygon_demo_label,
                    R.string.polygon_demo_description,
                    ExampleDetails.SDK_TYPE_ANTARES,
                    PolygonAntaresActivity.class),
            new ExampleDetails(R.string.polygon_demo_label,
                    R.string.polygon_demo_description,
                    ExampleDetails.SDK_TYPE_GOOGLE,
                    PolygonGoogleActivity.class),
            new ExampleDetails(R.string.polyline_demo_label,
                    R.string.polyline_demo_description,
                    ExampleDetails.SDK_TYPE_ANTARES,
                    PolylineAntaresActivity.class),
            new ExampleDetails(R.string.polyline_demo_label,
                    R.string.polyline_demo_description,
                    ExampleDetails.SDK_TYPE_GOOGLE,
                    PolylineGoogleActivity.class),
            new ExampleDetails(R.string.china_map_demo_label,
                       R.string.china_map_demo_description,
                       ExampleDetails.SDK_TYPE_ANTARES,
                       ChinaMapAntaresActivity.class));
}
