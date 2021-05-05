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
 *
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
     * @return
     */
    public int getLol() {
        if (lol == -1)
            lol = lol + 1;
        return lol;
    }

    /**
     * @return
     */
    public int getTripTracker() {
        if (tripTracker == -1)
            setTripTracker();
        return tripTracker;
    }

    /**
     *
     */
    private void setTripTracker() {
        tripTracker++;
    }

    /**
     *
     */
    public ViewModelMap() {

    }

    /**
     * @param sourceLatitudes
     */
    public void setSourceLatitudes(List<Double> sourceLatitudes) {
        this.sourceLatitudes = sourceLatitudes;
    }

    /**
     * @param sourceLongitudes
     */
    public void setSourceLongitudes(List<Double> sourceLongitudes) {
        this.sourceLongitudes = sourceLongitudes;
    }

    /**
     * @param siteLatitudes
     */
    public void setSiteLatitudes(List<Double> siteLatitudes) {
        this.siteLatitudes = siteLatitudes;
    }

    /**
     * @param siteLongitudes
     */
    public void setSiteLongitudes(List<Double> siteLongitudes) {
        this.siteLongitudes = siteLongitudes;
    }

    /**
     * @return
     */
    public List<GeoCoordinate> getSourceGeoCoordinates() {
        if (sourceGeoCoordinates == null) {
            Log.i("TAG", "new geo created.");
            setSourceGeoCoordinates();
        }
        return sourceGeoCoordinates;
    }

    /**
     *
     */
    private void setSourceGeoCoordinates() {
        sourceGeoCoordinates = new ArrayList<GeoCoordinate>();
        for (int i = 0; i < sourceLatitudes.size(); i++) {
            sourceGeoCoordinates.add(new GeoCoordinate(sourceLatitudes.get(i), sourceLongitudes.get(i)));
        }
    }

    /**
     * @return
     */
    public List<GeoCoordinate> getSiteGeoCoordinates() {
        if (siteGeoCoordinates == null)
            setSiteGeoCoordinates();
        return siteGeoCoordinates;
    }

    /**
     *
     */
    private void setSiteGeoCoordinates() {
        siteGeoCoordinates = new ArrayList<GeoCoordinate>();
        for (int i = 0; i < siteLongitudes.size(); i++) {
            Log.i("RVM", "" + siteLongitudes.size());
            siteGeoCoordinates.add(new GeoCoordinate(siteLatitudes.get(i), siteLongitudes.get(i)));
        }
    }

    /**
     * @return
     */
    public NavigationManager getM_navigationManager() {
        if (m_navigationManager == null)
            setM_navigationManager();
        return m_navigationManager;
    }

    /**
     *
     */
    private void setM_navigationManager() {
        m_navigationManager = NavigationManager.getInstance();
    }

    /**
     * @return
     */
    public GeoPosition getGeoPosition() {
        return geoPosition;
    }

    /**
     * @param geoPosition
     */
    public void setGeoPosition(GeoPosition geoPosition) {
        this.geoPosition = geoPosition;
    }

    /**
     *
     */
    public void setMap() {
        map = mapFragment.getMap();
    }

    /**
     * @return
     */
    public Map getMap() {
        if (map == null)
            setMap();
        return map;
    }

    /**
     * @param mapFragment
     * @return
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
     * @param mapFragment
     */
    private void setMapFragment(AndroidXMapFragment mapFragment) {
        this.mapFragment = mapFragment;
    }
}
