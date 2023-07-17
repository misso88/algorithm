import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, K, answer;
	static Ground[][] map;
	static int[][] A;
	static int[] dr = { -1, -1, 0, 1, 1, 1, 0, -1 }, dc = { 0, 1, 1, 1, 0, -1, -1, -1 };

	static class Ground {
		int energy, laterEnergy;
		PriorityQueue<Integer> trees;

		public Ground(int energy, int laterEnergy) {
			this.energy = energy;
			this.laterEnergy = laterEnergy;
			trees = new PriorityQueue<>();
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 땅 크기
		M = Integer.parseInt(st.nextToken()); // 나무 개수
		K = Integer.parseInt(st.nextToken()); // 햇수

		map = new Ground[N][N];
		A = new int[N][N]; // 각 칸에 추가될 양분의 양
		answer = M; // 살아있는 나무의 개수

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = new Ground(5, 0); // 양분 초기값
				A[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			map[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1].trees
					.offer(Integer.parseInt(st.nextToken()));
		}

		for (int k = 0; k < K; k++) {
			spring();
			summer();
			autumn();
			winter();
		}

		System.out.println(answer);
	}

	public static void spring() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				List<Integer> tList = new ArrayList<>();
				for (int i = 0, size = map[r][c].trees.size(); i < size; i++) {
					int tree = map[r][c].trees.poll();
					if (tree <= map[r][c].energy) {
						// 자신의 나이만큼 양분을 먹고, 나이가 1 증가한다.
						map[r][c].energy -= tree;
						tList.add(tree + 1);
					} else {
						// 양분을 먹지 못하면 즉사하고, 여름의 양분이 된다.
						map[r][c].laterEnergy += tree / 2;
						answer--; i--; size--;
					}
				}
				map[r][c].trees.addAll(tList);
			}
		}
	}

	public static void summer() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				// 봄에 죽은 나무가 양분으로 추가된다.
				map[r][c].energy += map[r][c].laterEnergy;
				map[r][c].laterEnergy = 0;
			}
		}
	}

	public static void autumn() {
		int[][] babyTree = new int[N][N];

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				List<Integer> tList = new ArrayList<>(map[r][c].trees);
				for (int i = 0, size = tList.size(); i < size; i++) {
					// 나무의 나이가 5의 배수일 때 번식한다.
					int tree = tList.get(i);
					if (tree % 5 != 0) continue;

					for (int d = 0; d < 8; d++) {
						int nr = r + dr[d];
						int nc = c + dc[d];
						if (!isIn(nr, nc)) continue;

						babyTree[nr][nc]++;
						answer++;
					}
				}
			}
		}

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				for (int i = 0, size = babyTree[r][c]; i < size; i++) {
					// 나이가 1인 나무가 생긴다.
					map[r][c].trees.add(1);
				}
			}
		}
	}

	public static void winter() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				// S2D2가 땅을 돌아다니면서 땅에 양분을 추가한다.
				map[r][c].energy += A[r][c];
			}
		}
	}

	public static boolean isIn(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < N;
	}
}