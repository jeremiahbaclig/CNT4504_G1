package cnt4504;
import java.net.*;
import java.util.Scanner;
import java.io.*;

public class MultiClient {
	public static void main(String args[]) {
		if (args.length < 2) return;
		 
        String hostname = args[0];
        int port = Integer.parseInt(args[1]);
 
        try (Socket socket = new Socket(hostname, port)) {
 
            // InputStream input = socket.getInputStream();
            // BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            
            Scanner inputNum = new Scanner(System.in);
 
            int choice = 0;
            do {
	            System.out.println("(1) Server: Date and Time\n"
		    			+ "(2) Server: Uptime\n"
		    			+ "(3) Server: Memory Use\n"
		    			+ "(4) Server: Netstat\n"
		    			+ "(5) Server: Current Users\n"
		    			+ "(6) Server: Running Processes\n"
		    			+ "(7) Quit\n");
		    	
		    	choice = inputNum.nextInt();
		    	
		    	switch(choice) {
		    		case 1:
		    			// Do something for server date and time
		    			break;
		    		case 2:
		    			// Do something for server date and time
		    			break;
		    		case 3:
		    			// Do something for memory use
		    			break;
		    		case 4:
		    			// Do something for netstat
		    			break;
		    		case 5:
		    			// Do something for current users
		    			break;
		    		case 6:
		    			// Do something for running processes
		    			break;
		    		case 7:
		    			// Quit client thread
		    			break;
		    		default:
		    			// Do something for input error
		    			break;
		    			
		    	}
            } while(choice != 7);
 
	    	inputNum.close();
        } catch (UnknownHostException ex) {
 
            System.out.println("Server not found: " + ex.getMessage());
 
        } catch (IOException ex) {
 
            System.out.println("I/O error: " + ex.getMessage());
        }
    }
	
}

