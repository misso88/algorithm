import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int K = Integer.parseInt(st.nextToken());	// 가지고 있는 랜선 개수
		int N = Integer.parseInt(st.nextToken());	// 필요한 랜선 개수
		long[] lines = new long[K];					// 랜선 길이 목록
		
		long start = 1, end= 1;
		
		for (int i = 0; i < K; i++) {
			lines[i] = Long.parseLong(br.readLine());
			if(end < lines[i]) end = lines[i];
		}
		
		long result = 1;
		
		while(start <= end) {
			int cnt = 0;
			long middle = (start + end) / 2;
			
			for (int i = 0; i < K; i++) {
				cnt += lines[i] / middle;
			}
			
			if(N <= cnt) {
				result = Math.max(result, middle);
				start = middle + 1;
			} else {
				end = middle - 1;
			}
		}
		
		System.out.println(result);
	}
}