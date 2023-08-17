import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine()); // 계단 개수

		int[] steps = new int[N + 1]; // 계단 점수
		int[] score = new int[N + 1]; // 각 계단의 최대 점수

		for (int i = 1; i <= N; i++) {
			steps[i] = Integer.parseInt(br.readLine());
		}

		// 초기값
		score[1] = steps[1];
		if (1 < N) score[2] = score[1] + steps[2];

		for (int i = 3; i <= N; i++) {
			score[i] = Math.max(score[i - 2], score[i - 3] + steps[i - 1]) + steps[i];
		}

		System.out.println(score[N]);
	}
}