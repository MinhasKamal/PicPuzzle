/************************************************************************************************************
 * Developer: Minhas Kamal (IIT, DU, BSSE-0509)																*
 * Date: 03-Dec-2013 																						*
 * Total Lines: almost 2700																					*
 * Comment: This is where the game starts running.															*
*************************************************************************************************************/

package run.operation;

import mainFrame.gui.*;


public class MainApp {
	public static void main(String args[]) {
		/**///##using NIMBUS##///**/
		try{	
			javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		}catch(Exception ex){
			//do nothing
		}
		
		Frame frame = new Frame(1);
    	frame.setVisible(true);
    	frame.setJCBItemPic1Selected();
	}
}


