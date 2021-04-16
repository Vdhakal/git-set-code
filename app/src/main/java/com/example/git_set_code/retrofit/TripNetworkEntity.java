package com.example.git_set_code.retrofit;
import com.google.gson.annotations.Expose;
import  com.google.gson.annotations.SerializedName;

public class TripNetworkEntity {
    public long id;
    @SerializedName("status")
    private String jsonstatus;
    @SerializedName("StatusCode")
    private int statusCode;
    @SerializedName("Status")
    private String datastatus;
    @SerializedName("DriverCode")
    private String driverCode;
    @SerializedName("DriverName")
    private String driverName;
    @SerializedName("TruckId")
    private int truckId;
    @SerializedName("TruckCode")
    private String truckCode;
    @SerializedName("TruckDesc")
    private String truckDesc;
    @SerializedName("TrailerId")
    private int trailerId;
    @SerializedName("TrailerCode")
    private String trailerCode;
    @SerializedName("TrailerDesc")
    private String trailerDesc;
    @SerializedName("TripId")
    private int tripId;
    @SerializedName("TripName")
    private String tripName;
    @SerializedName("TripDate")
    private String tripDate;
    @SerializedName("SeqNum")
    private int seqNum;
    @SerializedName("WaypointTypeDescription")
    private String waypointTypeDescription;
    @SerializedName("Latitude")
    private float latitude;
    @SerializedName("Longitude")
    private float longitude;
    @SerializedName("DestinationCode")
    private String  destinationCod;
    @SerializedName("DestinationName")
    private  String  destinationName;
    @SerializedName("SiteContainerCode")
    private  String   siteContainerCode;
    @SerializedName("SiteContainerDescription")
    private  String   siteContainerDescription;
    @SerializedName("Address1")
    private  String   address1;
    @SerializedName("Address2")
    private  String   address2;
    @SerializedName("City")
    private  String   city;
    @SerializedName("StateAbbrev")
    private  String   stateAbbrev;
    @SerializedName("PostalCode")
    private int   postalCode;
    @SerializedName("DelReqNum")
    private  int  delReqNum;
    @SerializedName("DelReqLineNum")
    private   int  delReqLineNum;
    @SerializedName("ProductId")
    private  int   productId;
    @SerializedName("ProductCode")
    private  String   productCode;

    public TripNetworkEntity(int statusCode, String datastatus, String jsonstatus, String driverCode, String driverName, int truckId, String truckCode, String truckDesc, int trailerId, String trailerCode, String trailerDesc, int tripId, String tripName, String tripDate, int seqNum, String waypointTypeDescription, float latitude, float longitude, String destinationCod, String destinationName, String siteContainerCode, String siteContainerDescription, String address1, String address2, String city, String stateAbbrev, int postalCode, int delReqNum, int delReqLineNum, int productId, String productCode, String productDesc, int requestedQty, String uom, String fill, int stops) {
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

    public void setId(long id) {
        this.id = id;
    }

    public void setJsonstatus(String jsonstatus) {
        this.jsonstatus = jsonstatus;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public void setDatastatus(String datastatus) {
        this.datastatus = datastatus;
    }

    public void setDriverCode(String driverCode) {
        this.driverCode = driverCode;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public void setTruckId(int truckId) {
        this.truckId = truckId;
    }

    public void setTruckCode(String truckCode) {
        this.truckCode = truckCode;
    }

    public void setTruckDesc(String truckDesc) {
        this.truckDesc = truckDesc;
    }

    public void setTrailerId(int trailerId) {
        this.trailerId = trailerId;
    }

    public void setTrailerCode(String trailerCode) {
        this.trailerCode = trailerCode;
    }

    public void setTrailerDesc(String trailerDesc) {
        this.trailerDesc = trailerDesc;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    public void setTripName(String tripName) {
        this.tripName = tripName;
    }

    public void setTripDate(String tripDate) {
        this.tripDate = tripDate;
    }

    public void setSeqNum(int seqNum) {
        this.seqNum = seqNum;
    }

    public void setWaypointTypeDescription(String waypointTypeDescription) {
        this.waypointTypeDescription = waypointTypeDescription;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public void setDestinationCod(String destinationCod) {
        this.destinationCod = destinationCod;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    public void setSiteContainerCode(String siteContainerCode) {
        this.siteContainerCode = siteContainerCode;
    }

    public void setSiteContainerDescription(String siteContainerDescription) {
        this.siteContainerDescription = siteContainerDescription;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStateAbbrev(String stateAbbrev) {
        this.stateAbbrev = stateAbbrev;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public void setDelReqNum(int delReqNum) {
        this.delReqNum = delReqNum;
    }

    public void setDelReqLineNum(int delReqLineNum) {
        this.delReqLineNum = delReqLineNum;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public void setRequestedQty(int requestedQty) {
        this.requestedQty = requestedQty;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public void setFill(String fill) {
        this.fill = fill;
    }

    public void setStops(int stops) {
        this.stops = stops;
    }

    public long getId() {
        return id;
    }

    public String getJsonstatus() {
        return jsonstatus;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getDatastatus() {
        return datastatus;
    }

    public String getDriverCode() {
        return driverCode;
    }

    public String getDriverName() {
        return driverName;
    }

    public int getTruckId() {
        return truckId;
    }

    public String getTruckCode() {
        return truckCode;
    }

    public String getTruckDesc() {
        return truckDesc;
    }

    public int getTrailerId() {
        return trailerId;
    }

    public String getTrailerCode() {
        return trailerCode;
    }

    public String getTrailerDesc() {
        return trailerDesc;
    }

    public int getTripId() {
        return tripId;
    }

    public String getTripName() {
        return tripName;
    }

    public String getTripDate() {
        return tripDate;
    }

    public int getSeqNum() {
        return seqNum;
    }

    public String getWaypointTypeDescription() {
        return waypointTypeDescription;
    }

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public String getDestinationCod() {
        return destinationCod;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public String getSiteContainerCode() {
        return siteContainerCode;
    }

    public String getSiteContainerDescription() {
        return siteContainerDescription;
    }

    public String getAddress1() {
        return address1;
    }

    public String getAddress2() {
        return address2;
    }

    public String getCity() {
        return city;
    }

    public String getStateAbbrev() {
        return stateAbbrev;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public int getDelReqNum() {
        return delReqNum;
    }

    public int getDelReqLineNum() {
        return delReqLineNum;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductCode() {
        return productCode;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public int getRequestedQty() {
        return requestedQty;
    }

    public String getUom() {
        return uom;
    }

    public String getFill() {
        return fill;
    }

    public int getStops() {
        return stops;
    }

    @SerializedName("ProductDesc")
    private  String   productDesc;
    @SerializedName("RequestedQty")
    private   int  requestedQty;
    @SerializedName("UOM")
    private   String  uom;
    @SerializedName("Fill")
    private  String   fill;
    private int stops;


}
