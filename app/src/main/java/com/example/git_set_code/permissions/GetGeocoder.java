package com.example.git_set_code.permissions;

import android.content.Context;
import com.example.git_set_code.locations.PlatformPositioningProvider;
import com.here.sdk.core.GeoCoordinates;
import com.here.sdk.core.Location;

import java.util.Date;

public class GetGeocoder {
    Context context;

    public GetGeocoder(Context context) {
        this.context = context;
        PlatformPositioningProvider platformPositioningProvider = new PlatformPositioningProvider(context);

        platformPositioningProvider.startLocating(new PlatformPositioningProvider.PlatformLocationListener() {
            @Override
            public void onLocationUpdated(android.location.Location location) {
                convertLocation(location);
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

}
