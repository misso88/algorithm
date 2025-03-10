import java.util.*;

class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int[][] dp = new int[n + 1][m + 1];
        int[][] map = new int[n + 1][m + 1];
        
        for(int[] puddle : puddles) {
            // 물웅덩이 좌표
            map[puddle[1]][puddle[0]] = 1;
        } 
        
        dp[0][1] = 1;
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= m; j++) {
                if(map[i][j] == 1) continue;
                dp[i][j] = (dp[i - 1][j] + dp[i][j - 1]) % 1000000007;
            }
        }

        return dp[n][m];
    }
}