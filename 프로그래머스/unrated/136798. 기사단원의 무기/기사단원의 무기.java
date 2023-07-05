import java.util.*;

class Solution {
    public int solution(int number, int limit, int power) {
        int answer = 0;
        
        for(int i = 1; i <= number; i++) {
            int cnt = (Math.sqrt(i) - (int)Math.sqrt(i) == 0) ? 1 : 0;
            for(int j = 1; j < Math.sqrt(i); j++) {
                if(i % j == 0) cnt += 2;
            }
            if(limit < cnt) answer += power;
            else answer += cnt;
        }
        
        return answer;
    }
}