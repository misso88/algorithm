import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        Deque<String> dq = new LinkedList<>();
        
        for(String city : cities) {
            city = city.toLowerCase();
            if(!dq.isEmpty() && dq.contains(city)) {
                // 캐시에 해당 데이터가 있을 때
                answer += 1;
                dq.remove(city);
            } else {
                // 캐시에 해당 데이터가 없을 때
                answer += 5;
            }
            dq.offerLast(city);
            if(cacheSize < dq.size()) dq.pollFirst(); // 캐시 크기 제한
        }
        
        return answer;
    }
}