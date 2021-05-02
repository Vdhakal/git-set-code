package com.example.git_set_code.permissions;

import android.content.Context;
import com.example.git_set_code.locations.PlatformPositioningProvider;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.mapping.Location;

import java.util.Date;

public class GetGeocoder {
    Context context;

    public GetGeocoder(Context context) {
        this.context = context;
        PlatformPositioningProvider platformPositioningProvider = new PlatformPositioningProvider(context);

        platformPositioningProvider.startLocating(new PlatformPositioningProvider.PlatformLocationListener() {
            @Override
            public void onLocationUpdated(android.location.Location location) throws IllegalAccessException, InstantiationException {
                convertLocation(location);
            }
        });
    }

    private Location convertLocation(android.location.Location nativeLocation) throws InstantiationException, IllegalAccessException {
        GeoCoordinate geoCoordinates = new GeoCoordinate(
                nativeLocation.getLatitude(),
                nativeLocation.getLongitude(),
                nativeLocation.getAltitude());

        Location location = Location.class.newInstance();



        return location;
    }

}
