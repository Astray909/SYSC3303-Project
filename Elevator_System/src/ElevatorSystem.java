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
	private ArrayList<Integer> selectedFloors = new ArrayList<>();
	private boolean direction;
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
		
	}
	
	private void goToFloor(int floor)
	{
		currFloor = floor;
	}
	
	private void delay()
	{
		try
		{
			Thread.sleep(1000);//door opening and closing delay 1s.
		}
		catch (InterruptedException e)
		{}
	}
	
	/**
	 * starts thread
	 */
	public void run()
	{
		while(true)
		{
			goToFloor(1);
		}
	}
	
	/**
	 * Getter for the current floor
	 */
	public int getCurrFloor ()
	{
		 return this.currFloor;
	}
	
	/**
	 * Getter for direction
	 */
	public boolean getDIrection ()
	{
		return this.direction;
		//if the elevator is on the ground floor initially then the direction should be up not down
	}
}
