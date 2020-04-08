import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/*
 * @Author Hassan Yusuf 101053489
 * Tests that CSVParser class parses CSVfile
 */
class CSVParserTest {
	
	CSVParser parser;

	@BeforeEach
	void setUp() {
		ElevatorSystem elevator = new ElevatorSystem(0);
		List<ElevatorSystem> elevators = new ArrayList<ElevatorSystem>();
		elevators.add(elevator);
		Scheduler scheduler = new Scheduler(elevators);
		parser = new CSVParser("CSVfile");
	}
	
	@Test
	void testGetRequest() {
		Request request1 = new Request(1, true, 5, "10:20:10", 0);
		Request request2 = new Request(4, false, 1, "10:25:35", 0);
		Request request3 = new Request(6, false, 2, "11:25:12", 0);
		Request request4 = new Request(1, true, 4, "13:20:11", 0);
		ArrayList<Request> request = parser.getRequests();
		assertEquals(request1.toString(), request.get(0).toString());
		assertEquals(request2.toString(), request.get(1).toString());
		assertEquals(request3.toString(), request.get(2).toString());
		assertEquals(request4.toString(), request.get(3).toString());
	}

}
