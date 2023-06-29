import java.util.*;

class Solution {
    static int answer;
    static boolean[] visit;
    
    public int solution(int k, int[][] dungeons) {
        int total = dungeons.length;
        visit = new boolean[total];
        
        perm(k, dungeons, 0, total);
        return answer;
    }
    
    public static void perm(int k, int[][] dungeons, int cnt, int total) {
        if(k == 0 || cnt == total) {
            answer = Math.max(answer, cnt);
            return;
        }
        
        for(int i = 0; i < total; i++) {
            if(visit[i] || k < dungeons[i][0]) continue;
            
            visit[i] = true;
            perm(k - dungeons[i][1], dungeons, cnt + 1, total);
            visit[i] = false;
        }
        answer = Math.max(answer, cnt);
    }
}