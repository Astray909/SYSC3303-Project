import java.util.*;
import java.lang.Math.*;
import java.io.*;
import java.net.*;
/**
 * The elevator system is responsible for transporting passengers.
 * It takes requests from Scheduler, 
 * then travel to destination floor in the desired direction
 * @author Jia Chen Huang
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
	private int portNum; //port number for the elevator
	private boolean errorB = false;
	private int error;

	private static DatagramPacket receivePacket;
	private static DatagramSocket sendSocket, receiveSocket;
	private InetAddress address;

	/**
	 * Constructor for ElevatorSystem
	 */
	public ElevatorSystem (int id)
	{
		this.id = id;
		//currFloor = 1;
		direction = true;
		try
		{
			sendSocket = new DatagramSocket();
			receiveSocket = new DatagramSocket(portNum);
		}
		catch (SocketException se)
		{
			se.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * Setter for port number 
	 */
	public void setPortNum (int num)
	{
		this.portNum = num;
	}

	public int getPortNum ()
	{
		return this.portNum;
	}

	/*public void sendAndReceive()
	{
		receivePacket = waitForPacket(receiveSocket);

		ByteArrayInputStream bis = new ByteArrayInputStream(receivePacket.getData());
		ObjectInputStream in = null;

		try
		{
			in = new ObjectInputStream(bis);
			try
			{
				Request request = (Request) in.readObject();
				this.getRequest(request);
			}
			catch (ClassNotFoundException e)
			{
				System.out.println("Elevator: Error parse request from packet");
			}
			finally
			{
				in.close();
			}

		}
		catch (IOException e)
		{
			System.out.println("Elevator: Error parse packet.");
		}

		byte[] replyData = null;

		System.out.println("Elevator: Packet sending");
		//sendPacket(null);
		System.out.println("Send Packet: Success");
	}*/

	public DatagramPacket waitForPacket(DatagramSocket s)
	{

		DatagramPacket receivePacket = new DatagramPacket(new byte[1000], 1000);
		System.out.println("Elevator: Waiting for Packet.\n");


		try
		{
			System.out.println("Elevator: Waiting...");
			s.receive(receivePacket);
		}
		catch (IOException e)
		{
			System.out.print("Elevator: IO Exception: likely:");
			System.out.println("Elevator: Receive Socket Timed Out.\n" + e);
			e.printStackTrace();
			System.exit(1);
		}

		System.out.println("Elevator: Packet received:");
		//prints out information about the packet
		System.out.println("Elevator: Packet from host: " + receivePacket.getAddress());
		System.out.println("Elevator: From host port: " + receivePacket.getPort());
		System.out.println("Elevator: Length: " + receivePacket.getLength());

		return receivePacket;
	}

	private void sendPacket (Request request)
	{
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream out = null;
		try
		{
			out = new ObjectOutputStream(bos);
			out.writeObject(request);
			out.flush();
			DatagramPacket sendPacket = new DatagramPacket(bos.toByteArray(), bos.toByteArray().length, 23);
			this.sendSocket.send(sendPacket);
		}
		catch (IOException e)
		{
			System.out.println("Elevator: Error create output stream.");
		}
	}

	/**
	 * prints out the contents of a byte array
	 * @param bytes: the byte array
	 * @param len: length of the byte array
	 */
	private static void print(byte[] bytes, int len)
	{
		System.out.print("Data as bytes: ");
		for (int i=0; i<len; i++)
		{
			System.out.print(Integer.toHexString(bytes[i]));
			System.out.print(' ');
		}
		System.out.print("\n");

		System.out.print("Data as string: ");
		for (int i=0; i<len; i++)
		{
			if (bytes[i] < 32)
			{
				System.out.print((char) (bytes[i] + '0'));
			}
			else
			{
				System.out.print((char) bytes[i]);
			}
			System.out.print(' ');
		}
		System.out.print("\n\n");
	}

	/**
	 * fetch the specific task
	 * @param request: Request type request, what kind of request
	 */
	public void getRequest(Request request)
	{
		this.request = request;
		direction = this.request.getDirection();
		addFloorRequest(direction, this.request.getDest());
		error = this.request.getError();
		moveElevator();
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
	private void addFloorRequest(boolean dir, int floor)
	{
		selectedFloors.add(floor);
		Collections.sort(selectedFloors);
		if(!dir)
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
	private void delay(int d)
	{
		try
		{
			Thread.sleep(d * 3000);//delay in seconds.
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
	 * Getter for the elevator id
	 * @return the elevator id
	 */
	public int getElevatorId()
	{
		return this.id;
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
	 * 
	 */
	private void moveElevator()
	{
		//sendAndReceive();
		System.out.println("Elevator gets the request and moving from floor "+ this.currFloor + " to floor "+ selectedFloors.get(0));
		if(error != 0)
		{
			if(error == 1)
			{
				System.out.println("The door has reached maximum open time, will close in 3 seconds");
				delay(1);
				delay(Math.abs(this.currFloor - selectedFloors.get(0)));
				goToFloor(selectedFloors.get(0));
				Scheduler.elevatorFloor(this, selectedFloors.get(0));
				selectedFloors.remove(0);
				return;
			}
			else if(error == 2)
			{
				System.out.println("The elevator is stuck on floor " + this.currFloor);
				return;
			}
		}
		delay(Math.abs(this.currFloor - selectedFloors.get(0)));
		goToFloor(selectedFloors.get(0));
		Scheduler.elevatorFloor(this, selectedFloors.get(0));
		if(!errorB)
		{
			System.out.println("The elevator has moved to floor " + selectedFloors.get(0) + "\n");
		}
		selectedFloors.remove(0);
	}
	
	/**
	 * hold door open
	 * @param oc true for open, false for close
	 */
	public void holdDoor(boolean oc)
	{
		if(oc)
		{
			delay(1);
		}
	}

	/**
	 * Getter for direction
	 */
	public boolean getDIrection ()
	{
		return this.direction;
	}


	/**
	 * starts thread
	 */
	public void run()
	{
		while(true)
		{
			//this.sendAndReceive();
		}
	}

	/**
	 * getter for variable request to be used in testing
	 * @return the request received by the elevator
	 */
	public Request getTestRequest()
	{
		return request;
	}

	/**
	 * getter for variable address to be used in sending packets to the class
	 * @return the address of the elevator
	 */
	public InetAddress getAddress()
	{
		return address;
	}
}
