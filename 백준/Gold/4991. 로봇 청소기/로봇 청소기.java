import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int W, H, answer;
	static char[][] map;
	static List<Point> points;
	static int[] numbers;
	static boolean[] visitNum;
	static int[][] graph;
	static int[] dw = { -1, 0, 1, 0 }, dh = { 0, 1, 0, -1 };

	static class Point {
		int w, h;

		public Point(int w, int h) {
			this.w = w;
			this.h = h;
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
			if (W == 0 && H == 0)
				break;

			answer = Integer.MAX_VALUE; // 최소 이동 횟수
			map = new char[H][W]; // 방 정보
			points = new ArrayList<>(); // 더러운 칸 목록 (인덱스 0은 로봇청소기 초기 위치)

			for (int h = 0; h < H; h++) {
				String input = br.readLine();
				for (int w = 0; w < W; w++) {
					map[h][w] = input.charAt(w);
					if (map[h][w] == 'o') points.add(0, new Point(w, h));
					else if (map[h][w] == '*') points.add(new Point(w, h));
				}
			}

			int size = points.size();
			numbers = new int[size]; // 각 칸의 순서 정보
			visitNum = new boolean[size];
			graph = new int[size][size]; // 각 칸들 간의 거리 정보

			perm(1, 0, size);
			sb.append(answer).append("\n");
		}

		System.out.println(sb);
	}

	public static void perm(int cnt, int sum, int size) {
		if (cnt == size || answer < sum || answer == -1) {
			answer = Math.min(answer, sum);
			return;
		}

		for (int i = 1; i < size; i++) {
			if (visitNum[i]) continue;

			if (graph[numbers[cnt - 1]][i] == 0) {
				// 이전 좌표와 현재 좌표 사이의 거리
				int dis = bfs(points.get(numbers[cnt - 1]), points.get(i));
				graph[numbers[cnt - 1]][i] = dis;
				graph[i][numbers[cnt - 1]] = dis;
			}

			visitNum[i] = true;
			numbers[cnt] = i;
			perm(cnt + 1, sum + graph[numbers[cnt - 1]][i], size);
			visitNum[i] = false;
		}

	}

	public static int bfs(Point start, Point end) {
		int cnt = 0;
		Queue<Point> q = new LinkedList<>();
		boolean[][] visit = new boolean[H][W];
		q.offer(start);
		visit[start.h][start.w] = true;

		while (!q.isEmpty()) {
			cnt++; // 좌표 간 거리 카운팅
			for (int i = 0, size = q.size(); i < size; i++) {
				Point now = q.poll();
				for (int d = 0; d < 4; d++) {
					int nw = now.w + dw[d];
					int nh = now.h + dh[d];
					if (!isIn(nw, nh) || visit[nh][nw] || map[nh][nw] == 'x') continue;

					if (nw == end.w && nh == end.h) return cnt;
					q.offer(new Point(nw, nh));
					visit[nh][nw] = true;
				}
			}
		}

		answer = -1;
		return cnt;
	}

	public static boolean isIn(int w, int h) {
		return 0 <= w && w < W && 0 <= h && h < H;
	}
}