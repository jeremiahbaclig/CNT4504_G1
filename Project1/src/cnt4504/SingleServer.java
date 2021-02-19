package cnt4504;

import java.io.*;
import java.net.*;
import java.util.*;

public class SingleServer {
	public static void main(String args[]) {
		if (args.length < 1) 
			System.out.println("USAGE: java SingleServer.java <port number>");
		
		int port = Integer.parseInt(args[0]);
		
	    try (ServerSocket serverSocket = new ServerSocket(port)) {
		 
		    System.out.println("Server is listening on port " + port);
		    System.out.println("Press ctrl+C to exit.");
		 
		    while (true) {
		    	Socket socket = serverSocket.accept();
		    	
		    	InputStream input = socket.getInputStream();
		    	BufferedReader reader = new BufferedReader(new InputStreamReader(input));
		    	
		        OutputStream output = socket.getOutputStream();
		        PrintWriter writer = new PrintWriter(output, true);
		 
		        String text;
		        do {
                    text = reader.readLine();
                    // do things with text based on client input
                    // String reverseText = new StringBuilder(text).reverse().toString();
                    // writer.println("Server: " + reverseText);
                    
                } while (!text.equals("7"));
	            
	            socket.close();
	        }
		 
		} catch (IOException ex) {
	        System.out.println("Server exception: " + ex.getMessage());
	        ex.printStackTrace();
	    }
	}
}
