class Solution {
    // A[m][k] * B[k][n] = C[m][n]
    // A의 i행의 각 성분과 B의 j열의 각 성분을 순서대로 곱하여 더한 값은 C의 (i, j) 값이다.
    public int[][] solution(int[][] arr1, int[][] arr2) {
        int M = arr1.length, N = arr2[0].length, K = arr1[0].length;
        int[][] answer = new int[M][N];
        
        for(int i = 0; i < M; i++) {
            for(int j = 0; j < N; j++) {
                for(int k = 0; k < K; k++) {
                    answer[i][j] += arr1[i][k] * arr2[k][j];
                }
            }
        }
        
        return answer;
    }
}