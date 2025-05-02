class Solution {
    public int solution(int n, int w, int num) {
        int totalFloor = n / w;                 // w개가 꽉 채워져있는 최소 층
        int currentFloor = (num - 1) / w + 1 ;  // num의 현재 층
        
        int answer = totalFloor - currentFloor;
        
        if(0 < n % w) {
            int numIdx = num % w == 0 ? w : num % w;
            int endIdx = n % w;
            
            if((currentFloor % 2 == (totalFloor + 1) % 2 && numIdx <= endIdx) ||
              (currentFloor % 2 != (totalFloor + 1) % 2 && (w - numIdx + 1) <= endIdx)) {
                answer++;
            }
        }
        
        
        return answer + 1;
    }
}