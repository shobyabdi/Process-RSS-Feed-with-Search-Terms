package com.resumerobot.rssparser.server;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import com.google.appengine.api.users.User;
import com.google.appengine.api.datastore.GeoPt;
import com.google.appengine.api.datastore.Blob;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class UserRecord {
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Long id;
	
	@Persistent
	private User userRecord;
	
	@Persistent
	private String terms;
	
	@Persistent
	private Location location;
	
	public Long getId() {
		return this.id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public User getUser() {
		return this.userRecord;
	}
	
	public void setUser(User userRecord) {
		this.userRecord = userRecord;
	}
	
	public String getTerms() {
		return this.terms;
	}
	
	public void setTerms(String terms) {
		this.terms = terms;
	}
	
	public Location getLocation() {
		return this.location;
	}
	
	public void Location(Location location) {
		this.location = location;
	}
}
