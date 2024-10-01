import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;

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

		N = Integer.parseInt(st.nextToken()); // 세로 크기
		M = Integer.parseInt(st.nextToken()); // 가로 크기

		char[][] map = new char[N][M];
		boolean[][] isVisit = new boolean[N][M];
		Queue<Point> q = new LinkedList<>();
		int[] dr = { -1, 0, 1, 0 }, dc = { 0, 1, 0, -1 };

		for (int n = 0; n < N; n++) {
			String input = br.readLine();
			for (int m = 0; m < M; m++) {
				map[n][m] = input.charAt(m);
				if (map[n][m] == 'I') {
					q.offer(new Point(n, m));
					isVisit[n][m] = true;
				}
			}
		}
		
		int result = 0;

		while (!q.isEmpty()) {
			for (int i = 0, size = q.size(); i < size; i++) {
				Point now = q.poll();
				for (int d = 0; d < 4; d++) {
					int nr = now.r + dr[d];
					int nc = now.c + dc[d];
					if(!isIn(nr, nc) || isVisit[nr][nc] || map[nr][nc] == 'X') continue;
					
					q.offer(new Point(nr, nc));
					isVisit[nr][nc] = true;
					if(map[nr][nc] == 'P') result++;
				}
			}
		}

		System.out.println(result == 0 ? "TT" : result);
	}

	public static boolean isIn(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < M;
	}
}