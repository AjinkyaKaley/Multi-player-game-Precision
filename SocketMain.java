/*SocketMain.java
 * 
 * Version:
 *   $hkak$
 *   
 * Revision:
 *   $log$
 */

/**
 *This program contains main method for the server 
 *
 * @author Harshal Khandare
 * @author Ajinkya Kale
 *
 */

import java.io.*;
import java.net.*;
import java.util.*;

/**
 * this class contains main method
 */

public class SocketMain {
	
	/**
	 * this is main program
	 * @param  args  commandline arguments
	 */
	
	public static void main(String args[]) throws IOException{
		SocketModel model=new SocketModel(); // object of model
		SocketView view=new SocketView(model); // object if view
		SocketController controller=new SocketController(view,model);
		SocketInteract interact=new SocketInteract(controller,view
								     ,args);
		interact.begin(args);  // starts the socket connection
	}
}

