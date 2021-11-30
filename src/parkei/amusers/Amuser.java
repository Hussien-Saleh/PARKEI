package parkei.amusers;

import java.util.ArrayList;

import parkei.exceptions.WrongTicketException;
import parkei.park.rides.FunRide;
import parkei.utilities.Effect;
import parkei.utilities.Ticket;

public abstract class Amuser implements Rider {
	private ArrayList<Effect> affects = new ArrayList<Effect>();

	private String name;
	private int age;
	private int height;
	private boolean riding;
	private FunRide location;

	public Amuser() {
	}

	public Amuser(String name, int age, int height) {
		this.name = name;
		this.age = age;
		this.height = height;

	}

	public Amuser(boolean riding, FunRide location) {
		this.riding = riding;
		this.location = location;
	}

	public final String getName() {
		return name;
	}

	public final void setName(String name) {
		this.name = name;
	}

	public final int getAge() {
		return age;
	}

	public final void setAge(int age) {
		this.age = age;
	}

	public final int getHeight() {
		return height;
	}

	public final void setHeight(int height) {
		this.height = height;
	}

	public final boolean isRiding() {
		return riding;
	}

	public final void setRiding(boolean riding) {
		this.riding = riding;
	}

	public final FunRide getNewLocation() {
		return location;
	}

	public final void setNewLocation(FunRide newLocation) {
		this.location = newLocation;
	}

	public final boolean move(FunRide newLocation) {
		if (location == newLocation) {
			return false;
		} else {
			this.location = newLocation;
			return true;
		}

	}

	public final FunRide getLocation() {
		return location;
	}

	public final void setLocation(FunRide location) {
		this.location = location;
	}

	public Ticket getTicket() {
		return null;

	}

	public abstract void buyTicket(Ticket ticket) throws WrongTicketException;

	public void applyEffect(Effect effect) {
		affects.add(effect);

	}

	public final ArrayList<Effect> getEffects() {
		return affects;
	}

	public final void setAffects(ArrayList<Effect> affects) {
		this.affects = affects;
	}
}
