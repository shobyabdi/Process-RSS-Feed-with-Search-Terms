package com.resumerobot.rssparser.client;

import java.util.List;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface ServerServiceAsync {
	void getMapLocations(AsyncCallback<List<GeoLocation>> callback);
	void getUserData(AsyncCallback<String> callback);
	void getRSSProcessServer(String rssinput, String searchinput, AsyncCallback<String> callback);
}
