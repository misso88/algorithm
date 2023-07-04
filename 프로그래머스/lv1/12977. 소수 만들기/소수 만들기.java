class Solution {
    static int answer;
    static int[] input, numbers;
    
    public int solution(int[] nums) {
        input = nums.clone();
        numbers = new int[3];
        comb(0, 0); // 주어진 숫자 중 3개의 수 구하기
        return answer;
    }
    
    public static void comb(int cnt, int start) {
        if(cnt == 3) {
            if(isPrimeNumber(numbers[0] + numbers[1] + numbers[2])) answer++;
            return;
        }
        
        for(int i = start, len = input.length; i < len; i++) {
            numbers[cnt] = input[i];
            comb(cnt + 1, i + 1);
        }
    }
    
    public static boolean isPrimeNumber(int num) {
        for(int i = 2, size = num / 2; i <= size; i++) {
            if(num % i == 0) return false;
        }
        return true;
    }
}