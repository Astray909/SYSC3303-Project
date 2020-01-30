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
	private boolean doorOpenClose;
	private Request request;
	private int id;
	
	private void doorOpenCose()
	{
		doorOpenClose = !doorOpenClose;
	}
	
	public void getTask(Request request)
	{
		request = this.request;
	}
	
	private void addFloorRequest(int floor)
	{
		selectedFloors.add(floor);
		
	}
	
	public void run()
	{
		while(true)
		{
			try
			{
				Thread.sleep(1000);//door opening and closing delay 1s
			}
			catch (InterruptedException e)
			{
				
			}
		}
	}
}
