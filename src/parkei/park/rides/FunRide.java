package parkei.park.rides;

import java.util.ArrayList;

import parkei.amusers.Amuser;

import parkei.exceptions.CannotBoardException;
import parkei.exceptions.CannotOperateException;
import parkei.exceptions.LocationException;

import parkei.exceptions.NoRidersException;
import parkei.exceptions.OutOfOrderException;

import parkei.utilities.Effect;

public abstract class FunRide extends Ride {

	public FunRide() {
	}

	public FunRide(String name, int duration, int batchSize) {
		super(name, duration, batchSize);

	}

	public boolean board(Amuser amuser) throws CannotBoardException,
			OutOfOrderException {
		if (amuser.getLocation() != this) {
			throw new LocationException();
		}
		if (!(eligibleToRide(amuser))) {
			throw new CannotBoardException("You cannot take this ride");

		}

		return super.board(amuser);
	}

	public boolean start() throws CannotOperateException {

		if (inMaintenance()) {
			throw new OutOfOrderException(" Sorry");
		}
		if (getCurrentAmusers().size() == 0) {
			throw new NoRidersException("Sorry");
		}

		for (int i = 0; i < getCurrentAmusers().size(); i++) {
			getCurrentAmusers().get(i).getEffects().clear();
			for (int l = 0; l < affects(getCurrentAmusers().get(i)).size(); l++) {
				getCurrentAmusers().get(i).applyEffect(
						affects(getCurrentAmusers().get(i)).get(l));
			}
		}
		return super.start();

	}

	public abstract boolean eligibleToRide(Amuser amuser)
			throws CannotBoardException, OutOfOrderException;

	public abstract ArrayList<Effect> affects(Amuser amuser);

}
