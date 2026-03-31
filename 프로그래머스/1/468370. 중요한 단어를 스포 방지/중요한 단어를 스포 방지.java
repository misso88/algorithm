import java.util.*;

class Solution {
    // **간편한 풀이법을 보고 리팩토링
    public int solution(String message, int[][] spoiler_ranges) {
        StringBuilder sb = new StringBuilder(message);
        
        // 스포일러 구간 문자를 *로 대체
        for(int[] range: spoiler_ranges) {
            for(int r = range[0]; r <= range[1]; r++) {
                // 하나의 스포 방지 구간에 여러 단어가 포함될 수 있으니 공백 패스
                if(sb.charAt(r) == ' ') continue;
                sb.setCharAt(r, '*');
            }
        }
        
        Set<String> set = new HashSet<>(Arrays.asList(sb.toString().split(" ")));
        Set<String> importantSet = new HashSet<>();
        
        for(String word: message.split(" ")) {
            if(set.contains(word)) continue;
            // 스포 방지 단어인 경우
            importantSet.add(word);
        }
        
        return importantSet.size();
    }
}