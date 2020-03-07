import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
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
	private DatagramSocket schedulerSocket; // Socket at port 23, used to send and receive packet 
	
	public Scheduler (List<ElevatorSystem> elevators) {
		this.elevators = elevators;
		Scheduler.elevatorStatus = new HashMap<ElevatorSystem, Integer>();
		try {
			this.schedulerSocket = new DatagramSocket(23);
		} catch (SocketException e) {
			System.out.println("Scheduler: Fail to create socket 23");
		}
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
			}
		}
		this.sendPacket(request, desiredElevator);
		
	}

	public void sendAndReceive () {
		DatagramPacket packet = new DatagramPacket(new byte[1000], 1000);
		try {
			this.schedulerSocket.receive(packet);
			ByteArrayInputStream bis = new ByteArrayInputStream(packet.getData());
			ObjectInputStream in = null;
			
			try {
				in = new ObjectInputStream(bis);
				try {
					Request request = (Request) in.readObject();
					this.getRequest(request);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					System.out.println("Scheduler: Error parse request from packet");
				} finally {
					in.close();
				}
				
			} catch (IOException e) {
				System.out.println("Scheduler: Error parse packet.");
			}
		} catch (IOException e) {
			System.out.println("Scheduler: Error receive packet.");
		}
	}
	
	private void sendPacket (Request request, ElevatorSystem elevator) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream(bos);
			out.writeObject(request);
			out.flush();
			DatagramPacket sendPacket = new DatagramPacket(bos.toByteArray(), bos.toByteArray().length, elevator.getPortNum());
			this.schedulerSocket.send(sendPacket);
		} catch (IOException e) {
			System.out.println("Scheduler: Error create output stream.");
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
