/************************************************************************************************************
* Developer: Minhas Kamal(BSSE-0509, IIT, DU)																*
* Date: 04-May-2014																							*
* Comment: It was a lot of fun creating this simple	AI. This program uses some really plain technique to 	*									
* 	solve puzzle. Sometimes even I get astonished to see its working style. I have tried to make the AI more*
* 	humane, and so it some times make mistakes and resolves mistakes. Sometimes it takes unnecessary steps.	*
* 	All have been done to make solving technique more interesting to see :). With a little modification it	*
* 	can solve picture type puzzle of infinite number of grids.												*
*************************************************************************************************************/


package robot.operation;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import sound.operation.SoundPlayer;

public class PuzzleSolver implements Runnable{
	//**
	// Variable Declaration 																				#*******D*******#
	//**
	private ImageIcon icon[][];
	private JLabel[][] jLabelPicPart;
	private JLabel jLabelTemp;
	private Boolean end;
	
	Thread thread;
	
	SoundPlayer soundPlayer;
	// End of Variable Declaration 																			#_______D_______#

	
	/***##Constructor##***/
	public PuzzleSolver(ImageIcon[][] icon, JLabel[][] jLabelPicPart, JLabel jLabelTemp, Boolean end){
		this.icon = icon;
		this.jLabelPicPart = jLabelPicPart;
		this.jLabelTemp = jLabelTemp;
		this.end = end;
		
		thread = new Thread(this);
		soundPlayer = new SoundPlayer();
	}
	
	
	/**
	 * triggers the action to start solving mechanism
	 */
	public void solvePuzzle(){
		thread.start();
	}
	
	
	/**
	 * program runs from here and all the picture parts' solving technique is controlled from here 
	 */
	@Override
	public void run() {
		int aeroKey=1;			//arrow key pressed for changing the place of the picture-parts
		int selectedLabelNo=19;	//specific label we want to match picture-part to
		int step=19;

		//leave matched labels
		for(int i=4; i>=2; i--){
			for(int j=3; j>=0; j--){
				if(jLabelPicPart[i][j].getIcon() == icon[i][j]){
					step--;
				}else{
					i=2;
					break;
				}
			}
		}
		for(int i=0; i<2; i++){
			for(int j=0; j<2; j++){
				if(jLabelPicPart[j][i].getIcon() == icon[j][i]){
					step--;
				}else{
					i=2;
					break;
				}
			}
		}
		
		for(; step>=3 && !end; step--){
			waitTill(600);
			selectedLabelNo=step;
			
			//select label
			if(selectedLabelNo==7) selectedLabelNo=4;
			else if(selectedLabelNo==6) selectedLabelNo=0;
			else if(selectedLabelNo==5) selectedLabelNo=5;
			else if(selectedLabelNo==4) selectedLabelNo=1;
			else if(selectedLabelNo==3) selectedLabelNo=6;
			//System.out.println("Selected label no: " + selectedLabelNo);///test
			
			//*find the label containing the icon of selectedLabel*//
			int targetLabelNo=findLabelNo(selectedLabelNo);
			
			//*go to the targetLabel*//
			aeroKey = go(targetLabelNo);
			
			//using last used arrow-key to find out the changed targetLabelNo
			if(aeroKey==40){			//down
				targetLabelNo = targetLabelNo+4;
			}else if(aeroKey==38){	//up
				targetLabelNo = targetLabelNo-4;
			}else if(aeroKey==39){	//right
				targetLabelNo = targetLabelNo+1;
			}else if(aeroKey==37){	//left
				targetLabelNo = targetLabelNo-1;
			}
			
			//get label no where the icon will be fetched first
			int fetchLabelNo=getFetchLabelNo(selectedLabelNo);
			
			//*fetch target-label-icon to fetch-label*//
			if(selectedLabelNo==17 ||selectedLabelNo==13 ||selectedLabelNo==9){
				fetchHorizontal(targetLabelNo, fetchLabelNo);
			}else{
				fetchVertical(targetLabelNo, fetchLabelNo);
			}
			
			//*settle icon to selected label*//
			settle(selectedLabelNo);
			
			//*Check if everything is OK or not*//
			step=check(step);
			
			
			//System.out.println(end);	///test
		}
		
		if(jLabelTemp != jLabelPicPart[0][3]){
			go(3);
		}
		
	}
	
	
	//**
	// logical steps																						#********LS*******#
	//**
	
	/**
	 * Takes <code>jLabelTemp</code> to specific label by steps & returns last used aerokey as int.
	 * First take vertical path then horizontal path.
	 * @param targetLabelNo <code>jLabelTemp</code> should be taken here
	 * @return last used arrow key
	 */
	private int go(int targetLabelNo){
		int arrowKey=1;
		int tempLabelNo=targetLabelNo;
		
		//find temp-label
		for(int i=0; i<20; i++){
			if(jLabelPicPart[i/4][i%4] == jLabelTemp){
				tempLabelNo=i;
				break;
			}
		}
		
		//go to specific location
		while(targetLabelNo != tempLabelNo){
			//get arrow key
			if((int)targetLabelNo/4 < (int)tempLabelNo/4){
				arrowKey = 40;	//go down
			}else if((int)targetLabelNo/4 > (int)tempLabelNo/4){
				arrowKey = 38;	//go up
			}else if(targetLabelNo%4 < tempLabelNo%4){
				arrowKey = 39;	//go right
			}else if(targetLabelNo%4 > tempLabelNo%4){
				arrowKey = 37;	//go left
			}
			
			//swap lables
			JLabel swapLabel;
			swapLabel=FindSelectedLabel(jLabelTemp, arrowKey);
			labelSwap(swapLabel);
			
			//update temp
			if(arrowKey==40){		//down
				tempLabelNo = tempLabelNo-4;
			}else if(arrowKey==38){	//up
				tempLabelNo = tempLabelNo+4;
			}else if(arrowKey==39){	//right
				tempLabelNo = tempLabelNo-1;
			}else if(arrowKey==37){	//left
				tempLabelNo = tempLabelNo+1;
			}
			
			//wait a little
			waitTill(180);
		}
		
		return arrowKey;
	}
	
	/**
	 * Takes <code>jLabelTemp</code> to specific label by steps & returns last used arrow key as <code>int</code> but never 
	 * touches forbidden label. First take vertical path then horizontal path
	 * @param targetLabelNo <code>jLabelTemp</code> should be taken here
	 * @param forbiddenLabelNo label no that should never be touched.
	 * @return last used arrow key
	 */
	private int go(int targetLabelNo, int forbiddenLabelNo){
		int arrowKey=1;
		int tempLabelNo=targetLabelNo;
		
		//find temp-label
		for(int i=0; i<20; i++){
			if(jLabelPicPart[i/4][i%4] == jLabelTemp){
				tempLabelNo=i;
				break;
			}
		}
		
		//go to specific location
		int method=0;
		while(targetLabelNo != tempLabelNo){
			//get arrow key
			if(method==0){	//vertical
				if((int)targetLabelNo/4 < (int)tempLabelNo/4){
					arrowKey = 40;	//go down
				}else if((int)targetLabelNo/4 > (int)tempLabelNo/4){
					arrowKey = 38;	//go up
				}else if(targetLabelNo%4 < tempLabelNo%4){
					arrowKey = 39;	//go right
				}else if(targetLabelNo%4 > tempLabelNo%4){
					arrowKey = 37;	//go left
				}
			}else{			//horizontal
				if(targetLabelNo%4 < tempLabelNo%4){
					arrowKey = 39;	//go right
				}else if(targetLabelNo%4 > tempLabelNo%4){
					arrowKey = 37;	//go left
				}else if((int)targetLabelNo/4 < (int)tempLabelNo/4){
					arrowKey = 40;	//go down
				}else if((int)targetLabelNo/4 > (int)tempLabelNo/4){
					arrowKey = 38;	//go up
				}
			}
			
			//get swap-label
			JLabel swapLabel;
			swapLabel=FindSelectedLabel(jLabelTemp, arrowKey);
			
			if(jLabelPicPart[forbiddenLabelNo/4][forbiddenLabelNo%4] == swapLabel){	//prevent from touching forbiddenLabel
				if(arrowKey==40 || arrowKey==38){		//vertical
					method=0;
					arrowKey=37;
					swapLabel=FindSelectedLabel(jLabelTemp, arrowKey);
					if(jLabelTemp == swapLabel){	//if blocked
						arrowKey=39;
						swapLabel=FindSelectedLabel(jLabelTemp, arrowKey);
					}
				}else if(arrowKey==39 || arrowKey==37){	//horizontal
					method=1;
					arrowKey=40;
					swapLabel=FindSelectedLabel(jLabelTemp, arrowKey);
					if(jLabelTemp == swapLabel){	//if blocked
						arrowKey=38;
						swapLabel=FindSelectedLabel(jLabelTemp, arrowKey);
					}
				}
			}
			
			//swap lables
			labelSwap(swapLabel);
			
			//update temp
			if(arrowKey==40){		//down
				tempLabelNo = tempLabelNo-4;
			}else if(arrowKey==38){	//up
				tempLabelNo = tempLabelNo+4;
			}else if(arrowKey==39){	//right
				tempLabelNo = tempLabelNo-1;
			}else if(arrowKey==37){	//left
				tempLabelNo = tempLabelNo+1;
			}
			
			//wait a little
			waitTill(230);
		}
		
		return arrowKey;
	}
	
	/**
	 * Returns <code>fetchLabelNo</code> depending on <code>selectedLabelNo</code>.
	 * @param selectedLabelNo the main label where icon should be fetched
	 * @return fetchLabelNo the temporary label where icon should be fetched now
	 */
	private int getFetchLabelNo(int selectedLabelNo){
		int fetchLabelNo=selectedLabelNo;
		
		if(selectedLabelNo==19) fetchLabelNo=19;
		else if(selectedLabelNo==18) fetchLabelNo=14;
		else if(selectedLabelNo==17) fetchLabelNo=16;
		else if(selectedLabelNo==16) fetchLabelNo=12;
		else if(selectedLabelNo==15) fetchLabelNo=15;
		else if(selectedLabelNo==14) fetchLabelNo=10;
		else if(selectedLabelNo==13) fetchLabelNo=12;
		else if(selectedLabelNo==12) fetchLabelNo=8;
		else if(selectedLabelNo==11) fetchLabelNo=11;
		else if(selectedLabelNo==10) fetchLabelNo=6;
		else if(selectedLabelNo==9) fetchLabelNo=8;
		else if(selectedLabelNo==8) fetchLabelNo=4;
		else if(selectedLabelNo==4) fetchLabelNo=0;
		else if(selectedLabelNo==0) fetchLabelNo=1;
		else if(selectedLabelNo==5) fetchLabelNo=1;
		else if(selectedLabelNo==1) fetchLabelNo=2;
		else if(selectedLabelNo==6) fetchLabelNo=6;
		
		return fetchLabelNo;
	}
	
	/**
	 * Fetches icon to a specific label <code>fetchLabelNo</code> from <code>targetLabelNo</code>.
	 * First fetches through vertical path then horizontal path.
	 * @param targetLabelNo label containing the icon
	 * @param fetchLabelNo label where icon should be fetched
	 */
	private void fetchVertical(int targetLabelNo, int fetchLabelNo){	
		int arrowKey=1;
		
		while(targetLabelNo != fetchLabelNo){
			//get arrow key
			if((int)targetLabelNo/4 < (int)fetchLabelNo/4){
				go(targetLabelNo+4, targetLabelNo);
				
				arrowKey = 40;	//go down
			}else if((int)targetLabelNo/4 > (int)fetchLabelNo/4){
				go(targetLabelNo-4, targetLabelNo);
				
				arrowKey = 38;	//go up
			}else if(targetLabelNo%4 < fetchLabelNo%4){
				go(targetLabelNo+1, targetLabelNo);
				
				arrowKey = 39;	//go right
			}else if(targetLabelNo%4 > fetchLabelNo%4){
				go(targetLabelNo-1, targetLabelNo);
				
				arrowKey = 37;	//go left
			}
			
			
			//swap labels
			JLabel swapLabel;
			swapLabel=FindSelectedLabel(jLabelTemp, arrowKey);
			labelSwap(swapLabel);
			
			//update temp
			if(arrowKey==40){		//down
				targetLabelNo = targetLabelNo+4;
			}else if(arrowKey==38){	//up
				targetLabelNo = targetLabelNo-4;
			}else if(arrowKey==39){	//right
				targetLabelNo = targetLabelNo+1;
			}else if(arrowKey==37){	//left
				targetLabelNo = targetLabelNo-1;
			}
			
			//wait a little
			waitTill(300);
		}
		
		return ;
	}
	
	/**
	 * Fetches icon to a specific label <code>fetchLabelNo</code> from <code>targetLabelNo</code>.
	 * First fetches through horizontal path then vertical path.
	 * @param targetLabelNo label containing the icon
	 * @param fetchLabelNo label where icon should be fetched
	 */
	private void fetchHorizontal(int targetLabelNo, int fetchLabelNo){
		int arrowKey=1;
		
		while(targetLabelNo != fetchLabelNo){
			//get arrow key
			if(targetLabelNo%4 < fetchLabelNo%4){
				go(targetLabelNo+1, targetLabelNo);
				
				arrowKey = 39;	//go right
			}else if(targetLabelNo%4 > fetchLabelNo%4){
				go(targetLabelNo-1, targetLabelNo);
				
				arrowKey = 37;	//go left
			}else if((int)targetLabelNo/4 < (int)fetchLabelNo/4){
				go(targetLabelNo+4, targetLabelNo);
				
				arrowKey = 40;	//go down
			}else if((int)targetLabelNo/4 > (int)fetchLabelNo/4){
				go(targetLabelNo-4, targetLabelNo);
				
				arrowKey = 38;	//go up
			}
			
			
			//swap labels
			JLabel swapLabel;
			swapLabel=FindSelectedLabel(jLabelTemp, arrowKey);
			labelSwap(swapLabel);
			
			//update temp
			if(arrowKey==40){		//down
				targetLabelNo = targetLabelNo+4;
			}else if(arrowKey==38){	//up
				targetLabelNo = targetLabelNo-4;
			}else if(arrowKey==39){	//right
				targetLabelNo = targetLabelNo+1;
			}else if(arrowKey==37){	//left
				targetLabelNo = targetLabelNo-1;
			}
			
			//wait a little
			waitTill(400);
		}
		
		return ;
	}
	
	/**
	 * Settle <code>selectedLabelNo</code>'s icon to specific place and managing other icons to its own place.
	 * @param selectedLabelNo where the icon should be settled
	 */
	private void settle(int selectedLabelNo){
		int arrowKey=1;
		
		//for label No-18, 14 & 10
		if(selectedLabelNo==18 || selectedLabelNo==14 || selectedLabelNo==10){
			if(jLabelPicPart[selectedLabelNo/4][selectedLabelNo%4] != jLabelTemp){
				
				go(selectedLabelNo-9, selectedLabelNo-4);
				go(selectedLabelNo, selectedLabelNo-4);
			}
			arrowKey=40;	//down
			
			JLabel selectedLabel;
			selectedLabel=FindSelectedLabel(jLabelTemp, arrowKey);
			labelSwap(selectedLabel);
		}
		//for label No-17, 13 & 9
		else if(selectedLabelNo==17 || selectedLabelNo==13 || selectedLabelNo==9){
			if(jLabelPicPart[selectedLabelNo/4][selectedLabelNo%4].getIcon() == icon[selectedLabelNo/4][selectedLabelNo%4-1]){				
				waitTill(550);
				
				fetchHorizontal(selectedLabelNo, selectedLabelNo-9);
				
				fetchHorizontal(selectedLabelNo, selectedLabelNo-1);
				
			}
		}
		//for label No-16, 12 & 8
		else if(selectedLabelNo==16 || selectedLabelNo==12 || selectedLabelNo==8){
			JLabel selectedLabel;
			
			go(selectedLabelNo+1, selectedLabelNo-4);
			
			waitTill(550);
			
			arrowKey=39;	//right
			selectedLabel=FindSelectedLabel(jLabelTemp, arrowKey);
			labelSwap(selectedLabel);
			
			waitTill(400);
			
			arrowKey=40;	//down
			selectedLabel=FindSelectedLabel(jLabelTemp, arrowKey);
			labelSwap(selectedLabel);
		}
		//for label No-4 & 5
		else if(selectedLabelNo==4 || selectedLabelNo==5){
			if(jLabelPicPart[selectedLabelNo/4][selectedLabelNo%4].getIcon() == icon[selectedLabelNo/4-1][selectedLabelNo%4]){				
				waitTill(550);
				
				fetchHorizontal(selectedLabelNo, selectedLabelNo-2);
				
				fetchHorizontal(selectedLabelNo, selectedLabelNo-4);
			}
		}
		//for label No-0 & 1
		else if(selectedLabelNo==0 || selectedLabelNo==1){
			JLabel selectedLabel;
			
			go(selectedLabelNo+4, selectedLabelNo+1);
			
			waitTill(500);
			
			arrowKey=40;	//down
			selectedLabel=FindSelectedLabel(jLabelTemp, arrowKey);
			labelSwap(selectedLabel);
			
			waitTill(200);
			
			arrowKey=37;	//left
			selectedLabel=FindSelectedLabel(jLabelTemp, arrowKey);
			labelSwap(selectedLabel);
		}
		
		return ;
	}
	
	/**
	 * Check if all the picture parts matched are stable(to right place).
	 * @param step solving step(0-19) of picture parts
	 * @return revised no of step
	 */
	private int check(int step){
		if(step==16 || step==12 || step==8){
			int i = step/4;
			for(int j=3; j>=0; j--){
				if(jLabelPicPart[i][j].getIcon() != icon[i][j]){
					step=step+j+1;
					break;
				}
			}
		}
		else if(step==4){
			for(int j=0; j<2; j++){
				for(int i=1; i>=0; i--){
					if(jLabelPicPart[i][j].getIcon() != icon[i][j]){
						step=step+4;
						j=2;
						break;
					}
				}
			}
		}
		
		return step;
	}
	// End of logical steps 																				#________LS_______#

	
	//**
	// Auxiliary Methods 																					#********AM*******#
	//**
	
	/**
	 * Finds and returns the label no containing specific icon.
	 * @param iconNumber here icon number is the <code>selectedLabelNo</code>
	 * @return label containing specified icon
	 */
	private int findLabelNo(int iconNumber){
		ImageIcon iconTemp = icon[iconNumber/4][iconNumber%4];
		int labelNo=-1;
		
		for(int i=0; i<20; i++){
			if(jLabelPicPart[i/4][i%4].getIcon() == iconTemp){
				labelNo=i;
				break;
			}
		}
		
		return labelNo;
	}
	
	/**
	 * Takes a <code>jLabelTemp</code> & an <code>int</code>(pressed arrow key) and returns the neighboring label.
	 * @param jLabelTemp label containing empty pic-part
	 * @param key pressed arrow key
	 * @return label by the side of <code>jLabelTemp</code> depending on <code>int</code>(pressed arrow key)
	 */
   	private JLabel FindSelectedLabel(JLabel jLabelTemp, int key){
  		JLabel selectedLabel=jLabelTemp;
  		
  		if(key==39){			/////right/////
  			if(jLabelTemp==jLabelPicPart[0][0] || jLabelTemp==jLabelPicPart[1][0] || jLabelTemp==jLabelPicPart[2][0] || 
  					jLabelTemp==jLabelPicPart[3][0] || jLabelTemp==jLabelPicPart[4][0]){
  				
  				selectedLabel=jLabelTemp;
  			}
  			else{
  				for(int i=0; i<5; i++){
  					for(int j=1; j<4; j++){
  						if(jLabelTemp==jLabelPicPart[i][j]){
  							selectedLabel=jLabelPicPart[i][j-1];
  							break;
  						}
  					}
  				}
  			}
  		}
  		else if(key==37){			/////left/////
  			if(jLabelTemp==jLabelPicPart[0][3] || jLabelTemp==jLabelPicPart[1][3] || jLabelTemp==jLabelPicPart[2][3] || 
  					jLabelTemp==jLabelPicPart[3][3] || jLabelTemp==jLabelPicPart[4][3]){
  				
  				selectedLabel=jLabelTemp;
  			}
  			else{
  				for(int i=0; i<5; i++){
  					for(int j=0; j<3; j++){
  						if(jLabelTemp==jLabelPicPart[i][j]){
  							selectedLabel=jLabelPicPart[i][j+1];
  							break;
  						}
  					}
  				}
  			}
  		}
  		else if(key==38){			/////up/////
  			if(jLabelTemp==jLabelPicPart[4][0] || jLabelTemp==jLabelPicPart[4][1] || jLabelTemp==jLabelPicPart[4][2] || 
  					jLabelTemp==jLabelPicPart[4][3]){
  				
  				selectedLabel=jLabelTemp;
  			}
  			else{
  				for(int i=0; i<4; i++){
  					for(int j=0; j<4; j++){
  						if(jLabelTemp==jLabelPicPart[i][j]){
  							selectedLabel=jLabelPicPart[i+1][j];
  							break;
  						}
  					}
  				}
  			}
  		}
  		else if(key==40){		/////down/////
  			if(jLabelTemp==jLabelPicPart[0][0] || jLabelTemp==jLabelPicPart[0][1] || jLabelTemp==jLabelPicPart[0][2] || 
  					jLabelTemp==jLabelPicPart[0][3]){
  				
  				selectedLabel=jLabelTemp;
  			}
  			else{
  				for(int i=1; i<5; i++){
  					for(int j=0; j<4; j++){
  						if(jLabelTemp==jLabelPicPart[i][j]){
  							selectedLabel=jLabelPicPart[i-1][j];
  							break;
  						}
  					}
  				}
  			}
  		}
  		
  		return selectedLabel;
  	}
	
   	
   	/**
   	 * Swaps icons of <code>selectedLabel</code> and <code>jLabelTemp</code>. Doesn't swap labels directly.
   	 * @param selectedLabel label containing swap-icon
   	 */
	private void labelSwap(JLabel selectedLabel){
//		soundPlayer.playSound("sound/sounds/Drop2.wav");
		
  		Icon icon = selectedLabel.getIcon();
  		selectedLabel.setIcon(jLabelTemp.getIcon());
  		jLabelTemp.setIcon(icon);
  		
  		jLabelTemp=selectedLabel;
  	}
	

	/**
	 * Sends thread to sleep for a specific amount of time.
	 * @param time waiting time in mili seconds
	 */
	private void waitTill(int time){
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// do nothing
		}
	}
	// End of Auxiliary Methods 																			#________AM_______#
}



