import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/*
 * @Author: Zhe 100968704
 * Data structure for the info transfer between Floor, Scheduler and Elevators
 */
public class Request {

	private int sourceFloor;  // floor of the passenger
	private int destFloor; // floor that passenger wants to go 
	private LocalTime timeStamp; //current time stamp once request created
	private boolean direction; //represent up or down button, true-->up, false--> down

	public Request(int sourceFloor, boolean direction) {

		this.sourceFloor = sourceFloor;
		this.direction = direction;

	}

	public Request(int sourceFloor, boolean direction, int destFloor, String time) {

		this.sourceFloor = sourceFloor;
		this.direction = direction;
		this.destFloor = destFloor;
		LocalTime timeStamp = LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm:ss"));
		this.timeStamp = timeStamp;

	}

	/**
	 * Get the source floor
	 * @return integer represents the floor
	 */
	public int getSource () {
		return this.sourceFloor;
	}

	/**
	 * Setter for the dest floor
	 * @param destFloor
	 */
	public void setDest(int destFloor) {
		this.destFloor = destFloor;
	}

	/**
	 * Getter for the dest floor
	 * @param destFloor
	 * @return
	 */
	public int getDest () {
		return this.destFloor;
	}

	/**
	 * Getter for the direction, true-->up, false-->down
	 * @return
	 */
	public boolean getDirection () {
		return this.direction;
	}

	/**
	 * Getter for the time stamp
	 * @return
	 */
	public LocalTime getTimeStamp() {
		return this.timeStamp;
	}

	@Override 
	public String toString () {
		String info = "";
		String dir;
		if (direction) {
			dir = "up";
		} else {
			dir = "down"; 
		}
		info = this.timeStamp.toString() +  " Source floor: " + this.sourceFloor + "Directon: " + dir;
		return info; 
	}

}
