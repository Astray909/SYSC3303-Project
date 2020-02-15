import java.util.HashMap;
import java.util.List;
/**
 * This class used to handle the request from floor system, choose the right Elavatorsystem and pass the request to Elevator system
 * Currently we just have one Elevator, so scheduler just needs to pass request to that one
 * @author zhe 100968704
 *
 */

public class Scheduler extends Thread {


	private List<ElevatorSystem> elevators; //All elevators in the system
	private static HashMap<ElevatorSystem, Integer> elevatorStatus;

	public Scheduler (List<ElevatorSystem> elevators) {
		this.elevators = elevators;
		Scheduler.elevatorStatus = new HashMap<ElevatorSystem, Integer>();
		for (ElevatorSystem elevator: elevators) {
			Scheduler.elevatorStatus.put(elevator, elevator.getCurrFloor());
		}
	}

	@Override
	public void run() {

	}

	/**
	 * A method to receive request from floor and pass the request to the best(nearest) elevator
	 * @param request
	 */
	public synchronized void getRequest (Request request) {
		int level = request.getSource();
		ElevatorSystem desiredElevator = this.elevators.get(0); 
		if (request.getDirection()) { //going up
			int tempFloorNum=0;
			for (ElevatorSystem elevator: this.elevators) {
				if (tempFloorNum==0) {
					tempFloorNum = elevator.getCurrFloor();
					desiredElevator = elevator;
				} else if (elevator.getDIrection() && elevator.getCurrFloor()> tempFloorNum && elevator.getCurrFloor()<level) {//going up elevator
					desiredElevator = elevator;	
					tempFloorNum = elevator.getCurrFloor();
				} else if (!elevator.getDIrection() && elevator.getCurrFloor()< tempFloorNum && elevator.getCurrFloor()>level) {//going down elevator
					desiredElevator = elevator;	
					tempFloorNum = elevator.getCurrFloor();
				}
				desiredElevator.getRequest(request);
			}
		} else if (!request.getDirection()) {//going down
			int tempFloorNum=0;
			for (ElevatorSystem elevator: this.elevators) {
				if (tempFloorNum==0) {
					tempFloorNum = elevator.getCurrFloor();
					desiredElevator = elevator;
				} else if (!elevator.getDIrection() && elevator.getCurrFloor()< tempFloorNum && elevator.getCurrFloor()>level) {//going up elevator
					desiredElevator = elevator;	
					tempFloorNum = elevator.getCurrFloor();
				} else if (elevator.getDIrection() && elevator.getCurrFloor()> tempFloorNum && elevator.getCurrFloor()<level) {//going down elevator
					desiredElevator = elevator;	
					tempFloorNum = elevator.getCurrFloor();
				}
				desiredElevator.getRequest(request);
			}
		}		
	}


	public static void elevatorFloor (ElevatorSystem elevator, int floor) {
		elevatorStatus.put(elevator, floor);
		System.out.println("Scheduler: Receive signal from Elevator sytem: Elevator "+ elevator.getId() + " arrive at floor "+ floor);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
