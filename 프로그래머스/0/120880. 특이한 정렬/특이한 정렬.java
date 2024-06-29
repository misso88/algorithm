import java.util.*;

class Solution {
    public int[] solution(int[] numlist, int n) {
        Integer[] answer = Arrays.stream(numlist).boxed().toArray(Integer[]::new);
        
        Arrays.sort(answer, (n1, n2) -> {
            if(Math.abs(n - n1) == Math.abs(n - n2)) return n2 - n1;
            else return Math.abs(n - n1) - Math.abs(n - n2);
            
        });
        
        return Arrays.stream(answer).mapToInt(Integer::intValue).toArray();
    }
}