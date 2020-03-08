import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/*
 * @Author Hassan Yusuf 101053489
 * Tests the scheduler class if its interaction with 
 * the elevator subsystem and the request class is correct
 */
public class SchedulerTest {

	Scheduler scheduler;
	List<ElevatorSystem> elevators;
	ElevatorSystem elevator1;
	ArrayList<Request> requests;
	Building build;
	
	@BeforeEach
	void setUp() {
		elevators = new ArrayList<ElevatorSystem>();
		elevator1 = new ElevatorSystem(1);
		elevators.add(elevator1);
		scheduler = new Scheduler(elevators);
		build = new Building(10, scheduler);
		requests = new ArrayList<Request>();
	}
	
	@AfterEach
	void tearDown() {
		scheduler.getSchedulerSocket().close();
	}
	
	@Test
	void testGetUpRequest() {
		Request request = new Request(0, true,  2, "10:20:10");
		requests.add(request);
		build.parseRequest(requests);
		scheduler.sendAndReceive();
		elevator1.sendAndReceive();
		assertEquals(elevator1.getTestRequest(), request);
	}
	
	@Test
	void testGetDownRequest() {
		Request request = new Request(5, false,  2, "10:20:10");
		requests.add(request);
		build.parseRequest(requests);
		scheduler.sendAndReceive();
		elevator1.sendAndReceive();
		assertEquals(elevator1.getTestRequest(), request);
	}
}
