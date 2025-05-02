import java.util.*;

class Solution {
    static int[] answer = new int[2];
    static int[] discounts = {10, 20, 30, 40};
    static int emoticonLen;
    static int[][] _users;
    static int[] _emoticons, emoticonDiscounts;
    
    public int[] solution(int[][] users, int[] emoticons) {
        _users = users;
        _emoticons = emoticons;
        emoticonLen = emoticons.length;
        emoticonDiscounts = new int[emoticonLen];
        
        perm(0);
        
        return answer;
    }
    
    public static void perm(int cnt) {
        if(emoticonLen <= cnt) {
            int subscriberCnt = 0, sales = 0;
            for(int i = 0, len = _users.length; i < len; i++) {
                int price = 0;
                for(int j = 0; j < emoticonLen; j++) {
                    if(_users[i][0] <= emoticonDiscounts[j]) {
                        price += _emoticons[j] * (100 - emoticonDiscounts[j]) / 100;
                    }
                }
                
                if(_users[i][1] <= price) {
                    // 구매액이 일정 금액 이상이면 이모티콘 플러스 가입
                    subscriberCnt++;
                } else {
                    // 구매액이 일정 금액 미만
                    sales += price;
                }
            }
            
            if(answer[0] < subscriberCnt) {
                answer[0] = subscriberCnt;
                answer[1] = sales;
            } else if(answer[0] == subscriberCnt && answer[1] < sales) {
                answer[1] = sales;
            }
            
            return;
        }
        
        for(int i = 0; i < 4; i++) {
            emoticonDiscounts[cnt] = discounts[i];
            perm(cnt + 1);
        }
    }
}