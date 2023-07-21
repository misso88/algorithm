import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N, M, K;
	static List<FireBall>[][] map;
	static int[] dr = { -1, -1, 0, 1, 1, 1, 0, -1 }, dc = { 0, 1, 1, 1, 0, -1, -1, -1 };

	static class FireBall {
		int m, s, d; // 질량, 속력, 방향

		public FireBall(int m, int s, int d) {
			this.m = m;
			this.s = s;
			this.d = d;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 격자 크기
		M = Integer.parseInt(st.nextToken()); // 파이어볼 개수
		K = Integer.parseInt(st.nextToken()); // 이동 수

		map = initMap();

		int r, c;
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			r = Integer.parseInt(st.nextToken()) - 1;
			c = Integer.parseInt(st.nextToken()) - 1;
			map[r][c].add(new FireBall(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken())));
		}

		while (K-- > 0) {
			move();
			divideFB();
		}

		System.out.println(sumM());
	}

	public static void move() {
		List<FireBall>[][] moveMap = initMap();

		int nr, nc;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (map[r][c].size() == 0)
					continue;

				for (int i = 0, size = map[r][c].size(); i < size; i++) {
					FireBall now = map[r][c].get(i);
					nr = (r + (dr[now.d] * now.s)) % N;
					nc = (c + (dc[now.d] * now.s)) % N;

					// 좌표가 음수일 때 처리
					moveMap[nr < 0 ? N + nr : nr][nc < 0 ? N + nc : nc].add(now);
				}
			}
		}

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				map[r][c] = moveMap[r][c];
			}
		}
	}

	public static void divideFB() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (map[r][c].size() < 2)
					continue;

				boolean isEven = map[r][c].get(0).d % 2 == 0 ? true : false; // 짝수 여부
				int sumM = 0, sumS = 0, d = 0, size = map[r][c].size();
				for (int i = 0; i < size; i++) {
					sumM += map[r][c].get(i).m;
					sumS += map[r][c].get(i).s;
					if (isEven != (map[r][c].get(i).d % 2 == 0))
						d = 1;
				}

				map[r][c].clear();
				if (sumM / 5 == 0)
					continue;

				for (int i = 0; i < 4; i++) {
					map[r][c].add(new FireBall(sumM / 5, sumS / size, d));
					d += 2;
				}
			}
		}
	}

	public static int sumM() {
		int sum = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				for (int i = 0, size = map[r][c].size(); i < size; i++) {
					sum += map[r][c].get(i).m;
				}
			}
		}
		return sum;
	}

	public static List[][] initMap() {
		List<FireBall>[][] copyMap;
		copyMap = new ArrayList[N][N];
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				copyMap[r][c] = new ArrayList<>(); // 초기화
			}
		}
		return copyMap;
	}
}