/*SocketController.java
 * 
 * Version:
 *   $hkak$
 *   
 * Revision:
 *   $log$
 */

/**
 * This program implements the socket programming concepts 
 * for the game called precision, implemented in previous
 * homework(hw12)
 * 
 * @author Harshal Khandare
 * @author Ajinkya Kale
 *
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class contains the actionlistener, that controls
 * the logic and view of the game
 * 
 */

public class SocketController {
	private SocketView view;  // class variable  
	private SocketModel model;
	int check=100;  
	boolean allow=true; // boolean variable for freezing buttons

	/**
	 * constructor for SocketController
	 * @param  view     initializing the object
	 * @param  model    initializing the object
	 * 
	 */
	
	SocketController(SocketView view,SocketModel model){
		this.view=view;
		this.model=model;
		for ( int index = -5; index <=5; index ++ )
			view.buttonListener(new ButtonListener(),index+5);
		//view.restartListener(new RestartListener());
	}

	/**
	 * this method listens to event action of other player
	 * @param  no  number that is input from other player
	 * 
	 */
	
	public void emulateButtonListener(int no){
		model.action(no); //perform action on click of other player
		view.labelSet(model.getlabel());
		view.label_2Set(model.getlabel_2());
		view.statusSet(model.getstatus());
		view.disableButton(no+5);
		view.dialogvisible(false);
		allow=true;
	}

	/**
	 * This class implements ActionListner, it implements the 
	 * mechanisim to percive the input from the player.
	 * 
	 */
	
	class ButtonListener implements ActionListener{
		
		/**
		 * this method is executes when the action is performed
		 * from the user.
		 * @param  e  input as the action performed.
		 */
		
		public void actionPerformed(ActionEvent e) {
			if(allow){
				int no=Integer.parseInt(e.getActionCommand());
				check=no;
				model.action(no); //perform action on click 
				view.labelSet(model.getlabel()); // sets lable
				view.label_2Set(model.getlabel_2());
				view.statusSet(model.getstatus()); //set status
				view.disableButton(no+5);
				//displays dialog box
				view.setDialog("Please wait for other player");
				view.dialogvisible(true);
				allow=false;
			}
		}
	}

	/**
	 * this method calls the randomnumset method
	 * @param   no   random number to be set
	 */
	
	public void random(String no){
		view.randomNumSet(no);
	}

	/**
	 * this method updates the random num in model of the server
	 * @param   no   random number to be set
	 */
	
	public void setServerRandom(String no){
		model.rand_num=Integer.parseInt(no);
	}
}
