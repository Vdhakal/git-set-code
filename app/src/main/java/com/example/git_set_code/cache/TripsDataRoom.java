package com.example.git_set_code.cache;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;

import com.example.git_set_code.typeConverters.StringTypeConverter;

import java.util.List;

@Entity(tableName = "trips_table")
public class TripsDataRoom {
    @PrimaryKey(autoGenerate = true)
    public long id;
    private int statusCode;
    private String datastatus;
    private String jsonstatus;
    private List<String> driverCode;
    private List<String> driverName;
    private List<Integer> truckId;
    private List<String> truckCode;
    private List<String> truckDesc;
    private List<Integer> trailerId;
    private List<String> trailerCode;
    private List<String> trailerDesc;
    private List<Integer> tripId;
    private List<String> tripName;
    private List<String> tripDate;
    private List<Integer> seqNum;
    private List<String> waypointTypeDescription;
    private List<Double> latitude;
    private List<Double> longitude;
    private List<String>  destinationCod;
    private  List<String>  destinationName;
    private  List<String>   siteContainerCode;
    private  List<String>   siteContainerDescription;
    private  List<String>   address1;
    private  List<String>   address2;
    private  List<String>   city;
    private  List<String>   stateAbbrev;
    private List<Integer>   postalCode;
    private  List<Integer>  delReqNum;
    private   List<Integer>  delReqLineNum;
    private  List<Integer>   productId;
    private  List<String>   productCode;
    private  List<String>   productDesc;
    private   List<Integer>  requestedQty;
    private   List<String>  uom;
    private  List<String>   fill;
    private int stops;

    public TripsDataRoom(int statusCode, String datastatus, String jsonstatus, List<String> driverCode, List<String> driverName, List<Integer> truckId, List<String> truckCode, List<String> truckDesc, List<Integer> trailerId, List<String> trailerCode, List<String> trailerDesc, List<Integer> tripId, List<String> tripName, List<String> tripDate, List<Integer> seqNum, List<String> waypointTypeDescription, List<Double> latitude, List<Double> longitude, List<String> destinationCod, List<String> destinationName, List<String> siteContainerCode, List<String> siteContainerDescription, List<String> address1, List<String> address2, List<String> city, List<String> stateAbbrev, List<Integer> postalCode, List<Integer> delReqNum, List<Integer> delReqLineNum, List<Integer> productId, List<String> productCode, List<String> productDesc, List<Integer> requestedQty, List<String> uom, List<String> fill, int stops) {
        this.statusCode = statusCode;
        this.datastatus = datastatus;
        this.jsonstatus = jsonstatus;
        this.driverCode = driverCode;
        this.driverName = driverName;
        this.truckId = truckId;
        this.truckCode = truckCode;
        this.truckDesc = truckDesc;
        this.trailerId = trailerId;
        this.trailerCode = trailerCode;
        this.trailerDesc = trailerDesc;
        this.tripId = tripId;
        this.tripName = tripName;
        this.tripDate = tripDate;
        this.seqNum = seqNum;
        this.waypointTypeDescription = waypointTypeDescription;
        this.latitude = latitude;
        this.longitude = longitude;
        this.destinationCod = destinationCod;
        this.destinationName = destinationName;
        this.siteContainerCode = siteContainerCode;
        this.siteContainerDescription = siteContainerDescription;
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.stateAbbrev = stateAbbrev;
        this.postalCode = postalCode;
        this.delReqNum = delReqNum;
        this.delReqLineNum = delReqLineNum;
        this.productId = productId;
        this.productCode = productCode;
        this.productDesc = productDesc;
        this.requestedQty = requestedQty;
        this.uom = uom;
        this.fill = fill;
        this.stops = stops;
    }

    public long getId() {
        return id;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getDatastatus() {
        return datastatus;
    }

    public String getJsonstatus() {
        return jsonstatus;
    }

    public List<String> getDriverCode() {
        return driverCode;
    }

    public List<String> getDriverName() {
        return driverName;
    }

    public List<Integer> getTruckId() {
        return truckId;
    }

    public List<String> getTruckCode() {
        return truckCode;
    }

    public List<String> getTruckDesc() {
        return truckDesc;
    }

    public List<Integer> getTrailerId() {
        return trailerId;
    }

    public List<String> getTrailerCode() {
        return trailerCode;
    }

    public List<String> getTrailerDesc() {
        return trailerDesc;
    }

    public List<Integer> getTripId() {
        return tripId;
    }

    public List<String> getTripName() {
        return tripName;
    }

    public List<String> getTripDate() {
        return tripDate;
    }

    public List<Integer> getSeqNum() {
        return seqNum;
    }

    public List<String> getWaypointTypeDescription() {
        return waypointTypeDescription;
    }

    public List<Double> getLatitude() {
        return latitude;
    }

    public List<Double> getLongitude() {
        return longitude;
    }

    public List<String> getDestinationCod() {
        return destinationCod;
    }

    public List<String> getDestinationName() {
        return destinationName;
    }

    public List<String> getSiteContainerCode() {
        return siteContainerCode;
    }

    public List<String> getSiteContainerDescription() {
        return siteContainerDescription;
    }

    public List<String> getAddress1() {
        return address1;
    }

    public List<String> getAddress2() {
        return address2;
    }

    public List<String> getCity() {
        return city;
    }

    public List<String> getStateAbbrev() {
        return stateAbbrev;
    }

    public List<Integer> getPostalCode() {
        return postalCode;
    }

    public List<Integer> getDelReqNum() {
        return delReqNum;
    }

    public List<Integer> getDelReqLineNum() {
        return delReqLineNum;
    }

    public List<Integer> getProductId() {
        return productId;
    }

    public List<String> getProductCode() {
        return productCode;
    }

    public List<String> getProductDesc() {
        return productDesc;
    }

    public List<Integer> getRequestedQty() {
        return requestedQty;
    }

    public List<String> getUom() {
        return uom;
    }

    public List<String> getFill() {
        return fill;
    }

    public int getStops() {
        return stops;
    }

    public void setId(long id) {
        this.id = id;
    }
}
