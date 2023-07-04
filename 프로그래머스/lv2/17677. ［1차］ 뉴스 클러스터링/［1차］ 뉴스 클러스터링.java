import java.util.*;
import java.util.regex.*;

class Solution {
    public int solution(String str1, String str2) {
        double intersection = 0, union = 0; // 교집합, 합집합
        int cnt1 = 0, cnt2 = 0; // 각 집합의 원소 수
        Map<String, Integer> map1 = new HashMap<>();
        Map<String, Integer> map2 = new HashMap<>();
        
        // 두 글자씩 끊어서 다중 집합의 원소로 만들기
        for(int i = 0, len = str1.length(); i < len - 1; i++) {
            String key = str1.substring(i, i + 2).toLowerCase();
            if(Pattern.matches("^[a-zA-A]*$", key)) {
                cnt1++;
                map1.put(key, map1.getOrDefault(key, 0) + 1); 
            }
        }
        for(int i = 0, len = str2.length(); i < len - 1; i++) {
            String key = str2.substring(i, i + 2).toLowerCase();
            if(Pattern.matches("^[a-zA-A]*$", key)) {
                cnt2++;
                map2.put(key, map2.getOrDefault(key, 0) + 1); 
            }
        }
   
        // 교집합 구하기
        for(String key : map1.keySet()) {
            if(map2.containsKey(key)) intersection += Math.min(map1.get(key), map2.get(key));
        }

        union = cnt1 + cnt2 - intersection;
        return union == 0 ? 65536 : (int)(intersection / union * 65536);
    }
}