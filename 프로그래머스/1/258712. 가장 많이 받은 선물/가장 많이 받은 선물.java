import java.util.*;

class Solution {
    
    static class User {
        int givenCnt;           // 준 선물 수
        int receivedCnt;        // 받은 선물 수
        int score;              // 선물 지수
        int nextReceivedCnt;    // 다음달에 받을 선물 수
        Map<String, Integer> toFriends = new HashMap<>();    // 친구별 준 선물 수
        Map<String, Integer> fromFriends = new HashMap<>();  // 친구별 받은 선물 수
        
        public User(String[] friends) {
            for(String friend : friends) {
                toFriends.put(friend, 0);
                fromFriends.put(friend, 0);
            }
        }
    }
    
    public int solution(String[] friends, String[] gifts) {
        Map<String, User> users = new HashMap<>();
        
        for(String friend : friends) {
            users.put(friend, new User(friends));
        }
        
        for(String gift : gifts) {
            String from = gift.split(" ")[0];   // 선물을 준 친구
            String to = gift.split(" ")[1];     // 선물을 받은 친구
            User fromUser = users.get(from);
            User toUser = users.get(to);
            
            // 주고 받은 선물 수 카운팅
            fromUser.givenCnt++;
            toUser.receivedCnt++;
            
            // 친구별 주고 받은 선물 수 카운팅
            fromUser.toFriends.put(to, fromUser.toFriends.get(to) + 1);
            toUser.fromFriends.put(from, toUser.fromFriends.get(from) + 1);
        }
        
        // 선물 지수 계산
        for(String friend : friends) {
            User user = users.get(friend);
            user.score = user.givenCnt - user.receivedCnt;
        }
        
        // 다음 달에 받을 최대 선물 수
        int answer = 0;
        
        for(String me : friends) {
            User user = users.get(me);
            
            for(String friend : friends) {
                if(user.fromFriends.get(friend) < user.toFriends.get(friend)
                   || (user.fromFriends.get(friend) == user.toFriends.get(friend)
                   && users.get(friend).score < user.score)) {
                    // 내가 더 많은 선물을 줬거나
                    // 주고 받은 선물 수가 같지만 내 선물 지수가 더 높으면
                    user.nextReceivedCnt++;
                    
                    if(answer < user.nextReceivedCnt) answer = user.nextReceivedCnt;
                }
            }
        }
        
        return answer;
    }
}