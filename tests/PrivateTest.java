package tests;

import java.lang.reflect.Field;
import java.util.ArrayList;

import junit.framework.TestCase;

import parkei.amusers.Adult;
import parkei.amusers.Amuser;
import parkei.amusers.Baby;
import parkei.amusers.Kid;
import parkei.amusers.Senior;
import parkei.park.rides.FunRide;
import parkei.park.rides.Ride;
import parkei.park.rides.TransportRide;

public class PrivateTest extends TestCase {
	private Kid kid;
	private Amuser am;
	private Adult ad;
	private Senior sen;
	private Baby baby;
	private FunRide loc;
	private FunRide fri;
	private Ride ride;
	private TransportRide tri;

	protected void setUp() throws Exception {
		try {
			kid = new Kid("Zeen", 11, 120);
		} catch (Exception e) {
			System.out.println("Where is Amuser constructor!!");
		}
		try {
			ad = new Adult("Zeen", 21, 180);
		} catch (Exception e) {
			System.out.println("Where is Amuser constructor!!");
		}
		try {
			sen = new Senior("Osama", 71, 155);
		} catch (Exception e) {
			System.out.println("Where is Amuser constructor!!");
		}
		try {
			am = new Amuser("Zeen", 21, 180);
		} catch (Exception e) {
			System.out.println("Where is Amuser constructor!!");
		}
		try {
			baby = new Baby("Zeen", 2, 50);
		} catch (Exception e) {
			System.out.println("Where is Amuser constructor!!");
		}
		try {
			loc = new FunRide("Dark", 5, 2);
		} catch (Exception e) {
			System.out.println("Where is FunRide constructor!!");
		}
		try {
			ride = new Ride("Ride", 0, 0);
		} catch (Exception e) {
			System.out.println("Where is Ride constructor!!");
		}
		try {
			fri = new FunRide("Splash", 15, 3);
		} catch (Exception e) {
			System.out.println("Where is Ride constructor!!");
		}
		try {
			ArrayList<FunRide> loc = new ArrayList<FunRide>();
			loc.add(fri);
			tri = new TransportRide("Parrade", 15, 3, loc);
		} catch (Exception e) {
			System.out.println("Where is Ride constructor!!");
		}
	}

	public void testKidInheritance() {
		assertEquals("Class Kid should extend class Amuser", kid.getClass()
				.getSuperclass(), am.getClass());
	}

	public void testAdultInheritance() {
		assertEquals("Class Adult should extend class Amuser", ad.getClass()
				.getSuperclass(), am.getClass());
	}

	public void testSeniorInheritance() {
		assertEquals("Class Senior should extend class Amuser", sen.getClass()
				.getSuperclass(), am.getClass());
	}

	public void testAmuserNameEncapsulation() throws NoSuchFieldException {
		Field field = kid.getClass().getSuperclass().getDeclaredField("name");
		assertEquals("Instance variables should be private", 2,
				field.getModifiers());
	}

	public void testHeightEncapsulation() throws NoSuchFieldException {
		Field field = kid.getClass().getSuperclass().getDeclaredField("height");
		assertEquals("Instance variables should be private", 2,
				field.getModifiers());
	}

	public void testLocationEncapsulation() throws NoSuchFieldException {
		Field field = kid.getClass().getSuperclass()
				.getDeclaredField("location");
		assertEquals("Instance variables should be private", 2,
				field.getModifiers());
	}
	
	public void testRidingEncapsulation() throws NoSuchFieldException {
		Field field = kid.getClass().getSuperclass()
				.getDeclaredField("riding");
		assertEquals("Instance variables should be private", 2,
				field.getModifiers());
	}

	public void testInheritance() {
		assertEquals("Class TransportRide should extend class Ride", tri
				.getClass().getSuperclass(), ride.getClass());
	}

	public void testRideNameEncapsulation() throws NoSuchFieldException {
		Field field = fri.getClass().getSuperclass().getDeclaredField("name");
		assertEquals("Instance variables should be private", 2,
				field.getModifiers());
	}

	public void testDurationEncapsulation() throws NoSuchFieldException {
		Field field = fri.getClass().getSuperclass()
				.getDeclaredField("duration");
		assertEquals("Instance variables should be private", 2,
				field.getModifiers());
	}

	public void testCurrentAmusersEncapsulation() throws NoSuchFieldException {
		Field field = fri.getClass().getSuperclass()
				.getDeclaredField("batchSize");
		assertEquals("Instance variables should be private", 2,
				field.getModifiers());
	}

	public void testRidesToMaintainEncapsulation() throws NoSuchFieldException {
		Field field = fri.getClass().getSuperclass()
				.getDeclaredField("batchSize");
		assertEquals("Instance variables should be private", 2,
				field.getModifiers());
	}

	public void testBoardWrongLoc() {
		assertFalse("Baby is not at Splash to be able to ride it",
				fri.board(baby));
	}
	
	public void testBoardFull() {
		kid.setLocation(fri);
		am.setLocation(fri);
		sen.setLocation(fri);
		ad.setLocation(fri);
		assertTrue(fri.board(kid));
		assertTrue(fri.board(am));
		assertTrue(fri.board(sen));
		assertFalse("Splash is already full",
				fri.board(ad));
	}

	public void testBoardBoarderAnother() {
		baby.move(tri.getCurrentLocation());
		tri.board(baby);
		assertFalse(
				"Baby is already boarding Parrade thus he can not board Splash",
				fri.board(baby));
	}
	
	public void testunBoardunBoarding() {
		baby.move(fri);
		fri.board(baby);
		fri.unBoard(baby);
		assertFalse("Baby already unboarded Splash", fri.unBoard(baby));
	}

	public void testTransportAvailability() {
		assertTrue("Transport rides are available for all amusers",
				tri.availableForAll());
	}

	public void testFunAvailability() {
		assertFalse("Fun rides are not available for all amusers",
				fri.availableForAll());
	}

	public void testSuccessStart() {
		tri.setRouteLocations(new ArrayList<FunRide>());
		tri.getRouteLocations().add(fri);
		tri.getRouteLocations().add(loc);
		tri.start();
		assertEquals(
				"The number of rides before the next maintenance is decreased",
				9, tri.getRidesToMaintain());
	}
	
	public void testFailureStart() {
		fri.start();
		assertNotSame(
				"The number of rides before the next maintenance is not decreased",
				9, fri.getRidesToMaintain());
	}
}
