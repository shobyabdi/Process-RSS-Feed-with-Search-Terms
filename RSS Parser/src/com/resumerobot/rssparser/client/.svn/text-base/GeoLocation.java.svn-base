package com.resumerobot.rssparser.client;

import java.io.Serializable;
import java.util.Date;
import com.google.gwt.maps.client.geom.LatLng;

public class GeoLocation implements Serializable {
    private String locationname;

    private String urlheader;

    private Double longitude;
    
    private Double latitude;

    public String getLocation() {
        return locationname;
    }
    
    public void setLocation(String locationname) {
    	this.locationname = locationname;
    }

    public String getURLHeader() {
        return urlheader;
    }
    
    public void setURLHeader(String urlheader) {
    	this.urlheader = urlheader;
    }
    
    public Double getLongitude() {
        return longitude;
    }
    
    public void setLongitude(Double longitude) {
    	this.longitude = longitude;
    }
    
    public Double getLatitude() {
        return latitude;
    }
    
    public void setLatitude(Double latitude) {
    	this.latitude = latitude;
    }
    
    public LatLng getLocationInstance()
    {
    	return LatLng.newInstance(latitude, longitude);
    }
}
