/*SocketMain2.java
 * 
 * Version:
 *   $hkak$
 *   
 * Revision:
 *   $log$
 */

/**
 *This program contains main method for the client 
 *
 * @author Harshal Khandare
 * @author Ajinkya Kale
 *
 */

import java.io.*;
import java.net.*;
import java.util.*;

/**
 * this class contain main program
 */

public class SocketMain2 {
	
	/**
	 * this is main program
	 * @param  args  commandline arguments
	 */
	
	public static void main(String args[]) throws IOException{
		SocketModel model=new SocketModel(); // object of model
		SocketView view=new SocketView(model); // object of view
		SocketController controller=new SocketController(view,model);
		SocketInteract2 interact2=new SocketInteract2(controller,view
								,args);
		interact2.begin(args);	// starts socket connection for client
	}
}
