import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N, M, size;
	static int ans = Integer.MAX_VALUE;
	static int[][] office;
	static List<CCTV> cctvList;
	static int[] comb;
	static int[] dx = { 0, 1, 0, -1 }, dy = { -1, 0, 1, 0 }; // 상우하좌

	static class CCTV {
		int x, y; 
		List<List<Integer>> dir = new ArrayList<>();

		public CCTV(int x, int y, List<List<Integer>> dir) {
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 세로
		M = Integer.parseInt(st.nextToken()); // 가로

		office = new int[N][M];
		cctvList = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				office[i][j] = Integer.parseInt(st.nextToken());
				if (office[i][j] == 0 || office[i][j] == 6) continue;

				switch (office[i][j]) {
				case 1:
					cctvList.add(new CCTV(j, i, Arrays.asList(
							Arrays.asList(0), Arrays.asList(1), Arrays.asList(2), Arrays.asList(3)
							)));
					break;
				case 2:
					cctvList.add(new CCTV(j, i, Arrays.asList(
							Arrays.asList(0, 2), Arrays.asList(1, 3)
							)));
					break;
				case 3:
					cctvList.add(new CCTV(j, i, Arrays.asList(
							Arrays.asList(0, 1), Arrays.asList(1, 2), Arrays.asList(2, 3), Arrays.asList(0, 3)
							)));
					break;
				case 4:
					cctvList.add(new CCTV(j, i, Arrays.asList(
							Arrays.asList(0, 1, 2), Arrays.asList(1, 2, 3), Arrays.asList(0, 2, 3), Arrays.asList(0, 1, 3)
							)));
					break;
				case 5:
					cctvList.add(new CCTV(j, i, Arrays.asList(Arrays.asList(0, 1, 2, 3))));
					break;
				}
			}
		}

		size = cctvList.size();
		comb = new int[size];
		selectDir(0, 0);
		System.out.println(ans);
	}

	public static void selectDir(int idx, int start) {
		if (idx == size) {
			int[][] room = new int[N][M];
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < cctvList.get(i).dir.get(comb[i]).size(); j++) {
					monitoring(room, cctvList.get(i).x, cctvList.get(i).y, cctvList.get(i).dir.get(comb[i]).get(j));
				}
			}

			int cnt = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (room[i][j] == 7 || office[i][j] != 0) continue;

					cnt++;
				}
			}
			ans = Math.min(ans, cnt);
			return;
		}

		for (int i = start; i < size; i++) {
			for (int d = 0; d < cctvList.get(idx).dir.size(); d++) {
				comb[idx] = d;
				selectDir(idx + 1, i + 1);
			}
		}

	}

	public static void monitoring(int[][] room, int x, int y, int d) {
		while (true) {
			x = x + dx[d];
			y = y + dy[d];
			if (!isIn(x, y) || office[y][x] == 6) break;

			if (room[y][x] != 0) continue;

			room[y][x] = 7; // 감시 영역 표시
		}
	}

	public static boolean isIn(int x, int y) {
		return x >= 0 && x < M && y >= 0 && y < N;
	}
}