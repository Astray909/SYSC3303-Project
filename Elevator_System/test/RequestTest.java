import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/*
 * @Author Hassan Yusuf 101053489
 * Tests method of Request class
 */
public class RequestTest {
	Request request;
	Date time;
	
	@BeforeEach
	void setUp() {
		request = new Request(2, true, 4, "10:20:10");
	}

	@Test
	void sourceTest() {
		assertEquals(request.getSource(), 2);
	}

	@Test
	void destTest() {
		assertEquals(request.getDest(), 4);
		request.setDest(3);
		assertEquals(request.getDest(), 3);
	}
	
	@Test
	void directionTest() {
		assertTrue(request.getDirection());
	}
	
	@Test 
	void timeTest()	{
		LocalTime time = LocalTime.parse("10:20:10", DateTimeFormatter.ofPattern("HH:mm:ss"));
		assertEquals(time, request.getTimeStamp());
	}
	
	@Test
	void toStringTest() {
		assertEquals(request.toString(), "10:20:10 Source floor: " + 2 + "Directon: up");
	}
	
	@Test
	void downToStringTest() {
		Request down = new Request(2, false, 1, "10:20:10");
		assertEquals(down.toString(), "10:20:10 Source floor: " + 2 + "Directon: down");
	}
}
