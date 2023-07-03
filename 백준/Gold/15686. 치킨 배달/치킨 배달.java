import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, M, answer = Integer.MAX_VALUE;
    static List<Point> house, chicken;
    static Point[] selectChicken;

    static class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 도시의 크기
        M = Integer.parseInt(st.nextToken()); // 폐업시키지 않을 최대 치킨집 수

        house = new ArrayList<>();
        chicken = new ArrayList<>();
        selectChicken = new Point[M];

        for(int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c = 0; c < N; c++) {
                int input = Integer.parseInt(st.nextToken());
                if(input == 1) house.add(new Point(r, c)); // 집의 좌표
                else if(input == 2) chicken.add(new Point(r, c)); // 치킨집의 좌표
            }
        }

        comb(0, 0); // 폐업시키지 않을 치킨집 선책
        System.out.println(answer);
    }

    public static void comb(int cnt, int start) {
        if(cnt == M) {
            getChickenDistance(); // 치킨 거리 계산
            return;
        }

        for(int i = start, size = chicken.size(); i < size; i++) {
            selectChicken[cnt] = chicken.get(i);
            comb(cnt + 1, i + 1);
        }
    }

    public static void getChickenDistance() {
        int sum = 0;

        for(int i = 0, size = house.size(); i < size; i++) {
            int distance = Integer.MAX_VALUE; // 해당 집의 치킨 거리
            for(int j = 0; j < M; j++) {
                distance = Math.min(distance, Math.abs(house.get(i).r - selectChicken[j].r) + Math.abs(house.get(i).c - selectChicken[j].c));
            }
            sum += distance;
        }

        answer = Math.min(answer, sum);
    }
}