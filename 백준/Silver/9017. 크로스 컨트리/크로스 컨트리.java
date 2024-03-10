import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Team implements Comparable<Team> {
		// 팀 번호, 총 주자 수, 점수 받는 주자 카운팅, 팀 점수, 다섯 번 째 선수 점수
		int teamNum, totalPlayerCount, playerCount, teamScore, fifthPlayer;
		
		public Team(int teamNum) {
			this.teamNum = teamNum;
			this.totalPlayerCount = 1;
			this.playerCount = 0; 
			this.teamScore = 0;
			this.fifthPlayer = Integer.MAX_VALUE;
		}

		@Override
		public int compareTo(Team t) {
			if(this.teamScore == t.teamScore) return this.fifthPlayer - t.fifthPlayer;
			return this.teamScore - t.teamScore;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스
		int[] players; // 각 주자의 팀 번호 목록
		Map<Integer, Team> teams; // 팀 목록
		
		for(int t = 0; t < T; t++) {
			int N = Integer.parseInt(br.readLine()); // 주자 수
			st = new StringTokenizer(br.readLine()); // 각 주자의 팀 번호
			
			players = new int[N];
			teams = new HashMap<>();
			
			for (int n = 0; n < N; n++) {
				players[n] = Integer.parseInt(st.nextToken());
				if(teams.containsKey(players[n])) {
					teams.get(players[n]).totalPlayerCount++;
				} else {
					teams.put(players[n], new Team(players[n]));
				}
			}
			
			int score = 1;
			for (int n = 0; n < N; n++) {
				if(teams.get(players[n]).totalPlayerCount < 6) {
					teams.get(players[n]).teamScore = 40000;
					continue;
				}
				
				teams.get(players[n]).playerCount++;
				if(teams.get(players[n]).playerCount == 5) {
					// 5번째 주자 점수
					teams.get(players[n]).fifthPlayer = score;
				} else if(teams.get(players[n]).playerCount <= 4) {
					// 4명의 주자 점수 합산 전
					teams.get(players[n]).teamScore += score;
				}
				score++;
			}
			
			// 우승 팀 골라내기
			PriorityQueue<Team> pq = new PriorityQueue<>();
			
			for(Integer teamNum : teams.keySet()) {
				pq.offer(teams.get(teamNum));
			}
			
			sb.append(pq.poll().teamNum).append("\n");
		}
		
		System.out.println(sb.toString());
	}
}