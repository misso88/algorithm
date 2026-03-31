import java.util.*;

class Solution {
    public int solution(String message, int[][] spoiler_ranges) {
        Set<String> importantSet = new HashSet<>();
        boolean[] isSpoiler = new boolean[message.length()];
        
        // 스포일러 여부 배열 초기 세팅
        for(int[] range: spoiler_ranges) {
            for(int i = range[0]; i <= range[1]; i++) {
                isSpoiler[i] = true;
            }
        }
        
        String[] words = message.split(" ");
        Set<String> spoSet = new HashSet<>();     // 스포 방지 단어
        Set<String> notSpoSet = new HashSet<>();  // 스포 방지 단어가 아닌 단어
        
        int index = 0;
        
        for(String word: words) {
            int wordLen = word.length();
            boolean isSpo = false;
            
            for(int i = index; i < index + wordLen; i++) {
                // 스포 방지 구간인지 확인
                if(isSpoiler[i]) {
                    isSpo = true;
                    break;
                }
            }
            if(isSpo) spoSet.add(word);
            else notSpoSet.add(word);
            
            index += wordLen + 1;
        }
    
        // 1. 스포 방지 단어여야 한다
        for(String word: spoSet) {
            // 2. 스포 방지 구간이 아닌 구간에 등정한 적이 없어야 한다
            if(!notSpoSet.contains(word)) {
                // 3. 이전에 공개된 스포 방지 단어와 중복되면 안된다
                importantSet.add(word);
            }
        }
        
        return importantSet.size();
    }
}