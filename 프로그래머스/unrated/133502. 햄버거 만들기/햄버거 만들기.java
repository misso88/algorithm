import java.util.*;
import java.util.stream.Collectors;

class Solution {
    public int solution(int[] ingredient) {
        int answer = 0;
        List<Integer> list = Arrays.stream(ingredient).boxed().collect(Collectors.toList());
        
        for(int i = 3; i < list.size(); i++) {
            if(i < 3) continue;
            if(list.get(i) == 1 && list.get(i - 1) == 3 
               && list.get(i - 2) == 2 && list.get(i - 3) == 1) {
                list.remove(i);
                list.remove(i - 1);
                list.remove(i - 2);
                list.remove(i - 3);
                answer++;
                i -= 4;
            }
        }
        
        return answer;
    }
}