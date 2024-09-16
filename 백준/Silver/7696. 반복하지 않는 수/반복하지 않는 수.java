import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	static List<Integer> ans = new ArrayList<>();
	static int[] numbers = new int[10];
	static boolean[] isSelected = new boolean[10];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		// 1000000 -> 26195083
		for (int i = 1; i <= 8; i++) {
			perm(0, i);
		}
		
		int input = -1;
		
		while(true) {
			input = Integer.parseInt(br.readLine());
			if(input == 0) break;
	
			sb.append(ans.get(input)).append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
	public static void perm(int cnt, int end) {
		if(cnt == end) {
			int result = 0, digit = 1;
			for (int i = cnt - 1; 0 <= i; i--) {
				result += numbers[i] * digit;
				digit *= 10;
			}
			ans.add(result);
			return;
		}
		
		for(int i = 0; i < 10; i++) {
			if(isSelected[i] || (i == 0 && cnt == 0 && end != 1)) continue;
			
			numbers[cnt] = i;
			isSelected[i] = true;
			perm(cnt + 1, end);
			isSelected[i] = false;
		}
	}
}