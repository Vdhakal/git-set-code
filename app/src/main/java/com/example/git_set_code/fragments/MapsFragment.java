  package com.example.git_set_code.fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.git_set_code.R;
import com.example.git_set_code.helperClasses.ForegroundService;
import com.example.git_set_code.locations.PlatformPositioningProvider;
import com.example.git_set_code.trip_database.Database.TripDatabase;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.here.android.mpa.common.GeoBoundingBox;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.GeoPosition;
import com.here.android.mpa.common.OnEngineInitListener;
import com.here.android.mpa.common.PositioningManager;
import com.here.android.mpa.guidance.NavigationManager;
import com.here.android.mpa.mapping.AndroidXMapFragment;
import com.here.android.mpa.mapping.Map;
import com.here.android.mpa.mapping.MapRoute;
import com.here.android.mpa.mapping.MapView;
import com.here.android.mpa.odml.MapLoader;
import com.here.android.mpa.odml.MapPackage;
import com.here.android.mpa.prefetcher.MapDataPrefetcher;
import com.here.android.mpa.routing.CoreRouter;
import com.here.android.mpa.routing.Route;
import com.here.android.mpa.routing.RouteOptions;
import com.here.android.mpa.routing.RoutePlan;
import com.here.android.mpa.routing.RouteResult;
import com.here.android.mpa.routing.RouteWaypoint;
import com.here.android.mpa.routing.Router;
import com.here.android.mpa.routing.RoutingError;
import com.skydoves.powerspinner.PowerSpinnerView;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.List;

  public class MapsFragment extends Fragment implements LifecycleOwner {

      private ProgressBar progressBar;
      private PowerSpinnerView powerSpinnerView;
      private View rootView;
      private static final String TAG = "Maps";
      private FloatingActionButton fabMapScene, fabMapLocation;
      private Button startNavigationButton;
      private MapsFragment mapsFragment;
      private MapView mapView;
      private boolean markerAdded = false;
      private boolean mapHasBeenLoaded = false;
      private static final float DEFAULT_ZOOM_LEVEL = 14;
      boolean paused = false;
      // map embedded in the map fragment
      private Map map = null;
      // map fragment embedded in this activity
      private AndroidXMapFragment mapFragment = null;
      private PositioningManager.OnPositionChangedListener positionListener = null;
      private PositioningManager posManager;
      private NavigationManager m_navigationManager;
      private GeoBoundingBox m_geoBoundingBox;
      private Route m_route;
      private boolean m_foregroundServiceStarted;
//      final FragmentManager mFragmentmanager = getChildFragmentManager();
      private final OnMapReadyCallback callback = new OnMapReadyCallback() {

        @Override
        public void onMapReady(GoogleMap googleMap) {
            LatLng sydney = new LatLng(-34, 151);
        }

      };
      @Nullable
      @Override
      public View onCreateView(@NonNull LayoutInflater inflater,
                                 @Nullable ViewGroup container,
                                 @Nullable Bundle savedInstanceState) {
          rootView =  inflater.inflate(R.layout.fragment_maps, container, false);
          progressBar = rootView.findViewById(R.id.progress_circular);
          fabMapScene = rootView.findViewById(R.id.fab_satelite);
          fabMapLocation = rootView.findViewById(R.id.fab_CurrentLocation);
          startNavigationButton = rootView.findViewById(R.id.navigationButton);
          powerSpinnerView =  rootView.findViewById(R.id.power_spinner);
          startNavigationButton.setVisibility(View.GONE);
          fabMapLocation.setVisibility(View.GONE);
          fabMapScene.setVisibility(View.GONE);
          powerSpinnerView.setVisibility(View.GONE);

        return rootView;

        }
        private AndroidXMapFragment getInstance(){
          if(mapFragment==null)
              mapFragment = getMapFragment();
          return mapFragment;
        }
      private AndroidXMapFragment getMapFragment() {
          return (AndroidXMapFragment) getChildFragmentManager().findFragmentById(R.id.mapfragment);

      }
      private void initialize() {
//          PositioningManager.getInstance().addListener(WeakReference<OnPositionChangedListener>);
          // Search for the map fragment to finish setup by calling init().
          mapFragment = getInstance();

          if(mapFragment!=null) {
              // Set up disk map cache path for this application
              // Use path under your application folder for storing the disk cache
              com.here.android.mpa.common.MapSettings.setDiskCacheRootPath(
                      getContext().getExternalFilesDir(null) + File.separator + "com.example.git_set_code");
              mapFragment.init(new OnEngineInitListener() {
                  @Override
                  public void onEngineInitializationCompleted(OnEngineInitListener.Error error) {
                      posManager = PositioningManager.getInstance();
                      MapLoader.getInstance().selectDataGroup(MapPackage.SelectableDataGroup.TruckAttributes);
                      if (error == OnEngineInitListener.Error.NONE) {
                          posManager.start(
                                  PositioningManager.LocationMethod.GPS_NETWORK);
                          setPosition();
                          GeoPosition geoPosition = posManager.getPosition();
                          // retrieve a reference of the map from the map fragment
                          map = mapFragment.getMap();
                          map.setCenter(new GeoCoordinate(32.52845001220703,-92.07698059082031),Map.Animation.NONE);
                          m_navigationManager = NavigationManager.getInstance();
//                      Toast.makeText(getContext(), geoPosition.getCoordinate().toString()+"", Toast.LENGTH_SHORT).show();
                          map.setZoomLevel((map.getMaxZoomLevel() + map.getMinZoomLevel()) / 2);
                          mapFragment.getPositionIndicator().setVisible(true);
                      } else {
                          new AlertDialog.Builder(getActivity()).setMessage(
                                  "Error : " + error.name() + "\n\n" + error.getDetails())
                                  .setTitle("Her Engine Error")
                                  .setNegativeButton(android.R.string.cancel,
                                          new DialogInterface.OnClickListener() {
                                              @Override
                                              public void onClick(
                                                      DialogInterface dialog,
                                                      int which) {
                                                  getActivity().finish();
                                              }
                                          }).create().show();
                      }
                  }
              });
          }
      }

      private void setPosition() {
          PositioningManager.OnPositionChangedListener positionListener = new
                  PositioningManager.OnPositionChangedListener() {

          public void onPositionUpdated(PositioningManager.LocationMethod method,
                                        GeoPosition position, boolean isMapMatched) {
              if (!paused) {
                  map.setCenter(position.getCoordinate(),
                          Map.Animation.LINEAR);
                  loadMapScene();
                  initNaviControlButton(position);
                  fabonclickListeners(map, position);
              }

          }

          public void onPositionFixChanged(PositioningManager.LocationMethod method,
                                           PositioningManager.LocationStatus status) {
          }
      };
          PositioningManager.getInstance().addListener(
                  new WeakReference<PositioningManager.OnPositionChangedListener>(positionListener));
      }


      private void fabonclickListeners(Map map1, GeoPosition position) {
        fabMapScene.setOnClickListener(v -> {
            if(map1.getMapScheme().equals(Map.Scheme.HYBRID_DAY))
                map1.setMapScheme(Map.Scheme.NORMAL_DAY);
            else
                map1.setMapScheme(Map.Scheme.HYBRID_DAY);
        });
        fabMapLocation.setOnClickListener(v -> {
            map1.setCenter(position.getCoordinate(),
                    Map.Animation.LINEAR);
            map1.setZoomLevel(map.getMaxZoomLevel()-2);
        });
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
            initialize();

    }

      private void loadMapScene() {

        progressBar.setVisibility(View.GONE);
        startNavigationButton.setVisibility(View.VISIBLE);
        fabMapLocation.setVisibility(View.VISIBLE);
        fabMapScene.setVisibility(View.VISIBLE);
        powerSpinnerView.setVisibility(View.VISIBLE);

    }


      private void createRoute(GeoPosition position) {
          /* Initialize a CoreRouter */
          CoreRouter coreRouter = new CoreRouter();

          /* Initialize a RoutePlan */
          RoutePlan routePlan = new RoutePlan();

          /*
           * Initialize a RouteOption. HERE Mobile SDK allow users to define their own parameters for the
           * route calculation,including transport modes,route types and route restrictions etc.Please
           * refer to API doc for full list of APIs
           */
          RouteOptions routeOptions = new RouteOptions();
          /* Other transport modes are also available e.g Pedestrian */
          routeOptions.setTransportMode(RouteOptions.TransportMode.TRUCK);
          /* Calculate the shortest route available. */
          routeOptions.setRouteType(RouteOptions.Type.SHORTEST);
          /* Calculate 1 route. */
          routeOptions.setRouteCount(1);
          /* Finally set the route option */
          routePlan.setRouteOptions(routeOptions);

          /* Define waypoints for the route */
          /* START: 4350 Still Creek Dr */
          RouteWaypoint startPoint = new RouteWaypoint(position.getCoordinate());
          /* END: Langley BC */
          RouteWaypoint destination = new RouteWaypoint(new GeoCoordinate(32.51322, -92.157874));

          /* Add both waypoints to the route plan */
          routePlan.addWaypoint(startPoint);
          routePlan.addWaypoint(destination);

          /* Trigger the route calculation,results will be called back via the listener */
          coreRouter.calculateRoute(routePlan,
                  new Router.Listener<List<RouteResult>, RoutingError>() {

                      @Override
                      public void onProgress(int i) {
                          /* The calculation progress can be retrieved in this callback. */
                      }

                      @Override
                      public void onCalculateRouteFinished(List<RouteResult> routeResults,
                                                           RoutingError routingError) {
                          /* Calculation is done.Let's handle the result */
                          if (routingError == RoutingError.NONE) {
                              if (routeResults.get(0).getRoute() != null) {

                                  m_route = routeResults.get(0).getRoute();
                                  /* Create a MapRoute so that it can be placed on the map */
                                  MapRoute mapRoute = new MapRoute(routeResults.get(0).getRoute());

                                  /* Show the maneuver number on top of the route */
                                  mapRoute.setManeuverNumberVisible(true);

                                  /* Add the MapRoute to the map */
                                  map.addMapObject(mapRoute);

                                  /*
                                   * We may also want to make sure the map view is orientated properly
                                   * so the entire route can be easily seen.
                                   */
                                  m_geoBoundingBox = routeResults.get(0).getRoute().getBoundingBox();
                                  map.zoomTo(m_geoBoundingBox, Map.Animation.NONE,
                                          Map.MOVE_PRESERVE_ORIENTATION);

                                  startNavigation();
                              } else {
                                  Toast.makeText(getActivity(),
                                          "Error:route results returned is not valid",
                                          Toast.LENGTH_LONG).show();
                              }
                          } else {
                              Toast.makeText(getActivity(),
                                      "Error:route calculation returned error code: " + routingError,
                                      Toast.LENGTH_LONG).show();

                          }
                      }
                  });
      }

      private void initNaviControlButton(GeoPosition position) {
          startNavigationButton = getActivity().findViewById(R.id.navigationButton);
          startNavigationButton.setText("Start Navigation");
          startNavigationButton.setOnClickListener(new View.OnClickListener() {
              @Override

              public void onClick(View v) {
                  /*
                   * To start a turn-by-turn navigation, a concrete route object is required.We use
                   * the same steps from Routing sample app to create a route from 4350 Still Creek Dr
                   * to Langley BC without going on HWY.
                   *
                   * The route calculation requires local map data.Unless there is pre-downloaded map
                   * data on device by utilizing MapLoader APIs,it's not recommended to trigger the
                   * route calculation immediately after the MapEngine is initialized.The
                   * INSUFFICIENT_MAP_DATA error code may be returned by CoreRouter in this case.
                   *
                   */
                  if (m_route == null) {
                      createRoute(position);
                  } else {
                      m_navigationManager.stop();
                      /*
                       * Restore the map orientation to show entire route on screen
                       */
                      map.zoomTo(m_geoBoundingBox, Map.Animation.NONE, 0f);
                      startNavigationButton.setText("Start Navigation");
                      m_route = null;
                  }
              }
          });
      }

      /*
       * Android 8.0 (API level 26) limits how frequently background apps can retrieve the user's
       * current location. Apps can receive location updates only a few times each hour.
       * See href="https://developer.android.com/about/versions/oreo/background-location-limits.html
       * In order to retrieve location updates more frequently start a foreground service.
       * See https://developer.android.com/guide/components/services.html#Foreground
       */
      private void startForegroundService() {
          if (!m_foregroundServiceStarted) {
              m_foregroundServiceStarted = true;
              Intent startIntent = new Intent(getActivity(), ForegroundService.class);
              startIntent.setAction(ForegroundService.START_ACTION);
              getActivity().getApplicationContext().startService(startIntent);
          }
      }

      private void stopForegroundService() {
          if (m_foregroundServiceStarted) {
              m_foregroundServiceStarted = false;
              Intent stopIntent = new Intent(getActivity(), ForegroundService.class);
              stopIntent.setAction(ForegroundService.STOP_ACTION);
              getActivity().getApplicationContext().startService(stopIntent);
          }
      }

      private void startNavigation() {
          startNavigationButton.setText("Stop Navigation");
          /* Configure Navigation manager to launch navigation on current map */
          m_navigationManager.setMap(map);
          // show position indicator
          // note, it is also possible to change icon for the position indicator
          mapFragment.getPositionIndicator().setVisible(true);

          /*
           * Start the turn-by-turn navigation.Please note if the transport mode of the passed-in
           * route is pedestrian, the NavigationManager automatically triggers the guidance which is
           * suitable for walking. Simulation and tracking modes can also be launched at this moment
           * by calling either simulate() or startTracking()
           */

          /* Choose navigation modes between real time navigation and simulation */
          android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(getActivity());
          alertDialogBuilder.setTitle("Navigation");
          alertDialogBuilder.setMessage("Choose Mode");
          alertDialogBuilder.setNegativeButton("Navigation",new DialogInterface.OnClickListener() {
              public void onClick(DialogInterface dialoginterface, int i) {
                  m_navigationManager.startNavigation(m_route);
                  map.setTilt(60);
                  startForegroundService();
              };
          });
          alertDialogBuilder.setPositiveButton("Simulation",new DialogInterface.OnClickListener() {
              public void onClick(DialogInterface dialoginterface, int i) {
                  m_navigationManager.simulate(m_route,60);//Simualtion speed is set to 60 m/s
                  map.setTilt(60);
                  startForegroundService();
              };
          });
          android.app.AlertDialog alertDialog = alertDialogBuilder.create();
          alertDialog.show();
          /*
           * Set the map update mode to ROADVIEW.This will enable the automatic map movement based on
           * the current location.If user gestures are expected during the navigation, it's
           * recommended to set the map update mode to NONE first. Other supported update mode can be
           * found in HERE Mobile SDK for Android (Premium) API doc
           */
          m_navigationManager.setMapUpdateMode(NavigationManager.MapUpdateMode.ROADVIEW);

          /*
           * NavigationManager contains a number of listeners which we can use to monitor the
           * navigation status and getting relevant instructions.In this example, we will add 2
           * listeners for demo purpose,please refer to HERE Android SDK API documentation for details
           */
          addNavigationListeners();
      }

      private void addNavigationListeners() {

          /*
           * Register a NavigationManagerEventListener to monitor the status change on
           * NavigationManager
           */
          m_navigationManager.addNavigationManagerEventListener(
                  new WeakReference<NavigationManager.NavigationManagerEventListener>(
                          m_navigationManagerEventListener));

          /* Register a PositionListener to monitor the position updates */
          m_navigationManager.addPositionListener(
                  new WeakReference<NavigationManager.PositionListener>(m_positionListener));
      }

      private NavigationManager.PositionListener m_positionListener = new NavigationManager.PositionListener() {
          @Override
          public void onPositionUpdated(GeoPosition geoPosition) {
              /* Current position information can be retrieved in this callback */
          }
      };

      private NavigationManager.NavigationManagerEventListener m_navigationManagerEventListener = new NavigationManager.NavigationManagerEventListener() {
          @Override
          public void onRunningStateChanged() {
              Toast.makeText(getActivity(), "Running state changed", Toast.LENGTH_SHORT).show();
          }

          @Override
          public void onNavigationModeChanged() {
              Toast.makeText(getActivity(), "Navigation mode changed", Toast.LENGTH_SHORT).show();
          }

          @Override
          public void onEnded(NavigationManager.NavigationMode navigationMode) {
              Toast.makeText(getActivity(), navigationMode + " was ended", Toast.LENGTH_SHORT).show();
              stopForegroundService();
          }

          @Override
          public void onMapUpdateModeChanged(NavigationManager.MapUpdateMode mapUpdateMode) {
              Toast.makeText(getActivity(), "Map update mode is changed to " + mapUpdateMode,
                      Toast.LENGTH_SHORT).show();
          }

          @Override
          public void onRouteUpdated(Route route) {
              Toast.makeText(getActivity(), "Route updated", Toast.LENGTH_SHORT).show();
          }

          @Override
          public void onCountryInfo(String s, String s1) {
              Toast.makeText(getActivity(), "Country info updated from " + s + " to " + s1,
                      Toast.LENGTH_SHORT).show();
          }
      };
      private void startWaypoint(android.location.Location location) {


      }


    @Override
    public void onPause() {
        if (posManager != null) {
            posManager.stop();
        }
        super.onPause();
        paused = true;
    }

    @Override
    public void onResume() {
          super.onResume();
          paused = false;
          if (posManager != null) {
              Toast.makeText(getContext(), "On Resume", Toast.LENGTH_SHORT).show();
              posManager.start(
                      PositioningManager.LocationMethod.GPS_NETWORK);
          }

    }

    @Override
    public void onDestroy() {
        if (posManager != null) {
            // Cleanup
            posManager.removeListener(
                    positionListener);
        }
        map = null;
        super.onDestroy();
    }
}