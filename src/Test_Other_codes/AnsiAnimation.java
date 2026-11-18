package Test_Other_codes;//public class ProgressBar {
//	public static void main(String[] args) throws InterruptedException {
//		for (int i = 0; i <= 100; i += 20) {
//			// \r resets the cursor to the beginning of the line every loop iteration
//			System.out.print("\rLoading: " + i + "%");
//			Thread.sleep(500); // Pause to simulate work
//		}
//		System.out.println("\nDone!");
//	}
//}
//public class ConsoleAnimation {
//	public static void main(String[] args) throws InterruptedException {
//		// Define the frames of a 2D animation (a small car driving across the screen)
//		String[] frames = {
//				"o-o>        ",
//				"  o-o>      ",
//				"    o-o>    ",
//				"      o-o>  ",
//				"        o-o>",
//				"      <o-o  ",
//				"    <o-o    ",
//				"  <o-o      "
//		};
//
//		System.out.println("Starting console animation (Press Ctrl+C to stop)...");
//
//		// Run the animation loop 5 times
//		for (int cycles = 0; cycles < 5; cycles++) {
//			for (String frame : frames) {
//				// \r moves the cursor back to the start, overwriting the previous frame
//				System.out.print("\r" + frame);
//
//				// Flush the stream to ensure the text renders immediately
//				System.out.flush();
//
//				// Control the animation speed (200 milliseconds per frame)
//				Thread.sleep(200);
//			}
//		}
//
//		System.out.println("\nAnimation finished!");
//	}
//}
import java.io.IOException;

public class AnsiAnimation {

	// Terminal dimensions
	private static final int WIDTH = 100;
	private static final int HEIGHT = 5;

	public static void main(String[] args) throws InterruptedException, IOException {
		// Ball position and velocity
		int ballX = 1, ballY = 1;
		int deltaX = 5, deltaY = 1;

		// Clear the screen once at startup
		System.out.print("\033[2J");

		while (true) {
			// 1. Move the ball
			ballX += deltaX;
			ballY += deltaY;

			// Bounce off left/right walls
			if (ballX <= 1 || ballX >= WIDTH - 2) {
				deltaX = -deltaX;
			}
			// Bounce off top/bottom walls
			if (ballY <= 1 || ballY >= HEIGHT - 2) {
				deltaY = -deltaY;
			}

			// 2. Reset cursor to top-left (1,1) instead of clearing the screen
			// This prevents screen flickering
			System.out.print("\033[H");

			// 3. Render the frame to a StringBuilder buffer
			StringBuilder frame = new StringBuilder();

			for (int y = 0; y < HEIGHT; y++) {
				for (int x = 0; x < WIDTH; x++) {
					if (y == 0 || y == HEIGHT - 1 || x == 0 || x == WIDTH - 1) {
						// Cyan border
						frame.append("\033[36m#\033[0m");
					} else if (x == ballX && y == ballY) {
						// Yellow bouncing ball
						frame.append("\033[33;1mO\033[0m");
					} else {
						// Empty space
						frame.append(" ");
					}
				}
				frame.append("\n");
			}

			// 4. Print the entire frame at once
			System.out.print(frame.toString());

			// Frame rate control (~30 frames per second)
			Thread.sleep(50);
		}
	}
}

