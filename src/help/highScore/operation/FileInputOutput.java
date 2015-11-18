/**
 * Developer: Minhas Kamal (IIT, DU, BSSE-0509)
 * Attribute: Can read & write any general file
 * Date: 08-Nov-2013
 **/


package help.highScore.operation;


import help.notifications.message.gui.Message;

import java.io.*;




public class FileInputOutput {
	public String fileReader(String fileName){
		String string = new String();	//for temporary data store
		String Information = new String();	//#contains the full file 
		
		try{
			File mainFile = new File(fileName);
			
			if (!mainFile.exists()){ 		//File doesn't exists, then create it
				File folder = new File(fileName.substring(0, fileName.lastIndexOf("/")));
				folder.mkdirs();
				
				mainFile.createNewFile();
				fileWriter(fileName, new FileIOOperation().defaultInfo());//initializing with default value
			}
			
			/*file reading*/
			BufferedReader mainBR = new BufferedReader(new FileReader(mainFile));	//making buffered reader object
			
			Information = "";
			
			string = mainBR.readLine();
			while(true){	//reading step by step
				if(string==null) //when file ends
					break;
				
				Information = Information + string + "\n"; 
				
				string = mainBR.readLine();
			}
			
			mainBR.close();	//closing the file
			
		}catch(IOException e){			//catching exception
			new Message("Error!", 420);
		}catch(NullPointerException e){
			new Message("Error!", 420);
		}catch(Exception e){
			new Message("Error!", 420);
		}
		return Information; 
	}
	
	
	public void fileWriter(String fileName, String Information){
		try{
			BufferedWriter mainBW = new BufferedWriter(new FileWriter(fileName));
			mainBW.write(Information);
			
			mainBW.close();
			
		}catch(IOException e){
			new Message("Error!", 420);
		}catch(NullPointerException e){
			new Message("Error!", 420);
		}catch(Exception e){
			new Message("Error!", 420);
		}
		return ;
	}
}
