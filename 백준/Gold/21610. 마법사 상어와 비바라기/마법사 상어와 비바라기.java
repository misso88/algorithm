import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] A;
	static List<Cloud> cloudList;
	static int[] dx = { -1, -1, 0, 1, 1, 1, 0, -1 }, dy = { 0, -1, -1, -1, 0, 1, 1, 1 }; // 팔방탐색

	static class Cloud {
		int x, y;

		public Cloud(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 격자 크기
		M = Integer.parseInt(st.nextToken()); // 이동 수

		A = new int[N][N]; // 바구니에 저장된 물의 양

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				A[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		// 구름 초기 위치
		cloudList = new ArrayList<>();
		cloudList.add(new Cloud(0, N - 1));
		cloudList.add(new Cloud(1, N - 1));
		cloudList.add(new Cloud(0, N - 2));
		cloudList.add(new Cloud(1, N - 2));

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			move(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()));
			copyBug();
			makeCloud();
		}

		// 물의 양 합 출력
		int ans = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				ans += A[r][c];
			}
		}
		System.out.println(ans);
	}

	public static void move(int d, int s) {
		// 1. 모든 구름이 d 방향 s 칸 이동
		for (int i = 0, size = cloudList.size(); i < size; i++) {
			Cloud now = cloudList.remove(0);
			int nx = (now.x + (dx[d] * s)) % N;
			int ny = (now.y + (dy[d] * s)) % N;

			if (nx < 0) nx = N + (nx % N);
			if (ny < 0) ny = N + (ny % N);

			// 2. 각 구름에 비가 내려 바구니 물의 양 1 증가
			A[ny][nx]++;

			cloudList.add(new Cloud(nx, ny));
		}
	}

	public static void copyBug() {
		// 4. 물복사버그 마법
		for (int i = 0, size = cloudList.size(); i < size; i++) {
			Cloud now = cloudList.get(i);

			int cnt = 0;
			for (int d = 1; d < 8; d += 2) {
				int nx = now.x + dx[d];
				int ny = now.y + dy[d];
				if (!isIn(nx, ny)) continue;

				if (0 < A[ny][nx]) cnt++; 
			}
			// 대각선에 물이 있는 바구니 수만큼 증가
			A[now.y][now.x] += cnt;
		}
	}

	public static void makeCloud() {
		// 5. 바구니 물의 양이 2 이상이면 해당 좌표에 구름 생성, 물의 양 2 감소
		List<Cloud> preCloudList = new ArrayList<>(); // 이전 구름 위치 정보
		preCloudList.addAll(cloudList);
		
		// 3. 구름이 모두 사라진다
		cloudList.clear();

		for (int r = 0; r < N; r++) {
			label: for (int c = 0; c < N; c++) {
				if (A[r][c] < 2) continue;

				// 물의 양이 2 이상일 때
				for (int i = 0, size = preCloudList.size(); i < size; i++) {
					// 구름이 사라진 칸에는 구름 생성 X
					if (r == preCloudList.get(i).y && c == preCloudList.get(i).x) {
						preCloudList.remove(i);
						continue label;
					}
				}
				cloudList.add(new Cloud(c, r));
				A[r][c] -= 2;
			}
		}
	}

	public static boolean isIn(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}
}
