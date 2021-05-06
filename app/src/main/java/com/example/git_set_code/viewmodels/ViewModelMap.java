package com.example.git_set_code.viewmodels;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.git_set_code.R;
import com.example.git_set_code.fragments.MapsFragment;
import com.example.git_set_code.trip_database.Table.SourceInformation;
import com.example.git_set_code.trip_database.ViewModel.TripViewModel;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.GeoPosition;
import com.here.android.mpa.common.PositioningManager;
import com.here.android.mpa.guidance.NavigationManager;
import com.here.android.mpa.mapping.AndroidXMapFragment;
import com.here.android.mpa.mapping.Map;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for modeling the map according to the geolocations
 */
public class ViewModelMap extends ViewModel {
    private List<Double> sourceLatitudes;
    private List<Double> sourceLongitudes;
    private List<Double> siteLatitudes;
    private List<Double> siteLongitudes;
    private List<GeoCoordinate> sourceGeoCoordinates;
    private List<GeoCoordinate> siteGeoCoordinates;
    private NavigationManager m_navigationManager;
    private GeoPosition geoPosition;
    private Map map = null;
    private AndroidXMapFragment mapFragment = null;
    private int tripTracker = -1;
    private int lol = -1;

    /**
     * @return an int representing the lol
     */
    public int getLol() {
        if (lol == -1)
            lol = lol + 1;
        return lol;
    }

    /**
     * @return an int representing the tripTracker
     */
    public int getTripTracker() {
        if (tripTracker == -1)
            setTripTracker();
        return tripTracker;
    }

    /**
     * Setter for tripTracker
     */
    private void setTripTracker() {
        tripTracker++;
    }

    /**
     *Constructor for ViewModelMap
     */
    public ViewModelMap() {

    }

    /**
     * Setter for sourceLatitudes
     * @param sourceLatitudes, a list of Double
     */
    public void setSourceLatitudes(List<Double> sourceLatitudes) {
        this.sourceLatitudes = sourceLatitudes;
    }

    /**
     * Setter for sourceLongitudes
     * @param sourceLongitudes, a list of Double
     */
    public void setSourceLongitudes(List<Double> sourceLongitudes) {
        this.sourceLongitudes = sourceLongitudes;
    }

    /**
     * Setter for siteLatitudes
     * @param siteLatitudes, a list of Double
     */
    public void setSiteLatitudes(List<Double> siteLatitudes) {
        this.siteLatitudes = siteLatitudes;
    }

    /**
     * Setter for siteLongitudes
     * @param siteLongitudes, a list of Double
     */
    public void setSiteLongitudes(List<Double> siteLongitudes) {
        this.siteLongitudes = siteLongitudes;
    }

    /**
     * Getter for sourceGeoCoordinates
     * @return sourceGeoCoordinates
     */
    public List<GeoCoordinate> getSourceGeoCoordinates() {
        if (sourceGeoCoordinates == null) {
            Log.i("TAG", "new geo created.");
            setSourceGeoCoordinates();
        }
        return sourceGeoCoordinates;
    }

    /**
     * Setter for sourceGeoCoordinates
     */
    private void setSourceGeoCoordinates() {
        sourceGeoCoordinates = new ArrayList<GeoCoordinate>();
        for (int i = 0; i < sourceLatitudes.size(); i++) {
            sourceGeoCoordinates.add(new GeoCoordinate(sourceLatitudes.get(i), sourceLongitudes.get(i)));
        }
    }

    /**
     * Getter for siteGeoCoordinates
     * @return siteGeoCoordinates, a list of GeoCoordinates
     */
    public List<GeoCoordinate> getSiteGeoCoordinates() {
        if (siteGeoCoordinates == null)
            setSiteGeoCoordinates();
        return siteGeoCoordinates;
    }

    /**
     * Setter for siteGeoCoordinates
     */
    private void setSiteGeoCoordinates() {
        siteGeoCoordinates = new ArrayList<GeoCoordinate>();
        for (int i = 0; i < siteLongitudes.size(); i++) {
            Log.i("RVM", "" + siteLongitudes.size());
            siteGeoCoordinates.add(new GeoCoordinate(siteLatitudes.get(i), siteLongitudes.get(i)));
        }
    }

    /**
     * Getter for m_navigationManager
     * @return m_navigationManager, a NavigationManager object
     */
    public NavigationManager getM_navigationManager() {
        if (m_navigationManager == null)
            setM_navigationManager();
        return m_navigationManager;
    }

    /**
     * Setter for m_navigationManager
     */
    private void setM_navigationManager() {
        m_navigationManager = NavigationManager.getInstance();
    }

    /**
     * Getter for geoPosition
     * @return geoPosition, a GeoPosition object
     */
    public GeoPosition getGeoPosition() {
        return geoPosition;
    }

    /**
     * Setter for geoPosition
     * @param geoPosition, a GeoPosition object
     */
    public void setGeoPosition(GeoPosition geoPosition) {
        this.geoPosition = geoPosition;
    }

    /**
     * Setter for map
     */
    public void setMap() {
        map = mapFragment.getMap();
    }

    /**
     * Getter for map
     * @return a Map object
     */
    public Map getMap() {
        if (map == null)
            setMap();
        return map;
    }

    /**
     * Getter for mapFragment
     * @param mapFragment, a AndroidXMapFragment object
     * @return a AndroidXMapFragment object
     */
    public AndroidXMapFragment getMapFragment(AndroidXMapFragment mapFragment) {
        if (this.mapFragment == null) {
            Log.i("VMP", "MAP IS NULL ");
            setMapFragment(mapFragment);
        } else
            Log.i("VMP", "mapIsNotNull " + mapFragment.toString());
        return mapFragment;
    }

    /**
     * Setter for the mapFragment
     * @param mapFragment, a AndroidXMapFragment object
     */
    private void setMapFragment(AndroidXMapFragment mapFragment) {
        this.mapFragment = mapFragment;
    }
}
