package tests;

import java.lang.reflect.Modifier;
import java.util.ArrayList;

import parkei.amusers.Adult;
import parkei.amusers.Amuser;
import parkei.amusers.Baby;
import parkei.utilities.Effect;
import parkei.amusers.Kid;
import parkei.amusers.Senior;
import parkei.park.rides.BiDirectionalRide;
import parkei.park.rides.CircularRide;
import parkei.park.rides.FerrisWheel;
import parkei.park.rides.FunRide;
import parkei.park.rides.RollerCoaster;
import parkei.park.rides.ScareRide;
import parkei.park.rides.TransportRide;
import parkei.park.rides.WaterRide;
import junit.framework.TestCase;

public class PublicTest extends TestCase {
	private Amuser baby;
	private Amuser kid;
	private Amuser adult;
	private Amuser senior;
	private FunRide splash;
	private FunRide dark;
	private FunRide coaster;
	private FunRide ferris;
	private TransportRide parrade;
	private TransportRide paddleBoats;

	protected void setUp() throws Exception {
		try {
			baby = new Baby("Zeen", 2, 50);
			kid = new Kid("Ahmed", 5, 90);
			adult = new Adult("Eman", 22, 160);
			senior = new Senior("Karima", 65, 150);
		} catch (Exception e) {
			System.out.println("Where is the Amuser constructor!!");
		}

		try {
			splash = new WaterRide("Splash", 15, 4);
			dark = new ScareRide("Dark", 7, 10);
			coaster = new RollerCoaster("Fun Fire", 10, 2);
			ferris = new FerrisWheel("Big O", 30, 30);
		} catch (Exception e) {
			System.out.println("Where is the FunRide constructor!!");
		}

		try {
			ArrayList<FunRide> locs = new ArrayList<FunRide>();
			locs.add(coaster);
			locs.add(ferris);
			locs.add(splash);
			parrade = new CircularRide("Parrade", 30, 30, locs);
			ArrayList<FunRide> loc = new ArrayList<FunRide>();
			loc.add(coaster);
			loc.add(ferris);
			loc.add(splash);
			loc.add(dark);
			paddleBoats = new BiDirectionalRide("Paddle Boats", 45, 50, loc);
		} catch (Exception e) {
			System.out.println("Where is the TransportRide constructor!!");
		}
	}

	public void testCoasterInheritance() {
		assertEquals("Class RollerCoaster should extend class FunRide", coaster
				.getClass().getSuperclass().getName(),
				"eg.edu.guc.parkei.park.rides.FunRide");
	}

	public void testCircularInheritance() {
		assertEquals("Class RollerCoaster should extend class FunRide", parrade
				.getClass().getSuperclass().getName(),
				"eg.edu.guc.parkei.park.rides.TransportRide");
	}

	public void testAmuserAbstraction() {
		assertTrue(
				"Amuser class should be abstract",
				Modifier.isAbstract(baby.getClass().getSuperclass()
						.getModifiers()));
	}

	public void testRider() {
		assertTrue(
				"Baby should implement Rider interface",
		Modifier.isInterface(baby.getClass().getSuperclass().getInterfaces()[0]
				.getModifiers()));
	}

	public void testWetEnum() {
		assertNotNull("Fun rides can turn an amuser Wet", Effect.valueOf("Wet"));
	}

	public void testMaxiEnum() {
		assertNotNull("An amuser can own a Maxi Ticket",
				eg.edu.guc.parkei.utilities.Ticket.valueOf("Maxi"));
	}

	public void testCircularRoute() {
		assertEquals(
				"This is not the correct route that Parrade should follow",
				"The followed route is: Fun Fire, Big O, Splash, Fun Fire",
				parrade.getFullRoute());
	}

	public void testBiDirectionalRoute() {
		assertEquals(
				"This is not the correct route that Paddle Boats should follow",
				"The followed route is: Fun Fire, Big O, Splash, Dark, Splash, Big O, Fun Fire",
				paddleBoats.getFullRoute());
	}

	public void testStartTransport() {
		baby.move(coaster);
		paddleBoats.board(baby);
		paddleBoats.start();
		assertEquals(
				"The number of rides before the next maintenance is decreased",
				9, paddleBoats.getRidesToMaintain());
		assertEquals("Parrade should be stopping at Big O", ferris,
				paddleBoats.getCurrentLocation());
	}

	public void testStartFun() {
		baby.move(ferris);
		ferris.board(baby);
		ferris.start();
		assertEquals(
				"The number of rides before the next maintenance is decreased",
				9, ferris.getRidesToMaintain());
		ArrayList<Effect> ef = new ArrayList<Effect>();
		ef.add(Effect.Thrilled);
		assertEquals("Baby should be Thrilled", ef, baby.getEffects());
	}

	public void testCircularMove() {
		assertEquals("Initially, Parrade is at Fun Fire", "Fun Fire", parrade
				.getCurrentLocation().getName());
		parrade.move();
		assertEquals("Initially, Parrade is at Big O", "Big O", parrade
				.getCurrentLocation().getName());
		parrade.move();
		assertEquals("Initially, Parrade is at Splash", "Splash", parrade
				.getCurrentLocation().getName());
		parrade.move();
		assertEquals("Initially, Parrade is at Fun Fire", "Fun Fire", parrade
				.getCurrentLocation().getName());
	}

	public void testBiDirectionalMove() {
		assertEquals("Initially, Paddle Boats is at Fun Fire", "Fun Fire",
				paddleBoats.getCurrentLocation().getName());
		paddleBoats.move();
		assertEquals("Initially, Paddle Boats is at Big O", "Big O",
				paddleBoats.getCurrentLocation().getName());
		paddleBoats.move();
		assertEquals("Initially, Paddle Boats is at Splash", "Splash",
				paddleBoats.getCurrentLocation().getName());
		paddleBoats.move();
		assertEquals("Initially, Paddle Boats is at Dark", "Dark", paddleBoats
				.getCurrentLocation().getName());
		paddleBoats.move();
		assertEquals("Initially, Paddle Boats is at Splash", "Splash",
				paddleBoats.getCurrentLocation().getName());
		paddleBoats.move();
		assertEquals("Initially, Paddle Boats is at Big O", "Big O",
				paddleBoats.getCurrentLocation().getName());
		paddleBoats.move();
		assertEquals("Initially, Paddle Boats is at Fun Fire", "Fun Fire",
				paddleBoats.getCurrentLocation().getName());
	}

	public void testEligibleWaterBaby() {
		assertFalse("Babies can not ride Water rides!",
				splash.eligibleToRide(baby));
	}

	public void testEligibleWaterKid() {
		assertTrue("Kids can ride Water rides!", splash.eligibleToRide(kid));
	}

	public void testEligibleWaterAdult() {
		assertTrue("Adults can ride Water rides!", splash.eligibleToRide(adult));
	}

	public void testEligibleWaterSenior() {
		assertTrue("Seniors can ride Water rides!",
				splash.eligibleToRide(senior));
	}

	public void testAffectsWaterBaby() {
		assertEquals(
				"Babies can not ride Water rides! It does not affect babies.",
				null, splash.affects(baby));
	}

	public void testAffectsWaterKid() {
		ArrayList<Effect> eff = new ArrayList<Effect>();
		eff.add(Effect.Wet);
		eff.add(Effect.Thrilled);
		assertEquals("Kids turn wet and thrilled after taking water rides",
				eff, splash.affects(kid));
	}

	public void testAffectsWaterAdult() {
		ArrayList<Effect> eff = new ArrayList<Effect>();
		eff.add(Effect.Wet);
		eff.add(Effect.Happy);
		assertEquals("Adults turn wet and happy after taking water rides", eff,
				splash.affects(adult));
	}

	public void testAffectsWaterSenior() {
		ArrayList<Effect> eff = new ArrayList<Effect>();
		eff.add(Effect.Wet);
		eff.add(Effect.Angry);
		assertEquals("Seniors turn wet and angry after taking water rides", eff,
				splash.affects(senior));
	}

}
