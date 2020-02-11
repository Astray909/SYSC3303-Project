import java.util.ArrayList;

/**
 * The building class creates and runs the floor threads inside the building.
 * @author Simon
 *
 */
public class Building {
	
	/** The floors in the building */
	ArrayList<Floor> floors;
	
	/** The scheduler responsible for scheduling the floors */
	Scheduler scheduler;
	
	/**
	 * Constructor for a bulding object. 
	 * @param numOfFloors The number of floors the building has.
	 * @param scheduler The scheduler responsible for scheduling the floors.
	 */
	public Building(int numOfFloors, Scheduler scheduler) {
		this.scheduler = scheduler;
		floors = new ArrayList<Floor>();
		
		for(int floorNumber = 0; floorNumber < numOfFloors; floorNumber++) {
			floors.add(new Floor(floorNumber, scheduler));
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

}
