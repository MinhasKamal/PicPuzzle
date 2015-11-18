/**
 * Developer: Minhas Kamal(BSSE-0509, IIT, DU)
 * Date: 19-Dec-2013
**/

package help.highScore.operation;

import sound.operation.SoundPlayer;

public class FileIOOperation {
	//default writing
	private String DefaultString="___________________________________________\n  1. empty \n  Time: 00-00-00\n" +
			"___________________________________________\n  2. empty \n  Time: 00-00-00\n" +
			"___________________________________________\n  3. empty \n  Time: 00-00-00\n" +
			"___________________________________________\n  4. empty \n  Time: 00-00-00\n" +
			"___________________________________________\n  5. empty \n  Time: 00-00-00\n" +
			"___________________________________________\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n" +		//15 \n
			"1_990000\n2_990000\n3_990000\n4_990000\n5_990000\n";
	
	//file name
	private String FileName="PicturePuzzle/HighScore.png";
	
	//reads precise file
	public String FileReader(){
		String Info = new FileInputOutput().fileReader(FileName);	//use a function to read the whole file
		
		return Info;
	}
	
	//opening and processing a file
	public void FileOpener(int time, String stringTime){
		int index1, index2;
		
		String Info=new String();
		Info= new FileInputOutput().fileReader(FileName);	//use a function to read the whole file
		
		int recordTime[]=new int[5];	//time records
		
		
		///////////////////////////////////////////////////////
		index1=Info.indexOf("1_");
		index2=Info.indexOf("2_");
		recordTime[0]=Integer.parseInt(Info.substring(index1+2, index2-1));
		
		index1=Info.indexOf("2_");
		index2=Info.indexOf("3_");
		recordTime[1]=Integer.parseInt(Info.substring(index1+2, index2-1));
		
		index1=Info.indexOf("3_");
		index2=Info.indexOf("4_");
		recordTime[2]=Integer.parseInt(Info.substring(index1+2, index2-1));
		
		index1=Info.indexOf("4_");
		index2=Info.indexOf("5_");
		recordTime[3]=Integer.parseInt(Info.substring(index1+2, index2-1));
		
		index1=Info.indexOf("5_");
		index2=Info.length();
		recordTime[4]=Integer.parseInt(Info.substring(index1+2, index2-1));
		////////////////////////////////////////////////////////////
		
		if(time < recordTime[4]){
			int position=0;
			
			if(time < recordTime[0]){
				position=1;
			}else if(time < recordTime[1]){
				position=2;
			}else if(time < recordTime[2]){
				position=3;
			}else if(time < recordTime[3]){
				position=4;
			}else {
				position=5;
			}
			
			//call information form
			new SoundPlayer().playSound("sound/sounds/HighScore.wav");
			
			new help.highScore.gui.InformationForm(time, stringTime, position).setVisible(true);
		}
	}
	
	//edits the file accordingly
	public void FileWriter(String Name, int Time, String StringTime, int position){
		//When no name is input
		if(Name.length()<2){
			Name="UnKnown";
		}
		
		String Info=new String();
		Info= new FileInputOutput().fileReader(FileName);	//use a function to read the whole file
		
		/**
		 * very complex work, reads the string and divides it into different sections
		**/
		int index1, index2;
		String NameTime[]=new String[5];	//taking array of strings
		String TimeInMiliSecond[]=new String[5];
		
		//////////////////////////////////////////////////////////////////////////////////
		index1=Info.indexOf(" 1. ");
		index2=Info.indexOf(" 2. ");
		NameTime[0]=Info.substring(index1+4, index2-46);
		
		index1=Info.indexOf(" 2. ");
		index2=Info.indexOf(" 3. ");
		NameTime[1]=Info.substring(index1+4, index2-46);
		
		index1=Info.indexOf(" 3. ");
		index2=Info.indexOf(" 4. ");
		NameTime[2]=Info.substring(index1+4, index2-46);
		
		index1=Info.indexOf(" 4. ");
		index2=Info.indexOf(" 5. ");
		NameTime[3]=Info.substring(index1+4, index2-46);
		
		index1=Info.indexOf(" 5. ");
		index2=Info.indexOf("\n\n");
		NameTime[4]=Info.substring(index1+4, index2-44);
		///////////////////////////////////////////////////////
		index1=Info.indexOf("1_");
		index2=Info.indexOf("2_");
		TimeInMiliSecond[0]=Info.substring(index1+2, index2-1);
		
		index1=Info.indexOf("2_");
		index2=Info.indexOf("3_");
		TimeInMiliSecond[1]=Info.substring(index1+2, index2-1);
		
		index1=Info.indexOf("3_");
		index2=Info.indexOf("4_");
		TimeInMiliSecond[2]=Info.substring(index1+2, index2-1);
		
		index1=Info.indexOf("4_");
		index2=Info.indexOf("5_");
		TimeInMiliSecond[3]=Info.substring(index1+2, index2-1);
		
		index1=Info.indexOf("5_");
		index2=Info.length();
		TimeInMiliSecond[4]=Info.substring(index1+2, index2-2);
		///////////////////////////////////////////////////////////////////////////////////////////
		
		//resetting lower rankings
		for(int i=5; i>position; i--){
			NameTime[i-1]=NameTime[i-2];
			TimeInMiliSecond[i-1]=TimeInMiliSecond[i-2];
		}
		
		
		//Setting name to the right place
		NameTime[position-1]= Name + " \n  Time: " + StringTime;
		TimeInMiliSecond[position-1]=Time + "";
		
		//reconstruction of string///////////////////////////////////////////////////////////////
		Info="___________________________________________\n  1. " + NameTime[0] + "\n" +
				"___________________________________________\n  2. " + NameTime[1] + "\n" +
				"___________________________________________\n  3. " + NameTime[2] + "\n" +
				"___________________________________________\n  4. " + NameTime[3] + "\n" +
				"___________________________________________\n  5. " + NameTime[4] + "\n" +
				"___________________________________________\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n" +		//15 \n
				"1_" + TimeInMiliSecond[0] + "\n2_" + TimeInMiliSecond[1] + "\n3_" + 
				TimeInMiliSecond[2] + "\n4_" + TimeInMiliSecond[3] + "\n5_" + TimeInMiliSecond[4] + "\n";
		/////////////////////////////////////////////////////////////////////////////////////////
		
		new FileInputOutput().fileWriter(FileName, Info);	//use a function to write information in the file
	}
	
	//returns default info
	public String defaultInfo(){
		return this.DefaultString;
	}
	
	
	
	
	/*///only for test//
	public static void main(String[] args) {
		new FileIOOperation().FileOpener(30000, "2-54-33");
	}
	/**/
}
