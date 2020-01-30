import java.util.Date;

/*
 * @Author: Zhe 100968704
 * Data structure for the info transfer between Floor, Scheduler and Elevators
 */
public class Request {
	
	private int sourceFloor;  // floor of the passenger
	private int destFloor; // floor that passenger wants to go 
	private Date timeStamp; //current time stamp once request created
	private boolean direction; //represent up or down button, true-->up, false-->
	
	public Request(int sourceFloor, boolean direction) {
		
		this.sourceFloor = sourceFloor;
		this.direction = direction;
		this.timeStamp = new Date();
		
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
	public int getDest (int destFloor) {
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
	public Date getTimeStamp() {
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
		info = this.timeStamp.toString() +  " Source floor: " + Integer.toString((this.sourceFloor)) + "Directon: " + dir;
		return info; 
	}
	

}
