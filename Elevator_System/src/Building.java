import java.util.ArrayList;

public class Building {
	
	ArrayList<Floor> floors;
	int topFloorNum = 0;
	int bottomFloorNum = 0;
	Scheduler scheduler;
	
	
	public Building(int numOfFloors, Scheduler scheduler) {
		floors = new ArrayList<Floor>();
		this.scheduler = scheduler;
		
		for(int floorNumber = 0; floorNumber < numOfFloors; floorNumber++) {
			floors.add(new Floor(floorNumber, scheduler));
		}
		topFloorNum = numOfFloors - 1; 
		
	}
	
	public void addUpperFloor() {
		topFloorNum++; 
		floors.add(new Floor(topFloorNum, scheduler));
	}
	
	public void addLowerFloor() { 
		bottomFloorNum--;
		floors.add(new Floor(bottomFloorNum, scheduler));
	}

	public void startFloors() {
		for(Floor f: floors) {
			f.run();
		}
	}
}
