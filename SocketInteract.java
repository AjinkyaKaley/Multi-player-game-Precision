
/*SocketInteract.java
 * 
 * Version:
 *   $hkak$
 *   
 * Revision:
 *   $log$
 */

/**
 * This program interacts with the client
 * @author Harshal Khandare
 * @author Ajinkya Kale
 *
 */

import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 * this class provieds methods for interaction between server and client
 */

public class SocketInteract extends Thread {
	int portNumber;
	int counter=0;
	String servermove,clientmessage,randomnum;
	Scanner sc=new Scanner(System.in);
	ServerSocket serverSocket;
	SocketController controller;
	SocketView view;

	/**
	 * this is contructor 
	 * @param  controller object of socketcontroller
	 * @param  view       object of socketview
	 */

	public SocketInteract(SocketController controller,SocketView view
			,String args[]){
		this.controller=controller;
		this.view=view;
		view.randomNumSet("");
		view.setDialog("Let player 1 join");
		view.dialogvisible(true);
		controller.allow=false;
	}

	/**
	 * this method begins to interact with the MVC.
	 * @param  args   commandline argument
	 */

	public void begin(String args[]) throws IOException{
		try{
			if (args.length != 1) {
				System.err.println("Please input port number");
				System.exit(1);
			}
			portNumber = Integer.parseInt(args[0]);
			ServerSocket serverSocket=new ServerSocket(portNumber);
			Socket clientSocket = serverSocket.accept();  //accept 
								  //connection
			PrintWriter out =
			new PrintWriter(clientSocket.getOutputStream(), true);                 
			BufferedReader in = new BufferedReader(
			new InputStreamReader(clientSocket.getInputStream()));
			randomnum=in.readLine(); //reading client random number
			view.randomNumSet(randomnum);
			controller.setServerRandom(randomnum);
			view.dialogvisible(false);

			while (true) {
				clientmessage=in.readLine();  //reading 
							      //client message
				int message=Integer.parseInt(clientmessage);
				controller.emulateButtonListener(message);
				counter++;
				if(counter==11){  //close socket when game ends
					in.close();
					out.close();
					serverSocket.close();
					break;
				}
				int check=100;
				while(check>10){	//waiting for other 
							//players move
				    check=controller.check;
				    servermove=new Integer(check).toString();
				}
				controller.check=100;
				out.println(servermove);    //writing its move 
							    //to client
				counter++;
				if(counter==11){  //close socket when game ends
					in.close();
					out.close();
					serverSocket.close();
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
