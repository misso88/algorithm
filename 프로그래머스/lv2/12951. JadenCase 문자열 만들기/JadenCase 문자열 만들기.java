class Solution {
    public String solution(String s) {
        StringBuilder sb = new StringBuilder();
        s = s.toLowerCase();
        String pre = " ";
        
        for(int i = 0, size = s.length(); i < size; i++) {
            String now = s.charAt(i) + "";
            if(pre.equals(" ")) {
                // 단어의 맨 앞글자
                now = now.toUpperCase();
            }
            
            sb.append(now);
            pre = now;
        }
        
        return sb.toString();
    }
}