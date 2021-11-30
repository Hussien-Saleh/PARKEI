package parkei.park.rides;

import java.util.ArrayList;

import parkei.amusers.Amuser;

import parkei.exceptions.CannotOperateException;

import parkei.exceptions.NoRouteException;

public abstract class TransportRide extends Ride {

	private ArrayList<FunRide> routeLocations;
	private FunRide currentLocation;

	public TransportRide() {
	}

	public TransportRide(String name, int duration, int batchSize,
			ArrayList<FunRide> routeLocations) {
		super(name, duration, batchSize);
		this.routeLocations = routeLocations;
		if (routeLocations != null && !routeLocations.isEmpty()) {
			this.currentLocation = routeLocations
					.get(routeLocations.size() - 1);
		}
	}

	public ArrayList<FunRide> getRouteLocations() {
		return routeLocations;
	}

	public final void setRouteLocations(ArrayList<FunRide> routeLocations) {
		this.routeLocations = routeLocations;
	}

	public FunRide getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocation(FunRide currentLocation) {
		this.currentLocation = currentLocation;
	}

	public void move() throws NoRouteException {

		if (routeLocations.indexOf(currentLocation) >= routeLocations.size() - 1) {
			this.currentLocation = routeLocations.get(0);
		} else {
			this.currentLocation = routeLocations.get(routeLocations
					.indexOf(currentLocation) + 1);
		}

		for (Amuser amuser : getCurrentAmusers()) {
			amuser.move(currentLocation);
		}
	}

	public boolean start() throws CannotOperateException, NoRouteException {
		if (getRouteLocations().isEmpty()) {
			throw new NoRouteException();
		}
		if (super.start()) {
			move();

			return true;
		}
		return false;

	}

	public abstract String getFullRoute() throws NoRouteException;

}
