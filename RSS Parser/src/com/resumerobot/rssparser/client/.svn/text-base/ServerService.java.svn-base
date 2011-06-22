package com.resumerobot.rssparser.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import java.util.List;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("server")
public interface ServerService extends RemoteService {
	List<GeoLocation> getMapLocations();
	String getUserData();
	String getRSSProcessServer(String rssinput, String searchinput);
}
