import java.util.*;

class Solution {
    static class Point {
        int r, c, dir, cost;
        
        public Point(int r, int c, int dir, int cost) {
            this.r = r;
            this.c = c;
            this.dir = dir;
            this.cost = cost;
        }
    }
    
    public int solution(int[][] board) {
        int N = board.length;                           // 보드 크기
        int[] dr = {-1, 0, 1, 0}, dc = {0, 1, 0, -1};   // 동서남북 방향
        Queue<Point> q = new LinkedList<>();          
        int[][] costArr = new int[N][N];
        
        for(int i = 0; i < N; i++) {
            Arrays.fill(costArr[i], Integer.MAX_VALUE);
        }
        
        // 초기값 세팅
        // 방향 d 값: -1=시작/0=상/1=우/2=하/3=좌
        q.offer(new Point(0, 0, -1, 0));
        costArr[0][0] = 0;
        
        // BFS
        while(!q.isEmpty()) {
            Point now = q.poll();
            for(int d = 0; d < 4; d++) {
                // if(now.dir == d) continue;

                int nr = now.r + dr[d];
                int nc = now.c + dc[d];
                if(!(0 <= nr && nr < N && 0 <= nc && nc < N) || board[nr][nc] == 1) continue;

                int cost = now.cost + 100;
                // 이전 방향과 현재 방향의 합이 홀수면 코너, 짝수면 직진
                if(now.dir != -1 && (now.dir + d) % 2 == 1) cost += 500;
                if(costArr[nr][nc] != Integer.MAX_VALUE && costArr[nr][nc] + 500 < cost) continue;
                    
                q.offer(new Point(nr, nc, (d + 6) % 4, cost));
                if(cost < costArr[nr][nc]) costArr[nr][nc] = cost;
            }
        }
        
        return costArr[N - 1][N - 1];
    }
}