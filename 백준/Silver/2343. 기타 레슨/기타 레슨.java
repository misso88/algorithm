import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] videos;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());	// 강의 수
		M = Integer.parseInt(st.nextToken());	// 블루레이 수
		
		videos = new int[N + 1];	// 강의 크기 누적 합
		
		int maxSize = 0;			// 강의 하나의 최대 크기
		
		st = new StringTokenizer(br.readLine());
		
		for(int i = 1; i <= N; i++ ) {
			videos[i] = videos[i - 1] + Integer.parseInt(st.nextToken());
			maxSize = Math.max(maxSize, videos[i] - videos[i - 1]);
		}
		
		int start = maxSize, end = videos[N], mid = (start + end) / 2;
		int result = end;

		while(start < end) {
			mid = (start + end) / 2;
			
			if(isAvailable(mid)) {
				end = mid;
				result = Math.min(result, mid);
			} else {
				start = mid + 1;
			}
		}
		
		System.out.println(result);
	}
	
	public static boolean isAvailable(int size) {
		int cnt = 1;
				
		for(int i = 1, prev = 0; i <= N; i++) {
			if(size < videos[i] - videos[prev]) {
				prev = i - 1;
				cnt++;
			}
		}

		return cnt <= M;
	}
}