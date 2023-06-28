import java.util.*;

class Solution {
    public int solution(String s) {
        int answer = 0;
                
        Stack<Character> stack = new Stack<>();
        
        for(int x = 0, len = s.length(); x < len; x++) {
            boolean isOk = true;
            for(char c : s.toCharArray()) {
                if(c == '(' || c == '{' || c == '[') stack.push(c);
                else if(!stack.isEmpty() && ((c == ')' && stack.peek() == '(')
                         || (c == '}' && stack.peek() == '{')
                         || (c == ']' && stack.peek() == '['))) stack.pop(); 
                else {
                    isOk = false;
                    break;
                }
            }
            
            if(stack.isEmpty() && isOk) answer++;
            
            stack.clear();
            s = s.substring(1) + s.charAt(0);
        }
        
        return answer;
    }
}