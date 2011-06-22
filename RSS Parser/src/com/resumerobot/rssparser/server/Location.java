package com.resumerobot.rssparser.server;

import java.util.Date;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import com.google.gwt.maps.client.geom.LatLng;
import com.resumerobot.rssparser.client.GeoLocation;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Location {
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Long id;

	@Persistent
    private String locationname;

    @Persistent
    private String urlheader;

    @Persistent
    private Double longitude;
    
    @Persistent
    private Double latitude;
    
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
    	this.id = id;
    }

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
    
    public GeoLocation getGeoLocationInstance() {
		GeoLocation geolocale = new GeoLocation();
		geolocale.setLocation(this.locationname);
		geolocale.setURLHeader(this.urlheader);
		geolocale.setLatitude(this.latitude);
		geolocale.setLongitude(this.longitude);
		return geolocale;
    }
}
