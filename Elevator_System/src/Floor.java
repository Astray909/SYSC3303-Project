/**
 * The floor subsystem is used simulate the arrival of passengers to the
 * elevators and for simulating all button presses and lamps. It should also
 * test for the proper operation of the elevator
 */
public class Floor implements Runnable {
	private static final int GROUNDFLOOR_SLEEP_RANGE = 500;
	private static final int FLOOR_SLEEP_RANGE = 1000;
	private static int topFloorNum = 0;
	private static int bottomFloorNum = 0;
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
		if(floorNumber > topFloorNum) {
			topFloorNum = floorNumber;
		}else if(floorNumber < bottomFloorNum) {
			bottomFloorNum = floorNumber;
		}
		this.floorNumber = floorNumber;
		this.scheduler = scheduler;
	}

	@Override
	public void run() {
		while (true) {
			simulatePassengerFlow();
		}
	}

	/**
	 * This method simulates passengers flowing into the elevators by waiting a random amount of
	 * time and then generating a request and sending it to the scheduler. 
	 */
	private void simulatePassengerFlow() {
		try {
			int randomSleep;
			if (floorNumber == 0) { // If ground floor then more activity
				randomSleep = (int) ((Math.random() * GROUNDFLOOR_SLEEP_RANGE) + GROUNDFLOOR_SLEEP_RANGE); // generates a request
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

	/**
	 * This method generates requests appropriate for the floor
	 * @return
	 */
	public Request generateRequest() {
		boolean upOrDown = Math.random() < 0.5; // 50% true, 50% false
		return new Request(floorNumber, upOrDown);
	}

	private void sendRequest(Request request) {
		scheduler.getRequest(request);

	}

}
