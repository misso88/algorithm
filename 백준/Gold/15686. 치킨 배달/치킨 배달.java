import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N, M, ans = Integer.MAX_VALUE;
	static int[][] city;
	static List<Pos> houseList = new ArrayList<>(); // 모든 집 정보
	static List<Pos> chickenList = new ArrayList<>(); // 모든 치킨집 정보
	static Pos[] selectedChicken; // 선택된 치킨집 정보
	static int houseCnt; // 총 집 수
	static int chickenCnt; // 총 치킨집 수
	static int[] dx = { 0, 0, -1, 1 }, dy = { -1, 1, 0, 0 }; // 상하좌우

	static class Pos {
		int x, y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 도시 크기
		M = Integer.parseInt(st.nextToken()); // 치킨집 최대 수

		city = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				// 0:빈칸 1:집 2:치킨집
				city[i][j] = Integer.parseInt(st.nextToken());

				if (city[i][j] == 1) {
					houseList.add(new Pos(j, i));
					houseCnt++;
				} else if (city[i][j] == 2) {
					chickenList.add(new Pos(j, i));
					chickenCnt++;
				}
			}
		}

		selectedChicken = new Pos[M];
		comb(0, 0);
		System.out.println(ans);
	}

	public static void comb(int cnt, int start) {
		if (cnt == M) {
			int totalDis = 0;
			for (int h = 0; h < houseCnt; h++) {
				int min = Integer.MAX_VALUE;
				for (int c = 0; c < M; c++) {
					min = Math.min(min, Math.abs(selectedChicken[c].x - houseList.get(h).x)
							+ Math.abs(selectedChicken[c].y - houseList.get(h).y));
				}
				totalDis += min;
			}
			ans = Math.min(ans, totalDis);
			return;
		}

		for (int i = start; i < chickenCnt; i++) {
			selectedChicken[cnt] = chickenList.get(i);
			comb(cnt + 1, i + 1);
		}
	}
}
