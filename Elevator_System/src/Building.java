import java.util.ArrayList;

/**
 * The building class creates and runs the floor threads inside the building.
 * @author Simon
 *
 */
public class Building implements Runnable{

	/** The floors in the building */
	ArrayList<Floor> floors;

	/** The scheduler responsible for scheduling the floors */
	Scheduler scheduler;

	/**
	 * Constructor for a bulding object. 
	 * @param numOfFloors The number of floors the building has.
	 * @param scheduler The scheduler responsible for scheduling the floors.
	 */
	public Building(int numOfFloors) {
		floors = new ArrayList<Floor>();

		for(int floorNumber = 0; floorNumber < numOfFloors; floorNumber++) {
			floors.add(new Floor(floorNumber));
		}
	}

	/**
	 * Starts the floor threads in the building
	 */
	public void startFloors() {
		for(Floor f: floors) {
			Thread floorThread = new Thread(f);
			floorThread.start();
		}
	}

	/**
	 * Takes an array list of requests sorted by earliest time and then 
	 * sends the requests to the appropriate floors. The floors then send requests to the scheduler
	 */
	public void parseRequest(ArrayList<Request> requests) {
		for(Request req: requests) {
			Floor sourceFloor = floors.get(req.getSource());
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sourceFloor.sendRequest(req);
		}

	}

	/**
	 * Getter for the list of floors
	 * @return floors
	 */
	public ArrayList<Floor> getFloors() {
		return this.floors;
	}

	@Override
	public void run() {
		CSVParser parser = new CSVParser("CSVfile");
		this.parseRequest(parser.getRequests());
	}
}
