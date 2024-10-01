import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 사다리 개수
        int M = Integer.parseInt(st.nextToken()); // 뱀 개수
        
        int[] board = new int[101];
        for (int i = 1; i <= 100; i++) {
            board[i] = i;
        }

        for (int i = 0; i < N + M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            board[x] = y;
        }

        Queue<int[]> queue = new LinkedList<>();
        boolean[] visited = new boolean[101];
        
        queue.offer(new int[]{1, 0}); // 시작 위치와 주사위 굴린 횟수
        visited[1] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int pos = current[0];
            int count = current[1];

            if (pos == 100) {
                System.out.println(count);
                return;
            }

            for (int i = 1; i <= 6; i++) {
                int nextPos = pos + i;
                if (nextPos > 100) continue;
                
                nextPos = board[nextPos]; // 사다리나 뱀 적용
                
                if (!visited[nextPos]) {
                    visited[nextPos] = true;
                    queue.offer(new int[]{nextPos, count + 1});
                }
            }
        }
    }
}