/**
 * Developer: Minhas Kamal (BSSE0509, IIT, DU)
 * Date: 06-Jun-2014
 * Comment: It was a lot of fun making this class.
 */

package sound.operation;

import java.net.URL;
import javax.sound.sampled.*;

public class SoundPlayer implements Runnable{
	private Clip clip;
	private URL url;
	private AudioInputStream audioIn;
	
	public SoundPlayer() {
		clip = null;
		url = null;
		audioIn = null;
	}
	
	public void playSound(String audioResource){
		try {
			url = this.getClass().getClassLoader().getResource(audioResource);
			audioIn = AudioSystem.getAudioInputStream(url);
			
			clip = AudioSystem.getClip();
			
			new Thread(this).start();
			
			finalize();
		} catch (Throwable e) {
			//new Message("Error happened while playing sound.", 420);
			e.printStackTrace();
		}finally{
			clip.flush();
			clip.close();
			System.gc();
		}
	}

	@Override
	public void run(){

		try{
			clip.open(audioIn);
			clip.start();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}


