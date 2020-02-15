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
	void testGetId() {
		assertEquals(1, elevator.getElevatorId());
	}

	@Test
	void testGetCurrFloor() {
		assertEquals(elevator.getCurrFloor(), 0);// intial floor
	}
	
	@Test
	void testGetDirection() {
		assertTrue(elevator.getDIrection());
	}
}
