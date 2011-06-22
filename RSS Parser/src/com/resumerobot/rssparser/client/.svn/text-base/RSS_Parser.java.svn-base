package com.resumerobot.rssparser.client;

import java.util.HashMap;
import java.util.List;
import com.resumerobot.rssparser.server.Location;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.maps.client.InfoWindowContent;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.control.LargeMapControl;
import com.google.gwt.maps.client.event.MarkerClickHandler;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.user.client.Window; 



/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class RSS_Parser implements EntryPoint {

	/**
	 * Create a remote service proxy to talk to the server-side service.
	 */
	private final ServerServiceAsync serverService = GWT
			.create(ServerService.class);

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		final MapWidget map;
		final VerticalPanel vpanel = new VerticalPanel();
		final HashMap<String, String> latLongToCity = new HashMap<String, String>();
		final String currentLocation;
		final Button processRSS = new Button("Parse RSS Feed");
		//final TextBox locationField = new TextBox();
		final TextBox searchTerm = new TextBox();
		final HTML serverResponseLabel = new HTML();
		final ListBox locationField = new ListBox();
		final HTML appEngineLogo = new HTML();
		final HTML welcomeUser = new HTML();
	    map = new MapWidget(LatLng.newInstance(41.85, -87.65), 3);
	    map.setSize("500px", "300px");
	    
	    // Add some controls for the zoom level
	    map.addControl(new LargeMapControl());
	    
	    // Add an info window to highlight a point of interest
	    // Add the map to the HTML host page
	    RootPanel.get("mapsTutorial").add(map);		
		locationField.setEnabled(false);
		searchTerm.setText("Java");
		serverResponseLabel.setHTML("<b>Results here</b>");
		appEngineLogo.setHTML("<img src=\"http://code.google.com/appengine/images/appengine-noborder-120x30.gif\" alt=\"Powered by Google App Engine\"/>");
		// We can add style names to widgets
		processRSS.addStyleName("sendButton");

		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element
		RootPanel.get("processRSSContainer").add(processRSS);
		RootPanel.get("locationFieldContainer").add(locationField);
		RootPanel.get("searchTermContainer").add(searchTerm);
		RootPanel.get("responseContainer").add(serverResponseLabel);
		RootPanel.get("appEngineLogoContainer").add(appEngineLogo);
		RootPanel.get("userContainer").add(welcomeUser);

		// Focus the cursor on the name field when the app loads
		searchTerm.setFocus(true);
		searchTerm.selectAll();
		

		// Create a handler for the sendButton and nameField
		class MyHandler implements ClickHandler {
			/**
			 * Fired when the user clicks on the sendButton.
			 */
			public void onClick(ClickEvent event) {
				callRSSFeedAndProcess();
			}

			private void callRSSFeedAndProcess() {
				serverResponseLabel.setText("Processing.......");
				String locationinput = locationField.getValue(locationField.getSelectedIndex());
				String searchinput = searchTerm.getText();
				serverService.getRSSProcessServer(locationinput, searchinput, new AsyncCallback<String>() {
					public void onFailure(Throwable caught) {
						serverResponseLabel.setHTML("Server Error:" + caught.toString());
					}

					public void onSuccess(String result) {
						serverResponseLabel.setHTML(result);
					}
				});
			}
		}
		
		class MyInfoHandler implements MarkerClickHandler{

			public void onClick(MarkerClickEvent event) {
				String currentLocation = latLongToCity.get(((Marker) event.getSource()).getLatLng().toString());
				for(int i=0; i<locationField.getItemCount(); i++) {
					if(locationField.getItemText(i).contains(currentLocation)) {
						locationField.setSelectedIndex(i);
					}
				}
			}
		}

		// Add a handler to send the name to the server
		MyHandler handler = new MyHandler();
		processRSS.addClickHandler(handler);
		final MyInfoHandler markerHandler = new MyInfoHandler();
		
		serverService.getUserData(new AsyncCallback<String>() {
			public void onFailure(Throwable caught) {
				welcomeUser.setHTML("User Error: Redirecting to Login Window");
				Window.open("/index.jsp", "_self", "");
			}

			public void onSuccess(String result) {
				welcomeUser.setHTML(result);
			}
		});
		
		serverService.getMapLocations(new AsyncCallback<List<GeoLocation>>() {
			public void onFailure(Throwable caught) {
			}

			public void onSuccess(List<GeoLocation> geolocations) {
				for(GeoLocation l : geolocations) {
					latLongToCity.put(l.getLocationInstance().toString(), l.getLocation());
					Marker newMarker = new Marker(l.getLocationInstance());
					map.addOverlay(newMarker);
					newMarker.addMarkerClickHandler(markerHandler);
					locationField.addItem(l.getLocation(), l.getURLHeader());
				}
			}			
		});
	}
}
