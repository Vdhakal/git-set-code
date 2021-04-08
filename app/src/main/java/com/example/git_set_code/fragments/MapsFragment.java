  package com.example.git_set_code.fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.git_set_code.R;
import com.example.git_set_code.locations.PlatformPositioningProvider;
import com.example.git_set_code.permissions.PermissionsRequestor;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.here.sdk.core.GeoCoordinates;
import com.here.sdk.core.Location;
import com.here.sdk.mapview.MapError;
import com.here.sdk.mapview.MapScene;
import com.here.sdk.mapview.MapScheme;
import com.here.sdk.mapview.MapView;

import java.util.Date;

public class MapsFragment extends Fragment {

    private static final String TAG = "Maps";
    private PermissionsRequestor permissionsRequestor;
    private MapView mapView;
    private View rootView;
    private Location currentLocation;

    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {
            LatLng sydney = new LatLng(-34, 151);
        }
    };

    public MapsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        rootView =  inflater.inflate(R.layout.fragment_maps, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        SupportMapFragment mapFragment =
//                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapView = rootView.findViewById(R.id.map_view);
        if (mapView != null) {
            mapView.onCreate(savedInstanceState);
            //ASK FOR PERMISSIONS
            mapView.setOnReadyListener(new MapView.OnReadyListener() {
                @Override
                public void onMapViewReady() {
                    // This will be called each time after this activity is resumed.
                    // It will not be called before the first map scene was loaded.
                    // Any code that requires map data may not work as expected beforehand.
                    Log.d(TAG, "HERE Rendering Engine attached.");
                }
            });

            handleAndroidPermissions();
        }
    }
    private void handleAndroidPermissions() {
        permissionsRequestor = new PermissionsRequestor(getActivity());
        permissionsRequestor.request(new PermissionsRequestor.ResultListener(){

            @Override
            public void permissionsGranted() {
                loadMapScene();
            }

            @Override
            public void permissionsDenied() {
                Log.e(TAG, "Permissions denied by user.");
            }
        });

    }
    private void loadMapScene() {
        // Load a scene from the HERE SDK to render the map with a map scheme.
        mapView.getMapScene().loadScene(MapScheme.HYBRID_NIGHT, new MapScene.LoadSceneCallback() {
            @Override
            public void onLoadScene(@Nullable MapError mapError) {
                if (mapError == null) {
                    PlatformPositioningProvider platformPositioningProvider = new PlatformPositioningProvider(getContext());
                    platformPositioningProvider.startLocating(new PlatformPositioningProvider.PlatformLocationListener() {
                        @Override
                        public void onLocationUpdated(android.location.Location location) {

                            double distanceInMeters = 1000 * 0.1;
                            mapView.getCamera().lookAt(
                                    convertLocation(location).coordinates, distanceInMeters);
                        }
                    });

                } else {
                    Log.d(TAG, "Loading map failed: mapError: " + mapError.name());
                }
            }
        });
    }

    private Location convertLocation(android.location.Location nativeLocation) {

        GeoCoordinates geoCoordinates = new GeoCoordinates(
                nativeLocation.getLatitude(),
                nativeLocation.getLongitude(),
                nativeLocation.getAltitude());

        Location location = new Location(geoCoordinates, new Date());

        if (nativeLocation.hasBearing()) {
            location.bearingInDegrees = (double) nativeLocation.getBearing();
        }

        if (nativeLocation.hasSpeed()) {
            location.speedInMetersPerSecond = (double) nativeLocation.getSpeed();
        }

        if (nativeLocation.hasAccuracy()) {
            location.horizontalAccuracyInMeters = (double) nativeLocation.getAccuracy();
        }

        return location;
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
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }
}