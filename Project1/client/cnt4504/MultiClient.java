package cnt4504;
import java.net.*;
import java.util.*;
import java.io.*;

public class MultiClient { 
 public static void main(String[] args) 
 { 
     
         /*-----------------IMPORTANT---------------------*/
	 	// Don't forget to change args length Now it's requests just a port
	 	// host name is stored in String hostname for fast debuging
	 	/*-----------------IMPORTANT---------------------*/
         if (args.length < 1) {
 			System.out.println("USAGE: java MultiClient.java <port number>");
 			return;
 		}
//       Request the network address and port to which to connect
         String hostname = "DESKTOP-DCT3DBM"; // <---- temp name for a host Change it on your computer name
         int port = Integer.parseInt(args[0]);
         
         
         
         try {
        	 ThreadGenerator a = new ThreadGenerator(hostname, port); // <---- create ThreadGenerator class 
             Scanner inputNum = new Scanner(System.in); // userInput stream

             int choice = 0; // numb for processes to do
             int client = 0; // numb of clients
             do {
            	 System.out.println("(1) Server: Date and Time\n"
  		    			+ "(2) Server: Uptime\n"
  		    			+ "(3) Server: Memory Use\n"
  		    			+ "(4) Server: Netstat\n"
  		    			+ "(5) Server: Current Users\n"
  		    			+ "(6) Server: Running Processes\n"
  		    			+ "(7) Quit\n");
            	 
            	 choice = inputNum.nextInt(); // get numb for process
            	 switch(choice) {
            	 
           		case 1:
           			// Do something for server date and time
           			System.out.println("How many clients generate? ");
           			client = inputNum.nextInt();
           			a.setNumbUsers(client); // set to the class ThreadGenerator numb of clients
           			a.generateTime(); // call generateTime method in ThreadGenerator class
           			break;
           		case 2:
           			// Do something for server uptime --> based on test output from feb 1 recording, looks like we need more?
           			System.out.println("How many clients generate? ");
           			client = inputNum.nextInt();
           			a.setNumbUsers(client);
           			a.generateUpTime();
           			
           			break;
           		case 3:
           			// Do something for memory use
           			break;
           		case 4:
           			// Do something for netstat --> not sure why this while loop isn't breaking
           			break;
           		case 5:
           			// Do something for current users
           			break;
           		case 6:
           			// Do something for running processes --> this one prints blank to client (on server prints just fine)
           			break;
           		case 7:
           			// Quit client thread
           			break;
           		default:
           			// Do something for input error
           			break;
             	 }

             }while(choice != 7);
             inputNum.close();
            
/*
So, team. I am not 100% sure how to go about this part. We have to run this process for each thread. An idea I had was putting all the users in a table and 
running through the 'choice' for all of the values in the table, but I think that that is not how you utilize multithreading. I think multithreading involves 
moving the vital processes of the function to an object (the stuff directly below), then executing the 'choice' in the thread class, not main.
-B
*/
             
/*
As for turn around time for each thread... i've researched that ping is something that may help with this, but I have no idea how to go about
implementing that. this one has me scratching my head a little right now. after we figure this out, the other data is just an extension of this
-B
*/
		 
		 

         } catch (Exception ex) {
  
             System.out.println("Server not found: " + ex.getMessage());
  
         }
         
         
     }
} 

/*-----------------IMPORTANT---------------------*/
// Usage each generator method creates a socket connection and loops threads in multithread class
// writer.println(1) contains a request number for a server
// each method pass request number and reader from a socket
/*-----------------------------------------------*/
class ThreadGenerator {
	private int numbUsers = 0;
	private String host;
	private int port;
	
	
	public ThreadGenerator (String host, int port) {
		this.host = host;
		this.port = port;
	}
	
	public void setNumbUsers(int i) {
		this.numbUsers = i;
	}
	public int getNumbUsers() {
		return this.numbUsers;
	}
	
	private Socket connectToSocket(String host, int port) {
		try{
			Socket socket = new Socket(host, port);
			return socket;
		}catch(Exception e) {
			return null;
		}
	}
	
	public void generateTime() {
		try {
			Socket socket = connectToSocket(this.host, this.port);
			InputStream input = socket.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));
			OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            
            for(int i = 0; i < this.numbUsers; i++) {
            	Multithread thread = new Multithread();
            	writer.println(1);
            	thread.run(1, reader);
            }
            
 			reader.close();
		}catch(Exception e) {
			 System.out.println("Socket error: " + e.getMessage());
		}
	}
	public void generateUpTime() {
		try {
			Socket socket = connectToSocket(this.host, this.port);
			InputStream input = socket.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));
			OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            
            for(int i = 0; i < this.numbUsers; i++) {
            	Multithread thread = new Multithread();
            	writer.println(2);
            	thread.run(2, reader);
            }
            
 			reader.close();
		}catch(Exception e) {
			 System.out.println("Socket error: " + e.getMessage());
		}
	}
	public void generateMemoryUse() {
		try {
			Socket socket = connectToSocket(this.host, this.port);
			InputStream input = socket.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));
			OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            
            for(int i = 0; i < this.numbUsers; i++) {
            	Multithread thread = new Multithread();
            	writer.println(3);
            	thread.run(3, reader);
            }
            
 			reader.close();
		}catch(Exception e) {
			 System.out.println("Socket error: " + e.getMessage());
		}
	}
	public void generateNetStat() {
		try {
			Socket socket = connectToSocket(this.host, this.port);
			InputStream input = socket.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));
			OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            
            for(int i = 0; i < this.numbUsers; i++) {
            	Multithread thread = new Multithread();
            	writer.println(4);
            	thread.run(4, reader);
            }
            
 			reader.close();
		}catch(Exception e) {
			 System.out.println("Socket error: " + e.getMessage());
		}
	}
	
	public void generateCurrentUsers() {
		try {
			Socket socket = connectToSocket(this.host, this.port);
			InputStream input = socket.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));
			OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            
            for(int i = 0; i < this.numbUsers; i++) {
            	Multithread thread = new Multithread();
            	writer.println(5);
            	thread.run(5, reader);
            }
            
 			reader.close();
		}catch(Exception e) {
			 System.out.println("Socket error: " + e.getMessage());
		}
	}
	
	public void generateRunningProcesses() {
		try {
			Socket socket = connectToSocket(this.host, this.port);
			InputStream input = socket.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));
			OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            
            for(int i = 0; i < this.numbUsers; i++) {
            	Multithread thread = new Multithread();
            	writer.println(6);
            	thread.run(6, reader);
            }
 			reader.close();
		}catch(Exception e) {
			 System.out.println("Socket error: " + e.getMessage());
		}
	}
	
	public void closeSocket() {
		
	}
}

class Multithread extends Thread 
{ 
 public void run(int choice, BufferedReader reader)
 { 
     try
     { 
    /* 
    Maybe we pass in the port & server name and do the whole setup of the client here?
    This would make sense because this way we can do all the processes for each thread
    while maintaining a group data bank, which is something that multithreading requires.
    
   If we were going to display this to many users I would think that we would put the print statements in here too,
   but I think that we do not because the project description has us choosing the amount of threads at one time
   and then (from the way im interpreting it) doing the same choice for all of them.
   
   Putting everything in here would allow us to track the time it takes for an indivual thread to run a process, which can 
   be passed back to main and used in the data collection phase of the client program.
   
   -B
    
    */
    	switch(choice) {
  		case 1:
  			// Do something for server date and time
  			String date = reader.readLine();
  			System.out.println(date + "\n");
  			break;
  		case 2:
  			// Do something for server uptime --> based on test output from feb 1 recording, looks like we need more?
  			String uptime = reader.readLine();
  			System.out.println(uptime + "\n");
  			break;
  		case 3:
  			// Do something for memory use
			String max = reader.readLine();
 			String free = reader.readLine();
 			String available = reader.readLine();
 			System.out.println(max + "\n");
 			System.out.println(free + "\n");
 			System.out.println(available + "\n");
  			break;
  		case 4:
  			// Do something for netstat --> not sure why this while loop isn't breaking
			String netstat = reader.readLine();
 			while (netstat != null) {
 				System.out.print(netstat + "\n");
 				netstat = reader.readLine();
 			}
  			break;
  		case 5:
  			// Do something for current users
			String users = reader.readLine();
 			System.out.println(users + "\n");
  			break;
  		case 6:
  			// Do something for running processes --> this one prints blank to client (on server prints just fine)
			String running = reader.readLine();
 			while (running != null) {
 				System.out.print(running + "\n");
 				netstat = reader.readLine();
 			}
  			break;
  		case 7:
  			// Quit client thread
  			break;
  		default:
  			// Do something for input error
  			break;
    	 }

    	 
    	 //reader.close();
     } 
     catch (Exception e) 
     { 
         System.out.println ("Exception: " +  e.getMessage()); 
     } 
 } 
} 
