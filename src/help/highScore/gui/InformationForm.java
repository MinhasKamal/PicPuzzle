package help.highScore.gui;


import java.awt.event.ActionEvent;
import javax.swing.*;


@SuppressWarnings("serial")
public class InformationForm extends JDialog {
	
	/**
	 * Declaration Starts/////////////////////////////////////////////////////////////////////////////////////////////////////
	**/
	JPanel jPanelMain;
	JLabel jLabelNotification, jLabelStringTime, jLabelPosition;
	JLabel jLabelName;
	JTextField jTextFieldName;
	JButton jButtonOK;
	
	String Name;
	String StringTime;
	int Time;
	int Position;
	/**
	 * Declaration ends
	**/
	
	/**
	 * The constructor////////////////////////////////////////////////////////////////////////////////////////////////////////
	**/
	public InformationForm(int time, String stringStringTime, int position) {	//constructor
		this.Time=time;
		this.StringTime=stringStringTime;
		this.Position=position;
		
		initComponents(stringStringTime);
	}
	
	//the initial components
	public void initComponents(String time){
		
		/**
		 * Initialization starts//////////////////////////////////////////////////////////////////////////////////////////////
		**/
		jPanelMain = new JPanel();
		jLabelNotification = new JLabel();
		jLabelStringTime = new JLabel();
		jLabelPosition = new JLabel();
 		jLabelName = new JLabel();
		jTextFieldName = new JTextField();
		jButtonOK = new JButton();
		
		Name=new String();
		/**
		 * Initialization ends
		**/
		
		
		
		///****criteria of the frame****///
        setIconImage(new ImageIcon(getClass().getResource("/help/highScore/pictures/IconHighScore.png")).getImage());
		setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Information Form");
        setResizable(false);
        setBounds(400, 185, 450, 300);
        setLayout(null);
        add(jPanelMain); add(jButtonOK);
        setFocusable(true);
        setModal(true);
		
		
		/**
         * setting the position and size -> (x, y, l, h) /////////////////////////////////////////////////////////////////////
        **/
        jLabelNotification.setText("          New Record !!!");
        jLabelNotification.setBounds(10, 10, 350, 30);
        jLabelNotification.setForeground(new java.awt.Color(255, 23, 140));
        jLabelNotification.setFont(new java.awt.Font("Kunstler Script", 0, 40)); // NOI18N
        
        jLabelStringTime.setText("Time:      " + time + ".");
        jLabelStringTime.setBounds(20, 70, 350, 30);
        jLabelStringTime.setForeground(new java.awt.Color(255, 191, 223));
        jLabelStringTime.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        
        jLabelPosition.setText("Position:  0" + Position + ".");
        jLabelPosition.setBounds(20, 95, 350, 30);
        jLabelPosition.setForeground(new java.awt.Color(255, 191, 223));
        jLabelPosition.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        
        jLabelName.setText("Enter Your Name ");
        jLabelName.setBounds(20, 135, 230, 30);
        jLabelName.setForeground(new java.awt.Color(200, 150, 200));
        jLabelName.setFont(new java.awt.Font("Lucida Bright", 0, 15)); // NOI18N

        jTextFieldName.setBounds(20, 170, 320, 30);
        jTextFieldName.setBackground(new java.awt.Color(220, 215, 215));
        jTextFieldName.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jTextFieldName.setFont(new java.awt.Font("Times new Roman", 0, 15)); // NOI18N
        jTextFieldName.setCaretColor(new java.awt.Color(86, 0, 35));
        jTextFieldName.setForeground(new java.awt.Color(150, 0, 150));
        
        
        jButtonOK.setText("OK");
        jButtonOK.setBounds(373, 230, 60, 30);
        jButtonOK.setBackground(new java.awt.Color(220, 250, 180));
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
        /**
         * Setting Position & Size ends
        **/
        
        /**
         * The main panel declaration and adding characteristics//////////////////////////////////////////////////////////////
        **/
        jPanelMain.setBackground(new java.awt.Color(86, 0, 35));
        jPanelMain.setBounds(0, 0, 360, 300);
        jPanelMain.setLayout(null);
        
        //ADD//
        jPanelMain.add(jLabelNotification); jPanelMain.add(jLabelStringTime); jPanelMain.add(jLabelPosition);
        jPanelMain.add(jLabelName); jPanelMain.add(jTextFieldName);
        /**
         * The main panel ends
        **/
	}



	/**
	 * Actions//////////////////////////////////////////////////////////////////////////////////////////////////////////////
	**/
	private void jButtonOKActionPerformed(ActionEvent evt){
		Name=jTextFieldName.getText();
		
		//Send Name & StringTime//call function to save info being input//
		new help.highScore.operation.FileIOOperation().FileWriter(Name, Time, StringTime, Position);	
				
		dispose();
	}
	



	/*
	 * The main method////////////////////////////////////////////////////////////////////////////////////////////////////////
	**/
	public static void main(String args[]) {
		/**/
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(InformationForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(InformationForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(InformationForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(InformationForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		/**/
		
		InformationForm informationForm = new InformationForm(0 ,"00-00-00", 0);
		informationForm.setVisible(true);
	}

}


