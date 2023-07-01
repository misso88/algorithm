import java.util.*;

class Solution {
    public int solution(int[] citations) {
        int answer = 0;
        Arrays.sort(citations);
        
        for(int i = 0, len = citations.length; i < len; i++){
            if(len - i <= citations[i]) answer++;
        }
        
        return answer;
    }
}