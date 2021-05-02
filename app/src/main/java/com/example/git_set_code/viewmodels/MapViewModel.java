//package com.example.git_set_code.viewmodels;
//
//import android.content.Context;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.ProgressBar;
//import android.widget.Toast;
//
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//import androidx.lifecycle.ViewModel;
//
//import com.example.git_set_code.permissions.PermissionsRequestor;
//import com.google.android.material.floatingactionbutton.FloatingActionButton;
//import com.here.sdk.core.Color;
//import com.here.sdk.core.GeoBox;
//import com.here.sdk.core.GeoCoordinates;
//import com.here.sdk.core.GeoPolyline;
//import com.here.sdk.core.Location;
//import com.here.sdk.core.errors.InstantiationErrorException;
//import com.here.sdk.mapview.MapCamera;
//import com.here.sdk.mapview.MapError;
//import com.here.sdk.mapview.MapMarker;
//import com.here.sdk.mapview.MapPolyline;
//import com.here.sdk.mapview.MapScene;
//import com.here.sdk.mapview.MapScheme;
//import com.here.sdk.mapview.MapView;
//import com.here.sdk.routing.CalculateRouteCallback;
//import com.here.sdk.routing.CarOptions;
//import com.here.sdk.routing.Maneuver;
//import com.here.sdk.routing.ManeuverAction;
//import com.here.sdk.routing.Route;
//import com.here.sdk.routing.RoutingEngine;
//import com.here.sdk.routing.RoutingError;
//import com.here.sdk.routing.Section;
//import com.here.sdk.routing.Waypoint;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import static android.content.ContentValues.TAG;
//
//public class MapViewModel extends ViewModel {
//    private Fragment mapFragment;
//    private MapScheme mapScheme;
//    private MapView mapView;
//    private Context context;
//    private Location currentLocation;
//    private  double distanceInMeters = 1000 * 0.1;
//    private RoutingEngine routingEngine;
//    private List<Waypoint> waypoints = new ArrayList<>();
//    private List<MapMarker> waypointMarkers = new ArrayList<>();
//    private MapPolyline routePolyLine;
//    private boolean routeHasBeenDrawn = false;
//    private Route route;
//    GeoPolyline routeGeoPolyline;
//    private boolean mapHasBeenLoaded = false;
//    private GeoBox routeGeoBox;
//
//    public GeoBox getRouteGeoBox() {
//        return routeGeoBox;
//    }
//    public void setMapScheme(MapScheme mapScheme) {
//        this.mapScheme = mapScheme;
//    }
//
//    android.location.Location location;
//
//
//    public boolean isMapHasBeenLoaded() {
//        return mapHasBeenLoaded;
//    }
//
//    public void setMapHasBeenLoaded(boolean mapHasBeenLoaded) {
//        this.mapHasBeenLoaded = mapHasBeenLoaded;
//    }
//
//    public GeoPolyline getRouteGeoPolyline() {
//        return routeGeoPolyline;
//    }
//
//    public Fragment getMapFragment() {
//        return mapFragment;
//    }
//
//    public MapScheme getMapScheme() {
//        return mapScheme;
//    }
//
//    public Context getContext() {
//        return context;
//    }
//
//    public Location getCurrentLocation() {
//        return currentLocation;
//    }
//
//    public double getDistanceInMeters() {
//        return distanceInMeters;
//    }
//
//    public RoutingEngine getRoutingEngine() {
//        return routingEngine;
//    }
//
//    public List<Waypoint> getWaypoints() {
//        return waypoints;
//    }
//
//    public List<MapMarker> getWaypointMarkers() {
//        return waypointMarkers;
//    }
//
//    public MapView getMapView() {
//        return mapView;
//    }
//
//    public void setMapView(MapView mapView) {
//        this.mapView = mapView;
//    }
//
//    public boolean isRouteHasBeenDrawn() {
//        return routeHasBeenDrawn;
//    }
//
//    public void init(Context context){
//        this.context = context;
//
//    }
//    public void addWayPoints(){
//        if(waypoints.size()==0) {
//            waypoints.add(new Waypoint(new GeoCoordinates(32.50279, -92.11615)));
//            waypoints.add(new Waypoint(currentLocation.coordinates));
//        }
//    }
//
//    public void calculateRoute() {
//        try {
//            routingEngine = new RoutingEngine();
//        }catch (InstantiationErrorException e){
//
//        }
//        routingEngine.calculateRoute(waypoints,
//                new CarOptions(),
//                new CalculateRouteCallback() {
//                    @Override
//                    public void onRouteCalculated(@Nullable RoutingError routingError, @Nullable List<Route> routes) {
//                        if(routingError==null){
//                            route = routes.get(0);
//                            try {
//                                routeGeoPolyline = new GeoPolyline(route.getPolyline());
//                            }catch (InstantiationErrorException e){
//                                return;
//                            }
//                            routeGeoBox = route.getBoundingBox();
//                            mapView.getCamera().lookAt(routeGeoBox, new MapCamera.OrientationUpdate());
//                            Color lineColor = Color.valueOf(0, 0.56f, 0.54f, 0.63f);
//                            routePolyLine = new MapPolyline(routeGeoPolyline, 20, lineColor);
//                            drawRoute();
//                            List<Section> sections = route.getSections();
//                            for (Section section : sections) {
//                                logManeuverInstructions(section);
//                            }
//                        }else {
//                            Toast.makeText(context, "Could not calculate route.", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
//    }
//    private void logManeuverInstructions(Section section) {
//        Log.d(TAG, "Log maneuver instructions per route section:");
//        List<Maneuver> maneuverInstructions = section.getManeuvers();
//        for (Maneuver maneuverInstruction : maneuverInstructions) {
//            ManeuverAction maneuverAction = maneuverInstruction.getAction();
//            GeoCoordinates maneuverLocation = maneuverInstruction.getCoordinates();
//            String maneuverInfo = maneuverInstruction.getText()
//                    + ", Action: " + maneuverAction.name()
//                    + ", Location: " + maneuverLocation.toString();
//            Toast.makeText(getContext(), maneuverInfo, Toast.LENGTH_SHORT).show();
//        }
//    }
//    public void drawRoute() {
//        mapView.getMapScene().addMapPolyline(routePolyLine);
//        routeHasBeenDrawn = true;
//    }
//    public void fabMapSceneClick(){
//        if(mapScheme == MapScheme.NORMAL_DAY) mapScheme = MapScheme.SATELLITE;
//        else{ mapScheme = MapScheme.NORMAL_DAY;}
//        distanceInMeters = mapView.getCameraDistance();
//    }
//
//    public void setDistanceInMeters(double distanceInMeters) {
//        this.distanceInMeters = distanceInMeters;
//    }
//
//    public Route getRoute() {
//        return route;
//    }
//
//    public void fabLocationClick(){
//        distanceInMeters = 1000*0.1;
//
//    }
//    public Location convertLocation(android.location.Location nativeLocation) {
//
//        GeoCoordinates geoCoordinates = new GeoCoordinates(
//                nativeLocation.getLatitude(),
//                nativeLocation.getLongitude(),
//                nativeLocation.getAltitude());
//
//        Location location = new Location(geoCoordinates, new Date());
//        currentLocation = location;
//        if (nativeLocation.hasBearing()) {
//            location.bearingInDegrees = (double) nativeLocation.getBearing();
//        }
//
//        if (nativeLocation.hasSpeed()) {
//            location.speedInMetersPerSecond = (double) nativeLocation.getSpeed();
//        }
//
//        if (nativeLocation.hasAccuracy()) {
//            location.horizontalAccuracyInMeters = (double) nativeLocation.getAccuracy();
//        }
//
//        return location;
//    }
//}
