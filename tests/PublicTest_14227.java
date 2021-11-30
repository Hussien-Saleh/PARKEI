package tests;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import junit.framework.TestCase;

import parkei.amusers.Amuser;
import parkei.amusers.Baby;
import parkei.park.rides.FunRide;
import parkei.park.rides.Ride;
import parkei.park.rides.TransportRide;

public class PublicTest extends TestCase {
	private Baby baby;
	private Amuser am;
	private FunRide loc;
	private FunRide fri;
	private FunRide ano;
	private Ride ride;
	private TransportRide tri;

	protected void setUp() throws Exception {
		try {
			baby = new Baby("Zeen", 2, 50);
		} catch (Exception e) {
			System.out.println("Where is Amuser constructor!!");
		}

		try {
			am = new Amuser("Zeen", 2, 50);
		} catch (Exception e) {
			System.out.println("Where is Amuser constructor!!");
		}

		try {
			loc = new FunRide("Dark", 5, 2);
		} catch (Exception e) {
			System.out.println("Where is FunRide constructor!!");
		}

		try {
			fri = new FunRide("Splash", 15, 3);
		} catch (Exception e) {
			System.out.println("Where is Ride constructor!!");
		}
		
		try {
			ano = new FunRide("Scare", 5, 2);
		} catch (Exception e) {
			System.out.println("Where is Ride constructor!!");
		}

		try {
			ride = new Ride("Ride", 0, 0);
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

	public void testGetBabyName() {
		assertEquals("Name should be Zeen as expected from construction",
				"Zeen", baby.getName());
	}

	public void testGetBabyAge() {
		assertEquals("Age should be 2 as expected from construction", 2,
				baby.getAge());
	}

	public void testGetBabyLocation() {
		assertEquals("Initially, everybody is nowhere", null,
				baby.getLocation());
	}

	public void testBabyMove() {
		assertTrue("A baby should be able to move", baby.move(loc));
	}

	public void testGetBabyNewLocation() {
		baby.move(loc);
		assertEquals("The location should have been set to Dark", loc,
				baby.getLocation());
	}

	public void testGetRiding() {
		assertEquals("Initially, an amuser is not riding anything", false,
				baby.isRiding());
	}

	public void testRideInheritance() {
		assertEquals("Class Baby should extend class Amuser", baby.getClass()
				.getSuperclass(), am.getClass());
	}

	public void testAgeEncapsulation() throws NoSuchFieldException {
		Field field = baby.getClass().getSuperclass().getDeclaredField("age");
		assertEquals("Instance variables should be private", 2,
				field.getModifiers());
	}

	public void testSetBabyName() {
		baby.setName("Ahmed");
		assertEquals("Name should be Zeen as expected from setting name",
				"Ahmed", baby.getName());
	}

	public void testsetBabyAge() {
		baby.setAge(3);
		assertEquals("Age should be 3 as expected from setting age", 3,
				baby.getAge());
	}

	public void testsetBabyLocation() {
		baby.setLocation(fri);
		assertEquals("Babi's location should become fri", fri,
				baby.getLocation());
	}

	public void testsetRiding() {
		baby.setRiding(true);
		assertEquals("Now, baby should be riding", true, baby.isRiding());
	}

	public void testGetRideName() {
		assertEquals("Name should be Splash as expected from construction",
				"Splash", fri.getName());
	}

	public void testGetRideDuration() {
		assertEquals(
				"Splash duration should be 15 minutes as expected from construction",
				15, fri.getDuration());
	}

	public void testGetRideBatchSize() {
		assertEquals("Splash should take up to 3 people", 3, fri.getBatchSize());
	}

	public void testGetCurrentAmusers() {
		assertEquals("Initially, nobody is riding Splash",
				new ArrayList<Amuser>(), fri.getCurrentAmusers());
	}

	public void testGetRidesToMaintain() {
		assertEquals("Initially, the ride should be maintained after 10 runs",
				10, fri.getRidesToMaintain());
	}

	public void testSetRideName() {
		fri.setName("Water Splash");
		assertEquals("Name should be Splash as expected from construction",
				"Water Splash", fri.getName());
	}

	public void testSetRideDuration() {
		fri.setDuration(20);
		assertEquals("Splash duration should become 20 minutes as set", 20,
				fri.getDuration());
	}

	public void testSetRideBatchSize() {
		fri.setBatchSize(4);
		assertEquals("Splash can now take up to 4 people", 4,
				fri.getBatchSize());
	}

	public void testSetCurrentAmusers() {
		ArrayList<Amuser> ams = new ArrayList<Amuser>();
		ams.add(baby);
		fri.setCurrentAmusers(ams);
		assertEquals("Now, baby is riding Splash", ams, fri.getCurrentAmusers());
	}

	public void testSetRidesToMaintain() {
		fri.setRidesToMaintain(4);
		assertEquals("Splash is now set to run 4 times before maintenance", 4,
				fri.getRidesToMaintain());
	}

	public void testInheritance() {
		assertEquals("Class FunRide should extend class Ride", fri.getClass()
				.getSuperclass(), ride.getClass());
	}

	public void testBatchSizeEncapsulation() throws NoSuchFieldException {
		Field field = fri.getClass().getSuperclass()
				.getDeclaredField("batchSize");
		assertEquals("Instance variables should be private", 2,
				field.getModifiers());
	}

	public void testBoardCorrectLoc() {
		baby.move(fri);
		assertTrue("Baby is now at Splash thus he can ride it", fri.board(baby));
	}

	public void testBoardBoarderThis() {
		baby.move(fri);
		fri.board(baby);
		assertFalse(
				"Baby is already boarding Splash thus he can not board again",
				fri.board(baby));
	}

	public void testBoardInMaintenance() {
		fri.setRidesToMaintain(0);
		assertFalse("Splash should be maintained now", fri.board(baby));
	}

	public void testBoardOutMaintenance() {
		baby.move(fri);
		fri.setRidesToMaintain(1);
		assertFalse("Splash should not be maintained now", fri.inMaintenance());
	}

	public void testEligibleToRide() throws NoSuchMethodException,
			IllegalAccessException, InvocationTargetException {
		Method method = fri.getClass().getDeclaredMethod("eligibleToRide",
				baby.getClass().getSuperclass());
		method.setAccessible(true);
		assertEquals("Everybody is eleigible to ride any ride for now", true,
				method.invoke(fri, baby));
	}

	public void testunBoard() {
		fri.unBoard();
		assertEquals("Splash should have no amusers on board",
				new ArrayList<Amuser>(), fri.getCurrentAmusers());
	}

	public void testunBoardBoarding() {
		baby.move(fri);
		fri.board(baby);
		assertTrue("Baby should succeed in unboarding Splash",
				fri.unBoard(baby));
	}

	public void testEndMaintenance() {
		fri.setRidesToMaintain(0);
		assertTrue("Splash should be maintained now", fri.inMaintenance());
		fri.endMaintenance();
		assertFalse("Splash should not be maintained now", fri.inMaintenance());
		assertEquals(10, fri.getRidesToMaintain());
	}

	public void testRouteLocations() {
		tri.setRouteLocations(new ArrayList<FunRide>());
		assertEquals("Splash should be maintained now",
				new ArrayList<FunRide>(), tri.getRouteLocations());
		tri.getRouteLocations().add(fri);
		tri.getRouteLocations().add(loc);
		assertEquals("Parrade is currently at Splash", fri,
				tri.getCurrentLocation());
	}

	public void testMove() throws NoSuchMethodException,
			IllegalAccessException, InvocationTargetException {
		tri.setRouteLocations(new ArrayList<FunRide>());
		assertEquals("The set of locations is now empty",
				new ArrayList<FunRide>(), tri.getRouteLocations());
		tri.getRouteLocations().add(fri);
		tri.getRouteLocations().add(loc);
		assertEquals("Parrade is currently at Splash", fri,
				tri.getCurrentLocation());
		Method method = tri.getClass().getDeclaredMethod("move");
		method.setAccessible(true);
		baby.setLocation(fri);
		assertEquals("Baby is now at Splash", fri, baby.getLocation());
		assertTrue(tri.board(baby));
		method.invoke(tri);
		assertEquals("Parrade should be stopping at Dark", loc,
				tri.getCurrentLocation());
		assertEquals("Baby should have moved as it boarded Parradeh", loc,
				baby.getLocation());
	}
	
	public void testUnboard() 
	 {
	       baby.move(fri);
	       fri.board(baby);
	       fri.unBoard(baby);
	       assertFalse("Baby shouldn't be riding", baby.isRiding()); 
	 }
	
	public void testCircularMove() {
		tri.setRouteLocations(new ArrayList<FunRide>());
		tri.getRouteLocations().add(fri);
		tri.getRouteLocations().add(loc);
		tri.getRouteLocations().add(ano);
		assertEquals(
				"Now, Parrade should be at Splash",
				fri, tri.getCurrentLocation());
		tri.move();
		assertEquals(
				"Now, Parrade should be at Dark",
				loc, tri.getCurrentLocation());
		tri.move();
		assertEquals(
				"Now, Parrade should be at Scare",
				ano, tri.getCurrentLocation());
		tri.move();
		assertEquals(
				"Now, Parrade should be at Splash",
				fri, tri.getCurrentLocation());
	}
}
