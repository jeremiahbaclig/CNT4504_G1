package cnt4504;

import java.io.*;
import java.net.*;
import java.util.*;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

public class SingleServer {
	public static void main(String args[]) {
		if (args.length < 1) {
			System.out.println("USAGE: java SingleServer.java <port number>");
			return;
		}
		
		int port = Integer.parseInt(args[0]);
		
//		try {
//	        InetAddress myHost = InetAddress.getLocalHost();
//	        System.out.println(myHost.getHostName());
//        } catch(UnknownHostException ex) {
//        	return;
//        }
		
	    try (ServerSocket serverSocket = new ServerSocket(port)) {
		 
		    System.out.println("Server is listening on port " + port);
		    System.out.println("Press ctrl+C to exit.");
		 
		    while (true) {
		    	Socket socket = serverSocket.accept();
		    	
		    	// read data from client
		    	InputStream input = socket.getInputStream();
		    	BufferedReader reader = new BufferedReader(new InputStreamReader(input));
		    	
		    	// send data to client
		        OutputStream output = socket.getOutputStream();
		        PrintWriter writer = new PrintWriter(output, true);

		        
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
		    		case 51:
		    			// Do something for memory use (3)
		    			break;
		    		case 52:
		    			// Do something for netstat (4)
		    			break;
		    		case 53:
		    			// Do something for current users (5)
		    			break;
		    		case 54:
		    			// Do something for running processes (6)
		    			break;
		    		case 55:
		    			// Quit client thread (7)
		    			socket.close();
		    			break;
		    		default:
		    			// Do something for input error
		    			break;	
		        	}
                } while (choice != 7);
	            
	            socket.close();
	        }
		 
		} catch (IOException ex) {
	        System.out.println("Server exception: " + ex.getMessage());
	        ex.printStackTrace();
	    }
	}
}
