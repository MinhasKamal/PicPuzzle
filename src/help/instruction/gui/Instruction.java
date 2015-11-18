/****************************************************************************************************************************
* Developer: Minhas Kamal(BSSE-0509, IIT, DU)																				*
* Date: 21-Dec-2013; Modified: 14-Jul-2014																					*
* Comment: This will make a window that will show a manual of how the software should be used.							 	*									
****************************************************************************************************************************/

package help.instruction.gui;


import java.awt.event.ActionEvent;
import javax.swing.*;

@SuppressWarnings("serial")
public class Instruction extends JFrame {
	//**
	// Variable Declaration 																				#*******D*******#
	//**
	private JPanel jPanelMain;
	private JScrollPane jScrollPane;
	private JTextArea jTextAreaNotification;
	private JButton jButtonOK;
	
	//operational variables
	private String notification;
	// End of Variable Declaration 																			#_______D_______#

	/***##Constructor##***/
	public Instruction() {
		//write your message here
		this.notification="It is very easy to play the game-\n\n" +
			"#  Press 'ENTER' to start the game.\n" +
			"#  Use 'AERROW' keys to move tiles up, down, left & right.\n" +
			"#  One may press 'P' to pause the game.\n" +
			"#  There is also a list of top 5 scorers. " +
			"You  can add  your name there by beating record holder players.\n" +
			"#  You may also clear off the  records (press 'OK' 3 times).\n" +
			"#  There are a number of pictures to play with. You may select them from the picture menu.\n" +
			"#  There are also options like- restart the game, see developer's profile & high scores.\n" +
			"#  The most interesting feature of the game is an option- \"Solve Picture\" this option calls an AI to" +
			" solve the" +
			" picture step by step. It is actually a helping tool that teaches you how to solve a puzzel. \n" +
			"\n\n\n@  Wish you enjoy the game (if you are smart!).";
		
		initialComponent();
	}
	
	public Instruction(String notification) {
		this.notification = notification;
		
		initialComponent();
	}

	
	/**
	 * Method for Initializing all the GUI variables, placing them all to specific space on the frame and adding action
	 * listener to them. Also specifies criteria of the main frame.
	 */
	private void initialComponent() {
		//**
		// Initialization 																					#*******I*******#
		//**
		jPanelMain=new JPanel();
		jScrollPane=new JScrollPane();
		jTextAreaNotification=new JTextArea();
		jButtonOK = new JButton();
		// End of Initialization																			#_______I_______#

		//**
		// Setting Bounds and Attributes of the Elements 													#*******S*******#
		//**
		jPanelMain.setBackground(new java.awt.Color(255, 255, 205));
        jPanelMain.setBounds(0, 0, 350, 350);
        jPanelMain.setLayout(null);
		
		jScrollPane.setViewportView(jTextAreaNotification);
		jScrollPane.setBounds(10, 10, 330, 330);
		jScrollPane.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
		jScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		jTextAreaNotification.setText(notification);
		jTextAreaNotification.setForeground(new java.awt.Color(102, 53, 0));
		jTextAreaNotification.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		jTextAreaNotification.setEditable(false);
		jTextAreaNotification.setCaretPosition(0);
		jTextAreaNotification.setLineWrap(true);
		jTextAreaNotification.setWrapStyleWord(true);
        
        jButtonOK.setText("OK");
        jButtonOK.setBounds(280, 360, 60, 30);
        jButtonOK.setBackground(new java.awt.Color(255, 255, 205));
        jButtonOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOKActionPerformed(evt);
            }
        });
        jButtonOK.getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).
	    	put(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ENTER,0), "ENTER_pressed");
	    jButtonOK.getActionMap().put("ENTER_pressed", new AbstractAction() {
	        public void actionPerformed(java.awt.event.ActionEvent evt) {
	        	jButtonOKActionPerformed(evt);
	        }
	    });
		// End of Setting Bounds and Attributes 															#_______S_______#

		//**
		// Adding Components 																				#*******A*******#
		//**
        jPanelMain.add(jScrollPane); 
		// End of Adding Components 																		#_______A_______#

        
		//**Setting Criterion of the Frame**//
		setIconImage(new ImageIcon(getClass().getResource("/help/instruction/pictures/IconInstruction.png")).getImage());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Instruction");
		setBounds(200, 150, 355, 430);
		setLayout(null);
		setResizable(false);
		add(jPanelMain); add(jButtonOK);
		setFocusable(true);
		setAlwaysOnTop(true);
	}

	//**
	// Action Events 																						#********AE*******#
	//**
	private void jButtonOKActionPerformed(ActionEvent evt){	
		dispose();
	}
	// End of Action Events 																				#________AE_______#

	/********* Main Method *********/
	public static void main(String args[]) {
		/*// Set the NIMBUS look and feel //*/
		try {
			javax.swing.UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception ex) {
			// do nothing if operation is unsuccessful
		}

		/* Create and display the form */
		Instruction gui = new Instruction();
		gui.setVisible(true);
	}

	//**
	// Auxiliary Methods 																					#********AM*******#
	//**

	// End of Auxiliary Methods 																			#________AM_______#
}
