class Solution {
    public int solution(String[] babbling) {
        String[] talking = {"aya", "ye", "woo", "ma"};
        int answer = 0;
        
        for(String b : babbling) {
            for(String t : talking) {
                if(b.contains(t)) b = b.replace(t, "*");
            }
            b = b.replace("*", "");
            if(b.length() == 0) answer++;
        }
        
        return answer;
    }
}