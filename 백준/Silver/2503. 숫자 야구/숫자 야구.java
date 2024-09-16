import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int result = 0;
	static int[] numbers = new int[3];
	static boolean[] isSelected = new boolean[10];
	static Game[] games;
	
	static class Game {
		int number, strike, ball;
		
		public Game(int number, int strike, int ball) {
			this.number = number;  // 질문한 3자리 수
			this.strike = strike;  // 스트라이크 개수
			this.ball = ball;      // 볼 개수
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());  // 질문 횟수
		games = new Game[N];                  // 게임 정보
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			games[i] = new Game(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		perm(0);
		System.out.println(result);
	}
	
	public static void perm(int cnt) {
		if(cnt == 3) {
			// 순열로 3자리 숫자를 구한 후, 모든 게임 질문에 통과면 가능한 정답값이라고 판단 
			for(int i = 0; i < N; i++) {
				Game game = games[i];
				int number = game.number;
				int digit = 100, strike = 0, ball = 0;
				
				for(int j = 0; j < 3; j++) {
					if(number / digit == numbers[j]) strike++;
					for(int k = 0; k < 3; k++) {
						if(number / digit == numbers[k]) ball++;
					}
						
					number %= digit;
					digit /= 10;
				}
				
				if(!(game.strike == strike && game.ball == ball - strike)) return; 
			}
			result ++;
			return;
		}
		
		for(int i = 1; i < 10; i++) {
			if(isSelected[i]) continue;
			
			numbers[cnt] = i;
			isSelected[i] = true;
			perm(cnt + 1);
			isSelected[i] = false;
		}
	}
}