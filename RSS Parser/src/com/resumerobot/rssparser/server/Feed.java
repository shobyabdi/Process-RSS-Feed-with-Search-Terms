package com.resumerobot.rssparser.server;
import java.util.Date;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Feed {
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Long id;

	@Persistent
    private String name;

    @Persistent
    private String siteurl;

    @Persistent
    private String feedurl;
    
    @Persistent
    private Date dateadded;
    
    public Feed(String name, String siteurl, String feedurl, Date dateadded) {
    	this.name = name;
    	this.siteurl = siteurl;
    	this.feedurl = feedurl;
    	this.dateadded = dateadded;
    }
    
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    } 

    public String getSiteURL() {
        return siteurl;
    } 
    
    public String getFeedURL() {
        return feedurl;
    } 
    
    public Date getDateAdded() {
        return dateadded;
    } 
}
