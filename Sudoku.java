import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class Solution {

	public static void main(String[] args) throws IOException {

		// read lines from file
		String fileName = "sudoku.txt";
		int sum = 0;
		FileReader fr = new FileReader(fileName);
		BufferedReader br = new BufferedReader(fr);
		int sudoku[][] = new int[9][9];
		String strLine;
		while ((strLine = br.readLine()) != null) {
			for (int i = 0; i < 9; i++) {
				strLine = br.readLine();
				for (int j = 0; j < 9; j++)
					sudoku[i][j] = strLine.charAt(j) - '0';
			}

			// solving individual sudoku
			solve(sudoku, 0, 0);

			// sum all the three top left corner values
			sum += 100 * sudoku[0][0] + 10 * sudoku[0][1] + sudoku[0][2];
		}
		System.out.println(sum);
	}

	// method to solve sudoku
	public static boolean solve(int sudoku[][], int row, int col) {
		boolean complete = false;
		int i, j = col;

		for (i = row; i < 9; i++) {
			for (; j < 9; j++) {
				if (sudoku[i][j] == 0) {
					int nextCol = (j + 1) % 9;
					int nextRow = i;
					if (nextCol == 0) {
						nextRow = i + 1;
						complete = (nextRow == 9);
					}
					for (int v = 1; v <= 9; v++) {
						sudoku[i][j] = v;
						if (check(sudoku, i, j)) {
							if (complete || solve(sudoku, nextRow, nextCol)) {
								return true;
							}
						}
					}
					sudoku[i][j] = 0;
					return false;
				}
			}
			j = 0;
		}
		return true;
	}

	// check if the value added is right or wrong 
	// at row, column and the current grid
	public static boolean check(int sudoku[][], int row, int col) {
		for (int i = 0; i < 9; ++i) {
			int p = 3 * (row / 3) + i % 3, q = 3 * (col / 3) + i / 3;
			if (i != row
					&& sudoku[i][col] == sudoku[row][col] // col
					|| i != col
					&& sudoku[row][i] == sudoku[row][col] // row
					|| (p != row || q != col)
					&& sudoku[p][q] == sudoku[row][col]) { // box
				return false;
			}
		}
		return true;
	}

}
