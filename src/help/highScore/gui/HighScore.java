package help.highScore.gui;


import java.awt.event.ActionEvent;

import javax.swing.*;


@SuppressWarnings("serial")
public class HighScore extends JFrame{
	
	/**
	 * Declaration Starts/////////////////////////////////////////////////////////////////////////////////////////////////////
	**/
	private JPanel jPanelMain;
	private JButton jButtonOK;
	private JTextPane jTextPaneInfo;
    private JLabel jLabelNote;
    
    String info;
	/**
	 * Declaration ends
	**/
	
	
	/**
	 * The constructor////////////////////////////////////////////////////////////////////////////////////////////////////////
	**/
	public HighScore(String information){
		
		initComponent(information);
	}
	
	
	//the initial components
	private void initComponent(String information){
		
		/**
		 * Initialization starts//////////////////////////////////////////////////////////////////////////////////////////////
		**/
		jButtonOK=new JButton();
		jTextPaneInfo=new JTextPane();
		jLabelNote=new JLabel();
		jPanelMain=new JPanel();
		
		info = new String();
		/**
		 * Initialization ends
		**/
		
		
		//////////
		this.info=information;
		
		
		///****criteria of the frame****///
		setIconImage(new ImageIcon(getClass().getResource("/help/highScore/pictures/IconHighScore.png")).getImage());
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("High Score");
        setResizable(false);
        setBounds(500, 145, 350, 450);
        setLayout(null);
        add(jPanelMain);
        setFocusable(true);
        setAlwaysOnTop(true);
		
		/**
         * setting the position and size -> (x, y, l, h) /////////////////////////////////////////////////////////////////////
        **/
        jLabelNote.setForeground(new java.awt.Color(102, 51, 0));
        jLabelNote.setFont(new java.awt.Font("Lucida Calligraphy", 0, 18)); // NOI18N
        jLabelNote.setText(" Top 5 ");
        jLabelNote.setBounds(20, 10, 200, 40);

        jTextPaneInfo.setEditable(false);
        jTextPaneInfo.setText(info);
        jTextPaneInfo.setFont(new java.awt.Font("Times new Roman", 0, 15));
        jTextPaneInfo.setForeground(new java.awt.Color(150, 0, 150));
        jTextPaneInfo.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jTextPaneInfo.setSelectionColor(new java.awt.Color(250, 80, 0));
        jTextPaneInfo.setSelectedTextColor(new java.awt.Color(202, 255, 150));
        jTextPaneInfo.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jTextPaneInfo.setBounds(20, 50, 305, 310);
        
        
        jButtonOK.setText("OK");
        jButtonOK.setBounds(270, 380, 60, 30);
        jButtonOK.setBackground(new java.awt.Color(220, 180, 180));
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
        jPanelMain.setBackground(new java.awt.Color(230, 200, 255));
        jPanelMain.setBounds(0, 0, 350, 450);
        jPanelMain.setLayout(null);
        
        //ADD//
        jPanelMain.add(jLabelNote); jPanelMain.add(jTextPaneInfo); jPanelMain.add(jButtonOK);
        /**
         * The main panel ends
        **/
	}
	
	
	/**
	 * Actions//////////////////////////////////////////////////////////////////////////////////////////////////////////////
	**/
	private void jButtonOKActionPerformed(ActionEvent evt){
		dispose();
	}
	
	
	
	
	/**
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
			java.util.logging.Logger.getLogger(HighScore.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(HighScore.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(HighScore.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(HighScore.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		/**/
		
		HighScore highScore = new HighScore("___________________________________________\n  1. Minhas Kamal\n  Time: 01-22-54\n" +
											"___________________________________________\n  2. Mahedi Kamal\n  Time: 01-43-67\n" +
											"___________________________________________\n  3. Fagilatun Naher\n  Time: 02-22-54\n" +
											"___________________________________________\n  4. Md. Mokbul Hossain\n  Time: 02-43-67\n" +
											"___________________________________________\n  5. Md. Mostafa Kamal\n  Time: 02-55-11\n" +
											"___________________________________________"
											);	//default shower
		highScore.setVisible(true);
	}
	
}
