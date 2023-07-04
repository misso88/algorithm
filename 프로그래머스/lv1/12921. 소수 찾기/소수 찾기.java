import java.util.*;

class Solution {
    public int solution(int n) {
        int answer = 1;
        
        for(int i = 3; i <= n; i++) {
            if(isPrimeNumber(i)) answer++;
        }
        
        return answer;
    }
    
    public static boolean isPrimeNumber(int num) {
        for(int i = 2, size = (int)Math.sqrt(num); i <= size; i++) {
            if(num % i == 0) return false;
        }
        return true;
    }
}