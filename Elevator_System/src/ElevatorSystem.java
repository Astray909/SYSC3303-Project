import java.util.*;
/**
 * The elevator system is responsible for transporting passengers.
 * It takes requests from Scheduler, 
 * then travel to destination floor in the desired direction
 * @author 
 * @version 
 *
 */
public class ElevatorSystem extends Thread
{
	private static final int ELEVATOR_CAPACITY = 8;//maximum capacity of an elevator
	private static final int FLOOR_HEIGHT = 3;//distance between floors
	private ArrayList<Integer> selectedFloors = new ArrayList<>();
	private boolean direction;//true for up, false for down
	private boolean doorOpenClose;//state of the door
	private Request request;
	private int id;//elevator car id
	private int currFloor;//floor the car is currently on

	/**
	 * Constructor for ElevatorSystem
	 */
	public ElevatorSystem (int id)
	{
		this.id = id;
	}
	
	/**
	 * fetch the specific task
	 * @param request: Request type request, what kind of request
	 */
	public void getRequest(Request request)
	{
		this.request = request;
	}
	
	/**
	 * doorOpenClose class, toggles the opening and closing of the door
	 */
	private void doorOpenClose()
	{
		doorOpenClose = !doorOpenClose;
	}
	
	/**
	 * add requests to stop at specific floors
	 * @param floor: which floor do you want to stop at
	 */
	private void addFloorRequest(int floor)
	{
		selectedFloors.add(floor);
		Collections.sort(selectedFloors);
		if(!direction)
		{
			Collections.reverse(selectedFloors);
		}
		
	}
	
	private void goToFloor(int floor)
	{
		currFloor = floor;
	}
	
	/**
	 * adds appropriate delays
	 */
	private void delay(int delay)
	{
		try
		{
			Thread.sleep(delay * 1000);//delay in seconds.
		}
		catch (InterruptedException e)
		{}
	}
	
	/**
	 * Getter for the current floor
	 * @return the current floor
	 */
	public int getCurrFloor ()
	{
		return this.currFloor;
	}
	
	/**
	 * calculates the distance between floors
	 * @return the distance between the current floor and the first destination floor
	 */
	private int getFloorDistance()
	{
		int diff = Math.abs(selectedFloors.get(0) - currFloor);
		return FLOOR_HEIGHT * diff;
	}
	
	/**
	 * moves the elevator
	 * @return times takes to move from one floor to another
	 */
	private int moveElevator()
	{
		return 0;
	}
	
	/**
	 * Getter for direction
	 */
	public boolean getDIrection ()
	{
		return this.direction;
		//if the elevator is on the ground floor initially then the direction should be up not down
	}
	

	/**
	 * starts thread
	 */
	public void run()
	{
		while(true)
		{
			//delay();
		}
	}
	
}
