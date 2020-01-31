import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/*
 * @Author Hassan Yusuf 101053489
 * Tests public methods of ElevatorSystem class
 */
class ElevatorSystemTest {

	ElevatorSystem elevator;
	
	@BeforeEach
	void setUp() {
		elevator = new ElevatorSystem(1);
	}

	@Test
	void testGetCurrFloor() {
		assertEquals(elevator.getCurrFloor(), 1);
	}
	
	@Test
	void testGetDirection() {
		assertTrue(elevator.getDIrection());
	}
}
