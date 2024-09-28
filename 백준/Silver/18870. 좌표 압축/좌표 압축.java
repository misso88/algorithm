import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());	// 좌표 개수
		int[] points = new int[N]; 
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			points[i] = Integer.parseInt(st.nextToken());
			pq.offer(points[i]);
		}
		
		Map<Integer, Integer> map = new HashMap<>();
		
		int cnt = 0, pre = pq.poll();
		map.put(pre, cnt);
		
		while(!pq.isEmpty()) {
			int now = pq.poll();
			
			if(pre != now) map.put(now, ++cnt);
			pre = now;
		}
		
		for (int i = 0; i < N; i++) {
			sb.append(map.get(points[i])).append(" ");
		}
		
		System.out.println(sb);
	}
}