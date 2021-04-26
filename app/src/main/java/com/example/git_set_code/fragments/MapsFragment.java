  package com.example.git_set_code.fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.git_set_code.R;
import com.example.git_set_code.locations.PlatformPositioningProvider;
import com.example.git_set_code.trip_database.Database.TripDatabase;
import com.example.git_set_code.viewmodels.MapViewModel;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.here.sdk.core.Color;
import com.here.sdk.mapview.MapCamera;
import com.here.sdk.mapview.MapError;
import com.here.sdk.mapview.MapImage;
import com.here.sdk.mapview.MapImageFactory;
import com.here.sdk.mapview.MapMarker;
import com.here.sdk.mapview.MapPolyline;
import com.here.sdk.mapview.MapScene;
import com.here.sdk.mapview.MapScheme;
import com.here.sdk.mapview.MapView;
import com.skydoves.powerspinner.PowerSpinnerView;

  public class MapsFragment extends Fragment implements LifecycleOwner {

      private ProgressBar progressBar;
      private PowerSpinnerView powerSpinnerView;
      private View rootView;
      private static final String TAG = "Maps";
      private MapViewModel mapViewModel;
      private FloatingActionButton fabMapScene, fabMapLocation;
      private Button startNavigationButton;
      private MapView mapView;
      private boolean markerAdded = false;
      private boolean mapHasBeenLoaded = false;
      private static final float DEFAULT_ZOOM_LEVEL = 14;
      private static MapsFragment mapFragment;

    private final OnMapReadyCallback callback = new OnMapReadyCallback() {

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
     public MapsFragment(){

    }
      public static MapsFragment getInstance() {
          if (mapFragment == null) {
              synchronized (TripDatabase.class) {
                  mapFragment = new MapsFragment();
              }
          }
          return mapFragment;
      }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        rootView =  inflater.inflate(R.layout.fragment_maps, container, false);
        mapViewModel = new ViewModelProvider(requireActivity()).get(MapViewModel.class);
        mapViewModel.init(getContext());


        progressBar = rootView.findViewById(R.id.progress_circular);
        fabMapScene = rootView.findViewById(R.id.fab_satelite);
        fabMapLocation = rootView.findViewById(R.id.fab_CurrentLocation);
        startNavigationButton = rootView.findViewById(R.id.navigationButton);
        powerSpinnerView =  rootView.findViewById(R.id.power_spinner);
        startNavigationButton.setVisibility(View.GONE);
        fabMapLocation.setVisibility(View.GONE);
        fabMapScene.setVisibility(View.GONE);
        powerSpinnerView.setVisibility(View.GONE);
        setUpSpinner();
        fabonclickListeners();

        navigationClickListener(rootView);
        return rootView;

    }

      private void setUpSpinner() {
          powerSpinnerView.showOrDismiss();
      }

      private void navigationClickListener(View rootView) {
          startNavigationButton.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  mapViewModel.calculateRoute();
                  mapViewModel.getMapView().getCamera().setTargetOrientation(new MapCamera.OrientationUpdate(90.0, 180.0));
//                  mapViewModel.getMapView().getCamera().lookAt(mapViewModel.getRouteGeoBox(), new MapCamera.OrientationUpdate());
                  mapViewModel.getMapView().getCamera().flyTo(mapViewModel.getCurrentLocation().coordinates,1000*0.1, new MapCamera.FlyToOptions());
              }
          });

      }



      private void fabonclickListeners() {
        fabMapScene.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mapViewModel.fabMapSceneClick();
                loadMapScene();
            }
        });
        fabMapLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mapViewModel.fabLocationClick();
                mapViewModel.getMapView().getCamera().flyTo(mapViewModel.getCurrentLocation().coordinates,1000*0.1, new MapCamera.FlyToOptions());
            }
        });
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mapView = rootView.findViewById(R.id.map_view);
        mapViewModel.setMapView(mapView);
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
        loadMapScene();
        }
    }

      private void loadMapScene() {
          if(!mapViewModel.isMapHasBeenLoaded()) mapViewModel.setMapScheme(MapScheme.NORMAL_DAY);
          PlatformPositioningProvider platformPositioningProvider = new PlatformPositioningProvider(mapViewModel.getContext());
          platformPositioningProvider.startLocating(new PlatformPositioningProvider.PlatformLocationListener() {
            @Override
            public void onLocationUpdated(android.location.Location location) {
                // Load a scene from the HERE SDK to render the map with a map scheme.
                mapView.getMapScene().loadScene(mapViewModel.getMapScheme(), new MapScene.LoadSceneCallback() {
                    @Override
                    public void onLoadScene(@Nullable MapError mapError) {
                        if (mapError == null) {
                            mapViewModel.setMapHasBeenLoaded(true);
                            mapHasBeenLoaded = true;
                            mapView.getCamera().lookAt(
                                    mapViewModel.convertLocation(location).coordinates, mapViewModel.getMapView().getCameraDistance());

                            if(!markerAdded)startWaypoint(location);

                            if(mapViewModel.isRouteHasBeenDrawn()){
                                Color lineColor = Color.valueOf(0, 0.56f, 0.54f, 0.63f);
                                MapPolyline routePolyLine = new MapPolyline(mapViewModel.getRouteGeoPolyline(), 20, lineColor);
                                mapView.getMapScene().addMapPolyline(routePolyLine);
                                startNavigationButton.setText("Re-Navigate");
                            }

                        } else {
                            Log.d(TAG, "Loading map failed: mapError: " + mapError.name());
                            Toast.makeText(getContext(), "Map Failed", Toast.LENGTH_SHORT).show();
                        }
                    }

                });
                progressBar.setVisibility(View.GONE);
                startNavigationButton.setVisibility(View.VISIBLE);
                fabMapLocation.setVisibility(View.VISIBLE);
                fabMapScene.setVisibility(View.VISIBLE);
                powerSpinnerView.setVisibility(View.VISIBLE);
            }
        });

    }

      private void startWaypoint(android.location.Location location) {
        Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.truck_top, null);
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        MapImage wayPointImage = MapImageFactory.fromBitmap(Bitmap.createScaledBitmap(bitmap, 75, 75, true));
        markerAdded = true;
        MapMarker wayPointMarker = new MapMarker(mapViewModel.getCurrentLocation().coordinates, wayPointImage);
        mapView.getMapScene().addMapMarker(wayPointMarker);
        mapViewModel.addWayPoints();
        mapViewModel.getWaypointMarkers().add(wayPointMarker);

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