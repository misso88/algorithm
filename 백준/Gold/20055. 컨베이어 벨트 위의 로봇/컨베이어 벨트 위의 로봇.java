import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, K, step;
	static int[] durability;
	static boolean[] isRobot;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 컨베이터 벨트 길이(벨트 길이는 2N)
		K = Integer.parseInt(st.nextToken()); // 내구도 0인 칸 수 제한

		durability = new int[2 * N]; // 내구도
		isRobot = new boolean[2 * N]; // 로봇이 올라가있는지 여부

		st = new StringTokenizer(br.readLine());
		for (int n = 0; n < 2 * N; n++) {
			durability[n] = Integer.parseInt(st.nextToken());
		}

		int start = 0; // 올리는 위치
		int end = N - 1; // 내리는 위치

		// 내구도가 0인 칸의 개수가 K개 이상이면 종료한다.
		while (0 < K) {
			step++;

			// 1. 벨트가 각 탄 위에 있는 로봇과 함께 한 칸 회전한다.
			start = (start == 0 ? 2 * N : start) - 1;
			end = (start + N - 1) % (2 * N);
			out(end);

			// 2. 가장 먼저 벨트에 올라간 로봇부터 벨트가 회전하는 방향으로 한 칸 이동할 수 있다면 이동한다.
			int now = end;
			while (now != start) {
				int next = now; // 로봇이 이동할 다음 칸
				now = --now == -1 ? 2 * N - 1 : now;
				if (!isRobot[now] || isRobot[next] || durability[next] < 1) continue;

				isRobot[now] = false;
				isRobot[next] = true;
				durability[next]--;
				if (durability[next] == 0) K--;
			}
			out(end);

			// 3. 올리는 위치에 있는 칸의 내구도가 0이 아니면 올리는 위치에 로봇을 올린다.
			in(start);
		}

		System.out.println(step);
	}

	public static void in(int point) {
		if (0 < durability[point]) {
			isRobot[point] = true;
			durability[point]--;
			if (durability[point] == 0) K--;
		}
	}

	public static void out(int point) {
		// 내리는 위치에 로봇이 있으면 즉시 내리기
		if (isRobot[point]) isRobot[point] = false;
	}
}