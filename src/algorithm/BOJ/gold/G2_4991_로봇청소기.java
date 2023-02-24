package algorithm.BOJ.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class G2_4991_로봇청소기 {
	static int W, H, dirtyCnt;
	static Point[][] room;
	static Queue<Point> q;
	static int[] dr = { -1, 0, 1, 0 }, dc = { 0, 1, 0, -1 };

	static class Point {
		char data;
		int r, c, moveCnt, cleanCnt; // 행, 열, 움직임횟수, 청소횟수

		public Point(char data, int r, int c, int moveCnt, int cleanCnt) {
			this.data = data;
			this.r = r;
			this.c = c;
			this.moveCnt = moveCnt;
			this.cleanCnt = cleanCnt;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		while (true) {
			st = new StringTokenizer(br.readLine());
			W = Integer.parseInt(st.nextToken()); // 가로
			H = Integer.parseInt(st.nextToken()); // 세로
			if (W + H == 0)
				break;

			room = new Point[H][W];
			q = new LinkedList<>();

			for (int h = 0; h < H; h++) {
				String input = br.readLine();
				for (int w = 0; w < W; w++) {
					room[h][w] = new Point(input.charAt(w), h, w, 0, 0);
					if (room[h][w].data == 'o')
						q.offer(room[h][w]); // 청소기 현재 위치
					else if (room[h][w].data == '*')
						dirtyCnt++; // 총 더러운 방
				}
			}
//			sb.append(bfs()).append("\n");
			System.out.println(bfs());
		}

		System.out.println(sb);
	}

	public static int bfs() {
		while (!q.isEmpty()) {
			Point now = q.poll();
			for (int d = 0; d < 4; d++) {
				int nr = now.r + dr[d];
				int nc = now.c + dc[d];
				if (!isIn(nr, nc) || room[nr][nc].data == 'x' || room[nr][nc].cleanCnt < room[now.r][now.c].cleanCnt
						|| room[nr][nc].moveCnt > W * H)
					continue;

				room[nr][nc].moveCnt = room[now.r][now.c].moveCnt + 1;
				if (room[nr][nc].data == '*')
					room[nr][nc].cleanCnt++;
				if (room[nr][nc].cleanCnt == dirtyCnt)
					return room[nr][nc].moveCnt;

				q.offer(room[nr][nc]);
			}
		}
		return -1;
	}

	public static boolean isIn(int r, int c) {
		return r >= 0 && r < H && c >= 0 && c < W;
	}
}
