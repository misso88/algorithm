package algorithm.BOJ.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class G3_20058_마법사상어와파이어스톰 {
	static int N, Q, total;
	static int[][] map, playMap;
	static int[] dr = { -1, 0, 1, 0 }, dc = { 0, 1, 0, -1 };

	static class Point {
		int r, c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = (int) Math.pow(2, Integer.parseInt(st.nextToken())); // 격자 크기
		Q = Integer.parseInt(st.nextToken()); // 파이어스톰 시전 횟수

		map = new int[N][N];
		playMap = new int[N][N];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				// 얼음의 양
				map[r][c] = Integer.parseInt(st.nextToken());
				total += map[r][c];
			}
		}

		st = new StringTokenizer(br.readLine());
		for (int q = 0; q < Q; q++) {
			firestorm(Integer.parseInt(st.nextToken()));
			melt();
		}

		sb.append(total).append("\n");
		sb.append(getMaxIce()).append("\n");

		System.out.println(sb);
	}

	public static void firestorm(int L) {
		int l = (int) Math.pow(2, L); // 부분격자 크기

		for (int r = 0; r < N; r += l) {
			for (int c = 0; c < N; c += l) {
				rotate(r, c, l);
			}
		}

		map = copyArray(playMap);
	}

	// map[startR][startC]에서 L크기만큼 시계방향으로 90도 회전
	public static void rotate(int startR, int startC, int L) {
		for (int r = startR; r < startR + L; r++) {
			for (int c = startC; c < startC + L; c++) {
				playMap[startR + c - startC][startC + L - 1 - r + startR] = map[r][c];
			}
		}
	}

	// 얼음이 없는 칸과 2개 이상 인접하면 얼음의 양이 1씩 감소
	public static void melt() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (map[r][c] == 0)
					continue;

				int cnt = 0; // 인접한 칸 중에 얼음이 있는 칸 수
				for (int d = 0; d < 4; d++) {
					if (isIn(r + dr[d], c + dc[d]) && map[r + dr[d]][c + dc[d]] > 0)
						cnt++;
				}
				if (cnt < 3) {
					playMap[r][c]--;
					total--;
				}
			}
		}

		map = copyArray(playMap);
	}

	public static int getMaxIce() {
		int maxCnt = 0;

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (map[r][c] == 0)
					continue;
				maxCnt = Math.max(maxCnt, bfs(r, c));
			}
		}

		return maxCnt;
	}

	private static int bfs(int r, int c) {
		int cnt = 0;
		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(r, c));

		while (!q.isEmpty()) {
			Point now = q.poll();
			for (int d = 0; d < 4; d++) {
				int nr = now.r + dr[d];
				int nc = now.c + dc[d];
				if (!isIn(nr, nc) || map[nr][nc] == 0)
					continue;

				q.offer(new Point(nr, nc));
				map[nr][nc] = 0;
				cnt++;
			}
		}

		return cnt;
	}

	public static int[][] copyArray(int[][] arr) {
		int len = arr.length;
		int[][] copyArr = new int[len][len];
		for (int i = 0; i < len; i++) {
			copyArr[i] = arr[i].clone();
		}
		return copyArr;
	}

	public static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}
}
