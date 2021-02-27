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
         String hostname = "139.62.210.153"; // <---- temp name for a host Change it on your computer name
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
				System.out.println("How many clients generate? ");
           			client = inputNum.nextInt();
           			a.setNumbUsers(client);
           			a.generateMemoryUse();
           			break;
           		case 4:
           			// Do something for netstat --> not sure why this while loop isn't breaking
           			System.out.println("How many clients generate? ");
           			client = inputNum.nextInt();
           			a.setNumbUsers(client);
           			a.generateNetStat();
           			break;
           		case 5:
           			// Do something for current users
           			System.out.println("How many clients generate? ");
           			client = inputNum.nextInt();
           			a.setNumbUsers(client);
           			a.generateCurrentUsers();
           			break;
           		case 6:
           			// Do something for running processes --> this one prints blank to client (on server prints just fine)
           			System.out.println("How many clients generate? ");
           			client = inputNum.nextInt();
           			a.setNumbUsers(client);
           			a.generateRunningProcesses();
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
	private Socket socket = null;
	private InputStream input = null;
	private BufferedReader reader = null;
	private OutputStream output = null;
	private PrintWriter writer = null;
	
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
	
	private void initializeSocket() {
		try {
			 this.socket = connectToSocket(this.host, this.port);
			 this.input = this.socket.getInputStream();
			 this.reader = new BufferedReader(new InputStreamReader(this.input));
			 this.output = this.socket.getOutputStream();
			 this.writer = new PrintWriter(this.output, true);
		} catch (Exception e) {
			System.out.println("Socket error.");
		}
	}
	
	public void generateTime() {
		try {
			if(this.socket == null) {
				initializeSocket();
			}
            
            for(int i = 0; i < this.numbUsers; i++) {
            	Multithread thread = new Multithread();
            	this.writer.println("1");
            	thread.run(1, this.reader);
            }

		}catch(Exception e) {
			 System.out.println("Socket error: " + e.getMessage());
		}
	}
	public void generateUpTime() {
		try {
			if(this.socket == null) {
				initializeSocket();
			}
            
            for(int i = 0; i < this.numbUsers; i++) {
            	Multithread thread = new Multithread();
            	this.writer.println("2");
            	thread.run(2, this.reader);
            }

		}catch(Exception e) {
			 System.out.println("Socket error: " + e.getMessage());
		}
	}
	public void generateMemoryUse() {
		try {
			if(this.socket == null) {
				initializeSocket();
			}
            
            for(int i = 0; i < this.numbUsers; i++) {
            	Multithread thread = new Multithread();
            	this.writer.println("3");
            	thread.run(3, this.reader);
            }

		}catch(Exception e) {
			 System.out.println("Socket error: " + e.getMessage());
		}
	}
	public void generateNetStat() {
		try {
			if(this.socket == null) {
				initializeSocket();
			}
            
            for(int i = 0; i < this.numbUsers; i++) {
            	Multithread thread = new Multithread();
            	this.writer.println("4");
            	thread.run(4, this.reader);
            }

		}catch(Exception e) {
			 System.out.println("Socket error: " + e.getMessage());
		}
	}
	
	public void generateCurrentUsers() {
		try {
			if(this.socket == null) {
				initializeSocket();
			}
            
            for(int i = 0; i < this.numbUsers; i++) {
            	Multithread thread = new Multithread();
            	this.writer.println("5");
            	thread.run(5, this.reader);
            }

		}catch(Exception e) {
			 System.out.println("Socket error: " + e.getMessage());
		}
	}
	
	public void generateRunningProcesses() {
		try {
			if(this.socket == null) {
				initializeSocket();
			}
            
            for(int i = 0; i < this.numbUsers; i++) {
            	Multithread thread = new Multithread();
            	this.writer.println("6");
            	thread.run(6, this.reader);
            }
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
    	switch(choice) {
  		case 1: // Do something for server date and time
  			String date = reader.readLine();
  			System.out.println(date + "\n");
  			Thread.sleep(1000);
  			break;
  		case 2: // Do something for server uptime
  			String uptime = reader.readLine();
  			System.out.println(uptime + "\n");
  			Thread.sleep(1000);
  			break;
  		case 3: // Do something for memory use
			String max = reader.readLine();
 			String free = reader.readLine();
 			String available = reader.readLine();
 			System.out.println(max + "\n");
 			System.out.println(free + "\n");
 			System.out.println(available + "\n");
 			Thread.sleep(1000);
 			break;
  		case 4: // Do something for netstat
			String netstat = reader.readLine();
			int counter = 0;
 			while (netstat != null && counter < 20) {
 				System.out.print(netstat + "\n");
 				netstat = reader.readLine();
 				counter++;
 			}
 			Thread.sleep(1000);
 			break;
  		case 5: // Do something for current users
			String users = reader.readLine();
 			System.out.println(users + "\n");
 			Thread.sleep(1000);
 			break;
  		case 6: // Do something for running processes
			String running = reader.readLine();
			int count = 0;
 			while (running != null && count < 20) {
 				System.out.print(running + "\n");
 				running = reader.readLine();
 				count++;
 			}
 			Thread.sleep(1000);
 			break;
  		case 7:
  			// Quit client thread
  			break;
  		default:
  			// Do something for input error
  			break;
    	 }
     } 
     catch (Exception e) 
     { 
         System.out.println ("Exception: " +  e.getMessage()); 
     } 
 } 
} 
