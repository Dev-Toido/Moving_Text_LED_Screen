import LedScreen.Screen;
import TextToBigMatrix.BigLetters;

import java.util.LinkedList;

public class Main {
	// Terminal dimensions
	private static final int SCALE = 3;
	private static final int WIDTH = 111;
	private static final int HEIGHT = 5*SCALE;

	public static void main(String[] args) throws InterruptedException{
		try {
			String str = "Hi Viewers, Wishing you a Happy Diwali and Good Health.";
			Screen s = new Screen(HEIGHT,WIDTH);
			BigLetters l = new BigLetters(str,SCALE,2);
			LinkedList<Integer>[] window = l.wholeRowWiseMat();


			for(int i = 0; i < HEIGHT; i++){
				for(int j = 0; j < WIDTH; j++){window[i].addFirst(0);
			}}

			//printing starts here
			System.out.print("\033[2J");

			while(true){
				// 1. Update the frames
				for(int i = 0; i < HEIGHT; i++){
					s.rowOn(i,window[i]);
				}
				// 2. Reset cursor to top-left (1,1) instead of clearing the screen
				// This prevents screen flickering
				System.out.print("\033[H");

				// 3. Render the frame to a StringBuilder buffer
				StringBuilder frame = s.displayStringBuilder();
				s.clearScreen();

				// 4. Print the entire frame at once and update the window
				System.out.print(frame.toString());
				for(int i = 0; i < HEIGHT; i++){
					window[i].add(window[i].removeFirst());
				}

				// Frame rate control (~16.7 frames per second)
				Thread.sleep(60);


			}
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}

	}

}
