import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int start = 0, end = progresses.length;
        List<Integer> list = new ArrayList<>();
        
        while(start < end){
            for(int i = start; i < end; i++) {
                progresses[i] += speeds[i];
            }
            
            if(progresses[start] < 100) continue;
            
            int cnt = 1;
            for(int i = start + 1; i < end; i++) {
                if(progresses[i] < 100) break;
                cnt++;
            }
            start += cnt;
            list.add(cnt);
        }
        
        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}