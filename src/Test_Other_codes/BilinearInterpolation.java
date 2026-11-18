package Test_Other_codes;

public class BilinearInterpolation {

	/**
	 * Resizes a 2D image matrix using Bilinear Interpolation.
	 *
	 * @param source    The original 2D matrix (grayscale/single channel intensity)
	 * @param newWidth  The desired target width
	 * @param newHeight The desired target height
	 * @return A new resized 2D matrix
	 */
	public static double[][] resize(double[][] source, int newWidth, int newHeight) {
		int oldHeight = source.length;
		int oldWidth = source[0].length;
		double[][] result = new double[newHeight][newWidth];

		// Ratios to map new coordinates back to original coordinates
		double xRatio = (double) (oldWidth - 1) / (newWidth - 1);
		double yRatio = (double) (oldHeight - 1) / (newHeight - 1);

		for (int i = 0; i < newHeight; i++) {
			for (int j = 0; j < newWidth; j++) {
				// Find the fractional coordinates on the original grid
				double x = j * xRatio;
				double y = i * yRatio;

				// Grab the integer coordinates of the 4 surrounding pixels
				int xL = (int) Math.floor(x);
				int xH = (int) Math.ceil(x);
				int yL = (int) Math.floor(y);
				int yH = (int) Math.ceil(y);

				// Calculate the fractional weights
				double xWeight = x - xL;
				double yWeight = y - yL;

				// Get the pixel values of the 4 neighbors
				double topLeft  = source[yL][xL];
				double topRight = source[yL][xH];
				double bottomLeft = source[yH][xL];
				double bottomRight = source[yH][xH];

				// Interpolate horizontally between top two pixels
				double topInterpolation = topLeft * (1 - xWeight) + topRight * xWeight;

				// Interpolate horizontally between bottom two pixels
				double bottomInterpolation = bottomLeft * (1 - xWeight) + bottomRight * xWeight;

				// Interpolate vertically between the two horizontal results
				result[i][j] = topInterpolation * (1 - yWeight) + bottomInterpolation * yWeight;
			}
		}
		return result;
	}

	public static void main(String[] args) {
		// A simple 2x2 matrix
		double[][] original = {
				{10.0, 20.0},
				{30.0, 40.0}
		};

		// Resize it smoothly to a 4x4 matrix
		double[][] smoothImage = resize(original, 4, 4);

		// Print the smooth gradient result
		for (double[] row : smoothImage) {
			for (double val : row) {
				System.out.printf("%5.1f ", val);
			}
			System.out.println();
		}
	}
}
