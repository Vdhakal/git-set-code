//package com.example.git_set_code.helperClasses;
//
//
//import java.io.File;
//import java.lang.ref.WeakReference;
//import java.util.List;
//
//import com.example.git_set_code.R;
//import com.here.android.mpa.common.GeoBoundingBox;
//import com.here.android.mpa.common.GeoCoordinate;
//import com.here.android.mpa.common.GeoPosition;
//import com.here.android.mpa.common.OnEngineInitListener;
//import com.here.android.mpa.common.PositioningManager;
//import com.here.android.mpa.guidance.NavigationManager;
//import com.here.android.mpa.mapping.Map;
//import com.here.android.mpa.mapping.AndroidXMapFragment;
//import com.here.android.mpa.mapping.MapRoute;
//import com.here.android.mpa.odml.MapLoader;
//import com.here.android.mpa.odml.MapPackage;
//import com.here.android.mpa.routing.CoreRouter;
//import com.here.android.mpa.routing.Route;
//import com.here.android.mpa.routing.RouteOptions;
//import com.here.android.mpa.routing.RoutePlan;
//import com.here.android.mpa.routing.RouteResult;
//import com.here.android.mpa.routing.RouteWaypoint;
//import com.here.android.mpa.routing.Router;
//import com.here.android.mpa.routing.RoutingError;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.app.Activity;
//import android.app.AlertDialog;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.content.pm.ApplicationInfo;
//import android.content.pm.PackageManager;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.Toast;
//
//import soup.neumorphism.NeumorphButton;
//
///**
// * This class encapsulates the properties and functionality of the Map view.It also triggers a
// * turn-by-turn navigation from HERE Burnaby office to Langley BC.There is a sample voice skin
// * bundled within the SDK package to be used out-of-box, please refer to the Developer's guide for
// * the usage.
// */
//public class MapFragmentView {
//    private AndroidXMapFragment m_mapFragment;
//    private AppCompatActivity m_activity;
//    private Button m_naviControlButton;
//    private NavigationManager m_navigationManager;
//    private GeoBoundingBox m_geoBoundingBox;
//    private Route m_route;
//    private boolean m_foregroundServiceStarted;
//    private Map map = null;
//    // map fragment embedded in this activity
//    private AndroidXMapFragment mapFragment = null;
//    private PositioningManager.OnPositionChangedListener positionListener = null;
//    private PositioningManager posManager;
////      final FragmentManager mFragmentmanager = getChildFragmentMan
//
//    public MapFragmentView(AppCompatActivity activity) {
//        m_activity = activity;
//        initMapFragment();
//        initNaviControlButton();
//    }
//
//    private AndroidXMapFragment getMapFragment() {
//        return (AndroidXMapFragment) m_activity.getSupportFragmentManager().findFragmentById(R.id.mapfragment);
//    }
//
//    private void initMapFragment(Activity activity) {
//        PositioningManager.getInstance().addListener(WeakReference<OnPositionChangedListener>);
//        // Search for the map fragment to finish setup by calling init().
//        mapFragment = getMapFragment();
//
//        if(mapFragment!=null) {
//            // Set up disk map cache path for this application
//            // Use path under your application folder for storing the disk cache
//            com.here.android.mpa.common.MapSettings.setDiskCacheRootPath(
//                    activity.getExternalFilesDir(null) + File.separator + "com.example.git_set_code");
//            mapFragment.init(new OnEngineInitListener() {
//                @Override
//                public void onEngineInitializationCompleted(OnEngineInitListener.Error error) {
//                    posManager = PositioningManager.getInstance();
//                    MapLoader.getInstance().selectDataGroup(MapPackage.SelectableDataGroup.TruckAttributes);
//                    if (error == OnEngineInitListener.Error.NONE) {
//                        posManager.start(
//                                PositioningManager.LocationMethod.GPS_NETWORK);
//                        setPosition();
//                        GeoPosition geoPosition = posManager.getPosition();
//                        // retrieve a reference of the map from the map fragment
//                        map = mapFragment.getMap();
////                      Toast.makeText(getContext(), geoPosition.getCoordinate().toString()+"", Toast.LENGTH_SHORT).show();
//                        map.setZoomLevel((map.getMaxZoomLevel() + map.getMinZoomLevel()) / 2);
//                        mapFragment.getPositionIndicator().setVisible(true);
//                    } else {
//                        new androidx.appcompat.app.AlertDialog.Builder(activity).setMessage(
//                                "Error : " + error.name() + "\n\n" + error.getDetails())
//                                .setTitle("Her Engine Error")
//                                .setNegativeButton(android.R.string.cancel,
//                                        new DialogInterface.OnClickListener() {
//                                            @Override
//                                            public void onClick(
//                                                    DialogInterface dialog,
//                                                    int which) {
//                                                activity.finish();
//                                            }
//                                        }).create().show();
//                    }
//                }
//            });
//        }
//    }
//
//    private void setPosition() {
//        PositioningManager.OnPositionChangedListener positionListener = new
//                PositioningManager.OnPositionChangedListener() {
//
//                    public void onPositionUpdated(PositioningManager.LocationMethod method,
//                                                  GeoPosition position, boolean isMapMatched) {
//                            map.setCenter(position.getCoordinate(),
//                                    Map.Animation.BOW);
//                            loadMapScene();
//                            fabonclickListeners(map);
//                        }
//
//                    }
//
//                    public void onPositionFixChanged(PositioningManager.LocationMethod method,
//                                                     PositioningManager.LocationStatus status) {
//                    }
//                };
//        PositioningManager.getInstance().addListener(
//                new WeakReference<PositioningManager.OnPositionChangedListener>(positionListener));
//    }
//
//
//    private void fabonclickListeners(Map map1) {
//        fabMapScene.setOnClickListener(v -> {
//            if(map1.getMapScheme().equals(Map.Scheme.HYBRID_DAY))
//                map1.setMapScheme(Map.Scheme.NORMAL_DAY);
//            else
//                map1.setMapScheme(Map.Scheme.HYBRID_DAY);
//        });
//        fabMapLocation.setOnClickListener(v -> {
//
//        });
//        startNavigationButton.setOnClickListener(v -> {
//            // Create the RouteOptions and set its transport mode & routing type
//            RouteOptions routeOptions = new RouteOptions();
//            RoutePlan routePlan = new RoutePlan();
//            routeOptions.setTransportMode(RouteOptions.TransportMode.TRUCK);
//            routeOptions.setRouteType(RouteOptions.Type.FASTEST);
//            routePlan.setRouteOptions(routeOptions);
//
//
//        });
//    }
//
//    private void createRoute() {
//        /* Initialize a CoreRouter */
//        CoreRouter coreRouter = new CoreRouter();
//
//        /* Initialize a RoutePlan */
//        RoutePlan routePlan = new RoutePlan();
//
//        /*
//         * Initialize a RouteOption. HERE Mobile SDK allow users to define their own parameters for the
//         * route calculation,including transport modes,route types and route restrictions etc.Please
//         * refer to API doc for full list of APIs
//         */
//        RouteOptions routeOptions = new RouteOptions();
//        /* Other transport modes are also available e.g Pedestrian */
//        routeOptions.setTransportMode(RouteOptions.TransportMode.CAR);
//        /* Disable highway in this route. */
//        routeOptions.setHighwaysAllowed(false);
//        /* Calculate the shortest route available. */
//        routeOptions.setRouteType(RouteOptions.Type.SHORTEST);
//        /* Calculate 1 route. */
//        routeOptions.setRouteCount(1);
//        /* Finally set the route option */
//        routePlan.setRouteOptions(routeOptions);
//
//        /* Define waypoints for the route */
//        /* START: 4350 Still Creek Dr */
//        RouteWaypoint startPoint = new RouteWaypoint(new GeoCoordinate(49.259149, -123.008555));
//        /* END: Langley BC */
//        RouteWaypoint destination = new RouteWaypoint(new GeoCoordinate(49.073640, -122.559549));
//
//        /* Add both waypoints to the route plan */
//        routePlan.addWaypoint(startPoint);
//        routePlan.addWaypoint(destination);
//
//        /* Trigger the route calculation,results will be called back via the listener */
//        coreRouter.calculateRoute(routePlan,
//                new Router.Listener<List<RouteResult>, RoutingError>() {
//
//                    @Override
//                    public void onProgress(int i) {
//                        /* The calculation progress can be retrieved in this callback. */
//                    }
//
//                    @Override
//                    public void onCalculateRouteFinished(List<RouteResult> routeResults,
//                                                         RoutingError routingError) {
//                        /* Calculation is done.Let's handle the result */
//                        if (routingError == RoutingError.NONE) {
//                            if (routeResults.get(0).getRoute() != null) {
//
//                                m_route = routeResults.get(0).getRoute();
//                                /* Create a MapRoute so that it can be placed on the map */
//                                MapRoute mapRoute = new MapRoute(routeResults.get(0).getRoute());
//
//                                /* Show the maneuver number on top of the route */
//                                mapRoute.setManeuverNumberVisible(true);
//
//                                /* Add the MapRoute to the map */
//                                map.addMapObject(mapRoute);
//
//                                /*
//                                 * We may also want to make sure the map view is orientated properly
//                                 * so the entire route can be easily seen.
//                                 */
//                                m_geoBoundingBox = routeResults.get(0).getRoute().getBoundingBox();
//                                map.zoomTo(m_geoBoundingBox, Map.Animation.NONE,
//                                        Map.MOVE_PRESERVE_ORIENTATION);
//
//                                startNavigation();
//                            } else {
//                                Toast.makeText(m_activity,
//                                        "Error:route results returned is not valid",
//                                        Toast.LENGTH_LONG).show();
//                            }
//                        } else {
//                            Toast.makeText(m_activity,
//                                    "Error:route calculation returned error code: " + routingError,
//                                    Toast.LENGTH_LONG).show();
//
//                        }
//                    }
//                });
//    }
//
//    private void initNaviControlButton() {
//        m_naviControlButton = m_activity.findViewById(R.id.navigationButton);
//        m_naviControlButton.setText("Start Navigation");
//        m_naviControlButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//
//            public void onClick(View v) {
//                /*
//                 * To start a turn-by-turn navigation, a concrete route object is required.We use
//                 * the same steps from Routing sample app to create a route from 4350 Still Creek Dr
//                 * to Langley BC without going on HWY.
//                 *
//                 * The route calculation requires local map data.Unless there is pre-downloaded map
//                 * data on device by utilizing MapLoader APIs,it's not recommended to trigger the
//                 * route calculation immediately after the MapEngine is initialized.The
//                 * INSUFFICIENT_MAP_DATA error code may be returned by CoreRouter in this case.
//                 *
//                 */
//                if (m_route == null) {
//                    createRoute();
//                } else {
//                    m_navigationManager.stop();
//                    /*
//                     * Restore the map orientation to show entire route on screen
//                     */
//                    map.zoomTo(m_geoBoundingBox, Map.Animation.NONE, 0f);
//                    m_naviControlButton.setText("Start Navigation");
//                    m_route = null;
//                }
//            }
//        });
//    }
//
//    /*
//     * Android 8.0 (API level 26) limits how frequently background apps can retrieve the user's
//     * current location. Apps can receive location updates only a few times each hour.
//     * See href="https://developer.android.com/about/versions/oreo/background-location-limits.html
//     * In order to retrieve location updates more frequently start a foreground service.
//     * See https://developer.android.com/guide/components/services.html#Foreground
//     */
//    private void startForegroundService() {
//        if (!m_foregroundServiceStarted) {
//            m_foregroundServiceStarted = true;
//            Intent startIntent = new Intent(m_activity, ForegroundService.class);
//            startIntent.setAction(ForegroundService.START_ACTION);
//            m_activity.getApplicationContext().startService(startIntent);
//        }
//    }
//
//    private void stopForegroundService() {
//        if (m_foregroundServiceStarted) {
//            m_foregroundServiceStarted = false;
//            Intent stopIntent = new Intent(m_activity, ForegroundService.class);
//            stopIntent.setAction(ForegroundService.STOP_ACTION);
//            m_activity.getApplicationContext().startService(stopIntent);
//        }
//    }
//
//    private void startNavigation() {
//        m_naviControlButton.setText("Stop Navigation");
//        /* Configure Navigation manager to launch navigation on current map */
//        m_navigationManager.setMap(map);
//        // show position indicator
//        // note, it is also possible to change icon for the position indicator
//        mapFragment.getPositionIndicator().setVisible(true);
//
//        /*
//         * Start the turn-by-turn navigation.Please note if the transport mode of the passed-in
//         * route is pedestrian, the NavigationManager automatically triggers the guidance which is
//         * suitable for walking. Simulation and tracking modes can also be launched at this moment
//         * by calling either simulate() or startTracking()
//         */
//
//        /* Choose navigation modes between real time navigation and simulation */
//        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(m_activity);
//        alertDialogBuilder.setTitle("Navigation");
//        alertDialogBuilder.setMessage("Choose Mode");
//        alertDialogBuilder.setNegativeButton("Navigation",new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialoginterface, int i) {
//                m_navigationManager.startNavigation(m_route);
//                map.setTilt(60);
//                startForegroundService();
//            };
//        });
//        alertDialogBuilder.setPositiveButton("Simulation",new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialoginterface, int i) {
//                m_navigationManager.simulate(m_route,60);//Simualtion speed is set to 60 m/s
//                map.setTilt(60);
//                startForegroundService();
//            };
//        });
//        AlertDialog alertDialog = alertDialogBuilder.create();
//        alertDialog.show();
//        /*
//         * Set the map update mode to ROADVIEW.This will enable the automatic map movement based on
//         * the current location.If user gestures are expected during the navigation, it's
//         * recommended to set the map update mode to NONE first. Other supported update mode can be
//         * found in HERE Mobile SDK for Android (Premium) API doc
//         */
//        m_navigationManager.setMapUpdateMode(NavigationManager.MapUpdateMode.ROADVIEW);
//
//        /*
//         * NavigationManager contains a number of listeners which we can use to monitor the
//         * navigation status and getting relevant instructions.In this example, we will add 2
//         * listeners for demo purpose,please refer to HERE Android SDK API documentation for details
//         */
//        addNavigationListeners();
//    }
//
//    private void addNavigationListeners() {
//
//        /*
//         * Register a NavigationManagerEventListener to monitor the status change on
//         * NavigationManager
//         */
//        m_navigationManager.addNavigationManagerEventListener(
//                new WeakReference<NavigationManager.NavigationManagerEventListener>(
//                        m_navigationManagerEventListener));
//
//        /* Register a PositionListener to monitor the position updates */
//        m_navigationManager.addPositionListener(
//                new WeakReference<NavigationManager.PositionListener>(m_positionListener));
//    }
//
//    private NavigationManager.PositionListener m_positionListener = new NavigationManager.PositionListener() {
//        @Override
//        public void onPositionUpdated(GeoPosition geoPosition) {
//            /* Current position information can be retrieved in this callback */
//        }
//    };
//
//    private NavigationManager.NavigationManagerEventListener m_navigationManagerEventListener = new NavigationManager.NavigationManagerEventListener() {
//        @Override
//        public void onRunningStateChanged() {
//            Toast.makeText(m_activity, "Running state changed", Toast.LENGTH_SHORT).show();
//        }
//
//        @Override
//        public void onNavigationModeChanged() {
//            Toast.makeText(m_activity, "Navigation mode changed", Toast.LENGTH_SHORT).show();
//        }
//
//        @Override
//        public void onEnded(NavigationManager.NavigationMode navigationMode) {
//            Toast.makeText(m_activity, navigationMode + " was ended", Toast.LENGTH_SHORT).show();
//            stopForegroundService();
//        }
//
//        @Override
//        public void onMapUpdateModeChanged(NavigationManager.MapUpdateMode mapUpdateMode) {
//            Toast.makeText(m_activity, "Map update mode is changed to " + mapUpdateMode,
//                    Toast.LENGTH_SHORT).show();
//        }
//
//        @Override
//        public void onRouteUpdated(Route route) {
//            Toast.makeText(m_activity, "Route updated", Toast.LENGTH_SHORT).show();
//        }
//
//        @Override
//        public void onCountryInfo(String s, String s1) {
//            Toast.makeText(m_activity, "Country info updated from " + s + " to " + s1,
//                    Toast.LENGTH_SHORT).show();
//        }
//    };
//
//    public void onDestroy() {
//        /* Stop the navigation when app is destroyed */
//        if (m_navigationManager != null) {
//            stopForegroundService();
//            m_navigationManager.stop();
//        }
//    }
//}