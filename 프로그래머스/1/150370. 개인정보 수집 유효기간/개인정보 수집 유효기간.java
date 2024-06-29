import java.util.*;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        List<Integer> answer = new ArrayList<>();
        
        Map<String, Integer> expireOfTerms = new HashMap<>();
        
        for(String t : terms) {
            expireOfTerms.put(t.split(" ")[0], Integer.parseInt(t.split(" ")[1]) * 28);
        }
        
        for(int i = 0, size = privacies.length; i < size; i++) {
            if((convertDateToDay(privacies[i].split(" ")[0]) + expireOfTerms.get((privacies[i].split(" ")[1])))
               <= convertDateToDay(today)) {
                   answer.add(i + 1);
               }
        }
        
        return answer.stream().mapToInt(i -> i).toArray();
    }
    
    public static int convertDateToDay(String date) {
        int yy = Integer.parseInt(date.split("\\.")[0].substring(2));
        int mm = Integer.parseInt(date.split("\\.")[1]);
        int dd = Integer.parseInt(date.split("\\.")[2]);
        return (yy * 28 * 12) + (mm * 28) + dd;
    }  
}