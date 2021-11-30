package parkei.park.rides;

import java.util.ArrayList;

import parkei.amusers.Amuser;
import parkei.exceptions.NoRouteException;

public class BiDirectionalRide extends TransportRide {

	private int route = 1;
	private FunRide currentLocation;

	public FunRide getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocation(FunRide currentLocation) {
		this.currentLocation = currentLocation;
	}

	public BiDirectionalRide() {
		super();
	}

	public BiDirectionalRide(String name, int duration, int batchSize,
			ArrayList<FunRide> routeLocations) {
		super(name, duration, batchSize, routeLocations);
		if (getRouteLocations() != null && getRouteLocations().size() != 0) {
			currentLocation = getRouteLocations().get(0);
		} else {
			currentLocation = null;
		}
	}

	public void move() throws NoRouteException {
		if (getRouteLocations().isEmpty()) {
			throw new NoRouteException("Sorry");
		}
		int k;
		for (k = 0; k < getRouteLocations().size(); k++) {
			if (currentLocation.getName().equals(
					getRouteLocations().get(k).getName())) {
				break;
			}
		}
		if (route == 1) {
			if (k == getRouteLocations().size() - 1) {
				k--;
				route = 0;
			} else {
				k++;
			}
		} else if (k == 0) {
			k++;
			route = 1;
		} else {
			k--;
		}
		currentLocation = getRouteLocations().get(k);
		for (Amuser amuser : getCurrentAmusers()) {
			amuser.move(currentLocation);
		}

	}

	public String getFullRoute() throws NoRouteException {

		if (getRouteLocations().isEmpty()) {
			throw new NoRouteException("Sorry");
		}
		String s = "The followed route is: ";
		int k;
		for (k = 0; k < getRouteLocations().size(); k++) {
			s = s + getRouteLocations().get(k).getName() + ", ";
		}
		for (k = k - 2; k > 0; k--) {
			s = s + getRouteLocations().get(k).getName() + ", ";
		}

		s = s + getRouteLocations().get(0).getName();

		return s;

	}

}
