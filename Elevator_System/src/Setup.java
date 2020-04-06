import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
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
	
	public static InetAddress deserializeInetAddress(String fileName) {
		InetAddress address = null;
        try
        {    
            FileInputStream file = new FileInputStream(fileName); 
            ObjectInputStream in = new ObjectInputStream(file); 
              
            // Method for serialization of object 
            address = (InetAddress) in.readObject(); 
              
            in.close(); 
            file.close();  
  
        }catch(IOException ex) 
        { 
            System.out.println("IOException is caught"); 
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        return address;
	}

}
