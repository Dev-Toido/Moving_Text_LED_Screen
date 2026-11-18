package Test_Other_codes;
import TextToBigMatrix.Letters;

public class SmoothedLetterA {
	public static void main(String[] args) {
		// Original 5x5 "A" matrix
		Letters let;
		for(char c='A'; c<='Z'; c++) {
			let = new Letters(c);
			double[][] filteredMatrix = enlarge(let.getLetterMat(), 3);
			display(filteredMatrix);
			System.out.println();
		}
	}
	public static void display(double[][] filteredMatrix) {
		int size = filteredMatrix[0].length;
		// Step 3: Render with custom text shading to see the smooth curves
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				double val = filteredMatrix[i][j];

				// Map continuous gradient values to visual thresholds
				if (val >= 0.7) {
					System.out.print("@  "); // Solid center core
				} else if (val >= 0.3) {
					System.out.print("#  "); // Mid-tone anti-aliasing
				} else if (val >= 0.15) {
					System.out.print(". "); // Light edge glow / curve transition
				} else {
					System.out.print("   "); // Empty space
				}
			}
			System.out.println();
		}
	}
	public static double[][] enlarge(int[][] smallA,int scale) {

		int size = smallA.length*scale;
		double[][] smoothMatrix = new double[size][size];

		// Step 1: Up-sample to 15x15 baseline
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				smoothMatrix[i][j] = smallA[i / scale][j / scale];
			}
		}
		display(smoothMatrix);
		System.out.println();
		// Step 2: Apply a Box Blur Kernel to blend and smooth jagged corners
		double[][] filteredMatrix = new double[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				double sum = 0;
				int count = 0;

				// 3x3 window around the current pixel
				for (int ki = -1; ki <= 1; ki++) {
					for (int kj = -1; kj <= 1; kj++) {
						int ni = i + ki;
						int nj = j + kj;

						// Check boundaries
						if (ni >= 0 && ni < size && nj >= 0 && nj < size) {
							sum += smoothMatrix[ni][nj];
							count++;
						}
					}
				}
				filteredMatrix[i][j] = sum / count;
			}
		}

		return filteredMatrix;
	}

}
