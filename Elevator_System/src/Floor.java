/**
 * The floor subsystem is used simulate the arrival of passengers to the
 * elevators and for simulating all button presses and lamps. It should also
 * test for the proper operation of the elevator
 */
public class Floor implements Runnable {
	private static final int GROUND_SLEEP_RANGE = 500;
	private static final int FLOOR_SLEEP_RANGE = 1000;
	private Scheduler scheduler;

	/**
	 * The lobby/ground floor is floorNumber 0. This floor is where most passengers
	 * enter the elevator. Every floor above that has a +positive floorNumber and
	 * below has a -negative floorNumber.
	 */
	private int floorNumber;

	
	/**
	 * Constructor for floor
	 * @param floorNumber The floor number
	 * @param scheduler The scheduler responsible for handling requests sent from the floor
	 */
	public Floor(int floorNumber, Scheduler scheduler) {
		this.floorNumber = floorNumber;
		this.scheduler = scheduler;
	}

	@Override
	public void run() {
		while (true) {
			simulatePassengerFlow();
		}
	}

	private void simulatePassengerFlow() {
		try {
			int randomSleep;
			if (floorNumber == 0) { // If ground floor then more activity
				randomSleep = (int) ((Math.random() * GROUND_SLEEP_RANGE) + GROUND_SLEEP_RANGE); // generates a request
																									// every 5 to 10
																									// seconds
			} else {
				randomSleep = (int) ((Math.random() * FLOOR_SLEEP_RANGE) + FLOOR_SLEEP_RANGE); // generates an request
																								// every 10 to 20
																								// seconds
			}
			Thread.sleep(randomSleep);
			sendRequest(generateRequest());
		} catch (InterruptedException e) {

		}

	}

	public Request generateRequest() {
		boolean upOrDown = Math.random() < 0.5; // 50% true, 50% false
		return new Request(floorNumber, upOrDown);
	}

	public void sendRequest(Request request) {
		scheduler.getRequest(request);

	}

}
