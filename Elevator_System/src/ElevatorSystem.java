import java.util.*;
/**
 * The elevator system is responsible for transporting passengers.
 * It takes requests from Floor and Scheduler, move to designated floor, 
 * then travel to destination floor in desired direction
 * @author Jia Chen Huang 101073186
 * @version January 30
 *
 */
public class ElevatorSystem 
{
	private ArrayList<Integer> selectedFloors = new ArrayList<>();
	private boolean direction;
	private boolean doorOpenClose;//state of the door
	private Request request;
	private int id;//elevator car id
	private int currFloor;//floor the car is currently on

	/**
	 * fetch the specific task
	 * @param request: Request type request, what kind of request
	 */
	public void getRequest(Request request)
	{
		request = this.request;
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
	
	/**
	 * starts thread
	 */
	public void run()
	{
		while(true)
		{
			try
			{
				Thread.sleep(1000);//door opening and closing delay 1s. testing commit shortcut
				
			}
			catch (InterruptedException e)
			{
				
			}
		}
	}
}
