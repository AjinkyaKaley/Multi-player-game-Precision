/*SocketInteract2.java
 * 
 * Version:
 *   $hkak$
 *   
 * Revision:
 *   $log$
 */

/**
 *This program interacts with the server
 * @author Harshal Khandare
 * @author Ajinkya Kale
 *
 */

import java.io.*;
import java.net.*;
import java.util.*;

/**
 * this class provieds methods for interaction between client and server
 * this is the client side of it
 */

public class SocketInteract2 {
	String clientmove,servermessage,sendrandomnum;
	SocketController controller;
	SocketView view;
	String hostName;
	int portNumber;
	int counter=0;

	/**
	 * this is contructor and sets random no for server
	 * @param  controller object of socketcontroller
	 * @param  view       object of socketview
	 */
	
	SocketInteract2(SocketController controller,SocketView view
			, String args[]){
		this.controller=controller;
		this.view=view;
		sendrandomnum=view.getRandomNum();
	}

	/**
	 * this method begins to interact with the MVC and server.
	 * @param  args   commandline argument
	 */
	
	public void begin(String args[]) throws IOException {
		try{
			if (args.length != 2) {
				System.err.println("Please input hostname" 
							+"and portnumber");
				System.exit(1);
			}
			hostName = args[0];  // hostname
			portNumber = Integer.parseInt(args[1]); // port number
			// object of socket
			Socket clientSocket = new Socket(hostName, portNumber);
			// to write stream
			PrintWriter out =
			new PrintWriter(clientSocket.getOutputStream(), true);
			// to read stream
			BufferedReader in =
			new BufferedReader(
			new InputStreamReader(clientSocket.getInputStream()));
			out.println(sendrandomnum);	//send clients random 
							//number to server

			while (true) {
				int check=100;
				while(check>10){	//waiting for other 
							//players move
				    check=controller.check;
				    clientmove=new Integer(check).toString();
				}
				controller.check=100;
				out.println(clientmove);
				counter++;
				if(counter==11){  //close socket when game ends
					in.close();
					out.close();
					clientSocket.close();
					break;
				}
				servermessage=in.readLine();  //reading 
							      //server message
				int message=Integer.parseInt(servermessage);
				controller.emulateButtonListener(message);
				counter++;
				if(counter==11){  //close socket when game ends
					in.close();
					out.close();
					clientSocket.close();
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
