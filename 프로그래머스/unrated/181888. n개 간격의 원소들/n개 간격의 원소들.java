class Solution {
    public int[] solution(int[] num_list, int n) {
        int[] result = new int[1 + ((num_list.length - 1)/n)];
        int idx = 0;
        
        for(int i = 0, size = num_list.length; i < size; i += n) {
            result[idx++] = num_list[i];
        }
        
        return result;
    }
}