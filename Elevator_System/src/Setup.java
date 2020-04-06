import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;

public class Setup {
	
	public static void serializeInetAddress(String fileName) {
        try
        {    
            FileOutputStream file = new FileOutputStream(fileName); 
            ObjectOutputStream out = new ObjectOutputStream(file); 
              
            // Method for serialization of object 
            out.writeObject(InetAddress.getLocalHost()); 
              
            out.close(); 
            file.close();  
  
        }catch(IOException ex) 
        { 
            System.out.println("IOException is caught"); 
        } 
  
	}

}
