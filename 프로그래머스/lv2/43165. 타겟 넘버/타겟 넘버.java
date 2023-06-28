class Solution {
    public int solution(int[] numbers, int target) {
		return process(numbers, target, 0, 0);
	}

	public static int process(int[] numbers, int target, int sum, int idx) {
		if (idx == numbers.length) {
			if (sum == target) return 1;
			return 0;
		}
		
		return process(numbers, target, sum + numbers[idx], idx+1)
				+ process(numbers, target, sum - numbers[idx], idx+1);
	}
}