package cnt4504;
import java.net.*;
import java.util.*;
import java.io.*;

public class MultiClient 
{ 
 public static void main(String[] args) 
 { 
     
         
         if (args.length < 2) {
 			System.out.println("USAGE: java MultiClient.java <host name> <port number>");
 			return;
 		}
//       Request the network address and port to which to connect
         String hostname = args[0];
         int port = Integer.parseInt(args[1]);
  
         try (Socket socket = new Socket(hostname, port)) {
  
         	InputStream input = socket.getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(input));
  
//             String time = reader.readLine();
//             System.out.println(time);

             OutputStream output = socket.getOutputStream();
 	        PrintWriter writer = new PrintWriter(output, true);
             Scanner inputNum = new Scanner(System.in);
           
  
             int choice = 0;
             int clients = 0;
             do {
//         	 Request the operation to request
 	            System.out.println("(1) Server: Date and Time\n"
 		    			+ "(2) Server: Uptime\n"
 		    			+ "(3) Server: Memory Use\n"
 		    			+ "(4) Server: Netstat\n"
 		    			+ "(5) Server: Current Users\n"
 		    			+ "(6) Server: Running Processes\n"
 		    			+ "(7) Quit\n");
 		    	
 		    	choice = inputNum.nextInt();
 		    	writer.println(choice);
 		    	
// 	           Request how many client requests to generate (1, 5, 10, 15, 20 and 25) ((Do request for each client)) 
 		       System.out.println("How many clients would you like to generate?");
 		       clients = inputNum.nextInt();
		       
		    	
		  
 		    	int i;
				for (i=0; i < clients; i++) {
					Multithread object = new Multithread(); 
					object.start(); 
				}
 		    	
 		    	
/*
So, team. I am not 100% sure how to go about this part. We have to run this process for each thread. An idea I had was putting all the users in a table and 
running through the 'choice' for all of the values in the table, but I think that that is not how you utilize multithreading. I think multithreading involves 
moving the vital processes of the function to an object (the stuff directly below), then executing the 'choice' in the thread class, not main.
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
             } while(choice != 7);
  

/*
As for turn around time for each thread... i've researched that ping is something that may help with this, but I have no idea how to go about
implementing that. this one has me scratching my head a little right now. after we figure this out, the other data is just an extension of this
-B
*/
		 
		 
//             Turn-around Time (elapsed time) for each client request
             
             
             
             
             
//		Other datas             
             
             
 	    	inputNum.close();
 	    	
         } catch (UnknownHostException ex) {
  
             System.out.println("Server not found: " + ex.getMessage());
  
         } catch (IOException ex) {
  
             System.out.println("I/O error: " + ex.getMessage());
         }
         
         
     }
     
     
     
     
     
     
     
     
     
     
 } 











class Multithread extends Thread 
{ 
 public void run() 
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
    	 System.out.println ("Thread " + 
                 Thread.currentThread().getId() + 
                 " is running");
		 
	
    	   	 

     } 
     catch (Exception e) 
     { 
         System.out.println ("Exception"); 
     } 
 } 
} 
