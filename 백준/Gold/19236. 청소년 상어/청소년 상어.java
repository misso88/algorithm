import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int answer;
	static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1}, dc = {0, -1, -1, -1, 0, 1, 1, 1}; // 12시 부터 반시계 방향
	
	static class Fish implements Comparable<Fish>{
		int r, c, num, dir;
		
		public Fish(int r, int c, int num, int dir) {
			this.r = r;
			this.c = c;
			this.num = num;
			this.dir = dir;
		}

		@Override
		public int compareTo(Fish o) {
			return o.num - this.num;
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		Fish[][] map = new Fish[4][4];
		
		for (int r = 0; r < 4; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < 4; c++) {
				map[r][c] = new Fish(r, c, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) - 1);
			}
		}
		
		// 1. 상어가 (0, 0)에 있는 물고기를 먹고, 해당 물고기의 방향을 가진다.
		play(map[0][0].num, new Fish(0, 0, 0, map[0][0].dir), map); // 상어는 0번
		System.out.print(answer);
	}
	
	public static void play(int sum, Fish shark, Fish[][] map) {
		// 2. 물고기는 번호가 작은 순서대로 이동한다.
		map[shark.r][shark.c] = shark;
		moveFish(map);
		
		// 3. 상어가 이동하고, 물고기를 먹는다.
		map[shark.r][shark.c] = null;
		boolean isHunting = false;
		while(true) {
			shark.r += dr[shark.dir];
			shark.c += dc[shark.dir];
			// 공간에서 벗어나면 집으로 이동(종료)
			if(!isIn(shark.r, shark.c)) break; 
			// 상어는 물고기가 없는 칸은 이동X
			if(map[shark.r][shark.c] == null) continue;
			
			isHunting = true;
			play(sum + map[shark.r][shark.c].num, new Fish(shark.r, shark.c, 0, map[shark.r][shark.c].dir), copyMap(map));
		}
		
		// 4. 상어가 이동할 수 있는 칸이 없으면 종료된다.
		if(!isHunting) answer = Math.max(answer, sum);
	}
	
	public static void moveFish(Fish[][] map) {
		PriorityQueue<Fish> pq = new PriorityQueue<>();

		for (int r = 0; r < 4; r++) {
			for (int c = 0; c < 4; c++) {
				if(map[r][c] == null || map[r][c].num == 0) continue;
				pq.offer(map[r][c]);
			}
		}

		int start = 0, end = pq.peek().num;
		
		while(start < end) {
			Fish fish = new Fish(-1, -1, 16, -1);
			for (int r = 0; r < 4; r++) {
				for (int c = 0; c < 4; c++) {
					if(map[r][c] == null || map[r][c].num == 0 || map[r][c].num <= start) continue;
					if(map[r][c].num <= fish.num) fish = map[r][c];
				}
			}
			
			start = fish.num;
			
			for(int d = 0; d < 8; d++) {
				int nr = fish.r + dr[(fish.dir + d) % 8];
				int nc = fish.c + dc[(fish.dir + d) % 8];
				// 물고기는 공간의 경계를 넘거나, 상어가 있는 칸은 이동X
				if(!isIn(nr, nc) || (map[nr][nc] != null && map[nr][nc].num == 0)) continue;
				
				// 해당 물고기는 (fish.r, fish.c)에서 (nr, nc)로 이동
				if(map[nr][nc] == null) map[fish.r][fish.c] = null;
				else {
					map[fish.r][fish.c] = map[nr][nc];
					map[fish.r][fish.c].r = fish.r;
					map[fish.r][fish.c].c = fish.c;
				}
				
				map[nr][nc] = fish;
				map[nr][nc].dir = (fish.dir + d) % 8;
				map[nr][nc].r = nr;
				map[nr][nc].c = nc;
				break;
			}
		}
	}
	
	public static Fish[][] copyMap(Fish[][] map) {
		Fish[][] copyMap = new Fish[4][4];
		for (int r = 0; r < 4; r++) {
			for (int c = 0; c < 4; c++) {
				if(map[r][c] == null) continue;
				copyMap[r][c] = new Fish(map[r][c].r, map[r][c].c, map[r][c].num, map[r][c].dir); 
			}
		}
		return copyMap;
	}
	
	public static boolean isIn(int r, int c) {
		return 0 <= r && r < 4 && 0 <= c && c < 4;
	}
}