import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * ElevatorSystemGUI - This class displays a menu for the elevator system. 
 * @author Simon Yacoub
 * @Version 7/04/2020
 * 
 *
 */
public class ElevatorSystemGUI extends JFrame implements ActionListener{

	private static final int GRID_SZ = 2;

	/**
	 * Constructor for the ElevatorSystemGUI. Creates a menu with four buttons in a grid layout:
	 * [1][2]
	 * [3][4]
	 */
	
	public ElevatorSystemGUI() {
		super("Elevator System");
		this.setLayout(new GridLayout(GRID_SZ,GRID_SZ));

		JButton floorSetupButton = new JButton("Floors Set Up");
		this.add(floorSetupButton);
		floorSetupButton.addActionListener(this);
		
		JButton seButton = new JButton("Scheduler and Elevators Set Up");
		this.add(seButton);
		seButton.addActionListener(this);
		
		JButton simSEbutton = new JButton("Simulate Scheduler and Elevator response");
		this.add(simSEbutton);
		simSEbutton.addActionListener(this);
		
		JButton simFloorsButton = new JButton("Simulate Floor Requests");
		this.add(simFloorsButton);
		simFloorsButton.addActionListener(this);
		
		this.setSize(800, 800);
		this.setVisible(true);
	}

	/**
	 * handles ActionEvents using handleAction(String action) method.
	 * @param ActionEvent e the action event 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//converts button presses to String using e.getActionCommand();
		String action = e.getActionCommand();
		handleAction(action);
		
	}

	/**
	 * handleAction handles a button press performed on the gui.
	 * @param action The action performed 
	 */
	private void handleAction(String action) {
		
		
		/*
		 * Simulate Floor Requests: This runs the building class which sends requests to the scheduler 
		 */
		if(action.equals("Simulate Floor Requests")) {
			Thread buildingThread = new Thread(new Building(10));
			buildingThread.run();
			
		/*
		 * Floors Setup: This button is used to serialize the InetAddress of the localHost that the floors are 
		 * being hosted on. 
		 */
		}else if(action.equals("Floors Set Up")) {
			Serializer.serializeInetAddress("FloorsAddress.txt");
			System.out.print("FloorsAddress serilaized");
			
		/*
		 * Scheduler and Elevators Set Up: This button is used to serialize the InetAddress of the localHost that
		 * the Scheduler and Elevator are being hosted on.
		 */
		}else if(action.equals("Scheduler and Elevators Set Up")) {
			Serializer.serializeInetAddress("SchedulerElevatorAddress.txt");
			System.out.print("SchedulerElevatorAddress serilaized");
		
		/*
		 * Simulate Scheduler and Elevator response:This runs the scheduler class which receives and handles
		 * requests from the floors.
		 */
		}else if(action.contentEquals("Simulate Scheduler and Elevator response")){
			ElevatorSystem elevator = new ElevatorSystem(0);
			List<ElevatorSystem> elevators = new ArrayList<ElevatorSystem>();
			elevators.add(elevator);
			Scheduler scheduler = new Scheduler(elevators);
			scheduler.run();
		}
	}
	
	/**
	 * Main method for ElevatorSystem. 
	 * @param args
	 */
	public static void main(String[] args) {
		new ElevatorSystemGUI();
	}

}
