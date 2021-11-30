package parkei.park.rides;

import java.util.ArrayList;

import parkei.amusers.Amuser;
import parkei.exceptions.AlreadyBoardingException;
import parkei.exceptions.CannotBoardException;
import parkei.exceptions.CannotOperateException;
import parkei.exceptions.CannotUnboardException;
import parkei.exceptions.FullRideException;

import parkei.exceptions.OutOfOrderException;
import parkei.utilities.Ticket;

public abstract class Ride {

	private String name;
	private int duration;
	private int batchSize;
	private ArrayList<Amuser> currentAmusers = new ArrayList<Amuser>();
	private int ridesToMaintain = 10;

	public Ride() {
	}

	public Ride(String name, int duration, int batchSize) {
		this.name = name;
		this.duration = duration;
		this.batchSize = batchSize;
		currentAmusers = new ArrayList<Amuser>(batchSize);

	}

	public final ArrayList<Amuser> getCurrentAmusers() {
		return currentAmusers;
	}

	public final void setCurrentAmusers(ArrayList<Amuser> currentAmusers) {
		this.currentAmusers = currentAmusers;
	}

	public final int getRidesToMaintain() {
		return ridesToMaintain;
	}

	public final void setRidesToMaintain(int ridesToMaintain) {
		this.ridesToMaintain = ridesToMaintain;
	}

	public final String getName() {
		return name;
	}

	public final void setName(String name) {
		this.name = name;
	}

	public final int getDuration() {
		return duration;
	}

	public final void setDuration(int duration) {
		this.duration = duration;
	}

	public final int getBatchSize() {
		return batchSize;
	}

	public final void setBatchSize(int batchSize) {
		this.batchSize = batchSize;
	}

	public boolean board(Amuser amuser) throws CannotBoardException,
			OutOfOrderException {
		if (currentAmusers.size() == batchSize) {
			throw new FullRideException("Sorry");
		}

		if (amuser.isRiding()) {
			throw new AlreadyBoardingException(" Sorry");

		}

		if (this.inMaintenance()) {
			throw new OutOfOrderException("Sorry");
		}

		if (inMaintenance() || amuser.isRiding()
				|| currentAmusers.size() >= batchSize) {

			return false;
		}
		if (amuser.getTicket() == Ticket.Micro
				|| amuser.getTicket() == Ticket.Mini
				|| amuser.getTicket() == Ticket.Maxi) {
			currentAmusers.add(amuser);
			amuser.setRiding(true);
			return true;
		}

		return false;
	}

	public final void unBoard() {
		for (int i = 0; i < currentAmusers.size() - 1
				&& !currentAmusers.isEmpty(); i++) {
			currentAmusers.get(i).setRiding(false);
		}
		this.currentAmusers.clear();

	}

	public final boolean unBoard(Amuser amuser) throws CannotUnboardException {

		if (!amuser.isRiding()) {
			throw new CannotUnboardException("Sorry");
		}

		if (currentAmusers.size() > 0) {
			int a = currentAmusers.indexOf(amuser);
			amuser.setRiding(false);
			currentAmusers.remove(a);

			return true;
		} else {

			return false;
		}

	}

	public boolean start() throws

	CannotOperateException {

		if (inMaintenance()) {
			throw new OutOfOrderException("sorry");
		}

		ridesToMaintain--;
		return true;

	}

	public final boolean inMaintenance() {
		return (ridesToMaintain == 0);
	}

	public final void endMaintenance() {
		ridesToMaintain = 10;
	}

	public boolean availableForAll() {
		for (int k = 0; k < currentAmusers.size(); k++) {
			if (!(currentAmusers.get(k).getTicket() == Ticket.Micro)
					|| !(currentAmusers.get(k).getTicket() == Ticket.Mini)
					|| !(currentAmusers.get(k).getTicket() == Ticket.Maxi)) {
				return false;
			}

		}
		return true;
	}

}
