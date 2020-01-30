import java.sql.Time;

/*
 * Data structure for the info transfer between Floor, Scheduler and Elevators
 */
public class Request {
	
	private int id; 
	private int sourceFloor; 
	private int destFloor; 
	private Time timeStamp;
	
	public Request() {
		
	}

}
