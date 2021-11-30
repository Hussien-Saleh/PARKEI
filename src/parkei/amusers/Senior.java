package parkei.amusers;

import parkei.exceptions.WrongAgeException;
import parkei.exceptions.WrongTicketException;
import parkei.utilities.Ticket;

public class Senior extends Amuser implements Rider {

	public Senior() {
		super();
	}

	public Senior(String name, int age, int height) throws WrongAgeException {
		super(name, age, height);
		if (getAge() < 60) {
			throw new WrongAgeException("Sorry");
		}
	}

	public Ticket getTicket() {
		return Ticket.Mini;
	}

	public void buyTicket(Ticket ticket) throws WrongTicketException {
		if (ticket != Ticket.Mini) {
			throw new WrongTicketException("Sorry");
		}
		ticket = Ticket.Mini;
	}

}
