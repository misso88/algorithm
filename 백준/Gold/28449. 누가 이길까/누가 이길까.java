import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // HI 인원 수
		int M = Integer.parseInt(st.nextToken()); // ARC 인원 수

		int[] teamHI = new int[N]; 				  // HI 팀원들의 코딩실력
		int[] teamARC = new int[M]; 			  // ARC 팀원들의 코딩실력
		int[] setHI = new int[100001];
		int[] setARC = new int[100001];
		int[] calHI = new int[100001];
		int[] calARC = new int[100001];

		st = new StringTokenizer(br.readLine());
		for (int n = 0; n < N; n++) {
			teamHI[n] = Integer.parseInt(st.nextToken());
			setHI[teamHI[n]]++;
		}

		st = new StringTokenizer(br.readLine());
		for (int m = 0; m < M; m++) {
			teamARC[m] = Integer.parseInt(st.nextToken());
			setARC[teamARC[m]]++;
		}

		for (int i = 1; i <= 100000; i++) {
			calHI[i] = calHI[i - 1] + setHI[i - 1];
			calARC[i] = calARC[i - 1] + setARC[i - 1];
		}

		long total = (long) N * (long) M;
		long winHI = 0;
		long winARC = 0;
		
		for (int n = 0; n < N; n++) {
			winHI += calARC[teamHI[n]];
		}

		for (int m = 0; m < M; m++) {
			winARC += calHI[teamARC[m]];
		}

		sb.append(winHI).append(" ").append(winARC).append(" ").append(total - winHI - winARC);

		System.out.println(sb);
	}
}