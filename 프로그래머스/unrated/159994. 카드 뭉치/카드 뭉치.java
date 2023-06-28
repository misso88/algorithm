class Solution {
    public String solution(String[] cards1, String[] cards2, String[] goal) {
        int size1 = cards1.length, size2 = cards2.length;
        int idx1 = 0, idx2 = 0;
        
        for(String s : goal) {
            if(idx1 < size1 && s.equals(cards1[idx1])) idx1++;
            else if(idx2 < size2 && s.equals(cards2[idx2])) idx2++;
            else return "No";
        }
        
        return "Yes";
    }
}