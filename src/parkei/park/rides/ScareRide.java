package parkei.park.rides;

import java.util.ArrayList;

import parkei.amusers.Amuser;

import parkei.exceptions.UnsuitableAgeCategoryException;

import parkei.utilities.Effect;
import parkei.utilities.Ticket;
import parkei.amusers.*;

public class ScareRide extends FunRide {

	public ScareRide() {
		super();
	}

	public ScareRide(String name, int duration, int batchSize) {
		super(name, duration, batchSize);
	}

	public boolean eligibleToRide(Amuser amuser)
			throws UnsuitableAgeCategoryException {
		if (amuser instanceof Baby || amuser instanceof Kid
				|| !(amuser.getAge() > 14 && amuser.getAge() < 60)) {
			throw new UnsuitableAgeCategoryException("Sorry");
		}
		return true;

	}

	public ArrayList<Effect> affects(Amuser amuser) {

		ArrayList<Effect> affects = new ArrayList<Effect>();
		if (amuser.getTicket() == Ticket.Maxi) {
			affects.add(Effect.Scared);
		}
		return affects;
	}
}
