import java.util.ArrayList;

public class Building {
	
	ArrayList<Floor> floors;
	Scheduler scheduler;
	
	public Building(int numOfFloors, Scheduler scheduler) {
		this.scheduler = scheduler;
		floors = new ArrayList<Floor>();
		
		for(int floorNumber = 0; floorNumber < numOfFloors; floorNumber++) {
			floors.add(new Floor(floorNumber, scheduler));
		}
	}
	
	public void startFloors() {
		for(Floor f: floors) {
			Thread floorThread = new Thread(f);
			floorThread.start();
		}
	}

}
