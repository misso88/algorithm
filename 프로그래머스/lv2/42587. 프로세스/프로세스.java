import java.util.*;

class Solution {
    static class Process {
        int num, priority;
        
        public Process(int num, int priority) {
            this.num = num;
            this.priority = priority;
        }
    }
    
    public int solution(int[] priorities, int location) {
        List<Process> list = new LinkedList<>();
        
        for(int i = 0, len = priorities.length; i < len; i++) {
            list.add(new Process(i, priorities[i]));
        }
        
        while(true) {
            Process now = list.remove(0);
            boolean run = true; // 해당 프로세스 실행 여부

            for(int i = 0, size = list.size(); i < size; i++) {
                if(list.get(i).priority <= now.priority) continue;
                
                list.add(now);
                run = false;            
                break;
            }
            
            if(run && now.num == location) return priorities.length - list.size();
        }
    }
}