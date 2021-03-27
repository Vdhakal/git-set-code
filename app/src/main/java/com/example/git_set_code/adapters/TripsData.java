package com.example.git_set_code.adapters;

import androidx.annotation.NonNull;

public class TripsData {
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

    public TripsData(){}

    public TripsData(int statusCode, String datastatus, String jsonstatus, String driverCode, String driverName, int truckId, String truckCode, String truckDesc, int trailerId, String trailerCode, String trailerDesc, int tripId, String tripName, String tripDate, int seqNum, String waypointTypeDescription, float latitude, float longitude, String destinationCod, String destinationName, String siteContainerCode, String siteContainerDescription, String address1, String address2, String city, String stateAbbrev, int postalCode, int delReqNum, int delReqLineNum, int productId, String productCode, String productDesc, int requestedQty, String uom, String fill) {
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
    }
    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getDatastatus() {
        return datastatus;
    }

    public void setDatastatus(String datastatus) {
        this.datastatus = datastatus;
    }

    public String getJsonstatus() {
        return jsonstatus;
    }

    public void setJsonstatus(String jsonstatus) {
        this.jsonstatus = jsonstatus;
    }

    public String getDriverCode() {
        return driverCode;
    }

    public void setDriverCode(String driverCode) {
        this.driverCode = driverCode;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public int getTruckId() {
        return truckId;
    }

    public void setTruckId(int truckId) {
        this.truckId = truckId;
    }

    public String getTruckCode() {
        return truckCode;
    }

    public void setTruckCode(String truckCode) {
        this.truckCode = truckCode;
    }

    public String getTruckDesc() {
        return truckDesc;
    }

    public void setTruckDesc(String truckDesc) {
        this.truckDesc = truckDesc;
    }

    public int getTrailerId() {
        return trailerId;
    }

    public void setTrailerId(int trailerId) {
        this.trailerId = trailerId;
    }

    public String getTrailerCode() {
        return trailerCode;
    }

    public void setTrailerCode(String trailerCode) {
        this.trailerCode = trailerCode;
    }

    public String getTrailerDesc() {
        return trailerDesc;
    }

    public void setTrailerDesc(String trailerDesc) {
        this.trailerDesc = trailerDesc;
    }

    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    public String getTripName() {
        return tripName;
    }

    public void setTripName(String tripName) {
        this.tripName = tripName;
    }

    public String getTripDate() {
        return tripDate;
    }

    public void setTripDate(String tripDate) {
        this.tripDate = tripDate;
    }

    public int getSeqNum() {
        return seqNum;
    }

    public void setSeqNum(int seqNum) {
        this.seqNum = seqNum;
    }

    public String getWaypointTypeDescription() {
        return waypointTypeDescription;
    }

    public void setWaypointTypeDescription(String waypointTypeDescription) {
        this.waypointTypeDescription = waypointTypeDescription;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public String getDestinationCod() {
        return destinationCod;
    }

    public void setDestinationCod(String destinationCod) {
        this.destinationCod = destinationCod;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    public String getSiteContainerCode() {
        return siteContainerCode;
    }

    public void setSiteContainerCode(String siteContainerCode) {
        this.siteContainerCode = siteContainerCode;
    }

    public String getSiteContainerDescription() {
        return siteContainerDescription;
    }

    public void setSiteContainerDescription(String siteContainerDescription) {
        this.siteContainerDescription = siteContainerDescription;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStateAbbrev() {
        return stateAbbrev;
    }

    public void setStateAbbrev(String stateAbbrev) {
        this.stateAbbrev = stateAbbrev;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public int getDelReqNum() {
        return delReqNum;
    }

    public void setDelReqNum(int delReqNum) {
        this.delReqNum = delReqNum;
    }

    public int getDelReqLineNum() {
        return delReqLineNum;
    }

    public void setDelReqLineNum(int delReqLineNum) {
        this.delReqLineNum = delReqLineNum;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public int getRequestedQty() {
        return requestedQty;
    }

    public void setRequestedQty(int requestedQty) { this.requestedQty = requestedQty; }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public String getFill() {
        return fill;
    }

    public void setFill(String fill) {
        this.fill = fill;
    }


}
