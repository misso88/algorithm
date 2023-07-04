import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N, answer;
    static int[][] map, likeStudent;
    static int[] dr = {-1, 0, 1, 0}, dc = {0, 1, 0, -1};
    
    static class Seat implements Comparable<Seat> {
        int r, c, like, empty; // 행,열, 인접한 좋아하는 학생 수, 인접한 비어있는 자리 수

        public Seat(int r, int c, int like, int empty) {
            this.r = r;
            this.c = c;
            this.like = like;
            this.empty = empty;
        }

		@Override
		public int compareTo(Seat o) {
			if(o.like == this.like) {
				if(o.empty == this.empty) {
					if(o.r == this.r) return this.c - o.c;
					else return this.r - o.r;
				} else return o.empty - this.empty;
			} else return o.like - this.like;
		}
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine()); // 교실 크기

        map = new int[N][N];
        likeStudent = new int[N * N + 1][4];

        for(int i = 0, total = N * N; i < total; i++) {
            st = new StringTokenizer(br.readLine());
            int student = Integer.parseInt(st.nextToken());
            for(int s = 0; s < 4; s++) {
                // 좋아하는 학생 번호
                likeStudent[student][s] = Integer.parseInt(st.nextToken());
            }
            chooseSeat(student);
        }
        
        for(int r = 0; r < N; r++) {
            for(int c = 0; c < N; c++) {
            	int like = 0;
            	for(int d = 0; d < 4; d++) {
                	int nr = r + dr[d];
                	int nc = c + dc[d];
                	if(!isIn(nr, nc)) continue;
                	
                	for(int s = 0; s < 4; s++) {
            			if(map[nr][nc] == likeStudent[map[r][c]][s]) like++;
            		}
                }
            	
            	if(like != 0) answer += Math.pow(10, like - 1);
            }
        }
        
        System.out.println(answer);
    }

    public static void chooseSeat(int student) {
        PriorityQueue<Seat> pq = new PriorityQueue<>();

        for(int r = 0; r < N; r++) {
            for(int c = 0; c < N; c++) {
            	if(map[r][c] != 0) continue;
            	
                int like = 0, empty = 0;
                for(int d = 0; d < 4; d++) {
                	int nr = r + dr[d];
                	int nc = c + dc[d];
                	if(!isIn(nr, nc)) continue;
                	
                	if(map[nr][nc] == 0) empty++;
                	else {
                		for(int s = 0; s < 4; s++) {
                			if(map[nr][nc] == likeStudent[student][s]) {
                				like++;
                				break;
                			}
                		}
                	}
                }

                pq.offer(new Seat(r, c, like, empty));
            }
        }

        Seat seat = pq.poll();
        map[seat.r][seat.c] = student; // 자리 선택
    }

    public static boolean isIn(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < N;
    }
}