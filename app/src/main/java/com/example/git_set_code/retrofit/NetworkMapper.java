package com.example.git_set_code.retrofit;

import com.example.git_set_code.networkUtil.EntityMapper;
import com.example.git_set_code.viewmodels.TripsData;

import java.util.List;

import javax.inject.Inject;

public class NetworkMapper implements EntityMapper<TripNetworkEntity, TripsData> {
    private int statusCode;
    private String datastatus;
    private String jsonstatus;
    private String driverCode;
    private String driverName;
    private int truckId;
    private String truckCode;
    private String truckDesc;
    private int trailerId;
    private String trailerCode;
    private String trailerDesc;
    private int tripId;
    private String tripName;
    private String tripDate;
    private int seqNum;
    private String waypointTypeDescription;
    private float latitude;
    private float longitude;
    private String  destinationCod;
    private  String  destinationName;
    private  String   siteContainerCode;
    private  String   siteContainerDescription;
    private  String   address1;
    private  String   address2;
    private  String   city;
    private  String   stateAbbrev;
    private int   postalCode;
    private  int  delReqNum;
    private   int  delReqLineNum;
    private  int   productId;
    private  String   productCode;
    private  String   productDesc;
    private   int  requestedQty;
    private   String  uom;
    private  String   fill;
    private int stops;

    @Inject
    public NetworkMapper() {}

    @Override
    public TripsData mapFromEntity(TripNetworkEntity tripNetworkEntity) {
        return new TripsData(
                statusCode = tripNetworkEntity.getStatusCode(),
                datastatus = tripNetworkEntity.getDatastatus(),
                jsonstatus = tripNetworkEntity.getJsonstatus(),
                driverCode = tripNetworkEntity.getDriverCode(),
                driverName = tripNetworkEntity.getDriverName(),
                truckId = tripNetworkEntity.getTruckId(),
                truckCode = tripNetworkEntity.getTruckCode(),
                truckDesc = tripNetworkEntity.getTruckDesc(),
                trailerId = tripNetworkEntity.getTrailerId(),
                trailerCode = tripNetworkEntity.getTrailerCode(),
                trailerDesc = tripNetworkEntity.getTrailerDesc(),
                tripId = tripNetworkEntity.getTripId(),
                tripName = tripNetworkEntity.getTripName(),
                tripDate = tripNetworkEntity.getTripDate(),
                seqNum = tripNetworkEntity.getSeqNum(),
                waypointTypeDescription = tripNetworkEntity.getWaypointTypeDescription(),
                latitude = tripNetworkEntity.getLatitude(),
                longitude = tripNetworkEntity.getLongitude(),
                destinationCod = tripNetworkEntity.getDestinationCod(),
                destinationName = tripNetworkEntity.getDestinationName(),
                siteContainerCode = tripNetworkEntity.getSiteContainerCode(),
                siteContainerDescription = tripNetworkEntity.getSiteContainerDescription(),
                address1 = tripNetworkEntity.getAddress1(),
                address2 = tripNetworkEntity.getAddress2(),
                city = tripNetworkEntity.getCity(),
                stateAbbrev = tripNetworkEntity.getStateAbbrev(),
                postalCode = tripNetworkEntity.getPostalCode(),
                delReqNum = tripNetworkEntity.getDelReqNum(),
                delReqLineNum = tripNetworkEntity.getDelReqLineNum(),
                productId = tripNetworkEntity.getProductId(),
                productCode = tripNetworkEntity.getProductCode(),
                productDesc = tripNetworkEntity.getProductDesc(),
                requestedQty = tripNetworkEntity.getRequestedQty(),
                uom = tripNetworkEntity.getUom(),
                fill = tripNetworkEntity.getFill(),
                stops = tripNetworkEntity.getStops()
        );
    }

    @Override
    public TripNetworkEntity mapToEntity(TripsData tripsData) {
        return new TripNetworkEntity(
                statusCode = tripsData.getStatusCode(),
                datastatus = tripsData.getDatastatus(),
                jsonstatus = tripsData.getJsonstatus(),
                driverCode = tripsData.getDriverCode(),
                driverName = tripsData.getDriverName(),
                truckId = tripsData.getTruckId(),
                truckCode = tripsData.getTruckCode(),
                truckDesc = tripsData.getTruckDesc(),
                trailerId = tripsData.getTrailerId(),
                trailerCode = tripsData.getTrailerCode(),
                trailerDesc = tripsData.getTrailerDesc(),
                tripId = tripsData.getTripId(),
                tripName = tripsData.getTripName(),
                tripDate = tripsData.getTripDate(),
                seqNum = tripsData.getSeqNum(),
                waypointTypeDescription = tripsData.getWaypointTypeDescription(),
                latitude = tripsData.getLatitude(),
                longitude = tripsData.getLongitude(),
                destinationCod = tripsData.getDestinationCod(),
                destinationName = tripsData.getDestinationName(),
                siteContainerCode = tripsData.getSiteContainerCode(),
                siteContainerDescription = tripsData.getSiteContainerDescription(),
                address1 = tripsData.getAddress1(),
                address2 = tripsData.getAddress2(),
                city = tripsData.getCity(),
                stateAbbrev = tripsData.getStateAbbrev(),
                postalCode = tripsData.getPostalCode(),
                delReqNum = tripsData.getDelReqNum(),
                delReqLineNum = tripsData.getDelReqLineNum(),
                productId = tripsData.getProductId(),
                productCode = tripsData.getProductCode(),
                productDesc = tripsData.getProductDesc(),
                requestedQty = tripsData.getRequestedQty(),
                uom = tripsData.getUom(),
                fill = tripsData.getFill(),
                stops = tripsData.getStops()
        );
    }
}
