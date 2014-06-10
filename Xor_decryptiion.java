import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Hello {
	public static void main(String args[]) throws IOException {
		String fileName = "cipher.txt";
		FileReader fr = new FileReader(fileName);
		BufferedReader br = new BufferedReader(fr);
		int message[];
		String s = br.readLine();
		String str[] = new String[9999];
		str = s.split(",");
		message = new int[str.length];
		int res;
		String key = "";
		int temp[] = new int[1000];
		for (int i = 0; i < str.length; i++)
			message[i] = Integer.parseInt(str[i]);
		int max = -1;
		int l = 0;
		for (int z = 0; z < 123; z++)
			temp[z] = 0;
		for (int k = 0; k < 3; k++) {
			max = -1;
			for (int i = 97; i < 123; i++) {
				for (int j = k; j < message.length; j += 3) {
					res = message[j] ^ i;
					temp[res]++;
				}
			}
			for (int z = 97; z < 123; z++) {
				if (max < temp[z]) {
					max = temp[z];
					l = z;
				}
			}
			for (int z = 0; z < 123; z++)
				temp[z] = 0;
			char ch = (char) l;
			key += ch;
		}
		int sum = 0;
		int z = 0;
		for (int k = 0; k < 3; k++) {
			if (k == 0)
				z = 'g';
			else if (k == 1)
				z = 'o';
			else
				z = 'd';
			for (int i = k; i < message.length; i += 3) {
				sum += message[i] ^ z;
			}
		}
		System.out.println(sum);
	}
}
