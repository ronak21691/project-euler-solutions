mport java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MaxSumPath {
	public static void main(String args[]) throws IOException {
		String fileName = "triangle.txt";
		FileReader fr = new FileReader(fileName);
		BufferedReader br = new BufferedReader(fr);
		int triangle[][] = { {0}, {0} };
		int lines = 0;
		String s;
		while ((s = br.readLine()) != null)
			lines++;
		FileReader fr_ = new FileReader(fileName);
		BufferedReader br_ = new BufferedReader(fr_);
		int j = 0;
		String strLine[] = new String[lines];
		triangle = new int[lines][lines];
		while ((s = br_.readLine()) != null) {
			strLine = s.split(" ");
			for (int i = 0; i < strLine.length; i++) {
				triangle[j][i] = Integer.parseInt(strLine[i]);
			}
			j++;
		}
		fr.close();
		fr_.close();

		for (int i = lines - 2; i >= 0; i--) {
			for (int k = 0; k <= i; k++) {
				triangle[i][k] += Math.max(triangle[i + 1][k],
						triangle[i + 1][k + 1]);
			}
		}
		System.out.println(triangle[0][0]);
	}
}
