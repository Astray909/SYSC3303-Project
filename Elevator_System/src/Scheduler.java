import java.util.List;
/**
 * This class used to handle the request from floor system, choose the right Elavatorsystem and pass the request to Elevator system
 * Currently we just have one Elevator, so scheduler just needs to pass request to that one
 * @author zhe 100968704
 *
 */

public class Scheduler extends Thread {
	
	
	private List<ElevatorSystem> elevators; //All elevators in the system
	
	public Scheduler(List<ElevatorSystem> elevators) {
		// TODO Auto-generated constructor stub
		this.elevators = elevators;
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
	

}
