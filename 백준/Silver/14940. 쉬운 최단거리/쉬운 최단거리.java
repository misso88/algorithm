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
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 세로 크기
		M = Integer.parseInt(st.nextToken()); // 가로 크기
		int[][] map = new int[N][M];			  // 지도
		
		Queue<Point> q = new LinkedList<>();
		boolean[][] isVisit = new boolean[N][M];
		int[] dr = {-1, 0, 1, 0}, dc = {0, 1, 0, -1};
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int input = Integer.parseInt(st.nextToken());
				map[i][j] = input == 1 ? -1 : input;
				
				if(map[i][j] == 2) {
					map[i][j] = 0;
					q.offer(new Point(i, j));
					isVisit[i][j] = true;
				}
			}
		}
		
		int dist = 1;
		
		while(!q.isEmpty()) {
			for (int i = 0, size = q.size(); i < size; i++) {
				Point now = q.poll();
				
				for (int d = 0; d < 4; d++) {
					int nr = now.r + dr[d];
					int nc = now.c + dc[d];
					if(!isIn(nr, nc) || isVisit[nr][nc] || map[nr][nc] == 0) continue;
					
					q.offer(new Point(nr, nc));
					isVisit[nr][nc] = true;
					map[nr][nc] = dist;
				}
			}
			dist++;
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				sb.append(map[i][j]).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
	
	public static boolean isIn(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < M;
	}
}