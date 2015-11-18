/****************************************************************************************************************************
* Developer: Minhas Kamal(BSSE-0509, IIT, DU)																				*
* Date: 01-Jan-2014; Modified: 14-Jul-2014																					*
* Comment: Shows necessary information over the software, like- weakness, developer's feeling, background etc				*									
*****************************************************************************************************************************/

package help.about.gui;

import java.awt.event.ActionEvent;
import javax.swing.*;


@SuppressWarnings("serial")
public class About extends JFrame {
	//**
	// Variable Declaration 																				#*******D*******#
	//**
	private JLabel jLabelMain;
	private JScrollPane jScrollPane;
	private JTextArea jTextAreaNotification;
	private JButton jButtonOK;

	//operational variable
	private String data;
	// End of Variable Declaration 																			#_______D_______#

	/***##Constructor##***/
	public About() {
		//write your message here
		data="@ This program is open for all. One can modify or change criterias of this program. \n\n" +
				"It took about one month to develop the core part of the game. The later extentions are created " +
				"in defferent phases seperately. It has become almost 6 months I started this project. " +
				"I am working continuously to improve its functionality. As a result " +
				"though I started it with a small number of criterias but at last it grew up as " +
				"a complex program. The program contains 13 classes, " +
				"about 2800 lines of code, 204 images & 12 sounds. Now this program is in its sixth stage " +
				"of development. Now I am working with three other projects which are even larger." +
				"\n\nI am really thank full to my teachers, friends and also to my cousine for encouraging and " +
				"advising me." +
				"\n\nOne may not find the program too great but as a java student & naive programmer " +
				"may be this was the best I could do with my limited knowledge. \n\n**Thanks**\n\n" +
				"                                                                                   -Developer\n" +
				"                                                                                   7-Jun-2014";

		
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
		jLabelMain=new JLabel();
		jScrollPane = new JScrollPane();
		jTextAreaNotification = new JTextArea();
		jButtonOK = new JButton();
		// End of Initialization																			#_______I_______#

		//**
		// Setting Bounds and Attributes of the Elements 													#*******S*******#
		//**
		jLabelMain.setIcon(new javax.swing.ImageIcon(getClass().getResource("/help/about/pictures/AboutBackGround.jpg")));
        jLabelMain.setBounds(0, 0, 370, 425);
        jLabelMain.setLayout(null);
        
		
		jScrollPane.setViewportView(jTextAreaNotification);
		jScrollPane.setBounds(10, 10, 348, 356);
		jScrollPane.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
		jScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		jTextAreaNotification.setText(data);
        jTextAreaNotification.setForeground(new java.awt.Color(102, 0, 106));
        jTextAreaNotification.setSelectedTextColor(new java.awt.Color(255, 170, 255));
        jTextAreaNotification.setSelectionColor(new java.awt.Color(80, 0, 80));
        jTextAreaNotification.setFont(new java.awt.Font("Gabriola", 0, 19)); // NOI18N
//        jTextAreaNotification.setEditable(false);
        jTextAreaNotification.setCaretPosition(0);
        jTextAreaNotification.setLineWrap(true);
        jTextAreaNotification.setWrapStyleWord(true);
        
        jButtonOK.setText("OK");
        jButtonOK.setBounds(297, 380, 60, 30);
        jButtonOK.setBackground(new java.awt.Color(212, 227, 250));
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
	    jLabelMain.add(jScrollPane); 
        jLabelMain.add(jButtonOK); 
		// End of Adding Components 																		#_______A_______#

		//**Setting Criterion of the Frame**//
		setIconImage(new ImageIcon(getClass().getResource("/help/about/pictures/IconAbout.png")).getImage());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(200, 150, 375, 450);
		setTitle("About");
		setLayout(null);
		add(jLabelMain);
		setResizable(false);
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
		About gui = new About();
		gui.setVisible(true);
	}

	//**
	// Auxiliary Methods 																					#********AM*******#
	//**

	// End of Auxiliary Methods 																			#________AM_______#
}
