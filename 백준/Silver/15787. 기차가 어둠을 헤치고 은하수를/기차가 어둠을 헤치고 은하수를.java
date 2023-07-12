import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static int[] train;
	static Set<Integer> set;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // 기차의 수
		int M = Integer.parseInt(st.nextToken()); // 명령의 수

		train = new int[N + 1]; // 기차 정보
		set = new HashSet<>(); // 은하수를 지난 기차 상태 기록

		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			switch (Integer.parseInt(st.nextToken())) {
			case 1:
				pickUp(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) - 1);
				break;
			case 2:
				getOut(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) - 1);
				break;
			case 3:
				moveBack(Integer.parseInt(st.nextToken()));
				break;
			case 4:
				moveForward(Integer.parseInt(st.nextToken()));
				break;
			default:
				break;
			}
		}
		
		// 은하수 지나가기
		int answer = 0;
		
		for (int n = 1; n <= N; n++) {
			if(set.contains(train[n])) continue; // 이미 목록에 존재하는 기록이면 건널 수 X
			
			set.add(train[n]);
			answer++;
		}
		
		System.out.println(answer);
	}

	/**
	 * i번째 기차에(1 ≤ i ≤ N) x번째 좌석에(1 ≤ x ≤ 20) 사람을 태워라. 
	 * 이미 사람이 타있다면, 아무런 행동을 하지 않는다.
	 * @param i
	 * @param x
	 */
	public static void pickUp(int i, int x) {
		train[i] |= 1 << x;
	}

	/**
	 * i번째 기차에 x번째 좌석에 앉은 사람은 하차한다. 
	 * 만약 아무도 그자리에 앉아있지 않았다면, 아무런 행동을 하지 않는다.
	 * @param i
	 * @param x
	 */
	public static void getOut(int i, int x) {
		train[i] &= ~(1 << x);
	}

	/**
	 * i번째 기차에 앉아있는 승객들이 모두 한칸씩 뒤로간다. 
	 * 만약 20번째 자리에 사람이 앉아있었다면 그 사람은 이 명령 후에 하차한다.
	 * @param i
	 */
	public static void moveBack(int i) {
		train[i] <<= 1;
		train[i] &= ~(1 << 20); // 20번째 사람 하차
	}

	/**
	 * i번째 기차에 앉아있는 승객들이 모두 한칸씩 앞으로간다.
	 * 만약 1번째 자리에 사람이 앉아있었다면 그 사람은 이 명령 후에 하차한다.
	 * @param i
	 */
	public static void moveForward(int i) {
		train[i] >>= 1;
	}
}