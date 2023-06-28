import java.util.*;

class Solution {
    public int solution(int[][] triangle) {
        for(int i = triangle.length - 2; 0 <= i; i--) {
            for(int j = 0, len = triangle[i].length; j < len; j++) {
                triangle[i][j] += Math.max(triangle[i + 1][j], triangle[i + 1][j + 1]);
            }
        }
        return triangle[0][0];
    }
}