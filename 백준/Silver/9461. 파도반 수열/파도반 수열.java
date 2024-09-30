import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		long[] sequence = new long[101];

		sequence[1] = 1;
		sequence[2] = 1;
		sequence[3] = 1;

		for (int i = 4; i <= 100; i++) {
			sequence[i] = sequence[i - 2] + sequence[i - 3];
		}

		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			int input = Integer.parseInt(br.readLine());
			sb.append(sequence[input]).append("\n");
		}
		
		System.out.println(sb);
	}
}