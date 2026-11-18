package TextToBigMatrix;

public class Letters {
	private int[][] letterMat = new int[5][5];
	private final char letter;

	//Constructor Zone
	public Letters(char letter) {
		this.letter = letter;
		letterMatGen();
	}

	//Getter Setter Zone
	public int[][]  getLetterMat() {
		return letterMat;
	}
	public int[][]  getLetterScaledMat(int scale) {
		return enlarge(letterMat,scale);
	}
	void setLetterMat(int[][] letterMat) {
		if(letterMat == null) {
			this.letterMat = null;
			return;
		}
		if(letterMat.length != letterMat[0].length || letterMat.length != letterMat[1].length) {
			System.out.println("Error: Matrices have different length");
			return;
		}
		for(int i = 0; i < letterMat.length; i++) {
			System.arraycopy(letterMat[i], 0, this.letterMat[i], 0, letterMat[0].length);
		}
	}
	char getLetter() {
		return letter;
	}

	//function to enlarge the 5x5 letter matrix.
	private int[][] enlarge(int[][] arr, int scale) {

		int size = arr.length*scale;
		double[][] smoothMatrix = new double[size][size];

		// Step 1: Up-sample to scale times baseline
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				smoothMatrix[i][j] = arr[i / scale][j / scale];
			}
		}
		System.out.println();
		// Step 2: Apply a Box Blur Kernel to blend and smooth jagged corners
		int[][] filteredMatrix = new int[size][size];
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
				filteredMatrix[i][j] = ((sum / count)>=0.3)?1:0;
			}
		}

		return filteredMatrix;
	}

	//The Matrix Maker function
	//Cap letters done, small letters and the punctuation are left
	private void letterMatGen(){
		switch (letter) {
			case 'A':
				letterMat = new int[][]{
						{0, 0, 1, 0, 0},
						{0, 1, 0, 1, 0},
						{1, 0, 0, 0, 1},
						{1, 1, 1, 1, 1},
						{1, 0, 0, 0, 1},
				};
				break;
			case 'B':
				letterMat = new int[][]{
						{1, 1, 1, 1, 0},
						{1, 0, 0, 0, 1},
						{1, 0, 1, 1, 0},
						{1, 0, 0, 0, 1},
						{1, 1, 1, 1, 0}
				};
				break;
			case 'C':
				letterMat = new int[][]{
						{0, 1, 1, 1, 0},
						{1, 0, 0, 0, 1},
						{1, 0, 0, 0, 0},
						{1, 0, 0, 0, 1},
						{0, 1, 1, 1, 0}
				};
				break;
			case 'D':
				letterMat = new int[][]{
						{1, 1, 1, 1, 0},
						{1, 0, 0, 0, 1},
						{1, 0, 0, 0, 1},
						{1, 0, 0, 0, 1},
						{1, 1, 1, 1, 0}
				};
				break;
			case 'E':
				letterMat = new int[][]{
						{1, 1, 1, 1, 1},
						{1, 0, 0, 0, 0},
						{1, 1, 1, 0, 0},
						{1, 0, 0, 0, 0},
						{1, 1, 1, 1, 1}
				};
				break;
			case 'F':
				letterMat = new int[][]{
						{1, 1, 1, 1, 1},
						{1, 0, 0, 0, 0},
						{1, 1, 1, 0, 0},
						{1, 0, 0, 0, 0},
						{1, 0, 0, 0, 0}
				};break;
			case 'G':
				letterMat = new int[][]{
						{1, 1, 1, 1, 1},
						{1, 0, 0, 0, 0},
						{1, 0, 1, 1, 1},
						{1, 0, 0, 0, 1},
						{1, 1, 1, 1, 1}
				};break;
			case 'H':
				letterMat = new int[][]{
						{1, 0, 0, 0, 1},
						{1, 0, 0, 0, 1},
						{1, 1, 1, 1, 1},
						{1, 0, 0, 0, 1},
						{1, 0, 0, 0, 1}
				};break;
			case 'I':
				letterMat = new int[][]{
						{1, 1, 1, 1, 1},
						{0, 0, 1, 0, 0},
						{0, 0, 1, 0, 0},
						{0, 0, 1, 0, 0},
						{1, 1, 1, 1, 1}
				};break;
				case 'J':
				letterMat = new int[][]{
						{1, 1, 1, 1, 1},
						{0, 0, 0, 1, 0},
						{1, 0, 0, 1, 0},
						{1, 0, 0, 1, 0},
						{0, 1, 1, 1, 0}
				};break;
				case 'K':
				letterMat = new int[][]{
						{1, 0, 0, 1, 0},
						{1, 0, 1, 0, 0},
						{1, 1, 0, 0, 0},
						{1, 0, 1, 0, 0},
						{1, 0, 0, 1, 0}
				};break;
				case 'L':
				letterMat = new int[][]{
						{1, 0, 0, 0, 0},
						{1, 0, 0, 0, 0},
						{1, 0, 0, 0, 0},
						{1, 0, 0, 0, 0},
						{1, 1, 1, 1, 1}
				};break;
				case 'M':
				letterMat = new int[][]{
						{1, 0, 0, 0, 1},
						{1, 1, 0, 1, 1},
						{1, 0, 1, 0, 1},
						{1, 0, 0, 0, 1},
						{1, 0, 0, 0, 1}
				};break;
				case 'N':
				letterMat = new int[][]{
						{1, 0, 0, 0, 1},
						{1, 1, 0, 0, 1},
						{1, 0, 1, 0, 1},
						{1, 0, 0, 1, 1},
						{1, 0, 0, 0, 1}
				};break;
				case 'O':
				letterMat = new int[][]{
						{1, 1, 1, 1, 1},
						{1, 0, 0, 0, 1},
						{1, 0, 0, 0, 1},
						{1, 0, 0, 0, 1},
						{1, 1, 1, 1, 1}
				};break;
				case 'P':
				letterMat = new int[][]{
						{1, 1, 1, 1, 0},
						{1, 0, 0, 0, 1},
						{1, 0, 0, 0, 1},
						{1, 1, 1, 1, 0},
						{1, 0, 0, 0, 0}
				};break;
				case 'Q':
				letterMat = new int[][]{
						{1, 1, 1, 1, 1},
						{1, 0, 0, 0, 1},
						{1, 0, 0, 0, 1},
						{1, 0, 0, 1, 1},
						{1, 1, 1, 1, 1}
				};break;
				case 'R':
				letterMat = new int[][]{
						{1, 1, 1, 1, 0},
						{1, 0, 0, 0, 1},
						{1, 0, 0, 0, 1},
						{1, 1, 1, 1, 0},
						{1, 0, 0, 0, 1}
				};break;
				case 'S':
				letterMat = new int[][]{
						{0, 1, 1, 1, 1},
						{1, 0, 0, 0, 0},
						{1, 1, 1, 1, 1},
						{0, 0, 0, 0, 1},
						{1, 1, 1, 1, 0}
				};break;
				case 'T':
				letterMat = new int[][]{
						{1, 1, 1, 1, 1},
						{0, 0, 1, 0, 0},
						{0, 0, 1, 0, 0},
						{0, 0, 1, 0, 0},
						{0, 0, 1, 0, 0}
				};break;
				case 'U':
				letterMat = new int[][]{
						{1, 0, 0, 0, 1},
						{1, 0, 0, 0, 1},
						{1, 0, 0, 0, 1},
						{1, 0, 0, 0, 1},
						{0, 1, 1, 1, 0}
				};break;
				case 'V':
				letterMat = new int[][]{
						{1, 0, 0, 0, 1},
						{1, 0, 0, 0, 1},
						{1, 0, 0, 0, 1},
						{0, 1, 0, 1, 0},
						{0, 0, 1, 0, 0}
				};break;
				case 'W':
				letterMat = new int[][]{
						{1, 0, 0, 0, 1},
						{1, 0, 0, 0, 1},
						{1, 0, 1, 0, 1},
						{1, 0, 1, 0, 1},
						{0, 1, 0, 1, 0}
				};break;
				case 'X':
				letterMat = new int[][]{
						{1, 0, 0, 0, 1},
						{0, 1, 0, 1, 0},
						{0, 0, 1, 0, 0},
						{0, 1, 0, 1, 0},
						{1, 0, 0, 0, 1}
				};break;
				case 'Y':
				letterMat = new int[][]{
						{1, 0, 0, 0, 1},
						{0, 1, 0, 1, 0},
						{0, 0, 1, 0, 0},
						{0, 0, 1, 0, 0},
						{0, 0, 1, 0, 0}
				};break;
				case 'Z':
				letterMat = new int[][]{
						{1, 1, 1, 1, 1},
						{0, 0, 0, 1, 0},
						{0, 0, 1, 0, 0},
						{0, 1, 0, 0, 0},
						{1, 1, 1, 1, 1}
				};break;
				default:
					break;
		}
	}
}
