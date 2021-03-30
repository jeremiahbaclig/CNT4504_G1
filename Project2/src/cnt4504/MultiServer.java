package cnt4504;
import java.io.*;
import java.net.*;
import java.util.*;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

public class MultiServer {
	public static void main(String args[]) {
		if (args.length < 1) {
			System.out.println("USAGE: java MultiServer.java <port number>");
			return;
		}
		
		int port = Integer.parseInt(args[0]);
		
	    try (ServerSocket serverSocket = new ServerSocket(port)) {
		 
		    System.out.println("Server is listening on port " + port);
		    System.out.println("Press ctrl+C to exit.");
		 
		    while (true) {
		    	Socket socket = serverSocket.accept();
		    	System.out.println("Client connected...");
		    	
			new ServerThread(socket).start();
			    
		    	// read data from client
		    	InputStream input = socket.getInputStream();
		    	BufferedReader reader = new BufferedReader(new InputStreamReader(input));
		    	
		    	// send data to client
		        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
		        
		        int choice = 0;
		        do {
			        choice = reader.read();
		        	switch(choice) {
			    		case 49: // print server date and time (1)
			    			writer.println(new Date().toString());
			    			break;
			    		case 50: // print server up time (2)
			    			RuntimeMXBean rb = ManagementFactory.getRuntimeMXBean();
			    			writer.println("Uptime in ms: " + rb.getUptime());
			    			break;
			    		case 51: // Do something for memory use (3)
			    			Runtime.getRuntime().gc(); // garbage collection
			    			long maxMem = Runtime.getRuntime().maxMemory() / 1024;
			    			long freeMem = Runtime.getRuntime().freeMemory() / 1024;
			    			long availableMem = maxMem - freeMem;
			    			
			    			writer.println("Max Memory(KB): " + maxMem);
			    			writer.println("Free Memory(KB): " + freeMem);
			    			writer.println("Available Memory(KB): " + availableMem);	
			    			break;
			    		case 52: // Do something for netstat (4)
			    			BufferedReader bufferedReader = new BufferedReader(
			    		              new InputStreamReader(Runtime.getRuntime().exec("netstat -a").getInputStream()));
			    			String line;
			    			StringBuilder log = new StringBuilder();
	
			    			int counter = 0;
			    		    while ((line = bufferedReader.readLine()) != null && counter < 20) {
			    		    	System.out.println(line);
			    		    	log.append(line + "\n");
			    		    	counter++;
			    		    }
			    		    writer.println(log.toString());
			    		    
			    			break;
			    		case 53: // Do something for current users (5)
			    			BufferedReader userReader = new BufferedReader(
			    		              new InputStreamReader(Runtime.getRuntime().exec("whoami").getInputStream())); 
			    			String users;
			    			StringBuilder logUsers = new StringBuilder();
	
			    		    while ((users = userReader.readLine()) != null) {
			    		    	logUsers.append(users + "\n");
			    		    	
			    		    }
			    		    writer.println(logUsers.toString());
			    			break;
			    		case 54: // Do something for running processes (6)
			    			BufferedReader runningReader = new BufferedReader(
			    		              new InputStreamReader(Runtime.getRuntime().exec("ps -aux").getInputStream()));
			    			String running;
			    			StringBuilder logRunning = new StringBuilder();
	
			    			int count = 0;
			    		    while ((running = runningReader.readLine()) != null && count < 20) {
			    		    	logRunning.append(running + "\n");
			    		    	count++;
			    		    }
			    		    writer.println(logRunning.toString());
			    		    break;
			    		case 55: // Quit client thread (7)
			    			socket.close();
			    			break;
			    		default: // Do something for input error
			    			break;	
		        	}
                } while (choice != 7);
	        }
		 
		} catch (SocketException ex) {
			System.out.println("Client connection closed. Ending...");
	    } 
	    catch (IOException ex) {
	        System.out.println("Server exception: " + ex.getMessage());
	        ex.printStackTrace();
	    } catch (Exception ex) {
	    	System.out.println("ERROR: " + ex.getMessage());
	    }
	}
}
