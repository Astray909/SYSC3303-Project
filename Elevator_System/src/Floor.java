import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * The floor subsystem is used simulate the arrival of passengers to the
 * elevators and for simulating all button presses and lamps. It should also
 * test for the proper operation of the elevator
 */
public class Floor extends Thread {
	//private InetAddress schedulerAddr = InetAddress.getLocalHost();
	private Scheduler scheduler;

	/**
	 * The lobby/ground floor is floorNumber 0. This floor is where most passengers
	 * enter the elevator. Every floor above that has a +positive floorNumber and
	 * below has a -negative floorNumber.
	 */
	private int floorNumber;
	
	private DatagramSocket sendSocket; 


	/**
	 * Constructor for floor
	 * @param floorNumber The floor number
	 */
	public Floor(int floorNumber) {
		this.floorNumber = floorNumber;
		try {
			sendSocket = new DatagramSocket(); 
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	public void sendRequest(Request request) {
		System.out.println("Floor class: At " + request.getTimeStamp() + ". \nA passenger on floor " + request.getSource() + " has requested an elevator to floor " + request.getDest() + ".");
		ByteArrayOutputStream data = new ByteArrayOutputStream();
		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream(data);
			out.writeObject(request);
			out.flush();
			DatagramPacket sendPacket = new DatagramPacket(data.toByteArray(), data.toByteArray().length, Serializer.deserializeInetAddress("SchedulerElevatorAddress.txt"), 53266);
			sendSocket.send(sendPacket);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Scheduler: Error create output stream.");
		}
	}

	/**
	 * Getter for the floorNumber
	 * @return floorNumber
	 */
	public int getFloorNumber() {
		return this.floorNumber;
	}

}
