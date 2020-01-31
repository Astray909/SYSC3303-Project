import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
		request = new Request(2, true);
		time = new Date();
	}

	@Test
	void sourceTest() {
		assertEquals(request.getSource(), 2);
	}
	
	@Test
	void destTest() {
		assertEquals(request.getDest(), 0);
		request.setDest(3);
		assertEquals(request.getDest(), 3);
	}
	
	@Test
	void directionTest() {
		assertTrue(request.getDirection());
	}
	
	@Test 
	void timeTest()	{
		assertEquals(time, request.getTimeStamp());
	}
	
	@Test
	void toStringTest() {
		assertEquals(request.toString(), time.toString() + " Source floor: " + 2 + "Directon: up");
	}
	
	@Test
	void downToStringTest() {
		Request down = new Request(2, false);
		assertEquals(down.toString(), time.toString() + " Source floor: " + 2 + "Directon: down");
	}
}
