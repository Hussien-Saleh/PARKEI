package parkei.park.rides;

import java.util.ArrayList;

import parkei.exceptions.NoRouteException;

public class CircularRide extends TransportRide {
	private int count = 1;

	public CircularRide() {
		super();

	}

	public CircularRide(String name, int duration, int batchSize,
			ArrayList<FunRide> routeLocations) {
		super(name, duration, batchSize, routeLocations);

	}

	public CircularRide(FunRide currentLocation) {
		currentLocation = getRouteLocations().get(0);
	}

	public String getFullRoute() throws NoRouteException {
		if (getRouteLocations().isEmpty()) {
			throw new NoRouteException("Sorry");
		}
		String r = "The followed route is: ";
		for (int j = 0; j < getRouteLocations().size(); j++) {
			r = r + getRouteLocations().get(j).getName() + ", ";

		}
		r = r + getRouteLocations().get(0).getName();
		return r;
	}

	public final int getCount() {
		return count;
	}

	public final void setCount(int count) {
		this.count = count;
	}
}