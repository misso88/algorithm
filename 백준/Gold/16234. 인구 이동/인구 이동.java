import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, L, R, answer = -1;
	static Land[][] map;
	static boolean[][] visit;
	static int[] dr = { -1, 0, 1, 0 }, dc = { 0, 1, 0, -1 };

	static class Land {
		int p; // 인구수
		boolean[] d; // 공유된 국경선 방향

		public Land(int p) {
			this.p = p;
			d = new boolean[4];
		}
	}

	static class Point {
		int r, c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 땅 크기
		L = Integer.parseInt(st.nextToken()); // 국경선 공유를 기준하는 인구차이 최소
		R = Integer.parseInt(st.nextToken()); // 국경선 공유를 기준하는 인구차이 최대

		map = new Land[N][N];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = new Land(Integer.parseInt(st.nextToken()));
			}
		}

		// 인구 이동이 발생하는 일수가 2,000번 보다 작거나 같은 입력만 주어진다.
		while (answer++ <= 2000) {
			if (!comparePopulation()) break; // 인구 수 비교

			visit = new boolean[N][N];
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (visit[r][c]) continue;
					movePopulation(r, c); // 인구 이동
				}
			}
		}

		System.out.println(answer);
	}

	public static boolean comparePopulation() {
		boolean check = false;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				for (int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					if (!isIn(nr, nc)) continue;

					// 인접한 나라의 인구 차이가 L명 이상, R명 이하라면, 국경선을 공유
					int diff = Math.abs(map[r][c].p - map[nr][nc].p);
					if (L <= diff && diff <= R) {
						check = true;
						map[r][c].d[d] = true;
					} else map[r][c].d[d] = false;
				}
			}
		}
		return check;
	}

	public static void movePopulation(int r, int c) {
		int sum = map[r][c].p; // 연합 인구 수
		Queue<Point> q = new LinkedList<>();
		List<Point> list = new ArrayList<>(); // 연합 나라 목록

		list.add(new Point(r, c));
		q.offer(new Point(r, c));
		visit[r][c] = true;

		// 연합 나라 찾기
		while (!q.isEmpty()) {
			Point now = q.poll();
			for (int d = 0; d < 4; d++) {
				int nr = now.r + dr[d];
				int nc = now.c + dc[d];
				if (!isIn(nr, nc) || visit[nr][nc] || !map[now.r][now.c].d[d]) continue;

				sum += map[nr][nc].p;
				list.add(new Point(nr, nc));
				q.offer(new Point(nr, nc));
				visit[nr][nc] = true;
			}
		}

		// 인구 이동
		int size = list.size();
		int avg = sum / size; // 연합 나라의 평균 인구 수
		for (int i = 0; i < size; i++) {
			map[list.get(i).r][list.get(i).c].p = avg;
		}
	}

	public static boolean isIn(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < N;
	}
}