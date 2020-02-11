import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CSVParser {

	private String fileName; 
	private BufferedReader br;
	private final String CSVStringSplit = ",";
	private final String UPButton = "UP";
	private final String downButton = "DOWN";
	private ArrayList<Request> requests; 
	
	public CSVParser (String fileName) {
		this.fileName = fileName;
		this.requests = new ArrayList<Request>();
		readFile();
		
	}
	
	/*
	 * Read lines from CSV file and then parse them into request objects 
	 */
	private void readFile() {
		String line, time;
		boolean isUp = false;
		int sourceFloor, destFloor;
		try {
			File myFile = new File("./src/" + this.fileName);
			br = new BufferedReader(new FileReader(myFile));
			br.readLine(); // skip the first line
			while ((line = br.readLine()) != null) {
				String[] info = line.split(CSVStringSplit);
				time = info[0];
				destFloor = Integer.valueOf(info[3]);
				sourceFloor = Integer.valueOf(info[1]);
				if (info[2].toUpperCase().equals(this.UPButton)) {
					isUp = true;
				} else if (info[2].toUpperCase().equals(this.downButton)) {
					isUp = false;
				}
				Request request = new Request(sourceFloor,isUp, destFloor, time);
				this.requests.add(request);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<Request> getRequests () {
		return this.requests;
	}
}
